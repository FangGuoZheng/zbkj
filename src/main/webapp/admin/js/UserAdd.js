$(function(){
	$("#confirm").click(function(){
		var areacode=$("#areacode-input").val();
		var mobile=$("#mobile-input").val();
        if(areacode=='') {
            $.notify({message: "手机国家地区代码不能为空" },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
        }
		if(mobile=='') {
			$.notify({message: "手机号码不能为空" },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
			return;
		}

		if(mobile!='') {
            $.ajax({
                type: "POST",
                url: ctx + '/UserAction!getByMobile.action',
                data:{
                    mobile:mobile
                },
                async:false,
                success: function(json){
                    var dataObj = eval("(" + json + ")"),
                        msg = dataObj["msg"],
                        successFlag = dataObj["success"];
                    if(successFlag) {
                        $('#mobileInput').text("用户已存在，请直接查看");
                        $('#promptModal').modal('show');
                    }else {
                        $('#mobileInput').text("你确定添加"+mobile+"用户吗？");
                        $('#promptModal').modal('show');
                    }
                },
                error: function(){
                    $.notify({message: "获取失败" },{placement: {from: "bottom",align: "right"},type: 'error',delay: 2000});
                }
            });
        }
	});

    $("#confirmModelLoginBtn").click(function(){
        $('#promptModal').modal('hide');

        var areacode=$("#areacode-input").val();
        var mobile=$("#mobile-input").val();
        if (roleId == 1){
            saleId=$("#salesmanName").val();
        }

        $.ajax({
            type: "POST",
            url: ctx + '/UserAction!add.action',
            data:{
                saleId:saleId,
                areacode:areacode,
                mobile:mobile
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
            },
            error: function(){
                $.notify({message: "获取失败" },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
            }
        });
    });

    $("#cancel").click(function(){
        window.location.href="/admin/UserManage.jsp";
	});

    $("#cancelModelLoginBtn").click(function(){
        $('#promptModal').modal('hide');
    });
});