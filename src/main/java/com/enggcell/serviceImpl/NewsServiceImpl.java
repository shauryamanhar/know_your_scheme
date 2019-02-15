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
import com.enggcell.repositories.NewsRepository;
import com.enggcell.services.NewsService;

@Service
@Transactional
@Scope(value = "request")

public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsRepository newsRepository;
    
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
    public News findOne(Long id) {
        return newsRepository.findOne(id);
    }

    @Override
    public void save(News registrations) {
    	newsRepository.save(registrations);
    }
    
    @Override
    public List<News> findAll(){
    	return newsRepository.findAll();
    }
    
    @Override
    public void delete(Long id){
    	newsRepository.delete(id);
    }

	@Override
	public News findAllByNewsDateAndNewsHeadline(Timestamp newsDate,
			String newsHeadline) {
		return newsRepository.findAllByNewsDateAndNewsHeadline(newsDate, newsHeadline);
	}

	@Override
	public List<News> findAllByTypeOfNews(String typeOfNews) {
		return newsRepository.findAllByTypeOfNews(typeOfNews);
	}
}
