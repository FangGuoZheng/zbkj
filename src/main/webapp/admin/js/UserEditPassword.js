$(function(){
	$("#mobile-input").val(mobile);
	$("#confirm").click(function(){
		var password=$("#newPassword-input").val();
		var confirmPassword=$("#confirmPassword-input").val();
		if(password!=confirmPassword)
		{
			var msg="新密码与确认密码不一致，请核对后再输入";
			$.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
			return;
		}
		if(password.length<6)  //密码长度必须大于等于6位
		{
			var msg="密码长度必须大于等于6位，请重新输入";
			$.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
			return;
		}
		$.ajax(
				{
					type: "POST",
					url: ctx + '/UserAction!update.action',
					data:{
						id:userIdWithUserEditPass,
						password:password
					},
					async:false,
					success: function(json){
						var dataObj = eval("(" + json + ")"),
						msg = dataObj["msg"],
						successFlag = dataObj["success"];
						if(successFlag)
						{
                            $.notify({message: "重设密码成功" },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
							window.location.href="/admin/UserManage.jsp";
						}
					},
					error: function(){
                        $.notify({message: "重设密码失败" },{placement: {from: "bottom",align: "right"},type: 'failure',delay: 2000});
					}
				});
	});
	$("#cancel").click(function(){
		window.location.href="/admin/UserManage.jsp";
	});
});