/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdac.egov.wamis.config;

import com.cdac.egov.wamis.security.interceptors.WamisSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author ambarishn
 */
@Configuration
public class WamisWebConfig implements WebMvcConfigurer {
    
//    @Autowired
//    AuthInterceptor authInterceptor;
    
    @Autowired
    WamisSecurityInterceptor securityInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(securityInterceptor);
//        registry.addInterceptor(authInterceptor).excludePathPatterns("/login", "/header");
    }
}
