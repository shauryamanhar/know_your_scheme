package com.enggcell.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.UserCategories;

@Repository
@Scope(value = "request")

public interface UserCategoriesRepository extends JpaRepository<UserCategories, Long> {
	public List<UserCategories> findAllByUsername(String username);
	public UserCategories findAllByUsernameAndCategory(String username, String category);
}
