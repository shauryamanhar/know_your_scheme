
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
        
        
        
        
<style type="text/css">

.btn{
  display: inline-block;
    padding: 6px 12px;
    margin-bottom: 0;
    font-size: 14px;
    font-weight: 400;
    line-height: 1.42857143;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 4px;

}

.btn-danger {
    color: #fff;
    background-color: #d9534f;
    border-color: #d43f3a;
}
.alert {
    padding: 15px;
    margin-bottom: 20px;
    border: 1px solid transparent;
    border-radius: 4px;
}
.alert-success {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
}

.alert-danger {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
}


#login-modal .modal-dialog, #login-modal2 .modal-dialog {
    width: 350px;
}

#login-modal input[type=text], input[type=password], #login-modal2 input[type=text]{
	margin-top: 10px;
}
#div-login-msg,
#div-lost-msg,
#div-register-msg {
    border: 1px solid #dadfe1;
    height: 30px;
    line-height: 28px;
    transition: all ease-in-out 500ms;
}

#div-login-msg.success,
#div-lost-msg.success,
#div-register-msg.success {
    border: 1px solid #68c3a3;
    background-color: #c8f7c5;
}

#div-login-msg.error,
#div-lost-msg.error,
#div-register-msg.error {
    border: 1px solid #eb575b;
    background-color: #ffcad1;
}

#icon-login-msg,
#icon-lost-msg,
#icon-register-msg {
    width: 30px;
    float: left;
    line-height: 28px;
    text-align: center;
    background-color: #dadfe1;
    margin-right: 5px;
    transition: all ease-in-out 500ms;
}

#icon-login-msg.success,
#icon-lost-msg.success,
#icon-register-msg.success {
    background-color: #68c3a3 !important;
}

#icon-login-msg.error,
#icon-lost-msg.error,
#icon-register-msg.error {
    background-color: #eb575b !important;
}

#img_logo {
    max-height: 100px;
    max-width: 100px;
}

.modal-backdrop.in {
    filter: alpha(opacity=50);
    opacity: .8;
}

.modal-content {
    background-color: #ececec;
    border: 1px solid #bdc3c7;
    border-radius: 0px;
    outline: 0;
}

.modal-header {
    min-height: 16.43px;
    padding: 15px 15px 15px 15px;
    border-bottom: 0px;
}

.modal-body {
    position: relative;
    padding: 5px 15px 5px 15px;
}

.modal-footer {
    padding: 15px 15px 15px 15px;
    text-align: left;
    border-top: 0px;
}

.checkbox {
    margin-bottom: 0px;
}

.btn {
    border-radius: 3px;
}

.btn:focus,
.btn:active:focus,
.btn.active:focus,
.btn.focus,
.btn:active.focus,
.btn.active.focus {
    outline: none;
}

.btn-lg, .btn-group-lg>.btn {
    border-radius: 0px;
}

.btn-link {
    padding: 5px 10px 0px 0px;
    color: #95a5a6;
}

.btn-link:hover, .btn-link:focus {
    color: #2c3e50;
    text-decoration: none;
}

.glyphicon {
    top: 0px;
}

.form-control {
  border-radius: 0px;
}

</style>        
        
</head>
<body class="hold-transition skin-blue sidebar-mini">

<script type="text/javascript">
function editDetails(id, schemeName, schemeLink, imageUrl){
	$("#hidId").val(id);
	$("#updateSchemeName").val(schemeName);
	$("#updateImageUrl").val(imageUrl);
	$("#updateSchemeLink").val(schemeLink);
}

