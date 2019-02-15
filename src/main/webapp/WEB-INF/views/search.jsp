<%

response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

%>

<%@page import="com.enggcell.entities.UserCategories"%>
<%@page import="com.enggcell.entities.User"%>
<%@page import="com.enggcell.entities.News"%>
<%@page import="com.enggcell.entities.ApplicationForms"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.enggcell.entities.StatementGovernmentSchemes"%>
<%@page import="com.enggcell.services.StateGovernmentSchemesService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]><html class="lt-ie9 lt-ie8 lt-ie7" lang="en" dir="ltr"><![endif]-->
<!--[if IE 7]><html class="lt-ie9 lt-ie8" lang="en" dir="ltr"><![endif]-->
<!--[if IE 8]><html class="lt-ie9" lang="en" dir="ltr"><![endif]-->
<!--[if gt IE 8]><!-->

<%@page import="com.enggcell.entities.CentralGovernmentSchemes"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility"%>
<%@page import="java.nio.ByteBuffer"%>
<%@page import="java.nio.CharBuffer"%>
<%@page import="java.nio.charset.Charset"%>
<%@page import="com.google.cloud.translate.Translate"%>
<%@page import="com.google.cloud.translate.Language"%>
<%@page import="com.google.cloud.translate.Translate.TranslateOption"%>
<%@page import="com.google.cloud.translate.Detection"%>
<%@page import="com.google.cloud.translate.TranslateOptions"%>
<%@page import="com.google.cloud.translate.Translation"%>
<%@page import="com.google.cloud.translate.Translate.TranslateOption"%>
<%@page import="java.util.Locale"%>
<html lang="en" dir="ltr" prefix="content: http://purl.org/rss/1.0/modules/content/ dc: http://purl.org/dc/terms/ foaf: http://xmlns.com/foaf/0.1/ og: http://ogp.me/ns# rdfs: http://www.w3.org/2000/01/rdf-schema# sioc: http://rdfs.org/sioc/ns# sioct: http://rdfs.org/sioc/types# skos: http://www.w3.org/2004/02/skos/core# xsd: http://www.w3.org/2001/XMLSchema#"><!--<![endif]-->

<!-- Mirrored from www.india.gov.in/ by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 22 Jan 2018 17:06:38 GMT -->
<!-- Added by HTTrack --><meta http-equiv="content-type" content="text/html;charset=utf-8" /><!-- /Added by HTTrack -->

<style>


.my_sidebar_head{
   margin-left: 26px;
   width: 95%;
   border-bottom: 3px solid #e6e6e6;
   display: block;
   position: relative;
   margin-left: 32px;
   width: 90%;
   float: left;
}

@media screen and (max-width: 1100px){
.my_sidebar_head i.fa-sitemap {
    left: -30px;
}
}
@media screen and (max-width: 1500px){
.my_sidebar_head i.fa-sitemap {
    left: -30px;
}
}
@media screen and (max-width: 950px){
.my_sidebar_head i {
    margin-top: 0;
}
}
.my_sidebar_head i {
    background: #006cb7 none repeat scroll 0 0;
    border-radius: 100%;
    color: #fff;
    font-size: 16px!important;
    left: -30px;
    line-height: normal;
    padding: 4px;
    position: absolute;
    margin-top: 2px;
}
.my_sidebar_head span {
    background: url(../images/title-arrow.png) no-repeat scroll 12px 104% rgba(0, 0, 0, 0)!important;
    border-bottom: 3px solid #4f76b1!important;
    float: left!important;
    font-family: "roboto_slabbold"!important;
    font-size: 1.6em!important;
    font-weight: normal!important;
    line-height: 1.2em!important;
    padding: 1px 0 11px 5px!important;
    margin-bottom: -3px!important;
}

.my_sidebar_head_2 span{
    float: left!important;
    font-family: "roboto_slabbold"!important;
    font-size: 1.6em!important;
    font-weight: normal!important;
    line-height: 1.2em!important;
    padding: 1px 0 11px 5px!important;
    margin-top: 10px;!important;
    margin-left:25px;
}
</style>

