$(function(){
	$.ajax(
				{
					type: "POST",
					url: ctx + '/UserAction!getUserById.action',
					data:{
						id:userIdWithUserEdit
					},
					async:false,
					success: function(json){
						var dataObj = eval("(" + json + ")"),
						msg = dataObj["msg"],
						successFlag = dataObj["success"];
						$.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
						if(successFlag)
						{
							var row = dataObj["obj"];

							var id=row["id"];
							var name=row["name"];
							var nickname=row["nickname"];
							var mobile=row["mobile"];
							var email=row["email"];
							var gender=row["gender"];
							var location=row["location"];

							$("#name-input").val(name);
							$("#nickname-input").val(nickname);
							$("#mobile-input").val(mobile);
							$("#email-input").val(email);
							$("#gender-input").val(gender);
							$("#location-input").val(location);
						}
					},
					error: function(){}
	});

    $("#confirm").click(function(){
        var name=$("#name-input").val();
        var nickname=$("#nickname-input").val();
        var mobile=$("#mobile-input").val();
        var email=$("#email-input").val();
        var gender=$("#gender-input").val();
        var location=$("#location-input").val();
        if(mobile==null || mobile=='') {
            var msg="手机号不能为空";
            $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
        }
        if(name==null || name=='') {
            var msg="用户的姓名不能为空";
            $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
        }
        if(email == null || email==''){
        	var msg="Email不能为空";
            $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
        }
        $.ajax({
            type: "POST",
            url: ctx + '/UserAction!update.action',
            data:{
                id:userIdWithUserEdit,
                name:name,
                nickname:nickname,
                mobile:mobile,
                email:email,
                gender:gender,
                location:location
            },
            async:false,
            success: function(json){
                var dataObj = eval("(" + json + ")"),
                    msg = dataObj["msg"],
                    successFlag = dataObj["success"];
                $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
                if(successFlag)
                {
                    window.location.href="/admin/UserManage.jsp";
                }
            },    //获取老师的结果
            error: function(){}
        });
    });

    $("#cancel").click(function () {
        window.location.href="/admin/UserManage.jsp";
    });
});

