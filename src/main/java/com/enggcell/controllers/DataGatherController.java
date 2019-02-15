/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enggcell.controllers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.net.*;
import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.enggcell.entities.CentralGovernmentSchemes;
import com.enggcell.entities.CentralGovernmentSchemesInHindi;
import com.enggcell.entities.GovernmentJobs;
import com.enggcell.entities.GovernmentWebsites;
import com.enggcell.entities.News;
import com.enggcell.entities.StatementGovernmentSchemes;
import com.enggcell.services.ApplicationFormsService;
import com.enggcell.services.CentralGovernmentSchemesService;
import com.enggcell.services.CentralGovernmentSchemesServiceInHindi;
import com.enggcell.services.GovernmentJobsService;
import com.enggcell.services.GovernmentWebsitesService;
import com.enggcell.services.NewsService;
import com.enggcell.services.StateGovernmentSchemesService;
import com.enggcell.services.StateGovernmentSchemesServiceInHindi;
import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author 1003
 */
@Scope("request")
@RequestMapping(value = "/dataGather")
@Controller
public class DataGatherController {

	@Autowired
	CentralGovernmentSchemesService centralGovernmentSchemesService;

	@Autowired
	CentralGovernmentSchemesServiceInHindi centralGovernmentSchemesServiceInHindi;

	@Autowired
	StateGovernmentSchemesService stateGovernmentSchemesService;

	@Autowired
	StateGovernmentSchemesServiceInHindi stateGovernmentSchemesServiceInHindi;

	@Autowired
	NewsService newsService;

	@Autowired
	ApplicationFormsService applicationFormsService;

	@Autowired
	GovernmentWebsitesService governmentWebsitesService;

	@Autowired
	GovernmentJobsService governmentJobsService;

	@Autowired
	ThreadPoolTaskExecutor taskExecutor;

	public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
	public static final String GOOGLE_SEARCH_URL2 = "https://www.google.co.in/finance/company_news";
	public static final String GOOGLE_TRANSLATE_URL_HINDI = "https://translate.google.com/#en/hi/";

