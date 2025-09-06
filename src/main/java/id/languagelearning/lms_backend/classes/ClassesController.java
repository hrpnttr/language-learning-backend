package id.languagelearning.lms_backend.classes;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {

    private final ClassesRepository repo;
    public ClassesController(ClassesRepository repo) {this.repo = repo;}

    @GetMapping
    public Page<Classes> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String q
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "_id"));
        return (q == null || q.isBlank())
                ? repo.findAll(pageable)
                : repo.findByClass_NameContainingIgnoreCase(q, pageable);
    }

    // List with pagination & simple search: /api/classes?page=0&size=10&q=eng
    @GetMapping("/{id}")
    public ResponseEntity<Classes> get(@PathVariable String id) {
        return repo.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
