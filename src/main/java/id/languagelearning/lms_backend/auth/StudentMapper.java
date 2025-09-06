package id.languagelearning.lms_backend.auth;

import id.languagelearning.lms_backend.students.Students;

public class StudentMapper {
    public static AuthDtos.StudentView toView(Students s) {
        AuthDtos.StudentView v = new AuthDtos.StudentView();
        v.id = s.getId();
        v.email = s.getEmail();
        v.fullName = s.getFull_name();
        v.phone = s.getPhone();
        v.createdAt = s.getCreatedAt();
        return v;
    }
}