<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.portal.pageModel.SessionInfo"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="roleId" value="${sessionScope.sessionInfo.roleId}" />
<c:set var="loginPassword" value="${sessionScope.sessionInfo.loginPassword}" />
<script type="text/javascript">
    // 获取路径
    var ctx = '${ctx}';
    var roleId = '${roleId}';
    var oldPassWord = '${loginPassword}';
</script>

<!-- 登录Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title text-center" id="loginModalLabel">登录</h4>
			</div>
			<div class="modal-body">
				<form>
					<div class="input-group form-group" id="usernameInputGroup">
						<span class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span> 
						<input type="text" class="form-control" id="username" placeholder="手机号/邮箱地址" aria-describedby="basic-addon1">
					</div>
					<div class="input-group form-group" id="passwordInputGroup">
						<span class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span> 
						<input type="password" class="form-control" id="password" placeholder="请输入密码" aria-describedby="basic-addon1">
					</div>
					<div>
						<p style="margin:0 0 0 0";><span id="ray" style="color:red;">&nbsp;</span></p>
					</div>
					<button type="button" class="btn btn-info navbar-right-btn" id="loginModelLoginBtn">登录</button>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/admin/SalesmanRegister.jsp">注册</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/admin/RePwInfo.jsp">忘记密码</a>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 忘记密码Modal -->
<div class="modal fade" id="forgetPassword" tabindex="-1" role="dialog" aria-labelledby="forgetPasswordLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title text-center" id="forgetPasswordLabel">重置密码</h4>
			</div>
			<div class="modal-body">
				<form>
					<div class="input-group form-group" id="usernameResetInputGroup">
						<span class="input-group-addon"><span aria-hidden="true"></span>用户名</span> 
						<input type="text" class="form-control" id="username-reset-input" placeholder="请输入账户" aria-describedby="basic-addon1">
					</div>
					<div class="input-group form-group" id="emailInputGroup">
						<span class="input-group-addon"><span aria-hidden="true"></span>邮箱地址</span> 
						<input type="text" class="form-control" id="email-reset-input" placeholder="请输入完整邮箱" aria-describedby="basic-addon1">
					</div>
					<div>
						<p style="margin:0 0 0 0";><span id="newray" style="color:red; white-space:pre;">&nbsp;</span></p>
					</div>
					<button type="button" class="btn btn-info navbar-right-btn" id="resetPasswordBtn" data-toggle="modal">重置密码</button>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 提示激活成功 -->
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

<!--最上面的响应式导航栏-->
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-responsive-collapse">
				<span class="sr-only">Toggle Navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a href="/admin/index.jsp" class="navbar-brand">KGO-M4</a>
		</div>
		<div class="collapse navbar-collapse navbar-responsive-collapse">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/admin/index.jsp">首页</a></li>
				<%
					SessionInfo sessionInfo = (SessionInfo)session.getAttribute("sessionInfo");
					if(sessionInfo != null && sessionInfo.getRoleId() == 1) {
				%>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">人员管理<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/admin/StaffManage.jsp">查看所有人员</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/StaffAdd.jsp">添加人员</a></li>
					</ul>
				</li>
				<%} %>

				<%
					if(sessionInfo != null && (sessionInfo.getRoleId() == 1 || sessionInfo.getRoleId() == 3)) {
				%>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">用户管理<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/admin/UserManage.jsp">查看所有用户</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/UserModuleOrder.jsp">查看用户模块订单</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/UserAdd.jsp">添加用户</a></li>
					</ul>
				</li>

				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">用户缴费<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/admin/UserPaymentForModule.jsp">延长模块期限</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/UserPayment.jsp">延长用户期限</a></li>
					</ul>
				</li>
				<%} %>
			</ul>

			<ul class="nav navbar-nav navbar-right" id="navbarRightBtns">
				<%
					sessionInfo = (SessionInfo)session.getAttribute("sessionInfo");
					if(sessionInfo == null || sessionInfo.getRoleId() == null) {
				%>
				<li>
					<button type="button" class="btn btn-info navbar-right-btn" id="loginBtn" data-toggle="modal" data-target="#loginModal">登录</button>
				</li>
				<li>
					<button type="button" class="btn btn-info navbar-right-btn" id="sign" data-toggle="modal" data-target="#signinModal">注册</button>
				</li>
				<%
					out.close();
					}
					else {
				%>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionInfo.name} <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li class="text-center"><a href="${pageContext.request.contextPath}/admin/StaffPersonSetting.jsp"><span class="glyphicon glyphicon-cog"></span> 个人设置</a></li>
						<li class="text-center" id="safeLogOff"><a href="#"><span class="glyphicon glyphicon-off"></span> 安全退出</a></li>
					</ul>
				</li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
</div>