var datas = new Array();
var nowId=0;
$(function(){
    if(roleId == 1 || roleId == 3){
        // 初始化页面上的提示框
        $('[data-toggle="tooltip"]').tooltip();
        $.ajax(
            {
                type: "POST",
                url: ctx + '/UserModuleOrderAction!get.action',
                data:{
                    staffId:staffId
                },
                async:false,
                success: showAdminResult,
                error: function(){
                }
            });
    }else{
        $('#prompt').modal("toggle");
    }
});
function showAdminResult(json)
{
    var dataObj = eval("(" + json + ")"),
        msg = dataObj["msg"],
        successFlag = dataObj["success"];

    if(successFlag)
    {
        var rows = dataObj["obj"];
        for(var i = 0 ;i <rows.length; i++)
        {
            // 当前行所有数据
            var row = rows[i];
            //从此修改
            var userModuleOrder=new Object();

            userModuleOrder.id=row["id"];
            userModuleOrder.userName=row["userName"];
            userModuleOrder.moduleName=row["moduleName"];
            userModuleOrder.opeUserName=row["opeUserName"];
            userModuleOrder.amount=row["amount"];
            userModuleOrder.expiredate=row["expiredate"];
            userModuleOrder.description=row["description"];

            datas.push(userModuleOrder);
        }

    }
    else
    {
        $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
    }
}

function filter()
{
    if(roleId == 1 || roleId == 3){
        // 初始化页面上的提示框
        $('[data-toggle="tooltip"]').tooltip();

        datas.length=0;
        grid.refresh(true);
        var userName = $("#userName_search").val();
        var moduleName = $("#moduleName_search").val();
        var opeUserId = $("#opeUserId_search").val();

        $.ajax(
            {
                type: "POST",
                url: ctx + '/UserModuleOrderAction!get.action',
                data:{
                    staffId:staffId,
                    roleId:roleId,
                    userName:userName,
                    moduleName:moduleName,
                    opeUserId:opeUserId
                },
                async:false,
                success: showAdminResult,
                error: function(){
                }
            });
        grid.load();
    }else{
        $('#prompt').modal("toggle");
    }
}

function deleteAdminuser()
{
    $.ajax(
        {
            type: "POST",
            url: ctx + '/UserAction!delete.action',
            data:{
                id:nowId
            },
            async:false,
            success: function(json){
                var dataObj = eval("(" + json + ")"),
                    msg = dataObj["msg"],
                    successFlag = dataObj["success"];
                $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
                if(successFlag)
                {
                    for (var i=0,n= 0;i<datas.length;i++)
                    {
                        if (datas[i].id!=nowId)
                        {
                            datas[n ++ ]=datas[i];
                        }
                    }
                    datas.length=datas.length-1;
                    grid.refresh(true);
                    grid= $.fn.DtGrid.init(dtGridOption);
                    grid.load();
                }

            },
            error: function(){
            }
        });
}
var dtGridColumns = [
    {id:'userName', title:'用户名', type:'string', columnClass:'text-center'},
    {id:'moduleName', title:'模块名', type:'string', columnClass:'text-center'},
    {id:'opeUserName', title:'操作订单人', type:'string', columnClass:'text-center'},
    {id:'amount', title:'交易额', type:'float', columnClass:'text-center'},
    {id:'expiredate', title:'用户访问模块期限', type:'string', columnClass:'text-center'},
    {id:'description', title:'描述', type:'string', columnClass:'text-center'}
];
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : false,
    datas : datas,
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : 50,
    pageSizeLimit : [50, 100,150]
};
var grid= $.fn.DtGrid.init(dtGridOption);
$(function(){
    grid.load();
});