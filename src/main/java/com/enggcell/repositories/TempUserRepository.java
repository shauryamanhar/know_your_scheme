package com.enggcell.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.TempUser;
import com.enggcell.entities.User;

@Repository
@Scope(value = "request")

public interface TempUserRepository extends JpaRepository<TempUser, Long> {
	public TempUser findAllByUsername(String username);
	public TempUser findAllByEmail(String email);
	public TempUser findAllByHashGeneratedUniqueRgistrationId(String hashGeneratedUniqueRgistrationId);
	public TempUser findAllByMobileNumber(String mobileNumber);
}
