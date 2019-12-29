$(document).ready(function(){
        //记住密码功能
        var str = getCookie("loginInfo");
        str = str.substring(1,str.length-1);
        var username = str.split(",")[0];
        var password = str.split(",")[1];
        //自动填充用户名和密码
        $("#userName").val(username);
        $("#pwd").val(password);
});

//获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}

//记住密码功能
function remember(){
    var remFlag = $("input[type='checkbox']").is(':checked');
    if(remFlag==true){ //如果选中设置remFlag为1
        //cookie存用户名和密码,回显的是真实的用户名和密码,存在安全问题.
        var conFlag = confirm("记录密码功能不宜在公共场所(如网吧等)使用,以防密码泄露.您确定要使用此功能吗?");
        if(conFlag){ //确认标志
            $("#remFlag").val("1");
        }else{
            $("input[type='checkbox']").removeAttr('checked');
            $("#remFlag").val("");
        }
    }else{ //如果没选中设置remFlag为""
        $("#remFlag").val("");
    }
}