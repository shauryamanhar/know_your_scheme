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

import com.enggcell.entities.TempUser;
import com.enggcell.repositories.TempUserRepository;
import com.enggcell.services.TempUserService;

@Service
@Transactional
@Scope(value = "request")

public class TempUserServiceImpl implements TempUserService {

    @Resource
    private TempUserRepository tempUserRepository;
    
    @Override
    public TempUser findOne(Long id) {
        return tempUserRepository.findOne(id);
    }

    @Override
    public void save(TempUser registrations) {
    	tempUserRepository.save(registrations);
    }
    
    @Override
    public List<TempUser> findAll(){
    	return tempUserRepository.findAll();
    }
    
    @Override
    public void delete(Long id){
    	tempUserRepository.delete(id);
    }

	@Override
	public TempUser findAllByUsername(String username) {
		return tempUserRepository.findAllByUsername(username);
	}

	@Override
	public TempUser findAllByEmail(String email) {
		return tempUserRepository.findAllByEmail(email);
	}

	@Override
	public TempUser findAllByHashGeneratedUniqueRgistrationId(
			String hashGeneratedUniqueRgistrationId) {
		return tempUserRepository.findAllByHashGeneratedUniqueRgistrationId(hashGeneratedUniqueRgistrationId);
	}

	@Override
	public TempUser findAllByMobileNumber(String mobileNumber) {
		return tempUserRepository.findAllByMobileNumber(mobileNumber);
	}
}
