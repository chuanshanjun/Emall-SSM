<%@page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="utf-8" >
    <title>京西(JX.COM)-综合网购首选-正品低价、品质保障、配送及时、轻松购物！</title>
    <link rel="stylesheet" href="/css/reset.css" type="text/css">
    <link rel="stylesheet" href="/css/style.css" type="text/css">
</head>
<div id="head">
    <div class="head_top">
        <div class="wrap clearfix">
            <div class="leftArea">
                <a href="#" id="collection">收藏京西</a>
            </div>
            <shiro:authenticated>
                <div class="rightArea">欢迎<shiro:principal/>来到京西网！<a href="/malluser/logout.do">[退出登陆]</a></div>
            </shiro:authenticated>
            <shiro:guest>
                <div class="rightArea">欢迎来到京西网！<a href="/malluser/toLogin.do">[登录]</a><a href="/malluser/toRegister.do">[免费注册]</a></div>
            </shiro:guest>
        </div>
    </div>
    <div class="search">
        <div class="wrap">
            <div class="logo">
                <a href="#"><img src="/images/logo.png" alt="京西商城"></a>
            </div>
            <div class="search_box">
            </div>
            <div class="shop_car">
                <span class="car"><a href="/shopCart/toCart.do"/>购物车</span>
                <span class="num_text">0</span>
            </div>
        </div>
    </div>
    <div class="nav">

    </div>
    <div class="banner">

    </div>
</div>

<div id="goodshow">
    <div class="shopArea" v-for="xx in category"><!--遍历商品类目-->
        <div class="wrap">
            <div class="shop_title">
                <h3>{{xx.typeName}}</h3>
                <span>更多&gt;&gt;</span>
            </div>
            <div class="main clearfix">
                <div class="shop_banner">
                    <img src="/images/ad01.jpg" alt="shop_banner">
                </div>
                <div class="shop_list">
                    <div class="shoplist_box clearfix">
                        <div class="shopItem_top" v-for="(good, index) in xx.goods" v-if="index < 4"><!--遍历商品详情-->
                            <div class="shop_img">
                                <a v-bind:href="'/good/toGoodsIntroduce.do?goodId=' + good.id">
                                    <img src="/images/HTC.jpg" alt="商品">
                                </a>
                            </div>
                            <h4>{{good.name}}</h4>
                            <p>{{good.price}}元</p>
                        </div>

                        <div class="shopItem_bottom" v-for="(good, index) in xx.goods" v-if="index >= 4">
                            <div class="shop_img">
                                <a v-bind:href="'good/toGoodsIntroduce.do?goodId=' + good.id">
                                    <img src="/images/NFC.jpg" alt="商品">
                                </a>
                            </div>
                            <div class="shop_text">
                                <p>{{good.name}}</p>
                                <span>￥{{good.price}}</span>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="footer">
    <p>慕课简介|慕课公告| 招纳贤士| 联系我们|客服热线：400-675-1234</p>
    <p>Copyright © 2006 - 2014 慕课版权所有   京ICP备09037834号   京ICP证B1034-8373号   某市公安局XX分局备案编号：123456789123</p>
    <div class="credit_rating">
        <a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
        <a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
        <a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
        <a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
    </div>
</div>

<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/vue-mini.js"></script>
<script type="text/javascript">
    //将需要加载的资源放在下面，先给客户展示些页面信息
    var goodshow = {};
    $(document).ready(function () {
        goodshow = new Vue({
            el : '#goodshow',
            data : {
                category : [
                ]
            }
        });

        listSort();//首先遍历商品类目的信息
    });

    function listSort() {
        $.ajax({
            url:'/show/type.do',
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:{
            },
            timeout:5000,    //超时时间
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            beforeSend:function(xhr){
                console.log(xhr)
                console.log('发送前')
            },
            success:function(data,textStatus,jqXHR){

                if (!data.success) {
                    alert(data.message);
                    return;
                }

                goodshow.category = data.data;//将遍历出来的商品类目放到数组中
                var len = data.data.length;
                for (var i=0; i<len; i++) {
                    var cg = goodshow.category[i];
                    listGoods(cg, i);//遍历每个商品类目中的商品详情
                }

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

    function listGoods(cg, index) {
        $.ajax({
            url:'/show/goods.do',
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:{
                typeId: cg.id,
            },
            timeout:5000,    //超时时间
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            beforeSend:function(xhr){
                console.log(xhr)
                console.log('发送前')
            },
            success:function(data,textStatus,jqXHR){

                if (!data.success) {
                    alert(data.message);
                    return;
                }

                cg["goods"] = data.data;//将遍历出来的商品信息放到数组中
                goodshow.category.splice(index, 1, cg)//将带有商品详情的数组插入category中

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
