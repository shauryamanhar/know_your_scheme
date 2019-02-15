<!DOCTYPE html>
<%@page import="org.springframework.web.context.ContextLoader"%>
<%@page import="java.io.File"%>
<%@page import="com.enggcell.entities.Cities"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="com.enggcell.entities.Companies"%>
<%@page import="java.util.List"%>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="<%= request.getContextPath() %>/assets/img/icon.png" type="image/png" sizes="16x16">
	<meta name="description" content="EnggCell is India's largest job fair organizing portal, Which connects companies, colleges and students through job fair.">
	<meta name="keywords" content="EnggCell, Engineer Cell, Job Fair, Job Fair registration, Job fair in pune, hyderabad, chennai, Bhopal, delhi, noida, gaziabad, gurgaon, banglore, mumbai." />
    <meta name="description" content="Engineer placement cell is job fair organizer in collaboration with well reputed companies in India.Our mission is to provide jobs easily to fresh graduates. EnggCell organizes 4 Job fair in a year.">
    <meta name="author" content="enggCell">

    <title>EnggCell- India's largest and trusted job fairs</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath() %>/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%= request.getContextPath() %>/assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type='text/css'>
    <!-- Theme CSS -->
    <link href="<%= request.getContextPath() %>/assets/css/agency.min.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js" integrity="sha384-0s5Pv64cNZJieYFkXYOTId2HMA2Lfb6q2nAcx2n0RTLUnCAoTTsS0nKEO27XyKcY" crossorigin="anonymous"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js" integrity="sha384-ZoaMbDF+4LeFxg6WdScQ9nnR1QC2MIRxA1O9KWEXQwns1G8UNyIEZIQidzb0T1fo" crossorigin="anonymous"></script>
    <![endif]-->

</head>

<body id="page-top" class="index">
<%
List<Companies> indexCompanies = null;
//HashSet<String> cities = new HashSet<String>();
//indexCities
List<Cities> cities = null;
try{
if(request.getAttribute("indexCities") != null && !request.getAttribute("indexCities").equals("")){
	cities = (List<Cities>)request.getAttribute("indexCities");

}

if (request.getAttribute("indexCompanies") != null && !request.getAttribute("indexCompanies").equals("")) {
	indexCompanies = (List<Companies>) request.getAttribute("indexCompanies");
    
}
}catch(Exception e){
	e.printStackTrace();
}

