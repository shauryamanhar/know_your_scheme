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

import com.enggcell.entities.GovernmentJobs;
import com.enggcell.repositories.GovernmentJobsRepository;
import com.enggcell.services.GovernmentJobsService;

@Service
@Transactional
@Scope(value = "request")

public class GovernmentJobsServiceImpl implements GovernmentJobsService {

    @Resource
    private GovernmentJobsRepository governmentJobsRepository;
    
    @Override
    public GovernmentJobs findOne(Long id) {
        return governmentJobsRepository.findOne(id);
    }

    @Override
    public void save(GovernmentJobs registrations) {
    	governmentJobsRepository.save(registrations);
    }
    
    @Override
    public List<GovernmentJobs> findAll(){
    	return governmentJobsRepository.findAll();
    }
    
    @Override
    public void delete(Long id){
    	governmentJobsRepository.delete(id);
    }

	@Override
	public GovernmentJobs findAllByGovernmentJobHeadlineAndUpdatedDate(
			String governmentJobHeadline, String updatedDate) {
		return governmentJobsRepository.findAllByGovernmentJobHeadlineAndUpdatedDate(governmentJobHeadline, updatedDate);
	}

	@Override
	public List<GovernmentJobs> findAllByOldNew(String oldNew) {
		return governmentJobsRepository.findAllByOldNew(oldNew);
	}
}
