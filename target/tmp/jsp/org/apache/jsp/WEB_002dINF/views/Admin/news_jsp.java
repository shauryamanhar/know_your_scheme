/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.3.0.v20150612
 * Generated at: 2018-02-04 05:57:35 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.enggcell.entities.News;
import com.enggcell.entities.TempUser;
import com.enggcell.entities.GovernmentWebsites;
import com.enggcell.entities.GovernmentJobs;
import com.enggcell.entities.StatementGovernmentSchemes;
import com.enggcell.entities.CentralGovernmentSchemes;
import java.util.List;
import com.enggcell.entities.ApplicationForms;
import com.enggcell.entities.UserCategories;
import com.enggcell.entities.User;
import java.util.HashMap;
import java.util.ArrayList;

public final class news_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.enggcell.entities.User");
    _jspx_imports_classes.add("com.enggcell.entities.UserCategories");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.enggcell.entities.News");
    _jspx_imports_classes.add("com.enggcell.entities.TempUser");
    _jspx_imports_classes.add("com.enggcell.entities.ApplicationForms");
    _jspx_imports_classes.add("com.enggcell.entities.GovernmentJobs");
    _jspx_imports_classes.add("com.enggcell.entities.CentralGovernmentSchemes");
    _jspx_imports_classes.add("java.util.HashMap");
    _jspx_imports_classes.add("com.enggcell.entities.StatementGovernmentSchemes");
    _jspx_imports_classes.add("com.enggcell.entities.GovernmentWebsites");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;



