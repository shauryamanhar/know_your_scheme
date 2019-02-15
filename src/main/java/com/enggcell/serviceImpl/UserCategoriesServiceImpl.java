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
import com.enggcell.entities.UserCategories;
import com.enggcell.repositories.UserCategoriesRepository;
import com.enggcell.repositories.UserRepository;
import com.enggcell.services.UserCategoriesService;
import com.enggcell.services.UserService;

@Service
@Transactional
@Scope(value = "request")

public class UserCategoriesServiceImpl implements UserCategoriesService {

    @Resource
    private UserCategoriesRepository userCategoriesRepository;
    
    @Override
    public UserCategories findOne(Long id) {
        return userCategoriesRepository.findOne(id);
    }

    @Override
    public void save(UserCategories registrations) {
    	userCategoriesRepository.save(registrations);
    }
    
    @Override
    public List<UserCategories> findAll(){
    	return userCategoriesRepository.findAll();
    }
    
    @Override
    public void delete(Long id){
    	userCategoriesRepository.delete(id);
    }

	@Override
	public List<UserCategories> findAllByUsername(String username) {
		return userCategoriesRepository.findAllByUsername(username);
	}

	@Override
	public UserCategories findAllByUsernameAndCategory(String username,
			String category) {
		return userCategoriesRepository.findAllByUsernameAndCategory(username, category);
	}
}
