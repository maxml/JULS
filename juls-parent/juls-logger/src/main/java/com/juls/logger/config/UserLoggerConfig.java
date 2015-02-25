/**
 * 
 */
package com.juls.logger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

import com.juls.logger.UserLogger;

/**
 * 
 * @author Matvey Mitnitskyi
 * @version	1.0 
 * from 19/02/2015
 * 
 */

@Configuration
@ComponentScan("com.juls")
@EnableAspectJAutoProxy
public class UserLoggerConfig {
	
	@Bean
	public UserLogger createUserLogger(){
		return new UserLogger();
	}

} 