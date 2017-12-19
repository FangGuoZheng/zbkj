<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="M4后台" content="business">
    <title>首页</title>
    <!-- Bootstrap -->
    <link href="${ctx}/jslib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- 自定义样式 -->
    <link href="${ctx}/admin/css/index.css" rel="stylesheet">
    <!-- html5shiv.js和Respond.js为IE8提供HTML5元素以及媒体查询的支持 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="${ctx}/jslib/jQuery/jquery-1.11.3.min.js"></script>
    <!-- Bootstrap -->
    <script src="${ctx}/jslib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

    <script src="${ctx}/jslib/bootstrap-notify/bootstrap-notify.min.js"></script>
    <!-- 必须的公共JS -->
    <script src="${ctx}/admin/js/Common.js"></script>
    <!-- 自定义JS -->
    <script type="text/javascript">
        function showRemind(){
            if(roleId==""){
                $('#remind').modal("toggle");
            }else if(roleId==1){
                window.location.href = "/admin/StaffManage.jsp";
            }else if (roleId==3){
                window.location.href = "/admin/UserManage.jsp";
            }
        }
    </script>
</head>
<body>
<!-- 整个页面外层container -->
<div class="container">

    <!-- 测试标题 -->
    <p class="lead" id="indexTitle">欢迎来到M4后台管理系统
    </p>
    <div id="index-content">
        <div class="index-left-div">
            <img class="shadow-img" alt="" src="${ctx}/image/index.jpg">
        </div>
        <div class="index-right-div">
            <p class="index-right-line2">点此查看常见问题解答</p>
            <p class="index-right-line3"><a href="#" onclick="showRemind()">点此</a>进入管理页面</p>
        </div>
    </div>
    <!-- 提示查看邮件 -->
    <div class="modal fade" id = "remind">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">信息提示</h4>
                </div>
                <div class="modal-body">
                    <p class="warningText">请您先登录才能进入管理页面.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="sure" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 公用的导航栏 -->
    <jsp:include page="CommonHead.jsp"></jsp:include>
</div>

</body>
</html>