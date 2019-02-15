/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enggcell.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
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
import com.enggcell.entities.ForgetPasswordTempUser;
import com.enggcell.entities.GovernmentJobs;
import com.enggcell.entities.News;
import com.enggcell.entities.StatementGovernmentSchemes;
import com.enggcell.entities.TempUser;
import com.enggcell.entities.User;
import com.enggcell.entities.UserCategories;
import com.enggcell.services.ApplicationFormsService;
import com.enggcell.services.ForgetPasswordTempUserService;
import com.enggcell.services.GovernmentJobsService;
import com.enggcell.services.NewsService;
import com.enggcell.services.StateGovernmentSchemesService;
import com.enggcell.services.TempUserService;
import com.enggcell.services.UserCategoriesService;
import com.enggcell.services.UserService;
import com.enggcell.utilities.Constant;
import com.enggcell.utilities.EmailSender;
import com.enggcell.utilities.SortApplicationFormsByActualLastModifiedDateDesc;
import com.enggcell.utilities.SortGovernmentJobsByActualLastModifiedDateDesc;
import com.enggcell.utilities.SortStateGovernmentSchemesByActualLastModifiedDateDesc;
import com.enggcell.utilities.SortTimelineByAddedDateDesc;

/**
 *
 * @author 1003
 */
@Scope("request")
@RequestMapping(value = "/")
@Controller
public class LandingController {

	@Autowired
	NewsService newsService;

	@Autowired
	UserService userService;

	@Autowired
	GovernmentJobsService governmentJobsService;

	@Autowired
	StateGovernmentSchemesService stateGovernmentSchemesService;

	@Autowired
	ApplicationFormsService applicationFormsService;

	@Autowired
	TempUserService tempUserService;
	
	@Autowired
	ForgetPasswordTempUserService forgetPasswordTempUserService;
	
	@Autowired
	UserCategoriesService userCategoriesService;

	@Autowired
	ThreadPoolTaskExecutor taskExecutor;

	public static final String SALT = "my-salt-text";

	@RequestMapping(value = "/privacy")
	public String privacy(Model model, HttpServletRequest request) {
		return "privacy";
	}

	@RequestMapping(value = "/terms")
	public String terms(Model model, HttpServletRequest request) {
		return "terms";
	}

