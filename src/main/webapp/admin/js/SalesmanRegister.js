$(function(){
	$("#confirm").click(function(){
		var account=$("#account-input").val();
		if(account=='')
		{
			$.notify({message: "账号不能为空" },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
			return;
		}
		var password=$("#password-input").val();
		if(password=='')
		{
			$.notify({message: "密码不能为空" },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
			return;
		}
		var confirmPassword=$("#confirmPassword-input").val();
		if(confirmPassword=='')
		{
			$.notify({message: "确认密码不能为空" },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
			return;
		}
		if(password!=confirmPassword)
		{
			$.notify({message: "密码和确认密码不一致，请重新输入" },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
			return;
		}
		var name=$("#name-input").val();
		if(name=='')
		{
			$.notify({message: "姓名不能为空" },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
			return;
		}
		var roleId=$("#roleId").val();
		if(roleId == null || roleId==''){
            $.notify({message: "角色不能为空" },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
		}
		var phone=$("#phone-input").val();
		var email=$("#email-input").val();
		var question=$("#question-input").val();
		var answer=$("#answer-input").val();
		if (question!=null && answer==null){
            $.notify({message: "你已经填写密码提示问题，答案不能为空。" },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
		}

		$.ajax({
			type: "POST",
			url: ctx + '/StaffAction!add.action',
			data:{
				account:account,
				password:password,
				name:name,
				roleId:roleId,
				phone:phone,
				email:email,
                question:question,
                answer:answer
			},
			async:false,
			success: function(json){
			   var dataObj = eval("(" + json + ")"),
			   msg = dataObj["msg"],
			   successFlag = dataObj["success"];
			   $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
			   if(successFlag)
			   {
                   window.location.href="/admin/index.jsp";
			   }
		     },    
			error: function(){}
	     });
	});

    $("#cancel").click(function(){
        window.location.href="/admin/index.jsp";
	});
});