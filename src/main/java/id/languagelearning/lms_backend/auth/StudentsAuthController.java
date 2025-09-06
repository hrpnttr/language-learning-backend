package id.languagelearning.lms_backend.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentsAuthController {

    private final AuthService authService;

    public StudentsAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated AuthDtos.LoginRequest body) {
        try {
            return ResponseEntity.ok(authService.login(body));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(
                    java.util.Map.of("error", e.getMessage())
            );
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody @Validated AuthDtos.LoginRequest body,
//                                   HttpServletRequest request) {
//        try {
//            var login = authService.login(body); // AuthDtos.LoginResponse (throws on invalid)
//
//            HttpSession session = request.getSession(true);
//            session.setAttribute("student", login.user);
//            session.setAttribute("accessToken", login.accessToken);
//
//            // 1 minute for testing
//            session.setMaxInactiveInterval(60);
//
//            return ResponseEntity.ok(java.util.Map.of(
//                    "message", "Login successful",
//                    "data", login
//            ));
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
//                    java.util.Map.of("error", e.getMessage())
//            );
//        }
//    }
//
    @GetMapping("/me")
    public ResponseEntity<?> me(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("student") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(java.util.Map.of("error", "Not logged in"));
        }
        return ResponseEntity.ok(session.getAttribute("student"));
    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) session.invalidate();
//        return ResponseEntity.ok(java.util.Map.of("message", "Logged out successfully"));
//    }

}