package com.enggcell.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.ForgetPasswordTempUser;


@Scope(value = "request")

public interface ForgetPasswordTempUserService {

public ForgetPasswordTempUser findOne(Long id);

public void save(ForgetPasswordTempUser registrations);

public List<ForgetPasswordTempUser> findAll();

public ForgetPasswordTempUser findAllByUsername(String username);

public ForgetPasswordTempUser findAllByEmail(String email);

public ForgetPasswordTempUser findAllByHashGeneratedUniqueResetPasswordID(String hashGeneratedUniqueResetPasswordID);

public void delete(Long id);

}
