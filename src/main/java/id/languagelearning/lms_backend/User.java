package id.languagelearning.lms_backend;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("users")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String name;
    private String level;        // e.g., "beginner", "intermediate", "advanced"
    private Instant createdAt;

    public User() {
        this.createdAt = Instant.now();
    }

    public User(String email, String name, String level) {
        this.email = email;
        this.name = name;
        this.level = level;
        this.createdAt = Instant.now();
    }

    // getters & setters
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getLevel() { return level; }
    public Instant getCreatedAt() { return createdAt; }

    public void setId(String id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public void setLevel(String level) { this.level = level; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}