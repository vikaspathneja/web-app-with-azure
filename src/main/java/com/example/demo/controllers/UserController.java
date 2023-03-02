package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import static org.springframework.http.MediaType.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userservice;

	Logger logger=LoggerFactory.getLogger(UserController.class);
//	@Autowired
//	RedisTemplate<String,Object> redisTemplate;
//
//	@PostMapping(path="/savetocache",produces = APPLICATION_JSON_VALUE)
//	public String saveUserInRedisCache(@RequestBody User user) {
//		try {
//		redisTemplate.opsForHash().put("CacheUser",user.getId(),user);
//		return "true";
//		}catch(Exception eee) {
//			eee.printStackTrace();
//		}
//		return "false";
//	}
//	@GetMapping(path="/getfromcache/{id}",produces = APPLICATION_JSON_VALUE)
//	public Object getUserFromRedisCache(@PathVariable(name="id") long id) {
//		System.out.println(redisTemplate.hasKey("CacheUser"));
//		Object obj=redisTemplate.opsForHash().get("CacheUser",id);
//		System.out.println(obj.toString());
//		if(obj instanceof User) {
//			System.out.println("object returned from cache success"+obj);
//		}
//		return obj;
//	}
	
	
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public User createusertodb(@RequestBody User user) {
		return userservice.insert(user);
		
	}

	@PostMapping(path = "/{uid}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateuser(@PathVariable(name = "uid") String uid, @RequestBody User user) {
		ResponseEntity<User> response = getUser(uid);
		if (response.getStatusCode().is2xxSuccessful()) {
			user.setId(Long.valueOf(uid));
			userservice.update(user);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@DeleteMapping(path = "/{uid}", produces = APPLICATION_JSON_VALUE)
	public User deleteUser(@PathVariable(name = "uid") String uid) {
		User user = userservice.getUser(Long.valueOf(uid));
		userservice.delete(Long.valueOf(uid));
		return user;
	}

	@GetMapping(path = "/{uid}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable(name = "uid") String uid) {
		logger.debug("env Map:"+System.getenv());
		System.out.println("isDebugenabled:"+logger.isDebugEnabled());
		logger.debug("uid:"+uid);
		User user = userservice.getUser(Long.valueOf(uid));
		logger.debug("user:"+user);
		Optional<User> opt = Optional.ofNullable(user);
		logger.debug("opt is present: "+opt.isPresent());
		logger.debug("user object in optional: "+opt.get());
		return opt.isPresent() == true ? ResponseEntity.status(HttpStatus.OK).body(opt.get())
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

}