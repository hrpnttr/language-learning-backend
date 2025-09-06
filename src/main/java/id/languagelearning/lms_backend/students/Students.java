package id.languagelearning.lms_backend.students;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document("students")
public class Students {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    @JsonIgnore
    @Field("password")
    private String password;
    private String full_name;
    private String phone;
    private Instant createdAt;

    public Students() {
        this.createdAt = Instant.now();
    }

    public Students(String full_name, String phone, String email, String password) {
        this.full_name = full_name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.createdAt = Instant.now();
    }

    // getters & setters
    public String getId() { return id; }
    public String getFull_name() { return full_name; }
    public String getPhone() {return phone;}
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Instant getCreatedAt() { return createdAt; }

    public void setId(String id) { this.id = id; }
    public void setFull_name(String full_name) { this.full_name = full_name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}