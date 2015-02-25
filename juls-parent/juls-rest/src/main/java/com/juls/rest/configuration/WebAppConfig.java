package com.juls.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.juls.logger.UserLogger;

@Configuration
@EnableWebMvc
@ComponentScan("com.juls")
@EnableAspectJAutoProxy
public class WebAppConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		int cachePeriod = 3600 * 24 * 15;
		registry.addResourceHandler("/static/**")
				.addResourceLocations("/static/")
				.setCachePeriod(cachePeriod);
	}
	

} 