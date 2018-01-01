var basePath = getRootPath();
var json = {};

function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var hostPath=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(hostPath+projectName + "/");
}

$(function() {
    addBtnClickListener();
});

function addBtnClickListener() {
    $('#loginBtn').click(function () {
        login()
    });
}

function keyDown(e){
    var keycode = 0;
    //IE浏览器
    if(CheckBrowserIsIE()){
        keycode = event.keyCode;
    }else{
        //火狐浏览器
        keycode = e.which;
    }
    if (keycode == 13 ) { //enter 按键
        login();
    }
}
//判断访问者的浏览器是否是IE
function CheckBrowserIsIE(){
    var result = false;
    var browser = navigator.appName;
    if(browser == "Microsoft Internet Explorer"){
        result = true;
    }
    return result;
}

function checkForm() {
    var username = $("#username").val();
    var password = $("#password").val();
    if(username=='' || username.trim()=='') {
        $(".error").text("请填写用户名！");
        $("#username").focus();
        return false;
    }
    if(password=='' || password.trim()=='') {
        $(".error").text("请填写密码！");
        $("#password").focus();
        return false;
    }
    json = {};
    json.username = username;
    json.password = password;
    return true;
}

function login() {
	if(!checkForm()){
		return;
	}
    $.ajax({
        type : "post",
        url :  '/login.do',
        data : json,
        async : false,
        dataType : "json",
        success : function(rs) {
            if (rs.code == '200') {
                location.href = '/index.html';
            } else {
                $(".error").text(rs.msg);
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.statusText);
        }
    });
}