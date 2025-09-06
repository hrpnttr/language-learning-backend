package id.languagelearning.lms_backend.auth;

import org.springframework.stereotype.Service;

import id.languagelearning.lms_backend.students.Students;
import id.languagelearning.lms_backend.students.StudentsRepository;

@Service
public class AuthService {

    private final StudentsRepository repo;
    private final JwtService jwt;

    public AuthService(StudentsRepository repo, JwtService jwt) {
        this.repo = repo;
        this.jwt = jwt;
    }

    public AuthDtos.LoginResponse login(AuthDtos.LoginRequest req) {
        Students s = repo.findByEmailIgnoreCase(req.email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        String incoming = Hashing.sha256Hex(req.password); // matches your stored format
        if (!incoming.equalsIgnoreCase(s.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        var view = StudentMapper.toView(s);
        String token = jwt.generate(s.getId(), s.getEmail(), s.getFull_name());
        return new AuthDtos.LoginResponse(view, token);
    }
}