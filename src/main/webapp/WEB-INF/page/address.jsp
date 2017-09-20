<%@page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>最家</title>
		<link rel="stylesheet" type="text/css" href="/csscart/public.css"/>
		<link rel="stylesheet" type="text/css" href="/csscart/mygxin.css" />
		<link rel="stylesheet" type="text/css" href="/csscart/jquery-ui-css.css" />
	</head>
	<body>
		<!------------------------------head------------------------------>
		<jsp:include page="common/header.jsp"></jsp:include>
		<!------------------------------idea------------------------------>
		<div class="address mt">
			<div class="wrapper clearfix">
				<a href="index.html" class="fl">首页</a>
				<span>/</span>
				<a href="mygxin.html">个人中心</a>
				<span>/</span>
				<a href="address.html" class="on">地址管理</a>
			</div>
		</div>
		
		<!------------------------------Bott------------------------------>
		<div class="Bott">
			<div class="wrapper clearfix">
				<jsp:include page="common/leftMenu.jsp"></jsp:include>
				<div class="you fl" id="addreshow">
					<h2>收货地址</h2>
					<div class="add">
						<div>
							<a href="#2" id="addxad"><img src="/imgcart/jia.png"/></a>
							<span>添加新地址</span>
						</div>
					</div>

					<div class="add"  v-for="(address, index) in addresses">
						<div>
							<p>{{address.realName}}</p>
							<p>{{address.cellphoneNum}}</p>
							<p>{{address.provinceWord}} {{address.cityWord}} {{address.areaWord}}</p>
							<p>{{address.accurateAddress}}</p>
							<span v-on:click="modifyAdd(index)">修改</span>
							<span v-on:click="delAdd(index)">删除</span>
						</div>
					</div>

				</div>
			</div>
		</div>
		<!--编辑弹框-->
		<!--遮罩-->
		<div class="mask"></div>
		<div class="adddz">
			<form action="#" method="get">
				<input type="text" placeholder="姓名" class="on" id="realName"/>
				<input type="text" placeholder="手机号" id="phone"/>
				<div class="city">
					<select name="" id="addProvince" onchange="listCity();">
						<option value="xxx">省份/自治区</option>
					</select>
					<select id="addCity" onchange="listArea()">
						<option value="xxx">城市/地区</option>
					</select>
					<select id="addArea">
						<option value="xxx">区/县</option>
					</select>
				</div>
				默认地址:<br>
				否：<input type="radio" id="xx" name="defAdd" value="2" style="width: 15px;height: 15px">
				是：<input type="radio" id="xxx" name="defAdd" value="1" style="width: 15px;height: 15px">

				<textarea name="" rows="" cols="" id="detailAddress" placeholder="详细地址"></textarea>
				<input type="text" placeholder="邮政编码" />
				<div class="bc">
					<input type="button" id="save" value="保存" />
					<input type="button" value="取消" />
				</div>
			</form>
		</div>
		<div class="readd">
			<form action="#" method="get">
				<input type="text"  class="on" value="六六六" />
				<input type="text" value="157****0022" />
				<div class="city">
					<select name="">
						<option value="省份/自治区">河北省</option>
					</select>
					<select>
						<option value="城市/地区">唐山市</option>
					</select>
					<select>
						<option value="区/县">路北区</option>
					</select>
					<select>
						<option value="配送区域">火炬路</option>
					</select>
				</div>
				<textarea name="" rows="" cols="" placeholder="详细地址">高新产业园</textarea>
				<input type="text" placeholder="邮政编码" value="063000"/>
				<div class="bc">
					<input type="button" value="保存" />
					<input type="button" value="取消" />
				</div>
			</form>
		</div>
		<!--返回顶部-->
		<jsp:include page="common/goTop.jsp"></jsp:include>

		<div id="del-confirm" title="删除地址">

		</div>
		
		<!--footer-->
		<jsp:include page="common/fotter.jsp"></jsp:include>

		<script type="text/javascript" src="/js/jquery-mini.js"></script>
		<script type="text/javascript" src="/js/vue-mini.js"></script>
		<script type="text/javascript" src="/js/jquery-ui-js.js"></script>
		<script type="text/javascript">
			//新增加的jquery弹框首先加载依赖的js库,还有ui就是样式也要加载过来
			var actionFlag = false;
			var modifyAddressId = -1;
			var showAddress = {};
			var delAddressIndex = -1;

			$(document).ready(function () {

                showAddress = new Vue({
					el : '#addreshow',
					data : {
					    addresses : []
					},
					methods : {
					    modifyAdd : function (index) {
							$('.mask').show();
							$('.adddz').show();
							var address = this.addresses[index];
                            initModify(address);
							actionFlag = false;
                        },
						delAdd : function (index) {
							var address = this.addresses[index];//this指的是showAddress通过索引获取要删除的数据
                            delAddressIndex = index;//删除的时候跳对话框出来并且显示名字和手机号码
                            $('#del-confirm').html(address.realName + '-' + address.cellphoneNum);//渲染好对话框之后在打开
                            $('#del-confirm').dialog("open");//使用了jQuery的对话框框架，不要忘记添加js文件
                        }
					}
				});

				$('#addxad').click(function () {
                    initAddAddress();//每次重新点开新增按钮时候，都需要重置数据
				    $('.mask').show();//展现幕布
					$('.adddz').show();//展现增加地址窗口

					listProvince();//遍历省份
					actionFlag = true;//这边是新增所以置为true
                });

				$('.bc>input').click(function () {//当bc样式下的任意一个input被点击那么另mask和adddz页面消失
					$('.mask').hide();
					$('.adddz').hide()
                });

				$('#save').click(function () {
//					add();
					choseAction();
                });

				loadAddress();
				initDelDialog();//初始化对话框内容
            });
			
			function initDelDialog() {
				$('#del-confirm').dialog({
					resizable:false,//可调整大小
					height:140,
					modal:true,//模态窗口
					autoOpen:false,//自动打开dialog
					buttons:{
					    "删除":function () {
							del();//点击删除按钮执行删除函数
							$(this).dialog("close");//关闭对话框
                        },
						"取消":function () {
							$(this).dialog("close");
                        }
					}
				});
            }
            
            function del() {
				var delAdd = showAddress.addresses[delAddressIndex];//通过索引得到要删除的对象

                $.ajax({
                    url:'/address/del.do',
                    type:'POST', //GET
                    async:false,    //或false,是否异步
                    data:{
                        addressId : delAdd.id
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
                        //loadAddress();修改成功后需要使用vue重新展示下，但是不推荐这么做因为这会访问一次数据库浪费资源，尽量在前台操作
						//所以将数组中的一条数据删除再次遍历数组节省资源
						showAddress.addresses.splice(delAddressIndex, 1);
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
			
			function choseAction() {
				if (actionFlag) {
				    add();//看FLAG选择是新增还是修改
				} else {
				    modify();
				}
            }
            
            function modify() {
                $.ajax({
                    url:'/address/modify.do',
                    type:'POST', //GET
                    async:false,    //或false,是否异步
                    data:{
                        realName : $('#realName').val(),
                        cellphoneNum : $('#phone').val(),
                        provinceCode : $('#addProvince').val(),
                        cityCode : $('#addCity').val(),
                        areaCode : $('#addArea').val(),
                        accurateAddress : $('#detailAddress').val(),
                        defaultAddress : $('input:radio[name="defAdd"]:checked').val(),
                        id : modifyAddressId
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
                        loadAddress();//修改成功后需要使用vue重新展示下

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

			function initModify(address) {
                alert('修改！！！！');
			    listProvince();//因为重新弹出了窗口需要重新遍历下
				$('#realName').val(address.realName);//并且把个人数据填充上去
				$('#phone').val(address.cellphoneNum);
				$('#addProvince').val(address.provinceCode);
				alert(address.provinceCode);

				listCity();
				$('#addCity').val(address.cityCode);

				listArea();
				$('#addArea').val(address.areaCode);
				$('#detailAddress').val(address.accurateAddress);

				if ('默认' === address.defaultAddress) {
					$('input:radio[name="defAdd"][value="1"]').prop('checked', true);
				} else {
				    $('input:radio[name="defAdd"][value="2"]').prop('checked', true);
				}

                modifyAddressId = address.id;//记录修改的id
            }

			function loadAddress() {
                $.ajax({
                    url:'/address/listAddress.do',
                    type:'POST', //GET
                    async:false,    //或false,是否异步
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

                        showAddress.addresses = data.data;//将后台传输的数据赋值

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

			function add() {
                $.ajax({
                    url:'/address/addAddress.do',
                    type:'POST', //GET
                    async:false,    //或false,是否异步
                    data:{
                        realName:$('#realName').val(),
                        cellphoneNum:$('#phone').val(),
                        provinceCode:$('#addProvince').val(),
                        cityCode:$('#addCity').val(),
                        areaCode:$('#addArea').val(),
                        accurateAddress:$('#detailAddress').val(),
                        defaultAddress:$('input:radio[name="defAdd"]:checked').val()
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
                        loadAddress();//增加一个地址后需要再次再页面显示，然后数据改变vue会动态加载

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

			function initAddAddress() {
				$('#realName').val('');
				$('#phone').val('');
				$('#addProvince').val('xxx');//xxx代表默认的"省市区"
				$('#addCity').val('xxx');
				$('#addArea').val('xxx');
				$('#detailAddress').val('');
            }

			function listProvince() {
                $.ajax({
                    url:'/address/listProvince.do',
                    type:'POST', //GET
                    async:false,    //或false,是否异步
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

						var result = data.data;
                        var msg = '';//拿到数组直接遍历
                        for (var i=0;i<result.length;i++){
							var pro = result[i];
							msg += '<option value="'+pro.code+'">'+pro.name+'</option>';
						}

						$('#addProvince').append(msg);//去到上面的ID渲染，用Html的话会把原来的option value="xxx">省份/自治区</option>覆盖掉
//						$('#addProvince').html(msg);//去到上面的ID渲染

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

            function listCity() {

			    $.ajax({
                    url:'/address/listCity.do',
                    type:'POST', //GET
                    async:false,    //或false,是否异步
                    data:{
                        provinceCode:$('#addProvince').val()
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

                        var result = data.data;
                        var msg = '<option value="xxx">城市/地区</option>';//拿到数组直接遍历，给个默认值这样，省地址变动成默认值我这不会变空值，而且我这样我每次下拉菜单的时候还有默认的<option value="xxx">城市/地区</option>
                        for (var i=0;i<result.length;i++){
                            var pro = result[i];
                       		msg += '<option value="'+pro.code+'">'+pro.name+'</option>';
                        }

                        $('#addCity').html(msg);//去到上面的ID渲染
                        $('#addArea').html('<option value="xxx">区/县</option>');//每次刷新City数据时将Area区域数据充值
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

            function listArea() {
                $.ajax({
                    url:'/address/listArea.do',
                    type:'POST', //GET
                    async:false,    //或false,是否异步
                    data:{
                        cityCode:$('#addCity').val()
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

                        var result = data.data;
                        var msg = '<option value="xxx">区/县</option>';//拿到数组直接遍历
                        for (var i=0;i<result.length;i++){
                            var pro = result[i];
                            msg += '<option value="'+pro.code+'">'+pro.name+'</option>';
                        }

                        $('#addArea').html(msg);//去到上面的ID渲染

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
