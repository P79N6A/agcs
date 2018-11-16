<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@include file="/pages/context/context.jsp" %>
<html>
<head>
<title>爱度平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/index.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="plugin/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="plugin/jquery/cookie.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#code").click(function(){
			$("#img").show();
		});
		$("#code").keyup(function(){
			$("#img").show();
		});

		var yhzh = Cookies.get("yhzh");
		$('#username').val(yhzh);
	});
	
	function reloadImage(url) {
		var img = document.getElementById("img");
		img.src = url + "?Code=" + Math.random();
	}
	
	document.onkeydown = function(e) {
		if (!e)
			e = window.event;
		if ((e.keyCode || e.which) == 13) {
			checklogin(); //转到验证的函数
		}
	}
	
	function resetlogin(){
		$("#username").val("");
		$("#password").val("");
		$("#code").val("");
	}
	
	function checklogin() {
		//window.location.href = 'pages/main/frame.jsp';
		var cusername = $("#username").val();
		var cpassword = $("#password").val();
		var ccode = $("#code").val();
		var nullReg = /[^\f\n\r\t\v]/;
	
		if (!nullReg.test(cusername)) {
			alert("请输入用户名!");
			$("#username").focus();
			return false;
		} else if (!nullReg.test(cpassword)) {
			alert("请输入密码!");
			$("#password").focus();
			return false;
		} else if (!nullReg.test(ccode)) {
			alert("请输入验证码!");
			$("#code").focus();
			return false;
		}

		var date=new Date();
		date.setTime(date.getTime()+3*24*60*60*1000);
		Cookies.set("yhzh",cusername);
		$.ajax({
			cache:false,
			async:false,
			type:'post',                                                                                                                                                                                                                                                                                                                            
			url: "loginController.do?checkuser",
			data: {userCode:cusername,password:cpassword,round:ccode},
			success:function(data){
				var d = $.parseJSON(data);
				if (d.success) {
					window.location.href = 'loginController.do?login';
				} else {
					alert(d.msg);
					reloadImage('pages/login/img.jsp');
					return false;
				}
			},
			error: function(){
				alert("登陆超时,请重新登陆");
				reloadImage('pages/login/img.jsp');
				return false;
			}
		});
	}

	function exception(content){
		art.dialog({
			width:'50%',
			content: content,
			title: '系统异常',
			cancelVal: '关闭',
			cancel: true,
			lock: true,
			opacity: 0.4,
			icon: 'error'
		});
	}
	
</script>
</head>
<body style="background-color:#000;">
<div class="login_body">
    <div class="index_cnt">
        <div class="login_head">
        	<img class="logo" src="images/login_logo.png" alt="猪鼻子" />
            <div class="head_title">
            	<p class="p1">爱度平台</p>
                <p class="p2">doido platform</p>
            </div>
        </div>
        <div class="login_box">
        	<h3>用户登录</h3>
            <p class="h_entitle">userlogin</p>
            <p class="p_name">用户名</p>
            <p><input type="text" name="user_code" id="username" class="input_name" /></p>
            <p class="p_name">密码</p>
            <p><input type="password" name="password" id="password" class="input_passkey" /></p>
            <p class="p_yzm">
            	<input name="code" type="text" id="code" value="验证码" onfocus="if(this.value == '验证码'){this.value = ''}" onblur="if(this.value == ''){this.value = '验证码'}"/>
            	<img id="img" onclick="javascript:reloadImage('pages/login/img.jsp');" title="点击刷新" 
									style="cursor: pointer; width: 94px; height: 36px;" border="0" src="pages/login/img.jsp" />
			</p>
            <p><input class="input_subm" type="button" value="立即登录" onclick="checklogin();"/></p>
            <!-- <p class="p_wjma"><a href="/">快速注册</a><span>|</span><a href="/">忘记密码</a></p> -->
        </div>
        <div class="login_foot">
        	<p class="p1">珠宝行业第一家基于真实交易的钻石供应链金融服务平台</p>
            <p class="p2">地址：深圳市罗湖区翠竹路维平珠宝大厦12栋五楼东  Copyright © 2015 - 2016 深圳市爱度网联科技股份有限公司  All Rights Reserved   粤ICP备08006843号</p>
        </div>
    </div>
</div>
</body>
</html>
