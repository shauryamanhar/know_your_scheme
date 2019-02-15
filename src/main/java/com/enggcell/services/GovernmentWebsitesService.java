package com.enggcell.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.GovernmentWebsites;
import com.enggcell.entities.StatementGovernmentSchemes;

@Scope(value = "request")

public interface GovernmentWebsitesService {

public GovernmentWebsites findOne(Long id);

public void save(GovernmentWebsites registrations);

public List<GovernmentWebsites> findAll();

public GovernmentWebsites findAllByGovernmentWebsitesHeadlineAndUpdatedDate(String governmentWebsitesHeadline, String updatedDate);

public List<GovernmentWebsites> findAllByOldNew(String oldNew);

public void delete(Long id);

}
