package com.enggcell.serviceImpl;


import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;

import com.enggcell.entities.User;
import com.enggcell.repositories.UserRepository;
import com.enggcell.services.UserService;

@Service
@Transactional
@Scope(value = "request")

public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    
    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void save(User registrations) {
    	userRepository.save(registrations);
    }
    
    @Override
    public List<User> findAll(){
    	return userRepository.findAll();
    }
    
    @Override
    public void delete(Long id){
    	userRepository.delete(id);
    }

	@Override
	public User findAllByUsername(String username) {
		return userRepository.findAllByUsername(username);
	}

	@Override
	public User findAllByEmail(String email) {
		return userRepository.findAllByEmail(email);
	}

	@Override
	public User findAllByUsernameAndPassword(String username, String password) {
		return userRepository.findAllByUsernameAndPassword(username, password);
	}

	@Override
	public User findAllByMobileNumber(String mobileNumber) {
		return userRepository.findAllByMobileNumber(mobileNumber);
	}
}
