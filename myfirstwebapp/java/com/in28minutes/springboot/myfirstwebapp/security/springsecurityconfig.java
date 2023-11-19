package com.in28minutes.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class springsecurityconfig {

	// InMemoryUserDetailsManager

//	InMemoryUserDetailsManager(UserDetails... users)

	@Bean
	public InMemoryUserDetailsManager createuserdetailsmanager() {

      
     	UserDetails userdetails = createnewuser("mohamed", "4444");
		return new InMemoryUserDetailsManager(userdetails);
	}

	private UserDetails createnewuser(String username, String password) {
		Function<String, String> passwordencoder= input->passwordencoder().encode(input);
		
		UserDetails userdetails = User
				.builder()
				.passwordEncoder(passwordencoder)
				.username(username)
				.password(password)
				.roles("USER", "ADMIN").build();
		return userdetails;
	}

	@Bean
	public PasswordEncoder passwordencoder() {

		return new BCryptPasswordEncoder();

	}
	
	
	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		
		
		return http.build();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
