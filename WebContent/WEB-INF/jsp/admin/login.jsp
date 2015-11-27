<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8">
<title>登录</title>
<%@ include file="include_header.jsp"  %>
<style type="text/css">
body {
	background: url(static/com/img/bg-login.jpg) !important;
}
</style>
</head>
<body>
	<div class="container-fluid-full">
		<div class="row-fluid">

			<div class="row-fluid">
				<div class="login-box">
					<div class="icons">
						<a href="index.html"><i class="halflings-icon home"></i></a> <a
							href="#"><i class="halflings-icon cog"></i></a>
					</div>
					<h2>登录账户</h2>
					<form class="form-horizontal" action="index.html" method="post">
						<fieldset>

							<div class="input-prepend" title="Username">
								<span class="add-on"><i class="halflings-icon user"></i></span>
								<input class="input-large span10" name="username" id="username" type="text" placeholder="请输入用户名" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend" title="Password">
								<span class="add-on"><i class="halflings-icon lock"></i></span>
								<input class="input-large span10" name="password" id="password" type="password" placeholder="请输入密码" />
							</div>
							<div class="clearfix"></div>
							<div class="alert alert-error hide" id="result_alert">
								<button data-dismiss="alert" class="close" type="button">×</button>
								<strong>账户名或密码错误!</strong>
							</div>
							<label class="remember" for="remember">
								<input type="checkbox" id="rememberme" value="y"/>记住登录
							</label>
							<div class="button-login">
								<button type="button" id="btnLogin" class="btn btn-primary">登录</button>
							</div>
							<div class="clearfix"></div>
					</form>
					<hr>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="include_scripts.jsp"  %>
	<script type="text/javascript">
		if (window.top !== window.self){
			window.top.location.href="<%=basePath%>admin";
		}
		$('#btnLogin').click(function(){
			$.post('admin/login',{"name":$('#username').val(),"password":$('#password').val(),"rememberme":$('#rememberme').val()},function(data){
				if(data.code==0){
					location.href="<%=basePath%>admin/index";
				}else{
					$('#result_alert').show();
				}
			});
		});
		$('#changeCode').click(function(){
			$('#randCode').attr('src','admin/random?'+Math.random());
		});
	</script>
</body>
</html>