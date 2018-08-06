function setPage(id) {
    var oPageNode = $("page");
    if (id === 1) {
        oPageNode.val(1);
    }else if (id === 2) {
        if (oPageNode.val() > 1) {
            oPageNode.val(oPageNode.val()-1);
        }
    }else if (id === 3) {
        if (oPageNode.val() < ) {
            oPageNode.val(oPageNode.val()+1);
        }
    }

}