package com.enggcell.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.News;
import com.enggcell.entities.Visualisation;

@Scope(value = "request")

public interface VisualisationService {

public Visualisation findOne(Long id);

public void save(Visualisation registrations);

public List<Visualisation> findAll();

public Visualisation findAllByHeadline(String headline);

public List<Visualisation> findAllByCategory(String category);

public void delete(Long id);

}
