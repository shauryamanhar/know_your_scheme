package com.enggcell.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.News;

@Scope(value = "request")

public interface NewsService {

public News findOne(Long id);

public void save(News registrations);

public List<News> findAll();

public News findAllByNewsDateAndNewsHeadline(Timestamp newsDate, String newsHeadline);

public List<News> findAllByTypeOfNews(String typeOfNews);

public void delete(Long id);

}
