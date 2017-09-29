<%@page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" >
	<title>京西(JX.COM)-综合网购首选-正品低价、品质保障、配送及时、轻松购物！</title>
	<link rel="stylesheet" href="/csslogin/reset.css" type="text/css">
	<link rel="stylesheet" href="/csslogin/style.css" type="text/css">
</head>
<body>
<div id="head">
	<div class="wrap">
		<div class="logo">
			<a href="#"><img src="/imageslogin/logo.png" alt="京西商城"></a>
		</div>
		<h3>欢迎登录</h3>
	</div>
</div>
<div class="login">
	<form id="loginForm">
		<ul class="login_box">
			<li class="login_text">邮箱/用户名/已验证手机/</li>
			<li><input type="text" id="username" name="username" class="user_name"></li>
			<li class="login_text">密码</li>
			<li><input type="password" id="password" name="password" class="user_password"></li>
			<li class="login_check">
			</li>
			<li><input class="login_btn" type="button" value="登录" onclick="login();"></li>
			<li class="login_text">使用合作账号登录</li>
			<li class="co_account">
			</li>
		</ul>
	</form>
</div>
<div class="wrap register">
	<a href="#" class="free_register">免费注册&gt;&gt;</a></div>
<div id="footer">
	<p>慕课简介|慕课公告| 招纳贤士| 联系我们|客服热线：400-675-1234</p>
	<p>Copyright © 2006 - 2014 慕课版权所有   京ICP备09037834号   京ICP证B1034-8373号   某市公安局XX分局备案编号：123456789123</p>
	<div class="credit_rating">
		<a href="#"><img src="/imageslogin/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/imageslogin/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/imageslogin/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/imageslogin/pj.jpg" alt="信用评价"></a>
	</div>
</div>

<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/jquery-validate.min.js"></script>
<script type="text/javascript" src="/js/messages_zh.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
        $("#loginForm").validate({//使用jQuery的validate校验框架
            rules: {
                username: "required",
                password: {
                    required: true,
                    minlength: 3,
                    maxlength: 6
                }
            },
            messages: {
                username: "请输入登录名",
                password: {
                    required : "请输入密码",
                    minlength : "密码最小长度为1",
                    maxlength : "密码最大长度为6"
                }
            }
        });
    });


	function login() {
		if (!$("#loginForm").valid()) {
		    return false;
		}

		$.ajax({
            url:'/malluser/login.do',
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:{
                username:$('#username').val(),
                password:$('#password').val()
            },
            timeout:5000,    //超时时间
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            beforeSend:function(xhr){
                console.log(xhr)
                console.log('发送前')
            },
            success:function(data,textStatus,jqXHR){
                var obj = data;
                alert(obj.success);

                if (!obj.success) {
                    alert(obj.message);
                    return;
                }

                //window.location.href='/user/toUserCenter.do';
                window.location.href='/order/toMyOrder.do';

            },
            error:function(xhr,textStatus){
                console.log('错误')
                console.log(xhr)
                console.log(textStatus)
            },
            complete:function(){
                console.log('结束')
            }
        });
    }
</script>

</body>
</html>