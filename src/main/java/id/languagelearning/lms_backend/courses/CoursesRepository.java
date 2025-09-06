package id.languagelearning.lms_backend.courses;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CoursesRepository extends MongoRepository<Courses, String>{
    Page<Courses> findByTitleContainingIgnoreCase(String q, Pageable pageable);
}