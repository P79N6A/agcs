<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>消息发送</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@include file="/pages/context/context.jsp" %>
<b:base type="easyui,cookie,WdatePicker,Validform"></b:base>
<link rel="stylesheet" type="text/css" href="css/module.css">
<style type="text/css">
	
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$("#btn_send_normal").click(function(){
			var msg = $("#msg").val();
			$.ajax({
				async: false,
				cache: false,
				type: 'post',
				url: 'glptRocketMqController.do?sendNormalMq',
				data: {"msg":msg},
				dataType: 'json',
				success: function(data){
					tip(data.msg, 250, 100, 1000);
				}
			});
		});
		$("#btn_send_order").click(function(){
			var msg = $("#ordermsg").val();
			$.ajax({
				async: false,
				cache: false,
				type: 'post',
				url: 'glptRocketMqController.do?sendOrderMq',
				data: {"msg":msg},
				dataType: 'json',
				success: function(data){
					tip(data.msg, 250, 100, 1000);
				}
			});
		});
		$("#btn_send_transaction").click(function(){
			var msg = $("#transactionmsg").val();
			$.ajax({
				async: false,
				cache: false,
				type: 'post',
				url: 'glptRocketMqController.do?sendTransactionMq',
				data: {"msg":msg},
				dataType: 'json',
				success: function(data){
					tip(data.msg, 250, 100, 1000);
				}
			});
		});
	});
	
		
</script>
</head>
<body>
	<div style="margin: 0 auto;">
		<h2>RocketMQ普通消息测试</h2>
		<input type="text" id="msg" style="height: 24px"/>
		<input type="button" id="btn_send_normal" value="发送普通消息" style="height: 24px;"/>	
		
		<h2>RocketMQ顺序消息测试</h2>
		<input type="text" id="ordermsg" style="height: 24px"/>
		<input type="button" id="btn_send_order" value="发送顺序消息" style="height: 24px;"/>	
		
		<h2>RocketMQ事务消息测试</h2>
		<input type="text" id="transactionmsg" style="height: 24px"/>
		<input type="button" id="btn_send_transaction" value="发送事务消息" style="height: 24px;"/>	
		
	</div>
</body>
</html>