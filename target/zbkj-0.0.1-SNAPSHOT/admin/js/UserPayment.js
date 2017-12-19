var users =new Array();
$(function(){
    $.ajax({
        type: "POST",
        url: ctx + '/UserAction!getALLBySaleId.action',
        data: {
            saleId:saleId,
            roleId:roleId
        },
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
                    var nameclass= new Object();
                    nameclass.value = row["id"];
                    nameclass.label = row["mobile"];
                    nameclass.name = row["name"];
                    users.push(nameclass);
                }

            }
        },
        error: function(){
        }
    });

    // $("#accessTimeUnit").change(function () {
    //     var accessTimeValue=$("#accessTimeValue").val();
    //     var accessTimeUnit=$("#accessTimeUnit").val();
    //     $("#amount").val(accessTimeValue*accessTimeUnit);
    // });

    // $("#accessTimeTwo").change(function () {
    //     var currentDate = $( "#accessTimeTwo" ).datepicker( "getDate" );
    //     $("#amount").val(49);
    // });

    $("input[name=time]").change(function () {
        var timeValue=$('input[name=time]:checked').val();
        if(timeValue == "time1"){
            $('#accessTimeValue').removeAttr("disabled");
            $('#accessTimeUnit').removeAttr("disabled");
            $('#accessTimeTwo').attr("disabled","disabled");
        } else if(timeValue == "time2"){
            $('#accessTimeValue').attr("disabled","disabled");
            $('#accessTimeUnit').attr("disabled","disabled");
            $('#accessTimeTwo').removeAttr("disabled");
        }
    });

	$("#confirm").click(function(){
		var userName=$("#userName").val();
        var realName=$("#realName").val();
		var accessTimeValue=$("#accessTimeValue").val();
		var accessTimeTwo=$("#accessTimeTwo").val();
		var amount=$("#amount").val();
        if(userName=='') {
            $.notify({message: "手机号码不能为空" },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
        }
        if(accessTimeValue=='' && accessTimeTwo=='') {
            $.notify({message: "延长时长值和延长日期不能同时为空" },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
        }
        if(amount=='') {
            $.notify({message: "总金额不能为空" },{placement: {from: "bottom",align: "right"},type: 'danger',delay: 2000});
            return;
        }

        $('#mobileInput').text("你确定给"+realName+"用户充值"+amount+"元吗？");
        $('#promptModal').modal('show');
	});

    $("#confirmModelLoginBtn").click(function(){
        $('#promptModal').modal('hide');

        var userName=$("#userName").val();
        var accessTimeValue=$("#accessTimeValue").val();
        var accessTimeUnit=$("#accessTimeUnit").val();
        var accessTimeTwo=$("#accessTimeTwo").val();
        var amount=$("#amount").val();
        var description=$("#description").val();

        $.ajax({
            type: "POST",
            url: ctx + '/UserModuleOrderAction!addWhenModuleIdIsNull.action',
            data:{
                mobile:userName,
                opeUserId:saleId,
                amount:amount,
                duration:accessTimeValue,
                unit:accessTimeUnit,
                time:accessTimeTwo,
                description:description
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
        window.location.href="/admin/UserManage.jsp";
    });
});