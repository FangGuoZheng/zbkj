var datas = new Array();
var nowId=0;
$(function(){
    if(roleId == 1 || roleId == 3){
        // 初始化页面上的提示框
        $('[data-toggle="tooltip"]').tooltip();
        $.ajax(
            {
                type: "POST",
                url: ctx + '/UserAction!getALLBySaleId.action',
                data:{
                    saleId:saleId,
                    roleId:roleId
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
            var user=new Object();

            user.id=row["id"];
            user.name=row["name"];
            user.nickname=row["nickname"];
            user.mobile=row["mobile"];
            user.email=row["email"];
            user.gender=row["gender"];
            user.birthdate=row["birthdate"];
            user.location=row["location"];

            datas.push(user);
        }

    }
    else
    {
        $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
    }
}
function editAccount(id)
{
    window.open("UserEdit.jsp?id="+id);
}
function editPassword(id,mobile)
{
    window.open("UserEditPassword.jsp?id="+id+"&mobile="+mobile);
}
function deletePreprocess(id)
{
    nowId=id;
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
    {id:'name', title:'名称', type:'string', columnClass:'text-center'},
    {id:'nickname', title:'昵称', type:'string', columnClass:'text-center'},
    {id:'mobile', title:'手机号码', type:'string', columnClass:'text-center'},
    {id:'email', title:'Email', type:'string', columnClass:'text-center'},
    {id:'gender', title:'性别', type:'string',
        resolution:function(value){
            var content = '';
            if (value == -1){content+='未设置';}
            else if (value == 0){content+='女';}
            else if (value == 1){content+='男';}
            else if (value == 2){content+='其它';}
            return content;
        }, columnClass:'text-center'},
    {id:'birthdate', title:'生日', type:'string', columnClass:'text-center'},
    {id:'location', title:'联系人地址', type:'string', columnClass:'text-center'},
    {id:'operation', title:'操作', type:'string', columnClass:'text-center',resolution:function(value, record, column, grid, dataNo, columnNo){
        var editUrl = "editAccount.jsp?id="+record.id;

        var content = '';
        content += '<button class="btn btn-info btn-sm joinExamBtn" onclick="editAccount('+record.id+')">编辑</button>';
        content += '<button class="btn btn-info btn-sm joinExamBtn" onclick="editPassword('+record.id+',\''+record.mobile+'\')">重设密码</button>';
        content += '<button class="btn btn-info btn-sm joinExamBtn" data-toggle="modal" data-target="#deleteModal" onclick="deletePreprocess('+record.id+')">删除</button>';
        return content;
    }}

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