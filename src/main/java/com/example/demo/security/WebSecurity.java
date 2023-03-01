package com.example.demo.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	 private AuthenticationProvider authProvider;
	 
	
	 @Autowired
	 private UserDetailsService userservice;
	 
	  
	  @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authProvider);
	        auth.userDetailsService(userservice);
	    }
	  
    @Override
    protected void configure(HttpSecurity http) throws Exception { 
        http
        .cors().and()
        .csrf().disable().authorizeRequests()
        .antMatchers("/user").permitAll()
        .antMatchers("/user/*").permitAll()
        .antMatchers("/getfromcache").permitAll()
        .antMatchers("/getfromcache/*").permitAll()
        .antMatchers("/user/getfromcache/*").permitAll()
        .antMatchers("/savetocache").permitAll()
        .antMatchers("/savetocache/*").permitAll()
        .antMatchers("/user/savetocache/*").permitAll()
//        .antMatchers("/users").hasRole("manager")
        .anyRequest().authenticated()
        .and() 
        .logout().invalidateHttpSession(true) 
        .clearAuthentication(true) .permitAll()
        .and()
        .formLogin()
//        .loginProcessingUrl("/gotologin")
        .defaultSuccessUrl("/homepage.html", true);
    }
}