$(document).ready(function () {
    $("span").on("click", function () {
        $(this).next("ul").slideToggle(800);
    });
    $("a").on("click", function () {
        $("a").removeClass("on");
        $(this).addClass("on");
        $("#link_content").attr("src", $(this).attr("src"));
    });
});