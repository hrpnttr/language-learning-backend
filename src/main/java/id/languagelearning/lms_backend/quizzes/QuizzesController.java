package id.languagelearning.lms_backend.quizzes;

import org.bson.types.ObjectId;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quizzes")
public class QuizzesController {

    private final QuizzesRepository repo;
    public QuizzesController(QuizzesRepository repo) {this.repo = repo;}

//    private final TestRepository data;
//    public QuizzesController(TestRepository data) {this.data = data;}

    @GetMapping
    public Page<Quizzes> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String q
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return (q == null || q.isBlank())
                ? repo.findAll(pageable)
                : repo.findByTitleContaining(q, pageable);
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> getById(@RequestParam String id) {
        if (id == null || id.isBlank()) {
            return ResponseEntity.badRequest().body("Query param 'id' is required");
        }
        if (!id.matches("^[a-fA-F0-9]{24}$")) {
            return ResponseEntity.badRequest().body("'id' must be 24-hex ObjectId");
        }
        return repo.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // List with pagination & simple search: /api/courses?page=0&size=10&q=eng
    @GetMapping("/{id}")
    public ResponseEntity<Quizzes> get(@PathVariable String id) {
        return repo.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}