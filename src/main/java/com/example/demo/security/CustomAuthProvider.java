package com.example.demo.security;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService userDetailsService;

	// multiple bean of same type so identified with the help of bean creation
	// method name
	@Autowired
	PasswordEncoder passwordEncoder2;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		System.out.println("username in authprovider===" + username);
		String password = authentication.getCredentials().toString();
		System.out.println("password in authprovider===" + password);

		try {
			UserDetails user = userDetailsService.loadUserByUsername(username);
			if (user instanceof User) {
				User userPojo = (User) user;
				if (userPojo.isValidForAuthentication()) {
					if (passwordEncoder2.matches(password, user.getPassword()))
						return new UsernamePasswordAuthenticationToken(user, user.getPassword(), new ArrayList<>());
					throw new CredentialsExpiredException("Invalid Credentials");
				} else {
					throw new AccountExpiredException("Account is not active");
				}

			}else {
				throw new InvalidUserObject("Invalid User for Authentication");
			}
		} catch (NoSuchElementException nsee) {
			throw new AccountExpiredException("Account Not Found");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
