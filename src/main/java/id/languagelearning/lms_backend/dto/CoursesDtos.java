package id.languagelearning.lms_backend.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class CoursesDtos {
    public static class Create {
        @NotBlank public String title;
        @NotBlank public String description;
        @NotBlank public String level;
        @NotBlank public String language;
        public Double price;
        public Integer durationHours;
        public List<String> tags;
    }

    public static class Update {
        public String title;
        public String description;
        public String level;
        public String language;
        public Double price;
        public Integer durationHours;
        public List<String> tags;
        public Boolean published;
    }
}
