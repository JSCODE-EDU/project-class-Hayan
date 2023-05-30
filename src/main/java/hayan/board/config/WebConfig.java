package hayan.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // GET, POST, PUT, PATCH, DELETE 메서드를 이용한 요청을 허용
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                // 모든 헤더와 인증에 관한 정보 허용
                .allowedHeaders("*")
                .allowCredentials(true)
                // pre-flight 요청에 대한 응답을 브라우저에서 캐싱하는 시간 설정
                .maxAge(MAX_AGE_SECS);
    }
}
