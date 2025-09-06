package id.languagelearning.lms_backend.students;
import id.languagelearning.lms_backend.dto.ForgotPasswordDtos;
import id.languagelearning.lms_backend.students.StudentsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
public class PasswordResetController {

    private final StudentsRepository repo;

    public PasswordResetController(StudentsRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> byEmail(@RequestBody ForgotPasswordDtos dto) {
        return repo.findByEmailIgnoreCase(dto.getEmail())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
