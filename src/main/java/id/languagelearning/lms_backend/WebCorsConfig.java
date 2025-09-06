package id.languagelearning.lms_backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
//                .allowedOrigins("http://localhost:3000")
                .allowedOrigins("https://language-learning-xi.vercel.app")
                .allowedMethods("GET","POST","PATCH","DELETE","OPTIONS")
                .allowCredentials(true);
    }
}
