package com.techskillsit.springrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.techskillsit.springrestapi.service.UserInfoService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserInfoService userInfoService;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer:: disable)
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers(HttpMethod.GET, "/api/login").authenticated()
					.requestMatchers(HttpMethod.GET,"/api/student/getall").permitAll()
					.requestMatchers(HttpMethod.GET,"/api/employee/name/search")
								.hasAnyAuthority("EMPLOYEE","HR")
					.requestMatchers(HttpMethod.GET,"/api/project/{teamId}")
								.hasAuthority("HR")
					 .anyRequest().permitAll()
			)
			
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(){
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(passwordEncoder());
		dao.setUserDetailsService(userInfoService);
		ProviderManager provider = new ProviderManager(dao);
		return provider;
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}
}
