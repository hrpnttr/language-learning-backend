package id.languagelearning.lms_backend.scourses;

import id.languagelearning.lms_backend.dto.ScoursesDto.Create;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.Instant;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/scourses")
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true") // adjust if your FE origin differs
public class ScoursesController {

    private final ScoursesRepository repo;

    public ScoursesController(ScoursesRepository repo) {
        this.repo = repo;
    }

    // DTOs
    public record CreateScoursesReq(String studentId, List contents) {}
    public record ScoursesRes(String id, String studentId, List contents, Instant createdAt, Instant updatedAt) {}

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Create body) {
        if (body.contents == null || body.contents.isEmpty()) {
            return ResponseEntity.badRequest().body("contents cannot be empty");
        }

        List<String> courseIds = body.contents.stream()
                .map(c -> c.courseId) // direct field access
                .filter(id -> id != null && !id.isBlank())
                .toList();

        if (courseIds.isEmpty()) {
            return ResponseEntity.badRequest().body("contents must include at least one courseId");
        }

        // map DTO -> entity items
        List<Contents> incoming = body.contents.stream()
                .filter(c -> c.courseId != null && !c.courseId.isBlank())
//                .map(c -> new Contents(c.courseId, c.title))
                .map(c -> {
                    Contents e = new Contents(); // no-arg
                    e.setCourseId(c.courseId);
                    e.setTitle(c.title);
                    return e;
                })
                .toList();

        if (incoming.isEmpty()) {
            return ResponseEntity.badRequest().body("contents must include at least one courseId");
        }

        // fetch existing doc for this student
        Optional<Scourses> opt = repo.findByStudentId(body.studentId);
        Scourses target;
        boolean created;

        if (opt.isEmpty()) {
            target = new Scourses(body.studentId, new ArrayList<>());
            created = true;
        } else {
            target = opt.get();
            created = false;
        }

        // de-dup by courseId (case-insensitive); add only new ones
        Set<String> existing = target.getContents().stream()
                .map(c -> c.getCourseId().toLowerCase())
                .collect(Collectors.toSet());

        int added = 0;
        for (Contents c : incoming) {
            String key = c.getCourseId().toLowerCase();
            if (existing.add(key)) { // added returns true only if not present
                target.getContents().add(c);
                added++;
            }
        }

        boolean dup = repo.existsByStudentIdAndContentsCourseIdIn(body.studentId, courseIds);
        if (dup) {
            boolean course = repo.existsByContentsCourseIdIn(courseIds);
            if (course) {
                return ResponseEntity.status(409).body("One or more courses already exist for this student");
            }
        }

        Scourses saved = repo.save(target);

        if (created) {
            return ResponseEntity
                    .created(URI.create("/api/scourses/enroll" + saved.getId()))
                    .body(saved);
        }

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Scourses> all() {
        return repo.findAll();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Scourses> byStudentId(@PathVariable String studentId) {
        return repo.findByStudentId(studentId).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}