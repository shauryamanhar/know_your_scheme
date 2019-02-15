<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="icon" href="<%= request.getContextPath() %>/assets/img/icon.png" type="image/png" sizes="16x16">
        <meta name="description" content="EnggCell is India's largest job fair organizing portal, Which connects companies, colleges and students through job fair.">
        <meta name="keywords" content="EnggCell, Engineer Cell, Job Fair, Job Fair registration, Job fair in pune, hyderabad, chennai, Bhopal, delhi ncr, gaziabad, gurgaon, banglore, mumbai." />
        <meta name="description" content="Engineer placement cell is job fair organizer in collaboration with well reputed companies in India.Our mission is to provide jobs easily to fresh graduates. EnggCell organizes 4 Job fair in a year.">
        <meta name="author" content="enggcell">

        <title>EnggCell- India's largest and trusted job fairs</title>

        <!-- Bootstrap Core CSS -->
        <link href="<%=request.getContextPath()%>/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="<%=request.getContextPath()%>/assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400" rel="stylesheet" type="text/css">
        <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type='text/css'>
        <!-- Theme CSS -->
        <link href="<%=request.getContextPath()%>/assets/vendor/agency.min.css" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/vendor/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

    </head> 

    <body id="page-top" class="index">
        <!-- Navigation -->
        <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top affix">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                    </button>
                    <a class="navbar-brand page-scroll" href="http://www.enggcell.in">EnggCell</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="hidden">
                            <a href="#page-top"></a>
                        </li>
                        <li class="">
                            <a class="page-scroll" href="http://www.enggcell.in">Fairs</a>
                        </li>
                        <li class="">
                            <a class="page-scroll" href="http://www.enggcell.in">Companies</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="http://www.enggcell.in">About</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="http://www.enggcell.in">Gallary</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="http://www.enggcell.in">Contact</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>
        <!-- Registration form -->

        <div class="container1">
            <form id="signupForm" method="post" autocomplete="off" action="<%= request.getContextPath()%>/register/submitdetails" enctype="multipart/form-data">
                <div class="row">
                    <%
                        if (request.getAttribute("errors") != null && !request.getAttribute("errors").equals("")) {
                    %>
                    <div class="alert alert-danger">
                        <button class="close" data-close="alert"></button>
                        <%
                            out.println("<i class='fa fa-arrow-circle-right'></i> Please fill in all the details properly.<br/>");
                        %>
                    </div>
                    <%  }
                    %>
                    <div class="row">
                        <div class="col-md-12">
                        <h4 class="col-md-4">Personal Details </h4><h5 style="float: right;color: blue"class="col-md-8"> Already Registered? Click <a href="<%= request.getContextPath() %>/Invoice/"> here </a> to switch to payment page.</h5>
                        </div>
                        <br/>
                        <label for="it">Select your field</label>
                        <div class="col-md-12">
                            <select name="it" id="it" class="input-group-icon">
                                <option>Applying for</option>
                                <option value="Non Technical">Non Technical</option>
                                <option value="B.Tech(CSE/IT)">B.Tech(CSE/IT)</option>
                                <option value="B.Tech(Civil)">B.Tech(Civil)</option>
                                <option value="B.Tech(Electrical)">B.Tech(Electrical)</option>
                                <option value="B.Tech(Electronics)">B.Tech(Electronics)</option>
                                <option value="B.Tech(Mechanical)">B.Tech(Mechanical)</option>
                                <option value="B.Tech(Automobile)">B.Tech(Automobile)</option>
                                <option value="B.Pharma">B.Pharma</option>
                                <option value="BBA">BBA</option>
                                <option value="BCA">BCA</option>
                                <option value="M.Tech(CSE/IT)">M.Tech(CSE/IT)</option>
                                <option value="M.Tech(Mechanical)">M.Tech(Mechanical)</option>
                                <option value="MCA">MCA</option>
                                <option value="MBA">MBA</option>
                                <option value="B.Sc/M.Sc(IT)">B.Sc/M.Sc(IT)</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div id="name" class="input-group-icon">
                                <%--<input type="hidden" name="state" id="state" value="<%= request.getAttribute("state") %>"/>--%>
                                <input type="text" id="fullname" name="fullname" placeholder="Full Name"/>
                                <div class="input-icon"><i class="fa fa-user"></i></div>
                            </div>
                            <div id="address" class="input-group-icon">
                                <input type="text" id="address" name="address" placeholder="Address"/>
                                <div class="input-icon"><i class="fa fa-map-marker"></i></div>
                            </div>
                            <div id="input-group2" class="input-group-icon">
                                <input type="email" name="email" id="email" placeholder="Email Adress"/>
                                <div class="input-icon"><i class="fa fa-envelope"></i></div>
                            </div>
                            <div id="college" class="input-group-icon">
                                <input type="text" id="college" name="college" placeholder="Name Of College"/>
                                <div class="input-icon"><i class="fa fa-graduation-cap"></i></div>
                            </div>
                            <div class="col-md-8" style="margin-left: -15px;">
                                <div id="input-group3" class="input-group-icon">
                                    <input type="text" name="mobile" id="mobile" placeholder="Contact Number"/>
                                    <div class="input-icon"><i class="fa fa-mobile"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-half">
                        <h4>Date of Birth</h4>
                        <div class="col-md-12">
                            <div class="input-group">
                                <div class="col-third">
                                    <input type="text" name="dobday" id="dobday" placeholder="DD"/>
                                </div>
                                <div class="col-third">
                                    <input type="text" name="dobmonth" id="dobmonth" placeholder="MM"/>
                                </div>
                                <div class="col-third">
                                    <input type="text" name="dobyear" id="dobyear" placeholder="YYYY"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-half">
                        <h4>Gender</h4>
                        <div class="col-md-12">
                            <select name="gender" id="gender">
                                <option disabled="disabled">Select</option>
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-half">
                        <h4>PAN/Aadhar Card Number </h4>
                        <div class="col-md-8" style="margin-left: -15px;">
                            <div id="#input-group" class="input-group-icon">
                                <input type="text" id="aadhar" name="aadhar" placeholder="PAN/Aadhar Card Number"/>
                                <div class="input-icon"><i class="fa fa-credit-card"></i></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <h4>Enter 5 Companies Code </h4>
                    <h6>Companies code are given on home page in companies section</h6>
                    <div class="col-md-8" style="margin-left: -15px;">
                        <div id="#input-group" class="input-group-icon">
                            <input type="text" id="companies" name="companies" placeholder="Enter Companies Code seprating by , (Maximum 5 Companies)"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-8">
                            <select name="passingyear" id="year" class="input-group-icon">
                                <option>Select Passing Year</option>
                                <option value="2016">2016</option>
                                <option value="2017">2017</option>
                                <option value="2018">2018</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <h4>Preferred Location</h4>
                    <div class="col-md-12">
                        <div class="col-md-8">
                            <select name="location" id="location" class="input-group-icon">
                                <option value="">Select City</option>
                                <option value="NCR">NCR</option>
                                <option value="Gurgaon">Gurgaon</option>
                                <option value="Banglore">Banglore</option>
                                <option value="Hyderabad">Hyderabad</option>
                                <option value="Mumbai">Mumbai</option>
                                <option value="Chennai">Chennai</option>
                                <option value="Pune">Pune</option>
                                <option value="Bhopal">Bhopal</option>
                                <option value="Ahmedabad">Ahmedabad</option>
                                <option value="Kolkata">Kolkata</option>
                            </select>
                        </div>
                    </div>
                    <h4>Upload Resume</h4>
                    <div class="col-md-8" style="margin-left: -15px;">

                        <div class="container"> <br />
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="panel panel-default">
                                        <div class="panel-heading"><strong>Upload files</strong></div>
                                        <div class="panel-body">
                                            <div class="input-group file-preview">
                                                <!-- don't give a name === doesn't send on POST/GET -->
                                                <!--<span class="input-group-btn">--> 
                                                    <!-- file-preview-clear button -->
                                                    <!-- file-preview-input -->
                                                        <input type="file" accept="text/cfg,application/doc,application/docx,application/pdf" id="fileUpload" name="fileUpload" required/>
                                                        <!-- rename it --> 
                                                <!--</span>--> 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>					  
                <div class="row">
                    <h4>Terms and Conditions</h4>
                    <div class="input-group">
                        <input type="checkbox" class="checkbox" name="terms" id="terms"/>
                        <label for="terms">I accept the terms and conditions for this registration for job fair, and hereby confirm I have read the privacy policy.</label>
                    </div>
                    <div class="row" style="text-align: center;">
                        <input type="submit" class="submit" onclick="" value="Apply" style="width: 100px;background-color: #0bbff2; color: #fff;"/><span class="network-name"></span>
                    </div>
                </div>
            </form>
        </div>

        <!-- ./Registration form -->

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

        <!-- jQuery -->
        <script src="<%=request.getContextPath()%>/assets/vendor/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="<%=request.getContextPath()%>/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Plugin JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js" integrity="sha384-mE6eXfrb8jxl0rzJDBRanYqgBxtJ6Unn4/1F7q4xRRyIw7Vdg9jP4ycT7x1iVsgb" crossorigin="anonymous"></script>

        <!-- Contact Form JavaScript -->
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/script.js"></script>
        <!--<script src="<%=request.getContextPath()%>/assets/vendor/jqBootstrapValidation.js"></script>-->
        <!--<script src="<%=request.getContextPath()%>/assets/vendor/contact_me.js"></script>-->
        <!--&lt;!&ndash; Theme JavaScript &ndash;&gt;-->
        <!--<script src="<%=request.getContextPath()%>/assets/vendor/agency.min.js"></script>-->
    </body>

</html>