<%
if(session.getAttribute("knowYourSchemeUser") == null){
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="<%=request.getContextPath() %>/assets/sites/upload_files/npi/files/favicon_0.ico" type="image/vnd.microsoft.icon" />
<title>KnowYourScheme</title>
<meta name="keywords" content="India, India Portal, National Portal of India, Government Information, Government Services, National Informatics Centre, NIC, nic,  e-Government, e-Governance in India, MeitY" />
<meta name="description" content="National Portal of India is a Mission Mode Project under the National E-Governance Plan, designed and developed by National Informatics Centre (NIC), Ministry of Electronics & Information Technology, Government of India. It has been developed with an objective to enable a single window access to information and services being provided by the various Indian Government entities." />

<meta name="title" content="National Portal of India" />
<meta name="lang" content="en" />
<meta name="MobileOptimized" content="width" />
<meta name="HandheldFriendly" content="true" />
<meta name="viewport" content="width=device-width, initial-scale=1" />


<style>
@import url("<%=request.getContextPath() %>/assets/modules/system/system.baseb730.css");
@import url("<%=request.getContextPath() %>/assets/modules/system/system.menusb730.css");
@import url("<%=request.getContextPath() %>/assets/modules/system/system.messagesb730.css");
</style>
<style>
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/themes/base/minified/jquery.ui.core.minb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/themes/base/minified/jquery.ui.theme.minb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/themes/base/minified/jquery.ui.button.minb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/themes/base/minified/jquery.ui.resizable.minb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/themes/base/minified/jquery.ui.dialog.minb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/views_slideshow/views_slideshowb730.css?p1xei9");
</style>
<style>
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/calendar/css/calendar_multidayb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/date/date_api/dateb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/date/date_popup/themes/datepicker.1.7b730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/date/date_repeat_field/date_repeat_fieldb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/modules/field/theme/fieldb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/logintoboggan/logintobogganb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/modules/node/nodeb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/modules/search/searchb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/modules/user/userb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/views/css/viewsb730.css?p1xei9");
</style>
<style>
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/ctools/css/ctoolsb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/event_calendar/event_popup/css/event_popupb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/panels/css/panelsb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/panels/plugins/layouts/threecol_25_50_25/threecol_25_50_25b730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/quicktabs/css/quicktabsb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/panels/plugins/layouts/onecol/onecolb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/flexslider/assets/css/flexslider_imgb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/libraries/flexslider/flexsliderb730.css?p1xei9");
</style>
<style media="screen">
@import url("<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/at_core/css/at.layoutb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/css/global.custom.responsiveb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/css/global.baseb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/css/global.stylesb730.css?p1xei9");
</style>
<style media="print">
@import url("<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/css/printb730.css?p1xei9");
</style>
<style media="screen">
@import url("<%=request.getContextPath() %>/assets/sites/upload_files/npi/files/adaptivetheme/npi_adaptive_files/npi_adaptive.default.layoutb730.css?p1xei9");
</style>
<link href="<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/css/override.css" rel="alternate stylesheet" type="text/css" media="screen" title="change" />
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/jquery/1.8/jquery.mina532.js?v=1.8.3"></script>
<script src="<%=request.getContextPath() %>/assets/misc/jquery.once7839.js?v=1.2"></script>
<script src="<%=request.getContextPath() %>/assets/misc/drupalb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.core.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.widget.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.button.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.mouse.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.draggable.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.position.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.resizable.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.dialog.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/external/jquery.cookie1683.js?v=67fb34f6a866c40d0570"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/jquery.form/3/jquery.form.min4f81.js?v=3.51.0"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/views_slideshow/js/views_slideshowc619.js?v=1.0"></script>
<script src="<%=request.getContextPath() %>/assets/misc/ajaxdf1a.js?v=7.56"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/js/jquery_update241d.js?v=0.0.1"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/admin_menu/admin_devel/admin_develb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/customs/hb_documents/hb_documentsb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/customs/hb_forms/hb_formsb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/customs/hb_schemes/hb_schemesb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/customs/webform_email_reply/scriptb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/misc/progressb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/event_calendar/event_popup/js/event_popupb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/event_calendar/event_popup/js/event_popup_validateb730.js?p1xei9"></script>
<script>jQuery(document).ready(function($) { 
    $.expr[':'].regex = function(elem, index, match) {
            var matchParams = match[3].split(','),
            validLabels = /^(data|css):/,
            attr = {
                method: matchParams[0].match(validLabels) ? 
                matchParams[0].split(':')[0] : 'attr',
                property: matchParams.shift().replace(validLabels,'')
            },
            regexFlags = 'ig',
            regex = new RegExp(matchParams.join('').replace(/^\s+|\s+$/g,''), regexFlags);
            return regex.test(jQuery(elem)[attr.method](attr.property));
        }
    });</script>
<script>var base_url ="index.html"; var themePath = ""; var modulePath = "";</script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/customs/npi_security/scriptb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/views/js/baseb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/quicktabs/js/quicktabsb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/views/js/ajax_viewb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/libraries/flexslider/jquery.flexslider-minb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/flexslider_views_slideshow/js/flexslider_views_slideshowb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/scripts/jquery.minb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/scripts/customb730.js?p1xei9"></script>
<script>jQuery.extend(Drupal.settings, {"basePath":"\/","pathPrefix":"","ajaxPageState":{"theme":"npi_adaptive","theme_token":"soC10pNDnXd4LdAiJtHIi6vzJVzJR9L31cUh3mfDp6s","jquery_version":"1.8","js":{"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/jquery\/1.8\/jquery.min.js":1,"misc\/jquery.once.js":1,"misc\/drupal.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.core.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.widget.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.button.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.mouse.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.draggable.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.position.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.resizable.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.dialog.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/external\/jquery.cookie.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/jquery.form\/3\/jquery.form.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/views_slideshow\/js\/views_slideshow.js":1,"misc\/ajax.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/js\/jquery_update.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/admin_menu\/admin_devel\/admin_devel.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/customs\/hb_documents\/hb_documents.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/customs\/hb_forms\/hb_forms.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/customs\/hb_schemes\/hb_schemes.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/customs\/webform_email_reply\/script.js":1,"misc\/progress.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/event_calendar\/event_popup\/js\/event_popup.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/event_calendar\/event_popup\/js\/event_popup_validate.js":1,"0":1,"1":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/customs\/npi_security\/script.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/views\/js\/base.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/quicktabs\/js\/quicktabs.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/views\/js\/ajax_view.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/libraries\/flexslider\/jquery.flexslider-min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/flexslider_views_slideshow\/js\/flexslider_views_slideshow.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/npi_adaptive\/scripts\/jquery.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/npi_adaptive\/scripts\/custom.js":1},"css":{"modules\/system\/system.base.css":1,"modules\/system\/system.menus.css":1,"modules\/system\/system.messages.css":1,"misc\/ui\/jquery.ui.core.css":1,"misc\/ui\/jquery.ui.theme.css":1,"misc\/ui\/jquery.ui.button.css":1,"misc\/ui\/jquery.ui.resizable.css":1,"misc\/ui\/jquery.ui.dialog.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/views_slideshow\/views_slideshow.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/calendar\/css\/calendar_multiday.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/date\/date_api\/date.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/date\/date_popup\/themes\/datepicker.1.7.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/date\/date_repeat_field\/date_repeat_field.css":1,"modules\/field\/theme\/field.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/logintoboggan\/logintoboggan.css":1,"modules\/node\/node.css":1,"modules\/search\/search.css":1,"modules\/user\/user.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/views\/css\/views.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/ctools\/css\/ctools.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/event_calendar\/event_popup\/css\/event_popup.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/panels\/css\/panels.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/panels\/plugins\/layouts\/threecol_25_50_25\/threecol_25_50_25.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/quicktabs\/css\/quicktabs.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/panels\/plugins\/layouts\/onecol\/onecol.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/flexslider\/assets\/css\/flexslider_img.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/libraries\/flexslider\/flexslider.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/at_core\/css\/at.layout.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/npi_adaptive\/css\/global.custom.responsive.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/npi_adaptive\/css\/global.base.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/npi_adaptive\/css\/global.styles.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/npi_adaptive\/css\/print.css":1,"public:\/\/adaptivetheme\/npi_adaptive_files\/npi_adaptive.default.layout.css":1}},"encrypt_submissions":{"baseUrl":"https:\/\/www.india.gov.in"},"event_popup":{"classes":"","defaults":"width:300;height:auto;position:[center,60]","selector":"content","content_type":"event_calendar","op":false},"better_exposed_filters":{"views":{"news_feeds":{"displays":{"block":{"filters":[]}}},"metadata":{"displays":{"block_1":{"filters":[]}}},"featured":{"displays":{"block":{"filters":[]}}},"spotlight":{"displays":{"block":{"filters":[]}}},"most_search":{"displays":{"block":{"filters":[]}}},"home_main_slider":{"displays":{"block":{"filters":[]}}}}},"ajax":{"quicktabs-tab-_none_-0":{"progress":{"message":"","type":"throbber"},"event":"click","url":"\/quicktabs\/ajax\/_none_\/0\/view\/news_feeds\/block\/1\/home\/"},"quicktabs-tab-_none_-1":{"progress":{"message":"","type":"throbber"},"event":"click","url":"\/quicktabs\/ajax\/_none_\/1\/view\/metadata\/block_7\/2\/home\/"}},"urlIsAjaxTrusted":{"\/quicktabs\/ajax\/_none_\/0\/view\/news_feeds\/block\/1\/home\/":true,"\/quicktabs\/ajax\/_none_\/1\/view\/metadata\/block_7\/2\/home\/":true,"\/views\/ajax":true,"\/search\/node":true,"\/advance-search\/results":true},"quicktabs":{"qt__none_":{"name":"_none_","tabs":[{"vid":"news_feeds","display":"block","args":"","view_path":"home","view_dom_id":1,"ajax_args":"","actual_args":[]},{"vid":"metadata","display":"block_7","args":"","view_path":"home","view_dom_id":2,"ajax_args":"","actual_args":[]}],"ajaxPageState":{"jquery_version":"1.8"}}},"views":{"ajax_path":"\/views\/ajax","ajaxViews":{"views_dom_id:c8b9322252e3079eaef79220fd17a99f":{"view_name":"metadata","view_display_id":"block_1","view_args":"166659","view_path":"home","view_base_path":"admin\/metadata-online","view_dom_id":"c8b9322252e3079eaef79220fd17a99f","pager_element":0},"views_dom_id:23bc96e9dbd3a1db43950c661786a796":{"view_name":"metadata","view_display_id":"block_1","view_args":"166856","view_path":"home","view_base_path":"admin\/metadata-online","view_dom_id":"23bc96e9dbd3a1db43950c661786a796","pager_element":0},"views_dom_id:3cc54e956246bcc1ea9ac4b2ef33ce2e":{"view_name":"spotlight","view_display_id":"block","view_args":"","view_path":"home","view_base_path":"spotlight","view_dom_id":"3cc54e956246bcc1ea9ac4b2ef33ce2e","pager_element":0}}},"viewsSlideshow":{"home_main_slider-block":{"methods":{"goToSlide":["viewsSlideshowPager","viewsSlideshowSlideCounter","viewsSlideshowCycle"],"nextSlide":["viewsSlideshowPager","viewsSlideshowSlideCounter","flexsliderViewsSlideshow","viewsSlideshowCycle"],"pause":["viewsSlideshowControls","flexsliderViewsSlideshow","viewsSlideshowCycle"],"play":["viewsSlideshowControls","flexsliderViewsSlideshow","viewsSlideshowCycle"],"previousSlide":["viewsSlideshowPager","viewsSlideshowSlideCounter","flexsliderViewsSlideshow","viewsSlideshowCycle"],"transitionBegin":["viewsSlideshowPager","viewsSlideshowSlideCounter"],"transitionEnd":[]},"paused":0}},"flexslider_views_slideshow":{"#flexslider_views_slideshow_main_home_main_slider-block":{"num_divs":6,"id_prefix":"#flexslider_views_slideshow_main_","vss_id":"home_main_slider-block","namespace":"flex-","selector":".slides \u003E li","easing":"linear","direction":"horizontal","reverse":false,"smoothHeight":false,"startAt":0,"animationSpeed":600,"initDelay":0,"useCSS":false,"touch":true,"video":false,"keyboard":true,"multipleKeyboard":false,"mousewheel":0,"controlsContainer":".flex-control-nav-container","sync":"","asNavFor":"","itemWidth":0,"itemMargin":0,"minItems":0,"maxItems":0,"move":0,"animation":"slide","slideshow":true,"slideshowSpeed":7000,"directionNav":true,"controlNav":true,"prevText":"","nextText":"","pausePlay":true,"pauseText":"","playText":"","randomize":true,"thumbCaptions":false,"thumbCaptionsBoth":false,"animationLoop":true,"pauseOnAction":true,"pauseOnHover":false,"manualControls":""}},"adaptivetheme":{"npi_adaptive":{"layout_settings":{"bigscreen":"three-col-grail","tablet_landscape":"three-col-grail","tablet_portrait":"one-col-vert","smalltouch_landscape":"one-col-vert","smalltouch_portrait":"one-col-stack"},"media_query_settings":{"bigscreen":"only screen and (min-width:1025px)","tablet_landscape":"only screen and (min-width:769px) and (max-width:1024px)","tablet_portrait":"only screen and (min-width:581px) and (max-width:768px)","smalltouch_landscape":"only screen and (min-width:321px) and (max-width:580px)","smalltouch_portrait":"only screen and (max-width:320px)"}}}});</script>
<!--[if lt IE 9]>
<script src="https://www.india.gov.in/sites/all/themes/adaptivetheme/at_core/scripts/html5.js?p1xei9"></script>
<![endif]-->

 <style>

ul.dashboard-nav li{
   display:inline;
}

.dashboard-active{
   background-color:#ffeb3b;
}
</style>
</head>
<%
}else{
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="<%=request.getContextPath() %>/assets/sites/upload_files/npi/files/favicon_0.ico" type="image/vnd.microsoft.icon" />
<title>KnowYourScheme</title>
<meta name="keywords" content="India, India Portal, National Portal of India, Government Information, Government Services, National Informatics Centre, NIC, nic,  e-Government, e-Governance in India, MeitY" />
<meta name="description" content="National Portal of India is a Mission Mode Project under the National E-Governance Plan, designed and developed by National Informatics Centre (NIC), Ministry of Electronics & Information Technology, Government of India. It has been developed with an objective to enable a single window access to information and services being provided by the various Indian Government entities." />

<meta name="title" content="National Portal of India" />
<meta name="lang" content="en" />
<meta name="MobileOptimized" content="width" />
<meta name="HandheldFriendly" content="true" />
<meta name="viewport" content="width=device-width, initial-scale=1" />


<style>
@import url("<%=request.getContextPath() %>/assets/modules/system/system.baseb730.css");
@import url("<%=request.getContextPath() %>/assets/modules/system/system.menusb730.css");
@import url("<%=request.getContextPath() %>/assets/modules/system/system.messagesb730.css");
</style>
<style>
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/themes/base/minified/jquery.ui.core.minb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/themes/base/minified/jquery.ui.theme.minb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/themes/base/minified/jquery.ui.button.minb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/themes/base/minified/jquery.ui.resizable.minb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/themes/base/minified/jquery.ui.dialog.minb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/views_slideshow/views_slideshowb730.css?p1xei9");
</style>
<style type="text/css">
/*marquee{
  font-family:oswald;
  color:#fff;
  background:#333;
  padding:10px;
  width:200px;
  height:400px;
  text-align:justify;
  border:5px solid #555;
  border-radius:10px;
  box-shadow:inset 0px 0px 100px #c00c00;
}*/
</style>
<style>
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/calendar/css/calendar_multidayb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/date/date_api/dateb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/date/date_popup/themes/datepicker.1.7b730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/date/date_repeat_field/date_repeat_fieldb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/modules/field/theme/fieldb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/logintoboggan/logintobogganb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/modules/node/nodeb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/modules/search/searchb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/modules/user/userb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/views/css/viewsb730.css?p1xei9");
</style>
<style>
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/ctools/css/ctoolsb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/event_calendar/event_popup/css/event_popupb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/panels/css/panelsb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/panels/plugins/layouts/threecol_25_50_25/threecol_25_50_25b730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/quicktabs/css/quicktabsb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/panels/plugins/layouts/onecol/onecolb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/modules/contributed/flexslider/assets/css/flexslider_imgb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/libraries/flexslider/flexsliderb730.css?p1xei9");
</style>
<style media="screen">
@import url("<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/at_core/css/at.layoutb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/css/global.custom.responsiveb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/css/global.baseb730.css?p1xei9");
@import url("<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/css/global.stylesb730.css?p1xei9");
</style>
<style media="print">
@import url("<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/css/printb730.css?p1xei9");
</style>
<style media="screen">
@import url("<%=request.getContextPath() %>/assets/sites/upload_files/npi/files/adaptivetheme/npi_adaptive_files/npi_adaptive.default.layoutb730.css?p1xei9");
</style>
<link href="<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/css/override.css" rel="alternate stylesheet" type="text/css" media="screen" title="change" />
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/jquery/1.8/jquery.mina532.js?v=1.8.3"></script>
<script src="<%=request.getContextPath() %>/assets/misc/jquery.once7839.js?v=1.2"></script>
<script src="<%=request.getContextPath() %>/assets/misc/drupalb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.core.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.widget.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.button.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.mouse.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.draggable.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.position.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.resizable.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/ui/minified/jquery.ui.dialog.min468f.js?v=1.10.2"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/ui/external/jquery.cookie1683.js?v=67fb34f6a866c40d0570"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/replace/jquery.form/3/jquery.form.min4f81.js?v=3.51.0"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/views_slideshow/js/views_slideshowc619.js?v=1.0"></script>
<script src="<%=request.getContextPath() %>/assets/misc/ajaxdf1a.js?v=7.56"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/jquery_update/js/jquery_update241d.js?v=0.0.1"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/admin_menu/admin_devel/admin_develb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/customs/hb_documents/hb_documentsb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/customs/hb_forms/hb_formsb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/customs/hb_schemes/hb_schemesb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/customs/webform_email_reply/scriptb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/misc/progressb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/event_calendar/event_popup/js/event_popupb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/event_calendar/event_popup/js/event_popup_validateb730.js?p1xei9"></script>
<script>jQuery(document).ready(function($) { 
    $.expr[':'].regex = function(elem, index, match) {
            var matchParams = match[3].split(','),
            validLabels = /^(data|css):/,
            attr = {
                method: matchParams[0].match(validLabels) ? 
                matchParams[0].split(':')[0] : 'attr',
                property: matchParams.shift().replace(validLabels,'')
            },
            regexFlags = 'ig',
            regex = new RegExp(matchParams.join('').replace(/^\s+|\s+$/g,''), regexFlags);
            return regex.test(jQuery(elem)[attr.method](attr.property));
        }
    });</script>
<script>var base_url ="index.html"; var themePath = ""; var modulePath = "";</script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/customs/npi_security/scriptb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/views/js/baseb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/quicktabs/js/quicktabsb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/views/js/ajax_viewb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/libraries/flexslider/jquery.flexslider-minb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/modules/contributed/flexslider_views_slideshow/js/flexslider_views_slideshowb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/scripts/jquery.minb730.js?p1xei9"></script>
<script src="<%=request.getContextPath() %>/assets/sites/all/themes/adaptivetheme/npi_adaptive/scripts/customb730.js?p1xei9"></script>
<script>jQuery.extend(Drupal.settings, {"basePath":"\/","pathPrefix":"","ajaxPageState":{"theme":"npi_adaptive","theme_token":"soC10pNDnXd4LdAiJtHIi6vzJVzJR9L31cUh3mfDp6s","jquery_version":"1.8","js":{"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/jquery\/1.8\/jquery.min.js":1,"misc\/jquery.once.js":1,"misc\/drupal.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.core.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.widget.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.button.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.mouse.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.draggable.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.position.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.resizable.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/ui\/minified\/jquery.ui.dialog.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/ui\/external\/jquery.cookie.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/replace\/jquery.form\/3\/jquery.form.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/views_slideshow\/js\/views_slideshow.js":1,"misc\/ajax.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/jquery_update\/js\/jquery_update.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/admin_menu\/admin_devel\/admin_devel.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/customs\/hb_documents\/hb_documents.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/customs\/hb_forms\/hb_forms.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/customs\/hb_schemes\/hb_schemes.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/customs\/webform_email_reply\/script.js":1,"misc\/progress.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/event_calendar\/event_popup\/js\/event_popup.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/event_calendar\/event_popup\/js\/event_popup_validate.js":1,"0":1,"1":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/customs\/npi_security\/script.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/views\/js\/base.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/quicktabs\/js\/quicktabs.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/views\/js\/ajax_view.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/libraries\/flexslider\/jquery.flexslider-min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/flexslider_views_slideshow\/js\/flexslider_views_slideshow.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/npi_adaptive\/scripts\/jquery.min.js":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/npi_adaptive\/scripts\/custom.js":1},"css":{"modules\/system\/system.base.css":1,"modules\/system\/system.menus.css":1,"modules\/system\/system.messages.css":1,"misc\/ui\/jquery.ui.core.css":1,"misc\/ui\/jquery.ui.theme.css":1,"misc\/ui\/jquery.ui.button.css":1,"misc\/ui\/jquery.ui.resizable.css":1,"misc\/ui\/jquery.ui.dialog.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/views_slideshow\/views_slideshow.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/calendar\/css\/calendar_multiday.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/date\/date_api\/date.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/date\/date_popup\/themes\/datepicker.1.7.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/date\/date_repeat_field\/date_repeat_field.css":1,"modules\/field\/theme\/field.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/logintoboggan\/logintoboggan.css":1,"modules\/node\/node.css":1,"modules\/search\/search.css":1,"modules\/user\/user.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/views\/css\/views.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/ctools\/css\/ctools.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/event_calendar\/event_popup\/css\/event_popup.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/panels\/css\/panels.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/panels\/plugins\/layouts\/threecol_25_50_25\/threecol_25_50_25.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/quicktabs\/css\/quicktabs.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/panels\/plugins\/layouts\/onecol\/onecol.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/modules\/contributed\/flexslider\/assets\/css\/flexslider_img.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/libraries\/flexslider\/flexslider.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/at_core\/css\/at.layout.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/npi_adaptive\/css\/global.custom.responsive.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/npi_adaptive\/css\/global.base.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/npi_adaptive\/css\/global.styles.css":1,"<%=request.getContextPath() %>/assets/sites\/all\/themes\/adaptivetheme\/npi_adaptive\/css\/print.css":1,"public:\/\/adaptivetheme\/npi_adaptive_files\/npi_adaptive.default.layout.css":1}},"encrypt_submissions":{"baseUrl":"https:\/\/www.india.gov.in"},"event_popup":{"classes":"","defaults":"width:300;height:auto;position:[center,60]","selector":"content","content_type":"event_calendar","op":false},"better_exposed_filters":{"views":{"news_feeds":{"displays":{"block":{"filters":[]}}},"metadata":{"displays":{"block_1":{"filters":[]}}},"featured":{"displays":{"block":{"filters":[]}}},"spotlight":{"displays":{"block":{"filters":[]}}},"most_search":{"displays":{"block":{"filters":[]}}},"home_main_slider":{"displays":{"block":{"filters":[]}}}}},"ajax":{"quicktabs-tab-_none_-0":{"progress":{"message":"","type":"throbber"},"event":"click","url":"\/quicktabs\/ajax\/_none_\/0\/view\/news_feeds\/block\/1\/home\/"},"quicktabs-tab-_none_-1":{"progress":{"message":"","type":"throbber"},"event":"click","url":"\/quicktabs\/ajax\/_none_\/1\/view\/metadata\/block_7\/2\/home\/"}},"urlIsAjaxTrusted":{"\/quicktabs\/ajax\/_none_\/0\/view\/news_feeds\/block\/1\/home\/":true,"\/quicktabs\/ajax\/_none_\/1\/view\/metadata\/block_7\/2\/home\/":true,"\/views\/ajax":true,"\/search\/node":true,"\/advance-search\/results":true},"quicktabs":{"qt__none_":{"name":"_none_","tabs":[{"vid":"news_feeds","display":"block","args":"","view_path":"home","view_dom_id":1,"ajax_args":"","actual_args":[]},{"vid":"metadata","display":"block_7","args":"","view_path":"home","view_dom_id":2,"ajax_args":"","actual_args":[]}],"ajaxPageState":{"jquery_version":"1.8"}}},"views":{"ajax_path":"\/views\/ajax","ajaxViews":{"views_dom_id:c8b9322252e3079eaef79220fd17a99f":{"view_name":"metadata","view_display_id":"block_1","view_args":"166659","view_path":"home","view_base_path":"admin\/metadata-online","view_dom_id":"c8b9322252e3079eaef79220fd17a99f","pager_element":0},"views_dom_id:23bc96e9dbd3a1db43950c661786a796":{"view_name":"metadata","view_display_id":"block_1","view_args":"166856","view_path":"home","view_base_path":"admin\/metadata-online","view_dom_id":"23bc96e9dbd3a1db43950c661786a796","pager_element":0},"views_dom_id:3cc54e956246bcc1ea9ac4b2ef33ce2e":{"view_name":"spotlight","view_display_id":"block","view_args":"","view_path":"home","view_base_path":"spotlight","view_dom_id":"3cc54e956246bcc1ea9ac4b2ef33ce2e","pager_element":0}}},"viewsSlideshow":{"home_main_slider-block":{"methods":{"goToSlide":["viewsSlideshowPager","viewsSlideshowSlideCounter","viewsSlideshowCycle"],"nextSlide":["viewsSlideshowPager","viewsSlideshowSlideCounter","flexsliderViewsSlideshow","viewsSlideshowCycle"],"pause":["viewsSlideshowControls","flexsliderViewsSlideshow","viewsSlideshowCycle"],"play":["viewsSlideshowControls","flexsliderViewsSlideshow","viewsSlideshowCycle"],"previousSlide":["viewsSlideshowPager","viewsSlideshowSlideCounter","flexsliderViewsSlideshow","viewsSlideshowCycle"],"transitionBegin":["viewsSlideshowPager","viewsSlideshowSlideCounter"],"transitionEnd":[]},"paused":0}},"flexslider_views_slideshow":{"#flexslider_views_slideshow_main_home_main_slider-block":{"num_divs":6,"id_prefix":"#flexslider_views_slideshow_main_","vss_id":"home_main_slider-block","namespace":"flex-","selector":".slides \u003E li","easing":"linear","direction":"horizontal","reverse":false,"smoothHeight":false,"startAt":0,"animationSpeed":600,"initDelay":0,"useCSS":false,"touch":true,"video":false,"keyboard":true,"multipleKeyboard":false,"mousewheel":0,"controlsContainer":".flex-control-nav-container","sync":"","asNavFor":"","itemWidth":0,"itemMargin":0,"minItems":0,"maxItems":0,"move":0,"animation":"slide","slideshow":true,"slideshowSpeed":7000,"directionNav":true,"controlNav":true,"prevText":"","nextText":"","pausePlay":true,"pauseText":"","playText":"","randomize":true,"thumbCaptions":false,"thumbCaptionsBoth":false,"animationLoop":true,"pauseOnAction":true,"pauseOnHover":false,"manualControls":""}},"adaptivetheme":{"npi_adaptive":{"layout_settings":{"bigscreen":"three-col-grail","tablet_landscape":"three-col-grail","tablet_portrait":"one-col-vert","smalltouch_landscape":"one-col-vert","smalltouch_portrait":"one-col-stack"},"media_query_settings":{"bigscreen":"only screen and (min-width:1025px)","tablet_landscape":"only screen and (min-width:769px) and (max-width:1024px)","tablet_portrait":"only screen and (min-width:581px) and (max-width:768px)","smalltouch_landscape":"only screen and (min-width:321px) and (max-width:580px)","smalltouch_portrait":"only screen and (max-width:320px)"}}}});</script>
<!--[if lt IE 9]>
<script src="https://www.india.gov.in/sites/all/themes/adaptivetheme/at_core/scripts/html5.js?p1xei9"></script>
<![endif]-->

<script type="text/javascript">  
	if(_getCookie("fontSize") != null){
		fontSize = _getCookie("fontSize");
	}else{
		fontSize = 100;
	}
	var base_url ="index.html";
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
<%	
}
%>
<body class="html front not-logged-in no-sidebars page-home home i18n-en site-name-hidden atr-7.x-3.x atv-7.x-3.2 page-panels">

<%
User user = null;
List<News> news = null;
List<UserCategories> listOfUserCategories = null;
List<String> typeOfNewsList = null;
HashMap<String, String> typeOfNewsMap = null;
List<News> listOfNewsByUserSelectedCategory = null;
List<StatementGovernmentSchemes> searchResult = null;
List<StatementGovernmentSchemes> mainSearchResult = null;
List<StatementGovernmentSchemes> recentSchemes = null;
List<String> statesForUrl = null;
HashMap<String, String> mapOfstatesWithUrl = null;
String searchString = null;

if(session.getAttribute("knowYourSchemeUser") == null){
	
	if(request.getAttribute("statesForUrl") != null &&
	   !request.getAttribute("statesForUrl").equals("") &&
	   request.getAttribute("mapOfstatesWithUrl") != null &&
	   !request.getAttribute("mapOfstatesWithUrl").equals("") &&
	   request.getAttribute("searchResult") != null &&
	   request.getAttribute("mainSearchResult") != null &&
	   request.getAttribute("searchString") != null){
		statesForUrl = (List<String>)request.getAttribute("statesForUrl");
		mapOfstatesWithUrl = (HashMap<String, String>)request.getAttribute("mapOfstatesWithUrl");
		searchResult = (List<StatementGovernmentSchemes>)request.getAttribute("searchResult");
		mainSearchResult = (List<StatementGovernmentSchemes>)request.getAttribute("mainSearchResult");
		searchString = (String)request.getAttribute("searchString");
	}
}else{
	if(request.getAttribute("dashboardUser") != null &&
	   !request.getAttribute("dashboardUser").equals("") &&
	   request.getAttribute("newsListForMarque") != null &&
	   !request.getAttribute("newsListForMarque").equals("") &&
	   request.getAttribute("listOfUserCategories") != null &&
	   !request.getAttribute("listOfUserCategories").equals("") &&
	   request.getAttribute("typeOfNewsList") != null &&
	   !request.getAttribute("typeOfNewsList").equals("") &&
	   request.getAttribute("typeOfNewsMap") != null &&
	   !request.getAttribute("typeOfNewsMap").equals("") &&
	   request.getAttribute("listOfNewsByUserSelectedCategory") != null &&
	   !request.getAttribute("listOfNewsByUserSelectedCategory").equals("") &&
	   request.getAttribute("recentSchemes") != null &&
	   !request.getAttribute("recentSchemes").equals("") &&
	   request.getAttribute("statesForUrl") != null &&
	   !request.getAttribute("statesForUrl").equals("") &&
	   request.getAttribute("mapOfstatesWithUrl") != null &&
	   !request.getAttribute("mapOfstatesWithUrl").equals("") &&
	   request.getAttribute("searchResult") != null &&
	   request.getAttribute("mainSearchResult") != null &&
	   request.getAttribute("searchString") != null){
		user = (User)request.getAttribute("dashboardUser");
		news = (List<News>)request.getAttribute("newsListForMarque");
		listOfUserCategories = (List<UserCategories>)request.getAttribute("listOfUserCategories");
		typeOfNewsList = (List<String>)request.getAttribute("typeOfNewsList");
		typeOfNewsMap = (HashMap<String, String>)request.getAttribute("typeOfNewsMap");
		listOfNewsByUserSelectedCategory = (List<News>)request.getAttribute("listOfNewsByUserSelectedCategory");
		recentSchemes = (List<StatementGovernmentSchemes>)request.getAttribute("recentSchemes");
		statesForUrl = (List<String>)request.getAttribute("statesForUrl");
		mapOfstatesWithUrl = (HashMap<String, String>)request.getAttribute("mapOfstatesWithUrl");
		searchResult = (List<StatementGovernmentSchemes>)request.getAttribute("searchResult");
		mainSearchResult = (List<StatementGovernmentSchemes>)request.getAttribute("mainSearchResult");
		searchString = (String)request.getAttribute("searchString");
	}
}


int seaI = 1;
%>

  <div id="skip-link" class="nocontent">
    <a href="#main-content" class="element-invisible element-focusable">Skip to main content</a>
  </div>
    <!-- !Leaderboard Region -->

<link href="<%=request.getContextPath() %>/assets/maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<%
if(session.getAttribute("knowYourSchemeUser") == null){
%>
<header id="header" class="clearfix" role="banner">
	<section class="top_head" style = "border-bottom: 1px solid #673ab7;">
		<div class="wrapper">
			<div class="region region-header"><div class="region-inner clearfix"><div id="block-block-4" class="block block-block no-title" ><div class="block-inner clearfix">  
  
  <div class="block-content content">       
<div class="tophead_left" style = "width:10%;">
<span class="social_icons">
<a href="https://www.facebook.com/NationalPortalIndia" target="_blank" title="Visit Our Facebook Page (External Website that opens in a new window)"><span class="fa fa-facebook-square"></span></a>
<a href="https://twitter.com/indiagovin" target="_blank" title="Visit Our Twitter Page (External Website that opens in a new window)"><span class="fa fa-tumblr-square"></span></a>
<a href = "<%=request.getContextPath() %>/">KYS</a>
</span>
</div>
<div class="tophead_right">

<ul style = "display:inline;" class = "dashboard-nav">

<li><a href="<%=request.getContextPath() %>/schemes" class="loginlinks">Schemes</a></li>
<li><a href="<%=request.getContextPath() %>/schemes/Application-Forms" class="loginlinks">Application Forms</a></li>
<li><a href="<%=request.getContextPath() %>/schemes/Government-Jobs" class="loginlinks">Government Jobs</a></li>
<li><a href="<%=request.getContextPath() %>/schemes/Government-Websites" class="loginlinks">Important Websites</a></li><li><a href="<%=request.getContextPath() %>/schemes/visualisation/categories" class="loginlinks">Visualisation</a></li><li><a href="<%=request.getContextPath() %>/schemes/visualisation/categories" class="loginlinks">Visualisation</a></li>
<li><a href="<%=request.getContextPath() %>/assets/india-glance.html" class="loginlinks">Sitemap</a></li>
<li><a href="<%=request.getContextPath() %>/contactUs" class="loginlinks">Contact Us</a></li>
<li><a href="<%=request.getContextPath() %>/signin" class="loginlinks">Sign In</a></li>
<li><a href="<%=request.getContextPath() %>/register" class="loginlinks">Register</a></li>

</ul>
			
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
}
</style>	
<span class="sizechanger">

<div id="google_translate_element" style = "position:absolute; top:0px; left:200px;"></div>

<script type="text/javascript">
function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'en', layout: google.translate.TranslateElement.InlineLayout.HORIZONTAL}, 'google_translate_element');
}
</script>

<script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
<a id="text_resize_decrease" class="changer" href="javascript:;" onclick="set_font_size('decrease')" title="Decrease font size" role="link">-A</a>
<a id="text_resize_reset" class="changer" onclick="set_font_size('')" href="javascript:;" title="Reset font size" role="link">A</a>
<a id="text_resize_increase" class="changer" onclick="set_font_size('increase')" href="javascript:;" title="Increase font size" role="link">+A</a>
</span>
<span class="colorchanger">
<a href="javascript:void(0);" onclick="chooseStyle('none', 60);" role="link">A</a>
<a href="javascript:void(0);" class="blackbg"  onclick="chooseStyle('change', 60);" role="link">A</a>
</span>
<%
System.out.println("\u0917\u094d\u0930\u093e\u0939\u0915");
%>
</div></div>
  </div></div></div></div>		</div>
	</section>
    <section class="header_botton" style = "background:none; background-color: #8bc34a; height: 40px;">
		<div class="wrapper">
			<div class="search_section" style = "height: 40px; width: 100%;">
				<div class="searchform" style = "margin: 0 0; width: 100%;">
					<div class="searchrow" style = "padding: 0px; padding-left: 10px; width: 100%;"> 
                    	<div class="search_form" style = "width: 80%;">
							<div class="region region-goi-search"><div class="region-inner clearfix"><div id="block-block-16" class="block block-block no-title" ><div class="block-inner clearfix">  
  
  <div class="block-content content"><style type="text/css">
#searchForm label { display: none;}
</style>

<form action="<%=request.getContextPath() %>/schemes/search" id="searchForm"  autocomplete="off" method = "get">
   <label for="search_key">Search</label>
<input type="text" value="" maxlength="128" name="searchKeyword" id="searchKeyword" size="15" placeholder="Search - Keyword, Phrase" class="form-text searchText ui-autocomplete-input"   autocomplete="off" />
<input type="submit" name="op" value="Search" id="edit-submit" class="form-submit" />
<!-- <div id="auto_suggesion"></div>-->
    </form>
    
</div>

  </div></div></div></div>                        </div>
						 <a href="javascript:void(0)" id="advsearchbtn">Advanced Search</a>
						 
						<div id="light" class="white_box" style="width: 380px; top: 40px;">	
							<div class="search-form">
								<form action="https://www.india.gov.in/advance-search/results" method="get" id="views-exposed-form-advance-metadata-search-page-1" accept-charset="UTF-8"><div><div class="views-exposed-form">
  <div class="views-exposed-widgets clearfix">
          <div id="edit-title-wrapper" class="views-exposed-widget views-widget-filter-title">
                  <label for="edit-title">
            Title          </label>
                        <div class="views-widget">
          <div class="form-item form-type-textfield form-item-title">
 <input placeholder="Search Keyword" type="text" id="edit-title" name="title" value="" size="30" maxlength="128" class="form-text" />
</div>
        </div>
              </div>
          <div id="edit-term-node-tid-depth-wrapper" class="views-exposed-widget views-widget-filter-term_node_tid_depth">
                  <label for="edit-term-node-tid-depth">
            Section          </label>
                        <div class="views-widget">
          <div class="form-item form-type-select form-item-term-node-tid-depth">
 <select id="edit-term-node-tid-depth" name="term_node_tid_depth" class="form-select"><option value="All" selected="selected">- Any -</option><option value="166659">Topics</option><option value="166665">-Agriculture</option><option value="166691">--Agricultural Licence</option><option value="166692">--Agricultural Machinery</option><option value="166693">--Agricultural Produces</option><option value="166694">--Research &amp; Extension</option><option value="166695">--Storage</option><option value="166696">--Marketing &amp; Prices</option><option value="166697">--Animal Husbandry</option><option value="166698">--Certification &amp; Gradation</option><option value="166699">--Crops</option><option value="166700">--Dairy</option><option value="166701">--Fertilizers &amp; Pesticides</option><option value="166702">--Fisheries</option><option value="166703">--Floriculture</option><option value="166704">--Horticulture</option><option value="166705">--Irrigation</option><option value="166706">--Loans &amp; Credit</option><option value="166707">--Organic Farming</option><option value="166708">--Plant Protection</option><option value="166709">--Seeds</option><option value="166710">--Sericulture</option><option value="166711">--Soil and Water Conservation</option><option value="166712">--Weather</option><option value="166666">-Art &amp; Culture</option><option value="166713">--Archaeology</option><option value="166714">--Dance</option><option value="166715">--Fairs &amp; Festivals</option><option value="166716">--Handicrafts</option><option value="166717">--Heritage</option><option value="166718">--Literature</option><option value="166719">--Monuments</option><option value="166720">--Music</option><option value="166721">--Painting</option><option value="166722">--Theatre</option><option value="166667">-Commerce</option><option value="166723">--Export</option><option value="166724">--Import</option><option value="166725">--SEZs</option><option value="166726">--Trade Promotion</option><option value="166668">-Communication</option><option value="166727">--Information and Technology</option><option value="166728">--Post</option><option value="166729">--Telecom</option><option value="166669">-Defence</option><option value="166730">--Air Force</option><option value="166731">--Army</option><option value="166732">--Navy</option><option value="166733">--Paramilitary Forces</option><option value="166670">-Education</option><option value="166734">--Elementary Education</option><option value="166735">--Secondary &amp; Higher Secondary Education</option><option value="166736">--Higher Education</option><option value="166737">--Adult Education</option><option value="166671">-Environment &amp; Forest</option><option value="166738">--Environment</option><option value="166739">--Forest</option><option value="166740">--Natural Resources</option><option value="166741">--Wildlife</option><option value="166672">-Finance &amp; Taxes</option><option value="166742">--Insurance</option><option value="166743">--Pension</option><option value="166744">--Banking</option><option value="166745">--Economy</option><option value="166746">--Trade</option><option value="166747">--Taxes</option><option value="166673">-Food &amp; Public Distribution</option><option value="166748">--Commodities</option><option value="166749">--Consumer Affairs</option><option value="166750">--Consumer Cooperatives</option><option value="166751">--Public Distribution</option><option value="166674">-Foreign Affairs</option><option value="166752">--Embassy &amp; Consulates</option><option value="166753">--India &amp; World</option><option value="166754">--India and Its Neighbours</option><option value="166755">--NRIs</option><option value="166756">--Passport &amp; Visa</option><option value="166675">-Governance &amp; Administration</option><option value="166757">--Constitution</option><option value="166758">--Parliament</option><option value="166759">--State Legislature</option><option value="166760">--Union/State Government</option><option value="166761">--District Administration</option><option value="166762">--Local Administration</option><option value="166763">--RTI &amp; Grievances</option><option value="166676">-Health &amp; Family Welfare</option><option value="166764">--Family Welfare</option><option value="166765">--Health</option><option value="166677">-Housing</option><option value="166769">--EWS Housing</option><option value="166770">--Rural Housing</option><option value="166771">--Urban Housing</option><option value="166678">-Home Affairs &amp; Enforcement</option><option value="166766">--Enforcement Organizations</option><option value="166767">--Internal Security</option><option value="166768">--Police</option><option value="166679">-Industries</option><option value="166772">--Micro, Small &amp; Medium Enterprises</option><option value="166773">--Mining</option><option value="166774">--Chemicals and Petrochemicals</option><option value="166775">--Corporate Governance</option><option value="166776">--Cottage</option><option value="166777">--Defence Products</option><option value="166778">--Fertilizers</option><option value="166779">--Food Processing</option><option value="166780">--Heavy Industries</option><option value="166781">--Manufacturing</option><option value="166782">--Oil and Natural Gas</option><option value="166783">--Pharmaceuticals</option><option value="166784">--Retail</option><option value="166785">--Textiles</option><option value="166786">--Tourism</option><option value="166680">-Infrastructure</option><option value="166790">--Power</option><option value="166791">--Bridges</option><option value="166792">--Dams</option><option value="166793">--Roads</option><option value="166794">--Urban</option><option value="166681">-Information &amp; Broadcasting</option><option value="166787">--Broadcasting</option><option value="166788">--Film</option><option value="166789">--Print Media</option><option value="166682">-Labour &amp; Employment</option><option value="166803">--Employment</option><option value="166804">--Organized Sector Workers</option><option value="166805">--Unorganized Sector Workers</option><option value="166683">-Law &amp; Justice</option><option value="166795">--Judiciary</option><option value="166796">--Alternative Dispute Redressal (ADR)</option><option value="166797">--Enactment of Laws</option><option value="166798">--Institutions</option><option value="166799">--Law Commission of India</option><option value="166800">--Legal Aid</option><option value="166801">--Legal Profession</option><option value="166802">--Personal Law</option><option value="166684">-Power &amp; Energy</option><option value="166806">--Coal</option><option value="166807">--Conservation of Energy</option><option value="166808">--Petroleum &amp; Natural Gas</option><option value="166809">--Power</option><option value="166810">--Renewable Energy</option><option value="166685">-Rural</option><option value="166811">--Rural Employment</option><option value="166812">--Development</option><option value="166813">--Panchayati Raj</option><option value="166686">-Science &amp; Technology</option><option value="166814">--Scientific Education</option><option value="166815">--Research &amp; Development</option><option value="166687">-Social Development</option><option value="166816">--Other Backward Classes</option><option value="166817">--Scheduled Castes</option><option value="166818">--Children</option><option value="166819">--Disabled</option><option value="166820">--Minority Community</option><option value="166821">--Senior Citizen</option><option value="166822">--Tribal</option><option value="166823">--Women</option><option value="166688">-Transport</option><option value="166828">--Aviation</option><option value="166829">--Metro</option><option value="166830">--Railways</option><option value="166831">--Surface Transport</option><option value="166832">--Water Ways</option><option value="166689">-Travel &amp; Tourism</option><option value="166824">--Places</option><option value="166825">--Accommodation</option><option value="166826">--Modes of Travel</option><option value="166827">--Approved Agents</option><option value="166690">-Youth &amp; Sports</option><option value="166833">--Sports Infrastructure</option><option value="166834">--Games</option><option value="166835">--Youth Affairs</option><option value="166661">My Government</option><option value="166848">-Constitution of India</option><option value="166859">--Search in Constitution of India</option><option value="166849">-Government Directory</option><option value="166890">--Union Government</option><option value="166891">--States &amp; UTs</option><option value="166892">--Codes</option><option value="166893">--Judiciary</option><option value="166853">-Acts</option><option value="166854">-Schemes</option><option value="166855">-Documents</option><option value="166875">--Annual Report</option><option value="166876">--Budget</option><option value="166877">--Census Report</option><option value="166878">--Citizens Charter</option><option value="166879">--E-Books</option><option value="166880">--Gazetteer Notification</option><option value="166881">--Guidelines</option><option value="166882">--Others</option><option value="166883">--Plan Document</option><option value="166884">--Policy</option><option value="166885">--Reports</option><option value="166856">-Forms</option><option value="166886">--Downloadable</option><option value="166887">--Fillable</option><option value="166662">People Groups</option><option value="166894">-Community</option><option value="166899">--Businesspersons</option><option value="166900">--Indians Travelling Abroad</option><option value="166901">--Minority Community</option><option value="166902">--Rural Indian</option><option value="166903">--Scheduled Tribes/Schedule Caste</option><option value="166904">--Self-Employed</option><option value="166905">--Social Workers</option><option value="166906">--Sportsperson</option><option value="166907">--Student</option><option value="166908">--Tourists</option><option value="166909">--Women</option><option value="166910">--Defence &amp; Paramilitary Personnel</option><option value="166911">--Differently Abled</option><option value="166912">--Entrepreneur</option><option value="166913">--Environmentalists</option><option value="166914">--Government Employee</option><option value="166915">--Importers and Exporters</option><option value="166916">--Job Seekers</option><option value="166917">--Non-Resident Indians</option><option value="166895">-Life Cycle</option><option value="166896">--Kids</option><option value="166897">--Senior Citizens</option><option value="166898">--Youth</option><option value="166664">India at a Glance</option><option value="166927">-Profile</option><option value="166928">-History</option><option value="166929">-States of India</option><option value="166930">-My India My Pride</option><option value="166931">-National Symbols</option><option value="166932">-Culture &amp; Heritage</option><option value="166933">-People and Lifestyle</option><option value="166934">-Places to Visit</option><option value="166935">-Natural Wonders</option><option value="166936">-Where to stay</option><option value="166937">-Modes of Travel</option><option value="166938">-Travel Advisory</option><option value="166939">-Travel Agents</option></select>
</div>
        </div>
              </div>
              <div class="views-exposed-widget views-widget-sort-by">
        <div class="form-item form-type-select form-item-sort-by">
  <label for="edit-sort-by">Sort by </label>
 <select id="edit-sort-by" name="sort_by" class="form-select"><option value="created" selected="selected">Recent</option><option value="changed">Most Updated</option></select>
</div>
      </div>
      <div class="views-exposed-widget views-widget-sort-order">
        <div class="form-item form-type-select form-item-sort-order">
  <label for="edit-sort-order">Order </label>
 <select id="edit-sort-order" name="sort_order" class="form-select"><option value="ASC">Asc</option><option value="DESC" selected="selected">Desc</option></select>
</div>
      </div>
                <div class="views-exposed-widget views-submit-button">
      <input type="submit" id="edit-submit-advance-metadata-search" value="Search" class="form-submit" />    </div>
      </div>
</div>
</div></form>	                          	
							</div>
							<!--add-adres-footer-->
	
						</div>
                        
					</div> 	
				</div>

			</div>
			<div class="heraderbtm_col2">
				<div class="region region-leaderboard"><div class="region-inner clearfix"><section id="block-views-most-search-block" class="block block-views" ><div class="block-inner clearfix">
  
  <div class="block-content content"><div class="view view-most-search view-id-most_search view-display-id-block view-dom-id-e38b830996d06758e7d3042e35592d38">
  
  
  
  
  
  
  
</div></div>
  </div></section></div></div>			</div>
		 <!-- !Header Region -->
		</div>
	</section> 
			</header>					

<%
}else{
%>

<header id="header" class="clearfix" role="banner">
	<section class="top_head" style = "border-bottom: 1px solid #673ab7;">
		<div class="wrapper">
			<div class="region region-header"><div class="region-inner clearfix"><div id="block-block-4" class="block block-block no-title" ><div class="block-inner clearfix">  
  
  <div class="block-content content">       
<div class="tophead_left" style = "width:10%;">
<span class="social_icons">
<a href="https://www.facebook.com/NationalPortalIndia" target="_blank" title="Visit Our Facebook Page (External Website that opens in a new window)"><span class="fa fa-facebook-square"></span></a>
<a href="https://twitter.com/indiagovin" target="_blank" title="Visit Our Twitter Page (External Website that opens in a new window)"><span class="fa fa-tumblr-square"></span></a>
<a href = "<%=request.getContextPath() %>/">KYS</a>
</span>
</div>
<div class="tophead_right">
<ul style = "display:inline;" class = "dashboard-nav">

<li><a href="<%=request.getContextPath() %>/dashboard" class="loginlinks dashboard-active">Dashboard</a></li>
<li><a href="<%=request.getContextPath() %>/newsfeed" class="loginlinks">Newsfeed</a></li>
<li><a href="<%=request.getContextPath() %>/schemes" class="loginlinks">Schemes</a></li>
<li><a href="<%=request.getContextPath() %>/schemes/Application-Forms" class="loginlinks">Application Forms</a></li>
<li><a href="<%=request.getContextPath() %>/schemes/Government-Jobs" class="loginlinks">Government Jobs</a></li>
<li><a href="<%=request.getContextPath() %>/schemes/Government-Websites" class="loginlinks">Important Websites</a></li><li><a href="<%=request.getContextPath() %>/schemes/visualisation/categories" class="loginlinks">Visualisation</a></li><li><a href="<%=request.getContextPath() %>/schemes/visualisation/categories" class="loginlinks">Visualisation</a></li>
<li><a href="<%=request.getContextPath() %>/assets/india-glance.html" class="loginlinks">Sitemap</a></li>
<li><a href="<%=request.getContextPath() %>/profile" class="loginlinks"><%=user.getUsername() %></a></li>
<li><form method = "post" action = "<%=request.getContextPath() %>/signout" class="loginlinks">
<input type = "submit" value = "Sign Out" style = "outline: none; margin-left: -7px;margin-top: -5px;margin-right: -7px;margin-bottom: -2px;height: 30px;width: 70px; cursor:pointer;">
</form></li>

</ul>
			
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
}
</style>	
<span class="sizechanger">

