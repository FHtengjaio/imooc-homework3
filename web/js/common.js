$(document).ready(function () {
    //设置ajax访问每次判断session是否过期
    $.ajaxSetup({
        complete: function(xhr,status) {
            var sessionStatus = xhr.getResponseHeader('sessionstatus');
            if(sessionStatus === 'timeout') {
                var top = gettopwinow();
                alert('由于您长时间没有操作, session已过期, 请重新登录.');
                top.location.href = "index";
            }
        }
    });

    function gettopwinow() {
        var page = window;
        while(page !== page.parent){
            page = page.parent;
        }
        return page;
    }
});