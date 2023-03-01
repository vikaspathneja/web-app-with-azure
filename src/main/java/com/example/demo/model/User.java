package com.example.demo.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

//Table - User
@Entity
@Data
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String username;

	private String password;

	private boolean accountNonLocked;

	private boolean credentialsNonExpired;
	private boolean accountNonExpired;

	private boolean enabled;

	private String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {

	}

	public void setId(long id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
//		return false;
		return this.accountNonExpired;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
		// TODO Auto-generated method stub
//		return false;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", accountNonLocked="
				+ accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired + ", accountNonExpired="
				+ accountNonExpired + ", enabled=" + enabled + ", role=" + role + "]";
	}

	public boolean isValidForAuthentication() {
		if (this.accountNonExpired == true && this.accountNonLocked == true && this.credentialsNonExpired == true
				&& this.enabled == true)
			return true;
		return false;
	}
	

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


}