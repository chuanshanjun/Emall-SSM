<%@page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>cart</title>
		<link rel="stylesheet" type="text/css" href="/csscart/public.css"/>
		<link rel="stylesheet" type="text/css" href="/csscart/proList.css" />
	</head>
	<body>
		<!--------------------------------------cart--------------------->
		<div class="head ding">
			<div class="wrapper clearfix">
				<div class="clearfix" id="top">
					<h1 class="fl"><a href="index.html"><img src="/imgcart/logo.png"/></a></h1>
					<div class="fr clearfix" id="top1">
						<p class="fl">
							<a href="login.html" id="login">登录</a>
							<a href="reg.html" id="reg">注册</a>
						</p>
						<form action="#" method="get" class="fl">
							<input type="text" placeholder="搜索" />
							<input type="button" />
						</form>
						<div class="btn fl clearfix">
							<a href="mygxin.html"><img src="/imgcart/grzx.png"/></a>
							<a href="#" class="er1"><img src="/imgcart/ewm.png"/></a>
							<a href="cart.html"><img src="/imgcart/gwc.png"/></a>
							<p><a href="#"><img src="/imgcart/smewm.png"/></a></p>
						</div>
					</div>
				</div>
				<ul class="clearfix" id="bott">
					<li><a href="index.html">首页</a></li>
					<li>
						<a href="#">所有商品</a>
						<div class="sList">
							<div class="wrapper  clearfix">
								<a href="paint.html">
									<dl>
										<dt><img src="/imgcart/nav1.jpg"/></dt>
										<dd>浓情欧式</dd>
									</dl>
								</a>
								<a href="paint.html">
									<dl>
										<dt><img src="/imgcart/nav2.jpg"/></dt>
										<dd>浪漫美式</dd>
									</dl>
								</a>
								<a href="paint.html">
									<dl>
										<dt><img src="/imgcart/nav3.jpg"/></dt>
										<dd>雅致中式</dd>
									</dl>
								</a>
								<a href="paint.html">
									<dl>
										<dt><img src="/imgcart/nav6.jpg"/></dt>
										<dd>简约现代</dd>
									</dl>
								</a>
								<a href="paint.html">
									<dl>
										<dt><img src="/imgcart/nav7.jpg"/></dt>
										<dd>创意装饰</dd>
									</dl>
								</a>
							</div>
						</div>
					</li>
					<li>
						<a href="flowerDer.html">装饰摆件</a>
						<div class="sList2">
							<div class="clearfix">
								<a href="proList.html">干花花艺</a>
								<a href="vase_proList.html">花瓶花器</a>
							</div>
						</div>
					</li>
					<li>
						<a href="decoration.html">布艺软饰</a>
						<div class="sList2">
							<div class="clearfix">
								<a href="zbproList.html">桌布罩件</a>
								<a href="bzproList.html">抱枕靠垫</a>
							</div>
						</div>
					</li>
					<li><a href="paint.html">墙式壁挂</a></li>
					<li><a href="perfume.html">蜡艺香薰</a></li>
					<li><a href="idea.html">创意家居</a></li>
				</ul>
			</div>
		</div>
		<div class="cart mt">
			<!-----------------logo------------------->
			<!--<div class="logo">
				<h1 class="wrapper clearfix">
					<a href="index.html"><img class="fl" src="img/temp/logo.png"></a>
					<img class="top" src="img/temp/cartTop01.png">
				</h1>
			</div>-->
			<!-----------------site------------------->
			<div class="site">
				<p class=" wrapper clearfix">
					<span class="fl">购物车</span>
					<img class="top" src="/imgcart/temp/cartTop01.png">
					<a href="index.html" class="fr">继续购物&gt;</a>
				</p>
			</div>
			<!-----------------table------------------->
			<div class="table wrapper">
				<div class="tr">
					<div>商品</div>
					<div>单价</div>
					<div>数量</div>
					<div>小计</div>
					<div>操作</div>
				</div>
				<div id="app">
					<div class="th" v-for="(xx,index) in category">
						<div class="pro clearfix">
							<label class="fl">
								<input type="checkbox" v-bind:id="index" v-on:click="goodChecked"/><!-- 将input框中的id绑定index的值-->
								<span></span>
							</label>
							<a class="fl" href="#">
								<dl class="clearfix">
									<dt class="fl"><img src="/imgcart/temp/cart01.jpg"></dt>
									<dd class="fl">
										<p>{{xx.goods.name}}</p>
										<p>创意现代简约干花花瓶摆件</p>
										<p>颜色分类:</p>
										<p>白色瓷瓶+白色串枚</p>
									</dd>
								</dl>
							</a>
						</div>
						<div class="price">￥{{xx.goods.price}}</div>
						<div class="number">
							<p class="num clearfix">
								<img class="fl sub" src="/imgcart/temp/sub.jpg" v-on:click="minus(index)">
								<span class="fl">{{xx.counts}}</span>
								<img class="fl add" src="/imgcart/temp/add.jpg" v-on:click="plus(index)">
							</p>
						</div>
						<div class="price sAll">￥20.00</div>
						<div class="price"><a class="del" v-bind:href="'/shopCart/deleteGoods.do?shoppingCartId=' + xx.id" >删除</a></div>
					</div>

				<div class="goOn">空空如也~<a href="index.html">去逛逛</a></div>
				<div class="tr clearfix">
					<label class="fl">
						<input class="checkAll" type="checkbox" id="selectAll" onclick="selectAll();"/>
						<span></span>
					</label>
					<p class="fl">
						<a href="#">全选</a>
						<a href="#" class="del">删除</a>
					</p>
					<p class="fr">
						<span>共<small id="sl">0</small>件商品</span>
						<span>合计:&nbsp;<small id="totalAmount">￥0.00</small></span>
						<a href="###" id="settlementButton" class="count">结算</a>
					</p>
				</div>
			</div>
		</div>
		<div class="mask"></div>
		<div class="tipDel">
			<p>确定要删除该商品吗？</p>
			<p class="clearfix">
				<a class="fl cer" href="#">确定</a>
				<a class="fr cancel" href="#">取消</a>
			</p>
		</div>
		<!--返回顶部-->
		<div class="gotop">
			<a href="cart.html">
			<dl>
				<dt><img src="/imgcart/gt1.png"/></dt>
				<dd>去购<br />物车</dd>
			</dl>
			</a>
			<a href="#" class="dh">
			<dl>
				<dt><img src="/imgcart/gt2.png"/></dt>
				<dd>联系<br />客服</dd>
			</dl>
			</a>
			<a href="mygxin.html">
			<dl>
				<dt><img src="/imgcart/gt3.png"/></dt>
				<dd>个人<br />中心</dd>
			</dl>
			</a>
			<a href="#" class="toptop" style="display: none;">
			<dl>
				<dt><img src="/imgcart/gt4.png"/></dt>
				<dd>返回<br />顶部</dd>
			</dl>
			</a>
			<p>400-800-8200</p>
		</div>
		<!--footer-->
		<div class="footer">
			<p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br />
			违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
		</div>
		<!----------------mask------------------->
		<div class="mask"></div>
		<!-------------------mask内容------------------->
		<div class="proDets">
			<img class="off" src="/imgcart/temp/off.jpg" />
			<div class="proCon clearfix">
				<div class="proImg fr">
					<img class="list" src="/imgcart/temp/proDet.jpg"  />
					<div class="smallImg clearfix">
						<img src="/imgcart/temp/proDet01.jpg" data-src="/imgcart/temp/proDet01_big.jpg">
						<img src="/imgcart/temp/proDet02.jpg" data-src="/imgcart/temp/proDet02_big.jpg">
						<img src="/imgcart/temp/proDet03.jpg" data-src="/imgcart/temp/proDet03_big.jpg">
						<img src="/imgcart/temp/proDet04.jpg" data-src="/imgcart/temp/proDet04_big.jpg">
					</div>
				</div>
				<div class="fl">
					<div class="proIntro change">
						<p>颜色分类</p>
						<div class="smallImg clearfix">
							<p class="fl on"><img src="/imgcart/temp/prosmall01.jpg" alt="白瓷花瓶+20支快乐花" data-src="/imgcart/temp/proBig01.jpg"></p>
							<p class="fl"><img src="/imgcart/temp/prosmall02.jpg" alt="白瓷花瓶+20支兔尾巴草" data-src="/imgcart/temp/proBig02.jpg"></p>
							<p class="fl"><img src="/imgcart/temp/prosmall03.jpg" alt="20支快乐花" data-src="/imgcart/temp/proBig03.jpg"></p>
							<p class="fl"><img src="/imgcart/temp/prosmall04.jpg" alt="20支兔尾巴草" data-src="/imgcart/temp/proBig04.jpg"></p>
						</div>
					</div>
					<div class="changeBtn clearfix">
						<a href="#2" class="fl"><p class="buy">确认</p></a>
						<a href="#2" class="fr"><p class="cart">取消</p></a>
					</div>
				</div>
			</div>
		</div>
		</div>
		<div class="pleaseC">
			<p>请选择宝贝</p>
			<img class="off" src="/imgcart/temp/off.jpg" />
		</div>
		
