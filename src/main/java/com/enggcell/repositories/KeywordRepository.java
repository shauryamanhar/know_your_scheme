package com.enggcell.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.Keyword;
import com.enggcell.entities.News;

@Repository
@Scope(value = "request")

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
