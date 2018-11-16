<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>${funDescription}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@include file="/pages/context/context.jsp" %>
</head>
<body>
	<div class='div_center'>
		<form id="${entityName?uncap_first}Form" class="wraper" action="${entityName?uncap_first}Controller.do?save" method="post">
			<input id="btn_sub" name="btn_sub" type="hidden"/>
			<input id="id" name="id" type="hidden" value="${'$'}{${entityName?uncap_first}Page.id }">
			<table class='edittable' style='width: 90%;' cellpadding='0' cellspacing='0'>
				<#list originalColumns as po>
				<#if po.filedName != 'id'>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">${po.filedComment}:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="${po.filedName}" name="${po.filedName}" <#if po.nullable == 'N'>datatype="*" nullmsg="请输入${po.filedComment}！"</#if> <#if po.filedType == 'java.util.Date'>onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${'$'}{${entityName?uncap_first}Page.${po.filedName}}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>" <#else>value="${'$'}{${entityName?uncap_first}Page.${po.filedName}}"</#if> ></input>
					</td>
				</tr>
				</#if>
				</#list>
			</table>
			<script type="text/javascript">
				${'$'}(function(){
					var cztype = '${'$'}{cztype}';
					if(cztype == 'detail'){
						${'$'}(":input").attr("disabled","true");
					}
					${'$'}("#${entityName?uncap_first}Form").Validform({
						tiptype:3,
						btnSubmit:"#btn_sub", 
						ajaxPost:true,
						callback: function(data){
							if(data.success == true){
								//tip(data.msg);
								setTimeout(function(){
									window.location.href = "${entityName?uncap_first}Controller.do?${entityName?uncap_first}";
								}, 500);
							}else{
								if(data.msg == null || data.msg == "" || data.msg == undefined){
									tip(data.status+":"+data.statusText, 250, 100, 0);
								}else{
									tip(data.msg, 250, 100, 3000);
								}
								
							}
						}
					});
				});
			</script>
		</form>
	</div>
</body>
</html>