<div id="google_translate_element" style = "position:absolute; top:0px; left:200px;"></div>

<script type="text/javascript">
function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'en', layout: google.translate.TranslateElement.InlineLayout.HORIZONTAL}, 'google_translate_element');
}
</script>
<script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
<a id="text_resize_decrease" class="changer" href="javascript:;" onclick="set_font_size('decrease')" title="Decrease font size" role="link">-A</a>
<a id="text_resize_reset" class="changer" onclick="set_font_size('')" href="javascript:;" title="Reset font size" role="link">A</a>
<a id="text_resize_increase" class="changer" onclick="set_font_size('increase')" href="javascript:;" title="Increase font size" role="link">+A</a>
</span>
<span class="colorchanger">
<a href="javascript:void(0);" onclick="chooseStyle('none', 60);" role="link">A</a>
<a href="javascript:void(0);" class="blackbg"  onclick="chooseStyle('change', 60);" role="link">A</a>
</span>
<%
System.out.println("\u0917\u094d\u0930\u093e\u0939\u0915");
%>
</div></div>
  </div></div></div></div>		</div>
	</section>
    <section class="header_botton" style = "background:none; background-color: #8bc34a; height: 40px;">
		<div class="wrapper">
			<div class="search_section" style = "height: 40px; width: 100%;">
				<div class="searchform" style = "margin: 0 0; width: 100%;">
					<div class="searchrow" style = "padding: 0px; padding-left: 10px; width: 100%;"> 
                    	<div class="search_form" style = "width: 80%;">
							<div class="region region-goi-search"><div class="region-inner clearfix"><div id="block-block-16" class="block block-block no-title" ><div class="block-inner clearfix">  
  
  <div class="block-content content"><style type="text/css">
