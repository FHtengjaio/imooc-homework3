$(document).ready(function () {

    //添加表格样式
    tablestyle();

    //从localStorage中获取excel导入后的提示语句，并且展示在页面上
    var msg = localStorage.getItem("msg");
    $(".message").html(msg);
    localStorage.removeItem("msg");

    //解决搜索中文内容时，输入拼音就向服务器请求数据的问题
    var flag = false;
    $('#searchbox').on('compositionstart',function(){
        flag = true;
    });

    $('#searchbox').on('compositionend',function(){
        flag = false;
        if(!flag){
            $("#page").val("1");
            sendrequest();
        }
    });

    $('#searchbox').on('input propertychange', function() {
        if (flag) {
            return;
        }
        $("#page").val("1");
        requestdata();
    });

    //当改变页面显示条数时请求数据
    $("#size").on("change", function () {
        $("#page").val("1");
        requestdata();
    });

    //点击首页，上一页，下一页，改变当前页面数，并请求数据
    var fpNode = $(".tb_foot a:first");  //首页
    var spNode = $(".tb_foot a:eq(1)");  //上一页
    var tpNode = $(".tb_foot a:eq(2)");  //下一页
    var lpNode = $(".tb_foot a:last");   //尾页
    fpNode.on("click", function () {
        $("#page").val($(this).attr("data-id"));
        requestdata();
    });

    lpNode.on("click", function () {
        $("#page").val($(this).attr("data-id"));
        requestdata();
    });

    spNode.on("click", function () {
        if (parseInt($("#page").val()) > parseInt(fpNode.attr("data-id"))) {
            $("#page").val(parseInt($("#page").val())-1);
            requestdata();
        }
    });

    tpNode.on("click", function () {
        if (parseInt($("#page").val()) < parseInt(lpNode.attr("data-id"))) {
            $("#page").val(parseInt($("#page").val())+1);
            requestdata();
        }
    });

    //点击课程导出按钮，下载excel
    $("button").on("click", function () {
        var aNode = $("#a_exp_excel");
        var title = $("#searchbox").val();
        var href = aNode.attr("href") + title;
        window.open(href);
    });

    //ajax请求课程数据
    function requestdata() {
        $.ajax({
            url:"GetCourse.do",
            method:"post",
            data:{
                size:$("#size").val(),
                title:$("#searchbox").val(),
                page:$("#page").val()
            },
            dataType:'json',
            success:function (data) {
                $(".tb_bd").empty();    //把原有的数据清理
                $(".message").empty();  //把message清理
                //遍历数据，向表格添加内容
                $.each(data.allCourses, function (index, item) {
                    $(".tb_bd").append(
                        "<ul>" +
                        "<li>" +
                        "<div class='bd_content'>"+ item.id +"</div>" +
                        "<div class='bd_content'>"+ item.name +"</div>" +
                        "<div class='bd_content'>"+ item.direction +"</div>" +
                        "<div class='bd_content desc'>"+ item.des +"</div>" +
                        "<div class='bd_content'>"+ item.time +"</div>" +
                        "<div class='bd_content last'>"+ item.operator +"</div>" +
                        "</li>" +
                        "</ul>"
                    );
                });
                $("#last_a").attr("data-id", data.totalPage);  //设置尾页编号
                $("#searchedCount").html(data.searchedCount);  //提示搜索到的数据数
                $("#totalCount").html(data.totalCount);        //总共有的数据数
                tablestyle();                                  //重现加载表格样式
            },
            error:function (data) {
            }
        });
    }

    function tablestyle() {
        $("li:odd>div").addClass("odd");
        $("li>div").on("mouseenter mouseleave", function () {
            $(this).parent().children("div").toggleClass("on");
        });
    }
});