/**
 * 当标签出现在脚本代码后面时，不能使用$().click();绑定单击事件，使用on（）方法
 * on绑定事件称之为委托方式绑定, 但是on（）也需要标签已经被解析才可以绑定事件
 * 调用JQuery的ready函数执行文档加载完成后处理事件绑定
 */
$(function () { //文档加载完成后执行的回调函数
    $("td").on("click",".delbtn",function () {
        return confirm("确认删除吗？这个操作不可恢复");
    });

    //处理复选框的操作 吧所有的复选框遍历
    var uids={uids:[]};
    $(".cuid").click(function () {
        //{"uids":["4","4","5"]} 出现了这个问题
        //每次点击 都把选的对象放进去了
        //解决方法，将其每次制空 添加之后 =为{"uids":["6","7"]}
        uids.uids=[];
        $(".cuid:checked").each(function () {
            uids.uids.push($(this).val());
        });
    });

    //多条信息删除
    $(".delbtns").click(function () {
        if (uids.uids.length<=0) return;
        if(confirm("确认要删除被选择的信息吗？这个操作不可恢复")){
            var json = JSON.stringify(uids);
            //填充json字符串
            $("#deleteuids").val(json);

            //此处可以用ajax请求方式 此处用另一个表单来提交
            $("#form1").attr("action","/deleteusers");
            $("#form1").submit();
        }
    });

});