#searchForm label { display: none;}
</style>

<form action="<%=request.getContextPath() %>/schemes/search" id="searchForm"  autocomplete="off" method = "get">
   <label for="search_key">Search</label>
<input type="text" value="<%=searchString %>" maxlength="128" name="searchKeyword" id="searchKeyword" size="15" placeholder="Search - Keyword, Phrase" class="form-text searchText ui-autocomplete-input"   autocomplete="off" />
<input type="submit" name="op" value="Search" id="edit-submit" class="form-submit" />
<div id="auto_suggesion"></div>
    </form>
    
</div>

  </div></div></div></div>                        </div>
						 <a href="javascript:void(0)" id="advsearchbtn">Advanced Search</a>
						 
						<div id="light" class="white_box" style="width: 380px; top: 40px;">	
							<div class="search-form">
								<form action="https://www.india.gov.in/advance-search/results" method="get" id="views-exposed-form-advance-metadata-search-page-1" accept-charset="UTF-8"><div><div class="views-exposed-form">
  <div class="views-exposed-widgets clearfix">
          <div id="edit-title-wrapper" class="views-exposed-widget views-widget-filter-title">
                  <label for="edit-title">
            Title          </label>
                        <div class="views-widget">
          <div class="form-item form-type-textfield form-item-title">
 <input placeholder="Search Keyword" type="text" id="edit-title" name="title" value="" size="30" maxlength="128" class="form-text" />
