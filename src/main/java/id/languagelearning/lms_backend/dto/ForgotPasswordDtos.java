package id.languagelearning.lms_backend.dto;

public class ForgotPasswordDtos {
    private String email;

    public ForgotPasswordDtos(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
