package id.languagelearning.lms_backend.quizzes;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizzesRepository extends MongoRepository<Quizzes, String>{
    Page<Quizzes> findByTitleContaining(String q, Pageable pageable);
    List<Quizzes> findById(ObjectId _id);
}