%>
    <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">EnggCell</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#services">Fairs</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#portfolio">Copmanies</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#about">About</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#team">Gallary</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#contact">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <!-- Header -->
    <header>
        <div class="container">
            <div class="intro-text">
                <div class="intro-lead-in">Welcome To enggCell</div>
                <div class="intro-heading">The mega job fair</div>
                <a href="#services" class="page-scroll btn btn-default btn-lg">Apply Now</a>
            </div>
        </div>
    </header>

    <!-- Services Section -->
    <section id="services">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">What you need?</h2>
                </div>
            </div>
            <div class="row text-center">
                <div class="col-md-4">
				<a href="#portfolio" style="text-decoration:none;">
                    <span class="fa-stack fa-4x">
						<i class="fa fa-bullhorn" aria-hidden="true"></i>
                    </span>
                    <h4 class="service-heading">Current Job Fairs</h4>
                    <p class="text-muted">Apply for current job fairs and know the participating colleges and companies near you.</p>
                </a>
				</div>
                <div class="col-md-4">
				<a href="#portfolio" style="text-decoration:none;">
                    <span class="fa-stack fa-4x">
						<i class="fa fa-calendar" aria-hidden="true"></i>
                    </span>
                    <h4 class="service-heading">UpComing Job Fairs</h4>
                    <p class="text-muted">Check for the upcoming job fairs in your city.</p>
                </a>
				</div>
                <div class="col-md-4">
				<a href="<%= request.getContextPath() %>/register/adddetails" style="text-decoration:none;">
                    <span class="fa-stack fa-4x">
						<i class="fa fa-graduation-cap" aria-hidden="true"></i>
                    </span>
                    <h4 class="service-heading">Apply Now</h4>
                    <p class="text-muted">Apply Now for the upcoming job fairs in Bhopal, Pune, Hyderabad, Chennai & Ghaziabad.</p>
                </a>
				</div>
            </div>
        </div>
    </section>

    <!-- Portfolio Grid Section -->
    <section id="portfolio" class="bg-light-gray">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Companies</h2>
                    <h3 class="section-subheading text-muted">Participating in fair</h3>
                </div>
            </div>
            <div class="row">
                <%
                if(cities != null){
                	int portfolio = 0;
                for(Cities city : cities){
                	portfolio++;
                %>
                <div class="col-md-4 col-sm-6 portfolio-item">
                    <a href="#portfolioModal<%=portfolio %>" class="portfolio-link" data-toggle="modal">
                        <div class="portfolio-hover">
                            <div class="portfolio-hover-content">
                                <i class="fa fa-plus fa-3x"></i>
                            </div>
                        </div>
                        <%
                        try{
                        	String ApplicationPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("");
                        	//File f1 = new File(request.getContextPath() + "/assets/admin/Portfolio/"+ city.getCity() + ".png");
                        	//File f1 = new File(request.getServletContext().getRealPath("/") + "/Resumes/" + city.getCity() + ".png");
                        	//File f1 = new File(ApplicationPath + "\\Resumes\\" + city.getCity() + ".png");
                        	File f1 = new File(request.getContextPath() + "/assets/admin/Portfolio/" + city.getCity() + ".png");
                        	File f2 = new File(request.getContextPath() + "/assets/admin/Portfolio/"+ city.getCity() + ".jpg");
                        	File f3 = new File(request.getContextPath() + "/assets/admin/Portfolio/"+ city.getCity() + ".jpeg");
                        	File f4 = new File(request.getContextPath() + "/assets/admin/Portfolio/"+ city.getCity() + ".gif");
                        	
                        	
                            if(f1.exists()){
                            %>
                            <img src='<%=request.getContextPath() + "/assets/admin/Portfolio/" + city.getCity()%>.png' class="img-responsive" alt="">
                            <%
                            }else if(f2.exists()){
                            %>
                            <img src='<%=request.getContextPath() + "/assets/admin/Portfolio/" + city.getCity()%>.jpg' class="img-responsive" alt="">
                            <%	
                            }else if(f3.exists()){
                            %>
                            <img src='<%=request.getContextPath() + "/assets/admin/Portfolio/" + city.getCity()%>.jpeg' class="img-responsive" alt="">
                            <%	
                            }else{
                            %>
                            <img src='<%=request.getContextPath() + "/assets/admin/Portfolio/" + city.getCity()%>.gif' class="img-responsive" alt="">
                            <%	
                            }
                        }catch(Exception e){
                        	e.printStackTrace();
                        }
                        %>
                        
                    </a>
                    <div class="portfolio-caption">
                        <h4><%=city.getCity() %></h4>
                        <p class="text-muted"><%=city.getCollege() + ", " + city.getAddress() %></p>
                    </div>
                </div>
                <%
                }
                }
                %>
            </div>
        </div>
    </section>

    <!-- About Section -->
    <section id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">About</h2>
                    <h3 class="section-subheading text-muted">Engineering Placement cell</h3>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <ul class="timeline">
                        <li>
                            <div class="timeline-image">
                                <img class="img-circle img-responsive" src="<%= request.getContextPath() %>/assets/img/about/1.jpg" alt="">
                            </div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h4>2017</h4>
                                    <h4 class="subheading">Our Humble Beginnings</h4>
                                </div>
                                <div class="timeline-body">
                                    <p class="text-muted">Engineer placement cell is job fair organizer in collaboration with well reputed companies in India. <br />
									Our mission is to provide jobs easily to fresh graduates. EnggCell organizes 4 Job fair in a year.</p>
                                </div>
                            </div>
                        </li>
                        <li class="timeline-inverted">
                            <div class="timeline-image">
								
                                <img class="img-circle img-responsive" src="<%= request.getContextPath() %>/assets/img/about/4.jpg" alt="">
								</a>
                            </div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h4>June 2017</h4>
                                    <h4 class="subheading">Job Fair- Record placement</h4>
                                </div>
                                <div class="timeline-body">
                                    <p class="text-muted">920 out of 1000 studens were placed in job fair organized in june.</p>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="timeline-image">
								<a href="<%= request.getContextPath() %>/register/">
                                <img class="img-circle img-responsive" src="<%= request.getContextPath() %>/assets/img/about/3.jpg" alt="">
								</a>
							</div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h4>January 2018</h4>
                                    <h4 class="subheading">UpComing Job fair</h4>
                                </div>
                                <div class="timeline-body">
                                    <p class="text-muted">EnggCell is organizing job fairs in 5 major cities in India. Apply for the fair in cities near you. </p>
                                </div>
                            </div>
                        </li>
                        <li class="timeline-inverted">
                            <div class="timeline-image">
                                <h4>Be Part
                                    <br>Of Our
                                    <br>Story!</h4>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <!-- Team Section -->
    <section id="team" class="bg-light-gray">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Gallary</h2>
                </div>
            </div>
			<section style="padding: 0px;">
			  <div class="container gal-container">
				<div class="col-md-8 col-sm-12 co-xs-12 gal-item">
				  <div class="box">
					<a href="#" data-toggle="modal" data-target="#1">
					  <img src="<%= request.getContextPath() %>/assets/img/jf/jf10.png">
					</a>
					<div class="modal fade" id="1" tabindex="-1" role="dialog">
					  <div class="modal-dialog" role="document">
						<div class="modal-content">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						  <div class="modal-body">
							<img src="<%= request.getContextPath() %>/assets/img/jf/jf10.png">
						  </div>
							<div class="col-md-12 description">
							  <h4>Lunch for volunteers</h4>
							</div>
						</div>
					  </div>
					</div>
				  </div>
				</div>
				<div class="col-md-4 col-sm-6 co-xs-12 gal-item">
				  <div class="box">
					<a href="#" data-toggle="modal" data-target="#2">
					  <img src="<%= request.getContextPath() %>/assets/img/jf/jf1.png">
					</a>
					<div class="modal fade" id="2" tabindex="-1" role="dialog">
					  <div class="modal-dialog" role="document">
						<div class="modal-content">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						  <div class="modal-body">
							<img src="<%= request.getContextPath() %>/assets/img/jf/jf1.png">
						  </div>
							<div class="col-md-12 description">
							  <h4>Information Desk</h4>
							</div>
						</div>
					  </div>
					</div>
				  </div>
				</div>
				<div class="col-md-4 col-sm-6 co-xs-12 gal-item">
				  <div class="box">
					<a href="#" data-toggle="modal" data-target="#3">
					  <img src="<%= request.getContextPath() %>/assets/img/jf/jf9.png">
					</a>
					<div class="modal fade" id="3" tabindex="-1" role="dialog">
					  <div class="modal-dialog" role="document">
						<div class="modal-content">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						  <div class="modal-body">
							<img src="<%= request.getContextPath() %>/assets/img/jf/jf9.png">
						  </div>
							<div class="col-md-12 description">
							  <h4>Interview Block 1</h4>
							</div>
						</div>
					  </div>
					</div>
				  </div>
				</div>
				<div class="col-md-4 col-sm-6 co-xs-12 gal-item">
				  <div class="box">
					<a href="#" data-toggle="modal" data-target="#4">
					  <img src="<%= request.getContextPath() %>/assets/img/jf/jf5.png">
					</a>
					<div class="modal fade" id="4" tabindex="-1" role="dialog">
					  <div class="modal-dialog" role="document">
						<div class="modal-content">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						  <div class="modal-body">
							<img src="<%= request.getContextPath() %>/assets/img/jf/jf5.png">
						  </div>
							<div class="col-md-12 description">
							  <h4>Interview Block 2</h4>
							</div>
						</div>
					  </div>
					</div>
				  </div>
				</div>
				<div class="col-md-4 col-sm-6 co-xs-12 gal-item">
				  <div class="box">
					<a href="#" data-toggle="modal" data-target="#5">
					  <img src="<%= request.getContextPath() %>/assets/img/jf/jf4.png">
					</a>
					<div class="modal fade" id="5" tabindex="-1" role="dialog">
					  <div class="modal-dialog" role="document">
						<div class="modal-content">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						  <div class="modal-body">
							<img src="<%= request.getContextPath() %>/assets/img/jf/jf4.png">
						  </div>
							<div class="col-md-12 description">
							  <h4>Written</h4>
							</div>
						</div>
					  </div>
					</div>
				  </div>
				</div>
				<div class="col-md-4 col-sm-6 co-xs-12 gal-item">
				  <div class="box">
					<a href="#" data-toggle="modal" data-target="#6">
					  <img src="<%= request.getContextPath() %>/assets/img/jf/jf7.png">
					</a>
					<div class="modal fade" id="6" tabindex="-1" role="dialog">
					  <div class="modal-dialog" role="document">
						<div class="modal-content">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						  <div class="modal-body">
							<img src="<%= request.getContextPath() %>/assets/img/jf/jf7.png">
						  </div>
							<div class="col-md-12 description">
							  <h4>Written</h4>
							</div>
						</div>
					  </div>
					</div>
				  </div>
				</div>
				<div class="col-md-4 col-sm-6 co-xs-12 gal-item">
				  <div class="box">
					<a href="#" data-toggle="modal" data-target="#7">
					  <img src="<%= request.getContextPath() %>/assets/img/jf/jf6.png">
					</a>
					<div class="modal fade" id="7" tabindex="-1" role="dialog">
					  <div class="modal-dialog" role="document">
						<div class="modal-content">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						  <div class="modal-body">
							<img src="<%= request.getContextPath() %>/assets/img/jf/jf6.png">
						  </div>
							<div class="col-md-12 description">
							  <h4>Interview Room</h4>
							</div>
						</div>
					  </div>
					</div>
				  </div>
				</div>
				<div class="col-md-4 col-sm-6 co-xs-12 gal-item">
				  <div class="box">
					<a href="#" data-toggle="modal" data-target="#8">
					  <img src="<%= request.getContextPath() %>/assets/img/jf/jf2.png">
					</a>
					<div class="modal fade" id="8" tabindex="-1" role="dialog">
					  <div class="modal-dialog" role="document">
						<div class="modal-content">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						  <div class="modal-body">
							<img src="<%= request.getContextPath() %>/assets/img/jf/jf2.png">
						  </div>
							<div class="col-md-12 description">
							  <h4>Interview Room</h4>
							</div>
						</div>
					  </div>
					</div>
				  </div>
				</div>
				<div class="col-md-4 col-sm-6 co-xs-12 gal-item">
				  <div class="box">
					<a href="#" data-toggle="modal" data-target="#9">
					  <img src="<%= request.getContextPath() %>/assets/img/jf/jf8.png">
					</a>
					<div class="modal fade" id="9" tabindex="-1" role="dialog">
					  <div class="modal-dialog" role="document">
						<div class="modal-content">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						  <div class="modal-body">
							<img src="<%= request.getContextPath() %>/assets/img/jf/jf8.png">
						  </div>
							<div class="col-md-12 description">
							  <h4>Interview Room</h4>
							</div>
						</div>
					  </div>
					</div>
				  </div>
				</div>
				<div class="col-md-8 col-sm-12 co-xs-12 gal-item">
				  <div class="box">
					<a href="#" data-toggle="modal" data-target="#10">
					  <img src="<%= request.getContextPath() %>/assets/img/jf/jf3.png">
					</a>
					<div class="modal fade" id="10" tabindex="-1" role="dialog">
					  <div class="modal-dialog" role="document">
						<div class="modal-content">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						  <div class="modal-body">
							<img src="<%= request.getContextPath() %>/assets/img/jf/jf3.png">
						  </div>
							<div class="col-md-12 description">
							  <h4>Waiting Hall</h4>
							</div>
						</div>
					  </div>
					</div>
				  </div>
				</div>
			  </div>
			</section>
        </div>
    </section>

    <!-- Contact Section -->
    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Contact Us</h2>
                    <h3 class="section-subheading text-muted">Any queries related to companies, colleges and student participant, Please let us know. We will be happy to help you. </h3>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <form name="sentMessage" id="contactForm" novalidate>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Your Name *" id="name" required data-validation-required-message="Please enter your name.">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control" placeholder="Your Email *" id="email" required data-validation-required-message="Please enter your email address.">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input type="tel" class="form-control" placeholder="Your Phone *" id="phone" required data-validation-required-message="Please enter your phone number.">
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <textarea class="form-control" placeholder="Your Message *" id="message" required data-validation-required-message="Please enter a message."></textarea>
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                            <div class="col-lg-12 text-center">
                                <div id="success"></div>
                                <button type="submit" class="btn btn-xl">Send Message</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
	
    <div class="banner">

        <div class="container">

            <div class="row">
                <div class="col-lg-12" style="text-align: center; padding-top: 30px;">
                    <ul class="list-inline banner-social-buttons">
                        <li>
                            <a href="https://www.instagram.com/enggcell/" class="btn btn-default btn-lg"><i class="fa fa-instagram fa-fw"></i> <span class="network-name">Instagram</span></a>
                        </li>
                        <li>
                            <a href="https://www.facebook.com/placementenggcell/" class="btn btn-default btn-lg"><i class="fa fa-facebook fa-fw"></i> <span class="network-name">Facebook</span></a>
                        </li>
                        <li>
                            <a href="https://www.linkedin.com/company/enggcell/" class="btn btn-default btn-lg"><i class="fa fa-linkedin fa-fw"></i> <span class="network-name">LinkedIn</span></a>
                        </li>
                    </ul>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.banner -->

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <span class="copyright">Copyright &copy; EnggCell 2017. All Rights Reserved</span>
                </div>
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                    <ul class="list-inline quicklinks">
                        <li><a href="#">Privacy Policy</a>
                        </li>
                        <li><a href="#">Terms of Use</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>

    <!-- Portfolio Modals -->
    <!-- Use the modals below to showcase details about your portfolio projects! -->

    <!-- Portfolio Modal 1 -->
    <%
    
    if(cities != null){
    	int portfolioAgain = 0;
    	/*
    	while (iterator.hasNext()){
    		portfolioAgain++;
    		String city = iterator.next().toString().toLowerCase();*/
    		for(Cities city : cities){
    			portfolioAgain++;
    %>
    <div class="portfolio-modal modal fade" id="portfolioModal<%=portfolioAgain %>" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="close-modal" data-dismiss="modal">
                    <div class="lr">
                        <div class="rl">
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-lg-offset-2">
                            <div class="modal-body">
                                <!-- Project Details Go Here -->
                                <h2>Participating Companies</h2>
								<h3>OVERVIEW</h3>
								<p>
								 Group of Institutions, Greater Noida in association with EnggCell, Noida organising MEGA JOB FAIR on <%=city.getDate() %> from <%=city.getTime() %> 35+ Companies are coming for the recruitment process of B.Tech(All branches), BCA, BBA, MBA, MCA and all the Graduates(BA/B.Sc/B.Com etc) of 2016, 2017 & 2018 (Pursuing)batches.</p>
								
								<h3 style="color: orange;">Major Attractions</h3>
								<p style="font-size:15px;">	
								-  Core Companies for all the branches . <br />
								-  Bulk Recruitments<br />
								-  2000+ Openings<br />
								-  Companies for all Technical / Non Technical and Management Students.<br />
								-  Limited Seats.<br />
								-  Only first <%=city.getAvailSeats() %> Registrations will be eligible . Registrations will be closed after first <%=city.getAvailSeats() %> registrations.<br />
								-  On the spot offer letters.   <br />
								Registration Fee - <%=city.getRegPrice() %>/- for All the students <br />
                                Registered  candidates can appear in maximum five Companies.<br />
								Company wise details are given below.</p>
								<div class="row companies">
									<div class="col-lg-12 col-md-12">
										<div class="table-responsive">
											<table class="table table-striped table-bordered table-hover">
												<thead>
												<tr>
													<th>S. No.</th>
													<th>Company Name</th>
													<th>Qualification</th>
													<th>Profile</th>
													<th>CTC</th>
													<th>Criteria</th>
													<th>Selection Process</th>
													<th>Date of Campus</th>
													<th>Elegibility</th>
												</tr>
												</thead>
												<tbody>
												<%
												if(indexCompanies != null){
													int sno = 0;
									    			for(Companies company : indexCompanies){
									    				//System.out.println(company + " " + city);
									    				if(company.getCity().equalsIgnoreCase(city.getCity())){
									    					sno++;
												%>
												<tr>
													<td><%=sno %></td>
													<td><%=company.getCompanyName() %></td>
													<td><%=company.getQualification() %></td>
													<td><%=company.getProfile() %></td>
													<td><%=company.getCtc() %></td>
													<td><%=company.getCriteria() %></td>
													<td><%=company.getSelectionProcess() %></td>
													<td><%=company.getDateOfCampus() %></td>
													<td><%=company.getEligibility() %></td>
												</tr>
												<%}else{
									    				//System.out.println("ccccsdsd");
												}
									    			}
												}else{
												    %>
												    <h4>No companies yet</h4>
												    <%		
												    	}
												%>
												<!--<tr>
													<td>2</td>
													<td>Infosys Ltd.</td>
													<td>All Graduates</td>
													<td>Associate Software Engineer</td>
													<td>3 - 3.6 LPA</td>
													<td>50% (Throughout)</td>
													<td>GD + PI</td>
													<td>6th Jan</td>
													<td>2017/2018</td>
												</tr>
												<tr>
													<td>3</td>
													<td>Capgemini Ltd.</td>
													<td>All Graduates</td>
													<td>Associate Software Engineer</td>
													<td>3 - 3.6 LPA</td>
													<td>50% (Throughout)</td>
													<td>GD + PI</td>
													<td>6th Jan</td>
													<td>2017/2018</td>
												</tr>-->

												</tbody>
											</table>
										</div>
									</div>
								</div>
                                <button type="button" class="btn btn-primary" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%}
    	}else{
    %>
    <h4>No companies yet</h4>
    <%		
    	}
