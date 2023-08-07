package com.project.npdp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // Interceptor 이용해 전역의 Cross Origin 처리
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://i9b202.p.ssafy.io", "http://localhost:8080") // 허용할 도메인 설정
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드 설정
                .allowedHeaders("*") // 허용할 HTTP 헤더 설정
                .allowCredentials(true) // 인증 헤더 허용 (옵션)
                .maxAge(3600); // 캐시 시간 설정 (옵션)
    }
}
