package com.enggcell.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.TempUser;
import com.enggcell.entities.User;


@Scope(value = "request")

public interface TempUserService {

public TempUser findOne(Long id);

public void save(TempUser registrations);

public List<TempUser> findAll();

public TempUser findAllByUsername(String username);

public TempUser findAllByEmail(String email);

public TempUser findAllByHashGeneratedUniqueRgistrationId(String hashGeneratedUniqueRgistrationId);

public TempUser findAllByMobileNumber(String mobileNumber);

public void delete(Long id);

}
