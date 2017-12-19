$(function(){
	$("#account-input").val(account);
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
					url: ctx + '/StaffAction!editPassword.action',
					data:{
                        staffId:staffIdWithStaffEditPass,
						password:password
					},
					async:false,
					success: function(json){
						var dataObj = eval("(" + json + ")"),
						msg = dataObj["msg"],
						successFlag = dataObj["success"];
						$.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
						if(successFlag)
						{
							window.location.href="/admin/StaffManage.jsp";
						}
					},
					error: function(){}
				});
	});
	$("#cancel").click(function(){
		window.location.href="/admin/StaffManage.jsp";
	});
});