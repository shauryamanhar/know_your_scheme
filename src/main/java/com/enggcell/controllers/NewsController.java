/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enggcell.controllers;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enggcell.entities.News;
import com.enggcell.services.NewsService;
import com.enggcell.utilities.SortTimelineByAddedDateDesc;

/**
 *
 * @author 1003
 */
@Scope("request")
@RequestMapping(value = "/news")
@Controller
public class NewsController {
	
	@Autowired
	NewsService newsService;
	
    @Autowired
    ThreadPoolTaskExecutor taskExecutor;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String indexPage(Model model, HttpServletRequest request) {
    	List<News> news = newsService.findAll();
    	Collections.sort(news, new SortTimelineByAddedDateDesc());
    	model.addAttribute("newsListForMarque", news);
        return "index";
    }
    
    @RequestMapping(value = "/{newsHeadline}/{newsDate}", method = RequestMethod.GET)
    public String hindiIndexPage(Model model, HttpServletRequest request, @PathVariable("newsHeadline") String newsHeadline, @PathVariable("newsDate") Timestamp newsDate) {
    	News newsDetailed = newsService.findAllByNewsDateAndNewsHeadline(newsDate, newsHeadline);
    	model.addAttribute("newsDetailed", newsDetailed);
        return "newsDetail";
    }

}
