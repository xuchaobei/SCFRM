<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>四川出口食品农产品3+3G检验检疫监管系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- jQuery UI -->
<link href="css/jquery-ui-1.10.4.custom.css" rel="stylesheet"
	media="screen">
<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="vendors/datatables/dataTables.bootstrap.css"
	rel="stylesheet" media="screen">
<link href="vendors/form-helpers/css/bootstrap-formhelpers.css"
	rel="stylesheet">
<link href="vendors/bootstrap-datetimepicker/datetimepicker.css"
	rel="stylesheet">
<!-- styles -->
<link href="css/styles.css" rel="stylesheet">
<link href="css/buttons.css" rel="stylesheet">
</head>
<body>
<%-- <input type="text" id="loginyanz" value="${OPERATOR_NAME}"> --%>
	<div class="header" style="background-color:#E5F3FF">
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<!-- Logo -->
					<div class="logo">  
						<h1>
						<img alt="" src="css/images/top.gif" style="width: 105%;margin-top:-2%;margin-left:-8%">
							<!-- <a href="index.html">四川出口食品农产品3+3G检验检疫监管系统</a> -->
							
						</h1>
					</div>
				</div>
				<div class="col-md-3">        
				
					<div class="navbar navbar-inverse" role="banner">         
						<nav
							class="collapse navbar-collapse bs-navbar-collapse navbar-right"
							role="navigation">
						<ul class="nav navbar-nav">
							 <li class="dropdown" ><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"><b class=" glyphicon glyphicon-user" style="color:#215F2C"></b>&nbsp;&nbsp;&nbsp;<label style="color: black;">当前用户：&nbsp;<s:property value="#session.OPERATOR_NAME" /></label></a>
								<ul class="dropdown-menu animated fadeInUp">
									<li><a href="login.html?LoginAction_logout">退出</a></li>
								</ul></li> 
								
								
						</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="page-content">
		<div class="row" id="content">
			<div class="col-md-2" id="left-content">
				<div class="sidebar content-box" style="display: block;">
					<ul class="nav">
						<!-- Main menu -->
						<li class="submenu" id="business-nav"><a href="#"> <i
								class="glyphicon glyphicon-random"></i> 接单验放<span
								class="caret pull-right"></span>
						</a>
								<ul>
								<li><a href="#">检验员接单</a></li>
								<li><a href="#">样品登记</a></li>
								<li><a href="#">合格性评定</a></li>
								<li><a href="#">异常单证</a></li>
								<li><a href="#">单证流程查询</a></li>
								<li><a href="#">报检单证查询</a></li>								
								</ul>						
						</li>
						
						<li class="submenu" id="enterprise-nav"><a href="#"> <i
								class="glyphicon glyphicon-warning-sign"></i> 企业监管 <span
								class="caret pull-right"></span>
						</a>

						<ul>
							<li><a href="#">企业基本数据</a></li>
							<li><a href="#">企业产品审核</a></li>
							<li><a href="#">企业产品管理</a></li>
							<li><a href="#">基地管理</a></li>
							<li><a href="#">基地原料放行</a></li>
							<li><a href="#">原料批放行</a></li>
							<li><a href="#">监控计划结果登记</a></li>
							
						</ul>

						</li>
						<li class="submenu" id="risk-nav"><a href="#"> <i
								class="glyphicon glyphicon-fire"></i> 风险布控 <span
								class="caret pull-right"></span>
						</a>
							<ul>
								<li><a href="#">常规布控</a></li>
								<li><a href="#">应急布控</a></li>
								<li><a href="#">监控表单</a></li> 
								<li><a href="#">辅料布控</a></li>
								<li><a href="#">添加剂布控</a></li>
								<li><a href="#">国外通报登记</a></li>
							
							</ul>
						</li>
						<li class="submenu" id="statistics-nav"><a href="#"> <i

								class="glyphicon glyphicon-stats"></i> 查询统计 <span
								class="caret pull-right"></span>
						</a>
							<ul>
								<li><a href="#">报检批统计</a></li>
								<li><a href="#">货物批统计</a></li>
								<li><a href="#">产品分析</a></li>
								<li><a href="#">项目分析</a></li>
								<li><a href="#">抽批分析</a></li>
								<li><a href="#">原料基地分析</a></li>
								<li><a href="#">放行方式统计</a></li>
								<li><a href="#">报检批查询</a></li>
								<li><a href="#">辅料使用查询</a></li>
								<li><a href="#">添加剂使用查询</a></li>
								<li><a href="#">检测结果查询</a></li>
								<li><a href="#">统计计算</a></li>
							</ul>
						</li>
						<li class="submenu" id="management-nav"><a href="#"> <i
								class="glyphicon glyphicon-wrench"></i> 系统管理 <span
								class="caret pull-right"></span>
						</a>
							<ul>

								<li><a href="#">人员管理</a></li>
								<li><a href="#">密码修改</a></li>
								<li><a href="#">角色管理</a></li> 
								<li><a href="#">第三方检测机构</a></li> 
								<li><a href="#">机构及部门管理</a></li>
								<li><a href="#">产品数据规范</a></li>
							</ul>
						</li>
						<li class="submenu" id="basic-setting-nav"><a href="#"> <i
								class="glyphicon glyphicon-cog"></i> 基础参数设置 <span
								class="caret pull-right"></span>
						</a> <!-- Sub menu -->
							   <ul>
								<li><a href="#">产品类别设置</a></li>
								<li><a href="#">加工方式设置</a></li>
								<li><a href="#">原料类别设置</a></li>
								<li><a href="#">原料来源设置</a></li>
								<li><a href="#">包装类型设置</a></li>
								<li><a href="#">预期用途设置</a></li>
								<li><a href="#">加工程度设置</a></li>
								<li><a href="#">储存条件设置</a></li>
								<li><a href="#">国家地区设置</a></li>
								<li><a href="#">检测项目设置</a></li>
								<li><a href="#">辅料设置</a></li>
								<li><a href="#">添加剂设置</a></li>
								<li><a href="#">添加剂用途设置</a></li>
								<li><a href="#">抽检率设置</a></li>
								<!-- <li><a href="#">产品数据规范</a></li> -->
								</ul>
								

						</li>
					</ul>
				</div>
			</div>
			<div class="col-md-10" id="right-content"></div>
		</div>
	</div>

	<footer>
	<div class="container">
		<div class="copy text-center">
			版权所有： <a href='http://www.scciq.gov.cn/'>四川出入境检验检疫局</a>
		</div>
	</div>
	</footer>

	<div class="overlay"></div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/lib/jquery-1.10.1.js"></script>
	<script src="js/lib/jquery-ui-1.10.4.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="vendors/datatables/js/jquery.dataTables.js"></script>
	<script src="vendors/datatables/dataTables.bootstrap.js"></script>
	<script src="js/lib/custom.js"></script>
	<script src="js/lib/select.js"></script>
	<script src="js/lib/jquery.json.js"></script>
	<script src="js/index.js"></script>
</body>
</html>
