package id.languagelearning.lms_backend.students;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentsRepository extends MongoRepository<Students, String> {
    Optional<Students> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
}