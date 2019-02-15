/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enggcell.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.enggcell.entities.ApplicationForms;
import com.enggcell.entities.CentralGovernmentSchemes;
import com.enggcell.entities.GovernmentJobs;
import com.enggcell.entities.GovernmentWebsites;
import com.enggcell.entities.Keyword;
import com.enggcell.entities.News;
import com.enggcell.entities.StatementGovernmentSchemes;
import com.enggcell.entities.User;
import com.enggcell.entities.UserCategories;
import com.enggcell.services.ApplicationFormsService;
import com.enggcell.services.CentralGovernmentSchemesService;
import com.enggcell.services.GovernmentJobsService;
import com.enggcell.services.GovernmentWebsitesService;
import com.enggcell.services.KeywordService;
import com.enggcell.services.NewsService;
import com.enggcell.services.StateGovernmentSchemesService;
import com.enggcell.services.UserCategoriesService;
import com.enggcell.services.UserService;
import com.enggcell.services.VisualisationService;
import com.enggcell.utilities.SortStateGovernmentSchemesByActualLastModifiedDateDesc;
import com.enggcell.utilities.SortTimelineByAddedDateDesc;

/**
 *
 * @author 1003
 */
@Scope("request")
@RequestMapping(value = "/schemes")
@Controller
public class SchemeController {

	@Autowired
	CentralGovernmentSchemesService centralGovernmentSchemesService;

	@Autowired
	StateGovernmentSchemesService stateGovernmentSchemesService;
	
	@Autowired
	KeywordService keywordService;
	
	@Autowired
	ApplicationFormsService applicationFormsService;
	
	@Autowired
	VisualisationService visualisationService;
	
	@Autowired
	GovernmentWebsitesService governmentWebsitesService;

	@Autowired
	GovernmentJobsService governmentJobsService;

	@Autowired
	NewsService newsService;

	@Autowired
	UserCategoriesService userCategoriesService;

	@Autowired
	UserService userService;

	@Autowired
	ThreadPoolTaskExecutor taskExecutor;


	@RequestMapping(value = "", method = RequestMethod.GET)
	public String scheme(Model model, HttpServletRequest request) {
		
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){

				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");

				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");

				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);

