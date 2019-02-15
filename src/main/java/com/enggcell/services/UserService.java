package com.enggcell.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.User;


@Scope(value = "request")

public interface UserService {

public User findOne(Long id);

public void save(User registrations);

public List<User> findAll();

public User findAllByUsername(String username);

public User findAllByEmail(String email);

public User findAllByUsernameAndPassword(String username, String password);

public User findAllByMobileNumber(String mobileNumber);

public void delete(Long id);

}
