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

import com.enggcell.entities.ForgetPasswordTempUser;
import com.enggcell.repositories.ForgetPasswordTempUserRepository;
import com.enggcell.services.ForgetPasswordTempUserService;

@Service
@Transactional
@Scope(value = "request")

public class ForgetPasswordTempUserServiceImpl implements ForgetPasswordTempUserService {

    @Resource
    private ForgetPasswordTempUserRepository forgetPasswordTempUserRepository;
    
    @Override
    public ForgetPasswordTempUser findOne(Long id) {
        return forgetPasswordTempUserRepository.findOne(id);
    }

    @Override
    public void save(ForgetPasswordTempUser registrations) {
    	forgetPasswordTempUserRepository.save(registrations);
    }
    
    @Override
    public List<ForgetPasswordTempUser> findAll(){
    	return forgetPasswordTempUserRepository.findAll();
    }
    
    @Override
    public void delete(Long id){
    	forgetPasswordTempUserRepository.delete(id);
    }

	@Override
	public ForgetPasswordTempUser findAllByUsername(String username) {
		return forgetPasswordTempUserRepository.findAllByUsername(username);
	}

	@Override
	public ForgetPasswordTempUser findAllByEmail(String email) {
		return forgetPasswordTempUserRepository.findAllByEmail(email);
	}

	@Override
	public ForgetPasswordTempUser findAllByHashGeneratedUniqueResetPasswordID(
			String hashGeneratedUniqueResetPasswordID) {
		return forgetPasswordTempUserRepository.findAllByHashGeneratedUniqueResetPasswordID(hashGeneratedUniqueResetPasswordID);
	}
}
