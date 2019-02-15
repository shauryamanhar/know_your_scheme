package com.enggcell.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.User;

@Repository
@Scope(value = "request")

public interface UserRepository extends JpaRepository<User, Long> {
	public User findAllByUsername(String username);
	public User findAllByEmail(String email);
	public User findAllByUsernameAndPassword(String username, String password);
	public User findAllByMobileNumber(String mobileNumber);
}
