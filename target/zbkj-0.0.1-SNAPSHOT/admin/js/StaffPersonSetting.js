var oldQuestion = "";
function closeAlert()
{
	$('button.close').click();
}
$(function(){
	var account=$("#loginNameInput").val();
	$.ajax(
			{
				type: "POST",
				url: ctx + '/StaffAction!getByAccount.action',
				data:{
					account:account
				},
				async:false,
				success: showAdminResult,    //获取老师的结果
				error: function(){}
			});
	
		function showAdminResult(json)  //获取教师列表信息
		{
			var dataObj = eval("(" + json + ")"),
			msg = dataObj["msg"],
			successFlag = dataObj["success"];
		    
			if(successFlag)
			{
				$.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
				var row = dataObj["obj"];

                $("#account").val(row["loginName"]);
                $("#name").val(row["name"]);
                $("#roleId").val(row["roleId"]);
                $("#phone").val(row["phone"]);
				$("#email").val(row["email"]);

			}
			else
			{
				$.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
			}
		}
	// 用户名输入框的失去焦点事件
	// TODO 需要判断用户名是否合理等问题
	$('#loginNameInput').blur(function(){
		
	});
	
	// 邮箱输入框回车事件绑定
	$('#email').bind('keyup', function(event) {
		if (event.keyCode == "13") {
			// 回车执行
	        $('#savePersonInfoBtn').click();
		}
	});
	
	// 原密码输入框的失去焦点事件
	// TODO 需要判断原来的密码是否正确等问题
	$('#currentPassword').blur(function(){
		
	});
	
	// 新密码输入框的失去焦点事件
	// TODO 需要判断原密码和新密码是否相等问题
	$('#newPassword').blur(function(){
		
	});
	
	// 确认密码输入框的失去焦点事件
	// TODO 需要判断新密码和确认密码是否相等问题
	$('#repeatNewPassword').blur(function(){
		
	});
	
	// 确认密码输入框回车事件绑定
	$('#repeatNewPassword').bind('keyup', function(event) {

	});


	// 点击修改信息的保存按钮
	$('#savePersonInfoBtn').click(function(){
		var staffId=$("#staffId").val();
		var account=$("#loginNameInput").val();
		var name=$("#name").val();
		var roleId=$("#roleId").val();
		var phone=$("#phone").val();
		var email=$("#email").val()
		if(account ==''){
			var msg="用户名不能为空";
			$.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
			return;
		}
        if(name=='')
        {
            var msg="姓名不能为空";
            $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
        }
        if(phone=='')
        {
            var msg="电话号码不能为空";
            $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
        }
		if(email ==''){
			var msg="邮件不能为空";
			$.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
			return;
		}
		// 向后台action发送请求，保存修改后的用户信息
		$.ajax({
			type: "POST",
			url: ctx + '/StaffAction!edit.action',   //修改用户信息
			data:{
				staffId:staffId,
				account:account,
				name:name,
				roleId:roleId,
				phone:phone,
				email:email
			},
			async:false,
			success: function(json){
			   var dataObj = eval("(" + json + ")"),
			   msg = dataObj["msg"],
			   successFlag = dataObj["success"];
			   $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
			   if(successFlag) {

			   }
		     },    //获取老师的结果
			error: function(){}
	     });
	});
	
	// 点击修改密码的保存按钮
	$('#savePasswordBtn').click(function(){
        var staffId=$("#staffId").val();
		// 获取三个密码输入框中的值
		var currentPassword = $.trim($('#currentPassword').val()),
			newPassword = $.trim($('#newPassword').val()),
			repeatNewPassword = $.trim($('#repeatNewPassword').val());
		
		if(currentPassword.length<6)
		{
			var msg="密码的长度不能少于6位";
			$.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
			return;
		}
		if(newPassword.length<6)
		{
			var msg="密码的长度不能少于6位";
			$.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
			return;
		}
		if(repeatNewPassword!=newPassword)
		{
			var msg="新密码和确认密码不一致，请重新输入";
			$.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
			return;
		}
		var flag=true;
		$.ajax({
			type: "POST",
			url: ctx + '/StaffAction!verifyPassword.action',
			data:{
                staffId:staffId,
                password:currentPassword
			},
            async:false,
            success: function(json){
                var dataObj = eval("(" + json + ")"),
                    msg = dataObj["msg"],
                    successFlag = dataObj["success"];
                flag = successFlag;
                if(!successFlag) {
                    $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
                }
            },
            error: function(){}
		});

		if(!flag){
			return;
		}

		// 向后台action发送请求，保存修改后的用户密码
		$.ajax({
			type: "POST",
			url: ctx + '/StaffAction!editPassword.action',   //修改个人密码
			data:{
                staffId:staffId,
				password:newPassword
			},
			async:false,
			success: function(json){
			   var dataObj = eval("(" + json + ")"),
			   msg = dataObj["msg"],
			   successFlag = dataObj["success"];
			   $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
			   if(successFlag)
			   {

			   }
		     },
			error: function(){}
	     });
	});
});
