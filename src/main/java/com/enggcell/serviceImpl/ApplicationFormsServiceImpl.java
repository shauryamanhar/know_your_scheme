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

import com.enggcell.entities.ApplicationForms;
import com.enggcell.repositories.ApplicationFormsRepository;
import com.enggcell.services.ApplicationFormsService;

@Service
@Transactional
@Scope(value = "request")

public class ApplicationFormsServiceImpl implements ApplicationFormsService {

    @Resource
    private ApplicationFormsRepository applicationFormsRepository;
    
    @Override
    public ApplicationForms findOne(Long id) {
        return applicationFormsRepository.findOne(id);
    }

    @Override
    public void save(ApplicationForms registrations) {
    	applicationFormsRepository.save(registrations);
    }
    
    @Override
    public List<ApplicationForms> findAll(){
    	return applicationFormsRepository.findAll();
    }
    
    @Override
    public void delete(Long id){
    	applicationFormsRepository.delete(id);
    }

	@Override
	public ApplicationForms findAllByApplicationHeadlineAndUpdatedDate(
			String applicationHeadline, String updatedDate) {
		return applicationFormsRepository.findAllByApplicationHeadlineAndUpdatedDate(applicationHeadline, updatedDate);
	}

	@Override
	public List<ApplicationForms> findAllByOldNew(String oldNew) {
		return applicationFormsRepository.findAllByOldNew(oldNew);
	}
}
