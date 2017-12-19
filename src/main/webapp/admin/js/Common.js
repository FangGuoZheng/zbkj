$(function(){
	var email ='';
	// 如果点击首页
	
	// 如果点击考试
	
	$('ul.navbar-nav li').click(function(){
		$(this).addClass('active');
	});
	
	$('#sign').click(function(){
		window.location.href = "/admin/SalesmanRegister.jsp";
	});
	// 点击登录按钮
	$('#loginModelLoginBtn').click(function(){
		
		// 获取用户输入的用户名和密码
		var username = $('#username').val(),
			password = $('#password').val();
		
		// 如果用户名为空
		if(username == '')
		{
			// 给用户名输入框添加红色的警示色
			$('#usernameInputGroup').addClass('has-error');
			return;
		}
		if(password == '')
		{
			// 给密码输入框添加红色的警示色
			$('#passwordInputGroup').addClass('has-error');
			return;
		}
		
		// 向后台发送ajax请求，验证用户名和密码是否匹配
		$.ajax(
		{
			type: "POST",
			url: ctx + '/StaffAction!login.action',
			data: {
				"account":username,
				"password":password
			},
			success: function(json)
			{
				var dataObj = eval("(" + json + ")"),
				msg = dataObj["msg"],
				successFlag = dataObj["success"];
                var obj = dataObj["obj"];
				if(successFlag) {
                    if (obj["roleId"] == 6){
                        // 给用户名输入框添加红色的警示色
                        $('#usernameInputGroup').addClass('has-error');

                        // 给密码输入框添加红色的警示色
                        $('#passwordInputGroup').addClass('has-error');

                        $("#ray").text("当前为管理员的登录界面，请核对一下账号！");
                    } else{
                        chineseName = obj["name"];
                        var loginName=obj["loginName"];
                        var loginPassword=obj["loginPassword"];
                        var roleName=obj["roleName"];
                        location.reload(true);
                        setCookie("loginName", loginName, 1);   //将用户数据存入cookie中，，默认cookie有效时间为1天
                        setCookie("loginPassword", loginPassword, 1);
                        setCookie("roleName",  roleName, 1);
                        setCookie("chineseName",  chineseName, 1);
                        // 隐藏登录弹出框
                        $('#loginModal').modal('hide');
                        var msg="登录成功！";
                        $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
                        // 刷新页面
                        location.reload(true);
					}
				} else {
					// TODO 可以考虑增加左右晃动的动画来提示用户
					// 给用户名输入框添加红色的警示色
					$('#usernameInputGroup').addClass('has-error');

					// 给密码输入框添加红色的警示色
					$('#passwordInputGroup').addClass('has-error');

					$("#ray").text("用户名或密码错误！");
				}
			},
			error: function()
			{
			}
		});
	});
	
	// 用户名输入框的失去焦点事件
	$('#username').blur(function(){
		// 获取用户输入的用户名
		var username = $('#username').val();
		if(username == '')
		{
			// 给用户名输入框添加红色的警示色
			$('#usernameInputGroup').addClass('has-error'); 
		}
		else
		{
			// 给用户名输入框删除红色的警示色
			$('#usernameInputGroup').removeClass('has-error');
		}
	});
	
	// 密码输入框的失去焦点事件
	$('#password').blur(function(){
		// 获取用户输入的用户名
		var password = $('#password').val();
		if(password == '')
		{
			// 给密码输入框添加红色的警示色
			$('#passwordInputGroup').addClass('has-error');
		}
		else
		{
			// 给密码输入框删除红色的警示色
			$('#passwordInputGroup').removeClass('has-error');
		}
	});
	
	// 密码输入框回车事件绑定
	$('#password').bind('keyup', function(event) {
		if (event.keyCode == "13") {
			// 回车执行
	        $('#loginModelLoginBtn').click();
		}
	});
	// 点击安全退出按钮
	$('#safeLogOff').click(function(){
		// 向后台发送请求，清除登录信息
		$.ajax(
		{
			type: "POST",
			url: ctx + '/StaffAction!logout.action',
			cache:false,
			success: function(json)
			{
//				 console.log(json);
				// 解析后台传回的json数据为js对象
				var dataObj = eval("(" + json + ")"),
				successFlag = dataObj["success"];
				if(successFlag)
				{
					deleteCookie("loginName");   //退出后将cookie删除
					deleteCookie("loginPassword");
					deleteCookie("roleName");
					deleteCookie("chineseName");
                    if(roleId==1 || roleId==3){
                        window.location.href = "/admin/index.jsp";
                    }
				}
			},
			error: function()
			{
			}
		});
	});
	//清除所有缓存
	$("#removeAllCache").click(function(){
		$.ajax(
				{
					type: "POST",
					url: ctx + '/cacheAction!removeAllCache.action',
					success: function(json)
					{
						var dataObj = eval("(" + json + ")"),
						msg = dataObj["msg"],
						successFlag = dataObj["success"];
						if(successFlag)
						{	
							$.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
						}
						else
						{
							$.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
						}
					},
					error: function()
					{
					}
				});
	});
	function setCookie(name, value, expireday) {   //设置cookie
        var exp = new Date();
		exp.setTime(exp.getTime() + expireday*24*60*60*1000); //设置cookie的期限
		document.cookie = name+"="+escape(value)+";expires"+"="+exp.toGMTString();
		}
	//创建cookie
	function getCookie(name) {    //获取cookie
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
		else
		return null;
    }
    function  deleteCookie(name)
    {
    	var exp = new Date();
    	exp.setTime(exp.getTime() - 1);
    	var cval=getCookie(name);
    	if(cval!=null)
    	document.cookie= name + "="+cval+";expires="+exp.toGMTString();
    }
    function getWebSocketPath()
    {
    	var url = window.location.href;
    	var path=null;
    	var str=url;
    	if(str.indexOf("http://") == 0 )
    	{
    	    str=str.substring(7); 
    	    var temp=str.split("/");
    	    path=temp[0];

    	}
    	else
    	{
    		var temp=str.split("/");
    	    path=temp[0];
    	}
    	return path;
    }
    $("#username-reset-input").change(function(){
		var account = $("#username-reset-input").val();
			$.ajax({
				type : "POST",
				url : ctx + '/adminusersAction!getAdminuserByAccount.action',
				data : {
					account : account
				},
				async : false,
				success : showEmail, //
				error : function() {
				}
			});
	});
	
	function showEmail(json)  //显示课程筛选条件
	{
		var dataObj = eval("(" + json + ")"),
		msg = dataObj["msg"],
		successFlag = dataObj["success"];
        
		if(successFlag)
		{
			$("#newray").text(" ");
			var row = dataObj["obj"];
			email = row["email"]; 
			var hideemail = email.split("@")[1];
			hideemail="@"+hideemail;
			var emailPre=email.split("@")[0];
			var ePre=emailPre.substring(0,2);
			var eAfter=emailPre.substring(emailPre.length-2,emailPre.length);
			var hemail = ePre + "***" + eAfter + hideemail;
			//document.getElementById("email-reset-input").setAttribute("placeholder",hemail+"请输入完整邮箱");
			$("#newray").text("邮箱地址提示:"+hemail);
			//$("#email").val(row["email"]);
		}else{
			$("#newray").text("该用户名不存在");
		}
	}
	
	$("#resetPasswordBtn").click(function(){
		var account = $("#username-reset-input").val();
		if($("#email-reset-input").val()!=email || $("#email-reset-input").val()==""){
			$("#newray").text("请输入正确的邮箱");
		}else{
			$("#newray").text(" ");
			$('#forgetPassword').modal("toggle"); 
			// 向后台发送ajax请求,发送重置密码邮件
			$.ajax(
			{
				type: "POST",
				url: ctx + '/adminusersAction!resetTPW.action',//重置教师登陆密码
				data: {
					"account":account,
					"email":email
				},
				success: function(json)
				{
					var dataObj = eval("(" + json + ")"),
					msg = dataObj["msg"],
					successFlag = dataObj["success"];
					if(successFlag)
					{
						var reinfo="重置密码的邮件已发送到"+email+"邮箱，请点击邮件中链接进行重置";
						$("#resetInfo").text(reinfo);
						$('#resetPassword').modal("toggle");
					}
					else
					{
						$("#resetInfo").text("重置密码的邮件发送失败，请重新操作或者给管理员发送邮件11249242@qq.com");
						$('#resetPassword').modal("toggle"); 
					}
				},
				error: function()
				{
				}
			});
		}
	});
});