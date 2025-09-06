package id.languagelearning.lms_backend.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ScoursesDto {
    public static class Create {
        @NotBlank
        public String studentId;
        public List<Contents> contents;
    }

    public static class Contents {
        @NotBlank
        public String courseId;
        @NotBlank
        public String title;
    }
}