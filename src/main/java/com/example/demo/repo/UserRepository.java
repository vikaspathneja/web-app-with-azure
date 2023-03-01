package com.example.demo.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import com.example.demo.model.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonDeserialize(as = User.class)
//@EnableRedisRepositories
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findUserByUsername(String username); 	

	@Modifying
	@Query("update User u set u.username=?1 where u.id=?2")
	void setUsername(String name,long id);

}