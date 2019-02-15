<%@page import="java.util.List"%>
<%@page import="com.enggcell.entities.Cities"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="com.enggcell.entities.Registrations"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <meta name="description" content="" />
        <link rel="icon" href="<%= request.getContextPath() %>/assets/img/icon.png" type="image/png" sizes="16x16">
        <meta name="author" content="" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
            <title>Invoice</title>
            <link href="<%= request.getContextPath()%>/assets/vendor/bootstrap/css/bootstrap.css" rel="stylesheet" />
            <link href="<%= request.getContextPath()%>/assets/vendor/bootstrap/css/custom-style.css" rel="stylesheet" />
            <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css' />
            <style>
                .alert {
                    text-shadow: 0 1px 0 rgba(255, 255, 255, .2);
                    -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, .25), 0 1px 2px rgba(0, 0, 0, .05);
                    box-shadow: inset 0 1px 0 rgba(255, 255, 255, .25), 0 1px 2px rgba(0, 0, 0, .05);
                }
                .alert-success {
                    background-image: -webkit-linear-gradient(top, #dff0d8 0%, #c8e5bc 100%);
                    background-image:      -o-linear-gradient(top, #dff0d8 0%, #c8e5bc 100%);
                    background-image: -webkit-gradient(linear, left top, left bottom, from(#dff0d8), to(#c8e5bc));
                    background-image:         linear-gradient(to bottom, #dff0d8 0%, #c8e5bc 100%);
                    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffdff0d8', endColorstr='#ffc8e5bc', GradientType=0);
                    background-repeat: repeat-x;
                    border-color: #b2dba1;
                }
                .alert-danger {
                    background-image: -webkit-linear-gradient(top, #f2dede 0%, #e7c3c3 100%);
                    background-image:      -o-linear-gradient(top, #f2dede 0%, #e7c3c3 100%);
                    background-image: -webkit-gradient(linear, left top, left bottom, from(#f2dede), to(#e7c3c3));
                    background-image:         linear-gradient(to bottom, #f2dede 0%, #e7c3c3 100%);
                    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fff2dede', endColorstr='#ffe7c3c3', GradientType=0);
                    background-repeat: repeat-x;
                    border-color: #dca7a7;
                }
                
                label{
                    text-align:left;
                }
            </style>
    </head>
    <body>

        <!-- Navigation -->
        <!-- <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top affix"> -->
        <!-- <div class="container"> -->
        <!-- <!-- Brand and toggle get grouped for better mobile display --> 
        <!-- <div class="navbar-header page-scroll"> -->
        <!-- <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> -->
        <!-- <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i> -->
        <!-- </button> -->
        <!-- <a class="navbar-brand page-scroll" href="#page-top">EnggCell</a> -->
        <!-- </div> -->

        <!-- <!-- Collect the nav links, forms, and other content for toggling --> 
        <!-- <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"> -->
        <!-- <ul class="nav navbar-nav navbar-right"> -->
        <!-- <li class="hidden"> -->
        <!-- <a href="#page-top"></a> -->
        <!-- </li> -->
        <!-- <li class=""> -->
        <!-- <a class="page-scroll" href="#"></a> -->
        <!-- </li> -->
        <!-- <li class=""> -->
        <!-- <a class="page-scroll" href="#">Companies</a> -->
        <!-- </li> -->
        <!-- <li> -->
        <!-- <a class="page-scroll" href="#">About</a> -->
        <!-- </li> -->
        <!-- <li> -->
        <!-- <a class="page-scroll" href="#">Cities</a> -->
        <!-- </li> -->
        <!-- <li> -->
        <!-- <a class="page-scroll" href="#">Contact</a> -->
        <!-- </li> -->
        <!-- </ul> -->
        <!-- </div> -->
        <!-- <!-- /.navbar-collapse --> 
        <!-- </div> -->
        <!-- <!-- /.container-fluid --> 
        <!-- </nav> -->

        <br><br>
                <% if (request.getParameter("m") != null) {%>
                <%if (request.getParameter("m").equals("c")) {%>
                <div class="alert alert-success">
                    Payment has been completed successfully
                </div>
                <%} else if (request.getParameter("m").equals("perr")) {%>
                <div class="alert alert-danger">
                    Some error has occurred, please try again later.
                </div>
                <%}%>
                <%}%>
                <div class="container">
                    <% String regId = "0";
                        if (request.getAttribute("registration") != null && !request.getAttribute("registration").equals("") && request.getAttribute("prefcities") != null && !request.getAttribute("prefcities").equals("prefcities")) {
                            Registrations registrations = (Registrations) request.getAttribute("registration");
                            Cities cit = (Cities)request.getAttribute("prefcities");
                            String preferedCityCollege = cit.getCollege();
                            String preferedCityAddress = cit.getAddress();
                            String preferedCityDate = cit.getDate();
                            regId = registrations.getRegId();
                    %>
                    <div class="row pad-top-botm ">
                        <div class="col-lg-6 col-md-6 col-sm-6 " style="text-align: center">
                            <img src="<%= request.getContextPath()%>/assets/logo.png" style="padding-bottom:20px;" />
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6" style="text-align: left">
                            <h2><strong><i>EnggCell</i></strong></h2>
                            <br />
                            <i>Address :</i> Noida, India.
                        </div>
                    </div>
                    <div  class="row text-center contact-info">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <hr />
                            <span>
                                <strong>Email : </strong>  info@enggcell.in
                            </span>
                            <span>
                                <strong>Call : </strong>  +91 9643374137
                            </span>
                            <hr />
                        </div>
                    </div>
                    
                    
                    
              
    <div class = "row container">
       <div class = "col-lg-6 col-md-6 col-sm-12 col-xs-12">
       <h4><strong>Client Information</strong></h4>
           <form class="form-horizontal">
               <div class="form-group">
                   <label class="control-label col-sm-4" for="name" style = "text-align:left;"><span style = "font-weight:300;">Name</span></label>
                   <label class="control-label col-sm-6" for="name" style = "text-align:left;"><%=registrations.getFullName()%></label>
               </div>
               <div class="form-group">
                   <label class="control-label col-sm-4" for="address" style = "text-align:left;"><span style = "font-weight:300;">Address</span></label>
                   <label class="control-label col-sm-6" for="address" style = "text-align:left;"><%=registrations.getAddress()%></label>
               </div>
               <div class="form-group">
                   <label class="control-label col-sm-4" for="city" style = "text-align:left;"><span style = "font-weight:300;">City</span></label>
                   <label class="control-label col-sm-6" for="city" style = "text-align:left;"><%=registrations.getCity().toUpperCase()%></label>
               </div>
               <div class="form-group">
                   <label class="control-label col-sm-4" for="country" style = "text-align:left;"><span style = "font-weight:300;">Country</span></label>
                   <label class="control-label col-sm-6" for="country" style = "text-align:left;">India</label>
               </div>
               <div class="form-group">
                   <label class="control-label col-sm-4" for="mobile" style = "text-align:left;"><span style = "font-weight:300;">Call</span></label>
                   <label class="control-label col-sm-6" for="mobile" style = "text-align:left;"><%=registrations.getMobile()%></label>
               </div>
               <div class="form-group">
                   <label class="control-label col-sm-4" for="email" style = "text-align:left;"><span style = "font-weight:300;">Email</span></label>
                   <label class="control-label col-sm-6" for="email" style = "text-align:left;"><%=registrations.getEmail()%></label>
               </div>
               <div class="form-group">
                   <label class="control-label col-sm-4" for="college" style = "text-align:left;"><span style = "font-weight:300;">College</span></label>
                   <label class="control-label col-sm-6" for="college" style = "text-align:left;"><%=registrations.getCollege()%></label>
               </div>
               <div class="form-group">
                   <label class="control-label col-sm-4" for="id" style = "text-align:left;"><span style = "font-weight:300;">Registration Id</span></label>
                   <label class="control-label col-sm-6" for="id" style = "text-align:left;"><%=registrations.getRegId()%></label>
               </div>
           </form>
       </div>
       <div class = "col-lg-6 col-md-6 col-sm-12 col-xs-12">
           <h4>  <strong>Payment Details </strong></h4>
           <form class="form-horizontal">
           <div class="form-group">
               <label class="control-label col-sm-4" for="billAmount" style = "text-align:left;"><span style = "font-weight:300;">Bill Amount</span></label>
               <label class="control-label col-sm-6" for="billAmount" style = "text-align:left;">Rs. 1000</label>
           </div>
                            <%
                            String dayMonYear[] = registrations.getDate().split("/");
                            String day = dayMonYear[0];
                            int month = Integer.parseInt(dayMonYear[1]);
                            String year = dayMonYear[2];
                            String monthString = new DateFormatSymbols().getMonths()[month-1];
                            
                            %>
           <div class="form-group">
               <label class="control-label col-sm-4" for="billDate" style = "text-align:left;"><span style = "font-weight:300;">Bill Date</span></label>
               <label class="control-label col-sm-6" for="billDate" style = "text-align:left;"><%=day + " " + monthString + " " + year %></label>
           </div>
           <div class="form-group">
               <label class="control-label col-sm-4" for="paymentStatus" style = "text-align:left;"><span style = "font-weight:300;">Payment Status</span></label>
               <label class="control-label col-sm-6" for="paymentStatus" style = "text-align:left;"><%=registrations.getStatus() %></label>
           </div>
           </form>
           <br>
           <h4>  <strong>Fair Details </strong></h4>
           <form class="form-horizontal">
           <div class="form-group">
               <label class="control-label col-sm-4" for="billAmount" style = "text-align:left;"><span style = "font-weight:300;">College</span></label>
               <label class="control-label col-sm-6" for="billAmount" style = "text-align:left;"><%=preferedCityCollege %></label>
           </div>
           <div class="form-group">
               <label class="control-label col-sm-4" for="billDate" style = "text-align:left;"><span style = "font-weight:300;">Address</span></label>
               <label class="control-label col-sm-6" for="billDate" style = "text-align:left;"><%=preferedCityAddress %></label>
           </div>
           <div class="form-group">
               <label class="control-label col-sm-4" for="paymentStatus" style = "text-align:left;"><span style = "font-weight:300;">Date of Fair</span></label>
               <label class="control-label col-sm-6" for="paymentStatus" style = "text-align:left;"><%=preferedCityDate %></label>
           </div>
           </form>
       </div>
    </div>
                    
                    
                    
                   <!--  <div  class="row pad-top-botm client-info">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <h4><strong>Client Information</strong></h4>
                            <strong id="name"></strong>
                            <br />
                            <b>Address :</b> <b id="address"></b> ,
                            <br />
                            <b>City :</b> <b id="location"></b> ,
                            <br />
                            India.
                            <br />
                            <b>Call :</b> <b id="contact"></b>
                            <br />
                            <b>E-mail :</b> <b id="email"></b>
                            <br />
                            <b>College Name :</b> <b id="email"></b>
                            <br />
                            <b>Registration id :</b> <b id="regId"></b>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <h4>  <strong>Payment Details </strong></h4>
                            <b>Bill Amount :  Rs. 1000 </b>
                            <br />
                            Bill Date :  
                            <br />
                            <b>Payment Status :  Pending </b>

                        </div>
                    </div>
                    -->
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <hr />
                            <div class="ttl-amts">
                                <h4> <strong>Bill Amount : Rs 1000</strong> </h4>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <strong> Important: </strong>
                            <ol>
                                <li>
                                    This is an electronic generated invoice so doesn't require any signature.

                                </li>
                                <li>
                                    Please read all terms and polices on <a href="http://www.enggcell.in">www.enggcell.in</a> for any queries and other issues.

                                </li>
                            </ol>
                            <input type="hidden" id="regIdC" name="regIdC" value="<%= registrations.getRegId()%>" class="col-md-4" placeholder="Registration Id"/>
                        </div>
                    </div>
                    <div class="row pad-top-botm">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <hr />
                            &nbsp;&nbsp;&nbsp;
                            <a id="pay" class="btn btn-success btn-lg"  href="<%=request.getContextPath()%>/Invoice/addMoney/<%=regId%>"><img src="<%= request.getContextPath()%>/assets/paypal-checkout.jpeg" style="max-height: 50px !important;max-width: 150px !important" ></a>
                            <div class="process"></div>
                        </div>
                    </div>
                    <%} else {%>
                    <form id="signupForm" method="post" autocomplete="off" action="<%=request.getContextPath()%>/Invoice/addMoneyViaRegId">
                        <div class="row">
                            <div class="col-md-12">
                                <div id="name" class="input-group-icon">
                                    <h4 class="col-md-4">Enter your Registration Id &nbsp; </h4><input type="text" id="regId" name="regId" class="col-md-4" placeholder="Registration Id"/>
                                    <div class="input-icon"><i class="fa fa-user"></i></div>
                                </div>
                            </div>
                        </div>
                        <div class="row pad-top-botm">
                            <div class="col-lg-12 col-md-12 col-sm-12">
                                <hr />
                                &nbsp;&nbsp;&nbsp;
                                <button id="pay" class="btn btn-success btn-lg"  type="submit"><img src="<%= request.getContextPath()%>/assets/paypal-checkout.jpeg" style="max-height: 50px !important;max-width: 150px !important" ></button>
                                <div class="process"></div>
                            </div>
                        </div>
                    </form>
                    <%}%>

                </div>
                <!-- jQuery -->
                <script src="<%= request.getContextPath()%>/assets/vendor/jquery/jquery.min.js"></script>

                <!-- Bootstrap Core JavaScript -->
                <script src="<%= request.getContextPath()%>/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

                <!-- Plugin JavaScript -->
                <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js" integrity="sha384-mE6eXfrb8jxl0rzJDBRanYqgBxtJ6Unn4/1F7q4xRRyIw7Vdg9jP4ycT7x1iVsgb" crossorigin="anonymous"></script>

                <!-- Contact Form JavaScript -->
                <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
                <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
                <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
                <script src="https://www.gstatic.com/firebasejs/4.6.2/firebase.js"></script>
                <script src="<%= request.getContextPath()%>/assets/script.js"></script>

                <script>
//                    // Initialize Firebase
//                    var config = {
//                        apiKey: "AIzaSyBxAW0xlEZfW8PiYuGt1Ynqq9XblXwy4ns",
//                        authDomain: "clientdb-581ff.firebaseapp.com",
//                        databaseURL: "https://clientdb-581ff.firebaseio.com",
//                        projectId: "clientdb-581ff",
//                        storageBucket: "clientdb-581ff.appspot.com",
//                        messagingSenderId: "629508594814"
//                    };
//                    firebase.initializeApp(config);
//                    // Firebase connection established.
//                    console.log(firebase);
//                    var s = JSON.parse(localStorage.getItem("data"));
//                    $("#name").html(s.fullname);
//                    $("#location").html(s.location);
//                    $("#email").html(s.email);
//                    $("#contact").html(s.mobile);
//                  
                </script>
                </body>
                </html>
