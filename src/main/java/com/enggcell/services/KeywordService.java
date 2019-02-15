package com.enggcell.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.Keyword;

@Scope(value = "request")

public interface KeywordService {

public Keyword findOne(Long id);

public void save(Keyword registrations);

public List<Keyword> findAll();

public void delete(Long id);

public List<Keyword> findAllByKeyword(String searchKey);

}
