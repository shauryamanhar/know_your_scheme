package com.enggcell.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.News;

@Repository
@Scope(value = "request")

public interface NewsRepository extends JpaRepository<News, Long> {
	public News findAllByNewsDateAndNewsHeadline(Timestamp newsDate, String newsHeadline);
	public List<News> findAllByTypeOfNews(String typeOfNews);
}
