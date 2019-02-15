/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enggcell.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.python.core.Py;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;
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
import com.enggcell.entities.GovernmentJobs;
import com.enggcell.entities.GovernmentWebsites;
import com.enggcell.entities.News;
import com.enggcell.entities.StatementGovernmentSchemes;
import com.enggcell.entities.User;
import com.enggcell.entities.Visualisation;
import com.enggcell.services.ApplicationFormsService;
import com.enggcell.services.CentralGovernmentSchemesService;
import com.enggcell.services.GovernmentJobsService;
import com.enggcell.services.GovernmentWebsitesService;
import com.enggcell.services.NewsService;
import com.enggcell.services.StateGovernmentSchemesService;
import com.enggcell.services.TempUserService;
import com.enggcell.services.UserCategoriesService;
import com.enggcell.services.UserService;
import com.enggcell.services.VisualisationService;
import com.enggcell.utilities.Constant;
import com.enggcell.utilities.EmailSender;
import com.enggcell.utilities.SortTimelineByAddedDateDesc;

/**
 *
 * @author 1003
 */
@Scope("request")
@RequestMapping(value = "/admin")
@Controller
public class AdminController { 

	@Autowired
	NewsService newsService;

	@Autowired
	UserService userService;

	@Autowired
	ApplicationFormsService applicationFormsService;
	
	@Autowired
	VisualisationService visualisationService;

	@Autowired
	CentralGovernmentSchemesService centralGovernmentSchemesService;

	@Autowired
	GovernmentJobsService governmentJobsService;

	@Autowired
	GovernmentWebsitesService governmentWebsitesService;

	@Autowired
	StateGovernmentSchemesService stateGovernmentSchemesService;

	@Autowired
	TempUserService tempUserService;

	@Autowired
	UserCategoriesService userCategoriesService;


	@Autowired
	ThreadPoolTaskExecutor taskExecutor;

