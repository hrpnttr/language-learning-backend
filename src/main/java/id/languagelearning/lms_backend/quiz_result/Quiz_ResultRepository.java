package id.languagelearning.lms_backend.quiz_result;

import id.languagelearning.lms_backend.quiz_result.Quizz_Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Quiz_ResultRepository extends MongoRepository<Quizz_Result, String>{
}