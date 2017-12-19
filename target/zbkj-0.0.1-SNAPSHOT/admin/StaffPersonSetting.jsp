<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>个人设置</title>
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/jslib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- 自定义样式 -->
    <link href="${pageContext.request.contextPath}/admin/css/index.css" rel="stylesheet">
    <!-- html5shiv.js和Respond.js为IE8提供HTML5元素以及媒体查询的支持 -->
    <!--[if lt IE 9]>
    	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/jslib/jQuery/jquery-1.11.3.min.js"></script>
    
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/jslib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    
    <!-- bootstrap-notify -->
    <script src="${pageContext.request.contextPath}/jslib/bootstrap-notify/bootstrap-notify.min.js"></script>
    <!-- 必须的公共JS -->
    <script src="${pageContext.request.contextPath}/admin/js/Common.js"></script>
    <!-- 自定义JS -->
    <script src="${pageContext.request.contextPath}/admin/js/StaffPersonSetting.js"></script>
    <style type="text/css">
	  #emailcss{text-align: left;}
	</style>
</head>
<body>
	<!-- 整个页面外层container -->
	<div class="container">	
		<!-- 公用的导航栏 -->
		<jsp:include page="CommonHead.jsp"></jsp:include>
		
		<!-- 标题 -->
		<p class="lead"><span class="glyphicon glyphicon-cog"></span> 个人设置</p>
		
		<!-- 内容行 -->
		<div class="row">
			<div class="col-md-5 col-md-offset-1 col-with-border">
				<p class="lead text-center text-title">修改信息</p>
				<form class="form-horizontal">

					<input type="hidden" class="form-control" id="staffId" value="${sessionInfo.staffId}" >

					<div class="form-group">
				    	<label for="loginNameInput" class="col-sm-3 col-sm-offset-1 control-label">用户名</label>
				    	<div class="col-sm-7">
				      		<input type="text" class="form-control" id="loginNameInput" value="${sessionInfo.loginName}" disabled="disabled" >
				    	</div>
				  	</div>
					<div class="form-group">
						<label for="name" class="col-sm-3 col-sm-offset-1 control-label">姓名</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="name" value="${sessionInfo.name}" disabled="disabled" >
						</div>
					</div>
					<div class="form-group">
						<label for="roleId" class="col-sm-3 col-sm-offset-1 control-label">角色</label>
						<div class="col-sm-7">
							<select name="roleId" class="form-control" id="roleId" value="${sessionInfo.roleId}" >
								<option value="1">管理员</option>
								<option value="2">技术人员</option>
								<option value="3">销售人员</option>
								<option value="4">销售经理</option>
								<option value="5">总经理</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="phone" class="col-sm-3 col-sm-offset-1 control-label">电话号码</label>
						<div class="col-sm-7">
							<input type="number" class="form-control" id="phone" value="${sessionInfo.phone}" >
						</div>
					</div>
				  	<div class="form-group">
				    	<label for="email" class="col-sm-3 col-sm-offset-1 control-label">邮箱</label>
				    	<div class="col-sm-7">
				      		<input type="email" class="form-control" id="email" value="${sessionInfo.email}" >
				    	</div>
				  	</div>
				  	<div class="form-group">
				    	<div class="col-sm-7 col-sm-offset-3">
					    	<button type="button" class="btn btn-info login-btn" id="savePersonInfoBtn">保存</button>
				    	</div>
				  	</div>


				</form>			
			</div>
			<div class="col-md-4 col-md-offset-1  col-with-border">
				<p class="lead text-center text-title">修改密码</p>
				<form class="form-horizontal">
					<div class="form-group">
				    	<label for="currentPassword" class="col-sm-3 col-sm-offset-1 control-label">旧密码</label>
				  		<div class="col-sm-7">
				      		<div class="input-group" id="currentPasswordInputGroup">
	  							<span class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span>
	  							<input type="password" class="form-control" id="currentPassword" placeholder="请输入旧密码" aria-describedby="basic-addon1">
							</div>
				    	</div>
				  	</div> 
				  	<div class="form-group">
				    	<label for="newPassword" class="col-sm-3 col-sm-offset-1 control-label">新密码</label>
				  		<div class="col-sm-7">
				      		<div class="input-group" id="newPasswordInputGroup">
	  							<span class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span>
	  							<input type="password" class="form-control" id="newPassword" placeholder="请输入新密码" aria-describedby="basic-addon1">
							</div>
				    	</div>
				  	</div>
				  	<div class="form-group">
				  		<label for="repeatNewPassword" class="col-sm-3 col-sm-offset-1 control-label">确认密码</label>
				  		<div class="col-sm-7">
				      		<div class="input-group" id="repeatNewPasswordInputGroup">
	  							<span class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span>
	  							<input type="password" class="form-control" id="repeatNewPassword" placeholder="请输入新密码" aria-describedby="basic-addon1">
							</div>
				    	</div>
					</div>
				  	<div class="form-group">
				    	<div class="col-sm-7 col-sm-offset-4">
					    	<button type="button" class="btn btn-info login-btn" id="savePasswordBtn">保存</button>
				    	</div>
				  	</div>
				</form>			
			</div>
		</div>
	</div>
</body>
</html>