</div>
        </div>
              </div>
          <div id="edit-term-node-tid-depth-wrapper" class="views-exposed-widget views-widget-filter-term_node_tid_depth">
                  <label for="edit-term-node-tid-depth">
            Section          </label>
                        <div class="views-widget">
          <div class="form-item form-type-select form-item-term-node-tid-depth">
 <select id="edit-term-node-tid-depth" name="term_node_tid_depth" class="form-select"><option value="All" selected="selected">- Any -</option><option value="166659">Topics</option><option value="166665">-Agriculture</option><option value="166691">--Agricultural Licence</option><option value="166692">--Agricultural Machinery</option><option value="166693">--Agricultural Produces</option><option value="166694">--Research &amp; Extension</option><option value="166695">--Storage</option><option value="166696">--Marketing &amp; Prices</option><option value="166697">--Animal Husbandry</option><option value="166698">--Certification &amp; Gradation</option><option value="166699">--Crops</option><option value="166700">--Dairy</option><option value="166701">--Fertilizers &amp; Pesticides</option><option value="166702">--Fisheries</option><option value="166703">--Floriculture</option><option value="166704">--Horticulture</option><option value="166705">--Irrigation</option><option value="166706">--Loans &amp; Credit</option><option value="166707">--Organic Farming</option><option value="166708">--Plant Protection</option><option value="166709">--Seeds</option><option value="166710">--Sericulture</option><option value="166711">--Soil and Water Conservation</option><option value="166712">--Weather</option><option value="166666">-Art &amp; Culture</option><option value="166713">--Archaeology</option><option value="166714">--Dance</option><option value="166715">--Fairs &amp; Festivals</option><option value="166716">--Handicrafts</option><option value="166717">--Heritage</option><option value="166718">--Literature</option><option value="166719">--Monuments</option><option value="166720">--Music</option><option value="166721">--Painting</option><option value="166722">--Theatre</option><option value="166667">-Commerce</option><option value="166723">--Export</option><option value="166724">--Import</option><option value="166725">--SEZs</option><option value="166726">--Trade Promotion</option><option value="166668">-Communication</option><option value="166727">--Information and Technology</option><option value="166728">--Post</option><option value="166729">--Telecom</option><option value="166669">-Defence</option><option value="166730">--Air Force</option><option value="166731">--Army</option><option value="166732">--Navy</option><option value="166733">--Paramilitary Forces</option><option value="166670">-Education</option><option value="166734">--Elementary Education</option><option value="166735">--Secondary &amp; Higher Secondary Education</option><option value="166736">--Higher Education</option><option value="166737">--Adult Education</option><option value="166671">-Environment &amp; Forest</option><option value="166738">--Environment</option><option value="166739">--Forest</option><option value="166740">--Natural Resources</option><option value="166741">--Wildlife</option><option value="166672">-Finance &amp; Taxes</option><option value="166742">--Insurance</option><option value="166743">--Pension</option><option value="166744">--Banking</option><option value="166745">--Economy</option><option value="166746">--Trade</option><option value="166747">--Taxes</option><option value="166673">-Food &amp; Public Distribution</option><option value="166748">--Commodities</option><option value="166749">--Consumer Affairs</option><option value="166750">--Consumer Cooperatives</option><option value="166751">--Public Distribution</option><option value="166674">-Foreign Affairs</option><option value="166752">--Embassy &amp; Consulates</option><option value="166753">--India &amp; World</option><option value="166754">--India and Its Neighbours</option><option value="166755">--NRIs</option><option value="166756">--Passport &amp; Visa</option><option value="166675">-Governance &amp; Administration</option><option value="166757">--Constitution</option><option value="166758">--Parliament</option><option value="166759">--State Legislature</option><option value="166760">--Union/State Government</option><option value="166761">--District Administration</option><option value="166762">--Local Administration</option><option value="166763">--RTI &amp; Grievances</option><option value="166676">-Health &amp; Family Welfare</option><option value="166764">--Family Welfare</option><option value="166765">--Health</option><option value="166677">-Housing</option><option value="166769">--EWS Housing</option><option value="166770">--Rural Housing</option><option value="166771">--Urban Housing</option><option value="166678">-Home Affairs &amp; Enforcement</option><option value="166766">--Enforcement Organizations</option><option value="166767">--Internal Security</option><option value="166768">--Police</option><option value="166679">-Industries</option><option value="166772">--Micro, Small &amp; Medium Enterprises</option><option value="166773">--Mining</option><option value="166774">--Chemicals and Petrochemicals</option><option value="166775">--Corporate Governance</option><option value="166776">--Cottage</option><option value="166777">--Defence Products</option><option value="166778">--Fertilizers</option><option value="166779">--Food Processing</option><option value="166780">--Heavy Industries</option><option value="166781">--Manufacturing</option><option value="166782">--Oil and Natural Gas</option><option value="166783">--Pharmaceuticals</option><option value="166784">--Retail</option><option value="166785">--Textiles</option><option value="166786">--Tourism</option><option value="166680">-Infrastructure</option><option value="166790">--Power</option><option value="166791">--Bridges</option><option value="166792">--Dams</option><option value="166793">--Roads</option><option value="166794">--Urban</option><option value="166681">-Information &amp; Broadcasting</option><option value="166787">--Broadcasting</option><option value="166788">--Film</option><option value="166789">--Print Media</option><option value="166682">-Labour &amp; Employment</option><option value="166803">--Employment</option><option value="166804">--Organized Sector Workers</option><option value="166805">--Unorganized Sector Workers</option><option value="166683">-Law &amp; Justice</option><option value="166795">--Judiciary</option><option value="166796">--Alternative Dispute Redressal (ADR)</option><option value="166797">--Enactment of Laws</option><option value="166798">--Institutions</option><option value="166799">--Law Commission of India</option><option value="166800">--Legal Aid</option><option value="166801">--Legal Profession</option><option value="166802">--Personal Law</option><option value="166684">-Power &amp; Energy</option><option value="166806">--Coal</option><option value="166807">--Conservation of Energy</option><option value="166808">--Petroleum &amp; Natural Gas</option><option value="166809">--Power</option><option value="166810">--Renewable Energy</option><option value="166685">-Rural</option><option value="166811">--Rural Employment</option><option value="166812">--Development</option><option value="166813">--Panchayati Raj</option><option value="166686">-Science &amp; Technology</option><option value="166814">--Scientific Education</option><option value="166815">--Research &amp; Development</option><option value="166687">-Social Development</option><option value="166816">--Other Backward Classes</option><option value="166817">--Scheduled Castes</option><option value="166818">--Children</option><option value="166819">--Disabled</option><option value="166820">--Minority Community</option><option value="166821">--Senior Citizen</option><option value="166822">--Tribal</option><option value="166823">--Women</option><option value="166688">-Transport</option><option value="166828">--Aviation</option><option value="166829">--Metro</option><option value="166830">--Railways</option><option value="166831">--Surface Transport</option><option value="166832">--Water Ways</option><option value="166689">-Travel &amp; Tourism</option><option value="166824">--Places</option><option value="166825">--Accommodation</option><option value="166826">--Modes of Travel</option><option value="166827">--Approved Agents</option><option value="166690">-Youth &amp; Sports</option><option value="166833">--Sports Infrastructure</option><option value="166834">--Games</option><option value="166835">--Youth Affairs</option><option value="166661">My Government</option><option value="166848">-Constitution of India</option><option value="166859">--Search in Constitution of India</option><option value="166849">-Government Directory</option><option value="166890">--Union Government</option><option value="166891">--States &amp; UTs</option><option value="166892">--Codes</option><option value="166893">--Judiciary</option><option value="166853">-Acts</option><option value="166854">-Schemes</option><option value="166855">-Documents</option><option value="166875">--Annual Report</option><option value="166876">--Budget</option><option value="166877">--Census Report</option><option value="166878">--Citizens Charter</option><option value="166879">--E-Books</option><option value="166880">--Gazetteer Notification</option><option value="166881">--Guidelines</option><option value="166882">--Others</option><option value="166883">--Plan Document</option><option value="166884">--Policy</option><option value="166885">--Reports</option><option value="166856">-Forms</option><option value="166886">--Downloadable</option><option value="166887">--Fillable</option><option value="166662">People Groups</option><option value="166894">-Community</option><option value="166899">--Businesspersons</option><option value="166900">--Indians Travelling Abroad</option><option value="166901">--Minority Community</option><option value="166902">--Rural Indian</option><option value="166903">--Scheduled Tribes/Schedule Caste</option><option value="166904">--Self-Employed</option><option value="166905">--Social Workers</option><option value="166906">--Sportsperson</option><option value="166907">--Student</option><option value="166908">--Tourists</option><option value="166909">--Women</option><option value="166910">--Defence &amp; Paramilitary Personnel</option><option value="166911">--Differently Abled</option><option value="166912">--Entrepreneur</option><option value="166913">--Environmentalists</option><option value="166914">--Government Employee</option><option value="166915">--Importers and Exporters</option><option value="166916">--Job Seekers</option><option value="166917">--Non-Resident Indians</option><option value="166895">-Life Cycle</option><option value="166896">--Kids</option><option value="166897">--Senior Citizens</option><option value="166898">--Youth</option><option value="166664">India at a Glance</option><option value="166927">-Profile</option><option value="166928">-History</option><option value="166929">-States of India</option><option value="166930">-My India My Pride</option><option value="166931">-National Symbols</option><option value="166932">-Culture &amp; Heritage</option><option value="166933">-People and Lifestyle</option><option value="166934">-Places to Visit</option><option value="166935">-Natural Wonders</option><option value="166936">-Where to stay</option><option value="166937">-Modes of Travel</option><option value="166938">-Travel Advisory</option><option value="166939">-Travel Agents</option></select>
