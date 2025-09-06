package id.languagelearning.lms_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class StudentsDtos {
    public static class Create {
        @NotBlank
        public String full_name;
        @NotBlank
        public String phone;
        @Email @NotBlank
        public String email;
        @NotBlank
        public String password;
    }
}