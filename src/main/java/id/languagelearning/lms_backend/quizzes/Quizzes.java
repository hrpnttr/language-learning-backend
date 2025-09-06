package id.languagelearning.lms_backend.quizzes;

import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.List;

public class Quizzes {
    private String id;
    private String class_id;
    private String title;
    private String description;
    private String type;
    private List<id.languagelearning.lms_backend.quizzes.Contens> contens;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();

    //    getters/setter
    public String getId() {return id;}
    public String getClass_id() {return class_id;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public String getType() {return this.type;}
    public List<id.languagelearning.lms_backend.quizzes.Contens> getContens () {return this.contens;}
    public Instant getCreatedAt() {return createdAt;}
    public Instant getUpdatedAt() {return updatedAt;}

    public void setId(String id) {this.id = id;}
    public void setClass_id(String class_id) {this.class_id = class_id;}
    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description = description;}
    public void setType(String type) {this.type = type;}
    public void setContens(List<id.languagelearning.lms_backend.quizzes.Contens> contens) {this.contens = contens;}
    public void setCreatedAt(Instant createdAt) {this.createdAt = createdAt;}
    public void setUpdatedAt(Instant updatedAt) {this.updatedAt = updatedAt;}
}


class Contens {
    private String question;
    private List<id.languagelearning.lms_backend.quizzes.Choices> choices;

    // getters & setters
    public String getQuestion() {return question;}
    public List<id.languagelearning.lms_backend.quizzes.Choices> getChoices () {return this.choices;}

    public void setQuestion(String question) {this.question = question;}
    public void setChoices(List<id.languagelearning.lms_backend.quizzes.Choices> choices) {this.choices = choices;}
}

class Choices {
    private String text;
    private Boolean is_correct;

    // getters & setters
    public String getText() {return text;}
    public Boolean getIs_correct () {return this.is_correct;}

    public void setText(String text) {this.text = text;}
    public void setIs_correct(Boolean is_correct) {this.is_correct = is_correct;}
}