</div>
        </div>
              </div>
              <div class="views-exposed-widget views-widget-sort-by">
        <div class="form-item form-type-select form-item-sort-by">
  <label for="edit-sort-by">Sort by </label>
 <select id="edit-sort-by" name="sort_by" class="form-select"><option value="created" selected="selected">Recent</option><option value="changed">Most Updated</option></select>
</div>
      </div>
      <div class="views-exposed-widget views-widget-sort-order">
        <div class="form-item form-type-select form-item-sort-order">
  <label for="edit-sort-order">Order </label>
 <select id="edit-sort-order" name="sort_order" class="form-select"><option value="ASC">Asc</option><option value="DESC" selected="selected">Desc</option></select>
</div>
      </div>
                <div class="views-exposed-widget views-submit-button">
      <input type="submit" id="edit-submit-advance-metadata-search" value="Search" class="form-submit" />    </div>
      </div>
</div>
</div></form>	                          	
							</div>
							<!--add-adres-footer-->
	
						</div>
                        
					</div> 	
				</div>

			</div>
			<div class="heraderbtm_col2">
				<div class="region region-leaderboard"><div class="region-inner clearfix"><section id="block-views-most-search-block" class="block block-views" ><div class="block-inner clearfix">
  
  <div class="block-content content"><div class="view view-most-search view-id-most_search view-display-id-block view-dom-id-e38b830996d06758e7d3042e35592d38">
  
  
  
  
  
  
  
