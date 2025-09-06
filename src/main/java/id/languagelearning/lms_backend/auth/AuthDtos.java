package id.languagelearning.lms_backend.auth;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthDtos {

    public static class LoginRequest {
        @Email @NotBlank
        public String email;

        @NotBlank
        public String password;
    }

    /** What FE stores in localStorage as "user" */
    public static class StudentView {
        public String id;
        public String email;

        @JsonProperty("full_name")
        public String fullName;

        public String phone;
        public Instant createdAt;
    }

    public static class LoginResponse {
        public StudentView user;

        @JsonProperty("accessToken")
        public String accessToken;

        public LoginResponse(StudentView user, String accessToken) {
            this.user = user;
            this.accessToken = accessToken;
        }
    }
}