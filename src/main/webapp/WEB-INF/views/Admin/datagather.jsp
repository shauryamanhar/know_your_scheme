<%

response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);


%>

<!DOCTYPE html>
<%@page import="com.enggcell.entities.News"%>
<%@page import="com.enggcell.entities.TempUser"%>
<%@page import="com.enggcell.entities.GovernmentWebsites"%>
<%@page import="com.enggcell.entities.GovernmentJobs"%>
<%@page import="com.enggcell.entities.StatementGovernmentSchemes"%>
<%@page import="com.enggcell.entities.CentralGovernmentSchemes"%>
<%@page import="java.util.List"%>
<%@page import="com.enggcell.entities.ApplicationForms"%>
<%@page import="com.enggcell.entities.UserCategories"%>
<%@page import="com.enggcell.entities.User"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Admin KnowYourScheme</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/bower_components/Ionicons/css/ionicons.min.css">
  <!-- jvectormap -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/bower_components/jvectormap/jquery-jvectormap.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/dist/css/skins/_all-skins.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        
        
        
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
var errorMsg = "";
function collectSchemes(){
	document.getElementById("statusMsgForSchemes").style.color = 'orange';
	$("#statusMsgForSchemes").text("Collecting Scheme Data ....");
    $.ajax({
    	async: false,
		type: "POST",
		url: "<%= request.getContextPath()%>/admin/datagather/State-Government",
		success : function(response) {
			
			var msg = response;
			//alert(msg);
			if(msg == "success"){
				errorMsg = "truee";
				document.getElementById("statusMsgForSchemes").style.color = 'green';
				$("#statusMsgForSchemes").text("Schemes Data Collected !");
				return true;
			}else{//failure
				errorMsg = "falsee";
				document.getElementById("statusMsgForSchemes").style.color = 'red';
				$("#statusMsgForSchemes").text("Problem Collecting Data !");
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

//2

function collectApplicationForms(){
	document.getElementById("statusMsgForApplicationForms").style.color = 'orange';
	$("#statusMsgForApplicationForms").text("Collecting Application Forms Data ....");
    $.ajax({
    	async: false,
		type: "POST",
		url: "<%= request.getContextPath()%>/admin/datagather/Application-Forms",
		success : function(response) {
			
			var msg = response;
			//alert(msg);
			if(msg == "success"){
				errorMsg = "truee";
				document.getElementById("statusMsgForApplicationForms").style.color = 'green';
				$("#statusMsgForApplicationForms").text("Application Forms Data Collected !");
				return true;
			}else{//failure
				errorMsg = "falsee";
				document.getElementById("statusMsgForApplicationForms").style.color = 'red';
				$("#statusMsgForApplicationForms").text("Problem Collecting Data !");
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

//3

function collectNews(){
	document.getElementById("statusMsgForNews").style.color = 'orange';
	$("#statusMsgForNews").text("Collecting News Data ....");
    $.ajax({
    	async: false,
		type: "POST",
		url: "<%= request.getContextPath()%>/admin/datagather/News",
		success : function(response) {
			
			var msg = response;
			//alert(msg);
			if(msg == "success"){
				errorMsg = "truee";
				document.getElementById("statusMsgForNews").style.color = 'green';
				$("#statusMsgForNews").text("News Data Collected !");
				return true;
			}else{//failure
				errorMsg = "falsee";
				document.getElementById("statusMsgForNews").style.color = 'red';
				$("#statusMsgForNews").text("Problem Collecting Data !");
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

//4

function collectGovernmentJobs(){
	document.getElementById("statusMsgForGovernmentJobs").style.color = 'orange';
	$("#statusMsgForGovernmentJobs").text("Collecting Government Jobs Data ....");
    $.ajax({
    	async: false,
		type: "POST",
		url: "<%= request.getContextPath()%>/admin/datagather/Government-Jobs",
		success : function(response) {
			
			var msg = response;
			//alert(msg);
			if(msg == "success"){
				errorMsg = "truee";
				document.getElementById("statusMsgForGovernmentJobs").style.color = 'green';
				$("#statusMsgForGovernmentJobs").text("Government Jobs Data Collected !");
				return true;
			}else{//failure
				errorMsg = "falsee";
				document.getElementById("statusMsgForGovernmentJobs").style.color = 'red';
				$("#statusMsgForGovernmentJobs").text("Problem Collecting Data !");
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


//5

function collectGovernmentWebsites(){
	document.getElementById("statusMsgForGovernmentWebsites").style.color = 'orange';
	$("#statusMsgForGovernmentWebsites").text("Collecting Government Websites Data ....");
    $.ajax({
    	async: false,
		type: "POST",
		url: "<%= request.getContextPath()%>/admin/datagather/Government-Websites",
		success : function(response) {
			
			var msg = response;
			//alert(msg);
			if(msg == "success"){
				errorMsg = "truee";
				document.getElementById("statusMsgForGovernmentWebsites").style.color = 'green';
				$("#statusMsgForGovernmentWebsites").text("Government Websites Data Collected !");
				return true;
			}else{//failure
				errorMsg = "falsee";
				document.getElementById("statusMsgForGovernmentWebsites").style.color = 'red';
				$("#statusMsgForGovernmentWebsites").text("Problem Collecting Data !");
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

//6

function collectVisualisationData(){
	document.getElementById("statusMsgForVisualization").style.color = 'orange';
	$("#statusMsgForVisualization").text("Collecting Visuaisation Data ....");
    $.ajax({
    	async: false,
		type: "POST",
		url: "<%= request.getContextPath()%>/admin/datagather/visualisation",
		success : function(response) {
			
			var msg = response;
			//alert(msg);
			if(msg == "success"){
				errorMsg = "truee";
				document.getElementById("statusMsgForVisualization").style.color = 'green';
				$("#statusMsgForVisualization").text("Visualisation Data Collected !");
				return true;
			}else{//failure
				errorMsg = "falsee";
				document.getElementById("statusMsgForVisualization").style.color = 'red';
				$("#statusMsgForVisualization").text("Problem Collecting Data !");
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
        
</head>
<body class="hold-transition skin-blue sidebar-mini">

<%

List<String> statesForUrl = null;
HashMap<String, String> mapOfstatesWithUrl  = null;

List<User> listOfUsers = null;
List<UserCategories> listOfUserCategories = null;
List<ApplicationForms> listOfApplicationForms = null;
List<CentralGovernmentSchemes> listOfCentralGovernmentSchemes = null;
List<StatementGovernmentSchemes> listOfStatementGovernmentSchemes = null;
List<GovernmentJobs> listOfGovernmentJpbs = null;
List<GovernmentWebsites> listOfGovernmentWebsites = null;
List<TempUser> listOfTempUsers = null;
List<News> listOfNews = null;


if(request.getAttribute("listOfUsers") != null &&
!request.getAttribute("listOfUsers").equals("") &&
request.getAttribute("listOfUserCategories") != null &&
!request.getAttribute("listOfUserCategories").equals("") &&
request.getAttribute("listOfApplicationForms") != null &&
!request.getAttribute("listOfApplicationForms").equals("") &&
request.getAttribute("listOfCentralGovernmentSchemes") != null &&
!request.getAttribute("listOfCentralGovernmentSchemes").equals("") &&
request.getAttribute("listOfStatementGovernmentSchemes") != null &&
!request.getAttribute("listOfStatementGovernmentSchemes").equals("") &&
request.getAttribute("listOfGovernmentJpbs") != null &&
!request.getAttribute("listOfGovernmentJpbs").equals("") &&
request.getAttribute("listOfGovernmentWebsites") != null &&
!request.getAttribute("listOfGovernmentWebsites").equals("") &&
request.getAttribute("listOfTempUsers") != null &&
!request.getAttribute("listOfTempUsers").equals("")&& 
request.getAttribute("listOfNews") != null &&
!request.getAttribute("listOfNews").equals("") &&
request.getAttribute("statesForUrl") != null &&
!request.getAttribute("statesForUrl").equals("") &&
request.getAttribute("mapOfstatesWithUrl") != null &&
!request.getAttribute("mapOfstatesWithUrl").equals("")){
	listOfNews = (List<News>)request.getAttribute("listOfNews");
	listOfTempUsers = (List<TempUser>)request.getAttribute("listOfTempUsers");
	listOfGovernmentWebsites = (List<GovernmentWebsites>)request.getAttribute("listOfGovernmentWebsites");
	
	listOfGovernmentJpbs = (List<GovernmentJobs>)request.getAttribute("listOfGovernmentJpbs");
	listOfStatementGovernmentSchemes = (List<StatementGovernmentSchemes>)request.getAttribute("listOfStatementGovernmentSchemes");
	listOfCentralGovernmentSchemes = (List<CentralGovernmentSchemes>)request.getAttribute("listOfCentralGovernmentSchemes");
	
	listOfApplicationForms = (List<ApplicationForms>)request.getAttribute("listOfApplicationForms");
	listOfUserCategories = (List<UserCategories>)request.getAttribute("listOfUserCategories");
	listOfUsers = (List<User>)request.getAttribute("listOfUsers");
	
	mapOfstatesWithUrl = (HashMap<String, String>)request.getAttribute("mapOfstatesWithUrl");
	statesForUrl = (List<String>)request.getAttribute("statesForUrl");
}




%>

<div class="wrapper">

  <header class="main-header">

    <!-- Logo -->
    <a href="index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>KYS</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Admin</b> KYS</span>
    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar" style = "width: 300px;">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">MAIN NAVIGATION</li>
        <li class="active treeview menu-open">
          <a href="<%=request.getContextPath() %>/admin">
            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
          </a>
          <ul class="treeview-menu">
          </ul>
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-laptop"></i>
            <span>User</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<%=request.getContextPath() %>/admin/user/registered-users"><i class="fa fa-circle-o"></i>Registered Users</a></li>
            <li><a href="<%=request.getContextPath() %>/admin/user/temporary-users"><i class="fa fa-circle-o"></i>Temporary Users</a></li>
          </ul>
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-laptop"></i>
            <span>Schemes</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          <%
          if(statesForUrl != null && mapOfstatesWithUrl != null){
        	  for(String stateForU : statesForUrl){
          %>
            <li><a href="<%=request.getContextPath() %>/admin/schemes/<%=stateForU%>"><i class="fa fa-circle-o"></i><%=mapOfstatesWithUrl.get(stateForU) %></a></li>
          <%
        	  }
          }
          %>  
          </ul>
        </li>
        <li class="treeview">
          <a href="<%=request.getContextPath() %>/admin/schemes/Application-Forms">
            <i class="fa fa-laptop"></i>
            <span>Application Forms</span>
          </a>
        </li>
        <li class="treeview">
          <a href="<%=request.getContextPath() %>/admin/schemes/Government-Jobs">
            <i class="fa fa-laptop"></i>
            <span>Government Jobs</span>
          </a>
        </li>
        <li class="treeview">
          <a href="<%=request.getContextPath() %>/admin/schemes/Government-Websites">
            <i class="fa fa-laptop"></i>
            <span>Government Websites</span>
          </a>
        </li>
        <li class="treeview">
          <a href="<%=request.getContextPath() %>/admin/news">
            <i class="fa fa-laptop"></i>
            <span>News</span>
          </a>
        </li>
        <li class="treeview">
          <a href="<%=request.getContextPath() %>/admin/data-gather">
            <i class="fa fa-laptop"></i>
            <span>Data Gather</span>
          </a>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->

    <!-- Main content -->
    <section class="content" style = "margin-left: 60px;">
		<div class="row companies">
			<div class="col-lg-12 col-md-12">
			
			
			<div class="col-lg-6 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-green" style = "text-align:center;">
            <div class="inner">
              <h3>Schemes<sup style="font-size: 20px"></sup></h3>
            </div>
            <div class="icon">
              <!-- <i class="ion ion-stats-bars"></i> -->
            </div>
            <input style = "margin:auto" class="small-box-footer" type = "button" id = "collectSchemesButton" name = "collectSchemesButton" onclick = "collectSchemes()" value = "Collect Schemes Data">
            <br>
            <div id = "statusMsgForSchemes" style = "background-color:white; font-weight:bold; font-size:16px;"></div>
          </div>
        </div>
        <!-- ./col -->
        
        <div class="col-lg-6 col-xs-6" style = "text-align:center;">
          <!-- small box -->
          <div class="small-box bg-blue">
            <div class="inner">
              <h3>News<sup style="font-size: 20px"></sup></h3>
            </div>
            <div class="icon">
              <!-- <i class="ion ion-stats-bars"></i> -->
            </div>
            <input style = "margin:auto" class="small-box-footer" type = "button" id = "collectNewsButton" name = "collectNewsButton" onclick = "collectNews()" value = "Collect News Data">
            <br>
            <div id = "statusMsgForNews" style = "background-color:white; font-weight:bold; font-size:16px;"></div>
          </div>
        </div>
        
		<div class="col-lg-6 col-xs-6" style = "text-align:center;">
          <!-- small box -->
          <div class="small-box bg-red">
            <div class="inner">
              <h3>Application Forms<sup style="font-size: 20px"></sup></h3>
            </div>
            <div class="icon">
              <!-- <i class="ion ion-stats-bars"></i> -->
            </div>
            <input style = "margin:auto" class="small-box-footer" type = "button" id = "collectApplicationFormsButton" name = "collectApplicationFormsButton" onclick = "collectApplicationForms()" value = "Collect Application Forms Data">
            <br>
            <div id = "statusMsgForApplicationForms" style = "background-color:white; font-weight:bold; font-size:16px;"></div>
          </div>
        </div>
        
        <div class="col-lg-6 col-xs-6" style = "text-align:center;">
          <!-- small box -->
          <div class="small-box bg-yellow">
            <div class="inner">
              <h3>Government Jobs<sup style="font-size: 20px"></sup></h3>
            </div>
            <div class="icon">
              <!-- <i class="ion ion-stats-bars"></i> -->
            </div>
            <input style = "margin:auto" class="small-box-footer" type = "button" id = "collectGovernmentJobsButton" name = "collectGovernmentJobsButton" onclick = "collectGovernmentJobs()" value = "Collect Government Jobs Data">
            <br>
            <div id = "statusMsgForGovernmentJobs" style = "background-color:white; font-weight:bold; font-size:16px;"></div>
          </div>
        </div>
        
        <div class="col-lg-6 col-xs-6" style = "text-align:center;">
          <!-- small box -->
          <div class="small-box bg-purple">
            <div class="inner">
              <h3>Government Websites<sup style="font-size: 20px"></sup></h3>
            </div>
            <div class="icon">
              <!-- <i class="ion ion-stats-bars"></i> -->
            </div>
            <input style = "margin:auto" class="small-box-footer" type = "button" id = "collectGovernmentWebsitesButton" name = "collectGovernmentWebsitesButton" onclick = "collectGovernmentWebsites()" value = "Collect Govt Websites Data" value = "Collect Government Websites Data">
            <br>
            <div id = "statusMsgForGovernmentWebsites" style = "background-color:white; font-weight:bold; font-size:16px;"></div>
          </div>
        </div>
        
        <div class="col-lg-6 col-xs-6" style = "text-align:center;">
          <!-- small box -->
          <div class="small-box bg-purple">
            <div class="inner">
              <h3>Visualisation Data<sup style="font-size: 20px"></sup></h3>
            </div>
            <div class="icon">
              <!-- <i class="ion ion-stats-bars"></i> -->
            </div>
            <input style = "margin:auto" class="small-box-footer" type = "button" id = "collectVisualisationDataButton" name = "collectVisualisationDataButton" onclick = "collectVisualisationData()" value = "Collect Visualisation Data">
            <br>
            <div id = "statusMsgForVisualization" style = "background-color:white; font-weight:bold; font-size:16px;"></div>
          </div>
        </div>	
			
			</div>
		</div>
      <!-- /.row -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
    </div>
    <strong>Copyright &copy; 2017 <a href="https://www.enggcell.com">KnowYourScheme</a>.</strong> All rights
    reserved.
  </footer>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>

</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="<%=request.getContextPath() %>/assets/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="<%=request.getContextPath() %>/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="<%=request.getContextPath() %>/assets/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath() %>/assets/dist/js/adminlte.min.js"></script>
<!-- Sparkline -->
<script src="<%=request.getContextPath() %>/assets/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<!-- jvectormap  -->
<script src="<%=request.getContextPath() %>/assets/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- SlimScroll -->
<script src="<%=request.getContextPath() %>/assets/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- ChartJS -->
<script src="<%=request.getContextPath() %>/assets/bower_components/Chart.js/Chart.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="<%=request.getContextPath() %>/assets/dist/js/pages/dashboard2.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=request.getContextPath() %>/assets/dist/js/demo.js"></script>
</body>
</html>