</div></div>
  </div></section></div></div>			</div>
		 <!-- !Header Region -->
		</div>
	</section> 
			</header>

<%
}
%>

	    <div id="main" class="columns clearfix">
      <main id="content-column" class="content-column" role="main">
	  <!-- !Breadcrumbs -->
		<div class="bread_crumbs" > 
			<div class='wrapper'><a>Search</a></div>		</div> 
        <div class="content-inner wrapper">	
			<!-- !Messages and Help -->
									<div class="full_width_secction" style = "width: 100%;">
			  
								  <h1 class="static_page_head">Search</h1>
												<!-- !Secondary Content Region -->
				
				  <section id="main-content">

										<div class="static_content">	
					<!-- !Main Content -->
										  <div id="content" class="region">
						 <div id="block-system-main" class="block block-system no-title" >          <div class="three-25-50-25 at-panel panel-display clearfix" >     <div class="region region-three-25-50-25-first" style = "width: 100%!important;">     <div class="region-inner clearfix">       <div class="panel-pane pane-block pane-npi-utility-left-menu-siblings left_sidebar test2 no-title block">    <div class="block-inner clearfix">                                <div class="block-content" style = "width:100%;">        <div class="topic" id="leftside-bar">  					
						 <div class="my_sidebar_head"><i class="fa fa-sitemap"></i><span id = "searchCount" style = 'width:100%;'></span></div>
						 <div class="my_sidebar_head_2">
						 <span>
						 <form action = "<%=request.getContextPath() %>/schemes/search/<%=searchString %>/filter-By-State" method = "post">
						 <select name = "filterByState" id = "filterByState">
	  <option value = "all">All</option>
	  <option value = "central-government-schemes">Central Government Schemes</option>
      <option value = "arunachal-pradesh-govt-schemes">Arunachal Pradesh Government Schemes</option>
      <option value = "andhra-pradesh-govt-schemes">Andhra Pradesh Government Schemes</option>
      <option value = "assam-govt-schemes">Assam Government Schemes</option>
      <option value = "bihar-govt-schemes">Bihar Government Schemes</option>
      <option value = "chhattisgarh-govt-schemes">Chhattisgarh Government Schemes</option>
      <option value = "delhi-govt-schemes">Delhi Government Schemes</option>
      <option value = "haryana-govt-schemes">Haryana Government Schemes</option>
      <option value = "himachal-pradesh-govt-schemes">Himachal Pradesh Government Schemes</option>
      <option value = "jharkhand-govt-schemes">Jharkhand Government Schemes</option>
      <option value = "karnataka-govt-schemes">Karnataka Government Schemes</option>
      <option value = "kerala-govt-schemes">Kerala Government Schemes</option>
      <option value = "madhya-pradesh-govt-schemes">Madhya Pradesh Government Schemes</option>
      <option value = "maharashtra-govt-schemes">Maharastra Government Schemes</option>
      <option value = "mizoram-govt-schemes">Mizoram Government Schemes</option>
      <option value = "manipur-govt-schemes">Manipur Government Schemes</option>
      <option value = "meghalaya-govt-schemes">Meghalaya Government Schemes</option>
      <option value = "nagaland-govt-schemes">Nagaland Government Schemes</option>
      <option value = "odisha-govt-schemes">Odisha Government Schemes</option>
      <option value = "punjab-govt-schemes">Punjab Government Schemes</option>
      <option value = "rajasthan-govt-schemes">Rajasthan Government Schemes</option>
      <option value = "sikkim-govt-schemes">Sikkim Government Schemes</option>
      <option value = "tamil-nadu-govt-schemes">Tamil Nadu Government Schemes</option>
      <option value = "telangana-govt-schemes">Telangana Government Schemes</option>
      <option value = "tripura-govt-schemes">Tripura Government Schemes</option>
      <option value = "uttarakhand-govt-schemes">Uttarakhand Government Schemes</option>
      <option value = "uttar-pradesh-govt-schemes">Uttar Pradesh Government Schemes</option>
      <option value = "west-bengal-govt-schemes">West Bengal Government Schemes</option>
      <option value = "pradhan-mantri-yojana-govt-schemes">Pradhan Mantri Yojana Government Schemes</option>
						 </select>
						 <input type = "submit" value = "Filter by State" style = "cursor:pointer;">
						 </form>
						 </span>
						 <ul>
						 <%
						 if(searchResult != null && mainSearchResult != null){
							 for(StatementGovernmentSchemes schemeCat : mainSearchResult){
								 if(schemeCat.getTypeOfScheme().equalsIgnoreCase("central-government-schemes")){
								    	%>
								    	<li><a href="<%=request.getContextPath()%>/schemes/central-government-schemes/<%=schemeCat.getSchemeName() %>"><%=schemeCat.getSchemeName() %></a></li>
								    	<% 
								     }else{
								    	 %>
								    	 <li><a href="<%=request.getContextPath()%>/schemes/State-Government/<%=schemeCat.getTypeOfScheme() %>/<%=schemeCat.getSchemeName() %>"><%=schemeCat.getSchemeName() %></a></li>
								    	 <%
								     }
								 seaI++;
							 }
							 
							 for(StatementGovernmentSchemes schemeCat : searchResult){
								 if(!mainSearchResult.contains(schemeCat)){
						             if(schemeCat.getTypeOfScheme().equalsIgnoreCase("central-government-schemes")){
								    	%>
								    	<li><a href="<%=request.getContextPath()%>/schemes/central-government-schemes/<%=schemeCat.getSchemeName() %>"><%=schemeCat.getSchemeName() %></a></li>
								    	<% 
								     }else{
								    	 %>
								    	 <li><a href="<%=request.getContextPath()%>/schemes/State-Government/<%=schemeCat.getTypeOfScheme() %>/<%=schemeCat.getSchemeName() %>"><%=schemeCat.getSchemeName() %></a></li>
								    	 <%
								     }
						             seaI++;
								 }
							 }
						 }
						 
						%>  				
						 </div>    
						</div>                  
					</div>  
					</div>      
					</div>   
					</div>
					</div>
					</div>					
 					 
 					 </div>
															<!-- !Feed Icons -->
										</div>
										
					
					
				  </section><!-- /end #main-content -->

				<!-- !Content Aside Region-->
							</div>
        </div><!-- /end .content-inner -->
      </main><!-- /end #content-column -->

      <!-- !Sidebar Regions -->
            
    </div><!-- /end #columns -->

    <!-- !Tertiary Content Region -->
			

