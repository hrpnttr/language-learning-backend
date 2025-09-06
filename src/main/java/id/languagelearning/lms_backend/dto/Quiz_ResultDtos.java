package id.languagelearning.lms_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class Quiz_ResultDtos {
    public static class Create {
        @NotBlank
        public String studentId;
        @NotBlank
        public String quiz_id;
        @NotEmpty
        public List<Answers> answers;
        @NotNull
        public Integer score;
    }

    public static class Answers {
        @NotBlank
        public String text;
        @NotNull
        public Boolean is_correct;
    }
}