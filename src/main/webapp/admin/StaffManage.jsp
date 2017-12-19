<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.portal.pageModel.SessionInfo"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>人员管理</title>
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/jslib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- 自定义样式 -->
    <link href="${pageContext.request.contextPath}/admin/css/index.css" rel="stylesheet">
    <!-- DT Grid 表格插件的js、css文件 -->
       <script src="${pageContext.request.contextPath}/jslib/jquery.dtGrid.v1.1.9/jquery-1.11.3.min.js"></script>
       <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/jslib/jquery.dtGrid.v1.1.9/dependents/bootstrap/plugins/ie/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/jslib/jquery.dtGrid.v1.1.9/dependents/bootstrap/plugins/ie/respond.js"></script>
    <![endif]-->
    <!--[if lt IE 8]>
    <script src="${pageContext.request.contextPath}/jslib/jquery.dtGrid.v1.1.9/dependents/bootstrap/plugins/ie/json2.js"></script>
    <![endif]-->
    <!-- font-awesome -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/jquery.dtGrid.v1.1.9/dependents/fontAwesome/css/font-awesome.min.css" media="all" />
    <!-- dtGrid -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery.dtGrid.v1.1.9/jquery.dtGrid.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery.dtGrid.v1.1.9/i18n/zh-cn.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/jquery.dtGrid.v1.1.9/jquery.dtGrid.css" />
    <!-- DT Grid 表格插件的js、css文件 -->
    
    <%
					SessionInfo sessionInfo = (SessionInfo)session.getAttribute("sessionInfo");
	 %>
	  <script type="text/javascript">
        var roleName="<% if(sessionInfo!=null)
                              out.print(sessionInfo.getRoleName());%>";
	 </script>
        <!-- 必须的公共JS -->
    <script src="${pageContext.request.contextPath}/admin/js/Common.js"></script>
    <!-- 自定义JS -->
    <script src="${pageContext.request.contextPath}/admin/js/StaffManage.js"></script>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/jslib/jQuery/jquery-1.11.3.min.js"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/jslib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    <!-- bootstrap-notify -->
    <script src="${pageContext.request.contextPath}/jslib/bootstrap-notify/bootstrap-notify.min.js"></script>

</head>
<body>
	<!-- 整个页面外层container -->
	<div class="container">
		<!-- 公用的导航栏 -->
		<jsp:include page="./CommonHead.jsp"></jsp:include>
		<p class="lead"><span class="glyphicon glyphicon-list"></span>查看人员信息</p>

        <div style = "text-align:right;">
            <button class="btn btn-info btn-sm joinExamBtn" onclick="window.location.href='StaffAdd.jsp'">添加人员</button>
        </div>
	    <div id="dtGridContainer" class="dt-grid-container" style="overflow:hidden"></div>
        <div id="dtGridToolBarContainer" class="dt-grid-toolbar-container"></div>
	</div>
	<!--//删除模块  -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">
            </button>
            <h4 class="modal-title" id="myModalLabel">
              确认要删除以下人员信息？
            </h4>
         </div>
         <div class="modal-body">
  删除该用户以后，数据将会从数据库中删除
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">
             取消
            </button>
            <button type="button" class="btn btn-default" id="deleteSubmit" data-dismiss="modal">
               确认
            </button>
         </div>
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	<!-- 提示信息-->
	<div class="modal fade" id="prompt">
	  <div class="modal-dialog modal-sm">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">信息提示</h4>
	      </div>
	      <div class="modal-body">
	        <p class="warningText"><span>不能查看，请用管理员账号登录</span></p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
	      </div>
	    </div>
	  </div>
	</div>
	
    <script type="text/javascript">
       $("#deleteSubmit").click(function(){
         deleteAdminuser();
       });
    </script>

</body>
</html>