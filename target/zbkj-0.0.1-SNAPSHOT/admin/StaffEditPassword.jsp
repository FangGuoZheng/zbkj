<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>修改员工密码</title>
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
    <script src="${pageContext.request.contextPath}/admin/js/StaffEditPassword.js"></script>
</head>
<body>
	<script type="text/javascript">
		var staffIdWithStaffEditPass=<%=request.getParameter("id") %>;
		var account='<%=request.getParameter("account") %>';
	</script>
	<!-- 整个页面外层container -->
	<div class="container">
		<!-- 公用的导航栏 -->
		<jsp:include page="./CommonHead.jsp"></jsp:include>
		<p class="lead"><span class="glyphicon glyphicon-list"></span>修改员工密码</p>
	    <form class="form-horizontal">
	    <div style="padding-top:100px;">
	       <div class="form-group">
	          <div class="col-sm-3"></div>
	          <label for="account-input" class="col-sm-1 control-label codeSubmitLabel">账号:</label>
			  <div class="col-sm-4">
				  <input type="text" class="form-control" id="account-input" aria-describedby="basic-addon2" readonly="true">
			   </div>
		   </div>
		   <div class="form-group">
	          <div class="col-sm-3"></div>
	          <label for="newPassword-input" class="col-sm-1 control-label codeSubmitLabel">新密码:</label>
			  <div class="col-sm-4">
				  <input type="password" class="form-control" id="newPassword-input" aria-describedby="basic-addon2">
			   </div>
		   </div>
		   <div class="form-group">
	          <div class="col-sm-3"></div>
	          <label for="confirmPassword-input" class="col-sm-1 control-label codeSubmitLabel">确认密码:</label>
			  <div class="col-sm-4">
				  <input type="password" class="form-control" id="confirmPassword-input" aria-describedby="basic-addon2">
			   </div>
		   </div>
	       <div class="form-group" style="padding-top:50px;">
	          <div class="text-center">
	    		  <button class="btn btn-default" type="button" id="confirm">确认</button>&nbsp;&nbsp;&nbsp;<button class="btn btn-default" type="button" id="cancel">取消</button>
	    	  </div>
	       </div>
	    </div>
	    </form>
	</div>
	

</body>
</html>