	@RequestMapping(value = "/register")
	public String register(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				return "register";
			}else{
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
					return "dashboard";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/register/dashboard/{verificationLink}")
	public String selectCategory(Model model, HttpServletRequest request, @PathVariable("verificationLink") String verificationLink) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				TempUser tempUser = tempUserService.findAllByHashGeneratedUniqueRgistrationId(verificationLink);
				if(tempUser != null){
					User user = new User();
					user.setEmail(tempUser.getEmail());
					user.setMobileNumber(tempUser.getMobileNumber());
					user.setFullName(tempUser.getFullName());
					user.setPassword(tempUser.getPassword());
					user.setActualPassword(tempUser.getActualPassword());
					user.setSelectedCategoryCount(0);
					user.setUniqueRgistrationId(tempUser.getUniqueRgistrationId());
					user.setUsername(tempUser.getUsername());
					user.setAddedDate(tempUser.getAddedDate());
					userService.save(user);

					tempUserService.delete(tempUser.getId());

					session.setAttribute("knowYourSchemeUser", user.getUsername());

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
					model.addAttribute("verifiedUser", user);



					return "selectCategory";
				}else{
					return "404";
				}
			}else{
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
					return "dashboard";
				}
			}

		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/register/validate", method = RequestMethod.POST)
	@ResponseBody
	public String validate(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String email = request.getParameter("email");
				String mobileNumber = request.getParameter("mobileNumber");
				if(mobileNumber == null || mobileNumber.equals("") || username == null || username.equals("") || password == null || password.equals("") || email == null || email.equals("")){
					return "emptyFieldsFailure";
				}
				String saltedPassword = SALT + password;
				password = generateHash(saltedPassword);

				User userByUsername = userService.findAllByUsername(username);
				User userByEmail = userService.findAllByEmail(email);
				User userByMobileNumber = userService.findAllByMobileNumber(mobileNumber);
				TempUser tempUser = tempUserService.findAllByEmail(email);
				if(tempUser != null){
					return "emailGoesForVerificationAlreadyFailure";
				}

				if(userByUsername != null){	
					return "usernameFailure";
				}else if(userByEmail != null){
					return "emailFailure";
				}else if(userByMobileNumber != null){
					return "mobileNumberFailure";
				}else if(password.length() < 8){
					return "passwordFailure";
				}else{
					return "success";
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

	@RequestMapping(value = "/register/submit", method = RequestMethod.POST)
	public String registerSubmit(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				String fullName = request.getParameter("fullName");
				String email = request.getParameter("email");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String mobileNumber = request.getParameter("mobileNumber");

				if (mobileNumber == null || mobileNumber.equals("") || fullName == null || fullName.equals("") || email == null || email.equals("") || username == null || username.equals("") || password == null || password.equals("")){
					return "index";
				}

				TempUser tempUserDupByUsername = tempUserService.findAllByUsername(username);
				TempUser tempUserDupByEmail = tempUserService.findAllByEmail(email);
				TempUser tempUserDupByMobileNumber = tempUserService.findAllByMobileNumber(mobileNumber);
				if(tempUserDupByEmail != null){
					model.addAttribute("alreadySent", "true");
					return "verification";
				}else if(tempUserDupByUsername != null){
					model.addAttribute("alreadySent", "true");
					return "verification";
				}else if(tempUserDupByMobileNumber != null){
					model.addAttribute("alreadySent", "true");
					return "verification";
				}else{
					String actualPassword = password;
					String saltedPassword = SALT + password;
					password = generateHash(saltedPassword);

					String uniqueRgistrationId = SALT + username + password + email + fullName + new Date().toString();
					String hashGeneratedUniqueRgistrationId = generateHash(uniqueRgistrationId);

					TempUser tempUser = new TempUser();
					tempUser.setFullName(fullName);
					tempUser.setEmail(email);
					tempUser.setMobileNumber(mobileNumber);
					tempUser.setPassword(password);
					tempUser.setActualPassword(actualPassword);
					tempUser.setHashGeneratedUniqueRgistrationId(hashGeneratedUniqueRgistrationId);
					tempUser.setUniqueRgistrationId(uniqueRgistrationId);
					tempUser.setUsername(username);
					tempUser.setAddedDate(new java.sql.Date(new Date().getTime()));

					tempUserService.save(tempUser);
					ClassLoader classLoader = getClass().getClassLoader();
					String emailTemplate = "";
					try {
						emailTemplate = IOUtils.toString(classLoader.getResourceAsStream("Newuser.txt"));
					} catch (IOException ex) {

					}

					String verificationLink = "http://localhost:8088" + request.getContextPath() + "/register/dashboard/" + hashGeneratedUniqueRgistrationId;

					emailTemplate = emailTemplate.replace("{fullname}", fullName).replace("{verificationLink}", verificationLink);
					taskExecutor.execute(new EmailSender(Constant.EMAIL_USER, email, "Verification Email for ", emailTemplate, "KnowYourScheme"));

					model.addAttribute("alreadySent", "false");
					return "verification";
				}
			}else{
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

				List<ApplicationForms> listApplicationFormsForMarque = applicationFormsService.findAll();
				Collections.sort(listApplicationFormsForMarque, new SortApplicationFormsByActualLastModifiedDateDesc());
				model.addAttribute("listApplicationFormsForMarque", listApplicationFormsForMarque);

				List<GovernmentJobs> listOfGovernmentJobsForMarque = governmentJobsService.findAll();
				Collections.sort(listOfGovernmentJobsForMarque, new SortGovernmentJobsByActualLastModifiedDateDesc());
				model.addAttribute("listOfGovernmentJobsForMarque", listOfGovernmentJobsForMarque);

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
					return "dashboard";
				}
			}

		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	//@RequestMapping(value = "/register/dashboard/{verificationLink}")
	
	@RequestMapping(value = "/password-reset-link/{verificationLink}", method = RequestMethod.GET)
	public String passwordResetLink(Model model, HttpServletRequest request, @PathVariable("verificationLink") String verificationLink) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				ForgetPasswordTempUser tempUser = forgetPasswordTempUserService.findAllByHashGeneratedUniqueResetPasswordID(verificationLink);
				if(tempUser != null){
					User user = userService.findAllByUsername(tempUser.getUsername());
					forgetPasswordTempUserService.delete(tempUser.getId());
					
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
					model.addAttribute("dashboardUser", user);



					return "changePassword";
				}else{
					return "404";
				}
			}else{
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
					return "dashboard";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	
	@RequestMapping(value = "/password-reset-link-msg", method = RequestMethod.POST)
	public String passwordResetLink(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				String email = request.getParameter("email");
				if (email == null || email.equals("")){
					return "index";
				}
				ForgetPasswordTempUser tempUserDupByEmail = forgetPasswordTempUserService.findAllByEmail(email);
				User user = userService.findAllByEmail(email);
				if(tempUserDupByEmail != null){
					model.addAttribute("alreadySent", "true");
					return "passwordResetLink";
				}else{

					String uniqueRgistrationId = SALT + email + new Date().toString();
					String hashGeneratedUniqueRgistrationId = generateHash(uniqueRgistrationId);

					ForgetPasswordTempUser tempUser = new ForgetPasswordTempUser();
					tempUser.setAddedDateTime(new Timestamp(new Date().getTime()));
					tempUser.setEmail(email);
					tempUser.setHashGeneratedUniqueResetPasswordID(hashGeneratedUniqueRgistrationId);
					tempUser.setUsername(user.getUsername());
					tempUser.setUniqueResetPasswordID(uniqueRgistrationId);

					forgetPasswordTempUserService.save(tempUser);
					ClassLoader classLoader = getClass().getClassLoader();
					String emailTemplate = "";
					try {
						emailTemplate = IOUtils.toString(classLoader.getResourceAsStream("PasswordReset.txt"));
					} catch (IOException ex) {

					}

					String verificationLink = "http://localhost:8088" + request.getContextPath() + "/password-reset-link/" + hashGeneratedUniqueRgistrationId;

					emailTemplate = emailTemplate.replace("{fullname}", user.getFullName()).replace("{verificationLink}", verificationLink);
					taskExecutor.execute(new EmailSender(Constant.EMAIL_USER, email, "Password Reset Link ", emailTemplate, "KnowYourScheme"));

					model.addAttribute("alreadySent", "false");
					return "passwordResetLink";
				}
			}else{
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

				List<ApplicationForms> listApplicationFormsForMarque = applicationFormsService.findAll();
				Collections.sort(listApplicationFormsForMarque, new SortApplicationFormsByActualLastModifiedDateDesc());
				model.addAttribute("listApplicationFormsForMarque", listApplicationFormsForMarque);

				List<GovernmentJobs> listOfGovernmentJobsForMarque = governmentJobsService.findAll();
				Collections.sort(listOfGovernmentJobsForMarque, new SortGovernmentJobsByActualLastModifiedDateDesc());
				model.addAttribute("listOfGovernmentJobsForMarque", listOfGovernmentJobsForMarque);

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
					return "dashboard";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	
	@RequestMapping(value = "/password-Check-Change-Password/validate", method = RequestMethod.POST)
	@ResponseBody
	public String passwordCheckChangePassword(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				String password = request.getParameter("password");
				String confirmPassword = request.getParameter("confirmPassword");
				
				if(password == null || password.equals("") || 
						confirmPassword == null || confirmPassword.equals("")){
					return "emptyFieldsFailure";
				}
				if(password.equals(confirmPassword)){
					if(password.length() >= 8){
						return "success";
					}else{
						return "passwordFailure";
					}
				}else{
					return "confirmPasswordFailure";
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
	
	@RequestMapping(value = "/password-reset-success-msg", method = RequestMethod.POST)
	public String passwordResetMsgSuccess(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				if (username == null || username.equals("") || password == null || password.equals("")){
					return "index";
				}
				
				User user = userService.findAllByUsername(username);
				System.out.println(user.getPassword());
				if(user != null){
					String actualPassword = password;
					String saltedPassword = SALT + password;
					password = generateHash(saltedPassword);
					user.setActualPassword(actualPassword);
					user.setPassword(password);
					userService.save(user);
					
					model.addAttribute("passwordResetSuccessAttr", "true");
					return "login";
				}else{
					return "404";
				}
			}else{
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

				List<ApplicationForms> listApplicationFormsForMarque = applicationFormsService.findAll();
				Collections.sort(listApplicationFormsForMarque, new SortApplicationFormsByActualLastModifiedDateDesc());
				model.addAttribute("listApplicationFormsForMarque", listApplicationFormsForMarque);

				List<GovernmentJobs> listOfGovernmentJobsForMarque = governmentJobsService.findAll();
				Collections.sort(listOfGovernmentJobsForMarque, new SortGovernmentJobsByActualLastModifiedDateDesc());
				model.addAttribute("listOfGovernmentJobsForMarque", listOfGovernmentJobsForMarque);

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
					return "dashboard";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	@RequestMapping(value = "/forget-password-email/validate", method = RequestMethod.POST)
	@ResponseBody
	public String forgotPasswordEmailValidate(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				String email = request.getParameter("email");
				if(email == null || email.equals("")){
					return "emptyFieldsFailure";
				}

				User user = userService.findAllByEmail(email);

				if(user != null){	
					return "success";
				}else{
					return "emailFailure";
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
	@RequestMapping(value = "/forget-password", method = RequestMethod.GET)
	public String forgotPassword(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				return "forgotPassword";
			}else{
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
					return "dashboard";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	@RequestMapping(value = "/newsfeed", method = RequestMethod.GET)
	public String newsfeed(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				return "login";
			}else{
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
					return "newsfeed";
				} 
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/signin")
	public String signin(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				return "login";
			}else{
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
					return "dashboard";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboardGet(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				return "login";
			}else{
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
					return "dashboard";
				} 
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	@RequestMapping(value = "/dashboard/{category}", method = RequestMethod.GET)
	public String dashboardGetByCategory(Model model, HttpServletRequest request, @PathVariable("category") String category) {
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
				return "login";
			}else{
				
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
				
				
				String[] categoryArray = category.split("-");
				String searchKeyword = categoryArray[1];
				
				String searchString = searchKeyword;
				if(searchKeyword == null){
					return "404";
				}
				if(searchKeyword.equals("")){
					List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
					Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
					model.addAttribute("recentSchemes", recentSchemes);
					model.addAttribute("category", category);
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
					model.addAttribute("category", category);
					model.addAttribute("searchString", searchString);
					model.addAttribute("searchResult" ,searchResult);
					model.addAttribute("mainSearchResult", listOfGovernmentSchemesOfMainSearch);
					List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
					Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
					model.addAttribute("recentSchemes", recentSchemes);
				}
				
				/*List<StatementGovernmentSchemes> recentSchemes = stateGovernmentSchemesService.findAll();
				Collections.sort(recentSchemes, new SortStateGovernmentSchemesByActualLastModifiedDateDesc());
				model.addAttribute("recentSchemes", recentSchemes);*/
				
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
					return "dashboardByCategory";
				} 
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.POST)
	public String dashboardPost(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				String username = request.getParameter("username");
				String password = request.getParameter("password");

				if(username == null || username.equals("") || password == null || password.equals("")){
					return "index";
				}

				String saltedPassword = SALT + password;
				password = generateHash(saltedPassword);

				User user = userService.findAllByUsernameAndPassword(username, password);
				if(user != null){
					session.setAttribute("knowYourSchemeUser", username);
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
						return "dashboard";
					}
				}else{
					return "404";
				}

			}else{
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
					return "dashboard";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}

	}
	
	

	@RequestMapping(value = "/dashboard/selectedCategory", method = RequestMethod.POST)
	@ResponseBody
	public String selectCategory(Model model, HttpServletRequest request) {
		try{
			String username = request.getParameter("username");
			String category = request.getParameter("category");


			if (username == null || username.equals("") || category == null || category.equals("")) {
				return "Failure";
			}

			UserCategories userCategories = new UserCategories();
			userCategories.setCategory(category);
			userCategories.setUsername(username);

			User user = userService.findAllByUsername(username);
			int categoryCount = user.getSelectedCategoryCount();
			user.setSelectedCategoryCount(categoryCount + 1);

			userCategoriesService.save(userCategories);
			userService.save(user);

			System.out.println("bbbbbbbb if2");
		}catch(Exception e){
			e.printStackTrace();
			return "Failure";
		}
		return "Success";
	}

	@RequestMapping(value = "/dashboard/disSelecetedCategory", method = RequestMethod.POST)
	@ResponseBody
	public String disSelecetedCategory(Model model, HttpServletRequest request){
		try{
			String username = request.getParameter("username");
			String category = request.getParameter("category");
			if (username == null || username.equals("") || category == null || category.equals("")) {
				return "Failure";
			}
			UserCategories userCategories = userCategoriesService.findAllByUsernameAndCategory(username, category);
			User user = userService.findAllByUsername(username);
			int categoryCount = user.getSelectedCategoryCount();
			user.setSelectedCategoryCount(categoryCount - 1);

			userCategoriesService.delete(userCategories.getId());
			userService.save(user);

			System.out.println("bbbbbbbb if2");
		}catch(Exception e){
			e.printStackTrace();
			return "Failure";
		}
		return "Success";
	}

	@RequestMapping(value = "/signin/validate", method = RequestMethod.POST)
	@ResponseBody
	public String validateSignin(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				if(username == null || username.equals("") || password == null || password.equals("")){
					return "emptyFieldsFailure";
				}
				String saltedPassword = SALT + password;
				password = generateHash(saltedPassword);

				User user = userService.findAllByUsername(username);

				if(user != null){	
					if(user.getPassword().equals(password)){
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

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String indexPage(Model model, HttpServletRequest request) {

		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				List<News> news = newsService.findAll();
				List<ApplicationForms> listApplicationFormsForMarque = applicationFormsService.findAll();
				Collections.sort(listApplicationFormsForMarque, new SortApplicationFormsByActualLastModifiedDateDesc());
				model.addAttribute("listApplicationFormsForMarque", listApplicationFormsForMarque);

				List<GovernmentJobs> listOfGovernmentJobsForMarque = governmentJobsService.findAll();
				Collections.sort(listOfGovernmentJobsForMarque, new SortGovernmentJobsByActualLastModifiedDateDesc());
				model.addAttribute("listOfGovernmentJobsForMarque", listOfGovernmentJobsForMarque);

				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("newsListForMarque", news);
				return "index";
			}else{
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
					return "dashboard";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}

	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profilePage(Model model, HttpServletRequest request) {
		try{

			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				List<News> news = newsService.findAll();
				List<ApplicationForms> listApplicationFormsForMarque = applicationFormsService.findAll();
				Collections.sort(listApplicationFormsForMarque, new SortApplicationFormsByActualLastModifiedDateDesc());
				model.addAttribute("listApplicationFormsForMarque", listApplicationFormsForMarque);

				List<GovernmentJobs> listOfGovernmentJobsForMarque = governmentJobsService.findAll();
				Collections.sort(listOfGovernmentJobsForMarque, new SortGovernmentJobsByActualLastModifiedDateDesc());
				model.addAttribute("listOfGovernmentJobsForMarque", listOfGovernmentJobsForMarque);

				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("newsListForMarque", news);
				return "index";
			}else{
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
					return "profile";
				}
			}

		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
	public String editProfilePage(Model model, HttpServletRequest request) {
		try{

			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				List<News> news = newsService.findAll();
				List<ApplicationForms> listApplicationFormsForMarque = applicationFormsService.findAll();
				Collections.sort(listApplicationFormsForMarque, new SortApplicationFormsByActualLastModifiedDateDesc());
				model.addAttribute("listApplicationFormsForMarque", listApplicationFormsForMarque);

				List<GovernmentJobs> listOfGovernmentJobsForMarque = governmentJobsService.findAll();
				Collections.sort(listOfGovernmentJobsForMarque, new SortGovernmentJobsByActualLastModifiedDateDesc());
				model.addAttribute("listOfGovernmentJobsForMarque", listOfGovernmentJobsForMarque);

				Collections.sort(news, new SortTimelineByAddedDateDesc());
				model.addAttribute("newsListForMarque", news);
				return "index";
			}else{
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
					return "editProfile";
				}
			}

		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}


	@RequestMapping(value = "/edit-profile/editPassword", method = RequestMethod.POST)
	@ResponseBody
	public String editPasswordPage(Model model, HttpServletRequest request) {
		try{

			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				return "failure";
			}else{
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				if(username == null || username.equals("") || password == null || password.equals("")){
					return "emptyFieldsFailure";
				}
				String actualPassword = password;
				String saltedPassword = SALT + password;
				password = generateHash(saltedPassword);

				User user = userService.findAllByUsername(username);

				if(user != null){	
					if(user.getPassword().equals(password)){
						user.setActualPassword(actualPassword);
						user.setPassword(password);
						userService.save(user);
						return "success";
					}else{
						return "passwordFailure";
					}
				}else{
					return "usernameFailure";
				}
			}

		}catch(Exception e){
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/hi", method = RequestMethod.GET)
	public String hindiIndexPage(Model model, HttpServletRequest request) {
		return "hi/index";
	}

	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}

	@RequestMapping(value = "/signout", method = RequestMethod.POST)
	public String signout(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("knowYourSchemeUser") == null || session.getAttribute("knowYourSchemeUser") == ""){
				return "404";
			}else{
				session.removeAttribute("knowYourSchemeUser");
				return "index";
			}
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errors", "true");
			return "404";
		}
	}

}
