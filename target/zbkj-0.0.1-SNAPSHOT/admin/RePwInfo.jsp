<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.portal.pageModel.SessionInfo"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>销售人员重置密码</title>
	<script type="text/javascript">
	// 获取路径
	var ctx = '${ctx}';
	</script>
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
    <!-- 自定义JS -->
    <script src="${pageContext.request.contextPath}/admin/js/RePwInfo.js"></script>
    
</head>
<body>
	<!-- 整个页面外层container -->
	<div class="container">
		<p class="lead"><span class="glyphicon glyphicon-list"></span>销售人员重置密码</p>
	    <form class="form-horizontal">
	    <div style="padding-top:100px;">
	       <div class="form-group">
	          <div class="col-sm-3"></div>
	          <label for="username-reset-input" class="col-sm-1 control-label codeSubmitLabel">用户名:</label>
		  	  <div class="col-sm-4">
				<input type="text" class="form-control" id="username-reset-input"  aria-describedby="basic-addon2">
			   </div>
		   </div>
		  <div class="form-group">
	          <div class="col-sm-3"></div>
	          <label for="question" class="col-sm-1 control-label codeSubmitLabel">问题:</label>
			  <div class="col-sm-4">
				   <input type="text" class="form-control" readonly = "readonly" id="question" aria-describedby="basic-addon2">
			   </div>
		 </div>
		 <div class="form-group">
	          <div class="col-sm-3"></div>
	          <label for="answer" class="col-sm-1  control-label codeSubmitLabel">答案:</label>
			  <div class="col-sm-4">
				  <input type="text" class="form-control" id="answer" aria-describedby="basic-addon2">
			   </div>
		   </div>
		    
		    <div class="form-group" id="warning">
		    		<div class="col-sm-5"></div>
		   			<p><span id="ray" style="color:red;white-space:pre;">&nbsp;</span></p>
		    </div>
	       <div class="form-group" style="padding-top:30px;">
	          <div class="text-center">
	    		  <button class="btn btn-default" type="button" id="resetPasswordBtn">确认</button>&nbsp;&nbsp;&nbsp;
	    	  </div>
	       </div>
	    </div>
	    </form>
	</div>	
<!-- 提示 -->
	<div class="modal fade" id="resetPassword">
	  <div class="modal-dialog modal-sm">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">重置密码信息提示</h4>
	      </div>
	      <div class="modal-body">
	        <p class="warningText"><span id="resetInfo"  white-space:pre;>&nbsp;</span></p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
	      </div>
	    </div>
	  </div>
	</div>
</body>
</html>