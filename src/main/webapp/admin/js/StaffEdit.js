$(function(){
	$.ajax(
				{
					type: "POST",
					url: ctx + '/StaffAction!getById.action',
					data:{
                        staffId:staffIdWithStaffEdit
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

							var staffId=row["staffId"];
							var account=row["account"];
							var name=row["name"];
							var phone=row["phone"];
							var roleId=row["roleId"];
							var email=row["email"];
							var question=row["question"];
							var answer=row["answer"];

							$("#account-input").val(account);
							$("#name-input").val(name);
							$("#phone-input").val(phone);
							$("#role-input").val(roleId);
							$("#email-input").val(email);
							$("#question-input").val(question);
							$("#answer-input").val(answer);
						}
					},
					error: function(){}
	});

    $("#confirm").click(function(){
        var account=$("#account-input").val();
        var name=$("#name-input").val();
        var phone=$("#phone-input").val();
        var roleId=$("#role-input").val();
        var email=$("#email-input").val();
        var question=$("#question-input").val();
        var answer=$("#answer-input").val();
        if(account==null || account=='') {
            var msg="账号不能为空";
            $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
        }
        if(name==null || name=='') {
            var msg="用户的姓名不能为空";
            $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
        }
        if(roleId == null || roleId==''){
        	var msg="角色不能为空";
            $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
        }
        $.ajax({
            type: "POST",
            url: ctx + '/StaffAction!edit.action',
            data:{
                staffId:staffIdWithStaffEdit,
                account:account,
                name:name,
				phone:phone,
                roleId:roleId,
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
                    window.location.href="/admin/StaffManage.jsp";
                }
            },    //获取老师的结果
            error: function(){}
        });
    });

    $("#cancel").click(function () {
        window.location.href="/admin/StaffManage.jsp";
    });
});

