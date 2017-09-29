<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="zuo fl">
    <h3>
        <a href="#"><img src="/imgcart/tx.png"/></a>
        <p class="clearfix"><span class="fl"><shiro:principal/></span><span class="fr"><a href="/malluser/logout.do">[退出登录]</span></p>
    </h3>
    <div>
        <h4>我的交易</h4>
        <ul>
            <li><a href="cart.html">我的购物车</a></li>
            <li><a href="/order/toMyOrder.do">我的订单</a></li>
            <li><a href="myprod.html">评价晒单</a></li>
        </ul>
        <h4>个人中心</h4>
        <ul>
            <li><a href="mygxin.html">我的中心</a></li>
            <li class="on"><a href="address.html">地址管理</a></li>
        </ul>
        <h4>账户管理</h4>
        <ul>
            <li><a href="mygrxx.html">个人信息</a></li>
            <li><a href="remima.html">修改密码</a></li>
        </ul>
    </div>
</div>