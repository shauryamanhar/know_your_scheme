package com.enggcell.serviceImpl;

import com.enggcell.entities.StatementGovernmentSchemes;
import com.enggcell.repositories.StateGovernmentSchemesRepository;
import com.enggcell.services.StateGovernmentSchemesService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Service
@Transactional
@Scope(value = "request")

public class StateGovernmentSchemesServiceImpl implements StateGovernmentSchemesService {

    @Resource
    private StateGovernmentSchemesRepository stateGovernmentSchemesRepository;
    
    
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
    public StatementGovernmentSchemes findOne(Long id) {
        return stateGovernmentSchemesRepository.findOne(id);
    }

    @Override
    public void save(StatementGovernmentSchemes registrations) {
        stateGovernmentSchemesRepository.save(registrations);
    }
    
    @Override
    public List<StatementGovernmentSchemes> findAll(){
    	return stateGovernmentSchemesRepository.findAll();
    }
    
    @Override
    public void delete(Long id){
    	stateGovernmentSchemesRepository.delete(id);
    }

	@Override
	public StatementGovernmentSchemes findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(
			String schemeName, String typeOfScheme, String schemeLink) {
		return stateGovernmentSchemesRepository.findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(schemeName, typeOfScheme, schemeLink);
	}

	@Override
	public List<StatementGovernmentSchemes> findAllByTypeOfScheme(
			String typeOfScheme) {
		return stateGovernmentSchemesRepository.findAllByTypeOfScheme(typeOfScheme);
	}

	@Override
	public List<StatementGovernmentSchemes> findAllBySchemeNameAndTypeOfScheme(
			String schemeName, String typeOfScheme) {
		return stateGovernmentSchemesRepository.findAllBySchemeNameAndTypeOfScheme(schemeName, typeOfScheme);
	}

	@Override
	public List<StatementGovernmentSchemes> findAllByOldNew(String oldNew) {
		return stateGovernmentSchemesRepository.findAllByOldNew(oldNew);
	}

	@Override
	public List<StatementGovernmentSchemes> findAllBySearchKeyword(
			String searchKey) {
		
		jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        String sql = "SELECT * FROM stategovernmentschemes where lower(schemeName) like '%" + searchKey + "%'";
        List<StatementGovernmentSchemes> listOfGovernmentSchemesForSearch = jdbcTemplate.query(sql, new RowMapper<StatementGovernmentSchemes>() {
        	@Override
	        public StatementGovernmentSchemes mapRow(ResultSet rs, int rowNum) throws SQLException {
        		StatementGovernmentSchemes newsData = new StatementGovernmentSchemes();
	        	
	        	//newsData.setId(rs.getInt("contact_id"));
	        	newsData.setId(rs.getLong("id"));
	        	newsData.setSchemeName(rs.getString("schemeName"));
	        	newsData.setSchemeLink(rs.getString("schemeLink"));
	        	newsData.setAddedDate(rs.getDate("addedDate"));
	        	newsData.setDetailedInfo(rs.getString("detailedInfo"));
	        	newsData.setTypeOfScheme(rs.getString("typeOfScheme"));
	        	newsData.setLastModifiedDate(rs.getString("lastModifiedDate"));
	        	newsData.setImageUrl(rs.getString("imageUrl"));
	        	newsData.setVideosUrl(rs.getString("videosUrl"));
	        	newsData.setActualLastModifiedDate(rs.getTimestamp("actualLastModifiedDate"));
	        	newsData.setOldNew(rs.getString("oldNew"));
	            return newsData;
	        }
	 
	    });
		return listOfGovernmentSchemesForSearch;
	}
}
