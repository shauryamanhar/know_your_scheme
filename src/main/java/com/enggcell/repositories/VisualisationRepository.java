package com.enggcell.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.News;
import com.enggcell.entities.Visualisation;

@Repository
@Scope(value = "request")

public interface VisualisationRepository extends JpaRepository<Visualisation, Long> {
	public Visualisation findAllByHeadline(String headline);
	public List<Visualisation> findAllByCategory(String category);
}
