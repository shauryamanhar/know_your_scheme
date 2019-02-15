package com.enggcell.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.ApplicationForms;
import com.enggcell.entities.GovernmentWebsites;
import com.enggcell.entities.StatementGovernmentSchemes;

@Repository
@Scope(value = "request")

public interface ApplicationFormsRepository extends JpaRepository<ApplicationForms, Long> {
	public ApplicationForms findAllByApplicationHeadlineAndUpdatedDate(String applicationHeadline, String updatedDate);
	public List<ApplicationForms> findAllByOldNew(String oldNew);
}
