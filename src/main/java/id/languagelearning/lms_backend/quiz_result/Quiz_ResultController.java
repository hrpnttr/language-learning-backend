package id.languagelearning.lms_backend.quiz_result;

import id.languagelearning.lms_backend.dto.Quiz_ResultDtos;
import id.languagelearning.lms_backend.quizzes.Quizzes;
import id.languagelearning.lms_backend.students.Students;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/add-quizzes")
public class Quiz_ResultController {

    private final Quiz_ResultRepository repo;
    public Quiz_ResultController(Quiz_ResultRepository repo) {this.repo = repo;}

    @GetMapping
    public Page<Quizz_Result> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String q
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "_id"));
        return (q == null || q.isBlank())
                ? repo.findAll(pageable)
                : repo.findAll(pageable);
    }

    // List with pagination & simple search: /api/courses?page=0&size=10&q=eng
    @GetMapping("/{id}")
    public ResponseEntity<Quizz_Result> get(@PathVariable String id) {
        return repo.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DTOs
    public record CreateQuiz_Result(String quiz_id, List<Answers> answers, Number score) {}
    public record StudentRes(String id, String quiz_id, List<Answers> answers, Number score, Instant createdAt, Instant updatedAt) {}

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Quiz_ResultDtos.Create body) {
//        if (repo.existsByEmailIgnoreCase(body.email)) {
//            return ResponseEntity.status(409).body("Email already exists");
//        }

        List<id.languagelearning.lms_backend.quiz_result.Answers> answers = body.answers.stream()
            .map(ch -> new id.languagelearning.lms_backend.quiz_result.Answers(ch.text, ch.is_correct))
            .toList();

        Quizz_Result saved = repo.save(new Quizz_Result(body.studentId, body.quiz_id, answers, body.score));
        return ResponseEntity.created(URI.create("/api/quizzes/" + saved.getId())).body(saved);
    }
}