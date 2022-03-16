package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityAdapter extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());

//		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN")
//				.and().withUser("tony").password(passwordEncoder().encode("tony123")).roles("USER");
	}

	@Autowired
	private MyUserDetailsService userDetailsService;

//	SecurityAdapter(MyUserDetailsService userDetailsService) {
//		this.userDetailsService = userDetailsService;
//	}

//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		UserDetails tony = User.builder().username("tony").password(passwordEncoder().encode("tony123")).roles("USER")
//				.build();
//		
//		UserDetails mike = User.builder().username("mike").password(passwordEncoder().encode("mike123")).roles("USER")
//				.build();
//
//		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN").authorities("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(tony,admin,mike);
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
				.antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/customer/**").hasAuthority("ADMIN").and()
				.formLogin();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(this.userDetailsService);
		return provider;
	}
	

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
