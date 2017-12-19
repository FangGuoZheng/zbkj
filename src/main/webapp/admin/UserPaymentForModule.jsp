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
	<title>用户缴费</title>

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

	<!-- Autocomplete -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-ui-1.10.4/themes/base/jquery.ui.all.css">
	<script src="${pageContext.request.contextPath}/jslib/jquery-ui-1.10.4/jquery-1.10.2.js"></script>
	<script src="${pageContext.request.contextPath}/jslib/jquery-ui-1.10.4/ui/jquery.ui.core.js"></script>
	<script src="${pageContext.request.contextPath}/jslib/jquery-ui-1.10.4/ui/jquery.ui.widget.js"></script>
	<script src="${pageContext.request.contextPath}/jslib/jquery-ui-1.10.4/ui/jquery.ui.position.js"></script>
	<script src="${pageContext.request.contextPath}/jslib/jquery-ui-1.10.4/ui/jquery.ui.menu.js"></script>
	<script src="${pageContext.request.contextPath}/jslib/jquery-ui-1.10.4/ui/jquery.ui.autocomplete.js"></script>

    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/jslib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    <!-- bootstrap-notify -->
    <script src="${pageContext.request.contextPath}/jslib/bootstrap-notify/bootstrap-notify.min.js"></script>
    <!-- 必须的公共JS -->
    <script src="${pageContext.request.contextPath}/admin/js/Common.js"></script>
    <!-- 自定义JS -->
    <script src="${pageContext.request.contextPath}/admin/js/UserPaymentForModule.js"></script>

	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<%--<link rel="stylesheet" href="/resources/demos/style.css">--%>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	<script>
        $( function() {
            $( "#accessTimeTwo" ).datepicker({
                dateFormat: "yy-mm-dd",
                minDate:"%y-%M-{%d+1}"
            });
        } );
	</script>

	<script>
        $(function() {
            $("#userName").autocomplete({
                source:users,
                select: function(event,ui) {
                    $("#userName").val(ui.item.label);
                    $("#userId").val(ui.item.value);
                    $("#realName").val(ui.item.name);
                    return false;
                },
            });
        });
	</script>

	<script>
		$(function () {
            $.ajax({
                type: "POST",
                url: ctx + '/DictionaryAction!getAll.action',
                async:false,
                success: function(json)
                {
                    var dataObj = eval("(" + json + ")"),
                        msg = dataObj["msg"],
                        successFlag = dataObj["success"];

                    if (successFlag) {
                        $("#typeId").append("<option value=''></option>");

                        var rows = dataObj["obj"];

                        for (var i = 0; i < rows.length; i++) {
                            // 当前行所有数据
                            var row = rows[i];
                            var typeId = row["id"];
                            var typeName = row["name"];
                            $("#typeId").append("<option value='"+typeId+"'>"+typeName+"</option>");
                        }

                    }
                },
                error: function(){
                }
            });
        });
	</script>

</head>
<body>
	<!-- 整个页面外层container -->
	<div class="container">
		<!-- 公用的导航栏 -->
		<jsp:include page="/admin/CommonHead.jsp"></jsp:include>
		<p class="lead"><span class="glyphicon glyphicon-list"></span>用户缴费</p>
	    <form class="form-horizontal">
	    <div style="padding-top:100px;">

			<div class="form-group">
				<div class="col-sm-3"></div>
				<label for="userName" class="col-sm-1 control-label codeSubmitLabel">手机号码:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="userName" aria-describedby="basic-addon2" placeholder="填写用户手机号" />
				</div>
			</div>

			<input type="hidden" id="userId" value=""/>

			<div class="form-group">
				<div class="col-sm-3"></div>
				<label for="realName" class="col-sm-1 control-label codeSubmitLabel">用户姓名:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="realName" aria-describedby="basic-addon2" disabled="disabled" />
				</div>
			</div>

	       <div class="form-group">
	          <div class="col-sm-3"></div>
	          <label for="typeId" class="col-sm-1 control-label codeSubmitLabel">模块类别:</label>
			  <div class="col-sm-4">
				  <select name="typeId" class="form-control" id="typeId"  >
				  </select>
			   </div>
		   </div>

			<div class="form-group">
				<div class="col-sm-3"></div>
				<label for="moduleId" class="col-sm-1 control-label codeSubmitLabel">模块名称:</label>
				<div class="col-sm-4">
					<select name="moduleId" class="form-control" id="moduleId"  >
					</select>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-3"></div>
				<label for="accessTimeValue" class="col-sm-1 control-label codeSubmitLabel"><input type="radio" name="time" value="time1" checked="checked" >延长时长:</label>
				<div class="col-sm-4">
					<input style="width: 80px; height: 33px" type="number" id="accessTimeValue" /> &nbsp;
					<select style="width: 50px; height: 33px" id="accessTimeUnit"  >
						<option value=""></option>
						<option value="1">年</option>
						<option value="2">月</option>
						<option value="3">日</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-3"></div>
				<label for="accessTimeTwo" class="col-sm-1 control-label codeSubmitLabel"><input type="radio" name="time" value="time2" >延长到:</label>
				<div class="col-sm-4">
					<input style="height: 33px" type="text" id="accessTimeTwo" disabled="disabled" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-3"></div>
				<label for="amount" class="col-sm-1 control-label codeSubmitLabel">总金额:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="amount" aria-describedby="basic-addon2" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-3"></div>
				<label for="description" class="col-sm-1 control-label codeSubmitLabel">描述:</label>
				<div class="col-sm-4">
					<textarea id="description" class="form-control" aria-describedby="basic-addon2" rows="3" cols="30"></textarea>
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