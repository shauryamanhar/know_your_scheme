package com.enggcell.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.GovernmentWebsites;
import com.enggcell.entities.StatementGovernmentSchemes;

@Repository
@Scope(value = "request")

public interface GovernmentWebsitesRepository extends JpaRepository<GovernmentWebsites, Long> {
	public GovernmentWebsites findAllByGovernmentWebsitesHeadlineAndUpdatedDate(String governmentWebsitesHeadline, String updatedDate);
	public List<GovernmentWebsites> findAllByOldNew(String oldNew);
}