<!-- !Footer -->
  <footer id="footer" class="clearfix" role="contentinfo">
						
	<section id="footertop">
		<div class="wrapper">
          <a class="mobilebtmNav" href="javascript:;"><div id="menutext">Menu</div><div class="spanoutetr"><span></span><span></span><span></span></div></a>
			<div class="region region-footer"><div class="region-inner clearfix"><div id="block-block-6" class="block block-block no-title" ><div class="block-inner clearfix">  
  
  <div class="block-content content">
<ul>
<li><a title="NPI Overview " href="../about-portal.html" role="link">About us</a></li>
<li><a title="Write to NPI" href="../contact-us.html" role="link">Contact us</a></li>
<li><a title="Suggestion for NPI" href="../index.html" role="link">Feedback</a></li>
<li><a title="Using NPI" href="../help.html" role="link">Help</a></li>
<li><a href="../link-us.html" role="link">Link to Us</a></li>
<li><a title="Monthly Newsletter" href="../newsletters.html" role="link">Newsletter</a></li>
<li><a title="Recommend NPI" href="../index.html" role="link">Tell a Friend</a></li>
<li><a title="Policies &amp; Disclaimer" href="../website-policy.html" role="link">Website Policy</a></li>
<li><a href="../accessibility-statement.html" role="link">Accessibility Statement</a></li>
<li><a href="../visitor-summary.html" role="link">Visitor Summary</a></li>
<li><a href="../sitemap.html" role="link">Sitemap</a></li>
</ul>


</div>
  </div></div></div></div>		</div>
	</section>
		<section id="fotter-bottom">
	<div class="wrapper">
		<div class="region region-content-aside"><div class="region-inner clearfix"><div id="block-block-7" class="block block-block no-title" ><div class="block-inner clearfix">  
  
  <div class="block-content content"><div class="footer_col3">
	<a href="https://archive.india.gov.in/" target="_BLANK" title="Archive (External Website that opens in a new window)"><img src="../sites/all/themes/adaptivetheme/npi_adaptive/images/img-ftr-archive.png" alt="indiaportal"></a>
				<!--<a href="https://www.india.gov.in/sites/upload_files/npi/files/IndiaPortal.apk" title="Download Mobile App" ><img src="https://www.india.gov.in/sites/all/themes/adaptivetheme/npi_adaptive/images/img-ftr-mobile-center.png" alt="webinfo"></a>-->
				<a  title="Data Portal India (External site that opens in a new window)" target="_blank" href="http://data.gov.in/"><img src="../sites/all/themes/adaptivetheme/npi_adaptive/images/img-ftr-open-data-portal.png" alt="dataportal"></a>
				<a  title="Press Information Bureau Government of India (External site that opens in a new window)" target="_blank" href="http://pib.nic.in/"><img src="../sites/all/themes/adaptivetheme/npi_adaptive/images/pib.png" alt="dataportal"></a>
	</div>
			<div class="footerLinks">
			 

<ul>
  <li class="first"><a href="../topics.html" role="link"><span>Information Related To</span></a>
    <ul>
      <li><a href="agriculture.html" role="link">Agriculture</a></li>
      <li><a href="art-culture.html" role="link">Art &amp; Culture</a></li>
      <li><a href="commerce.html" role="link">Commerce</a></li>
      <li><a href="communication.html" role="link">Communication</a></li>
      <li><a href="defence.html" role="link">Defence</a></li>
      <li><a href="education.html" role="link">Education</a></li>
      <li><a href="environment-forest.html" role="link">Environment &amp; Forest</a></li>
      <li><a href="finance-taxes.html" role="link">Finance &amp; Taxes</a></li>
      <li><a href="food-public-distribution.html" role="link">Food &amp; Public Distribution</a></li>
      <li><a href="foreign-affairs.html" role="link">Foreign Affairs</a></li>
      <li><a href="governance-administration.html" role="link">Governance &amp; Administration</a></li>
      <li><a href="health-family-welfare.html" role="link">Health &amp; Family Welfare</a></li>
      <li><a href="housing.html" role="link">Housing</a></li>
      <li><a href="home-affairs-enforcement.html" role="link">Home Affairs &amp; Enforcement</a></li>
      <li><a href="industries.html" role="link">Industries</a></li>
      <li><a href="infrastructure.html" role="link">Infrastructure</a></li>
      <li><a href="information-broadcasting.html" role="link">Information &amp; Broadcasting</a></li>
      <li><a href="labour-employment.html" role="link">Labour &amp; Employment</a></li>
      <li><a href="law-justice.html" role="link">Law &amp; Justice</a></li>
      <li><a href="power-energy.html" role="link">Power &amp; Energy</a></li>
      <li><a href="rural.html" role="link">Rural</a></li>
      <li><a href="science-technology.html" role="link">Science &amp; Technology</a></li>
      <li><a href="social-development.html" role="link">Social Development</a></li>
      <li><a href="transport.html" role="link">Transport</a></li>
      <li><a href="travel-tourism.html" role="link">Travel &amp; Tourism</a></li>
      <li><a href="youth-sports.html" role="link">Youth &amp; Sports</a></li>
    </ul>
  </li>
  <li><a href="../my-government.html" role="link"><span>About the Government</span></a>
    <ul>
      <li><a href="../my-government/constitution-india.html" role="link">Constitution of India</a></li>
      <li><a href="../my-government/government-directory.html" role="link">Government Web Directory</a></li>
      <li><a href="../my-government/indian-parliament.html" role="link">Indian Parliament</a></li>
      <li><a href="../my-government/publications.html" role="link">Publications</a></li>
      <li><a href="../my-government/whos-who.html" role="link">Who's Who</a></li>
      <li><a href="../my-government/whos-who/president.html" role="link">President of India</a></li>
      <li><a href="../my-government/whos-who/vice-president.html" role="link">Vice-President of India</a></li>
      <li><a href="../my-government/whos-who/prime-minister.html" role="link">Prime Minister of India</a></li>
      <li><a href="../my-government/whos-who/council-ministers.html" role="link">Cabinet Ministers</a></li>
      <li><a href="../my-government/whos-who/chiefs-armed-forces.html" role="link">Chiefs of Armed Forces</a></li>
      <li><a href="../my-government/whos-who/members-parliament.html" role="link">Members of Parliament</a></li>
    </ul>
  </li>
 
</ul>




			</div>



			</div>
  </div></div><div id="block-block-9" class="block block-block no-title" ><div class="block-inner clearfix">  
  
  <div class="block-content content"><div class="lastupdated">
<a class="nic_logo" href="http://www.nic.in/" target="_blank"><img src="../sites/all/themes/adaptivetheme/npi_adaptive/images/nic.png" alt="nic"/></a>
<p>This is the National Portal of India, developed with an objective to enable a single window access to information and services being provided by the various Indian Government entities. This Portal is a <a href="http://meity.gov.in/content/mission-mode-projects" target="_blank" title="External site that opens in a new window" >Mission Mode Project</a> under the <a href="http://meity.gov.in/divisions/national-e-governance-plan" target="_blank" title="External site that opens in a new window" >National E-Governance Plan</a>, designed and developed by <a href="http://www.nic.in/" target="_blank" title="External site that opens in a new window" >National Informatics Centre (NIC)</a>, <a href="http://www.meity.gov.in/" target="_blank" title="External site that opens in a new window" >Ministry of Electronics & Information Technology</a>, Government of India.</p>
<div class="last-review"> Last reviewed and updated on  29 Dec, 2017<span style="color:#131313;"> 

 NPI 1

</span></div>
</div>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','../../www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-42583533-1', 'auto');
  ga('send', 'pageview');

  
  
document.getElementById("searchCount").innerHTML = "Search Results - " + "<%=seaI %> : " + "<%=searchString %>";  
  
</script>
</div>
  </div></div></div></div>		</div>
	</section>
	
  </footer>


   
			<script type="text/javascript">
				$(document).ready(function(){ 
					$("body").addClass("parent-166659");
				});
							
							
			</script>
			  </body>

<!-- Mirrored from www.india.gov.in/topics/agriculture by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 22 Jan 2018 17:10:34 GMT -->
</html>
