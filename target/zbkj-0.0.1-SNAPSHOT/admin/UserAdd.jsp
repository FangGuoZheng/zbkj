<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.portal.pageModel.SessionInfo"%>

<div class="modal fade" id="promptModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title text-center" id="loginModalLabel">提示框</h4>
			</div>
			<div class="modal-body">
				<form>
					<div class="input-group form-group" id="mobileInput"></div>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-info navbar-right-btn" id="confirmModelLoginBtn">确定</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-info navbar-right-btn" id="cancelModelLoginBtn">取消</button>
				</form>
			</div>
		</div>
	</div>
</div>

<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>添加用户</title>

	<%
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute("sessionInfo");
	%>
	<script type="text/javascript">
        var saleId="<% if(sessionInfo!=null)
                              out.print(sessionInfo.getStaffId());%>";
        var roleId="<% if(sessionInfo!=null)
                              out.print(sessionInfo.getRoleId());%>";
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
    <!-- 必须的公共JS -->
    <script src="${pageContext.request.contextPath}/admin/js/Common.js"></script>
    <!-- 自定义JS -->
    <script src="${pageContext.request.contextPath}/admin/js/UserAdd.js"></script>

	<script>
        $(function() {
            if (roleId == 1){
                $.ajax({
                    type: "POST",
                    url: ctx + '/StaffAction!getByRole.action',
                    data:{
                        roleId:3
                    },
                    async:false,
                    success: function(json)
                    {
                        var dataObj = eval("(" + json + ")"),
                            msg = dataObj["msg"],
                            successFlag = dataObj["success"];

                        if (successFlag) {
                            $("#salesmanName").append("<option value=''></option>");

                            var rows = dataObj["obj"];

                            for (var i = 0; i < rows.length; i++) {
                                // 当前行所有数据
                                var row = rows[i];
                                var id = row["id"];
                                var name = row["name"];
                                $("#salesmanName").append("<option value='"+id+"'>"+name+"</option>");
                            }

                        }
                    },
                    error: function(){
                    }
                });
            }
        });
	</script>
</head>
<body>
	<!-- 整个页面外层container -->
	<div class="container">
		<!-- 公用的导航栏 -->
		<jsp:include page="/admin/CommonHead.jsp"></jsp:include>
		<p class="lead"><span class="glyphicon glyphicon-list"></span>通过第三方网站获取用户信息</p>
	    <form class="form-horizontal">
	    <div style="padding-top:100px;">

			<%
				if (sessionInfo.getRoleId() == 1){
			%>
			<div class="form-group">
				<div class="col-sm-3"></div>
				<label for="salesmanName" class="col-sm-1 control-label codeSubmitLabel">销售人员:</label>
				<div class="col-sm-4">
					<select name="salesmanName" class="form-control" id="salesmanName" aria-describedby="basic-addon2">
					</select>
				</div>
			</div>
			<%
				}
			%>

			<div class="form-group">
				<div class="col-sm-3"></div>
				<label for="areacode-input" class="col-sm-1 control-label codeSubmitLabel">手机国家地区代码:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="areacode-input" aria-describedby="basic-addon2" placeholder="中国地区代码是86" value="86" />
				</div>
			</div>

	       <div class="form-group">
	          <div class="col-sm-3"></div>
	          <label for="mobile-input" class="col-sm-1 control-label codeSubmitLabel">手机号码:</label>
			  <div class="col-sm-4">
				  <input type="text" class="form-control" id="mobile-input" aria-describedby="basic-addon2">
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