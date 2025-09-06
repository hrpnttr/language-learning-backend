package id.languagelearning.lms_backend.scourses;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface ScoursesRepository extends MongoRepository<Scourses, String> {
    Optional<Scourses> findByStudentId(String studentId);
    boolean existsByStudentIdAndContentsCourseIdIn(String studentId, List<String> courseId);
    boolean existsByStudentIdAndContentsCourseIdIgnoreCase(String studentId, List<String> courseId);
    boolean existsByStudentIdIgnoreCase(String studentId);
    boolean existsByContentsCourseIdIn(List<String> courseId);
}