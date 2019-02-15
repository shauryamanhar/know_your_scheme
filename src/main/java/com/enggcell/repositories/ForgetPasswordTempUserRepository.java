package com.enggcell.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.ForgetPasswordTempUser;
import com.enggcell.entities.TempUser;
import com.enggcell.entities.User;

@Repository
@Scope(value = "request")

public interface ForgetPasswordTempUserRepository extends JpaRepository<ForgetPasswordTempUser, Long> {
	public ForgetPasswordTempUser findAllByUsername(String username);
	public ForgetPasswordTempUser findAllByEmail(String email);
	public ForgetPasswordTempUser findAllByHashGeneratedUniqueResetPasswordID(String hashGeneratedUniqueResetPasswordID);
}
