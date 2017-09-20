<%@page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" >
	<title>京西(JX.COM)-综合网购首选-正品低价、品质保障、配送及时、轻松购物！</title>
	<link rel="stylesheet" href="/cssregister/reset.css" type="text/css">
	<link rel="stylesheet" href="/cssregister/style.css" type="text/css">
</head>
<body>
<div id="head">
	<div class="wrap">
		<div class="logo">
			<a href="#"><img src="/imagesregister/logo.png" alt="京西商城"></a>
		</div>
		<h3>欢迎注册</h3>
	</div>
</div>
<div class="register">
	<form>
		<ul class="register_box">
			<li class="user_info"><span><b>*</b>账户名：</span><input type="text" id="username" placeholder="邮箱/用户名/手机号" class="login_name"></li>
			<li class="user_info"><span><b>*</b>请设置密码：</span><input type="password" id="password" class="login_password"></li>
			<li class="user_info"><span><b>*</b>其确认密码吗：</span><input type="password" id="passwordOther" class="login_password"></li>
			<li class="user_info"><span><b>*</b> 邮箱：</span><input type="text" id="email"></li>
			<li class="user_info"><span><b>*</b> 手机号码：</span><input type="text" id="cellPhone"></li>
			<li class="user_info"><span><b>*</b> 验证码：</span><input type="text" id="code" name="code"><img id="codeImg" alt="" src="/code/create.do" onclick="createImg();"></li>
			<li class="agree"><input type="checkbox" checked="checked" id="agreement"><label for="agreement">我已阅读并同意</label><a href="#">《用户注册协议》</a></li>
			<li class="submit"><input type="button" value="提交" class="submit_btn" onclick="register();"></li>
		</ul>
	</form>
</div>
<div id="footer">
	<p>慕课简介|慕课公告| 招纳贤士| 联系我们|客服热线：400-675-1234</p>
	<p>Copyright © 2006 - 2014 慕课版权所有   京ICP备09037834号   京ICP证B1034-8373号   某市公安局XX分局备案编号：123456789123</p>
	<div class="credit_rating">
		<a href="#"><img src="/imagesregister/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/imagesregister/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/imagesregister/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/imagesregister/pj.jpg" alt="信用评价"></a>
	</div>
</div>

<script type="text/javascript" src="/js/jquery-mini.js"></script>
    <script type="text/javascript">
		function createImg() {//页面加载时创建图片,点击时刷新图片
	    var token = Date.parse(new Date())/1000;//设置个token变量
		$('#codeImg').attr("src", "/code/create.do?" + token)//不加token的话每次请求一样,而且imag默认GET请求你每次访问的一样还是图片，会被缓存
    }

    function register() {
        $.ajax({
            url:'/malluser/register.do',
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:{
                username:$('#username').val(),
                password:$('#password').val(),
                passwordOther:$('#passwordOther').val(),
                email:$('#email').val(),
                cellPhone:$('#cellPhone').val(),
				code:$('#code').val()
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
                    createImg()
                    return;
                }

                //window.location.href='/user/toUserCenter.do';
                window.location.href='/';

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