<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>珠鼻子管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@include file="/pages/context/context.jsp" %>
<b:base type="easyui,cookie"></b:base>
<style type="text/css">
	body{ padding:0px; margin:0px;}
	.tabs-header{border-left: 0px; border-top: 0px;}
	.tabs-panels{border-left: 0px; border-bottom: 0px;}
	.accordion{border: 0px;}
	#title {height: 30px; background: url(images/dtbg.jpg) repeat-x;}
	#sptitle {background: url(images/dtico3.gif) no-repeat 7px center; line-height: 30px; padding-left: 27px; color: #fff;}
	#menu {background: url(images/dmbg.jpg) repeat-x;  height: 19px;}
	#menu_par li {list-style: none; display: inline; padding-left: 8px;}
	#menu_par a { padding: 1px 3px 3px 3px;text-decoration: none; color: #000;} 
	#tool {background: #d8e3f3;height: 42px; border-top: #fff 1px solid; border-bottom:  1px solid #95B8E7;}
	#tool a {display: block;text-align: center; padding: 16px 7px 0px 7px; text-decoration: none;
  			color: #000; width: 55px;
	}
	#welcome li {height: 20px;list-style-type: none;}
	#head_fun ul{margin-top: 6px;}
	#head_fun li a{color: #b9ccda;margin-left: 15px;}
	#head_fun li{height: 20px;width: 50px;float: left;list-style-type: none;color: #b9ccda;
		line-height: 11px;background: url(images/listLine.png) no-repeat;}
	#tright li {float: left;list-style-type: none;background: url(images/dtline.jpg) no-repeat left top;}
	#tright .ra1 {background: url(images/dthom.gif) no-repeat top center;}
	#tright .ra2 {background: url(images/dmemp.gif) no-repeat top center;}
	#tright .ra3 {background: url(images/dtext.gif) no-repeat top center;}
	#tright a { width: 35px;}
	#menu_par a:hover{background-color: rgb(242, 219, 168);}
	.tree-node-selected {background: #F5F3ED;}
	.l-btn-text{color:#b9ccda;}
</style>
<script type="text/javascript">
	var nodeList;
	var idx = 0;
	
	function endWith(str, split){
		var m = str.substr(str.length-1, 1);
		if(m == split){
			return true;
		}
		return false;
	}
	
	$(document).ready(function(){
		<%-- $('#ul_tree').tree({
			method: 'post',
			///pages/tree_data2.json
			url: 'glpt/menu_getMenu.action',
			loadFilter: function(data){
				return treeFilter(data);
			}
		}); --%>
		//查询用户菜单节点
		getNode();
		//加载菜单
		for(var i = 0; i < nodeList.length; i++){
			var node = nodeList[i];
			if(node.supNodeCode == '0'){
				if(idx == 0){
					addPanel(node.node_name, node.nodeIcon, true);
				}else{
					addPanel(node.node_name, node.nodeIcon, false);
				}
				var jsonobj = eval(getSonNodeLsit(node.nodeCode));
				$('#ul_tree'+idx).tree({data: jsonobj});
			}
		}
		treeClick("首页", 'pages/main/index.jsp', false, 'icon-home');
		$(".easyui-tree").each(function(){
			$(this).tree({
				onClick: function(node){
					if(node.url != null && node.url != ""){
						treeClick(node.text, node.url+"&icon="+node.iconCls, true, node.iconCls);
					}
				}
			});
		});
		
		$.extend($.fn.validatebox.defaults.rules, {    
		    equals: {    
		        validator: function(value,param){    
		            return value == $(param[0]).val();    
		        },    
		        message: '两次密码不一致'   
		    }    
		}); 
	});
	
	function treeFilter(data){
		var result = getNode();
		return result;
	}
	
	function getNode(){
		if(nodeList == null){
			$.ajax({
				cache:false,
				async:false,
				type:'post',
				url: 'glptNodeController.do?getNodePrivilege',
				success:function(data){
					var d = $.parseJSON(data);
					if (d.success) {
						nodeList = eval(d.msg);
					} else {
						alert(d.msg);
						return false;
					}
				},
				error: function(){
					alert("系统异常");
					return false;
				}
			});
		}
		return nodeList;
	}
	
	function addPanel(title, icon, open){
		idx++;
		$('#nodePanel').accordion('add',{
			title:title,
			iconCls: icon,
			selected: open,
			content:'<div style="padding:10px;"><ul id="ul_tree'+idx+'" class="easyui-tree"></ul></div>'
		});
		
	}
	
	function getSonNodeLsit(parentNode){
		var tree = "[";
		if(parentNode != null && nodeList != null){
			for(var i = 0; i < nodeList.length; i++){
				var node = nodeList[i];
				if(parentNode == node.supNodeCode){
					tree = tree+"{";
					tree = tree+"'id':'"+node.nodeCode+"'";
					tree = tree+",'text':'"+node.node_name+"'";
					tree = tree+",'iconCls':'"+node.nodeIcon+"'";
					if(node.nodeUrl != null && node.nodeUrl != ''){
						tree = tree+",'url':'"+node.nodeUrl+"'";
					}
					//判断是否有子节点
					var sonTree = getSonNodeLsit(node.nodeCode);
					if(sonTree != null && sonTree.length > 0 ){
						tree = tree+",'children':"+sonTree;
					}
					tree = tree+"},";
				}
			}
		}
		if(endWith(tree, ",")){
			tree = tree.substring(0,tree.length-1);
		}
		tree = tree+"]";
		return tree;
	}
	
	function treeClick(title, url, closable, icon){
		if($("#tabs_div").tabs('exists', title)){
			$('#tabs_div').tabs('select', title);
		}else{
			$('#tabs_div').tabs('add',{
				title: title,
				content: createFrame(url),
				closable: closable,
				iconCls: icon,
				cache:true 
			});
		}
	}
	function createFrame(url) {
		var heigt = document.body.clientHeight;
		heigt = heigt-110;
		var s = '<iframe name="mainFrame" id="mainFrame" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:'+heigt+'px;"></iframe>';
		return s;
	}
	
	function logout(){
		if(confirm("确定要退出系统!")){
			window.location.href = "loginController.do?logout";
		}
	}
	
	function updatePwd(){
		if($("#updatePwd").form("validate")){
			var oldpwd = $("#oldpwd").val();
			var newpwd = $("#newpwd").val();
			var confirmpwd = $("#confirmpwd").val();
			$.ajax({
				cache:false,
				async:false,
				type:'post',
				url: 'glpt/user_updatePwd.action',
				data: {"oldpwd":oldpwd, "newpwd":newpwd},
				dataType: 'json',
				success: function(result){
					var json = eval(result);
					var code = json.code;
					if(code == '0000'){
						alert(json.msg);
						closeDialog();
					}else if(code == '0002'){
						exception(json.msg);
					}else{
						alert(json.msg);
					}
				},
				error: function(){
					alert("系统异常，请稍候再试");
				}
			});
		}
	}
	/**
	 *修改系统风格
	*/
	function changeStyle(themes){
		$.ajax({
			cache: false,
			async: false,
			type: 'post',
			url: 'glptUserController.do?saveThemes',
			data: {'themes': themes},
			success: function(data){
				var d = $.parseJSON(data);
				if(d.success){
					$.messager.alert('提示', d.msg, 'info');
				}else{
					$.messager.alert('提示', d.msg, 'error');
				}
			},
			error: function(){
				alert("系统异常，请稍候再试");
			}
		});
	}
	
	function closeDialog(){
		$('#dialog').dialog('close');
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'north'" style="height:50px;border-top: 0px; border-bottom: 0px; background: url(images/header_bg.png) repeat-x; background-position: 100% -50px; overflow: hidden;">
			<div style="width: 50%;height: 50px; background: url(images/header_bg.png) repeat-x; float: left">
				<div style="width: 200px; margin-top: 6px; padding-left: 20px; float: left;">
					<img src="images/logo.png"/>
				</div>
				<div id="welcome" style="width: 130;  margin-top: 5px; padding-left: 20px; float: left; color: #b9ccda;">
					<ul>
						<li>欢迎您：<b>${userName}</b>&nbsp;&nbsp;[角色：${role.roleName }]</li>
						<li>响应IP：${ip}<b:div></b:div></li>
					</ul>
				</div>
			</div>
			<div id="head_fun" style="width: 49%;height: 50px; float: left;">
				<div style="width: 64%; height:48px; float: left;"> 
					<div style="color: #b9ccda;">
						JSP直接绑定国际化：<spring:message code="welcome" /><br/>
						后台取值国际化：${title }
					</div>
				</div>
				<div style="width: 24%; height:48px; float: left;">
					<ul>
						<li><a href="javasrcript: void(0);" target="_self" onclick="treeClick('首页', 'pages/inc/main.jsp', false);">首页</a></li>
						<li><a href="javasrcript: void(0);" onclick="openDialog('修改密码', 'icon-save', 500, 290, 'pages/glpt/updatePwd.jsp', true);">密码</a></li>
						<li><a href="javascript: logout();">退出</a></li>
					</ul>
				</div>	
				<div style="width: 12%; height:48px; float: left;">
					 <a href="#" class="easyui-menubutton" data-options="menu:'#mm1',iconCls:'icon-edit'">主题</a>
				</div>			
			</div>
			
		</div>
		<div data-options="region:'south'" style="height:30px; text-align: center; padding-top: 7px; padding-right: 30px; background: #F5F3F3;">
			Copyright©2015   深圳市爱度网联科技股份有限公司
		</div>
		<div id="menu_tree" data-options="region:'west',split:true" title="导航菜单" style="width:200px;">
			<div id="nodePanel" class="easyui-accordion"  style="width:100%;height:auto;">
			</div>
		</div>
		<div data-options="region:'center'">
			<div id="tabs_div" class="easyui-tabs" style="width:100%;height:100%; border-left: 0px;" >
				
			</div>
	    </div>
	    <div id="mm1" style="width:150px;">
			<div onclick="javascript:changeStyle('default')">经典风格</div>
			<div class="menu-sep"></div>
			<div onclick="javascript:changeStyle('black')">黑色风格</div>
			<div class="menu-sep"></div>
			<div onclick="javascript:changeStyle('bootstrap')">bootstrap风格</div>
			<div class="menu-sep"></div>
			<div onclick="javascript:changeStyle('gray')">灰色风格</div>
		</div>
	</div>
</body>
</html>