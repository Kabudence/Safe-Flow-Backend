package com.safeflow;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")           // Permite cualquier origen
                .allowedMethods("*")           // Permite cualquier método (GET, POST, PUT, DELETE, OPTIONS, etc)
                .allowedHeaders("*")           // Permite cualquier header
                .allowCredentials(false)       // No permite credenciales cuando origins="*"
                .maxAge(3600);
    }
}
