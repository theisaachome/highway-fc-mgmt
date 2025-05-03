package com.highwayfc.teamservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
//        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication)
//                .map(authentication -> authentication.getName())
//                .orElse("admin");  // Default value when no user is logged in
        return ()-> Optional.of("Admin");
    }
}
