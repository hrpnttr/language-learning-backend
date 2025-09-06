package id.languagelearning.lms_backend.courses;

import id.languagelearning.lms_backend.dto.CoursesDtos.Create;
import id.languagelearning.lms_backend.dto.CoursesDtos.Update;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CoursesRepository repo;
    public CourseController(CoursesRepository repo) {this.repo = repo;}

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Create body) {
        Courses c = new Courses();
        c.setTitle(body.title);
//        c.getDescription(body.description);
        c.getLevel();
        c.getLanguage();
//        c.getPrice(body.price);
//        c.getDurationHours(body.durationHours);
//        c.getTags(body.tags);
        Courses saved = repo.save(c);
        return ResponseEntity.created(URI.create("/api/course/" + saved.getId())).body(saved);
    }

    @GetMapping
    public Page<Courses> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String q
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return (q == null || q.isBlank())
                ? repo.findAll(pageable)
                : repo.findByTitleContainingIgnoreCase(q, pageable);
    }

    // List with pagination & simple search: /api/courses?page=0&size=10&q=eng
    @GetMapping("/{id}")
    public ResponseEntity<Courses> get(@PathVariable String id) {
        return repo.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Update body) {
        return repo.findById(id).map(c -> {
            if (body.title != null && !body.title.isBlank()) c.setTitle(body.title);
            if (body.description != null && !body.description.isBlank()) c.setDescription(body. description);
            if (body.level != null && !body.level.isBlank()) c.setLevel(body.level);
            if (body.language != null && !body.language.isBlank()) c.setLanguage(body.language);
            if (body.price != null) c.setPrice(body.price);
            if (body.durationHours != null) c.setDurationHours(body.durationHours);
            if (body.tags != null) c.setTags(body.tags);
            if (body.published != null) c.setPublished(body.published);
            c.setUpdatedAt(Instant.now());
            return ResponseEntity.ok(repo.save(c));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
