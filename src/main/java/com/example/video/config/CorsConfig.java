package com.example.video.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 🌐 CORS 전역 설정
     * - 프론트/백엔드 분리 환경에서 필수
     * - 모든 컨트롤러에 공통 적용
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**") // 모든 API 경로 허용
                .allowedOrigins(
                        "http://localhost:9000" //허용할 프론트 주소
                )
                .allowedMethods(
                        "GET", "POST", "PUT", "DELETE", "OPTIONS"
                )
                .allowedHeaders("*") //JSON, Authorization 헤더 허용
                .allowCredentials(true) //쿠키 / 세션 / 인증 토큰 사용 시 필요
                .maxAge(3600);
    }


}
