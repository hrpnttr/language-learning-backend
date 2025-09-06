package id.languagelearning.lms_backend;

import id.languagelearning.lms_backend.dto.UserDtos.Create;
import id.languagelearning.lms_backend.dto.UserDtos.Update;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true") // adjust if your FE origin differs
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Create body) {
        if (repo.existsByEmail(body.email)) {
            return ResponseEntity.status(409).body("Email already exists");
        }
        User saved = repo.save(new User(body.email, body.name, body.level));
        return ResponseEntity.created(URI.create("/api/users/" + saved.getId())).body(saved);
    }

    @GetMapping
    public List<User> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> byId(@PathVariable String id) {
        return repo.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-email")
    public ResponseEntity<User> byEmail(@RequestParam String email) {
        return repo.findByEmail(email).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Update body) {
        return repo.findById(id).map(user -> {
            if (body.name != null && !body.name.isBlank()) user.setName(body.name);
            if (body.level != null && !body.level.isBlank()) user.setLevel(body.level);
            return ResponseEntity.ok(repo.save(user));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}