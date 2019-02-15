<%@page import="com.enggcell.entities.User"%>
<%

response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);


if(session.getAttribute("knowYourSchemeUser") != null)
    response.sendRedirect(request.getContextPath()+"/");

%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KnowYourScheme</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>

  @import url(https://fonts.googleapis.com/css?family=Raleway:400,100,200,300);
* {
  margin: 0;
  padding: 0; }

a {
  color: #666;
  text-decoration: none; }
  a:hover {
    color: #4FDA8C; }

input {
  font: 16px/26px "Raleway", sans-serif; }

body {
  color: #666;
  background-color: #f1f2f2;
  font: 16px/26px "Raleway", sans-serif; }

.form-wrap {
  background-color: #fff;
  width: 400px;
  margin: 3em auto;
  box-shadow: 0px 1px 8px #BEBEBE;
  -webkit-box-shadow: 0px 1px 8px #BEBEBE;
  -moz-box-shadow: 0px 1px 8px #BEBEBE; }
  .form-wrap .tabs {
    overflow: hidden; }
    .form-wrap .tabs h3 {
      float: left;
      width: 100%;
      text-align: center; }
      .form-wrap .tabs h3 a {
        padding: 0.5em 0;
        text-align: center;
        font-weight: 400;
        background-color: #e6e7e8;
        display: block;
        color: #666; }
        .form-wrap .tabs h3 a.active {
          background-color: #fff; }
  .form-wrap .tabs-content {
    padding: 1.5em; }
    .form-wrap .tabs-content div[id$="tab-content"] {
      display: none; }
    .form-wrap .tabs-content .active {
      display: block !important; }
  .form-wrap form .input {
    box-sizing: border-box;
    -moz-box-sizing: border-box;
    color: inherit;
    font-family: inherit;
    padding: .8em 0 10px .8em;
    border: 1px solid #CFCFCF;
    outline: 0;
    display: inline-block;
    margin: 0 0 .8em 0;
    padding-right: 2em;
    width: 100%; }
  .form-wrap form .button {
    width: 100%;
    padding: .8em 0 10px .8em;
    background-color: #28A55F;
    border: none;
    color: #fff;
    cursor: pointer;
    text-transform: uppercase; }
    .form-wrap form .button:hover {
      background-color: #4FDA8C; }
  .form-wrap form .checkbox {
    visibility: hidden;
    padding: 20px;
    margin: .5em 0 1.5em; }
    .form-wrap form .checkbox:checked + label:after {
      -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=100)";
      filter: alpha(opacity=100);
      opacity: 1; }
  .form-wrap form label[for] {
    position: relative;
    padding-left: 20px;
    cursor: pointer; }
    .form-wrap form label[for]:before {
      content: '';
      position: absolute;
      border: 1px solid #CFCFCF;
      width: 17px;
      height: 17px;
      top: 0px;
      left: -14px; }
    .form-wrap form label[for]:after {
      -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
      filter: alpha(opacity=0);
      opacity: 0;
      content: '';
      position: absolute;
      width: 9px;
      height: 5px;
      background-color: transparent;
      top: 4px;
      left: -10px;
      border: 3px solid #28A55F;
      border-top: none;
      border-right: none;
      -webkit-transform: rotate(-45deg);
      -moz-transform: rotate(-45deg);
      -o-transform: rotate(-45deg);
      -ms-transform: rotate(-45deg);
      transform: rotate(-45deg); }
  .form-wrap .help-text {
    margin-top: .6em; }
    .form-wrap .help-text p {
      text-align: center;
      font-size: 14px; }

</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
var errorMsg = "";
function isMyValidForm(){
	var password = $('#password').val();
	var confirmPassword = $('#confirmPassword').val();
    //alert(username + " " + password);
    
    $.ajax({
    	async: false,
		type: "POST",
		url: "<%= request.getContextPath()%>/password-Check-Change-Password/validate",
		data: { confirmPassword: confirmPassword, password: password },
		success : function(response) {
			
			var msg = response;
			//alert(msg);
			if(msg == "emptyFieldsFailure"){
				$("#errorLoginMsg").text("All Fields Required");
				errorMsg = "falsee";
				return false;
			}else if(msg == "success"){
				errorMsg = "truee";
				return true;
			}else if(msg == "confirmPasswordFailure"){
				errorMsg = "falsee";
				$("#errorLoginMsg").text("Passwords did not match");
				return false;
			}else if(msg == "passwordFailure"){
				errorMsg = "falsee";
				$("#errorLoginMsg").text("Password must be 8 characters long");
				return false;
			}else{//failure
				errorMsg = "falsee";
				$("#errorLoginMsg").text("Server error please try again");
				return false;
			}
		}
	});
    //alert("hello 00");
    
    if(errorMsg == "falsee"){
    	return false;
    }else{
    	return true;
    }
    //alert(res);
}

</script>

<style>

ul.dashboard-nav li{
   display:inline;
}

.dashboard-active{
   background-color:#ffeb3b;
}
</style>

</head>
<body>

<%

User user = null;
if(request.getAttribute("dashboardUser") != null &&
   !request.getAttribute("dashboardUser").equals("")){
	user = (User)request.getAttribute("dashboardUser");
}
%>

<style>

.goog-logo-link {
   display:none !important;
} 

.goog-te-gadget{
   color: transparent !important;
}
.goog-te-combo{
   margin-top: -2px!important;
   height: 30px;
   width:130px;
   border: none!important;
   background-color: #FFEB3B!important;
   outline:none!important;
   color:black;
}
</style>	
<span class="sizechanger">

<div id="google_translate_element" style = "position:absolute; top:0px; left:160px;"></div>

<script type="text/javascript">
function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'en', layout: google.translate.TranslateElement.InlineLayout.HORIZONTAL}, 'google_translate_element');
}
</script>

<script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
<div class = "container text-center">
<br>
<h2>Know Your Scheme</h2>

</div>

    <div class="form-wrap">
		<div class="tabs">
			<h3 class="login-tab"><span class="active" href="#login-tab-content" style = "margin-top: -20px; text-decoration: none;">Forget Password</span></h3>
			<h5 style = "margin-top: 80px; text-align:center;"><span class="active" href="#login-tab-content" style = "text-decoration: none;">Change your Password</span></h5>
		</div><!--.tabs-->

		<div class="tabs-content">
			<div id="login-tab-content" class="active">
				<form class="login-form" action="<%=request.getContextPath() %>/password-reset-success-msg" method="post" onsubmit="return isMyValidForm();">
					<input type="password" class="input" id="password" autocomplete="off" placeholder="Password" name = "password" required>
					<input type="password" class="input" id="confirmPassword" autocomplete="off" placeholder="Confirm Password" name = "confirmPassword" required>
					<input type = "hidden" value = "<%=user.getUsername()%>" name = "username" id = "username">
					<div id = "errorLoginMsg" style = "color: red; font-size:12px;">&nbsp;</div>
					<input type="submit" class="button" value="Submit">
				</form><!--.login-form-->
			</div><!--.login-tab-content-->
		</div><!--.tabs-content-->
	</div><!--.form-wrap-->

<script>

jQuery(document).ready(function($) {
	tab = $('.tabs h3 a');

	tab.on('click', function(event) {
		event.preventDefault();
		tab.removeClass('active');
		$(this).addClass('active');

		tab_content = $(this).attr('href');
		$('div[id$="tab-content"]').removeClass('active');
		$(tab_content).addClass('active');
	});
});

</script>

</body>
</html>