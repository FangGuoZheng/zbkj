var datas = new Array();
var nowId=0;
$(function(){
    if(roleName=="admin"){
        // 初始化页面上的提示框
        $('[data-toggle="tooltip"]').tooltip();
        $.ajax(
            {
                type: "POST",
                url: ctx + '/StaffAction!getStaff.action',
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
            var m4Staff=new Object();
            m4Staff.staffId=row["staffId"];
            m4Staff.account=row["account"];
            m4Staff.name=row["name"];
            m4Staff.phone=row["phone"];
            m4Staff.email=row["email"];
            m4Staff.roleName=row["roleName"];
            datas.push(m4Staff);
        }

    }
    else
    {
        $.notify({message: msg },{placement: {from: "bottom",align: "right"},type: 'success',delay: 2000});
    }
}
function editAccount(staffId)
{
    window.open("StaffEdit.jsp?staffId="+staffId);
}
function editPassword(id,account)
{
    window.open("StaffEditPassword.jsp?id="+id+"&account="+account);
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
            url: ctx + '/StaffAction!deleteById.action',
            data:{
                staffId:nowId
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
    {id:'staffId', title:'编号', type:'number', columnClass:'text-center'},
    {id:'account', title:'账号', type:'string', columnClass:'text-center'},
    {id:'name', title:'姓名', type:'string', columnClass:'text-center'},
    {id:'phone', title:'电话号码', type:'string', columnClass:'text-center'},
    {id:'roleName', title:'角色', type:'string', columnClass:'text-center'},
    {id:'email', title:'Email', type:'string', columnClass:'text-center'},
    {id:'operation', title:'操作', type:'string', columnClass:'text-center',resolution:function(value, record, column, grid, dataNo, columnNo){
        var content = '';
        content += '<button class="btn btn-info btn-sm joinExamBtn" onclick="editAccount('+record.staffId+')">编辑</button>';
        content += '<button class="btn btn-info btn-sm joinExamBtn" onclick="editPassword('+record.staffId+',\''+record.account+'\')">重设密码</button>';
        content += '<button class="btn btn-info btn-sm joinExamBtn" data-toggle="modal" data-target="#deleteModal" onclick="deletePreprocess('+record.staffId+')">删除</button>';
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