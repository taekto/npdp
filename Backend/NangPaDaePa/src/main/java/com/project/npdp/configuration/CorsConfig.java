package com.project.npdp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // Interceptor 이용해 전역의 Cross Origin 처리
    public void addCorsMappings(CorsRegistry registry){
        // 해당 서버의 모든 URL 요청에 해당 Class가
        registry.addMapping("/**")
                // 허용할 도메인 설정
                .allowedOrigins("https://i9b202.p.ssafy.io/*", "http://localhost:8080/*", "http://localhost:8081/*")
                // 허용할 HTTP 메서드 설정
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 허용할 HTTP 헤더 설정
                .allowedHeaders("*")
                // 1초단위로 정책 캐시
                .maxAge(6000);
    }
}
