package id.languagelearning.lms_backend.classes;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassesRepository extends MongoRepository<Classes, String>{
    Page<Classes> findByClass_NameContainingIgnoreCase(String q, Pageable pageable);
}