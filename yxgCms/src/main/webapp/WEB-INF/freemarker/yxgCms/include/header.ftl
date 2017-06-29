<link href="${ (project.staticDomain)! }/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<!-- Font Awesome Icons -->
<link href="${ (project.staticDomain)! }/css/common/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="${ (project.staticDomain)! }/css/common/ionicons/css/ionicons.min.css" rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="${ (project.staticDomain)! }/libs/AdminLTE-2.1.1/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
<!-- base css -->
<link href="${ (project.staticDomain)! }/css/cas/base.css" rel="stylesheet" type="text/css" />
<!-- common -->
<link href="${ (project.staticDomain)! }/css/cas/common.css" rel="stylesheet" type="text/css" />

<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
<link href="${ (project.staticDomain)! }/libs/AdminLTE-2.1.1/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="${ (project.staticDomain)! }/libs/Zebra_Dialog/css/flat/zebra_dialog.css"/>
<link href="${(project.staticDomain!)}/css/cas/pagecss/pagination/dataTables.bootstrap.css"	rel="stylesheet" type="text/css" />
 <!-- jQuery 2.1.4 -->
<script src="${ (project.staticDomain)! }/libs/jquery/jquery-1.11.0.min.js"></script>
<!-- Bootstrap 3.3.2 JS -->
<script src="${ (project.staticDomain)! }/libs/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- AdminLTE App -->
<script src="${ (project.staticDomain)! }/libs/AdminLTE-2.1.1/dist/js/app.min.js" type="text/javascript"></script>

<script src="${ (project.staticDomain)! }/libs/Zebra_Dialog/js/zebra_dialog.js"></script>

<script src="${ (project.staticDomain)! }/js/cas/pagejs/pagination/jquery.dataTables.min.js"></script>
<script src="${ (project.staticDomain)! }/js/cas/pagejs/pagination/dataTables.bootstrap.js"></script>



<!-- jquery validate -->
<script src="${ (project.staticDomain)! }/libs/jquery-validate/jquery.validate.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/jquery-validate/additional-methods.min.js"></script>
<script src="${ (project.staticDomain)! }/js/xygCms/pagejs/include/navigator.js"></script>

<script src="${ (project.staticDomain)! }/js/xygCms/common.js"></script>

<style>
.form_check>span{ 
	color: red;
}
.showMenuBar{
	display: none;
}
</style>

<!-- header 头  -->
<header class="main-header">
    <!-- Logo -->
    <a href="/cas" class="logo">
      <span class="logo-mini">CMS</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg">内容管理系统</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
      <div class="navbar-custom-menu">
      	<ul class="nav navbar-nav">
      	<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
          <span class="username">${(userInfo.nickname)!"管理员"}</span>
           <span class="caret"></span>
           </a>
          <ul class="dropdown-menu" role="menu" >
             <li><a  id="user-login-out" href="#"><span class="glyphicon glyphicon-log-out"></span> 退出</a></li>
          </ul>
        </li>
      	</ul>
      </div>
    </nav>
 </header>
 <!-- 右侧菜单 -->
 <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
    
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="<#if  requestPath[2] == 'course'>active</#if>">
          <a  href="/yxgCms/course/listPage">
            <i class="fa fa-sitemap"></i><span>课程管理</span>
          </a>
        </li>
        <li class="<#if  requestPath[2] == 'view'>active</#if>">
          <a  href="/yxgCms/view/listPage">
            <i class="fa fa-sitemap"></i><span>资讯管理</span>
          </a>
        </li>
        <li class="<#if  requestPath[2] == 'teacher'>active</#if>">
          <a  href="/yxgCms/teacher/listPage">
            <i class="fa fa-sitemap"></i><span>老师管理</span>
          </a>
        </li>
        <li class="<#if  requestPath[2] == 'content' >active</#if>">
          <a  href="/yxgCms/content/listPage">
            <i class="fa fa-sitemap"></i><span>视频管理</span>
          </a>
        </li>
        <li class="<#if  requestPath[2] == 'homework'>active</#if>">
          <a  href="/yxgCms/homework/listPage">
            <i class="fa fa-sitemap"></i><span>作业管理</span>
          </a>
        </li>
        <li class="<#if  requestPath[2] == 'ad'>active</#if>">
          <a  href="/yxgCms/ad/listPage">
            <i class="fa fa-sitemap"></i><span>广告位管理</span>
          </a>
        </li>
        <li class="<#if  requestPath[2] == 'member'>active</#if>">
          <a  href="/yxgCms/member/listPage">
            <i class="fa fa-sitemap"></i><span>会员管理</span>
          </a>
        </li>
        <li class="<#if  requestPath[2] == 'conf'>active</#if>">
          <a  href="/yxgCms/conf/listPage">
            <i class="fa fa-sitemap"></i><span>系统配置管理</span>
          </a>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
 </aside>
