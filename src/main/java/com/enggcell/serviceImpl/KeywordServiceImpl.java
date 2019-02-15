package com.enggcell.serviceImpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.enggcell.entities.Keyword;
import com.enggcell.entities.News;
import com.enggcell.entities.StatementGovernmentSchemes;
import com.enggcell.repositories.KeywordRepository;
import com.enggcell.repositories.NewsRepository;
import com.enggcell.services.KeywordService;
import com.enggcell.services.NewsService;

@Service
@Transactional
@Scope(value = "request")

public class KeywordServiceImpl implements KeywordService {

    @Resource
    private KeywordRepository keywordRepository;
    
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
    public Keyword findOne(Long id) {
        return keywordRepository.findOne(id);
    }

    @Override
    public void save(Keyword registrations) {
    	keywordRepository.save(registrations);
    }
    
    @Override
    public List<Keyword> findAll(){
    	return keywordRepository.findAll();
    }
    
    @Override
    public void delete(Long id){
    	keywordRepository.delete(id);
    }
    
    @Override
	public List<Keyword> findAllByKeyword(String searchKey) {
		
		jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        String sql = "SELECT distinct keyword FROM Keyword where lower(keyword) like '" + searchKey + "%'";
        List<Keyword> listOfGovernmentSchemesForSearch = jdbcTemplate.query(sql, new RowMapper<Keyword>() {
        	@Override
	        public Keyword mapRow(ResultSet rs, int rowNum) throws SQLException {
        		Keyword newsData = new Keyword();
	        	
	        	//newsData.setId(rs.getInt("contact_id"));
	        	newsData.setKeyword(rs.getString("keyword"));
	            return newsData;
	        }
	 
	    });
		return listOfGovernmentSchemesForSearch;
	}
}
