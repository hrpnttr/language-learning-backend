package id.languagelearning.lms_backend.scourses;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("scourses")
public class Scourses {
    @Id
    private String id;
    private String studentId;
    private List<id.languagelearning.lms_backend.scourses.Contents> contents;
    private Instant createdAt;

    public Scourses() {
        this.createdAt = Instant.now();
    }

    public Scourses(String studentId, List contents) {
        this.studentId = studentId;
        this.contents = contents;
        this.createdAt = Instant.now();
    }

    // getters & setters
    public String getId() { return id; }
    public String getStudentId() { return studentId; }
    public List<id.languagelearning.lms_backend.scourses.Contents> getContents () {return this.contents;}
    public Instant getCreatedAt() { return createdAt; }

    public void setId(String id) { this.id = id; }
    public void setStudentId(String student_id) { this.studentId = studentId; }
    public void setContents(List<id.languagelearning.lms_backend.scourses.Contents> contents) {this.contents = contents;}
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}

class Contents {
    private String courseId;
    private String title;

    // getters & setters
    public String getCourseId() {return courseId;}
    public String getTitle() {return title;}

    public void setCourseId(String courseId) {this.courseId = courseId;}
    public void setTitle(String title) {this.title = title;}
}