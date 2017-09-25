<%@page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="v-bind" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" >
	<title>京西(JX.COM)-综合网购首选-正品低价、品质保障、配送及时、轻松购物！</title>
	<link rel="stylesheet" href="/cssGoodsIntroduce/reset.css" type="text/css">
	<link rel="stylesheet" href="/cssGoodsIntroduce/style.css" type="text/css">
</head>
<body class="grey">
<div id="head">
	<div class="head_top">
		<div class="wrap clearfix">
			<div class="leftArea">
				<a href="#" id="collection">收藏京西</a>
			</div>
			<div class="rightArea">欢迎来到京西网！<a href="#">[登录]</a><a href="#">[免费注册]</a></div>
		</div>
	</div>
	<div class="search">
		<div class="wrap">
			<div class="logo">
				<a href="#"><img src="/imagesGoodsIntroduce/logo.png" alt="京西商城"></a>
			</div>
			<div class="shop_car">
				<span class="car">购物车</span>
				<span class="num_text">0</span>
			</div>
		</div>
	</div>
<div class="bread wrap"><a class="index" href="#">首页</a><em>&gt;</em><a href="#">平板电脑</a>&gt;<a href="#">Apple 苹果</a>&gt;<a href="#">MD531CH/A</a></div>
<div class="commodity_info wrap clearfix">
	<div class="info_left">
		<div class="commodity_img"><img src="/imagesGoodsIntroduce/sp.jpg" alt="商品图片"></div>
		<ul class="clearfix">
			<li class="imgOn"><img src="/imagesGoodsIntroduce/img_list.jpg" alt="缩略图"></li>
			<li><img src="/imagesGoodsIntroduce/img_list.jpg" alt="缩略图"></li>
			<li><img src="/imagesGoodsIntroduce/img_list.jpg" alt="缩略图"></li>
			<li><img src="/imagesGoodsIntroduce/img_list.jpg" alt="缩略图"></li>
			<li><img src="/imagesGoodsIntroduce/img_list.jpg" alt="缩略图"></li>
		</ul>
	</div>
	<div class="info_right">
		<h3 class="shop_name">${mallGood.goodsDesc}</h3>
		<dl class="price">
			<dt>京西价</dt>
			<dd><b>￥</b>${mallGood.price}</dd>
		</dl>
		<dl class="favourable">
			<dt>优惠</dt>
			<dd><span>满换购</span>购ipad加价优惠够配件或USB充电插座</dd>
		</dl>
			<div class="selection">
				<dl>
					<dt>数量</dt>
					<dd class="clearfix" id="app">
						<div class="num_select">
							<span onclick="minus();">-</span>
								<input  type="text"  id="goodsCounts" name="goodsCounts" v-bind:value="a">
							<span onclick="plus();">+</span>
						</div>
						<span class="limit_num">限购<b>9</b>件</span>
					</dd>
				</dl>
			</div>
		<div class="buy">
			<h4 class="selected">已选择<span>“白色|WIFI 16G”</span></h4>
			<div class="buy_btn" id="app2">
				<a onclick="addCart();">加入购物车</a>
				<input type="hidden" id="goodsId" name="goodsId" value=${mallGood.id}>
				<span class="ver_line"></span>
				<a onclick="buy();">立即购买</a>
			</div>
			<p class="notice">注意：此商品可提供普通发票，不提供增值税发票。</p>
		</div>
	</div>
</div>
	<input type="hidden" id="cartToken" value="${cart_token}">

<div id="footer">
	<p>慕课简介|慕课公告| 招纳贤士| 联系我们|客服热线：400-675-1234</p>
	<p>Copyright © 2006 - 2014 慕课版权所有   京ICP备09037834号   京ICP证B1034-8373号   某市公安局XX分局备案编号：123456789123</p>
	<div class="credit_rating">
		<a href="#"><img src="/imagesGoodsIntroduce/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/imagesGoodsIntroduce/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/imagesGoodsIntroduce/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/imagesGoodsIntroduce/pj.jpg" alt="信用评价"></a>
	</div>
</div>

<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/vue-mini.js"></script>
<script type="text/javascript">
    var vm = {};
	$(document).ready(function () {
        var data = { a: 1 };//默认商品数量1
		vm = new Vue({
            el: '#app',
            data: data,
			methods: {
                plus: function () {
					this.a++;
                },

                minus: function () {
					this.a--
                }
			}
        })
	});

	function addCart() {
        $.ajax({
            url:'/shopCart/toOrder.do',
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:{
                goodsId:$('#goodsId').val(),
                goodsCounts:$('#goodsCounts').val(),
                cartToken:$('#cartToken').val()
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
                //window.location.href='/';

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

    function buy() {
        $.ajax({
            url:'/order/toOrder.do',
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:{
                goodsId:$('#goodsId').val(),
                goodsCounts:$('#goodsCounts').val()
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
                //window.location.href='/';
                window.location.href='/order/order.do';

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

	function plus() {
	    vm.plus();
	    alert(vm.a);
	}

    function minus() {
		if (vm.a <= 1) {
	        return false;
		}

		vm.minus();
	    alert(vm.a);
    }

	</script>
</body>
</html>