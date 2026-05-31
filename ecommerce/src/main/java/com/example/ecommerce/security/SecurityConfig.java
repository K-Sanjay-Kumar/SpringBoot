package com.example.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		return http.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(request -> request 
					// Public APIs
	                .requestMatchers(
	                        "/api/ecommerce/users/register"
	                ).permitAll()
	                
	                // Product APIs
	                .requestMatchers(
	                        "/api/ecommerce/products/add",
	                        "/api/ecommerce/products/update/**",
	                        "/api/ecommerce/products/delete/**"
	                ).hasRole("ADMIN")
	                
	                // Category APIs
	                .requestMatchers(
	                        "/api/ecommerce/categories/add",
	                        "/api/ecommerce/categories/update/**",
	                        "/api/ecommerce/categories/delete/**"
	                ).hasRole("ADMIN")
	                
	                // Cart APIs
	                .requestMatchers(
	                        "/api/cart/**"
	                ).hasRole("USER")
	                
	                // Orders APIs
	                .requestMatchers(
	                        "/api/orders/**"
	                ).hasRole("USER")
	                
	                // Remaining APIs
	                .anyRequest()
	                .authenticated()
                )
				.httpBasic(Customizer.withDefaults())
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
}