%>
        <!-- jQuery -->
    <script src="<%= request.getContextPath() %>/assets/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath() %>/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js" integrity="sha384-mE6eXfrb8jxl0rzJDBRanYqgBxtJ6Unn4/1F7q4xRRyIw7Vdg9jP4ycT7x1iVsgb" crossorigin="anonymous"></script>

    <!-- Contact Form JavaScript -->
    <script src="<%= request.getContextPath() %>/assets/js/jqBootstrapValidation.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/contact_me.js"></script>

    <!-- Theme JavaScript -->
    <script src="<%= request.getContextPath() %>/assets/js/agency.min.js"></script>
    <script>
        
        
 $("#checkPromocode").click(function () {
        if ($("#price").val() !== '0') {
            $.ajax({
                url: '<%=request.getContextPath()%>/Delivery/checkprmocode',
                type: 'post',
                dataType: 'html',
                data: {estimatedprice: $("#price").val(), promocode: $("#promocodetext").val(), pdistrict: $("#pdistrict").val()},
                success: function (data) {
                    var msg = JSON.parse(data);
                    console.log(msg[1]);
                    if (msg[0] === "Promo code is applied") {
                        console.log("Inside if");
                        $("#promocodespangreen").html(msg[2]);
                        $("#promocodespanred").html("");
                        $("#revisedprice").val(msg[1]);
                    } else {
                        console.log("Inside else");
                        $("#promocodespanred").html(msg[0]);
                        $("#promocodespangreen").html("");
                        $("#revisedprice").val($("#price").val());
                    }
                }
            });
        } else {
            //   $("#promocodespanred").html("First enter start and end location");
            $("#promocodespangreen").html("");
        }
    });
    $("#contactForm input,#contactForm textarea").jqBootstrapValidation({
        preventSubmit: true,
        submitError: function ($form, event, errors) {
            // additional error messages or events
        },
        submitSuccess: function ($form, event) {
            event.preventDefault(); // prevent default submit behaviour
            // get values from FORM
            var name = $("input#name").val();
            var email = $("input#email").val();
            var phone = $("input#phone").val();
            var message = $("textarea#message").val();
            var firstName = name; // For Success/Failure Message
            // Check for white space in name for Success/Fail message
            if (firstName.indexOf(' ') >= 0) {
                firstName = name.split(' ').slice(0, -1).join(' ');
            }
            $.ajax({
                url: '<%= request.getContextPath() %>/contactdetails/submitContact',
                type: 'post',
                dataType: 'html',
                data: {
                    name: name,
                    phone: phone,
                    email: email,
                    message: message
                },
                cache: false,
                success: function (data) {
                    var msg = data;
                    if (msg === "Success") {
                        // Success message
                        $('#success').html("<div class='alert alert-success'>");
                        $('#success > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                                .append("</button>");
                        $('#success > .alert-success')
                                .append("<strong>We have recorded your contact request. </strong>");
                        $('#success > .alert-success')
                                .append('</div>');
                    } else {
                        // Fail message
                        $('#success').html("<div class='alert alert-danger'>");
                        $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                                .append("</button>");
                        $('#success > .alert-danger').append($("<strong>").text("Hello " + firstName + ", Please fill in all the details properly to continue!"));
                        $('#success > .alert-danger').append('</div>');
                        //clear all fields
                        $('#contactForm').trigger("reset");
                    }
                    //clear all fields
                    $('#contactForm').trigger("reset");
                },
                error: function () {
                    // Fail message
                    $('#success').html("<div class='alert alert-danger'>");
                    $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                            .append("</button>");
                    $('#success > .alert-danger').append($("<strong>").text("Sorry " + firstName + ", it seems that my mail server is not responding. Please try again later!"));
                    $('#success > .alert-danger').append('</div>');
                    //clear all fields
                    $('#contactForm').trigger("reset");
                },
            });
        },
        filter: function () {
            return $(this).is(":visible");
        },
    });

    $("a[data-toggle=\"tab\"]").click(function (e) {
        e.preventDefault();
        $(this).tab("show");
    });


    </script>
</body>

</html>