</script>


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
List<StatementGovernmentSchemes> listOfStateGovernmentSchemesByState = null;
String mainState = null;
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
!request.getAttribute("mapOfstatesWithUrl").equals("") &&
request.getAttribute("listOfStateGovernmentSchemesByState") != null &&
!request.getAttribute("listOfStateGovernmentSchemesByState").equals("") &&
request.getAttribute("mainState") != null &&
!request.getAttribute("mainState").equals("")){
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
	
	listOfStateGovernmentSchemesByState = (List<StatementGovernmentSchemes>)request.getAttribute("listOfStateGovernmentSchemesByState");
	mainState = (String)request.getAttribute("mainState");
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
  
  
  <div class="modal fade" id="login-modal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="left">
				<h3 style="">Edit Company Details</h3>
				</div>
                <div id="div-forms">
                    <form id="editCom" method="post" autocomplete="off" action="<%= request.getContextPath()%>/admin/schemes/<%=mainState %>/updateSchemeDetails">
		                <div class="modal-body">
		                    <input type = "hidden" id = "hidId" name = "hidId">
		                    <br>
		                    <label>Scheme Name</label>
				    		<input id="updateSchemeName" name="updateSchemeName" class="form-control" type="text" placeholder="Edit Scheme Name" required>
				    		<br>
				    		<label>Scheme Link</label>
				    		<input id="updateSchemeLink" name="updateSchemeLink" class="form-control" type="text" placeholder="Edit Scheme Link" required>
				    		<br>
				    		<label>Image Url</label>
				    		<input id = "updateImageUrl" name = "updateImageUrl" class = "form-control" type = "text" placeholder = "Edit Image Url" required>
        		    	</div>
				        <div class="modal-footer">
                            <div>
                                <input type="submit" class="btn btn-primary btn-lg btn-block" value = "Edit"/>
                            </div>
				        </div>
                    </form>
                </div>
                
			</div>
		</div>
	</div>
  
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
    <%
    if(request.getAttribute("recordUpdated") != null && !request.getAttribute("recordUpdated").equals("")){
    	if(request.getAttribute("recordUpdated").equals("true")){
    %>
  <div class="alert alert-success">
    <strong>Success !</strong> Record Updated successfully.
  </div>
    <%
    	}else{
    %>
    <div class="alert alert-danger">
    <strong>Error !</strong> problem updating record please go back and try again
  </div>
    <%		
    	}
    }
    %>
    
    
    <%
    if(request.getAttribute("recordDeleted") != null && !request.getAttribute("recordDeleted").equals("")){
    	if(request.getAttribute("recordDeleted").equals("true")){
    %>
  <div class="alert alert-success">
    <strong>Success !</strong> Record deleted successfully.
  </div>
    <%
    	}else{
    %>
    <div class="alert alert-danger">
    <strong>Error !</strong> problem deleting record please go back and try again
  </div>
    <%		
    	}
    }
    %>
    
    
    <!-- Main content -->
    <section class="content" style = "margin-left: 60px;">
		<div class="row companies">
			<div class="col-lg-12 col-md-12">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
							<th>S. No</th>
							<th>Added Date</th>
							<th>Scheme Date</th>
							<th>Scheme Name</th>
							<th>Scheme Link</th>
							<th>Image Link</th>
						</tr>
						</thead>
						<tbody>
						<%
						int i = 0;
						if(listOfStateGovernmentSchemesByState != null){
						for(StatementGovernmentSchemes stateGovernmentSchemesByState : listOfStateGovernmentSchemesByState){
							String[] relatedVideosUrlArray = stateGovernmentSchemesByState.getVideosUrl().split("####%%%%####&&&&");
							String videosString = "";
			                if(relatedVideosUrlArray != null && relatedVideosUrlArray.length != 0){
			                	ArrayList<String> newrelatedVideosUrlArrayList = new ArrayList<String>();
			                	for(String relatedVideo : relatedVideosUrlArray){
			                		if(!newrelatedVideosUrlArrayList.contains(relatedVideo)){
			                			newrelatedVideosUrlArrayList.add(relatedVideo);
			                		}
			                	}
			                	
			                	for(String relatedVideo : newrelatedVideosUrlArrayList){
			                		if(relatedVideo.contains("youtube")){
			                			String headingUrlSplit[] = relatedVideo.split("--------");
			                			videosString = videosString + "<a href = '"+ headingUrlSplit[0] + "'>" + headingUrlSplit[0] + "</a> ,";
			                		}
			                	}
			                }
						%>
						<tr>
							<td><%=++i %></td>
							<td><%=stateGovernmentSchemesByState.getAddedDate() %></td>
							<td><%=stateGovernmentSchemesByState.getActualLastModifiedDate() %></td>
							<td><%=stateGovernmentSchemesByState.getSchemeName().trim() %></td>
							<td><a href = "<%=stateGovernmentSchemesByState.getSchemeLink() %>"><%=stateGovernmentSchemesByState.getSchemeLink().trim() %></a></td>
							<td><a href = "<%=stateGovernmentSchemesByState.getImageUrl() %>"><%=stateGovernmentSchemesByState.getImageUrl().trim() %></a></td>
							<td>
							   <form method = "post" action = "<%=request.getContextPath() %>/admin/schemes/<%=mainState %>/deleteScheme">
							        <input type = "hidden" value = "<%=stateGovernmentSchemesByState.getId() %>" name = "delId" id = "delId">
							        <input type = "submit" value = "Delete" class = "btn btn-danger">
							   </form>
							   
							   <div class = "form-group" style = "margin-top: 8px;">
							       <a href="#" class="btn btn-warning" id = "aId" value = "45" role="button" data-toggle="modal" data-target="#login-modal2" onclick = 'editDetails("<%=stateGovernmentSchemesByState.getId()%>", "<%=stateGovernmentSchemesByState.getSchemeName() %>", "<%=stateGovernmentSchemesByState.getSchemeLink() %>", "<%=stateGovernmentSchemesByState.getImageUrl() %>")' style = "padding:19px; padding-top:6px; padding-bottom:6px;">Edit</a>
							   </div>
							   
							</td>
						</tr>
						<%
			                }
						}
						%>
						</tbody>
					</table>
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
    <strong>Copyright &copy; 2017 <a href="https://www.enggcell.com">EnggCell</a>.</strong> All rights
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
