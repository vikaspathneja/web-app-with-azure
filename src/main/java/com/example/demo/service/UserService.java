package com.example.demo.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails usr=(UserDetails)repo.findUserByUsername(username).get();
		return usr;
	}


	@Autowired
	EntityManager em;
	
	@Autowired
	SessionFactory sf;
	
	@Autowired
	PasswordEncoder passwordEncoder2;
	
	public User insert(User user){
		Optional<User> updatedUser=Optional.of(user).map(x->{
			if(!x.getPassword().isEmpty())
				x.setPassword(passwordEncoder2.encode(x.getPassword()));
			return x ;
		});
		return updatedUser.map(x->repo.save(updatedUser.get())).get();
	}
	
	public void update(User user){
		repo.setUsername(user.getUsername(), user.getId());
	}
	
	public void delete(long userid){
		repo.deleteById(userid);
	}
	@Cacheable(key = "#userid",value = "User")
	public User getUser(long userid) {
		Optional<User> uu=repo.findById(userid);
		return uu.isPresent()==true?uu.get():null;
	}
}
