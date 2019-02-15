package com.enggcell.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.GovernmentJobs;
import com.enggcell.entities.StatementGovernmentSchemes;

@Scope(value = "request")

public interface GovernmentJobsService {

public GovernmentJobs findOne(Long id);

public void save(GovernmentJobs registrations);

public List<GovernmentJobs> findAll();

public GovernmentJobs findAllByGovernmentJobHeadlineAndUpdatedDate(String governmentJobHeadline, String updatedDate);

public List<GovernmentJobs> findAllByOldNew(String oldNew);

public void delete(Long id);

}
