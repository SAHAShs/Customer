package com.customer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CrosConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://127.0.0.1:5500").allowedMethods("*");
	}
}

//	config.addAllowedMethod("OPTIONS");
//	config.addAllowedMethod("GET");
//	config.addAllowedMethod("POST");
//	config.addAllowedMethod("PUT");
//	config.addAllowedMethod("DELETE");

