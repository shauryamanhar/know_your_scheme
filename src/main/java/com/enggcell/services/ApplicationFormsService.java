package com.enggcell.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.ApplicationForms;
import com.enggcell.entities.StatementGovernmentSchemes;

@Scope(value = "request")

public interface ApplicationFormsService {

public ApplicationForms findOne(Long id);

public void save(ApplicationForms registrations);

public List<ApplicationForms> findAll();

public ApplicationForms findAllByApplicationHeadlineAndUpdatedDate(String applicationHeadline, String updatedDate);

public List<ApplicationForms> findAllByOldNew(String oldNew);

public void delete(Long id);

}
