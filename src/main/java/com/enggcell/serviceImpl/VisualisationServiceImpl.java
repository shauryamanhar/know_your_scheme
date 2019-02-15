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

import com.enggcell.entities.News;
import com.enggcell.entities.Visualisation;
import com.enggcell.repositories.NewsRepository;
import com.enggcell.repositories.VisualisationRepository;
import com.enggcell.services.NewsService;
import com.enggcell.services.VisualisationService;

@Service
@Transactional
@Scope(value = "request")

public class VisualisationServiceImpl implements VisualisationService {

    @Resource
    private VisualisationRepository visualisationRepository;
    
    public DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    public DataSource getDataSource() {
        return dataSource;
    }
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        System.out.println(dataSource);
    }
    
    @Override
    public Visualisation findOne(Long id) {
        return visualisationRepository.findOne(id);
    }

    @Override
    public void save(Visualisation registrations) {
    	visualisationRepository.save(registrations);
    }
    
    @Override
    public List<Visualisation> findAll(){
    	return visualisationRepository.findAll();
    }
    
    @Override
    public void delete(Long id){
    	visualisationRepository.delete(id);
    }

	@Override
	public Visualisation findAllByHeadline(String headline) {
		return visualisationRepository.findAllByHeadline(headline);
	}

	@Override
	public List<Visualisation> findAllByCategory(String category) {
		return visualisationRepository.findAllByCategory(category);
	}
}
