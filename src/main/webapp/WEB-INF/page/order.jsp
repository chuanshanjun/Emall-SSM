<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>order</title>
		<link rel="stylesheet" type="text/css" href="/csscart/public.css"/>
		<link rel="stylesheet" type="text/css" href="/csscart/proList.css" />
		<link rel="stylesheet" type="text/css" href="/csscart/mygxin.css" />
	</head>
	<body>
		<!----------------------------------------order------------------>
		<!------------------ head begin --------------------------------->
		<jsp:include page="common/header.jsp"></jsp:include>
		<!------------head end--------------------------->
		<div class="order cart mt">
			<!-----------------site------------------->
			<div class="site">
				<p class="wrapper clearfix">
					<span class="fl">订单确认</span>
					<img class="top" src="/imgcart/temp/cartTop02.png">
				</p>
			</div>
			<!-----------------orderCon------------------->
			<div class="orderCon wrapper clearfix">
				<div class="orderL fl">
					<!--------h3---------------->
					<h3>收件信息<a href="#" class="fr">新增地址</a></h3>
					<!--------addres---------------->
					<div class="addres clearfix" id="addressVue">
						<div class="addre fl " v-bind:id="index" v-for="(address,index) in addressArray" v-on:click="changeClass(index)" v-if="address.defaultAddress != 1">
							<div class="tit clearfix">
								<p class="fl">
									{{address.realName}} {{address.defaultAddress}}
								</p>
							</div>
							<div class="addCon">
								<p>{{address.provinceWord}}&nbsp;{{address.cityWord}}&nbsp;{{address.areaWord}}</p>
								<p>{{address.cellphoneNum}}</p>
							</div>
						</div>

						<div class="addre fl on" v-bind:id="index" v-for="(address,index) in addressArray" v-on:click="changeClass(index)" v-if="address.defaultAddress == 1">
							<div class="tit clearfix">
								<p class="fl">
									{{address.realName}} {{address.defaultAddress}}
								</p>
							</div>
							<div class="addCon">
								<p>{{address.provinceWord}}&nbsp;{{address.cityWord}}&nbsp;{{address.areaWord}}</p>
								<p>{{address.cellphoneNum}}</p>
							</div>
						</div>
					</div>
					<h3>支付方式</h3>
					<!--------way---------------->
					<div class="way clearfix" id="payChannelDiv">
						<img id="pay1" class="on" src="/imgcart/temp/way01.jpg">
						<img id="pay2" src="/imgcart/temp/way02.jpg">
						<img id="pay3" src="/imgcart/temp/way03.jpg">
						<img id="pay4" src="/imgcart/temp/way04.jpg">
					</div>

					<h3>选择快递</h3>
					<!--------dis---------------->
					<div class="dis clearfix">
						<span class="on">顺风快递</span>
						<span>百世汇通</span>
						<span>圆通快递</span>
						<span>中通快递</span>
					</div>
				</div>
				<div class="orderR fr" id="goodsShow">
					<div class="msg">
						<h3>订单内容<a href="cart.html" class="fr">返回购物车</a></h3>
						<!--------ul---------------->
						<ul class="clearfix" v-for="good in goodInfo.goods">
							<li class="fl">
								<img src="/imgcart/temp/order01.jpg">
							</li>
							<li class="fl">
								<p>{{good.goodName}}</p>
								<p>数量：{{good.counts}}</p>
							</li>
							<li class="fr">￥{{good.amount}}</li>
						</ul>
					</div>
					<!--------tips---------------->
					<div class="tips">
						<p><span class="fl">商品金额：</span><span class="fr">{{goodInfo.amount}}</span></p>
						<p><span class="fl">优惠金额：</span><span class="fr">￥0.00</span></p>
						<p><span class="fl">运费：</span><span class="fr">免运费</span></p>
					</div>
					<!--------tips count---------------->
					<div class="count tips">
						<p><span class="fl">合计：</span><span class="fr">{{goodInfo.amount}}</span></p>
					</div>
					<!--<input type="button" name="" value="去支付"> -->
					<a href="###" onclick="pay()" class="pay">去支付</a>
				</div>
			</div>
		</div>
		<!--编辑弹框-->
		<!--遮罩-->
		<div class="mask"></div>
		<div class="adddz editAddre">
			<form action="#" method="get">
				<input type="text" placeholder="姓名" class="on" />
				<input type="text" placeholder="手机号" />
				<div class="city">
					<select name="">
						<option value="省份/自治区">省份/自治区</option>
					</select>
					<select>
						<option value="城市/地区">城市/地区</option>
					</select>
					<select>
						<option value="区/县">区/县</option>
					</select>
					<select>
						<option value="配送区域">配送区域</option>
					</select>
				</div>
				<textarea name="" rows="" cols="" placeholder="详细地址"></textarea>
				<input type="text" placeholder="邮政编码" />
				<div class="bc">
					<input type="button" value="保存" />
					<input type="button" value="取消" />
				</div>
			</form>
		</div>
		<!--返回顶部 gotop begin-->
		<jsp:include page="common/goTop.jsp"></jsp:include>
		<!-- 返回顶部gotop end----------------->
		<!------------------footer begin-------------------->
		<jsp:include page="common/fotter.jsp"></jsp:include>
		<!----------------------footer end-------------------------->
		<script type="text/javascript" src="/js/jquery-mini.js"></script>
		<script type="text/javascript" src="/js/vue-mini.js"></script>
		<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/pro.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			var mallOrderId = '${mallOrderId}';
			var checkedAddressId = -1;//定义被选择地址的id
			var checkedPayChannel = 1;//定义支付渠道
			
			var orderInfo = {};
			var addressInfo = {};
			
			$(document).ready(function () {
                orderInfo = new Vue({
					el : '#goodsShow',
					data : {
					    goodInfo : {}
					}
				});

                addressInfo = new Vue({
					el : '#addressVue',
					data : {
					    addressArray : []
					},
					methods:{
					    changeClass : function (index) {//通过节点选择器选择到被选中的节点增加class样式，并删除其余节点的class样式
							$('#' + index).addClass("on").siblings().removeClass("on");//默认情况下默认地址值为1 class样式中就带on
							checkedAddressId = this.addressArray[index].id;//将被选中的地址的Id记录下来
                        }
					}
				});

                loadOrderInfo();
                loadAddress();
                expressChannelChange();
                getPayChannel();
            });

			function pay() {
                $.ajax({
                    url:'/order/pay.do',
                    type:'POST', //GET
                    async:true,    //或false,是否异步
                    data: {
                        mallOrderId : mallOrderId,
                        checkedAddressId : checkedAddressId,
                        checkedPayChannel : checkedPayChannel
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

                        alert("订单生成OK，即将去支付"+ data.data.payOrderUrl);

                        //TODO 支付页面
                        window.location.href=data.data.payOrderUrl;

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
            
            function getPayChannel() {
                $.ajax({
                    url:'/pay/getPayChannle.do',
                    type:'POST', //GET
                    async:true,    //或false,是否异步
                    data: {
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

                        var channels = data.data;//从支付系统中将支付渠道拿到

						var msg = '';
						for (var i=0;i<channels.length;i++) {//遍历支付渠道
						    var channel = channels[i];
						    if (channel.payCode === 2) {
								msg += '<img id="'+channel.payCode+'" src="/imgcart/temp/way01.jpg">';
							}
							if (channel.payCode === 3) {
								msg += '<img id="'+channel.payCode+'" src="/imgcart/temp/way02.jpg">';
							}
							if (channel.payCode === 4) {
						        msg += '<img id="'+channel.payCode+'" src="/imgcart/temp/way05.png">';
							}
						}

						$('#payChannelDiv').html(msg);
						payChannelChange();
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

            /**
			 * 页面加载的时候就需要初始化该函数 选择支付方式 jQuery根据样式.way节点下面的 img节点 其中函数siblings 指移除兄弟姐妹样式上的on
             */
			function payChannelChange() {
				$('.way img').click(function () {
					$(this).addClass("on").siblings().removeClass("on");
                    checkedPayChannel = $(this).attr("id");
					alert(checkedPayChannel);
                });
            }
            
            function expressChannelChange() {
				$('.dis span').click(function () {
					$(this).addClass("on").siblings().removeClass("on");
                })
            }

            function loadAddress() {
                $.ajax({
                    url:'/address/listAddress.do',
                    type:'POST', //GET
                    async:true,    //或false,是否异步
                    data: {
                        mallOrderId : mallOrderId
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

                        addressInfo.addressArray = data.data;//将查出来的地址信息赋予给addressInfo.addressArray中
						for (var i=0;i<data.data.length; i++) {//判断默认地址
                            var addInfo = data.data[i];
                            if (parseInt(addInfo.defaultAddress) === 1) {
                                checkedAddressId = addInfo.id;
                            }
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

            function loadOrderInfo() {
                $.ajax({
                    url:'/order/loadOrderInfo.do',
                    type:'POST', //GET
                    async:true,    //或false,是否异步
                    data: {
                        mallOrderId : mallOrderId
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

                        //将从数据库中查出来的订单信息放到orderInfo.goodInfo中
                        orderInfo.goodInfo = data.data;

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
