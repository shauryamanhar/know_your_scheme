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

import com.enggcell.entities.GovernmentWebsites;
import com.enggcell.repositories.GovernmentWebsitesRepository;
import com.enggcell.services.GovernmentWebsitesService;

@Service
@Transactional
@Scope(value = "request")

public class GovernmentWebsitesServiceImpl implements GovernmentWebsitesService {

    @Resource
    private GovernmentWebsitesRepository governmentWebsitesRepository;
    
    @Override
    public GovernmentWebsites findOne(Long id) {
        return governmentWebsitesRepository.findOne(id);
    }

    @Override
    public void save(GovernmentWebsites registrations) {
    	governmentWebsitesRepository.save(registrations);
    }
    
    @Override
    public List<GovernmentWebsites> findAll(){
    	return governmentWebsitesRepository.findAll();
    }
    
    @Override
    public void delete(Long id){
    	governmentWebsitesRepository.delete(id);
    }

	@Override
	public GovernmentWebsites findAllByGovernmentWebsitesHeadlineAndUpdatedDate(
			String governmentWebsitesHeadline, String updatedDate) {
		return governmentWebsitesRepository.findAllByGovernmentWebsitesHeadlineAndUpdatedDate(governmentWebsitesHeadline, updatedDate);
	}

	@Override
	public List<GovernmentWebsites> findAllByOldNew(String oldNew) {
		return governmentWebsitesRepository.findAllByOldNew(oldNew);
	}
}
