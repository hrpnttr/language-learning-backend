package id.languagelearning.lms_backend.students;

import id.languagelearning.lms_backend.dto.StudentsDtos.Create;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.Instant;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true") // adjust if your FE origin differs
public class StudentsController {

    private final StudentsRepository repo;

    public StudentsController(StudentsRepository repo) {
        this.repo = repo;
    }

    // DTOs
    public record CreateStudentReq(String full_name, Long phone, String email, String password) {}
    public record StudentRes(String id, String full_name, Long phone, String email, Instant createdAt, Instant updatedAt) {}

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Create body) {
        if (repo.existsByEmailIgnoreCase(body.email)) {
            return ResponseEntity.status(409).body("Email already exists");
        }
        Students saved = repo.save(new Students(body.full_name, body.phone, body.email, body.password));
        return ResponseEntity.created(URI.create("/api/students/" + saved.getId())).body(saved);
    }

    @GetMapping
    public List<Students> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Students> byId(@PathVariable String id) {
        return repo.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/by-email")
    public ResponseEntity<Students> byEmail(@RequestParam String email) {
        return repo.findByEmailIgnoreCase(email).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}