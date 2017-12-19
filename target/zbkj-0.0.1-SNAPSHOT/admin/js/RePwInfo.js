$(function(){
	var email ='';
	var rightAnswer='';
	$("#username-reset-input").change(function(){
			var account = $("#username-reset-input").val();
				$.ajax({
					type : "POST",
					url : ctx + '/StaffAction!getByAccount.action',
					data : {
						account : account
					},
					async : false,
					success : showEmail,
					error : function() {
					}
				});
		});
	
	function showEmail(json)  
	{
		var dataObj = eval("(" + json + ")"),
		msg = dataObj["msg"],
		successFlag = dataObj["success"];
	    
		if(successFlag)
		{
			var row = dataObj["obj"];
			var question = row["question"]; 
			rightAnswer = row["answer"];
			if(typeof(question) != 'undefined'){
				$("#question").val(question);
				$("#ray").text(" "); 
			}else{
				$("#question").val("");
				$("#ray").text("无问题，请联系管理员11249242@qq.com");
			}
			/*$("#ray").text(" ");
			var row = dataObj["obj"];
			email = row["email"]; 
			if(typeof(email)!="undefined"){
				if(email!=''){
					var hideemail = email.split("@")[1];
					hideemail="@"+hideemail;
					var emailPre=email.split("@")[0];
					var ePre=emailPre.substring(0,2);
					var eAfter=emailPre.substring(emailPre.length-2,emailPre.length);
					var hemail = ePre + "***" + eAfter + hideemail;
					//document.getElementById("email-reset-input").setAttribute("placeholder",hemail+"请输入完整邮箱");
					$("#ray").text("邮箱地址提示:"+hemail);
				}else{
					$("#ray").text("无邮箱地址，请至教师处重置密码");
				}
			}else{
				$("#ray").text("无邮箱地址，请至教师处重置密码");
			}*/
		}else{
			$("#ray").text("该用户名不存在");
		}
	}
	

	/*$.ajax(
			{
				type: "POST",
				url: ctx + '/questionsAction!findAllQuestions.action',
				async:false,
				success: function(json)
				{
					var dataObj = eval("(" + json + ")"),
					msg = dataObj["msg"],
					successFlag = dataObj["success"];
			        
					if (successFlag) {
						var rows = dataObj["obj"];
						
						for (var i = 0; i < rows.length; i++) {
							// 当前行所有数据
							var row = rows[i];
							var questionsclass= new Object();
							questionsclass.id = row["id"];
							questionsclass.name = row["question"];
				            $("#questionsSelect").append("<option value=\""+questionsclass.name+"\">"+questionsclass.name+"</option>");

						}
						//$("#questionsSelect").find("option[value="+questionName+"]").attr("selected",true);
					} 
				},   
				error: function(){
			}
			});*/
	

	$("#resetPasswordBtn").click(function(){
		var account = $("#username-reset-input").val();
		var question = $("#question").val();
		/*if($("#email-reset-input").val()!=email || $("#email-reset-input").val()==""){
			$("#ray").text("请输入正确的邮箱");
		}*/
		var answer = $("#answer").val();
		if(question==""){
			$("#ray").text("无问题，请联系管理员11249242@qq.com");
		}else if(answer != rightAnswer){
			$("#ray").text("答案错误");
		}else{
			$("#ray").text(" "); 
			// window.location.href = "resetPassword.jsp?account="+account;

			// 向后台发送ajax请求,发送重置密码邮件
			$.ajax(
			{
				type: "POST",
				url: ctx + '/StaffAction!resetTPW.action',  // 重置密码
				data: {
					"account":account
				},
				success: function(json)
				{
					var dataObj = eval("(" + json + ")"),
					msg = dataObj["msg"],
					successFlag = dataObj["success"];
					if(successFlag)
					{
						var obj=dataObj["obj"];
						email=obj["email"];
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