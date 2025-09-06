package id.languagelearning.lms_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDtos {
    public static class Create {
        @Email @NotBlank
        public String email;
        @NotBlank
        public String name;
        @NotBlank
        public String level; // beginner/intermediate/advanced
    }

    public static class Update {
        public String name;   // optional
        public String level;  // optional
    }
}
