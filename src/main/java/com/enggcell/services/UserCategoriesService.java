package com.enggcell.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.UserCategories;


@Scope(value = "request")

public interface UserCategoriesService {

public UserCategories findOne(Long id);

public void save(UserCategories registrations);

public List<UserCategories> findAll();

public List<UserCategories> findAllByUsername(String username);

public UserCategories findAllByUsernameAndCategory(String username, String category);

public void delete(Long id);

}