<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/vue-mini.js"></script>
		<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript">
	var cartGoodsShow = {};

    function selectAll() {
        $('.th :checkbox').each(function () {//.意思是按照样式来选择(class="th")，选择th样式下所有的checkbox框
            if ($('#selectAll').is(':checked')) {//如果selectAll节点被选中
                $(this).prop('checked', true);//如果全选框选中，那么样式th下所有的checkbox的checked属性置为true，也就是全部选中
            } else {
                $(this).prop('checked', false);
            }
        });

        countAmount();
    }

    function countAmount() {
		var goodArray = [];//定义一个货物的数组
		$('.th :checkbox').each(function () {//遍历th样式下所有的checkbox
			if ($(this).is(':checked')) {
			    var attrValue = $(this).attr('id');//获取checkbox中的id的值，此时id的值就是商品货物的index
			    goodArray.push(attrValue)//将索引装入数组中
			}
        });

		var total = 0;
		for (var i=0; i<goodArray.length; i++) {
		    var index = goodArray[i];
		    var good = cartGoodsShow.category[index];
		    var price = good.goods.price;
		    var counts = good.counts;
		    total += price * counts;
		}
		$('#totalAmount').html(total);
    }

	$(document).ready(function () {
        cartGoodsShow = new Vue({
			el : '#app',
			data : {
			    category : []
			},
			methods : {
				plus :  function (index) {
					var cg = this.category[index];//通过索引来操作购物车中的每一条数据
					cg.counts += 1 ;//得到每条数据的数目参数
                    countAmount();
                },
				
				minus : function (index) {
					var cg = this.category[index];

					if ( cg.counts <= 1) {
						return;
					}
					
					cg.counts -= 1 ;
                    countAmount();
                },

                goodChecked : function () {
                    countAmount();
                }
			}

		});

        $('#settlementButton').click(function () {
			//settlement();
            //settlement4Json();
			settlement4JsonBody();
        });

        listCartGoods();

    });

	function settlement4JsonBody() {
		var buyGoodArray = [];//新建一个所需要结算的货物的数组

		$('.th :checkbox').each(function () {
			if ($(this).is(':checked')) {
			    var cartIndex = $(this).attr('id');
			    var cartGood = cartGoodsShow.category[cartIndex];
			    buyGoodArray.push({goodId : cartGood.goodsId, goodCounts : cartGood.counts});//将具体货物的信息放到数组中
			}
        });

		var msg = JSON.stringify(buyGoodArray);//将数组转换成JSON格式

		alert(msg);

        $.ajax({
            url:'/order/createOrder4JsonBody.do',
            type:'POST', //GET
            async:true,    //或false,是否异步
            data: msg,//直接放变量msg进去
			contentType:"application/json",//因为直接传对象需要在响应头中设置contentType，这样后台使用RequestBody接收
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

                alert('success');

                //跳转到结算页
                window.location.href = '/order/toOrder.do?mallOrderId=' + data.data.id;

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

    function settlement4Json() {
		var buyGoodArray = [];

		$('.th :checkbox').each(function () {
			if ($(this).is(':checked')) {
			    var cartIndesx = $(this).attr('id');
			    var cartGood = cartGoodsShow.category[cartIndesx];
			    buyGoodArray.push({goodId : cartGood.goodsId, goodCounts : cartGood.counts});
			}
        });

		//获取商品ID和数量
		var msg = JSON.stringify(buyGoodArray);

		alert(msg);

        $.ajax({
            url:'/order/createOrder4Json.do',
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:{
                buyMsg : msg//data数据以key value的格式发送到后台
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

				alert('success');

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
	
	function listCartGoods() {
        $.ajax({
            url:'/shopCart/showGoods.do',
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

                cartGoodsShow.category = data.data;//这样的话页面先加载数据但找不到goods会报错，等下面的数据填充上来就不报错了

                var len = data.data.length;
                for (var i=0; i<len; i++) {
                    var cg = data.data[i];
                    listGoodsDetail(cg, i);
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
    
    function settlement() {
		var buyGoodArray = [];

		$('.th :checkbox').each(function () {//遍历所有选择框查看是否有被选中的选择框
			if ($(this).is(':checked')) {//如果有选择框被选中 this只当前的的checkbox
			    var cartIndex = $(this).attr('id');//将被选中的选择框的id属性对应的值赋值给cartIndex
			    var cartGood = cartGoodsShow.category[cartIndex];//通过索引获取该商品的详细信息
			    buyGoodArray.push(cartGood);//将该商品的详细信息放到数组中
			}
        });

		//获取商品ID和数量
		var msg = "";
		for (var i=0; i<buyGoodArray.length; i++) {
		    var good = buyGoodArray[i];
		    msg += good.goodsId + '-' + good.counts + '$';//将商品的ID和数量拼接起来
		}

        $.ajax({
            url:'/order/createOrder.do',
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:{
                buyMsg : msg,
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

                alert('success');

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

    function listGoodsDetail(cg, index) {
        $.ajax({
            url:'/shopCart/showGoodsDetail.do',
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

                cartGoodsShow.$set(cg, "goods", data.data);//将data的内容给到cg的goods属性

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
