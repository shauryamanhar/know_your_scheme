<%@page import="com.enggcell.entities.Registrations"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <meta name="description" content="" />
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
            </style>
    </head>
    <body>

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
                        if (request.getAttribute("registrations") != null && !request.getAttribute("registrations").equals("")) {
                            Registrations registrations = (Registrations) request.getAttribute("registrations");
                            regId = registrations.getRegId();
                    %>
                    <div id="print">
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
                                    <strong>Call : </strong>  +91-9643374137
                                </span>
                                <hr />
                            </div>
                        </div>
                        <div  class="row pad-top-botm client-info">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <h4><strong>Client Information</strong></h4>
                                <strong id="name"><%=registrations.getFullName()%></strong>
                                <br />
                                <b>CIty :</b> <b id="location"><%=registrations.getState()%></b> ,
                                <br />
                                India.
                                <br />
                                <b>Call :</b> <b id="contact"><%=registrations.getMobile()%></b>
                                <br />
                                <b>E-mail :</b> <b id="email"><%=registrations.getEmail()%></b>
                                <br />
                                <b>Registration id :</b> <b id="regId"><%=registrations.getRegId()%></b> <br />
                                <b>Gender :</b> <b id="regId"><%=registrations.getGender()%></b> <br />
                                <b>Companies :</b> <b id="regId"><%=registrations.getCompanies()%></b> <br />
                                <b>Branch :</b> <b id="regId"><%=registrations.getBranch()%></b> <br />
                                <b>Passing year :</b> <b id="regId"><%=registrations.getPassingyear()%></b> <br />
                                <b>PAN/Aadhar no.  :</b> <b id="regId"><%=registrations.getPanOraadhar()%></b> <br />
                                <b>Date of birth :</b> <b id="regId"><%=registrations.getDay()%> - <%=registrations.getMonth()%> - <%=registrations.getYear()%></b>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <h4>  <strong>Payment Details </strong></h4>
                                <b>Bill Amount :  Rs. 1000 </b>
                                <br />
                                Bill Date :  19th November 2017
                                <br />
                                <b>Payment Status :  Pending </b>

                            </div>
                        </div>
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
                    </div>
                    <%}%>
                    <div class="row pad-top-botm">
                        <button onclick="printInvoice()" class="btn btn-alt btn-hover btn-info">
                            <span>Print Invoice</span>
                            <i class="glyph-icon icon-print"></i>
                        </button>
                        <br/>
                    </div>
                </div>
                    <br/>
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
                            function printInvoice() {
                                var restorepage = document.body.innerHTML;
                                var printcontent = document.getElementById('print').innerHTML;
                                document.body.innerHTML = printcontent;
                                window.print();
                                document.body.innerHTML = restorepage;
                            }
                </script>
                </body>
                </html>
