package com.chiyu.Util;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域问题
 *
 */
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedOrigins("*")
					.allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
					.allowedHeaders("*")
					.exposedHeaders("access-control-allow-headers",
							"access-control-allow-methods",
							"access-control-allow-origin",
							"access-control-max-age",
							"X-Frame-Options")
	                .allowCredentials(false).maxAge(3600);
	    }
}