response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);



      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("  <meta charset=\"utf-8\">\r\n");
      out.write("  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("  <title>Admin KnowYourScheme</title>\r\n");
      out.write("  <!-- Tell the browser to be responsive to screen width -->\r\n");
      out.write("  <meta content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\" name=\"viewport\">\r\n");
      out.write("  <!-- Bootstrap 3.3.7 -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/assets/bower_components/bootstrap/dist/css/bootstrap.min.css\">\r\n");
      out.write("  <!-- Font Awesome -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/assets/bower_components/font-awesome/css/font-awesome.min.css\">\r\n");
      out.write("  <!-- Ionicons -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/assets/bower_components/Ionicons/css/ionicons.min.css\">\r\n");
      out.write("  <!-- jvectormap -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/assets/bower_components/jvectormap/jquery-jvectormap.css\">\r\n");
      out.write("  <!-- Theme style -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/assets/dist/css/AdminLTE.min.css\">\r\n");
      out.write("  <!-- AdminLTE Skins. Choose a skin from the css/skins\r\n");
      out.write("       folder instead of downloading all of them to reduce the load. -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/assets/dist/css/skins/_all-skins.min.css\">\r\n");
      out.write("\r\n");
      out.write("  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\r\n");
      out.write("  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\r\n");
      out.write("  <!--[if lt IE 9]>\r\n");
      out.write("  <script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\r\n");
      out.write("  <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\r\n");
      out.write("  <![endif]-->\r\n");
      out.write("\r\n");
      out.write("  <!-- Google Font -->\r\n");
      out.write("  <link rel=\"stylesheet\"\r\n");
      out.write("        href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic\">\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"hold-transition skin-blue sidebar-mini\">\r\n");
      out.write("\r\n");


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





      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"wrapper\">\r\n");
      out.write("\r\n");
      out.write("  <header class=\"main-header\">\r\n");
      out.write("\r\n");
      out.write("    <!-- Logo -->\r\n");
      out.write("    <a href=\"index2.html\" class=\"logo\">\r\n");
      out.write("      <!-- mini logo for sidebar mini 50x50 pixels -->\r\n");
      out.write("      <span class=\"logo-mini\"><b>KYS</b></span>\r\n");
      out.write("      <!-- logo for regular state and mobile devices -->\r\n");
      out.write("      <span class=\"logo-lg\"><b>Admin</b> KYS</span>\r\n");
      out.write("    </a>\r\n");
      out.write("\r\n");
      out.write("    <!-- Header Navbar: style can be found in header.less -->\r\n");
      out.write("    <nav class=\"navbar navbar-static-top\">\r\n");
      out.write("      <!-- Sidebar toggle button-->\r\n");
      out.write("      <a href=\"#\" class=\"sidebar-toggle\" data-toggle=\"push-menu\" role=\"button\">\r\n");
      out.write("        <span class=\"sr-only\">Toggle navigation</span>\r\n");
      out.write("      </a>\r\n");
      out.write("    </nav>\r\n");
      out.write("  </header>\r\n");
      out.write("  <!-- Left side column. contains the logo and sidebar -->\r\n");
      out.write("  <aside class=\"main-sidebar\" style = \"width: 300px;\">\r\n");
      out.write("    <!-- sidebar: style can be found in sidebar.less -->\r\n");
      out.write("    <section class=\"sidebar\">\r\n");
      out.write("      <!-- /.search form -->\r\n");
      out.write("      <!-- sidebar menu: : style can be found in sidebar.less -->\r\n");
      out.write("      <ul class=\"sidebar-menu\" data-widget=\"tree\">\r\n");
      out.write("        <li class=\"header\">MAIN NAVIGATION</li>\r\n");
      out.write("        <li class=\"active treeview menu-open\">\r\n");
      out.write("          <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin\">\r\n");
      out.write("            <i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>\r\n");
      out.write("          </a>\r\n");
      out.write("          <ul class=\"treeview-menu\">\r\n");
      out.write("          </ul>\r\n");
      out.write("        </li>\r\n");
      out.write("        \r\n");
      out.write("        <li class=\"treeview\">\r\n");
      out.write("          <a href=\"#\">\r\n");
      out.write("            <i class=\"fa fa-laptop\"></i>\r\n");
      out.write("            <span>User</span>\r\n");
      out.write("            <span class=\"pull-right-container\">\r\n");
      out.write("              <i class=\"fa fa-angle-left pull-right\"></i>\r\n");
      out.write("            </span>\r\n");
      out.write("          </a>\r\n");
      out.write("          <ul class=\"treeview-menu\">\r\n");
      out.write("            <li><a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/user/registered-users\"><i class=\"fa fa-circle-o\"></i>Registered Users</a></li>\r\n");
      out.write("            <li><a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/user/temporary-users\"><i class=\"fa fa-circle-o\"></i>Temporary Users</a></li>\r\n");
      out.write("          </ul>\r\n");
      out.write("        </li>\r\n");
      out.write("        \r\n");
      out.write("        <li class=\"treeview\">\r\n");
      out.write("          <a href=\"#\">\r\n");
      out.write("            <i class=\"fa fa-laptop\"></i>\r\n");
      out.write("            <span>Schemes</span>\r\n");
      out.write("            <span class=\"pull-right-container\">\r\n");
      out.write("              <i class=\"fa fa-angle-left pull-right\"></i>\r\n");
      out.write("            </span>\r\n");
      out.write("          </a>\r\n");
      out.write("          <ul class=\"treeview-menu\">\r\n");
      out.write("          ");

          if(statesForUrl != null && mapOfstatesWithUrl != null){
        	  for(String stateForU : statesForUrl){
          
      out.write("\r\n");
      out.write("            <li><a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/schemes/");
      out.print(stateForU);
      out.write("\"><i class=\"fa fa-circle-o\"></i>");
      out.print(mapOfstatesWithUrl.get(stateForU) );
      out.write("</a></li>\r\n");
      out.write("          ");

        	  }
          }
          
      out.write("  \r\n");
      out.write("          </ul>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"treeview\">\r\n");
      out.write("          <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/schemes/Application-Forms\">\r\n");
      out.write("            <i class=\"fa fa-laptop\"></i>\r\n");
      out.write("            <span>Application Forms</span>\r\n");
      out.write("          </a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"treeview\">\r\n");
      out.write("          <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/schemes/Government-Jobs\">\r\n");
      out.write("            <i class=\"fa fa-laptop\"></i>\r\n");
      out.write("            <span>Government Jobs</span>\r\n");
      out.write("          </a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"treeview\">\r\n");
      out.write("          <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/schemes/Government-Websites\">\r\n");
      out.write("            <i class=\"fa fa-laptop\"></i>\r\n");
      out.write("            <span>Government Websites</span>\r\n");
      out.write("          </a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"treeview\">\r\n");
      out.write("          <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/news\">\r\n");
      out.write("            <i class=\"fa fa-laptop\"></i>\r\n");
      out.write("            <span>News</span>\r\n");
      out.write("          </a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"treeview\">\r\n");
      out.write("          <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/data-gather\">\r\n");
      out.write("            <i class=\"fa fa-laptop\"></i>\r\n");
      out.write("            <span>Data Gather</span>\r\n");
      out.write("          </a>\r\n");
      out.write("        </li>\r\n");
      out.write("      </ul>\r\n");
      out.write("    </section>\r\n");
      out.write("    <!-- /.sidebar -->\r\n");
      out.write("  </aside>\r\n");
      out.write("\r\n");
      out.write("  <!-- Content Wrapper. Contains page content -->\r\n");
      out.write("  <div class=\"content-wrapper\">\r\n");
      out.write("    <!-- Content Header (Page header) -->\r\n");
      out.write("\r\n");
      out.write("    <!-- Main content -->\r\n");
      out.write("    <section class=\"content\" style = \"margin-left: 60px;\">\r\n");
      out.write("\t\t<div class=\"row companies\">\r\n");
      out.write("\t\t\t<div class=\"col-lg-12 col-md-12\">\r\n");
      out.write("\t\t\t\t<div class=\"table-responsive\">\r\n");
      out.write("\t\t\t\t\t<table class=\"table table-striped table-bordered table-hover\">\r\n");
      out.write("\t\t\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<th>S. No</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>News Headline</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>News Link</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>News Summary</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>Category</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>Image Link</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>Added Date</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>News Date</th>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t");

						int i = 0;
						for(News newsOne : listOfNews){
						
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>");
      out.print(++i );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>");
      out.print(newsOne.getNewsHeadline() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><a href = \"");
      out.print(newsOne.getNewsLink() );
      out.write('"');
      out.write('>');
      out.print(newsOne.getNewsLink() );
      out.write("</a></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>");
      out.print(newsOne.getNewsSummary() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>");
      out.print(newsOne.getTypeOfNews() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>");
      out.print(newsOne.getImageUrl() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>");
      out.print(newsOne.getAddedDate() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>");
      out.print(newsOne.getNewsDate() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t");

						}
						
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("      <!-- /.row -->\r\n");
      out.write("\r\n");
      out.write("    </section>\r\n");
      out.write("    <!-- /.content -->\r\n");
      out.write("  </div>\r\n");
      out.write("  <!-- /.content-wrapper -->\r\n");
      out.write("\r\n");
      out.write("  <footer class=\"main-footer\">\r\n");
      out.write("    <div class=\"pull-right hidden-xs\">\r\n");
      out.write("    </div>\r\n");
      out.write("    <strong>Copyright &copy; 2017 <a href=\"https://www.enggcell.com\">EnggCell</a>.</strong> All rights\r\n");
      out.write("    reserved.\r\n");
      out.write("  </footer>\r\n");
      out.write("  <!-- /.control-sidebar -->\r\n");
      out.write("  <!-- Add the sidebar's background. This div must be placed\r\n");
      out.write("       immediately after the control sidebar -->\r\n");
      out.write("  <div class=\"control-sidebar-bg\"></div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("<!-- ./wrapper -->\r\n");
      out.write("\r\n");
      out.write("<!-- jQuery 3 -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/assets/bower_components/jquery/dist/jquery.min.js\"></script>\r\n");
      out.write("<!-- Bootstrap 3.3.7 -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/assets/bower_components/bootstrap/dist/js/bootstrap.min.js\"></script>\r\n");
      out.write("<!-- FastClick -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/assets/bower_components/fastclick/lib/fastclick.js\"></script>\r\n");
      out.write("<!-- AdminLTE App -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/assets/dist/js/adminlte.min.js\"></script>\r\n");
      out.write("<!-- Sparkline -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/assets/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js\"></script>\r\n");
      out.write("<!-- jvectormap  -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/assets/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js\"></script>\r\n");
      out.write("<!-- SlimScroll -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/assets/bower_components/jquery-slimscroll/jquery.slimscroll.min.js\"></script>\r\n");
      out.write("<!-- ChartJS -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/assets/bower_components/Chart.js/Chart.js\"></script>\r\n");
      out.write("<!-- AdminLTE dashboard demo (This is only for demo purposes) -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/assets/dist/js/pages/dashboard2.js\"></script>\r\n");
      out.write("<!-- AdminLTE for demo purposes -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/assets/dist/js/demo.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
