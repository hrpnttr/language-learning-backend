package id.languagelearning.lms_backend.quiz_result;

import java.time.Instant;
import java.util.List;

public class Quizz_Result {
    private String id;
    private String studentId;
    private String quiz_id;
    private List<Answers> answers;
    private Number score;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();

    public Quizz_Result() {
        this.createdAt = Instant.now();
    }

    public Quizz_Result(String studentId, String quiz_id, List<Answers> answers, Number score) {
        this.studentId = studentId;
        this.quiz_id = quiz_id;
        this.answers = answers;
        this.score = score;
        this.createdAt = Instant.now();
    }

    //    getters/setter
    public String getId() {return id;}
    public String getQuiz_id() {return quiz_id;}
    public List<Answers> getAnswers () {return this.answers;}
    public Number getScore() {return this.score;}
    public Instant getCreatedAt() {return createdAt;}
    public Instant getUpdatedAt() {return updatedAt;}

    public void setId(String id) {this.id = id;}
    public void setQuiz_id(String quiz_id) {this.quiz_id = quiz_id;}
    public void setAnswers(List<Answers> answers) {this.answers = answers;}
    public void setScore(Number score) {this.score = score;}
    public void setCreatedAt(Instant createdAt) {this.createdAt = createdAt;}
    public void setUpdatedAt(Instant updatedAt) {this.updatedAt = updatedAt;}
}

class Answers {
    private String text;
    private Boolean is_correct;

    // getters & setters
    public String getText() {return text;}
    public Boolean getIs_correct () {return this.is_correct;}

    public void setText(String text) {this.text = text;}
    public void setIs_correct(Boolean is_correct) {this.is_correct = is_correct;}

    public Answers() {}

    public Answers(String text, Boolean is_correct) {
        this.text = text;
        this.is_correct = is_correct;
    }
}