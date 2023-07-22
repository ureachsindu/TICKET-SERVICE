package com.dxc.tks.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@OpenAPIDefinition(info = @Info(title = "Ticket Service"))
public class ApplicationConfig {

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("application");
		source.setBasename("message");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	//@Bean
	//PasswordEncoder bcryptPasswordEncoder() {
	//	return new BCryptPasswordEncoder();
	//}
}
