$(document).ready(function () {
    $("li:odd>div").addClass("odd");
    $("li>div").on("mouseenter mouseleave", function () {
        $(this).parent().children("div").toggleClass("on");
    });
});