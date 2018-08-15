$(document).ready(function () {
    //添加展开列表动画
    $("span").on("click", function () {
        $(this).next("ul").slideToggle(500);
    });
    //添加被选中的样式
    $("a").on("click", function () {
        $("a").removeClass("on");
        $(this).addClass("on");
        $("#link_content").attr("src", $(this).attr("src"));
    });
});