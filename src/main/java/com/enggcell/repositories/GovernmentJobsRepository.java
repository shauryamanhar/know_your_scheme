package com.enggcell.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.GovernmentJobs;
import com.enggcell.entities.StatementGovernmentSchemes;

@Repository
@Scope(value = "request")

public interface GovernmentJobsRepository extends JpaRepository<GovernmentJobs, Long> {
	public GovernmentJobs findAllByGovernmentJobHeadlineAndUpdatedDate(String governmentJobHeadline, String updatedDate);
	public List<GovernmentJobs> findAllByOldNew(String oldNew);
}
