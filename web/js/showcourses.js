$(document).ready(function () {

    tablestyle();

    var msg = localStorage.getItem("msg");
    $(".message").html(msg);
    localStorage.removeItem("msg");

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
        sendrequest();
    });

    $("#size").on("change", function () {
        $("#page").val("1");
        sendrequest();
    });

    var fpNode = $(".tb_foot a:first");
    var spNode = $(".tb_foot a:eq(1)");
    var tpNode = $(".tb_foot a:eq(2)");
    var lpNode = $(".tb_foot a:last");
    fpNode.on("click", function () {
        $("#page").val($(this).attr("data-id"));
        sendrequest();
    });

    lpNode.on("click", function () {
        $("#page").val($(this).attr("data-id"));
        sendrequest();
    });

    spNode.on("click", function () {
        if (parseInt($("#page").val()) > parseInt(fpNode.attr("data-id"))) {
            $("#page").val(parseInt($("#page").val())-1);
            sendrequest();
        }
    });

    tpNode.on("click", function () {
        if (parseInt($("#page").val()) < parseInt(lpNode.attr("data-id"))) {
            $("#page").val(parseInt($("#page").val())+1);
            sendrequest();
        }
    });

    $("button").on("click", function () {
        var aNode = $("#a_exp_excel");
        var title = $("#searchbox").val();
        var href = aNode.attr("href") + title;
        window.open(href);
    });

    function sendrequest() {
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
                $(".tb_bd").empty();
                $(".message").empty();
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
                $("#last_a").attr("data-id", data.totalPage);
                $("#searchedCount").html(data.searchedCount);
                $("#totalCount").html(data.totalCount);
                tablestyle();
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