	public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
	public static final String GOOGLE_SEARCH_URL2 = "https://www.google.co.in/finance/company_news";
	public static final String GOOGLE_TRANSLATE_URL_HINDI = "https://translate.google.com/#en/hi/";
	public static final String ACCOUNT_SID = "AC43d350bde96ac74cf83a2a5b25154dc5";
	public static final String AUTH_TOKEN = "9773f56d55da1462e31b55772255a18d";

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String indexPage(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "Admin/login";
			}else{

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

				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());
				return "Admin/dashboard";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.POST)
	public String indexPageLogin(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){

				String username = request.getParameter("username");
				String password = request.getParameter("password");

				if(username == null || username.equals("") || password == null || password.equals("")){
					return "Admin/login";
				}

				if(username.equals("iamAdminKYS")){
					if(password.equals("iamAdminKYS")){
						session.setAttribute("knowYourSchemeAdminUser", username);
					}else{
						model.addAttribute("myError", "true");
						return "Admin/login";
					}
				}else{
					model.addAttribute("myError", "true");
					return "Admin/login";
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

				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());

				return "Admin/dashboard";
			}else{

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


				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());
				return "Admin/dashboard";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String indexPageSame(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "Admin/login";
			}else{
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


				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());

				return "Admin/dashboard";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/signin/validate", method = RequestMethod.POST)
	@ResponseBody
	public String validateSignin(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				if(username == null || username.equals("") || password == null || password.equals("")){
					return "emptyFieldsFailure";
				}

				if(username.equals("iamAdminKYS")){	
					if(password.equals("iamAdminKYS")){
						return "success";
					}else{
						return "passwordFailure";
					}
				}else{
					return "usernameFailure";
				}
			}else{
				return "success";
			}
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errors", "true");
			return "failure";
		}
	}


	@RequestMapping(value = "/schemes/{stateName}", method = RequestMethod.GET)
	public String stateGovt(Model model, HttpServletRequest request, @PathVariable("stateName") String stateName) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "Admin/login";
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

				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);

				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());

				model.addAttribute("mainState", stateName);
				model.addAttribute("listOfStateGovernmentSchemesByState", listOfStateGovernmentSchemesByState);

				return "Admin/stateGvt";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	
	@RequestMapping(value = "/schemes/{stateName}/deleteScheme", method = RequestMethod.POST)
	public String deleteScheme(Model model, HttpServletRequest request, @PathVariable("stateName") String stateName) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "Admin/login";
			}else{
				
                String delId = request.getParameter("delId");
				
				if(delId == null || delId.equals("")){
					model.addAttribute("recordDeleted", "false");
					return "Admin/stateGvt";
				}
				stateGovernmentSchemesService.delete(Long.parseLong(delId));
				
				if(stateName.equalsIgnoreCase("chhattisgarh-govt-schemes")){
					//chatt
					stateName = "chattisgarh-govt-schemes";
				}
				List<StatementGovernmentSchemes> listOfStatementGovernmentSchemes = stateGovernmentSchemesService.findAll();
				List<StatementGovernmentSchemes> listOfStateGovernmentSchemesByState = stateGovernmentSchemesService.findAllByTypeOfScheme(stateName);
				
				model.addAttribute("recordDeleted", "true");
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

				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());

				model.addAttribute("mainState", stateName);
				model.addAttribute("listOfStateGovernmentSchemesByState", listOfStateGovernmentSchemesByState);

				return "Admin/stateGvt";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	
	@RequestMapping(value = "/schemes/{stateName}/updateSchemeDetails", method = RequestMethod.POST)
	public String updateScheme(Model model, HttpServletRequest request, @PathVariable("stateName") String stateName) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "Admin/login";
			}else{
				
				String hidId = request.getParameter("hidId");
				String schemeName = request.getParameter("updateSchemeName");
				String schemeLink = request.getParameter("updateSchemeLink");
				String imageUrl = request.getParameter("updateImageUrl");
				
				if(schemeName == null || schemeName.equals("") ||
				   schemeLink == null || schemeLink.equals("") ||
				   imageUrl == null || imageUrl.equals("")){
					model.addAttribute("recordUpdated", "false");
					return "";
				}
				
				StatementGovernmentSchemes stateGovernmentScheme = stateGovernmentSchemesService.findOne(Long.parseLong(hidId));
				stateGovernmentScheme.setSchemeName(schemeName.replaceAll("\\+", " "));
				stateGovernmentScheme.setSchemeLink(schemeLink);
				stateGovernmentScheme.setImageUrl(imageUrl);
				stateGovernmentSchemesService.save(stateGovernmentScheme);
				
				model.addAttribute("recordUpdated", "true");
				
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

				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);

				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());

				model.addAttribute("mainState", stateName);
				model.addAttribute("listOfStateGovernmentSchemesByState", listOfStateGovernmentSchemesByState);

				return "Admin/stateGvt";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	
	@RequestMapping(value = "/schemes/Application-Forms", method = RequestMethod.GET)
	public String applicationForms(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "Admin/login";
			}else{

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

				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());


				return "Admin/applicationForms";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}


	@RequestMapping(value = "/schemes/Government-Jobs", method = RequestMethod.GET)
	public String governmentJobs(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "Admin/login";
			}else{

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

				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());


				return "Admin/governmentJobs";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/schemes/Government-Websites", method = RequestMethod.GET)
	public String governmentWebsites(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "Admin/login";
			}else{

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

				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());


				return "Admin/governmentWebsites";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String news(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "Admin/login";
			}else{

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

				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());


				return "Admin/news";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/user/registered-users", method = RequestMethod.GET)
	public String registeredUser(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "Admin/login";
			}else{

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

				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());


				return "Admin/registeredUsers";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/user/temporary-users", method = RequestMethod.GET)
	public String temporaryUsers(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "Admin/login";
			}else{

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

				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());


				return "Admin/temporaryUsers";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/data-gather", method = RequestMethod.GET)
	public String dataGather(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "Admin/login";
			}else{

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

				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());


				return "Admin/datagather";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	@RequestMapping(value = "/datagather/visualisation", method = RequestMethod.POST)
	@ResponseBody
	public String getDataForVisulations(Model model, HttpServletRequest request) {
		System.out.println(visualisationService.findAll().size() + " #####");
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
		
		String searchUrl1 = "https://community.data.gov.in/tag/";
		
		Properties props = new Properties();
		props.put("python.home","E:\\My_Projects\\workspace1\\CopyEngg12Dec\\Lib");
		props.put("python.console.encoding", "UTF-8"); // Used to prevent: console: Failed to install '': java.nio.charset.UnsupportedCharsetException: cp0.
		props.put("python.security.respectJavaAccessibility", "false"); //don't respect java accessibility, so that we can access protected members on subclasses
		props.put("python.import.site","false");

		Properties preprops = System.getProperties();

		//PythonInterpreter interp = new PythonInterpreter(null, new PySystemState());
		PySystemState sys = Py.getSystemState();
		sys.path.append(new PyString("E:\\My_Projects\\workspace1\\CopyEngg12Dec\\Lib"));
		sys.path.append(new PyString("E:\\My_Projects\\workspace1\\CopyEngg12Dec\\Lib"));
		PythonInterpreter.initialize(preprops, props, new String[0]);
		PythonInterpreter pi = new PythonInterpreter();
		pi.exec("import sys");
		pi.exec("import json");
		
		
		try{
			for(String visualisationCat : visualisationCategory){
				try{
				String searchUrl11 = searchUrl1 + visualisationCat + "/";
				URLEncoder.encode(searchUrl11, "UTF-8");
				Document doc2 = Jsoup.connect(searchUrl11).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
				//System.out.println(searchUrl11 + " " + visualisationService.findAll().size() + " @@@@@");
				if(doc2 != null){
					Elements gridHolderList = doc2.select("figcaption");//grid-holder
					//System.out.println(visualisationService.findAll().size() + " %%%%%");
					if(gridHolderList != null){
						for(Element gridHolderLi : gridHolderList){
							try{
								//Duplicate Checking
								//System.out.println(visualisationService.findAll().size() + " #####");
								Element anchorTag = gridHolderLi.select("a").first();
								String mainLink = anchorTag.attr("href");
								String mainHeading = anchorTag.text();
								Visualisation visualisationDup = visualisationService.findAllByHeadline(mainHeading);
								if(visualisationDup == null){
									String searchUrl2 = mainLink;
									URLEncoder.encode(searchUrl2, "UTF-8");
									Document doc3 = Jsoup.connect(searchUrl2).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
									if(doc3 != null){
										Element gridHolderInner = doc3.select("div.grid-holder-inner").first();
										mainHeading = mainHeading.replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~\\.]", " ").replaceAll("\\+", " ");
										String detailedInfo = gridHolderInner.html();
										Visualisation visualisation = new Visualisation();
										
										//for image
										String headlinee1 = "charts of " + mainHeading.replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~]", " ");
										String searchURL = GOOGLE_SEARCH_URL + "?q=" + headlinee1 + "&source=lnms&tbm=isch";
										//System.out.println(searchURL + "----");
										//without proper User-Agent, we will get 403 error
										//searchURL = searchURL.replaceAll("\\+", " ");
										//[\\\\/:*?\"<>|]
										URLEncoder.encode(searchURL, "UTF-8");
										Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
										Element masthead = doc.select("div.rg_meta").first();
										String finalImgUrl = "";
										if(masthead != null){
											//System.out.println(masthead.text().toString() + "----");
											String resultImageUrlJson = masthead.text().toString().replaceAll("[^\\x00-\\x7F]", "");
											pi.set("imgJson", new PyString(resultImageUrlJson));
											pi.exec("parsed_json_img = json.loads(str(imgJson))");
											pi.exec("imgHeadUrl = parsed_json_img['ou']");
											PyString imgOb = (PyString)pi.get("imgHeadUrl");
											finalImgUrl = imgOb.asString();
										}
										
										visualisation.setTableData(finalImgUrl);
										visualisation.setAddedDate(new java.util.Date());
										visualisation.setCategory(visualisationCat);
										visualisation.setDetailedInfoPage(detailedInfo);
										visualisation.setHeadline(mainHeading);
										visualisation.setLink(mainLink);
										
										visualisationService.save(visualisation);
										
									}
								}
								
								
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
					
				}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "failure";
		}
	}
	
	@RequestMapping(value = "/datagather/State-Government", method = RequestMethod.POST)
	@ResponseBody
	public String getStateGovSchemes(Model model, HttpServletRequest request) {
		try{
			
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
			
			try{
				HttpSession session = request.getSession();
				if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
					return "404";
				}


				Properties props = new Properties();
				props.put("python.home","E:\\My_Projects\\workspace1\\CopyEngg12Dec\\Lib");
				props.put("python.console.encoding", "UTF-8"); // Used to prevent: console: Failed to install '': java.nio.charset.UnsupportedCharsetException: cp0.
				props.put("python.security.respectJavaAccessibility", "false"); //don't respect java accessibility, so that we can access protected members on subclasses
				props.put("python.import.site","false");

				Properties preprops = System.getProperties();

				//PythonInterpreter interp = new PythonInterpreter(null, new PySystemState());
				PySystemState sys = Py.getSystemState();
				sys.path.append(new PyString("E:\\My_Projects\\workspace1\\CopyEngg12Dec\\Lib"));
				sys.path.append(new PyString("E:\\My_Projects\\workspace1\\CopyEngg12Dec\\Lib"));
				PythonInterpreter.initialize(preprops, props, new String[0]);
				PythonInterpreter pi = new PythonInterpreter();
				pi.exec("import sys");
				pi.exec("import json");

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

				

				HashMap<String, String> monthNumber = new HashMap<String, String>();
				monthNumber.put("January", "01");
				monthNumber.put("February", "02");
				monthNumber.put("March", "03");
				monthNumber.put("April", "04");
				monthNumber.put("May", "05");
				monthNumber.put("June", "06");
				monthNumber.put("July", "07");
				monthNumber.put("August", "08");
				monthNumber.put("September", "09");
				monthNumber.put("October", "10");
				monthNumber.put("November", "11");
				monthNumber.put("December", "12");
				
				for(String stateUrl : statesForUrl){
					try{
						boolean searchIndex = true;
						int i = 1;
						int stopi = i;
						while(searchIndex){
							if(stopi == i){}
							else{
								System.out.println("break ------ " + i);
								break;
							}
							try{
								stopi++;

								String searchURL1 = "https://www.sarkaridunia.in/sarkari-yojana/" + stateUrl + "/page/" + i;
								URLEncoder.encode(searchURL1, "UTF-8");
								System.out.println(searchURL1 + "----");
								if(stateUrl.equalsIgnoreCase("mizoram-govt-schemes")){
									searchURL1 = "https://www.sarkaridunia.in/sarkari-yojana/" + stateUrl.replaceAll("schemes", "shemes");
								}
								Document doc2 = Jsoup.connect(searchURL1).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
								if(doc2 != null){
									i++;
									Element gtrayHeaderNews = doc2.select("div.archive-meta").first();
									Element olParent = gtrayHeaderNews.select("ol").first();
									if(olParent != null){
										Elements liChilds = olParent.select("li");
										for(Element liChild : liChilds){
											try{

												StatementGovernmentSchemes statementGovernmentScheme = new StatementGovernmentSchemes();
												Element strongChild = liChild.select("strong").first();
												String schemeName = null;
												if(strongChild != null){
													String schemeName1 = strongChild.text().trim();
													System.out.println(" %%%% " + schemeName1);
													schemeName1 = schemeName1.replaceAll("-", "");
													System.out.println(" %%%% " + schemeName1);
													schemeName1 = schemeName1.replaceAll(" ", "+");
													schemeName = schemeName1;
												}else{
													String schemeName1 = liChild.text().trim();
													System.out.println(" &&&& " + schemeName1);
													schemeName1 = schemeName1.replaceAll("-", "");
													System.out.println(" &&&& " + schemeName1);
													schemeName1 = schemeName1.replaceAll(" ", "+");
													schemeName = schemeName1;
												}
												schemeName.replaceAll("\\.", " ");
												statementGovernmentScheme.setTypeOfScheme(stateUrl);
												if(!schemeName.trim().equals("") && !schemeName.trim().equals("A")){
													statementGovernmentScheme.setSchemeName(schemeName.trim().replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~\\.]", " ").replaceAll("\\+", " "));
												}												
												String searchURL2 = "https://www.sarkaridunia.in/?s=" + schemeName;
												System.out.println(searchURL2);
												Document doc3 = Jsoup.connect(searchURL2).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
												if(doc3 != null){
													Element articleFirst = doc3.select("article").first();
													if(articleFirst != null){
														Element articleLink = articleFirst.select("a").first();
														if(articleLink != null){
															String mainUrl = articleLink.attr("href");
															statementGovernmentScheme.setSchemeLink(mainUrl);
															StatementGovernmentSchemes statementGovernmentSchemeDup = stateGovernmentSchemesService.findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(schemeName.trim().replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~\\.]", " ").replaceAll("\\+", " "), stateUrl, mainUrl);
															if(statementGovernmentSchemeDup == null){
																Document doc4 = Jsoup.connect(mainUrl).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
																if(doc4 != null){
																	Element mainSchemeDescription = doc4.select("article").first();
																	String lastModifiedDate = mainSchemeDescription.select("span.lastmod").first().text();

																	String[] updatedDateArray = lastModifiedDate.trim().replaceAll(",", "").split(" ");


																	String monthNum = monthNumber.get(updatedDateArray[2]);
																	String day = updatedDateArray[3];
																	String year = updatedDateArray[4];

																	if(Integer.parseInt(day) < 10){
																		day = "0" + day;
																	}
																	String[] timeArray = updatedDateArray[6].split(":");
																	String hour = timeArray[0];

																	for(String cc : timeArray){
																		System.out.println("TTTTTTTTTTTTTTTT" + cc);
																	}

																	if(updatedDateArray[7].equals("pm")){
																		if(hour.equals("12")){

																		}else{
																			int h = Integer.parseInt(hour);
																			h = h + 12;
																			hour = h + "";
																		}

																	}
																	if(Integer.parseInt(hour) < 10){
																		hour = "0" + hour;
																	}
																	String min = timeArray[1];

																	String sec = "00";

																	String timeStampDate = year + "-" + monthNum + "-" + day + " " + hour + ":" + min + ":" + sec;
																	Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStampDate);
																	Timestamp timeStampDateSql = getTimestamp(date1);
																	System.out.println("EEEEEEEEEEE " + timeStampDate);

																	statementGovernmentScheme.setActualLastModifiedDate(timeStampDateSql);
																	statementGovernmentScheme.setLastModifiedDate(lastModifiedDate);
																	statementGovernmentScheme.setDetailedInfo(mainSchemeDescription.html());
																	statementGovernmentScheme.setAddedDate(new java.sql.Date(new Date().getTime()));


																	//for image
																	String headlinee1 = schemeName.replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~]", " ");
																	String searchURL = GOOGLE_SEARCH_URL + "?q=" + headlinee1 + "&source=lnms&tbm=isch";
																	//System.out.println(searchURL + "----");
																	//without proper User-Agent, we will get 403 error
																	//searchURL = searchURL.replaceAll("\\+", " ");
																	//[\\\\/:*?\"<>|]
																	URLEncoder.encode(searchURL, "UTF-8");
																	Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
																	Element masthead = doc.select("div.rg_meta").first();
																	String finalImgUrl = "";
																	if(masthead != null){
																		//System.out.println(masthead.text().toString() + "----");
																		String resultImageUrlJson = masthead.text().toString().replaceAll("[^\\x00-\\x7F]", "");
																		pi.set("imgJson", new PyString(resultImageUrlJson));
																		pi.exec("parsed_json_img = json.loads(str(imgJson))");
																		pi.exec("imgHeadUrl = parsed_json_img['ou']");
																		PyString imgOb = (PyString)pi.get("imgHeadUrl");
																		finalImgUrl = imgOb.asString();
																	}
																	statementGovernmentScheme.setImageUrl(finalImgUrl);
																	//System.out.println(mainSchemeDescription.html());

																	//for video
																	//&source=lnms&tbm=vid
																	String headlinee1ForVideos = schemeName.replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~]", " ");
																	String searchURLForVideos = GOOGLE_SEARCH_URL + "?q=" + headlinee1ForVideos + "&source=lnms&tbm=vid";
																	System.out.println(searchURLForVideos + " $$$$$$$");
																	//without proper User-Agent, we will get 403 error
																	//searchURL = searchURL.replaceAll("\\+", " ");
																	//[\\\\/:*?\"<>|]
																	URLEncoder.encode(searchURLForVideos, "UTF-8");
																	Document docVideos = Jsoup.connect(searchURLForVideos).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
																	Element mastheadVideos = docVideos.select("div._NId").first();
																	String finalVideosUrl = "";
																	if(mastheadVideos != null){
																		Element srg = mastheadVideos.select("div.srg").first();
																		if(srg != null){
																			Elements gList = srg.select("div.g");
																			if(gList != null){
																				for(Element g : gList){
																					Element rc = g.select("div.rc").first();
																					if(rc != null){
																						Element r  = rc.select("h3.r").first();
																						if(r != null){
																							Element anchorTag = r.select("a").first();
																							String videoUrl = anchorTag.attr("href");
																							String videoHeading = anchorTag.text();
																							if(finalVideosUrl.equals("")){
																								finalVideosUrl = finalVideosUrl + videoUrl + "--------" + videoHeading;
																							}
																							finalVideosUrl = finalVideosUrl + "####%%%%####&&&&" + videoUrl + "--------" + videoHeading;
																						}
																					}
																				}
																			}
																		}
																	}else{
																		System.out.println(searchURLForVideos + " $$$$$$ %%%%% &&&&&&");
																	}
																	statementGovernmentScheme.setVideosUrl(finalVideosUrl);
																}
															}
														}
													}
												}

												if(statementGovernmentScheme.getAddedDate() != null &&
														statementGovernmentScheme.getDetailedInfo() != null &&
														statementGovernmentScheme.getSchemeLink() != null &&
														statementGovernmentScheme.getSchemeName() != null &&
														statementGovernmentScheme.getTypeOfScheme() != null &&
														statementGovernmentScheme.getLastModifiedDate() != null){
													statementGovernmentScheme.setOldNew("new");
													stateGovernmentSchemesService.save(statementGovernmentScheme);
												}
											}catch(Exception e){
												e.printStackTrace();
											}
										}
									}

									Elements schemesFromArticles = doc2.select("article");
									if(schemesFromArticles != null){
										for(Element article : schemesFromArticles){
											StatementGovernmentSchemes statementGovernmentScheme = new StatementGovernmentSchemes();
											try{
												Element schemeLinkAnchor = article.select("a").first();
												if(schemeLinkAnchor != null){
													String schemeName = schemeLinkAnchor.text();
													String schemeLink = schemeLinkAnchor.attr("href");
													String typeOfScheme = stateUrl;
													StatementGovernmentSchemes statementGovernmentSchemeDup = stateGovernmentSchemesService.findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(schemeName.trim().replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~\\.]", " ").replaceAll("\\+", " "), typeOfScheme, schemeLink);
													if(statementGovernmentSchemeDup == null){
														statementGovernmentScheme.setTypeOfScheme(typeOfScheme);
														if(!schemeName.trim().equals("") && !schemeName.trim().equals("A")){
															statementGovernmentScheme.setSchemeName(schemeName.trim().replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~\\.]", " ").replaceAll("\\+", " "));
														}

														statementGovernmentScheme.setSchemeLink(schemeLink);
														Document doc5 = Jsoup.connect(schemeLink).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
														if(doc5 != null){
															Element mainSchemeDescription = doc5.select("article").first();
															String lastModifiedDate = mainSchemeDescription.select("span.lastmod").first().text();

															String[] updatedDateArray = lastModifiedDate.trim().replaceAll(",", "").split(" ");


															String monthNum = monthNumber.get(updatedDateArray[2]);
															String day = updatedDateArray[3];
															String year = updatedDateArray[4];

															if(Integer.parseInt(day) < 10){
																day = "0" + day;
															}
															String[] timeArray = updatedDateArray[6].split(":");
															String hour = timeArray[0];

															for(String cc : timeArray){
																System.out.println("TTTTTTTTTTTTTTTT" + cc);
															}

															if(updatedDateArray[7].equals("pm")){
																if(hour.equals("12")){

																}else{
																	int h = Integer.parseInt(hour);
																	h = h + 12;
																	hour = h + "";
																}

															}
															if(Integer.parseInt(hour) < 10){
																hour = "0" + hour;
															}
															String min = timeArray[1];

															String sec = "00";

															String timeStampDate = year + "-" + monthNum + "-" + day + " " + hour + ":" + min + ":" + sec;
															Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStampDate);
															Timestamp timeStampDateSql = getTimestamp(date1);
															System.out.println("EEEEEEEEEEE " + timeStampDate);

															statementGovernmentScheme.setActualLastModifiedDate(timeStampDateSql);

															statementGovernmentScheme.setLastModifiedDate(lastModifiedDate);
															statementGovernmentScheme.setDetailedInfo(mainSchemeDescription.html());
															statementGovernmentScheme.setAddedDate(new java.sql.Date(new Date().getTime()));


															//for image
															String headlinee1 = schemeName.replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~]", " ");
															String searchURL = GOOGLE_SEARCH_URL + "?q=" + headlinee1 + "&source=lnms&tbm=isch";
															//System.out.println(searchURL + "----");
															//without proper User-Agent, we will get 403 error
															//searchURL = searchURL.replaceAll("\\+", " ");
															//[\\\\/:*?\"<>|]
															URLEncoder.encode(searchURL, "UTF-8");
															Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
															Element masthead = doc.select("div.rg_meta").first();
															String finalImgUrl = "";
															if(masthead != null){
																//System.out.println(masthead.text().toString() + "----");
																String resultImageUrlJson = masthead.text().toString().replaceAll("[^\\x00-\\x7F]", "");
																pi.set("imgJson", new PyString(resultImageUrlJson));
																pi.exec("parsed_json_img = json.loads(str(imgJson))");
																pi.exec("imgHeadUrl = parsed_json_img['ou']");
																PyString imgOb = (PyString)pi.get("imgHeadUrl");
																finalImgUrl = imgOb.asString();
															}

															statementGovernmentScheme.setImageUrl(finalImgUrl);
															try{
																//for video
																//&source=lnms&tbm=vid
																String headlinee1ForVideos = schemeName.replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~]", " ");
																String searchURLForVideos = GOOGLE_SEARCH_URL + "?q=" + headlinee1ForVideos + "&source=lnms&tbm=vid";
																System.out.println(searchURLForVideos + " $$$$$$$$ ");
																//without proper User-Agent, we will get 403 error
																//searchURL = searchURL.replaceAll("\\+", " ");
																//[\\\\/:*?\"<>|]
																URLEncoder.encode(searchURLForVideos, "UTF-8");
																Document docVideos = Jsoup.connect(searchURLForVideos).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
																Element mastheadVideos = docVideos.select("div._Nid").first();
																String finalVideosUrl = "";
																if(mastheadVideos != null){
																	Element srg = mastheadVideos.select("div.srg").first();
																	if(srg != null){
																		Elements gList = srg.select("div.g");
																		if(gList != null){
																			for(Element g : gList){
																				Element rc = g.select("div.rc").first();
																				if(rc != null){
																					Element r  = rc.select("h3.r").first();
																					if(r != null){
																						Element anchorTag = r.select("a").first();
																						String videoUrl = anchorTag.attr("href");
																						String videoHeading = anchorTag.text();
																						if(finalVideosUrl.equals("")){
																							finalVideosUrl = finalVideosUrl + videoUrl + "--------" + videoHeading;
																						}
																						finalVideosUrl = finalVideosUrl + "####%%%%####&&&&" + videoUrl + "--------" + videoHeading;
																					}else{
																						System.out.println(searchURLForVideos + " No RRRRRR");
																					}
																				}else{
																					System.out.println(searchURLForVideos + " No RCCCCC");
																				}
																			}
																		}else{
																			System.out.println(searchURLForVideos + " No GLISTTTTTTTTT");
																		}
																	}else{
																		System.out.println(searchURLForVideos + " No SRGGGGGGGGGG");
																	}
																}else{
																	System.out.println(searchURLForVideos + " $$$$$$ %%%%% &&&&&&");
																}
																statementGovernmentScheme.setVideosUrl(finalVideosUrl);
															}catch(Exception e){
																e.printStackTrace();
															}
														}
													}
												}
												if(statementGovernmentScheme.getAddedDate() != null &&
														statementGovernmentScheme.getDetailedInfo() != null &&
														statementGovernmentScheme.getSchemeLink() != null &&
														statementGovernmentScheme.getSchemeName() != null &&
														statementGovernmentScheme.getTypeOfScheme() != null &&
														statementGovernmentScheme.getLastModifiedDate() != null){
													statementGovernmentScheme.setOldNew("new");
													stateGovernmentSchemesService.save(statementGovernmentScheme);

												}
											}catch(Exception e){
												e.printStackTrace();
											}
										}
									}

								}

							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
			//Email to Registered Users for new Schemes
			List<User> registeredUsers = userService.findAll();
			ClassLoader classLoader = getClass().getClassLoader();
			String emailTemplate = "";
			try {
				emailTemplate = IOUtils.toString(classLoader.getResourceAsStream("EmailToRegUser.txt"));
			} catch (IOException ex) {

			}
			
			List<StatementGovernmentSchemes> listOfNewStateGovernmentSchemes = stateGovernmentSchemesService.findAllByOldNew("new");
			StringBuffer strBuff = new StringBuffer("");
			
			StringBuffer strBuffHeadlineForMobile = new StringBuffer("");
			int schForMobile = 1;
			
			int sch = 1;
			for(StatementGovernmentSchemes newStatementGovernmentScheme : listOfNewStateGovernmentSchemes){
				strBuff.append(sch + "). <strong>" + newStatementGovernmentScheme.getSchemeName() + "</strong> - by " + mapOfstatesWithUrl.get(newStatementGovernmentScheme.getTypeOfScheme()).replaceFirst("Schemes", "") + "" + "<br>");
				strBuff.append("<article>");
				strBuff.append(newStatementGovernmentScheme.getDetailedInfo());
				strBuff.append("</article>");
				strBuff.append("<br>");
				strBuff.append("<hr>");
				strBuff.append("<br>");
				sch++;
				strBuffHeadlineForMobile.append(schForMobile + "). " + newStatementGovernmentScheme.getSchemeName() + "\n");
				schForMobile++;
			}
			sch--;
			schForMobile--;
			if(listOfNewStateGovernmentSchemes != null && listOfNewStateGovernmentSchemes.size() != 0){
				for(User user : registeredUsers){
					try {
						emailTemplate = emailTemplate.replace("{fullname}", user.getFullName()).replace("{detailedHtml}", strBuff).replace("{countApp}", sch + "");
						taskExecutor.execute(new EmailSender(Constant.EMAIL_USER, user.getEmail(), "New Government Schemes", emailTemplate, "KnowYourScheme"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			if(listOfNewStateGovernmentSchemes != null && listOfNewStateGovernmentSchemes.size() != 0){
				for(User user : registeredUsers){
					try{
						System.out.println("+91" + user.getMobileNumber());
						
						Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

					    Message message = Message
					    		.creator(new PhoneNumber("+91" + user.getMobileNumber()), new PhoneNumber("+13022869262"),
					            "" + schForMobile + " new Schemes launched by Government \n" + strBuffHeadlineForMobile + "\n For more details check your mail or visit our website")
					        .create();

					    System.out.println(message.getSid());
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
			

			List<StatementGovernmentSchemes> listOfStatementGovernmentSchemes = stateGovernmentSchemesService.findAll();
			if(listOfStatementGovernmentSchemes != null){
				for(StatementGovernmentSchemes statementGovernmentScheme : listOfStatementGovernmentSchemes){
					if(statementGovernmentScheme.getOldNew().equals("new")){
						statementGovernmentScheme.setOldNew("old");
						stateGovernmentSchemesService.save(statementGovernmentScheme);
					}
				}
			}

			return "success";
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errors", "true");
			return "failure";
		}
	}



	@RequestMapping(value = "/datagather/News", method = RequestMethod.POST)
	@ResponseBody
	public String getNews(Model model, HttpServletRequest request) {
		try{

			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "404";
			}

			Properties props = new Properties();
			props.put("python.home","E:\\My_Projects\\workspace1\\CopyEngg12Dec\\Lib");
			props.put("python.console.encoding", "UTF-8"); // Used to prevent: console: Failed to install '': java.nio.charset.UnsupportedCharsetException: cp0.
			props.put("python.security.respectJavaAccessibility", "false"); //don't respect java accessibility, so that we can access protected members on subclasses
			props.put("python.import.site","false");

			Properties preprops = System.getProperties();

			//PythonInterpreter interp = new PythonInterpreter(null, new PySystemState());
			PySystemState sys = Py.getSystemState();
			sys.path.append(new PyString("E:\\My_Projects\\workspace1\\CopyEngg12Dec\\Lib"));
			sys.path.append(new PyString("E:\\My_Projects\\workspace1\\CopyEngg12Dec\\Lib"));
			PythonInterpreter.initialize(preprops, props, new String[0]);
			PythonInterpreter pi = new PythonInterpreter();
			pi.exec("import sys");
			pi.exec("import json");


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

			HashMap<String, String> monthNumber = new HashMap<String, String>();
			monthNumber.put("Jan", "01");
			monthNumber.put("Feb", "02");
			monthNumber.put("Mar", "03");
			monthNumber.put("Apr", "04");
			monthNumber.put("May", "05");
			monthNumber.put("Jun", "06");
			monthNumber.put("Jul", "07");
			monthNumber.put("Aug", "08");
			monthNumber.put("Sep", "09");
			monthNumber.put("Oct", "10");
			monthNumber.put("Nov", "11");
			monthNumber.put("Dec", "12");
			for(String newsType : typeOfNewsList){
				try{
					String searchURL1 = "https://economictimes.indiatimes.com/topic/" + newsType + "/news";
					URLEncoder.encode(searchURL1, "UTF-8");
					System.out.println(searchURL1 + "----");
					Document doc2 = Jsoup.connect(searchURL1).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
					if(doc2 != null){
						Element masthead = doc2.getElementById("news");
						if(masthead != null){
							String newsLink = null;
							String headLine = null;
							String timeDetail = null;
							String summary = null;
							Elements divTopicstries = masthead.select("div.topicstry");
							if(divTopicstries != null){
								for(Element divTopicstry : divTopicstries){
									try{

										News news = new News();
										news.setTypeOfNews(newsType);
										//a
										Element anchorTag = divTopicstry.select("a").first();
										if(anchorTag != null){
											newsLink = anchorTag.attr("href");
											headLine = anchorTag.text();
										}

										news.setNewsHeadline(headLine.trim());


										Element timeTag = divTopicstry.select("time").first();
										if(timeTag != null){
											timeDetail = timeTag.text();
										}
										Element paraTag = divTopicstry.select("p").first();
										if(paraTag != null){
											summary = paraTag.text();
										}
										//news.setNewsDate(headLine);
										System.out.println(" ---- " + summary + " " + timeDetail + " " + headLine + " " + newsLink);
										String[] timeDetailArray = timeDetail.split(" ");
										String day = "";
										if(Integer.parseInt(timeDetailArray[0]) < 10){
											day = "0" + timeDetailArray[0];
										}else{
											day = timeDetailArray[0];
										}
										String monthNum = monthNumber.get(timeDetailArray[1].trim().replaceAll(",", ""));
										String year = timeDetailArray[2].trim().replaceAll(",", "");
										String time = "";
										String hour = "";
										String min = "";
										String sec = "00";
										if(timeDetailArray[3].contains("PM")){
											int indexP = timeDetailArray[3].indexOf("P");
											String c = timeDetailArray[3].substring(0, indexP);
											System.out.println(c);
											hour = c.substring(0, c.indexOf("."));
											min = c.substring(c.indexOf(".")+1, c.length());
											System.out.println(hour + " " + min);
										}else{
											int indexA = timeDetailArray[3].indexOf("A");
											String c = timeDetailArray[3].substring(0, indexA);
											System.out.println(c);
											hour = c.substring(0, c.indexOf("."));
											min = c.substring(c.indexOf(".")+1, c.length());
											System.out.println(hour + " " + min);
										}

										String timeStampDate = year + "-" + monthNum + "-" + day + " " + hour + ":" + min + ":" + sec;
										Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStampDate);
										Timestamp timeStampDateSql = getTimestamp(date1);

										News dupNews = newsService.findAllByNewsDateAndNewsHeadline(timeStampDateSql, headLine.trim());
										if(dupNews == null){
											news.setNewsDate(timeStampDateSql);
											news.setNewsSummary(summary);
											news.setAddedDate(new java.sql.Date(new Date().getTime()));
											//for image
											String headlinee1 = headLine.replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~]", " ");
											String searchURL = GOOGLE_SEARCH_URL + "?q=" + headlinee1 + "&source=lnms&tbm=isch";
											//System.out.println(searchURL + "----");
											//without proper User-Agent, we will get 403 error
											//searchURL = searchURL.replaceAll("\\+", " ");
											//[\\\\/:*?\"<>|]
											URLEncoder.encode(searchURL, "UTF-8");
											Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
											Element masthead3 = doc.select("div.rg_meta").first();
											String finalImgUrl = "";
											if(masthead3 != null){
												//System.out.println(masthead.text().toString() + "----");
												String resultImageUrlJson = masthead3.text().toString().replaceAll("[^\\x00-\\x7F]", "");
												pi.set("imgJson", new PyString(resultImageUrlJson));
												pi.exec("parsed_json_img = json.loads(str(imgJson))");
												pi.exec("imgHeadUrl = parsed_json_img['ou']");
												PyString imgOb = (PyString)pi.get("imgHeadUrl");
												finalImgUrl = imgOb.asString();
											}
											System.out.println(finalImgUrl);
											System.out.println(newsLink);
											news.setImageUrl(finalImgUrl);	
											String detailedNewsPageLink = "https://economictimes.indiatimes.com" + newsLink;
											news.setNewsLink(detailedNewsPageLink);
											System.out.println(detailedNewsPageLink);

											Document doc3 = Jsoup.connect(detailedNewsPageLink).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
											if(doc3 != null){
												//System.out.println("doc3----------" + doc3);
												Element masthead2 = doc3.select("article").first();
												System.out.println("masthead2--------" + masthead2);
												if(masthead2 != null){
													String detailedNewsPage = masthead2.html();
													System.out.println("detailedPage--------" + detailedNewsPage);
													news.setDetailedInfoPage(detailedNewsPage);
													System.out.println(detailedNewsPage);
												}
											}
											if(news.getAddedDate() != null &&
													news.getDetailedInfoPage() != null &&
													news.getImageUrl() != null &&
													news.getNewsDate() != null &&
													news.getNewsHeadline() != null &&
													news.getNewsLink() != null &&
													news.getNewsSummary() != null &&
													news.getTypeOfNews() != null){
												newsService.save(news);
											}
										}

									}catch(Exception e){
										e.printStackTrace();
									}
								}
							}
						}



					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errors", "true");
			return "failure";
		}
	}

	@RequestMapping(value = "/datagather/Application-Forms", method = RequestMethod.POST)
	@ResponseBody
	public String getApplicationForms(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
			return "404";
		}

		HashMap<String, String> monthNumber = new HashMap<String, String>();
		monthNumber.put("January", "01");
		monthNumber.put("February", "02");
		monthNumber.put("March", "03");
		monthNumber.put("April", "04");
		monthNumber.put("May", "05");
		monthNumber.put("June", "06");
		monthNumber.put("July", "07");
		monthNumber.put("August", "08");
		monthNumber.put("September", "09");
		monthNumber.put("October", "10");
		monthNumber.put("November", "11");
		monthNumber.put("December", "12");
		try{
			boolean searchIndex = true;
			int i = 1;
			int stopi = i;
			while(searchIndex){
				if(stopi == i){}
				else{
					System.out.println("break ------ " + i);
					break;
				}
				try{
					stopi++;
					System.out.println("i ------ " + i);
					String searchURL = "https://www.sarkaridunia.in/application-forms/page/" + i;
					URLEncoder.encode(searchURL, "UTF-8");
					Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
					if(doc != null){
						i++;
						Elements myArticles = doc.select("article");
						if(myArticles != null){
							for(Element myArticle : myArticles){
								try{
									ApplicationForms applicationForm = new ApplicationForms();
									Element firstAnchor = myArticle.select("a").first();
									Element firstSmall = myArticle.select("small").first();
									String heading = firstAnchor.text().replaceAll("\\.", " ").trim();
									String updatedDate = firstSmall.text();
									String[] updatedDateArray = updatedDate.split("\\|");
									for(String updatedDateArr : updatedDateArray){
										System.out.println(updatedDateArr + " ##### ");
									}
									String actualUpdatedDate = updatedDateArray[0].trim().replaceAll(",", "");
									System.out.println(actualUpdatedDate + " ^^^^ ");
									String[] dateArray = actualUpdatedDate.split(" ");
									String monthNum = monthNumber.get(dateArray[0]);
									String day = dateArray[1];
									String year = dateArray[2];

									if(Integer.parseInt(day) < 10){
										day = "0" + day;
									}

									String hour = "00";
									String min = "00";
									String sec = "00";

									String timeStampDate = year + "-" + monthNum + "-" + day + " " + hour + ":" + min + ":" + sec;
									Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStampDate);
									Timestamp timeStampDateSql = getTimestamp(date1);

									applicationForm.setApplicationPageLink(firstAnchor.attr("href"));
									applicationForm.setApplicationHeadline(heading.replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~\\.]", " "));
									applicationForm.setUpdatedDate(updatedDate);
									applicationForm.setActualLastModifiedDate(timeStampDateSql);

									ApplicationForms applicationFormDup = applicationFormsService.findAllByApplicationHeadlineAndUpdatedDate(heading.trim().replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~\\.]", " ").replaceAll("\\+", " "), updatedDate);

									if(applicationFormDup == null){
										String searchURL2 = firstAnchor.attr("href");
										URLEncoder.encode(searchURL2, "UTF-8");
										Document doc2 = Jsoup.connect(searchURL2).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
										if(doc2 != null){
											Element myDetailedPage = doc2.select("article").first();
											if(myDetailedPage != null){
												applicationForm.setDetailedInfoPage(myDetailedPage.html());
												applicationForm.setAddedDate(new java.sql.Date(new Date().getTime()));



												try{
													//for video
													//&source=lnms&tbm=vid
													String headlinee1ForVideos = heading.replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~]", " ");
													String searchURLForVideos = GOOGLE_SEARCH_URL + "?q=" + headlinee1ForVideos + "&source=lnms&tbm=vid";
													System.out.println(searchURLForVideos + " $$$$$$$$ ");
													//without proper User-Agent, we will get 403 error
													//searchURL = searchURL.replaceAll("\\+", " ");
													//[\\\\/:*?\"<>|]
													URLEncoder.encode(searchURLForVideos, "UTF-8");

													Document docVideos = Jsoup.connect(searchURLForVideos).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();

													Element mastheadVideos = docVideos.select("div._Nid").first();
													String finalVideosUrl = "";
													if(mastheadVideos != null){
														Element srg = mastheadVideos.select("div.srg").first();
														if(srg != null){
															Elements gList = srg.select("div.g");
															if(gList != null){
																for(Element g : gList){
																	Element rc = g.select("div.rc").first();
																	if(rc != null){
																		Element r  = rc.select("h3.r").first();
																		if(r != null){
																			Element anchorTag = r.select("a").first();
																			String videoUrl = anchorTag.attr("href");
																			String videoHeading = anchorTag.text();
																			if(finalVideosUrl.equals("")){
																				finalVideosUrl = finalVideosUrl + videoUrl + "--------" + videoHeading;
																			}
																			finalVideosUrl = finalVideosUrl + "####%%%%####&&&&" + videoUrl + "--------" + videoHeading;
																		}else{
																			System.out.println(searchURLForVideos + " No RRRRRR");
																		}
																	}else{
																		System.out.println(searchURLForVideos + " No RCCCCC");
																	}
																}
															}else{
																System.out.println(searchURLForVideos + " No GLISTTTTTTTTT");
															}
														}else{
															System.out.println(searchURLForVideos + " No SRGGGGGGGGGG");
														}
													}else{
														System.out.println(searchURLForVideos + " $$$$$$ %%%%% &&&&&&");
													}
													applicationForm.setVideoLinks(finalVideosUrl);
												}catch(Exception e){
													e.printStackTrace();
												}

												applicationForm.setOldNew("new");
												applicationFormsService.save(applicationForm);



												//EMAIL to registered users for Application Forms
												
												

											}
										}
									}
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					}else{
						break;
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			
			List<ApplicationForms> listOfApplicationForms = applicationFormsService.findAllByOldNew("new");
			List<User> registeredUsers = userService.findAll();
			ClassLoader classLoader = getClass().getClassLoader();
			String emailTemplate = "";
			try {
				emailTemplate = IOUtils.toString(classLoader.getResourceAsStream("EmailToRegUser2.txt"));
			} catch (IOException ex) {

			}
			
			StringBuffer strBuff = new StringBuffer("");
			int sch = 1;
			
			StringBuffer strBuffHeadlineForMobile = new StringBuffer("");
			int schForMobile = 1;
			
			for(ApplicationForms newApplicationForm : listOfApplicationForms){
				strBuff.append(sch + "). <strong>" + newApplicationForm.getApplicationHeadline() + "</strong><br>");
				strBuff.append("<article>");
				strBuff.append(newApplicationForm.getDetailedInfoPage());
				strBuff.append("</article>");
				strBuff.append("<br>");
				strBuff.append("<hr>");
				strBuff.append("<br>");
				sch++;
				strBuffHeadlineForMobile.append(schForMobile + "). " + newApplicationForm.getApplicationHeadline() + "\n");
				schForMobile++;
			}
			sch--;
			schForMobile--;
			if(listOfApplicationForms != null && listOfApplicationForms.size() != 0){
				for(User user : registeredUsers){
					try {
						emailTemplate = emailTemplate.replace("{fullname}", user.getFullName()).replace("{detailedHtml}", strBuff).replace("{countApp}", sch + "");
						taskExecutor.execute(new EmailSender(Constant.EMAIL_USER, user.getEmail(), "New Application forms", emailTemplate, "KnowYourScheme"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			
			
			if(listOfApplicationForms != null && listOfApplicationForms.size() != 0){
				for(User user : registeredUsers){
					try{
						System.out.println("+91" + user.getMobileNumber());
						
						Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
						
						
						String nationalNumber = URLEncoder.encode("917748850840", "UTF-8").replaceAll("\\+", "%20");

						com.twilio.rest.lookups.v1.PhoneNumber number = com.twilio.rest.lookups.v1.PhoneNumber
					        .fetcher(new com.twilio.type.PhoneNumber(nationalNumber))
					        .setType("carrier")
					        .setCountryCode("IN")
					        .fetch();
					    
						System.out.println(number.getCarrier().get("name"));
					    System.out.println(number.getCarrier().get("type"));

					    Message message = Message
					        .creator(new PhoneNumber("+91" + user.getMobileNumber()), new PhoneNumber("+13022869262"),
					            "" + schForMobile + " new Application Forms \n" + strBuffHeadlineForMobile + "\n For more details check your mail or visit our website")
					        .create();

					    System.out.println(message.getSid());
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
			
			List<ApplicationForms> listOfApplicationForms2 = applicationFormsService.findAll();
			if(listOfApplicationForms2 != null){
				for(ApplicationForms applicationForms : listOfApplicationForms2){
					if(applicationForms.getOldNew().equals("new")){
						applicationForms.setOldNew("old");
						applicationFormsService.save(applicationForms);
					}
				}
			}

			return "success";
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errors", "true");
			return "failure";
		}
	}



	@RequestMapping(value = "/datagather/Government-Jobs", method = RequestMethod.POST)
	@ResponseBody
	public String getGovernmentJobs(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
			return "404";
		}

		HashMap<String, String> monthNumber = new HashMap<String, String>();
		monthNumber.put("January", "01");
		monthNumber.put("February", "02");
		monthNumber.put("March", "03");
		monthNumber.put("April", "04");
		monthNumber.put("May", "05");
		monthNumber.put("June", "06");
		monthNumber.put("July", "07");
		monthNumber.put("August", "08");
		monthNumber.put("September", "09");
		monthNumber.put("October", "10");
		monthNumber.put("November", "11");
		monthNumber.put("December", "12");
		try{
			boolean searchIndex = true;
			int i = 1;
			int stopi = i;
			while(searchIndex){
				if(stopi == i){}
				else{
					System.out.println("break ------ " + i);
					break;
				}
				try{
					stopi++;
					String searchURL = "https://www.sarkaridunia.in/sarkari-naukri/page/" + i;
					URLEncoder.encode(searchURL, "UTF-8");
					Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
					if(doc != null){
						i++;
						Elements myArticles = doc.select("article");
						if(myArticles != null){
							for(Element myArticle : myArticles){
								try{
									GovernmentJobs governmentJobs = new GovernmentJobs();
									Element firstAnchor = myArticle.select("a").first();
									Element firstSmall = myArticle.select("small").first();
									String heading = firstAnchor.text().replaceAll("\\.", " ").trim();
									String updatedDate = firstSmall.text();


									String[] updatedDateArray = firstSmall.text().split("\\|");
									String actualUpdatedDate = updatedDateArray[0].trim().replaceAll(",", "");
									String[] dateArray = actualUpdatedDate.split(" ");
									String monthNum = monthNumber.get(dateArray[0]);
									String day = dateArray[1];
									String year = dateArray[2];

									if(Integer.parseInt(day) < 10){
										day = "0" + day;
									}

									String hour = "00";
									String min = "00";
									String sec = "00";

									String timeStampDate = year + "-" + monthNum + "-" + day + " " + hour + ":" + min + ":" + sec;
									Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStampDate);
									Timestamp timeStampDateSql = getTimestamp(date1);

									governmentJobs.setGoveernmentJobPageLink(firstAnchor.attr("href"));
									governmentJobs.setGovernmentJobHeadline(heading.replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~\\.]", " "));
									governmentJobs.setUpdatedDate(updatedDate);
									governmentJobs.setActualLastModifiedDate(timeStampDateSql);

									GovernmentJobs governmentJobsDup = governmentJobsService.findAllByGovernmentJobHeadlineAndUpdatedDate(heading.trim().replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~\\.]", " ").replaceAll("\\+", " "), updatedDate);

									if(governmentJobsDup == null){
										String searchURL2 = firstAnchor.attr("href");
										URLEncoder.encode(searchURL2, "UTF-8");
										Document doc2 = Jsoup.connect(searchURL2).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
										if(doc2 != null){
											Element myDetailedPage = doc2.select("article").first();
											if(myDetailedPage != null){
												governmentJobs.setDetailedInfoPage(myDetailedPage.html());
												governmentJobs.setAddedDate(new java.sql.Date(new Date().getTime()));


												try{
													//for video
													//&source=lnms&tbm=vid
													String headlinee1ForVideos = heading.replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~]", " ");
													String searchURLForVideos = GOOGLE_SEARCH_URL + "?q=" + headlinee1ForVideos + "&source=lnms&tbm=vid";
													System.out.println(searchURLForVideos + " $$$$$$$$ ");
													//without proper User-Agent, we will get 403 error
													//searchURL = searchURL.replaceAll("\\+", " ");
													//[\\\\/:*?\"<>|]
													URLEncoder.encode(searchURLForVideos, "UTF-8");

													Document docVideos = Jsoup.connect(searchURLForVideos).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();

													Element mastheadVideos = docVideos.select("div._Nid").first();
													String finalVideosUrl = "";
													if(mastheadVideos != null){
														Element srg = mastheadVideos.select("div.srg").first();
														if(srg != null){
															Elements gList = srg.select("div.g");
															if(gList != null){
																for(Element g : gList){
																	Element rc = g.select("div.rc").first();
																	if(rc != null){
																		Element r  = rc.select("h3.r").first();
																		if(r != null){
																			Element anchorTag = r.select("a").first();
																			String videoUrl = anchorTag.attr("href");
																			String videoHeading = anchorTag.text();
																			if(finalVideosUrl.equals("")){
																				finalVideosUrl = finalVideosUrl + videoUrl + "--------" + videoHeading;
																			}
																			finalVideosUrl = finalVideosUrl + "####%%%%####&&&&" + videoUrl + "--------" + videoHeading;
																		}else{
																			System.out.println(searchURLForVideos + " No RRRRRR");
																		}
																	}else{
																		System.out.println(searchURLForVideos + " No RCCCCC");
																	}
																}
															}else{
																System.out.println(searchURLForVideos + " No GLISTTTTTTTTT");
															}
														}else{
															System.out.println(searchURLForVideos + " No SRGGGGGGGGGG");
														}
													}else{
														System.out.println(searchURLForVideos + " $$$$$$ %%%%% &&&&&&");
													}
													governmentJobs.setVideoLinks(finalVideosUrl);
												}catch(Exception e){
													e.printStackTrace();
												}
												governmentJobs.setOldNew("new");
												governmentJobsService.save(governmentJobs);

											}
										}
									}
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			
			//EMAIL to registered users for Government Jobs
			
			List<GovernmentJobs> listOfGovernmentJobs = governmentJobsService.findAllByOldNew("new");
			
			List<User> registeredUsers = userService.findAll();
			ClassLoader classLoader = getClass().getClassLoader();
			String emailTemplate = "";
			try {
				emailTemplate = IOUtils.toString(classLoader.getResourceAsStream("EmailToRegUser3.txt"));
			} catch (IOException ex) {

			}
			
			StringBuffer strBuffHeadlineForMobile = new StringBuffer("");
			int schForMobile = 1;
			
			StringBuffer strBuff = new StringBuffer("");
			int sch = 1;
			for(GovernmentJobs newGovernmentJob : listOfGovernmentJobs){
				strBuff.append(sch + "). <strong>" + newGovernmentJob.getGovernmentJobHeadline() + "</strong><br>");
				strBuff.append("<article>");
				strBuff.append(newGovernmentJob.getDetailedInfoPage());
				strBuff.append("</article>");
				strBuff.append("<br>");
				strBuff.append("<hr>");
				strBuff.append("<br>");
				sch++;
				strBuffHeadlineForMobile.append(schForMobile + "). " + newGovernmentJob.getGovernmentJobHeadline() + "\n");
				schForMobile++;
			}
			sch--;
			schForMobile--;
			if(listOfGovernmentJobs != null && listOfGovernmentJobs.size() != 0){
				for(User user : registeredUsers){
					try {
						emailTemplate = emailTemplate.replace("{fullname}", user.getFullName()).replace("{detailedHtml}", strBuff).replace("{countApp}", sch + "");
						taskExecutor.execute(new EmailSender(Constant.EMAIL_USER, user.getEmail(), "New Government Jobs Posted", emailTemplate, "KnowYourScheme"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			if(listOfGovernmentJobs != null && listOfGovernmentJobs.size() != 0){
				for(User user : registeredUsers){
					try{
						System.out.println("+91" + user.getMobileNumber());
						
						Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

					    Message message = Message
					    		.creator(new PhoneNumber("+91" + user.getMobileNumber()), new PhoneNumber("+13022869262"),
					            "" + schForMobile + " New Government Jobs Posted \n" + strBuffHeadlineForMobile + "\n For more details check your mail or visit our website")
					        .create();

					    System.out.println(message.getSid());
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			

			List<GovernmentJobs> listOfGovernmentJobs2 = governmentJobsService.findAll();
			if(listOfGovernmentJobs2 != null){
				for(GovernmentJobs governmentJob : listOfGovernmentJobs2){
					if(governmentJob.getOldNew().equals("new")){
						governmentJob.setOldNew("old");
						governmentJobsService.save(governmentJob);
					}
				}
			}

			return "success";
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errors", "true");
			return "failure";
		}
	}

	@RequestMapping(value = "/datagather/Government-Websites", method = RequestMethod.POST)
	@ResponseBody
	public String getGovernmentWebsites(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
			return "404";
		}

		HashMap<String, String> monthNumber = new HashMap<String, String>();
		monthNumber.put("January", "01");
		monthNumber.put("February", "02");
		monthNumber.put("March", "03");
		monthNumber.put("April", "04");
		monthNumber.put("May", "05");
		monthNumber.put("June", "06");
		monthNumber.put("July", "07");
		monthNumber.put("August", "08");
		monthNumber.put("September", "09");
		monthNumber.put("October", "10");
		monthNumber.put("November", "11");
		monthNumber.put("December", "12");
		try{
			boolean searchIndex = true;
			int i = 1;
			int stopi = i;
			while(searchIndex){
				if(stopi == i){}
				else{
					System.out.println("break ------ " + i);
					break;
				}
				try{
					stopi++;
					String searchURL = "https://www.sarkaridunia.in/contact-numbers-address/page/" + i;
					URLEncoder.encode(searchURL, "UTF-8");
					Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
					if(doc != null){
						i++;
						Elements myArticles = doc.select("article");
						if(myArticles != null){
							for(Element myArticle : myArticles){
								try{
									GovernmentWebsites governmentWebsites = new GovernmentWebsites();
									Element firstAnchor = myArticle.select("a").first();
									Element firstSmall = myArticle.select("small").first();
									String heading = firstAnchor.text().replaceAll("\\.", " ").trim();
									String updatedDate = firstSmall.text();

									String[] updatedDateArray = firstSmall.text().split("\\|");
									String actualUpdatedDate = updatedDateArray[0].trim().replaceAll(",", "");
									String[] dateArray = actualUpdatedDate.split(" ");
									String monthNum = monthNumber.get(dateArray[0]);
									String day = dateArray[1];
									String year = dateArray[2];

									if(Integer.parseInt(day) < 10){
										day = "0" + day;
									}

									String hour = "00";
									String min = "00";
									String sec = "00";

									String timeStampDate = year + "-" + monthNum + "-" + day + " " + hour + ":" + min + ":" + sec;
									Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStampDate);
									Timestamp timeStampDateSql = getTimestamp(date1);

									governmentWebsites.setGovernmentWebsitesPageLink(firstAnchor.attr("href"));
									governmentWebsites.setGovernmentWebsitesHeadline(heading.replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~\\.]", " "));
									governmentWebsites.setUpdatedDate(updatedDate);
									governmentWebsites.setActualLastModifiedDate(timeStampDateSql);

									GovernmentWebsites governmentWebsitesDup = governmentWebsitesService.findAllByGovernmentWebsitesHeadlineAndUpdatedDate(heading.trim().replaceAll("[\\\\/:*?\"<>|%#&@;:={}^~\\.]", " ").replaceAll("\\+", " "), updatedDate);

									if(governmentWebsitesDup == null){
										String searchURL2 = firstAnchor.attr("href");
										URLEncoder.encode(searchURL2, "UTF-8");
										Document doc2 = Jsoup.connect(searchURL2).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
										if(doc2 != null){
											Element myDetailedPage = doc2.select("article").first();
											if(myDetailedPage != null){
												governmentWebsites.setDetailedInfoPage(myDetailedPage.html());
												governmentWebsites.setAddedDate(new java.sql.Date(new Date().getTime()));

												governmentWebsites.setOldNew("new");
												governmentWebsitesService.save(governmentWebsites);

											}
										}
									}
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			
			List<GovernmentWebsites> listOfGovernmentWebsites = governmentWebsitesService.findAllByOldNew("new");
			
			List<User> registeredUsers = userService.findAll();
			ClassLoader classLoader = getClass().getClassLoader();
			String emailTemplate = "";
			try {
				emailTemplate = IOUtils.toString(classLoader.getResourceAsStream("EmailToRegUser4.txt"));
			} catch (IOException ex) {

			}
			
			StringBuffer strBuff = new StringBuffer("");
			int sch = 1;
			
			StringBuffer strBuffHeadlineForMobile = new StringBuffer("");
			int schForMobile = 1;
			
			for(GovernmentWebsites newGovernmentWebsite : listOfGovernmentWebsites){
				strBuff.append(sch + "). <strong>" + newGovernmentWebsite.getGovernmentWebsitesHeadline() + "</strong><br>");
				strBuff.append("<article>");
				strBuff.append(newGovernmentWebsite.getDetailedInfoPage());
				strBuff.append("</article>");
				strBuff.append("<br>");
				strBuff.append("<hr>");
				strBuff.append("<br>");
				sch++;
				strBuffHeadlineForMobile.append(schForMobile + "). " + newGovernmentWebsite.getGovernmentWebsitesHeadline() + "<br>");
				schForMobile++;
			}
			sch--;
			schForMobile--;
			if(listOfGovernmentWebsites != null && listOfGovernmentWebsites.size() != 0){
				for(User user : registeredUsers){
					try {
						emailTemplate = emailTemplate.replace("{fullname}", user.getFullName()).replace("{detailedHtml}", strBuff).replace("{countApp}", sch + "");
						taskExecutor.execute(new EmailSender(Constant.EMAIL_USER, user.getEmail(), "New Government Websites launched", emailTemplate, "KnowYourScheme"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			if(listOfGovernmentWebsites != null && listOfGovernmentWebsites.size() != 0){
				for(User user : registeredUsers){
					try{
						System.out.println("+91" + user.getMobileNumber());
						
						Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

					    Message message = Message
					    		.creator(new PhoneNumber("+91" + user.getMobileNumber()), new PhoneNumber("+13022869262"),
					            "" + schForMobile + " New Government Websites launched \n" + strBuffHeadlineForMobile + "\n For more details check your mail or visit our website")
					        .create();

					    System.out.println(message.getSid());
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
			List<GovernmentWebsites> listOfGovernmentWebsites2 = governmentWebsitesService.findAll();
			if(listOfGovernmentWebsites2 != null){
				for(GovernmentWebsites governmentWebsite : listOfGovernmentWebsites2){
					if(governmentWebsite.getOldNew().equals("new")){
						governmentWebsite.setOldNew("old");
						governmentWebsitesService.save(governmentWebsite);
					}
				}
			}

			return "success";
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errors", "true");
			return "failure";
		}
	}

	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}

	public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);

		return cal.getTime();
	}

	public static Date subtractHours(Date date, int hours) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.HOUR, -hours);

		return cal.getTime();
	}

	public static Date subtractMinutes(Date date, int min) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, -min);

		return cal.getTime();
	}

	public static Date subtractSeconds(Date date, int secs) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.SECOND, -secs);

		return cal.getTime();
	}

	public Timestamp getTimestamp(java.util.Date date){
		return date == null ? null : new java.sql.Timestamp(date.getTime()); 
	}

	@RequestMapping(value = "/State-Government/{stateName}/{detailed}", method = RequestMethod.GET)
	public String stateGovtDup(Model model, HttpServletRequest request, @PathVariable("stateName") String stateName) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeAdminUser") == null || session.getAttribute("knowYourSchemeAdminUser") == ""){
				return "Admin/login";
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

				model.addAttribute("statesForUrl", statesForUrl);
				model.addAttribute("mapOfstatesWithUrl", mapOfstatesWithUrl);

				model.addAttribute("listOfUsers", userService.findAll());
				model.addAttribute("listOfUserCategories", userCategoriesService.findAll());
				model.addAttribute("listOfApplicationForms", applicationFormsService.findAll());
				model.addAttribute("listOfCentralGovernmentSchemes", centralGovernmentSchemesService.findAll());
				model.addAttribute("listOfStatementGovernmentSchemes", stateGovernmentSchemesService.findAll());
				model.addAttribute("listOfGovernmentJpbs", governmentJobsService.findAll());
				model.addAttribute("listOfGovernmentWebsites", governmentWebsitesService.findAll());
				model.addAttribute("listOfTempUsers", tempUserService.findAll());
				model.addAttribute("listOfNews", newsService.findAll());

				model.addAttribute("mainState", stateName);
				model.addAttribute("listOfStateGovernmentSchemesByState", listOfStateGovernmentSchemesByState);

				return "Admin/dashboard";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
}