				return "schemes"; 
			}else{


				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);

				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");

				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");

				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);

				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();				
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "schemes";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}

	}
	
	@RequestMapping(value = "/visualisation/categories/{category}", method = RequestMethod.GET)
	public String visualisationDetailedPage(Model model, HttpServletRequest request,  @PathVariable("category") String category) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				
				ArrayList<String> visualisationCategory = new ArrayList<String>();
				visualisationCategory.add("accident");
				visualisationCategory.add("children");
				visualisationCategory.add("education");
				visualisationCategory.add("electricity");
				visualisationCategory.add("employment");
				visualisationCategory.add("mgnrega");
				visualisationCategory.add("power");
				visualisationCategory.add("milk");
				visualisationCategory.add("motor-vehicles");
				visualisationCategory.add("mahatma-gandhi-national-rural-employment-guarantee-act");
				model.addAttribute("visualisationCategory", visualisationService.findAllByCategory(category));
				
				HashMap<String, String> mapOfVisualisationWithUrl = new HashMap<String, String>();
				mapOfVisualisationWithUrl.put("accident", "Accident");
				mapOfVisualisationWithUrl.put("children", "Children");
				mapOfVisualisationWithUrl.put("education", "Education");
				mapOfVisualisationWithUrl.put("electricity", "Electricity");
				mapOfVisualisationWithUrl.put("employment", "Employment");
				mapOfVisualisationWithUrl.put("mgnrega", "MGNREGA");
				mapOfVisualisationWithUrl.put("power", "Power");
				mapOfVisualisationWithUrl.put("milk", "Milk");
				mapOfVisualisationWithUrl.put("motor-vehicles", "Motor Vehicles");
				mapOfVisualisationWithUrl.put("mahatma-gandhi-national-rural-employment-guarantee-act", "Mahatma Gandhi National Rural Employment Guarantee Act");
				model.addAttribute("mapOfVisualisationWithUrl", mapOfVisualisationWithUrl);
				
				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");

				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");

				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);

				return "visulisationFullDetail"; 
			}else{
				
				
				ArrayList<String> visualisationCategory = new ArrayList<String>();
				visualisationCategory.add("accident");
				visualisationCategory.add("children");
				visualisationCategory.add("education");
				visualisationCategory.add("electricity");
				visualisationCategory.add("education");
				visualisationCategory.add("employment");
				visualisationCategory.add("mgnrega");
				visualisationCategory.add("power");
				visualisationCategory.add("milk");
				visualisationCategory.add("motor-vehicles");
				visualisationCategory.add("mahatma-gandhi-national-rural-employment-guarantee-act");
				visualisationCategory.add("milk");
				
				model.addAttribute("visualisationCategory", visualisationService.findAllByCategory(category));
				
				HashMap<String, String> mapOfVisualisationWithUrl = new HashMap<String, String>();
				mapOfVisualisationWithUrl.put("accident", "Accident");
				mapOfVisualisationWithUrl.put("children", "Children");
				mapOfVisualisationWithUrl.put("education", "Education");
				mapOfVisualisationWithUrl.put("electricity", "Electricity");
				mapOfVisualisationWithUrl.put("employment", "Employment");
				mapOfVisualisationWithUrl.put("mgnrega", "MGNREGA");
				mapOfVisualisationWithUrl.put("power", "Power");
				mapOfVisualisationWithUrl.put("milk", "Milk");
				mapOfVisualisationWithUrl.put("motor-vehicles", "Motor Vehicles");
				mapOfVisualisationWithUrl.put("mahatma-gandhi-national-rural-employment-guarantee-act", "Mahatma Gandhi National Rural Employment Guarantee Act");
				model.addAttribute("mapOfVisualisationWithUrl", mapOfVisualisationWithUrl);
				
				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);

				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");

				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");

				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);

				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();				
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "visulisationFullDetail";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	//visualisation
	@RequestMapping(value = "/visualisation/categories", method = RequestMethod.GET)
	public String visualisation(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				
				ArrayList<String> visualisationCategory = new ArrayList<String>();
				visualisationCategory.add("accident");
				visualisationCategory.add("children");
				visualisationCategory.add("education");
				visualisationCategory.add("electricity");
				visualisationCategory.add("employment");
				visualisationCategory.add("mgnrega");
				visualisationCategory.add("power");
				visualisationCategory.add("milk");
				visualisationCategory.add("motor-vehicles");
				visualisationCategory.add("mahatma-gandhi-national-rural-employment-guarantee-act");
				model.addAttribute("visualisationCategory", visualisationCategory);
				
				HashMap<String, String> mapOfVisualisationWithUrl = new HashMap<String, String>();
				mapOfVisualisationWithUrl.put("accident", "Accident");
				mapOfVisualisationWithUrl.put("children", "Children");
				mapOfVisualisationWithUrl.put("education", "Education");
				mapOfVisualisationWithUrl.put("electricity", "Electricity");
				mapOfVisualisationWithUrl.put("employment", "Employment");
				mapOfVisualisationWithUrl.put("mgnrega", "MGNREGA");
				mapOfVisualisationWithUrl.put("power", "Power");
				mapOfVisualisationWithUrl.put("milk", "Milk");
				mapOfVisualisationWithUrl.put("motor-vehicles", "Motor Vehicles");
				mapOfVisualisationWithUrl.put("mahatma-gandhi-national-rural-employment-guarantee-act", "Mahatma Gandhi National Rural Employment Guarantee Act");
				model.addAttribute("mapOfVisualisationWithUrl", mapOfVisualisationWithUrl);
				
				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");

				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");

				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);

				return "visiualisation"; 
			}else{
				
				
				ArrayList<String> visualisationCategory = new ArrayList<String>();
				visualisationCategory.add("accident");
				visualisationCategory.add("children");
				visualisationCategory.add("education");
				visualisationCategory.add("electricity");
				visualisationCategory.add("education");
				visualisationCategory.add("employment");
				visualisationCategory.add("mgnrega");
				visualisationCategory.add("power");
				visualisationCategory.add("milk");
				visualisationCategory.add("motor-vehicles");
				visualisationCategory.add("mahatma-gandhi-national-rural-employment-guarantee-act");
				visualisationCategory.add("milk");
				
				model.addAttribute("visualisationCategory", visualisationCategory);
				
				HashMap<String, String> mapOfVisualisationWithUrl = new HashMap<String, String>();
				mapOfVisualisationWithUrl.put("accident", "Accident");
				mapOfVisualisationWithUrl.put("children", "Children");
				mapOfVisualisationWithUrl.put("education", "Education");
				mapOfVisualisationWithUrl.put("electricity", "Electricity");
				mapOfVisualisationWithUrl.put("employment", "Employment");
				mapOfVisualisationWithUrl.put("mgnrega", "MGNREGA");
				mapOfVisualisationWithUrl.put("power", "Power");
				mapOfVisualisationWithUrl.put("milk", "Milk");
				mapOfVisualisationWithUrl.put("motor-vehicles", "Motor Vehicles");
				mapOfVisualisationWithUrl.put("mahatma-gandhi-national-rural-employment-guarantee-act", "Mahatma Gandhi National Rural Employment Guarantee Act");
				model.addAttribute("mapOfVisualisationWithUrl", mapOfVisualisationWithUrl);
				
				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);

				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");

				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");

				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);

				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();				
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "visiualisation";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	@RequestMapping(value = "/search/{searchStringFilter}/filter-By-State", method = RequestMethod.POST)
	public String searchSchemeFilter(Model model, HttpServletRequest request, @PathVariable("searchStringFilter") String searchStringFilter) {
		try{

			ArrayList<String> healthKeywords = new ArrayList<String>();
			healthKeywords.add("health");
			healthKeywords.add("ambulance");
			healthKeywords.add("diseases");
			healthKeywords.add("healthiness");
			healthKeywords.add("fitness");
			healthKeywords.add("pregnant");
			healthKeywords.add("pregnancy");
			healthKeywords.add("cancer");
			healthKeywords.add("medical");
			healthKeywords.add("treatment");
			healthKeywords.add("Swasthya");
			healthKeywords.add("hospital");
			healthKeywords.add("Suraksha");
			healthKeywords.add("hriday");
			healthKeywords.add("medicine");
			healthKeywords.add("accident");
			healthKeywords.add("vaccination");
			healthKeywords.add("tablet");
			healthKeywords.add("vaccine");
			healthKeywords.add("indradhanush");
			healthKeywords.add("arogya");
			healthKeywords.add("check-up");
			healthKeywords.add("mri");
			healthKeywords.add("clinic");
			healthKeywords.add("blind");
			healthKeywords.add("chikitsa");
			healthKeywords.add("children");
			healthKeywords.add("child");
			healthKeywords.add("heart");
			healthKeywords.add("sishu");
			healthKeywords.add("malaria");
			healthKeywords.add("eye");
			healthKeywords.add("care");
			healthKeywords.add("hiv");
			healthKeywords.add("acid");
			healthKeywords.add("victims");
			healthKeywords.add("patients");
			healthKeywords.add("liver");


			ArrayList<String> womenHealthKeywords = new ArrayList<String>();
			womenHealthKeywords.add("health");
			womenHealthKeywords.add("ambulance");
			womenHealthKeywords.add("diseases");
			womenHealthKeywords.add("healthiness");
			womenHealthKeywords.add("fitness");
			womenHealthKeywords.add("pregnant");
			womenHealthKeywords.add("pregnancy");
			womenHealthKeywords.add("cancer");
			womenHealthKeywords.add("medical");
			womenHealthKeywords.add("treatment");
			womenHealthKeywords.add("Swasthya");
			womenHealthKeywords.add("hospital");
			womenHealthKeywords.add("suraksha");
			womenHealthKeywords.add("hriday");
			womenHealthKeywords.add("medicine");
			womenHealthKeywords.add("accident");
			womenHealthKeywords.add("vaccination");
			womenHealthKeywords.add("tablet");
			womenHealthKeywords.add("vaccine");
			womenHealthKeywords.add("indradhanush");
			womenHealthKeywords.add("matri");
			womenHealthKeywords.add("matra");
			womenHealthKeywords.add("mahila");
			womenHealthKeywords.add("sakti");

			ArrayList<String> pregnantWomenKeywords = new ArrayList<String>();
			pregnantWomenKeywords.add("pregnant");
			pregnantWomenKeywords.add("pregnancy");
			pregnantWomenKeywords.add("ambulance");
			pregnantWomenKeywords.add("kcr");
			pregnantWomenKeywords.add("matri");
			pregnantWomenKeywords.add("matra");
			pregnantWomenKeywords.add("matrutva");
			pregnantWomenKeywords.add("maternity");
			pregnantWomenKeywords.add("child");
			pregnantWomenKeywords.add("health");
			pregnantWomenKeywords.add("medical");
			pregnantWomenKeywords.add("treatment");

			ArrayList<String> womenKeywords = new ArrayList<String>();
			womenKeywords.add("women");
			womenKeywords.add("lady");
			womenKeywords.add("girl");
			womenKeywords.add("female");
			womenKeywords.add("kanya");
			womenKeywords.add("widow");
			womenKeywords.add("aurat");
			womenKeywords.add("balika");
			womenKeywords.add("matron");
			womenKeywords.add("beti");
			womenKeywords.add("sanitary");
			womenKeywords.add("napkins");
			womenKeywords.add("pad");
			womenKeywords.add("mukhbir");
			womenKeywords.add("vaishnavi");
			womenKeywords.add("wife");
			womenKeywords.add("sister");
			womenKeywords.add("lakshmi");
			womenKeywords.add("laxmi");
			womenKeywords.add("bhagya");
			womenKeywords.add("matra");
			womenKeywords.add("matri");
			womenKeywords.add("pressure");
			womenKeywords.add("cooker");
			womenKeywords.add("sakti");
			womenKeywords.add("ujjwala");

			ArrayList<String> skillKeyword = new ArrayList<String>();
			skillKeyword.add("skill");
			skillKeyword.add("kalakar");
			skillKeyword.add("artists");
			skillKeyword.add("talent");
			skillKeyword.add("fitness");
			skillKeyword.add("strength");

			ArrayList<String> educationKeyword = new ArrayList<String>();
			educationKeyword.add("eudcation");
			educationKeyword.add("schooling");
			educationKeyword.add("tuition");
			educationKeyword.add("coaching");
			educationKeyword.add("training");
			educationKeyword.add("knowledge");
			educationKeyword.add("learning");
			educationKeyword.add("shiksha");
			educationKeyword.add("swayam");
			educationKeyword.add("courses");
			educationKeyword.add("vidyarthi");
			educationKeyword.add("utkarsh");
			educationKeyword.add("vaigyanik");
			educationKeyword.add("kishore");
			educationKeyword.add("vidya");
			educationKeyword.add("library");
			educationKeyword.add("siksha");
			educationKeyword.add("medhavi");
			educationKeyword.add("gyan");
			educationKeyword.add("pariksha");
			educationKeyword.add("graduates");
			educationKeyword.add("vidyalaya");
			educationKeyword.add("shikshit");
			educationKeyword.add("research");
			educationKeyword.add("computer");
			educationKeyword.add("laptop");
			educationKeyword.add("scholarship");
			educationKeyword.add("matric");

			ArrayList<String> studentKeyword = new ArrayList<String>();
			studentKeyword.add("students");
			studentKeyword.add("schools");
			studentKeyword.add("undergraduate");
			studentKeyword.add("postgraduate");
			studentKeyword.add("schoolchild");
			studentKeyword.add("schoolboy");
			studentKeyword.add("schoolgirl");
			studentKeyword.add("books");
			studentKeyword.add("9th class");
			studentKeyword.add("10th class");
			studentKeyword.add("11th class");
			studentKeyword.add("12th class");
			studentKeyword.add("laptop");
			studentKeyword.add("swayam");
			studentKeyword.add("courses");
			studentKeyword.add("vidyarthi");
			studentKeyword.add("utkarsh");
			studentKeyword.add("vaigyanik");
			studentKeyword.add("kishore");
			studentKeyword.add("meritorious");
			studentKeyword.add("vidya");
			studentKeyword.add("library");
			studentKeyword.add("siksha");
			studentKeyword.add("matric");
			studentKeyword.add("medhavi");
			studentKeyword.add("gyan");
			studentKeyword.add("shiksha");
			studentKeyword.add("pariksha");
			studentKeyword.add("graduates");
			studentKeyword.add("vidyalaya");
			studentKeyword.add("shikshit");
			studentKeyword.add("research");
			studentKeyword.add("computer");
			studentKeyword.add("scholarship");


			ArrayList<String> scholarshipKeyword = new ArrayList<String>();
			scholarshipKeyword.add("scholarships");
			scholarshipKeyword.add("fellowship");
			scholarshipKeyword.add("laptop");
			scholarshipKeyword.add("meritorious");
			scholarshipKeyword.add("matric");
			scholarshipKeyword.add("9th class");
			scholarshipKeyword.add("10th class");
			scholarshipKeyword.add("11th class");
			scholarshipKeyword.add("12th class");
			scholarshipKeyword.add("learning");
			scholarshipKeyword.add("book");
			scholarshipKeyword.add("learning");
			scholarshipKeyword.add("knowledge");
			scholarshipKeyword.add("education");
			scholarshipKeyword.add("scholarships");
			scholarshipKeyword.add("scholarships");

			ArrayList<String> sportsKeyword = new ArrayList<String>();
			sportsKeyword.add("sports");
			sportsKeyword.add("khel");
			sportsKeyword.add("game");
			sportsKeyword.add("play");
			sportsKeyword.add("yoga");
			sportsKeyword.add("fitness");

			ArrayList<String> agricultureKeyword = new ArrayList<String>();
			agricultureKeyword.add("agriculture");
			agricultureKeyword.add("krishi");
			agricultureKeyword.add("farmer");
			agricultureKeyword.add("farmers");
			agricultureKeyword.add("kisan");
			agricultureKeyword.add("fasal");
			agricultureKeyword.add("farming");
			agricultureKeyword.add("cultivation");
			agricultureKeyword.add("husbandry");
			agricultureKeyword.add("soil");
			agricultureKeyword.add("land");
			agricultureKeyword.add("farm");
			agricultureKeyword.add("agri");
			agricultureKeyword.add("rythu");
			agricultureKeyword.add("saur");
			agricultureKeyword.add("sujala");
			agricultureKeyword.add("bhavantar");
			agricultureKeyword.add("bharpai");

			ArrayList<String> pensionKeyword = new ArrayList<String>();
			pensionKeyword.add("pension");
			pensionKeyword.add("retirement");
			pensionKeyword.add("old");
			pensionKeyword.add("welfare");


			ArrayList<String> roadHighwayKeyword = new ArrayList<String>();
			roadHighwayKeyword.add("road");
			roadHighwayKeyword.add("highway");
			roadHighwayKeyword.add("route");

			ArrayList<String> urbanKeyword = new ArrayList<String>();
			urbanKeyword.add("urban");
			urbanKeyword.add("city");
			urbanKeyword.add("town");
			urbanKeyword.add("metropolitan");

			ArrayList<String> startupKeyword = new ArrayList<String>();
			startupKeyword.add("startup");
			startupKeyword.add("start");
			startupKeyword.add("entrepreneurship");
			startupKeyword.add("entrepreneur");
			startupKeyword.add("creation");
			startupKeyword.add("business");
			startupKeyword.add("vyapar");
			startupKeyword.add("naveen");
			startupKeyword.add("udhyog");

			ArrayList<String> employmentKeyword = new ArrayList<String>();
			employmentKeyword.add("employment");
			employmentKeyword.add("unemployment");
			employmentKeyword.add("rozgar");
			employmentKeyword.add("rozgaar");
			employmentKeyword.add("rojgaar");
			employmentKeyword.add("berojgari");
			employmentKeyword.add("vacancy");
			employmentKeyword.add("vacancies");
			employmentKeyword.add("job");
			employmentKeyword.add("jobs");
			employmentKeyword.add("employ");
			employmentKeyword.add("swarozgar");
			employmentKeyword.add("yaari");
			employmentKeyword.add("naukri");
			employmentKeyword.add("recruitment");
			employmentKeyword.add("kaushal");
			employmentKeyword.add("post");
			employmentKeyword.add("profession");
			employmentKeyword.add("work");
			employmentKeyword.add("hiring");
			employmentKeyword.add("hire");

			ArrayList<String> financeInsuaranceKeyword = new ArrayList<String>();
			financeInsuaranceKeyword.add("finance");
			financeInsuaranceKeyword.add("insuarance");
			financeInsuaranceKeyword.add("money");
			financeInsuaranceKeyword.add("financial");
			financeInsuaranceKeyword.add("economics");
			financeInsuaranceKeyword.add("business");
			financeInsuaranceKeyword.add("investment");
			financeInsuaranceKeyword.add("subsidy");
			financeInsuaranceKeyword.add("budget");
			financeInsuaranceKeyword.add("warranty");
			financeInsuaranceKeyword.add("guarantee");
			financeInsuaranceKeyword.add("bima");
			financeInsuaranceKeyword.add("dhan");
			financeInsuaranceKeyword.add("gst");
			financeInsuaranceKeyword.add("vyapar");
			financeInsuaranceKeyword.add("wallet");
			financeInsuaranceKeyword.add("payment");
			financeInsuaranceKeyword.add("khattar");
			financeInsuaranceKeyword.add("sand");

			ArrayList<String> houseInfrastructureKeyword = new ArrayList<String>();
			houseInfrastructureKeyword.add("house");
			houseInfrastructureKeyword.add("housing");
			houseInfrastructureKeyword.add("rera");
			houseInfrastructureKeyword.add("land");
			houseInfrastructureKeyword.add("infrastructure");
			houseInfrastructureKeyword.add("awas");
			houseInfrastructureKeyword.add("home");
			houseInfrastructureKeyword.add("aasra");
			houseInfrastructureKeyword.add("resident");
			houseInfrastructureKeyword.add("residence");
			houseInfrastructureKeyword.add("household");
			houseInfrastructureKeyword.add("tribe");
			houseInfrastructureKeyword.add("flats");
			houseInfrastructureKeyword.add("plots");
			houseInfrastructureKeyword.add("ghar");
			houseInfrastructureKeyword.add("mnrega");
			houseInfrastructureKeyword.add("toilets");
			houseInfrastructureKeyword.add("doorstep");
			houseInfrastructureKeyword.add("bhoomi");
			houseInfrastructureKeyword.add("bazaar");
			houseInfrastructureKeyword.add("swachh");
			houseInfrastructureKeyword.add("sharing");
			houseInfrastructureKeyword.add("twallet");

			ArrayList<String> loanKeyword = new ArrayList<String>();
			loanKeyword.add("loan");
			loanKeyword.add("due");
			loanKeyword.add("karz");
			loanKeyword.add("rin");
			loanKeyword.add("credit");
			loanKeyword.add("lend");
			loanKeyword.add("moneylending");

			ArrayList<String> ruralKeyword = new ArrayList<String>();
			ruralKeyword.add("rural");
			ruralKeyword.add("village");
			ruralKeyword.add("gram");
			ruralKeyword.add("gramin");
			ruralKeyword.add("johaar");
			ruralKeyword.add("poverty");
			ruralKeyword.add("bpl");
			ruralKeyword.add("agriculture");
			ruralKeyword.add("kisan");
			ruralKeyword.add("krishi");
			ruralKeyword.add("farming");

			ArrayList<String> teacherKeyword = new ArrayList<String>();
			teacherKeyword.add("teacher");
			teacherKeyword.add("teaching");
			teacherKeyword.add("educator");
			teacherKeyword.add("tutor");
			teacherKeyword.add("instructor");
			teacherKeyword.add("schoolteacher");
			teacherKeyword.add("lecturer");
			teacherKeyword.add("professor");
			teacherKeyword.add("mentor");
			teacherKeyword.add("guru");
			teacherKeyword.add("gyanodaya");

			ArrayList<String> digitalKeyword = new ArrayList<String>();
			digitalKeyword.add("digital");

			ArrayList<String> primeministerKeyword = new ArrayList<String>();
			primeministerKeyword.add("prime");
			primeministerKeyword.add("pradhan");
			primeministerKeyword.add("mantri");
			primeministerKeyword.add("modi");
			primeministerKeyword.add("narendra");


			ArrayList<String> electricityKeyword = new ArrayList<String>();
			electricityKeyword.add("electricity");
			electricityKeyword.add("koyla");
			electricityKeyword.add("electric");
			electricityKeyword.add("current");
			electricityKeyword.add("power");
			electricityKeyword.add("energy");
			electricityKeyword.add("ujjwala");
			electricityKeyword.add("urja");
			electricityKeyword.add("ujala");
			electricityKeyword.add("bulbs");
			electricityKeyword.add("tubelight");
			electricityKeyword.add("vidyut");
			electricityKeyword.add("uday");


			ArrayList<String> tourKeyword = new ArrayList<String>();
			tourKeyword.add("tour");
			tourKeyword.add("travels");
			tourKeyword.add("temple");
			tourKeyword.add("mandir");
			tourKeyword.add("mela");
			tourKeyword.add("mahotsav");
			tourKeyword.add("gorakhpurmahotsav");
			tourKeyword.add("ticket");
			tourKeyword.add("railway");
			tourKeyword.add("bus");
			tourKeyword.add("haj");
			tourKeyword.add("rail");
			tourKeyword.add("kailash");
			tourKeyword.add("udan");
			tourKeyword.add("air");
			tourKeyword.add("airport");
			tourKeyword.add("yatra");
			tourKeyword.add("bike");
			tourKeyword.add("taxi");
			tourKeyword.add("tirth");
			tourKeyword.add("tourism");

			ArrayList<String> animalKeyword = new ArrayList<String>();
			animalKeyword.add("animal");
			animalKeyword.add("pashu");
			animalKeyword.add("pashudhan");
			animalKeyword.add("cows");
			animalKeyword.add("goats");
			animalKeyword.add("gow");
			animalKeyword.add("cattle");
			animalKeyword.add("sheep");

			ArrayList<String> waterKeyword = new ArrayList<String>();
			waterKeyword.add("river");
			waterKeyword.add("water");
			waterKeyword.add("ganga");
			waterKeyword.add("samrakshana");
			waterKeyword.add("neeti");
			waterKeyword.add("udyamam");
			waterKeyword.add("bhagiratha");

			ArrayList<String> familyKeyword = new ArrayList<String>();
			familyKeyword.add("family");
			familyKeyword.add("parivar");
			familyKeyword.add("marriage");
			familyKeyword.add("shaadi");
			familyKeyword.add("pariwar");
			familyKeyword.add("vivah");
			familyKeyword.add("families");

			ArrayList<String> foodKeyword = new ArrayList<String>();
			foodKeyword.add("food");
			foodKeyword.add("bhojanalya");
			foodKeyword.add("bhojan");
			foodKeyword.add("aahar");
			foodKeyword.add("pressure");
			foodKeyword.add("cooker");
			foodKeyword.add("meal");
			foodKeyword.add("ration");
			foodKeyword.add("rasoi");
			foodKeyword.add("stove");
			foodKeyword.add("gas");
			foodKeyword.add("fruit");
			foodKeyword.add("vegetable");
			foodKeyword.add("aahaar");
			foodKeyword.add("Flour");
			foodKeyword.add("sugar");
			foodKeyword.add("lpg");
			foodKeyword.add("milk");
			foodKeyword.add("chapati");
			foodKeyword.add("ann");
			foodKeyword.add("annpurna");
			foodKeyword.add("akshaypatra");
			foodKeyword.add("ghee");
			foodKeyword.add("ujjawala");

			ArrayList<String> gadjetsKeyword = new ArrayList<String>();
			gadjetsKeyword.add("gadjets");
			gadjetsKeyword.add("smartphone");
			gadjetsKeyword.add("phone");
			gadjetsKeyword.add("mobile");
			gadjetsKeyword.add("pressure");
			gadjetsKeyword.add("cooker");
			gadjetsKeyword.add("laptop");
			gadjetsKeyword.add("bicycle");
			gadjetsKeyword.add("cycle");
			gadjetsKeyword.add("tube");
			gadjetsKeyword.add("light");
			gadjetsKeyword.add("bulb");
			gadjetsKeyword.add("wheeler");
			gadjetsKeyword.add("gas");
			gadjetsKeyword.add("stove");
			gadjetsKeyword.add("suchna");
			gadjetsKeyword.add("lanterns");
			gadjetsKeyword.add("ujjawala");
			gadjetsKeyword.add("sarees");
			gadjetsKeyword.add("shirts");
			gadjetsKeyword.add("lpg");
			gadjetsKeyword.add("wi-Fi");
			gadjetsKeyword.add("scooty");
			gadjetsKeyword.add("ujjawala");
			
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){


				String searchKeyword = searchStringFilter;
				String filterState = request.getParameter("filterByState");
				String searchString = searchKeyword;
				if(searchKeyword == null){
					return "404";
				}
				if(searchKeyword.equals("")){
					model.addAttribute("searchString", searchString);
					model.addAttribute("searchResult" ,stateGovernmentSchemesService.findAll());
					model.addAttribute("mainSearchResult", stateGovernmentSchemesService.findAll());
				}else{
					searchKeyword = searchKeyword.toLowerCase();
					String[] searchKeywordArray = searchKeyword.split(" ");
					HashSet<StatementGovernmentSchemes> setOfGovernmentSchemesOfSearch = new HashSet<StatementGovernmentSchemes>();
					List<StatementGovernmentSchemes> listOfGovernmentSchemesOfMainSearch = new ArrayList<StatementGovernmentSchemes>();
					for(String  searchKey : searchKeywordArray){
						if(searchKey.length() >= 3 && !searchKey.equalsIgnoreCase("the") 
						   && !searchKey.equalsIgnoreCase("and") && !searchKey.equalsIgnoreCase("but") && !searchKey.equalsIgnoreCase("for")
						   && !searchKey.equalsIgnoreCase("scheme") && !searchKey.equalsIgnoreCase("schemes")
						   && !searchKey.equalsIgnoreCase("yojana") && !searchKey.equalsIgnoreCase("yojanas")){
							
							for(int i = 1; i <= 2; i++){
								boolean isWithoutSWork = false;
								if(i == 1){
									if(healthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String healthKey : healthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(healthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenHealthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenHealthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pregnantWomenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pregnantWomenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(skillKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : skillKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(educationKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : educationKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(studentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : studentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(scholarshipKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : scholarshipKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(sportsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : sportsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(agricultureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										
										for(String womenHealthKey : agricultureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pensionKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pensionKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(roadHighwayKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : roadHighwayKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(urbanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : urbanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(startupKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : startupKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(employmentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : employmentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(financeInsuaranceKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : financeInsuaranceKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(houseInfrastructureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : houseInfrastructureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(loanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : loanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(ruralKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : ruralKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(teacherKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : teacherKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(digitalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : digitalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(primeministerKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : primeministerKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(electricityKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : electricityKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(tourKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : tourKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(animalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : animalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(waterKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : waterKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(familyKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : familyKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(foodKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : foodKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(gadjetsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : gadjetsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else{
										
										//gov search
										
									}
								}else{
									if(isWithoutSWork == true){
										break;
									}
									searchKey = searchKey + "s";
									if(healthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String healthKey : healthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(healthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenHealthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenHealthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pregnantWomenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pregnantWomenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(skillKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : skillKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(educationKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : educationKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(studentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : studentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(scholarshipKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : scholarshipKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(sportsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : sportsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(agricultureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										
										for(String womenHealthKey : agricultureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pensionKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pensionKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(roadHighwayKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : roadHighwayKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(urbanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : urbanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(startupKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : startupKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(employmentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : employmentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(financeInsuaranceKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : financeInsuaranceKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(houseInfrastructureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : houseInfrastructureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(loanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : loanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(ruralKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : ruralKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(teacherKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : teacherKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(digitalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : digitalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(primeministerKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : primeministerKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(electricityKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : electricityKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(tourKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : tourKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(animalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : animalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(waterKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : waterKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(familyKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : familyKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(foodKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : foodKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(gadjetsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : gadjetsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else{
										
										//gov search
										
									}
								}
							}

						}
					}
					
					ArrayList<StatementGovernmentSchemes> searchResult = new ArrayList<StatementGovernmentSchemes>();
					
					for(StatementGovernmentSchemes statementGovernmentScheme : setOfGovernmentSchemesOfSearch){
						if(statementGovernmentScheme.getTypeOfScheme().equalsIgnoreCase(filterState)){
							searchResult.add(statementGovernmentScheme);
						}
						
					}
					ArrayList<StatementGovernmentSchemes> listOfGovernmentSchemesOfMainSearchFromFilter = new ArrayList<StatementGovernmentSchemes>();
					for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfMainSearch){
						if(statementGovernmentScheme.getTypeOfScheme().equalsIgnoreCase(filterState)){
							listOfGovernmentSchemesOfMainSearchFromFilter.add(statementGovernmentScheme);
						}
					}
					model.addAttribute("searchString", searchString);
					model.addAttribute("searchResult" ,searchResult);
					model.addAttribute("mainSearchResult", listOfGovernmentSchemesOfMainSearchFromFilter);
				}

				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");

				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");





				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);

				return "search"; 
			}else{
				
				String searchKeyword = searchStringFilter;
				String filterState = request.getParameter("filterByState");
				String searchString = searchKeyword;
				if(searchKeyword == null){
					return "404";
				}
				if(searchKeyword.equals("")){
					model.addAttribute("searchString", searchString);
					model.addAttribute("searchResult" ,stateGovernmentSchemesService.findAll());
					model.addAttribute("mainSearchResult", stateGovernmentSchemesService.findAll());
				}else{
					searchKeyword = searchKeyword.toLowerCase();
					String[] searchKeywordArray = searchKeyword.split(" ");
					HashSet<StatementGovernmentSchemes> setOfGovernmentSchemesOfSearch = new HashSet<StatementGovernmentSchemes>();
					List<StatementGovernmentSchemes> listOfGovernmentSchemesOfMainSearch = new ArrayList<StatementGovernmentSchemes>();
					for(String  searchKey : searchKeywordArray){
						if(searchKey.length() >= 3 && !searchKey.equalsIgnoreCase("the") && !searchKey.equalsIgnoreCase("and") && !searchKey.equalsIgnoreCase("but") && !searchKey.equalsIgnoreCase("for")
						   && !searchKey.equalsIgnoreCase("scheme") && !searchKey.equalsIgnoreCase("schemes")
						   && !searchKey.equalsIgnoreCase("yojana") && !searchKey.equalsIgnoreCase("yojanas")){
							
							for(int i = 1; i <= 2; i++){
								boolean isWithoutSWork = false;
								if(i == 1){
									if(healthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String healthKey : healthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(healthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenHealthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenHealthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pregnantWomenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pregnantWomenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(skillKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : skillKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(educationKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : educationKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(studentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : studentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(scholarshipKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : scholarshipKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(sportsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : sportsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(agricultureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										
										for(String womenHealthKey : agricultureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pensionKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pensionKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(roadHighwayKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : roadHighwayKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(urbanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : urbanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(startupKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : startupKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(employmentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : employmentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(financeInsuaranceKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : financeInsuaranceKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(houseInfrastructureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : houseInfrastructureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(loanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : loanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(ruralKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : ruralKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(teacherKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : teacherKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(digitalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : digitalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(primeministerKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : primeministerKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(electricityKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : electricityKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(tourKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : tourKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(animalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : animalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(waterKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : waterKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(familyKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : familyKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(foodKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : foodKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(gadjetsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : gadjetsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else{
										
										//gov search
										
									}
								}else{
									if(isWithoutSWork == true){
										break;
									}
									searchKey = searchKey + "s";
									if(healthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String healthKey : healthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(healthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenHealthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenHealthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pregnantWomenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pregnantWomenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(skillKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : skillKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(educationKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : educationKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(studentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : studentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(scholarshipKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : scholarshipKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(sportsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : sportsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(agricultureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										
										for(String womenHealthKey : agricultureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pensionKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pensionKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(roadHighwayKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : roadHighwayKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(urbanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : urbanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(startupKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : startupKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(employmentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : employmentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(financeInsuaranceKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : financeInsuaranceKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(houseInfrastructureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : houseInfrastructureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(loanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : loanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(ruralKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : ruralKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(teacherKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : teacherKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(digitalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : digitalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(primeministerKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : primeministerKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(electricityKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : electricityKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(tourKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : tourKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(animalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : animalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(waterKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : waterKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(familyKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : familyKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(foodKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : foodKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(gadjetsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : gadjetsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else{
										
										//gov search
										
									}
								}
							}

						}
					}
					
					ArrayList<StatementGovernmentSchemes> searchResult = new ArrayList<StatementGovernmentSchemes>();
					
					for(StatementGovernmentSchemes statementGovernmentScheme : setOfGovernmentSchemesOfSearch){
						if(statementGovernmentScheme.getTypeOfScheme().equalsIgnoreCase(filterState)){
							searchResult.add(statementGovernmentScheme);
						}
						
					}
					ArrayList<StatementGovernmentSchemes> listOfGovernmentSchemesOfMainSearchFromFilter = new ArrayList<StatementGovernmentSchemes>();
					for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfMainSearch){
						if(statementGovernmentScheme.getTypeOfScheme().equalsIgnoreCase(filterState)){
							listOfGovernmentSchemesOfMainSearchFromFilter.add(statementGovernmentScheme);
						}
					}
					model.addAttribute("searchString", searchString);
					model.addAttribute("searchResult" ,searchResult);
					model.addAttribute("mainSearchResult", listOfGovernmentSchemesOfMainSearchFromFilter);
				}

				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);

				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");

				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");

				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);

				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();				
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "search";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	//search/autoComplete
	@RequestMapping(value = "/search/autoComplete", method = RequestMethod.POST)
	@ResponseBody
	public String searchSchemeAutoComplete(Model model, HttpServletRequest request) {
		try{
			String keyword = request.getParameter("keyword");
			if(keyword == null || keyword.equals("")){
				return "";
			}else{
				List<Keyword> listOfKeywords = keywordService.findAllByKeyword(keyword);
				StringBuffer sb = new StringBuffer("");
				for(Keyword keyword2 : listOfKeywords){
					sb.append(keyword2.getKeyword() + "<br>");
				}
				System.out.println(sb.toString());
				return sb.toString();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchScheme(Model model, HttpServletRequest request) {
		try{

			ArrayList<String> healthKeywords = new ArrayList<String>();
			healthKeywords.add("health");
			healthKeywords.add("ambulance");
			healthKeywords.add("diseases");
			healthKeywords.add("healthiness");
			healthKeywords.add("fitness");
			healthKeywords.add("pregnant");
			healthKeywords.add("pregnancy");
			healthKeywords.add("cancer");
			healthKeywords.add("medical");
			healthKeywords.add("treatment");
			healthKeywords.add("Swasthya");
			healthKeywords.add("hospital");
			healthKeywords.add("Suraksha");
			healthKeywords.add("hriday");
			healthKeywords.add("medicine");
			healthKeywords.add("accident");
			healthKeywords.add("vaccination");
			healthKeywords.add("tablet");
			healthKeywords.add("vaccine");
			healthKeywords.add("indradhanush");
			healthKeywords.add("arogya");
			healthKeywords.add("check-up");
			healthKeywords.add("mri");
			healthKeywords.add("clinic");
			healthKeywords.add("blind");
			healthKeywords.add("chikitsa");
			healthKeywords.add("children");
			healthKeywords.add("child");
			healthKeywords.add("heart");
			healthKeywords.add("sishu");
			healthKeywords.add("malaria");
			healthKeywords.add("eye");
			healthKeywords.add("care");
			healthKeywords.add("hiv");
			healthKeywords.add("acid");
			healthKeywords.add("victims");
			healthKeywords.add("patients");
			healthKeywords.add("liver");
			healthKeywords.add("bukhar");


			ArrayList<String> womenHealthKeywords = new ArrayList<String>();
			womenHealthKeywords.add("health");
			womenHealthKeywords.add("ambulance");
			womenHealthKeywords.add("diseases");
			womenHealthKeywords.add("healthiness");
			womenHealthKeywords.add("fitness");
			womenHealthKeywords.add("pregnant");
			womenHealthKeywords.add("pregnancy");
			womenHealthKeywords.add("cancer");
			womenHealthKeywords.add("medical");
			womenHealthKeywords.add("treatment");
			womenHealthKeywords.add("Swasthya");
			womenHealthKeywords.add("hospital");
			womenHealthKeywords.add("suraksha");
			womenHealthKeywords.add("hriday");
			womenHealthKeywords.add("medicine");
			womenHealthKeywords.add("accident");
			womenHealthKeywords.add("vaccination");
			womenHealthKeywords.add("tablet");
			womenHealthKeywords.add("vaccine");
			womenHealthKeywords.add("indradhanush");
			womenHealthKeywords.add("matri");
			womenHealthKeywords.add("matra");
			womenHealthKeywords.add("mahila");
			womenHealthKeywords.add("sakti");

			ArrayList<String> pregnantWomenKeywords = new ArrayList<String>();
			pregnantWomenKeywords.add("pregnant");
			pregnantWomenKeywords.add("pregnancy");
			pregnantWomenKeywords.add("ambulance");
			pregnantWomenKeywords.add("kcr");
			pregnantWomenKeywords.add("matri");
			pregnantWomenKeywords.add("matra");
			pregnantWomenKeywords.add("matrutva");
			pregnantWomenKeywords.add("maternity");
			pregnantWomenKeywords.add("child");
			pregnantWomenKeywords.add("health");
			pregnantWomenKeywords.add("medical");
			pregnantWomenKeywords.add("treatment");

			ArrayList<String> womenKeywords = new ArrayList<String>();
			womenKeywords.add("women");
			womenKeywords.add("lady");
			womenKeywords.add("girl");
			womenKeywords.add("female");
			womenKeywords.add("kanya");
			womenKeywords.add("widow");
			womenKeywords.add("aurat");
			womenKeywords.add("balika");
			womenKeywords.add("matron");
			womenKeywords.add("beti");
			womenKeywords.add("sanitary");
			womenKeywords.add("napkins");
			womenKeywords.add("pad");
			womenKeywords.add("mukhbir");
			womenKeywords.add("vaishnavi");
			womenKeywords.add("wife");
			womenKeywords.add("sister");
			womenKeywords.add("lakshmi");
			womenKeywords.add("laxmi");
			womenKeywords.add("bhagya");
			womenKeywords.add("matra");
			womenKeywords.add("matri");
			womenKeywords.add("pressure");
			womenKeywords.add("cooker");
			womenKeywords.add("sakti");
			womenKeywords.add("ujjwala");

			ArrayList<String> skillKeyword = new ArrayList<String>();
			skillKeyword.add("skill");
			skillKeyword.add("kalakar");
			skillKeyword.add("artists");
			skillKeyword.add("talent");
			skillKeyword.add("fitness");
			skillKeyword.add("strength");
			skillKeyword.add("maker");

			ArrayList<String> educationKeyword = new ArrayList<String>();
			educationKeyword.add("education");
			educationKeyword.add("schooling");
			educationKeyword.add("tuition");
			educationKeyword.add("coaching");
			educationKeyword.add("training");
			educationKeyword.add("knowledge");
			educationKeyword.add("learning");
			educationKeyword.add("shiksha");
			educationKeyword.add("swayam");
			educationKeyword.add("courses");
			educationKeyword.add("vidyarthi");
			educationKeyword.add("utkarsh");
			educationKeyword.add("vaigyanik");
			educationKeyword.add("kishore");
			educationKeyword.add("vidya");
			educationKeyword.add("library");
			educationKeyword.add("siksha");
			educationKeyword.add("medhavi");
			educationKeyword.add("gyan");
			educationKeyword.add("pariksha");
			educationKeyword.add("graduates");
			educationKeyword.add("vidyalaya");
			educationKeyword.add("shikshit");
			educationKeyword.add("research");
			educationKeyword.add("computer");
			educationKeyword.add("laptop");
			educationKeyword.add("scholarship");
			educationKeyword.add("matric");
			educationKeyword.add("padna");

			ArrayList<String> studentKeyword = new ArrayList<String>();
			studentKeyword.add("students");
			studentKeyword.add("schools");
			studentKeyword.add("undergraduate");
			studentKeyword.add("postgraduate");
			studentKeyword.add("schoolchild");
			studentKeyword.add("schoolboy");
			studentKeyword.add("schoolgirl");
			studentKeyword.add("books");
			studentKeyword.add("9th class");
			studentKeyword.add("10th class");
			studentKeyword.add("11th class");
			studentKeyword.add("12th class");
			studentKeyword.add("laptop");
			studentKeyword.add("swayam");
			studentKeyword.add("courses");
			studentKeyword.add("vidyarthi");
			studentKeyword.add("utkarsh");
			studentKeyword.add("vaigyanik");
			studentKeyword.add("kishore");
			studentKeyword.add("meritorious");
			studentKeyword.add("vidya");
			studentKeyword.add("library");
			studentKeyword.add("siksha");
			studentKeyword.add("matric");
			studentKeyword.add("medhavi");
			studentKeyword.add("gyan");
			studentKeyword.add("shiksha");
			studentKeyword.add("pariksha");
			studentKeyword.add("graduates");
			studentKeyword.add("vidyalaya");
			studentKeyword.add("shikshit");
			studentKeyword.add("research");
			studentKeyword.add("computer");
			studentKeyword.add("scholarship");
			studentKeyword.add("higher");
			studentKeyword.add("sr");
			studentKeyword.add("junior");
			studentKeyword.add("jr");
			studentKeyword.add("matric");


			ArrayList<String> scholarshipKeyword = new ArrayList<String>();
			scholarshipKeyword.add("scholarships");
			scholarshipKeyword.add("fellowship");
			scholarshipKeyword.add("laptop");
			scholarshipKeyword.add("meritorious");
			scholarshipKeyword.add("matric");
			scholarshipKeyword.add("9th class");
			scholarshipKeyword.add("10th class");
			scholarshipKeyword.add("11th class");
			scholarshipKeyword.add("12th class");
			scholarshipKeyword.add("learning");
			scholarshipKeyword.add("book");
			scholarshipKeyword.add("learning");
			scholarshipKeyword.add("knowledge");
			scholarshipKeyword.add("education");
			scholarshipKeyword.add("scholarships");

			ArrayList<String> sportsKeyword = new ArrayList<String>();
			sportsKeyword.add("sports");
			sportsKeyword.add("khel");
			sportsKeyword.add("game");
			sportsKeyword.add("yoga");
			sportsKeyword.add("play");
			sportsKeyword.add("fitness");

			ArrayList<String> agricultureKeyword = new ArrayList<String>();
			agricultureKeyword.add("agriculture");
			agricultureKeyword.add("krishi");
			agricultureKeyword.add("farmer");
			agricultureKeyword.add("farmers");
			agricultureKeyword.add("kisan");
			agricultureKeyword.add("fasal");
			agricultureKeyword.add("farming");
			agricultureKeyword.add("cultivation");
			agricultureKeyword.add("husbandry");
			agricultureKeyword.add("soil");
			agricultureKeyword.add("land");
			agricultureKeyword.add("farm");
			agricultureKeyword.add("agri");
			agricultureKeyword.add("rythu");
			agricultureKeyword.add("saur");
			agricultureKeyword.add("sujala");
			agricultureKeyword.add("bhavantar");
			agricultureKeyword.add("bharpai");
			agricultureKeyword.add("bhojn");

			ArrayList<String> pensionKeyword = new ArrayList<String>();
			pensionKeyword.add("pension");
			pensionKeyword.add("retirement");
			pensionKeyword.add("old");
			pensionKeyword.add("welfare");


			ArrayList<String> roadHighwayKeyword = new ArrayList<String>();
			roadHighwayKeyword.add("road");
			roadHighwayKeyword.add("highway");
			roadHighwayKeyword.add("route");

			ArrayList<String> urbanKeyword = new ArrayList<String>();
			urbanKeyword.add("urban");
			urbanKeyword.add("city");
			urbanKeyword.add("town");
			urbanKeyword.add("metropolitan");

			ArrayList<String> startupKeyword = new ArrayList<String>();
			startupKeyword.add("startup");
			startupKeyword.add("start");
			startupKeyword.add("entrepreneurship");
			startupKeyword.add("entrepreneur");
			startupKeyword.add("creation");
			startupKeyword.add("business");
			startupKeyword.add("vyapar");
			startupKeyword.add("naveen");
			startupKeyword.add("udhyog");

			ArrayList<String> employmentKeyword = new ArrayList<String>();
			employmentKeyword.add("employment");
			employmentKeyword.add("unemployment");
			employmentKeyword.add("rozgar");
			employmentKeyword.add("rozgaar");
			employmentKeyword.add("rojgaar");
			employmentKeyword.add("berojgari");
			employmentKeyword.add("vacancy");
			employmentKeyword.add("vacancies");
			employmentKeyword.add("job");
			employmentKeyword.add("jobs");
			employmentKeyword.add("employ");
			employmentKeyword.add("swarozgar");
			employmentKeyword.add("yaari");
			employmentKeyword.add("naukri");
			employmentKeyword.add("recruitment");
			employmentKeyword.add("kaushal");
			employmentKeyword.add("post");
			employmentKeyword.add("profession");
			employmentKeyword.add("work");
			employmentKeyword.add("hiring");
			employmentKeyword.add("hire");

			ArrayList<String> financeInsuaranceKeyword = new ArrayList<String>();
			financeInsuaranceKeyword.add("finance");
			financeInsuaranceKeyword.add("insuarance");
			financeInsuaranceKeyword.add("money");
			financeInsuaranceKeyword.add("financial");
			financeInsuaranceKeyword.add("economics");
			financeInsuaranceKeyword.add("business");
			financeInsuaranceKeyword.add("investment");
			financeInsuaranceKeyword.add("subsidy");
			financeInsuaranceKeyword.add("budget");
			financeInsuaranceKeyword.add("warranty");
			financeInsuaranceKeyword.add("guarantee");
			financeInsuaranceKeyword.add("bima");
			financeInsuaranceKeyword.add("dhan");
			financeInsuaranceKeyword.add("gst");
			financeInsuaranceKeyword.add("vyapar");
			financeInsuaranceKeyword.add("wallet");
			financeInsuaranceKeyword.add("payment");
			financeInsuaranceKeyword.add("paisa");
			financeInsuaranceKeyword.add("khattar");
			financeInsuaranceKeyword.add("sand");

			ArrayList<String> houseInfrastructureKeyword = new ArrayList<String>();
			houseInfrastructureKeyword.add("house");
			houseInfrastructureKeyword.add("housing");
			houseInfrastructureKeyword.add("rera");
			houseInfrastructureKeyword.add("land");
			houseInfrastructureKeyword.add("infrastructure");
			houseInfrastructureKeyword.add("awas");
			houseInfrastructureKeyword.add("home");
			houseInfrastructureKeyword.add("aasra");
			houseInfrastructureKeyword.add("resident");
			houseInfrastructureKeyword.add("residence");
			houseInfrastructureKeyword.add("household");
			houseInfrastructureKeyword.add("tribe");
			houseInfrastructureKeyword.add("flats");
			houseInfrastructureKeyword.add("plots");
			houseInfrastructureKeyword.add("ghar");
			houseInfrastructureKeyword.add("mnrega");
			houseInfrastructureKeyword.add("toilets");
			houseInfrastructureKeyword.add("doorstep");
			houseInfrastructureKeyword.add("bhoomi");
			houseInfrastructureKeyword.add("bazaar");
			houseInfrastructureKeyword.add("swachh");
			houseInfrastructureKeyword.add("sharing");
			houseInfrastructureKeyword.add("twallet");

			ArrayList<String> loanKeyword = new ArrayList<String>();
			loanKeyword.add("loan");
			loanKeyword.add("due");
			loanKeyword.add("karz");
			loanKeyword.add("rin");
			loanKeyword.add("credit");
			loanKeyword.add("lend");
			loanKeyword.add("moneylending");
			loanKeyword.add("paisa");
			loanKeyword.add("rupiya");

			ArrayList<String> ruralKeyword = new ArrayList<String>();
			ruralKeyword.add("rural");
			ruralKeyword.add("village");
			ruralKeyword.add("gram");
			ruralKeyword.add("gramin");
			ruralKeyword.add("johaar");
			ruralKeyword.add("poverty");
			ruralKeyword.add("bpl");
			ruralKeyword.add("agriculture");
			ruralKeyword.add("kisan");
			ruralKeyword.add("krishi");
			ruralKeyword.add("farming");
			ruralKeyword.add("gav");
			ruralKeyword.add("vill");

			ArrayList<String> teacherKeyword = new ArrayList<String>();
			teacherKeyword.add("teacher");
			teacherKeyword.add("teaching");
			teacherKeyword.add("educator");
			teacherKeyword.add("tutor");
			teacherKeyword.add("instructor");
			teacherKeyword.add("schoolteacher");
			teacherKeyword.add("lecturer");
			teacherKeyword.add("professor");
			teacherKeyword.add("mentor");
			teacherKeyword.add("guru");
			teacherKeyword.add("gyanodaya");
			teacherKeyword.add("sarswati");

			ArrayList<String> digitalKeyword = new ArrayList<String>();
			digitalKeyword.add("digital");
			digitalKeyword.add("aadhunik");
			digitalKeyword.add("unnat");
			digitalKeyword.add("bhavisya");

			ArrayList<String> primeministerKeyword = new ArrayList<String>();
			primeministerKeyword.add("prime");
			primeministerKeyword.add("pradhan");
			primeministerKeyword.add("mantri");
			primeministerKeyword.add("modi");
			primeministerKeyword.add("narendra");
			primeministerKeyword.add("governent");


			ArrayList<String> electricityKeyword = new ArrayList<String>();
			electricityKeyword.add("electricity");
			electricityKeyword.add("koyla");
			electricityKeyword.add("electric");
			electricityKeyword.add("current");
			electricityKeyword.add("power");
			electricityKeyword.add("energy");
			electricityKeyword.add("ujjwala");
			electricityKeyword.add("urja");
			electricityKeyword.add("ujala");
			electricityKeyword.add("bulbs");
			electricityKeyword.add("tubelight");
			electricityKeyword.add("vidyut");
			electricityKeyword.add("uday");


			ArrayList<String> tourKeyword = new ArrayList<String>();
			tourKeyword.add("tour");
			tourKeyword.add("travels");
			tourKeyword.add("temple");
			tourKeyword.add("mandir");
			tourKeyword.add("mela");
			tourKeyword.add("mahotsav");
			tourKeyword.add("gorakhpurmahotsav");
			tourKeyword.add("ticket");
			tourKeyword.add("railway");
			tourKeyword.add("bus");
			tourKeyword.add("haj");
			tourKeyword.add("rail");
			tourKeyword.add("kailash");
			tourKeyword.add("udan");
			tourKeyword.add("air");
			tourKeyword.add("airport");
			tourKeyword.add("yatra");
			tourKeyword.add("bike");
			tourKeyword.add("taxi");
			tourKeyword.add("tirth");
			tourKeyword.add("tourism");

			ArrayList<String> animalKeyword = new ArrayList<String>();
			animalKeyword.add("animal");
			animalKeyword.add("pashu");
			animalKeyword.add("pashudhan");
			animalKeyword.add("cows");
			animalKeyword.add("goats");
			animalKeyword.add("gow");
			animalKeyword.add("cattle");
			animalKeyword.add("sheep");
			animalKeyword.add("janwar");

			ArrayList<String> waterKeyword = new ArrayList<String>();
			waterKeyword.add("river");
			waterKeyword.add("water");
			waterKeyword.add("ganga");
			waterKeyword.add("samrakshana");
			waterKeyword.add("neeti");
			waterKeyword.add("udyamam");
			waterKeyword.add("bhagiratha");
			waterKeyword.add("pani");

			ArrayList<String> familyKeyword = new ArrayList<String>();
			familyKeyword.add("family");
			familyKeyword.add("parivar");
			familyKeyword.add("marriage");
			familyKeyword.add("shaadi");
			familyKeyword.add("pariwar");
			familyKeyword.add("vivah");
			familyKeyword.add("families");
			familyKeyword.add("dada");
			familyKeyword.add("dadi");

			ArrayList<String> foodKeyword = new ArrayList<String>();
			foodKeyword.add("food");
			foodKeyword.add("bhojanalya");
			foodKeyword.add("bhojan");
			foodKeyword.add("aahar");
			foodKeyword.add("pressure");
			foodKeyword.add("cooker");
			foodKeyword.add("meal");
			foodKeyword.add("ration");
			foodKeyword.add("rasoi");
			foodKeyword.add("stove");
			foodKeyword.add("gas");
			foodKeyword.add("fruit");
			foodKeyword.add("vegetable");
			foodKeyword.add("aahaar");
			foodKeyword.add("Flour");
			foodKeyword.add("sugar");
			foodKeyword.add("lpg");
			foodKeyword.add("milk");
			foodKeyword.add("chapati");
			foodKeyword.add("ann");
			foodKeyword.add("annpurna");
			foodKeyword.add("akshaypatra");
			foodKeyword.add("ghee");
			foodKeyword.add("ujjawala");

			ArrayList<String> gadjetsKeyword = new ArrayList<String>();
			gadjetsKeyword.add("gadjets");
			gadjetsKeyword.add("smartphone");
			gadjetsKeyword.add("phone");
			gadjetsKeyword.add("mobile");
			gadjetsKeyword.add("pressure");
			gadjetsKeyword.add("cooker");
			gadjetsKeyword.add("laptop");
			gadjetsKeyword.add("bicycle");
			gadjetsKeyword.add("cycle");
			gadjetsKeyword.add("tube");
			gadjetsKeyword.add("light");
			gadjetsKeyword.add("bulb");
			gadjetsKeyword.add("wheeler");
			gadjetsKeyword.add("gas");
			gadjetsKeyword.add("stove");
			gadjetsKeyword.add("suchna");
			gadjetsKeyword.add("lanterns");
			gadjetsKeyword.add("ujjawala");
			gadjetsKeyword.add("sarees");
			gadjetsKeyword.add("shirts");
			gadjetsKeyword.add("lpg");
			gadjetsKeyword.add("wi-Fi");
			gadjetsKeyword.add("scooty");
			gadjetsKeyword.add("ujjawala");
			
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){


				String searchKeyword = request.getParameter("searchKeyword");
				String searchString = searchKeyword;
				if(searchKeyword == null){
					return "404";
				}
				if(searchKeyword.equals("")){
					model.addAttribute("searchString", searchString);
					model.addAttribute("searchResult" ,stateGovernmentSchemesService.findAll());
					model.addAttribute("mainSearchResult", stateGovernmentSchemesService.findAll());
				}else{
					searchKeyword = searchKeyword.toLowerCase();
					String[] searchKeywordArray = searchKeyword.split(" ");
					HashSet<StatementGovernmentSchemes> setOfGovernmentSchemesOfSearch = new HashSet<StatementGovernmentSchemes>();
					List<StatementGovernmentSchemes> listOfGovernmentSchemesOfMainSearch = new ArrayList<StatementGovernmentSchemes>();
					for(String  searchKey : searchKeywordArray){
						if(searchKey.length() >= 3 && !searchKey.equalsIgnoreCase("the") && !searchKey.equalsIgnoreCase("and") && !searchKey.equalsIgnoreCase("but") && !searchKey.equalsIgnoreCase("for")
						   && !searchKey.equalsIgnoreCase("scheme") && !searchKey.equalsIgnoreCase("schemes")
						   && !searchKey.equalsIgnoreCase("yojana") && !searchKey.equalsIgnoreCase("yojanas")){
							
							for(int i = 1; i <= 2; i++){
								boolean isWithoutSWork = false;
								if(i == 1){
									if(healthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String healthKey : healthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(healthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenHealthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenHealthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pregnantWomenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pregnantWomenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(skillKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : skillKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(educationKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : educationKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(studentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : studentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(scholarshipKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : scholarshipKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(sportsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : sportsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(agricultureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										
										for(String womenHealthKey : agricultureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pensionKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pensionKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(roadHighwayKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : roadHighwayKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(urbanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : urbanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(startupKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : startupKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(employmentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : employmentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(financeInsuaranceKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : financeInsuaranceKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(houseInfrastructureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : houseInfrastructureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(loanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : loanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(ruralKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : ruralKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(teacherKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : teacherKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(digitalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : digitalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(primeministerKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : primeministerKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(electricityKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : electricityKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(tourKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : tourKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(animalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : animalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(waterKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : waterKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(familyKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : familyKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(foodKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : foodKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(gadjetsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : gadjetsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else{
										
										//gov search
										
									}
								}else{
									if(isWithoutSWork == true){
										break;
									}
									searchKey = searchKey + "s";
									if(healthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String healthKey : healthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(healthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenHealthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenHealthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pregnantWomenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pregnantWomenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(skillKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : skillKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(educationKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : educationKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(studentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : studentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(scholarshipKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : scholarshipKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(sportsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : sportsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(agricultureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										
										for(String womenHealthKey : agricultureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pensionKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pensionKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(roadHighwayKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : roadHighwayKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(urbanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : urbanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(startupKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : startupKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(employmentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : employmentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(financeInsuaranceKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : financeInsuaranceKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(houseInfrastructureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : houseInfrastructureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(loanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : loanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(ruralKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : ruralKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(teacherKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : teacherKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(digitalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : digitalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(primeministerKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : primeministerKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(electricityKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : electricityKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(tourKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : tourKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(animalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : animalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(waterKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : waterKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(familyKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : familyKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(foodKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : foodKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(gadjetsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : gadjetsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else{
										
										//gov search
										
									}
								}
							}

						}
					}
					
					ArrayList<StatementGovernmentSchemes> searchResult = new ArrayList<StatementGovernmentSchemes>();
					
					for(StatementGovernmentSchemes statementGovernmentScheme : setOfGovernmentSchemesOfSearch){
						searchResult.add(statementGovernmentScheme);
					}
					model.addAttribute("searchString", searchString);
					model.addAttribute("searchResult" ,searchResult);
					model.addAttribute("mainSearchResult", listOfGovernmentSchemesOfMainSearch);
				}

				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");

				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");





				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);

				return "search"; 
			}else{
				
				String searchKeyword = request.getParameter("searchKeyword");
				String searchString = searchKeyword;
				if(searchKeyword == null){
					return "404";
				}
				if(searchKeyword.equals("")){
					model.addAttribute("searchString", searchString);
					model.addAttribute("searchResult" ,stateGovernmentSchemesService.findAll());
					model.addAttribute("mainSearchResult", stateGovernmentSchemesService.findAll());
				}else{
					searchKeyword = searchKeyword.toLowerCase();
					String[] searchKeywordArray = searchKeyword.split(" ");
					HashSet<StatementGovernmentSchemes> setOfGovernmentSchemesOfSearch = new HashSet<StatementGovernmentSchemes>();
					List<StatementGovernmentSchemes> listOfGovernmentSchemesOfMainSearch = new ArrayList<StatementGovernmentSchemes>();
					for(String  searchKey : searchKeywordArray){
						if(searchKey.length() >= 3 && !searchKey.equalsIgnoreCase("the") && !searchKey.equalsIgnoreCase("and") && !searchKey.equalsIgnoreCase("but") && !searchKey.equalsIgnoreCase("for")
						   && !searchKey.equalsIgnoreCase("scheme") && !searchKey.equalsIgnoreCase("schemes")
						   && !searchKey.equalsIgnoreCase("yojana") && !searchKey.equalsIgnoreCase("yojanas")){
							
							for(int i = 1; i <= 2; i++){
								boolean isWithoutSWork = false;
								if(i == 1){
									if(healthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String healthKey : healthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(healthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenHealthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenHealthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pregnantWomenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pregnantWomenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(skillKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : skillKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(educationKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : educationKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(studentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : studentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(scholarshipKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : scholarshipKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(sportsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : sportsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(agricultureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										
										for(String womenHealthKey : agricultureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pensionKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pensionKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(roadHighwayKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : roadHighwayKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(urbanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : urbanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(startupKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : startupKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(employmentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : employmentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(financeInsuaranceKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : financeInsuaranceKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(houseInfrastructureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : houseInfrastructureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(loanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : loanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(ruralKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : ruralKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(teacherKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : teacherKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(digitalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : digitalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(primeministerKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : primeministerKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(electricityKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : electricityKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(tourKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : tourKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(animalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : animalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(waterKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : waterKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(familyKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : familyKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(foodKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : foodKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(gadjetsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : gadjetsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else{
										
										//gov search
										
									}
								}else{
									if(isWithoutSWork == true){
										break;
									}
									searchKey = searchKey + "s";
									if(healthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String healthKey : healthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(healthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenHealthKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenHealthKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pregnantWomenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pregnantWomenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(womenKeywords.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : womenKeywords){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(skillKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : skillKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(educationKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : educationKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(studentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : studentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(scholarshipKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : scholarshipKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(sportsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : sportsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(agricultureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										
										for(String womenHealthKey : agricultureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(pensionKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : pensionKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(roadHighwayKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : roadHighwayKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(urbanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : urbanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(startupKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : startupKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(employmentKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : employmentKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(financeInsuaranceKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : financeInsuaranceKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(houseInfrastructureKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : houseInfrastructureKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(loanKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : loanKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(ruralKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : ruralKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(teacherKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : teacherKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(digitalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : digitalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(primeministerKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : primeministerKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(electricityKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : electricityKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(tourKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : tourKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(animalKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : animalKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(waterKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : waterKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(familyKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : familyKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(foodKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : foodKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else if(gadjetsKeyword.contains(searchKey)){
										List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch = stateGovernmentSchemesService.findAllBySearchKeyword(searchKey);
										listOfGovernmentSchemesOfMainSearch.addAll(listOfGovernmentSchemesOfSearch);
										for(String womenHealthKey : gadjetsKeyword){
											List<StatementGovernmentSchemes> listOfGovernmentSchemesOfSearch2 = stateGovernmentSchemesService.findAllBySearchKeyword(womenHealthKey);
											listOfGovernmentSchemesOfSearch.addAll(listOfGovernmentSchemesOfSearch2);
										}
										
										for(StatementGovernmentSchemes statementGovernmentScheme : listOfGovernmentSchemesOfSearch){
											setOfGovernmentSchemesOfSearch.add(statementGovernmentScheme);
										}
										if(setOfGovernmentSchemesOfSearch != null){
											isWithoutSWork = true;
										}
									}else{
										
										//gov search
										
									}
								}
							}

						}
					}
					
					ArrayList<StatementGovernmentSchemes> searchResult = new ArrayList<StatementGovernmentSchemes>();
					
					for(StatementGovernmentSchemes statementGovernmentScheme : setOfGovernmentSchemesOfSearch){
						searchResult.add(statementGovernmentScheme);
					}
					model.addAttribute("searchString", searchString);
					model.addAttribute("searchResult" ,searchResult);
					model.addAttribute("mainSearchResult", listOfGovernmentSchemesOfMainSearch);
				}

				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);

				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");

				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");

				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);

				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();				
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "search";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}

	}


	@RequestMapping(value = "/Central-Government/{schemeCategory}", method = RequestMethod.GET)
	public String centralGovt(Model model, HttpServletRequest request, @PathVariable("schemeCategory") String schemeCategory) {
		try{

			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){

				List<CentralGovernmentSchemes> listOfCentralGovernmentSchemes = centralGovernmentSchemesService.findAll();
				List<String> listOfCentralGovernmentSchemesCategory = new ArrayList<String>();
				for(CentralGovernmentSchemes centralGovernmentSchemes : listOfCentralGovernmentSchemes){
					//setOfCentralGovernmentSchemesCategory.add();
					if(!listOfCentralGovernmentSchemesCategory.contains(centralGovernmentSchemes.getSchemeCategory())){
						listOfCentralGovernmentSchemesCategory.add(centralGovernmentSchemes.getSchemeCategory());
					}
				}
				/*Iterator iterator = setOfCentralGovernmentSchemesCategory.iterator();
	    		while (iterator.hasNext()) {
	    			listOfCentralGovernmentSchemesCategory.add(iterator.next().toString());  
	    	    }*/
				List<CentralGovernmentSchemes> listOfCentralGovernmentSchemesByCategory = centralGovernmentSchemesService.findAllBySchemeCategory(schemeCategory);
				model.addAttribute("mainCategory", schemeCategory);
				model.addAttribute("listOfCentralGovernmentSchemesByCategory", listOfCentralGovernmentSchemesByCategory);
				model.addAttribute("listOfCentralGovernmentSchemesCategory", listOfCentralGovernmentSchemesCategory);

				return "centralgovt";
			}else{
				List<CentralGovernmentSchemes> listOfCentralGovernmentSchemes = centralGovernmentSchemesService.findAll();
				List<String> listOfCentralGovernmentSchemesCategory = new ArrayList<String>();
				for(CentralGovernmentSchemes centralGovernmentSchemes : listOfCentralGovernmentSchemes){
					//setOfCentralGovernmentSchemesCategory.add();
					if(!listOfCentralGovernmentSchemesCategory.contains(centralGovernmentSchemes.getSchemeCategory())){
						listOfCentralGovernmentSchemesCategory.add(centralGovernmentSchemes.getSchemeCategory());
					}
				}
				/*Iterator iterator = setOfCentralGovernmentSchemesCategory.iterator();
	    		while (iterator.hasNext()) {
	    			listOfCentralGovernmentSchemesCategory.add(iterator.next().toString());  
	    	    }*/
				List<CentralGovernmentSchemes> listOfCentralGovernmentSchemesByCategory = centralGovernmentSchemesService.findAllBySchemeCategory(schemeCategory);
				model.addAttribute("mainCategory", schemeCategory);
				model.addAttribute("listOfCentralGovernmentSchemesByCategory", listOfCentralGovernmentSchemesByCategory);
				model.addAttribute("listOfCentralGovernmentSchemesCategory", listOfCentralGovernmentSchemesCategory);

				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);
				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "centralgovt";
				}

			}


		}catch(Exception e){
			e.printStackTrace();
			return "index";
		}

	}
	
	@RequestMapping(value = "/Central-Government/{schemeCategory}/{schemeName}", method = RequestMethod.GET)
	public String centralGovtSchemeFullInfo(Model model, HttpServletRequest request, @PathVariable("schemeCategory") String schemeCategory, @PathVariable("schemeName") String schemeName) {
		try{

			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				List<CentralGovernmentSchemes> listOfCentralGovernmentSchemes = centralGovernmentSchemesService.findAll();
				List<String> listOfCentralGovernmentSchemesCategory = new ArrayList<String>();
				for(CentralGovernmentSchemes centralGovernmentSchemes : listOfCentralGovernmentSchemes){
					//setOfCentralGovernmentSchemesCategory.add();
					if(!listOfCentralGovernmentSchemesCategory.contains(centralGovernmentSchemes.getSchemeCategory())){
						listOfCentralGovernmentSchemesCategory.add(centralGovernmentSchemes.getSchemeCategory());
					}
				}
				/*Iterator iterator = setOfCentralGovernmentSchemesCategory.iterator();
	    		while (iterator.hasNext()) {
	    			listOfCentralGovernmentSchemesCategory.add(iterator.next().toString());  
	    	    }*/

				System.out.println(schemeCategory + " " + schemeName);

				CentralGovernmentSchemes centralGovernmentSchemeFullDetail = centralGovernmentSchemesService.findAllBySchemeCategoryAndSchemeName(schemeCategory, schemeName);
				System.out.println(centralGovernmentSchemeFullDetail.getDetailedInfo());
				List<CentralGovernmentSchemes> listOfCentralGovernmentSchemesByCategory = centralGovernmentSchemesService.findAllBySchemeCategory(schemeCategory);
				model.addAttribute("mainCategory", schemeCategory);
				model.addAttribute("centralGovernmentSchemeFullDetail", centralGovernmentSchemeFullDetail);
				model.addAttribute("listOfCentralGovernmentSchemesByCategory", listOfCentralGovernmentSchemesByCategory);
				model.addAttribute("listOfCentralGovernmentSchemesCategory", listOfCentralGovernmentSchemesCategory);
				return "centralGovtSchemeFullDetail";
			}else{
				List<CentralGovernmentSchemes> listOfCentralGovernmentSchemes = centralGovernmentSchemesService.findAll();
				List<String> listOfCentralGovernmentSchemesCategory = new ArrayList<String>();
				for(CentralGovernmentSchemes centralGovernmentSchemes : listOfCentralGovernmentSchemes){
					//setOfCentralGovernmentSchemesCategory.add();
					if(!listOfCentralGovernmentSchemesCategory.contains(centralGovernmentSchemes.getSchemeCategory())){
						listOfCentralGovernmentSchemesCategory.add(centralGovernmentSchemes.getSchemeCategory());
					}
				}
				/*Iterator iterator = setOfCentralGovernmentSchemesCategory.iterator();
	    		while (iterator.hasNext()) {
	    			listOfCentralGovernmentSchemesCategory.add(iterator.next().toString());  
	    	    }*/

				System.out.println(schemeCategory + " " + schemeName);

				CentralGovernmentSchemes centralGovernmentSchemeFullDetail = centralGovernmentSchemesService.findAllBySchemeCategoryAndSchemeName(schemeCategory, schemeName);
				System.out.println(centralGovernmentSchemeFullDetail.getDetailedInfo());
				List<CentralGovernmentSchemes> listOfCentralGovernmentSchemesByCategory = centralGovernmentSchemesService.findAllBySchemeCategory(schemeCategory);
				model.addAttribute("mainCategory", schemeCategory);
				model.addAttribute("centralGovernmentSchemeFullDetail", centralGovernmentSchemeFullDetail);
				model.addAttribute("listOfCentralGovernmentSchemesByCategory", listOfCentralGovernmentSchemesByCategory);
				model.addAttribute("listOfCentralGovernmentSchemesCategory", listOfCentralGovernmentSchemesCategory);


				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);
				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "centralGovtSchemeFullDetail";
				}
			}


		}catch(Exception e){
			e.printStackTrace();
			return "index";
		}

	}
	
	
	@RequestMapping(value = "/central-government-schemes", method = RequestMethod.GET)
	public String centralGvt(Model model, HttpServletRequest request) {
		String stateName = "central-government-schemes";
		try{

			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				if(stateName.equalsIgnoreCase("chhattisgarh-govt-schemes")){
					//chatt
					stateName = "chattisgarh-govt-schemes";
				}
				List<StatementGovernmentSchemes> listOfStatementGovernmentSchemes = stateGovernmentSchemesService.findAll();
				List<StatementGovernmentSchemes> listOfStateGovernmentSchemesByState = stateGovernmentSchemesService.findAllByTypeOfScheme(stateName);


				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");


				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");
				/*List<String> listOfCentralGovernmentSchemesCategory = new ArrayList<String>();
	    		for(CentralGovernmentSchemes centralGovernmentSchemes : listOfCentralGovernmentSchemes){
	    			//setOfCentralGovernmentSchemesCategory.add();
	    			if(!listOfCentralGovernmentSchemesCategory.contains(centralGovernmentSchemes.getSchemeCategory())){
	    				listOfCentralGovernmentSchemesCategory.add(centralGovernmentSchemes.getSchemeCategory());
	    			}
	    		}
	    		/*Iterator iterator = setOfCentralGovernmentSchemesCategory.iterator();
	    		List<CentralGovernmentSchemes> listOfCentralGovernmentSchemesByCategory = centralGovernmentSchemesService.findAllBySchemeCategory(schemeCategory);
	    		model.addAttribute("mainCategory", schemeCategory);
	    		model.addAttribute("listOfCentralGovernmentSchemesCategory", listOfCentralGovernmentSchemesCategory);*/
				model.addAttribute("mainState", stateName);
				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);
				model.addAttribute("listOfStateGovernmentSchemesByState", listOfStateGovernmentSchemesByState);
				return "stategvt";
			}else{
				if(stateName.equalsIgnoreCase("chhattisgarh-govt-schemes")){
					//chatt
					stateName = "chattisgarh-govt-schemes";
				}
				List<StatementGovernmentSchemes> listOfStatementGovernmentSchemes = stateGovernmentSchemesService.findAll();
				List<StatementGovernmentSchemes> listOfStateGovernmentSchemesByState = stateGovernmentSchemesService.findAllByTypeOfScheme(stateName);


				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");


				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");
				/*List<String> listOfCentralGovernmentSchemesCategory = new ArrayList<String>();
	    		for(CentralGovernmentSchemes centralGovernmentSchemes : listOfCentralGovernmentSchemes){
	    			//setOfCentralGovernmentSchemesCategory.add();
	    			if(!listOfCentralGovernmentSchemesCategory.contains(centralGovernmentSchemes.getSchemeCategory())){
	    				listOfCentralGovernmentSchemesCategory.add(centralGovernmentSchemes.getSchemeCategory());
	    			}
	    		}
	    		/*Iterator iterator = setOfCentralGovernmentSchemesCategory.iterator();
	    		List<CentralGovernmentSchemes> listOfCentralGovernmentSchemesByCategory = centralGovernmentSchemesService.findAllBySchemeCategory(schemeCategory);
	    		model.addAttribute("mainCategory", schemeCategory);
	    		model.addAttribute("listOfCentralGovernmentSchemesCategory", listOfCentralGovernmentSchemesCategory);*/
				model.addAttribute("mainState", stateName);
				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);
				model.addAttribute("listOfStateGovernmentSchemesByState", listOfStateGovernmentSchemesByState);


				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);
				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "stategvt";
				}

			}


		}catch(Exception e){
			e.printStackTrace();
			return "index";
		}
	}
	@RequestMapping(value = "/State-Government/{stateName}", method = RequestMethod.GET)
	public String stateGovt(Model model, HttpServletRequest request, @PathVariable("stateName") String stateName) {
		try{

			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				if(stateName.equalsIgnoreCase("chhattisgarh-govt-schemes")){
					//chatt
					stateName = "chattisgarh-govt-schemes";
				}
				List<StatementGovernmentSchemes> listOfStatementGovernmentSchemes = stateGovernmentSchemesService.findAll();
				List<StatementGovernmentSchemes> listOfStateGovernmentSchemesByState = stateGovernmentSchemesService.findAllByTypeOfScheme(stateName);


				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");


				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");
				/*List<String> listOfCentralGovernmentSchemesCategory = new ArrayList<String>();
	    		for(CentralGovernmentSchemes centralGovernmentSchemes : listOfCentralGovernmentSchemes){
	    			//setOfCentralGovernmentSchemesCategory.add();
	    			if(!listOfCentralGovernmentSchemesCategory.contains(centralGovernmentSchemes.getSchemeCategory())){
	    				listOfCentralGovernmentSchemesCategory.add(centralGovernmentSchemes.getSchemeCategory());
	    			}
	    		}
	    		/*Iterator iterator = setOfCentralGovernmentSchemesCategory.iterator();
	    		List<CentralGovernmentSchemes> listOfCentralGovernmentSchemesByCategory = centralGovernmentSchemesService.findAllBySchemeCategory(schemeCategory);
	    		model.addAttribute("mainCategory", schemeCategory);
	    		model.addAttribute("listOfCentralGovernmentSchemesCategory", listOfCentralGovernmentSchemesCategory);*/
				model.addAttribute("mainState", stateName);
				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);
				model.addAttribute("listOfStateGovernmentSchemesByState", listOfStateGovernmentSchemesByState);
				return "stategvt";
			}else{
				if(stateName.equalsIgnoreCase("chhattisgarh-govt-schemes")){
					//chatt
					stateName = "chattisgarh-govt-schemes";
				}
				List<StatementGovernmentSchemes> listOfStatementGovernmentSchemes = stateGovernmentSchemesService.findAll();
				List<StatementGovernmentSchemes> listOfStateGovernmentSchemesByState = stateGovernmentSchemesService.findAllByTypeOfScheme(stateName);


				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");


				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");
				/*List<String> listOfCentralGovernmentSchemesCategory = new ArrayList<String>();
	    		for(CentralGovernmentSchemes centralGovernmentSchemes : listOfCentralGovernmentSchemes){
	    			//setOfCentralGovernmentSchemesCategory.add();
	    			if(!listOfCentralGovernmentSchemesCategory.contains(centralGovernmentSchemes.getSchemeCategory())){
	    				listOfCentralGovernmentSchemesCategory.add(centralGovernmentSchemes.getSchemeCategory());
	    			}
	    		}
	    		/*Iterator iterator = setOfCentralGovernmentSchemesCategory.iterator();
	    		List<CentralGovernmentSchemes> listOfCentralGovernmentSchemesByCategory = centralGovernmentSchemesService.findAllBySchemeCategory(schemeCategory);
	    		model.addAttribute("mainCategory", schemeCategory);
	    		model.addAttribute("listOfCentralGovernmentSchemesCategory", listOfCentralGovernmentSchemesCategory);*/
				model.addAttribute("mainState", stateName);
				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);
				model.addAttribute("listOfStateGovernmentSchemesByState", listOfStateGovernmentSchemesByState);


				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);
				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "stategvt";
				}

			}


		}catch(Exception e){
			e.printStackTrace();
			return "index";
		}

	}
	
	@RequestMapping(value = "/central-government-schemes/{schemeName}", method = RequestMethod.GET)
	public String centralGvtSchemeFullInfo(Model model, HttpServletRequest request, @PathVariable("schemeName") String schemeName) {
		String stateName = "central-government-schemes";
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				if(stateName.equalsIgnoreCase("chhattisgarh-govt-schemes")){
					//chatt
					stateName = "chattisgarh-govt-schemes";
				}
				List<StatementGovernmentSchemes> statementGovernmentScheme = stateGovernmentSchemesService.findAllBySchemeNameAndTypeOfScheme(schemeName, stateName);
				if(statementGovernmentScheme != null){
					model.addAttribute("statementGovernmentScheme", statementGovernmentScheme.get(0));
					model.addAttribute("statementGovernmentSchemeDetailedInfo", statementGovernmentScheme.get(0).getDetailedInfo());
					System.out.println(statementGovernmentScheme.get(0).getDetailedInfo());
				}

				List<StatementGovernmentSchemes> listOfStateGovernmentSchemesByState = stateGovernmentSchemesService.findAllByTypeOfScheme(stateName);

				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");


				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");

				model.addAttribute("mainState", stateName);
				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);
				model.addAttribute("listOfStateGovernmentSchemesByState", listOfStateGovernmentSchemesByState);
				return "stateGovtSchemeFullDetail";
			}else{
				if(stateName.equalsIgnoreCase("chhattisgarh-govt-schemes")){
					//chatt
					stateName = "chattisgarh-govt-schemes";
				}
				List<StatementGovernmentSchemes> statementGovernmentScheme = stateGovernmentSchemesService.findAllBySchemeNameAndTypeOfScheme(schemeName, stateName);
				if(statementGovernmentScheme != null){
					model.addAttribute("statementGovernmentScheme", statementGovernmentScheme.get(0));
					model.addAttribute("statementGovernmentSchemeDetailedInfo", statementGovernmentScheme.get(0).getDetailedInfo());
					System.out.println(statementGovernmentScheme.get(0).getDetailedInfo());
				}

				List<StatementGovernmentSchemes> listOfStateGovernmentSchemesByState = stateGovernmentSchemesService.findAllByTypeOfScheme(stateName);

				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("central-government-schemes");
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");


				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("central-government-schemes", "Central Government Schemes");
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");

				model.addAttribute("mainState", stateName);
				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);
				model.addAttribute("listOfStateGovernmentSchemesByState", listOfStateGovernmentSchemesByState);



				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);
				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "stateGovtSchemeFullDetail";
				}


			}

		}catch(Exception e){
			e.printStackTrace();
			return "index";
		}
	}
	
	@RequestMapping(value = "/visualisation/categories/{category}/{headline}", method = RequestMethod.GET)
	public String visualisationDetailedPage2(Model model, HttpServletRequest request,  @PathVariable("category") String category, @PathVariable("headline") String headline) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				
				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");


				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");
				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);
				model.addAttribute("headlineDetailedInfo", visualisationService.findAllByHeadline(headline));
				return "visuliFullDetailActual";
			}else{

				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");


				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");
				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);



				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);
				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					model.addAttribute("headlineDetailedInfo", visualisationService.findAllByHeadline(headline));
					return "visuliFullDetailActual";
				}


			}

		}catch(Exception e){
			e.printStackTrace();
			return "index";
		}
	}
	
	@RequestMapping(value = "/State-Government/{stateName}/{schemeName}", method = RequestMethod.GET)
	public String stateGovtSchemeFullInfo(Model model, HttpServletRequest request, @PathVariable("stateName") String stateName, @PathVariable("schemeName") String schemeName) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				if(stateName.equalsIgnoreCase("chhattisgarh-govt-schemes")){
					//chatt
					stateName = "chattisgarh-govt-schemes";
				}
				List<StatementGovernmentSchemes> statementGovernmentScheme = stateGovernmentSchemesService.findAllBySchemeNameAndTypeOfScheme(schemeName, stateName);
				if(statementGovernmentScheme != null){
					model.addAttribute("statementGovernmentScheme", statementGovernmentScheme.get(0));
					model.addAttribute("statementGovernmentSchemeDetailedInfo", statementGovernmentScheme.get(0).getDetailedInfo());
					System.out.println(statementGovernmentScheme.get(0).getDetailedInfo());
				}

				List<StatementGovernmentSchemes> listOfStateGovernmentSchemesByState = stateGovernmentSchemesService.findAllByTypeOfScheme(stateName);

				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");


				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");

				model.addAttribute("mainState", stateName);
				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);
				model.addAttribute("listOfStateGovernmentSchemesByState", listOfStateGovernmentSchemesByState);
				return "stateGovtSchemeFullDetail";
			}else{
				if(stateName.equalsIgnoreCase("chhattisgarh-govt-schemes")){
					//chatt
					stateName = "chattisgarh-govt-schemes";
				}
				List<StatementGovernmentSchemes> statementGovernmentScheme = stateGovernmentSchemesService.findAllBySchemeNameAndTypeOfScheme(schemeName, stateName);
				if(statementGovernmentScheme != null){
					model.addAttribute("statementGovernmentScheme", statementGovernmentScheme.get(0));
					model.addAttribute("statementGovernmentSchemeDetailedInfo", statementGovernmentScheme.get(0).getDetailedInfo());
					System.out.println(statementGovernmentScheme.get(0).getDetailedInfo());
				}

				List<StatementGovernmentSchemes> listOfStateGovernmentSchemesByState = stateGovernmentSchemesService.findAllByTypeOfScheme(stateName);

				ArrayList<String> statesForUrl = new ArrayList<String>();
				statesForUrl.add("arunachal-pradesh-govt-schemes");
				statesForUrl.add("andhra-pradesh-govt-schemes");
				statesForUrl.add("assam-govt-schemes");
				statesForUrl.add("bihar-govt-schemes");
				statesForUrl.add("chattisgarh-govt-schemes");
				statesForUrl.add("delhi-govt-schemes");
				statesForUrl.add("haryana-govt-schemes");
				statesForUrl.add("himachal-pradesh-govt-schemes");
				statesForUrl.add("jharkhand-govt-schemes");
				statesForUrl.add("karnataka-govt-schemes");
				statesForUrl.add("kerala-govt-schemes");
				statesForUrl.add("maharashtra-govt-schemes");
				statesForUrl.add("mizoram-govt-shemes");
				statesForUrl.add("manipur-govt-schemes");
				statesForUrl.add("meghalaya-govt-schemes");
				statesForUrl.add("nagaland-govt-schemes");
				statesForUrl.add("odisha-govt-schemes");
				statesForUrl.add("punjab-govt-schemes");
				statesForUrl.add("rajasthan-govt-schemes");
				statesForUrl.add("sikkim-govt-schemes");
				statesForUrl.add("tamil-nadu-govt-schemes");
				statesForUrl.add("telangana-govt-schemes");
				statesForUrl.add("tripura-govt-schemes");
				statesForUrl.add("uttarakhand-govt-schemes");
				statesForUrl.add("uttar-pradesh-govt-schemes");
				statesForUrl.add("west-bengal-govt-schemes");
				statesForUrl.add("pradhan-mantri-yojana");


				HashMap<String, String> mapOfstatesWithUrl = new HashMap<String, String>();
				mapOfstatesWithUrl.put("arunachal-pradesh-govt-schemes", "Arunachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("andhra-pradesh-govt-schemes", "Andhra Pradesh Government Schemes");
				mapOfstatesWithUrl.put("assam-govt-schemes", "Assam Government Schemes");
				mapOfstatesWithUrl.put("bihar-govt-schemes", "Bihar Government Schemes");
				mapOfstatesWithUrl.put("chattisgarh-govt-schemes", "Chhattisgarh Government Schemes");
				mapOfstatesWithUrl.put("delhi-govt-schemes", "Delhi Government Schemes");
				mapOfstatesWithUrl.put("haryana-govt-schemes", "Haryana Government Schemes");
				mapOfstatesWithUrl.put("himachal-pradesh-govt-schemes", "Himachal Pradesh Government Schemes");
				mapOfstatesWithUrl.put("jharkhand-govt-schemes", "Jharkhand Government Schemes");
				mapOfstatesWithUrl.put("karnataka-govt-schemes", "Karnataka Government Schemes");
				mapOfstatesWithUrl.put("kerala-govt-schemes", "Kerala Government Schemes");
				mapOfstatesWithUrl.put("madhya-pradesh-govt-schemes", "Madhya Pradesh Government Schemes");
				mapOfstatesWithUrl.put("maharashtra-govt-schemes", "Maharastra Government Schemes");
				mapOfstatesWithUrl.put("mizoram-govt-shemes", "Mizoram Government Schemes");
				mapOfstatesWithUrl.put("manipur-govt-schemes", "Manipur Government Schemes");
				mapOfstatesWithUrl.put("meghalaya-govt-schemes", "Meghalaya Government Schemes");
				mapOfstatesWithUrl.put("nagaland-govt-schemes", "Nagaland Government Schemes");
				mapOfstatesWithUrl.put("odisha-govt-schemes", "Odisha Government Schemes");
				mapOfstatesWithUrl.put("punjab-govt-schemes", "Punjab Government Schemes");
				mapOfstatesWithUrl.put("rajasthan-govt-schemes", "Rajasthan Government Schemes");
				mapOfstatesWithUrl.put("sikkim-govt-schemes", "Sikkim Government Schemes");
				mapOfstatesWithUrl.put("tamil-nadu-govt-schemes", "Tamil Nadu Government Schemes");
				mapOfstatesWithUrl.put("telangana-govt-schemes", "Telangana Government Schemes");
				mapOfstatesWithUrl.put("tripura-govt-schemes", "Tripura Government Schemes");
				mapOfstatesWithUrl.put("uttarakhand-govt-schemes", "Uttarakhand Government Schemes");
				mapOfstatesWithUrl.put("uttar-pradesh-govt-schemes", "Uttar Pradesh Government Schemes");
				mapOfstatesWithUrl.put("west-bengal-govt-schemes", "West Bengal Government Schemes");
				mapOfstatesWithUrl.put("pradhan-mantri-yojana", "Pradhan Mantri Yojana Government Schemes");

				model.addAttribute("mainState", stateName);
				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);
				model.addAttribute("listOfStateGovernmentSchemesByState", listOfStateGovernmentSchemesByState);



				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);
				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "stateGovtSchemeFullDetail";
				}


			}

		}catch(Exception e){
			e.printStackTrace();
			return "index";
		}

	}

	@RequestMapping(value = "/Application-Forms", method = RequestMethod.GET)
	public String applicationForms(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				List<ApplicationForms> listOfApplicationForms = applicationFormsService.findAll();
				model.addAttribute("listOfApplicationForms", listOfApplicationForms);
				return "applicationFormsList"; 
			}else{

				List<ApplicationForms> listOfApplicationForms = applicationFormsService.findAll();
				model.addAttribute("listOfApplicationForms", listOfApplicationForms);


				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);
				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "applicationFormsList";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "index";
		}
	}

	@RequestMapping(value = "/Application-Forms/{applicationHeadline}/{updatedDate}", method = RequestMethod.GET)
	public String applicationFormFullInfo(Model model, HttpServletRequest request, @PathVariable("applicationHeadline") String applicationHeadline, @PathVariable("updatedDate") String updatedDate) {
		try{

			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				System.out.println("------------ " + applicationHeadline + " " + updatedDate);
				ApplicationForms applicationForm = applicationFormsService.findAllByApplicationHeadlineAndUpdatedDate(applicationHeadline, updatedDate + ".");
				System.out.println("$$$$$$$$$ " + applicationForm);
				model.addAttribute("applicationForm", applicationForm);
				return "detailedApplicationForm";
			}else{

				System.out.println("------------ " + applicationHeadline + " " + updatedDate);
				ApplicationForms applicationForm = applicationFormsService.findAllByApplicationHeadlineAndUpdatedDate(applicationHeadline, updatedDate + ".");
				System.out.println("$$$$$$$$$ " + applicationForm);
				model.addAttribute("applicationForm", applicationForm);

				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);
				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "detailedApplicationForm";
				}

			}

		}catch(Exception e){
			e.printStackTrace();
			return "index";
		}
	}

	@RequestMapping(value = "/Government-Jobs", method = RequestMethod.GET)
	public String governmentJobs(Model model, HttpServletRequest request) {
		try{

			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				List<GovernmentJobs> listOfGovernmentJobs = governmentJobsService.findAll();
				model.addAttribute("listOfGovernmentJobs", listOfGovernmentJobs);
				return "governmentJobsList";
			}else{
				List<GovernmentJobs> listOfGovernmentJobs = governmentJobsService.findAll();
				model.addAttribute("listOfGovernmentJobs", listOfGovernmentJobs);

				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);
				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "governmentJobsList";
				}
			}


		}catch(Exception e){
			e.printStackTrace();
			return "index";
		}
	}

	@RequestMapping(value = "/Government-Jobs/{jobHeadline}/{updatedDate}", method = RequestMethod.GET)
	public String governmentJobFullInfo(Model model, HttpServletRequest request, @PathVariable("jobHeadline") String jobHeadline, @PathVariable("updatedDate") String updatedDate) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				GovernmentJobs governmentJobs = governmentJobsService.findAllByGovernmentJobHeadlineAndUpdatedDate(jobHeadline, updatedDate + ".");
				model.addAttribute("governmentJobs", governmentJobs);
				return "detailedGovernmentJob";
			}else{
				GovernmentJobs governmentJobs = governmentJobsService.findAllByGovernmentJobHeadlineAndUpdatedDate(jobHeadline, updatedDate + ".");
				model.addAttribute("governmentJobs", governmentJobs);

				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);
				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "detailedGovernmentJob";
				}
			}


		}catch(Exception e){
			e.printStackTrace();
			return "index";
		}
	}

	@RequestMapping(value = "/Government-Websites", method = RequestMethod.GET)
	public String governmentWebsites(Model model, HttpServletRequest request) {
		try{

			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				List<GovernmentWebsites> listOfGovernmentWebsites = governmentWebsitesService.findAll();
				model.addAttribute("listOfGovernmentWebsites", listOfGovernmentWebsites);
				return "governmentWebsitesList";
			}else{
				List<GovernmentWebsites> listOfGovernmentWebsites = governmentWebsitesService.findAll();
				model.addAttribute("listOfGovernmentWebsites", listOfGovernmentWebsites);

				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);
				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "governmentWebsitesList";
				}
			}


		}catch(Exception e){
			e.printStackTrace();
			return "index";
		}
	}

	@RequestMapping(value = "/Government-Websites/{websiteHeadline}/{updatedDate}", method = RequestMethod.GET)
	public String governmentWebsiteFullInfo(Model model, HttpServletRequest request, @PathVariable("websiteHeadline") String websiteHeadline, @PathVariable("updatedDate") String updatedDate) {
		try{

			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				GovernmentWebsites governmentWebsites = governmentWebsitesService.findAllByGovernmentWebsitesHeadlineAndUpdatedDate(websiteHeadline, updatedDate + ".");
				model.addAttribute("governmentWebsites", governmentWebsites);
				return "detailedGovernmentWebsite";
			}else{
				GovernmentWebsites governmentWebsites = governmentWebsitesService.findAllByGovernmentWebsitesHeadlineAndUpdatedDate(websiteHeadline, updatedDate + ".");
				model.addAttribute("governmentWebsites", governmentWebsites);

				String username = (String)session.getAttribute("knowYourSchemeUser");
				User user = userService.findAllByUsername(username);
				HashMap<String, String> typeOfNewsMap = new HashMap<String, String>();
				typeOfNewsMap.put("government-health-schemes-in-India", "Health Schemes");
				typeOfNewsMap.put("government-sports-schemes-in-India", "Sports Schemes");
				typeOfNewsMap.put("government-education-schemes-in-India", "Education Schemes");
				typeOfNewsMap.put("government-agriculture-schemes-in-India", "Agriculture Schemes");
				typeOfNewsMap.put("government-pension-schemes-in-India", "Pension Schemes");
				typeOfNewsMap.put("government-post-office-schemes-in-India", "Post Office Schemes");
				typeOfNewsMap.put("government-road-and-highways-schemes-in-India", "Road and Highways Schemes");
				typeOfNewsMap.put("government-scholarships-schemes-in-India", "Scholarships Schemes");
				typeOfNewsMap.put("government-skill-schemes-in-India", "Skill Schemes");
				typeOfNewsMap.put("government-services-schemes-in-India", "Services Schemes");
				typeOfNewsMap.put("government-women-schemes-in-India", "Women Empowerment Schemes");
				typeOfNewsMap.put("government-urban-schemes-in-India", "Urban Schemes");
				typeOfNewsMap.put("government-startup-schemes-in-India", "Start Up Schemes");
				typeOfNewsMap.put("government-solar-mission-schemes-in-India", "Solar Mission Schemes");
				typeOfNewsMap.put("government-employment-schemes-in-India", "Employment Schemes");
				typeOfNewsMap.put("government-finance-and-insuarance-schemes-in-India", "Finance And Insuarance Schemes");
				typeOfNewsMap.put("government-housing-and-infrastructure-schemes-in-India", "Housing And Infrastructure Schemes");
				typeOfNewsMap.put("government-loan-schemes-in-India", "Loan Schemes");
				typeOfNewsMap.put("government-rural-schemes-in-India", "Rural Schemes");
				typeOfNewsMap.put("government-msme-schemes-in-India", "MSME Schemes");
				typeOfNewsMap.put("government-pregnant-women-schemes-in-India", "Pregnant Women Schemes");
				typeOfNewsMap.put("government-teachers-schemes-in-India", "Teachers Schemes");
				typeOfNewsMap.put("government-digital-india-schemes-in-India", "Digital Schemes");


				ArrayList<String> typeOfNewsList = new ArrayList<String>();
				typeOfNewsList.add("government-health-schemes-in-India");
				typeOfNewsList.add("government-sports-schemes-in-India");
				typeOfNewsList.add("government-education-schemes-in-India");
				typeOfNewsList.add("government-agriculture-schemes-in-India");
				typeOfNewsList.add("government-pension-schemes-in-India");
				typeOfNewsList.add("government-post-office-schemes-in-India");
				typeOfNewsList.add("government-road-and-highways-schemes-in-India");
				typeOfNewsList.add("government-scholarships-schemes-in-India");
				typeOfNewsList.add("government-skill-schemes-in-India");
				typeOfNewsList.add("government-services-schemes-in-India");
				typeOfNewsList.add("government-women-schemes-in-India");
				typeOfNewsList.add("government-urban-schemes-in-India");
				typeOfNewsList.add("government-startup-schemes-in-India");
				typeOfNewsList.add("government-solar-mission-schemes-in-India");
				typeOfNewsList.add("government-employment-schemes-in-India");
				typeOfNewsList.add("government-finance-and-insuarance-schemes-in-India");
				typeOfNewsList.add("government-housing-and-infrastructure-schemes-in-India");
				typeOfNewsList.add("government-loan-schemes-in-India");
				typeOfNewsList.add("government-rural-schemes-in-India");
				typeOfNewsList.add("government-msme-schemes-in-India");
				typeOfNewsList.add("government-pregnant-women-schemes-in-India");
				typeOfNewsList.add("government-teachers-schemes-in-India");
				typeOfNewsList.add("government-digital-india-schemes-in-India");

				List<UserCategories> listOfUserCategories = userCategoriesService.findAllByUsername(user.getUsername());

				List<News> listOfNewsByUserSelectedCategory = new ArrayList<News>();

				if(listOfUserCategories != null){
					for(UserCategories userCategories : listOfUserCategories){
						if(typeOfNewsList.contains(userCategories.getCategory())){
							typeOfNewsList.remove(userCategories.getCategory());
						}
					}

					for(UserCategories userCategories : listOfUserCategories){
						List<News> smallerListOfNewsByUserSelectedCategory = newsService.findAllByTypeOfNews(userCategories.getCategory());
						listOfNewsByUserSelectedCategory.addAll(smallerListOfNewsByUserSelectedCategory);
					}
				}

				List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);
				Collections.sort(listOfNewsByUserSelectedCategory, new SortTimelineByAddedDateDesc()); 
				model.addAttribute("listOfNewsByUserSelectedCategory", listOfNewsByUserSelectedCategory);

				List<News> news = newsService.findAll();
				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("typeOfNewsMap", typeOfNewsMap);
				model.addAttribute("typeOfNewsList", typeOfNewsList);
				model.addAttribute("listOfUserCategories", listOfUserCategories);
				model.addAttribute("newsListForMarque", news);

				if(user.getSelectedCategoryCount() < 5){
					model.addAttribute("verifiedUser", user);
					return "selectCategory";
				}else{
					model.addAttribute("dashboardUser", user);
					return "detailedGovernmentWebsite";
				}
			}


		}catch(Exception e){
			e.printStackTrace();
			return "index";
		}
	}

}
