$(function(){
	$("#account").val(account);
	$('#sure').click(function(){
		window.location.href = "/admin/index.jsp";
	});
	// 点击确认按钮
	$('#confirm').click(function(){
		   // var uuid = $("#uuid").val();
			var password=$("#password-input").val();
			var confirmPassword=$("#confirmPassword-input").val();
			
			if(password.length<6)
			{
				var msg="密码的长度不能少于6位";
				$("#ray").text(msg);
				return;
			}
			if(confirmPassword!=password)
			{
				var msg="密码和重复密码不一致，请重新输入";
				$("#ray").text(msg);
				return;
			}
			
		$.ajax({
			type: "POST",
			url: ctx + '/StaffAction!editPassword.action',
			data:{
                staffId:staffId,
				password:password
			},
			async:false,
			success: function(json){
			   var dataObj = eval("(" + json + ")"),
			   msg = dataObj["msg"],
			   successFlag = dataObj["success"];
			   if(successFlag)
			   {
				   $("#ray").text(" ");
				   $('#remind').modal("toggle"); 
						  
			   }else{
				   $("#ray").text("重置密码失败");
			   }
		     },    //获取老师的结果
			error: function(){}
	     });
	});
});