package com.turkcell.tcell.core.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
public class BaseSecurityService {

    private final BaseJwtFilter baseJwtFilter;

    public BaseSecurityService(BaseJwtFilter baseJwtFilter) {
        this.baseJwtFilter = baseJwtFilter;
    }

    public void configureCommonSecurityRules(HttpSecurity http) throws Exception
    {
        http.csrf(AbstractHttpConfigurer::disable)  // Cross-Site Request Forgery
            .httpBasic(AbstractHttpConfigurer::disable)
            .addFilterBefore(baseJwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