	@RequestMapping(value = "/Central-Government", method = RequestMethod.GET)
	public String getCentralGovSchemes(Model model, HttpServletRequest request) {
		//model.addAttribute("indexCompanies", companyService.findAll());

		ArrayList<String> categoryOfCentralGovtSchemes = new ArrayList<String>();
		ArrayList<CentralGovernmentSchemes> listOfCentralGovernmentSchemes = new ArrayList<CentralGovernmentSchemes>();
		try{
			//HttpSession session = request.getSession();
			//if(session.getAttribute("ensuraaUser") == null || session.getAttribute("ensuraaUser") == ""){



			try{

				String searchURL2 = "https://www.indiahub.com/schemes-and-services";
				URLEncoder.encode(searchURL2, "UTF-8");
				System.out.println(searchURL2 + "----");
				Document doc2 = Jsoup.connect(searchURL2).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
				/*if(doc2 == null && compCode.getPrimaryExchange().contains("NYSE")){
								String searchURL3[] = searchURL2.split(" ");
								doc2 = Jsoup.connect(searchURL3[0]).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
							}*/
				//System.out.println(doc2 + " qqqqqqq");

				//System.out.println(doc2);

				//Element masthead2 = doc2.select("div.knowledge-finance-wholepage__section").first();
				Element gtrayHeaderNews = doc2.select("ul.nav-stacked").first();
				if(gtrayHeaderNews != null){
					//Element parentPatentDiv = doc2.select("g-section-with-header").first();
					Elements parentDiv = gtrayHeaderNews.select("li");
					for(Element ele : parentDiv){
						Element category = ele.select("a").first();
						categoryOfCentralGovtSchemes.add(category.text());
						//duplicate check
						//if(newsDataDuplicateCheck == null){

						//}

					}
				}
				Element gtrayHeaderNews2 = doc2.select("div.tab-content").first();
				if(gtrayHeaderNews2 != null){
					//Element parentPatentDiv = doc2.select("g-section-with-header").first();
					Elements parentDiv = gtrayHeaderNews2.select("div.tab-pane");
					int categoryIndex = -1;
					for(Element ele : parentDiv){
						categoryIndex++;
						Element tabPaneUl = ele.select("ul").first();
						System.out.println("------------------------");
						if(tabPaneUl != null){
							Elements tabPaneLi = tabPaneUl.select("li");
							for(Element ele2 : tabPaneLi){

								Element schemeLink = ele2.select("a").first();
								//schemeLink.attr("href");
								Element schemeName = schemeLink.select("strong").first(); 
								//schemeName.text();
								CentralGovernmentSchemes centralGovernmentScheme = new CentralGovernmentSchemes();
								centralGovernmentScheme.setTypeOfScheme("Central Government");
								centralGovernmentScheme.setSchemeCategory(categoryOfCentralGovtSchemes.get(categoryIndex));


								if(schemeName != null && schemeLink != null){
									System.out.println("Scheme Name - " + " - " + schemeName.text());
									if(!schemeName.text().trim().equals("") && !schemeName.text().trim().equals("A")){
										centralGovernmentScheme.setSchemeName(schemeName.text());
									}
									
									System.out.println("Scheme Link - " + " - " + schemeLink.attr("href"));
									centralGovernmentScheme.setSchemeLink(schemeLink.attr("href"));
									CentralGovernmentSchemes centralGovernmentScheme2 = centralGovernmentSchemesService.findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(schemeName.text(), "Central Government", schemeLink.attr("href"));
									System.out.println(centralGovernmentScheme2 + " ############## ");
									if(centralGovernmentScheme2 == null){
										Element innerUl = ele2.select("ul").first();
										if(innerUl != null){
											Elements innerUlLi = innerUl.select("li");
											int i = 1;
											for(Element innerLi : innerUlLi){
												Element overviewLink = innerLi.select("a").first();
												System.out.println(overviewLink.text() + " - " + overviewLink.attr("href"));

												if(i == 1){
													//overview
													centralGovernmentScheme.setOverviewLink(overviewLink.attr("href"));
												}else if(i == 2){
													//did you know
													centralGovernmentScheme.setDidYouKnowLink(overviewLink.attr("href"));
												}else if(i == 3){
													//faq
													centralGovernmentScheme.setFaqLink(overviewLink.attr("href"));
												}else{
													//related news
													centralGovernmentScheme.setRelatedNewsLink(overviewLink.attr("href"));
												}
												centralGovernmentScheme.setAddedDate(new java.sql.Date(new Date().getTime()));
												i++;
												/*Element didYouKnowLink
															Element faqLink
															Element relatedNewsLink*/
											}
										}

										if(centralGovernmentScheme.getAddedDate() != null &&
												centralGovernmentScheme.getDidYouKnowLink() != null &&
												centralGovernmentScheme.getFaqLink() != null &&
												centralGovernmentScheme.getOverviewLink() != null &&
												centralGovernmentScheme.getRelatedNewsLink() != null &&
												centralGovernmentScheme.getSchemeCategory() != null &&
												centralGovernmentScheme.getSchemeLink() != null &&
												centralGovernmentScheme.getSchemeName() != null &&
												centralGovernmentScheme.getTypeOfScheme() != null){

											//https://www.indiahub.com/schemes-and-services/Social-Assistance-Programme/National-Family-Benefit-Scheme

											String searchUrl4 = centralGovernmentScheme.getSchemeLink();
											URLEncoder.encode(searchUrl4, "UTF-8");
											if(!searchUrl4.contains("'")){
												Document doc3 = null;
												try{
													doc3 = Jsoup.connect(searchUrl4).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
												}catch(Exception e){
													doc3 = null;
												}
												if(doc3 != null){
													//System.out.println(doc3);

													//Element masthead2 = doc2.select("div.knowledge-finance-wholepage__section").first();
													Element gtrayHeaderNews3 = doc3.select("div.single-post").first();
													if(gtrayHeaderNews3 != null){
														//Element parentPatentDiv = doc2.select("g-section-with-header").first();
														Element parentDiv2 = gtrayHeaderNews3.select("div.post-content-area").first();
														Element parentDiv3 = parentDiv2.select("div.post-featured-image").first();
														Element imageLink = parentDiv3.select("a").first();

														Element parentDiv4 = parentDiv2.select("div.entry-content").first();
														/*Element heading3First = parentDiv4.select("h3").first();
																	if(heading3First.text().equalsIgnoreCase("overview")){

																	}else if(heading3First.text().equalsIgnoreCase("eligibility")){

																	}*/
														String detailedInfo = parentDiv4.html();
														centralGovernmentScheme.setDetailedInfo(detailedInfo);
														centralGovernmentScheme.setImageLink(imageLink.attr("href"));
														//System.out.println(detailedInfo);
													}
												}
											}
											centralGovernmentSchemesService.save(centralGovernmentScheme);
											listOfCentralGovernmentSchemes.add(centralGovernmentScheme);
										}
									}
								}

								//duplicate check
								//if(newsDataDuplicateCheck == null){

								//}


							}
						}
						System.out.println("---------------------------------------------------------------------------------");
					}
				}
				if(categoryOfCentralGovtSchemes != null){
					for(String categoryOfCentralGovtScheme : categoryOfCentralGovtSchemes){
						System.out.println(categoryOfCentralGovtScheme);
					}
				}
				if(listOfCentralGovernmentSchemes != null){
					System.out.println(listOfCentralGovernmentSchemes);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//model.addAttribute("errors", "");	
			return "DataGather";
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errors", "true");
			return "DataGather";
		}
	}

	@RequestMapping(value = "/Central-Government/hi", method = RequestMethod.GET)
	public String getCentralGovSchemesInHindi(Model model, HttpServletRequest request) {
		//model.addAttribute("indexCompanies", companyService.findAll());

		ArrayList<String> categoryOfCentralGovtSchemes = new ArrayList<String>();
		ArrayList<CentralGovernmentSchemesInHindi> listOfCentralGovernmentSchemes = new ArrayList<CentralGovernmentSchemesInHindi>();
		try{
			//HttpSession session = request.getSession();
			//if(session.getAttribute("ensuraaUser") == null || session.getAttribute("ensuraaUser") == ""){



			try{

				String searchURL2 = "https://www.indiahub.com/schemes-and-services";
				URLEncoder.encode(searchURL2, "UTF-8");
				System.out.println(searchURL2 + "----");
				Document doc2 = Jsoup.connect(searchURL2).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
				Element gtrayHeaderNews = doc2.select("ul.nav-stacked").first();
				if(gtrayHeaderNews != null){
					//Element parentPatentDiv = doc2.select("g-section-with-header").first();
					Elements parentDiv = gtrayHeaderNews.select("li");
					for(Element ele : parentDiv){
						Element category = ele.select("a").first();

						String translateUrl = GOOGLE_TRANSLATE_URL_HINDI + category.text();
						Document doc2translateUrl = Jsoup.connect(translateUrl).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
						Element gtrayHeaderNewsTranslateUrl = doc2translateUrl.getElementById("result_box");

						System.out.println("******** + " + doc2translateUrl.text() + " ******** " + gtrayHeaderNewsTranslateUrl.text());

						categoryOfCentralGovtSchemes.add(gtrayHeaderNewsTranslateUrl.text());
						//duplicate check
						//if(newsDataDuplicateCheck == null){

						//}

					}
				}
				Element gtrayHeaderNews2 = doc2.select("div.tab-content").first();
				if(gtrayHeaderNews2 != null){
					//Element parentPatentDiv = doc2.select("g-section-with-header").first();
					Elements parentDiv = gtrayHeaderNews2.select("div.tab-pane");
					int categoryIndex = -1;
					for(Element ele : parentDiv){
						categoryIndex++;
						Element tabPaneUl = ele.select("ul").first();
						System.out.println("------------------------");
						if(tabPaneUl != null){
							Elements tabPaneLi = tabPaneUl.select("li");
							for(Element ele2 : tabPaneLi){

								Element schemeLink = ele2.select("a").first();
								//schemeLink.attr("href");
								Element schemeName = schemeLink.select("strong").first(); 
								//schemeName.text();
								CentralGovernmentSchemesInHindi centralGovernmentScheme = new CentralGovernmentSchemesInHindi();
								centralGovernmentScheme.setTypeOfScheme("केंद्र सरकार");
								String schemeCategoryInHindi = categoryOfCentralGovtSchemes.get(categoryIndex);
								centralGovernmentScheme.setSchemeCategory(schemeCategoryInHindi);


								if(schemeName != null && schemeLink != null){
									System.out.println("Scheme Name - " + " - " + schemeName.text());

									String translateUrl2 = GOOGLE_TRANSLATE_URL_HINDI + schemeName.text();
									Document doc2translateUrl2 = Jsoup.connect(translateUrl2).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
									Element gtrayHeaderNewsTranslateUrl2 = doc2translateUrl2.getElementById("result_box");
									String schemeNameInHindi = gtrayHeaderNewsTranslateUrl2.text();

									centralGovernmentScheme.setSchemeName(schemeNameInHindi);
									System.out.println("Scheme Link - " + " - " + schemeLink.attr("href"));
									centralGovernmentScheme.setSchemeLink(schemeLink.attr("href"));
									CentralGovernmentSchemesInHindi centralGovernmentScheme2 = centralGovernmentSchemesServiceInHindi.findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(schemeName.text(), "Central Government", schemeLink.attr("href"));
									System.out.println(centralGovernmentScheme2 + " ############## ");
									if(centralGovernmentScheme2 == null){
										Element innerUl = ele2.select("ul").first();
										if(innerUl != null){
											Elements innerUlLi = innerUl.select("li");
											int i = 1;
											for(Element innerLi : innerUlLi){
												Element overviewLink = innerLi.select("a").first();
												System.out.println(overviewLink.text() + " - " + overviewLink.attr("href"));

												if(i == 1){
													//overview
													centralGovernmentScheme.setOverviewLink(overviewLink.attr("href"));
												}else if(i == 2){
													//did you know
													centralGovernmentScheme.setDidYouKnowLink(overviewLink.attr("href"));
												}else if(i == 3){
													//faq
													centralGovernmentScheme.setFaqLink(overviewLink.attr("href"));
												}else{
													//related news
													centralGovernmentScheme.setRelatedNewsLink(overviewLink.attr("href"));
												}
												centralGovernmentScheme.setAddedDate(new java.sql.Date(new Date().getTime()));
												i++;
												/*Element didYouKnowLink
															Element faqLink
															Element relatedNewsLink*/
											}
										}

										if(centralGovernmentScheme.getAddedDate() != null &&
												centralGovernmentScheme.getDidYouKnowLink() != null &&
												centralGovernmentScheme.getFaqLink() != null &&
												centralGovernmentScheme.getOverviewLink() != null &&
												centralGovernmentScheme.getRelatedNewsLink() != null &&
												centralGovernmentScheme.getSchemeCategory() != null &&
												centralGovernmentScheme.getSchemeLink() != null &&
												centralGovernmentScheme.getSchemeName() != null &&
												centralGovernmentScheme.getTypeOfScheme() != null){

											//https://www.indiahub.com/schemes-and-services/Social-Assistance-Programme/National-Family-Benefit-Scheme

											String searchUrl4 = centralGovernmentScheme.getSchemeLink();
											URLEncoder.encode(searchUrl4, "UTF-8");
											if(!searchUrl4.contains("'")){
												Document doc3 = null;
												try{
													doc3 = Jsoup.connect(searchUrl4).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
												}catch(Exception e){
													doc3 = null;
												}
												if(doc3 != null){
													//System.out.println(doc3);

													//Element masthead2 = doc2.select("div.knowledge-finance-wholepage__section").first();
													Element gtrayHeaderNews3 = doc3.select("div.single-post").first();
													if(gtrayHeaderNews3 != null){
														//Element parentPatentDiv = doc2.select("g-section-with-header").first();
														Element parentDiv2 = gtrayHeaderNews3.select("div.post-content-area").first();
														Element parentDiv3 = parentDiv2.select("div.post-featured-image").first();
														Element imageLink = parentDiv3.select("a").first();

														Element parentDiv4 = parentDiv2.select("div.entry-content").first();
														/*Element heading3First = parentDiv4.select("h3").first();
																	if(heading3First.text().equalsIgnoreCase("overview")){

																	}else if(heading3First.text().equalsIgnoreCase("eligibility")){

																	}*/
														String detailedInfo = parentDiv4.html();

														StringBuffer detailedInfoInHindi = new StringBuffer(detailedInfo);
														StringBuffer detailedInfoInHindi2 = new StringBuffer("");
														if(detailedInfoInHindi.length() > 5000){
															for(int i = 0; i < detailedInfoInHindi.length();){
																if(detailedInfoInHindi.substring(i).length() > 5000){
																	String translateString = detailedInfoInHindi.substring(i, 5000);

																	String translateUrl3 = GOOGLE_TRANSLATE_URL_HINDI + translateString;
																	Document doc2translateUrl3 = Jsoup.connect(translateUrl3).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
																	Element gtrayHeaderNewsTranslateUrl3 = doc2translateUrl3.getElementById("result_box");
																	detailedInfoInHindi2.append(gtrayHeaderNewsTranslateUrl3.text());

																	i = i + 5000;
																}else{
																	String translateString = detailedInfoInHindi.substring(i);
																	String translateUrl3 = GOOGLE_TRANSLATE_URL_HINDI + translateString;
																	Document doc2translateUrl3 = Jsoup.connect(translateUrl3).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").get();
																	Element gtrayHeaderNewsTranslateUrl3 = doc2translateUrl3.getElementById("result_box");
																	detailedInfoInHindi2.append(gtrayHeaderNewsTranslateUrl3.text());
																	i = i + detailedInfoInHindi.substring(i).length();
																}
															}
														}else{

														}


														centralGovernmentScheme.setDetailedInfo(detailedInfoInHindi2.toString());
														centralGovernmentScheme.setImageLink(imageLink.attr("href"));
														//System.out.println(detailedInfo);
													}
												}
											}
											centralGovernmentSchemesServiceInHindi.save(centralGovernmentScheme);
											listOfCentralGovernmentSchemes.add(centralGovernmentScheme);
										}
									}
								}

								//duplicate check
								//if(newsDataDuplicateCheck == null){

								//}


							}
						}
						System.out.println("---------------------------------------------------------------------------------");
					}
				}
				if(categoryOfCentralGovtSchemes != null){
					for(String categoryOfCentralGovtScheme : categoryOfCentralGovtSchemes){
						System.out.println(categoryOfCentralGovtScheme);
					}
				}
				if(listOfCentralGovernmentSchemes != null){
					System.out.println(listOfCentralGovernmentSchemes);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//model.addAttribute("errors", "");	
			return "DataGather";
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errors", "true");
			return "DataGather";
		}
	}

	@RequestMapping(value = "/News", method = RequestMethod.GET)
	public String getNews(Model model, HttpServletRequest request) {
		try{
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
			return "DataGather";
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errors", "true");
			return "DataGather";
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

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logoutPost(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("ensuraaUser") != null || !session.getAttribute("ensuraaUser").equals("")){
				session.removeAttribute("ensuraaUser");
				return "index";
			}else{
				return "index";
			}
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errors", "true");
			return "index";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGet(Model model, HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("ensuraaUser") != null || !session.getAttribute("ensuraaUser").equals("")){
				session.removeAttribute("ensuraaUser");
				return "index";
			}else{
				return "index";
			}
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errors", "true");
			return "index";
		}
	}

}
