<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>部门信息列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@include file="/pages/context/context.jsp" %>
<b:base type="easyui,cookie,WdatePicker,Validform"></b:base>
<link rel="stylesheet" type="text/css" href="css/module.css">
<style type="text/css">
	body{ padding:0px; margin:0px;}
	.datagrid .panel-header{ margin: 1px; margin-bottom: 0px;}
	.datagrid .panel-body{ margin: 1px; margin-top: 0px;}
	ul li{list-style:none; height: 25px;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#dgGlptDept').datagrid({   
			iconCls:'icon-search',//图标 
			type: 'POST',
			width: 700, 
			height: 'auto',
            pageSize : 30,//默认选择的分页是每页5行数据  
            pageList : [10, 15, 20, 30, 40, 50],//可以选择的分页集合
            nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
            striped : true,//设置为true将交替显示行背景。  
            collapsible : false,//显示可折叠按钮  
            toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
            border: true,
            fit: true,//自动大小
            url:'glptDeptController.do?datagridPage',
            loadMsg : '数据装载中......',  
            singleSelect:false,//为true时只能选择单行  
            //fitColumns:true,//允许表格自动缩放，以适应父容器  
            //sortName : 'xh',//当数据表格初始化时以哪一列来排序  
            //sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。  
            remoteSort : false, 
            pagination : true,//分页  
            rownumbers : true
        });   
		//设置分页控件 
	    var p = $('#dgGlptDept').datagrid('getPager'); 
	    $(p).pagination({   
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录&nbsp;&nbsp;&nbsp;&nbsp;'
	    }); 
		
	});
	
	function QueryData() {
		var queryParams = $('#dgGlptDept').datagrid('options').queryParams; 
	    $("#dgGlptDept").datagrid('load');
	}
	
	function reject(){
	}
	
		
</script>
</head>
<body>
	<table id="dgGlptDept" class="easyui-datagrid" title="部门信息">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id', sortable:true" width="100"><label class="lab1">序列，自动增长</label></th>
				<th data-options="field:'deptCode', sortable:true" width="100"><label class="lab1">部门代号</label></th>
				<th data-options="field:'deptName', sortable:true" width="100"><label class="lab1">部门名称</label></th>
				<th data-options="field:'supDeptCode', sortable:true" width="100"><label class="lab1">上级部门</label></th>
				<th data-options="field:'status', sortable:true" width="100"><label class="lab1">状态</label></th>
				<th data-options="field:'remark', sortable:true" width="100"><label class="lab1">描述</label></th>
				<th data-options="field:'lrr', sortable:true" width="100"><label class="lab1">操作人</label></th>
				<th data-options="field:'lrrxm', sortable:true" width="100"><label class="lab1">操作人姓名</label></th>
				<th data-options="field:'lrrbm', sortable:true" width="100"><label class="lab1">操作人部门</label></th>
				<th data-options="field:'lrsj', sortable:true, formatter:formatDate" width="100"><label class="lab1">操作时间</label></th>
				<th data-options="field:'lrip', sortable:true" width="100"><label class="lab1">操作IP</label></th>
				<th data-options="field:'lrmac', sortable:true" width="100"><label class="lab1">操作MAC</label></th>
				<th data-options="field:'lastUpdate', sortable:true, formatter:formatDate" width="100"><label class="lab1">最后修改时间</label></th>
			</tr>
		</thead>
	</table>

	<div id="tb" style="display: none;">
		<!--<div style="padding: 10px 10px 10px 50px; width: 100%;">
			<ul>
				<li>
					石号：<input class="easyui-textbox" type="text" id="rawstoneid" name="rawstoneid" value="" style="width: 200px;"></input>&nbsp;&nbsp;&nbsp;&nbsp;
					形状：<input class="easyui-textbox" type="text" id="shape" name="shape" value="" style="width: 200px;"></input>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="QueryData()">查&nbsp;询&nbsp;</a>&nbsp;&nbsp;
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="reject()">重&nbsp;置&nbsp;</a>
				</li>
			</ul>
		</div>-->
		<div style="padding-bottom: 5px; padding-left: 10px;">
			<div style="padding-bottom: 5px; padding-left: 10px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="add('录入','icon-add', 700, 400, 'glptDeptController.do?saveorupdate')">新&nbsp;增&nbsp;</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="update('编辑','icon-add', 700, 400, 'glptDeptController.do?saveorupdate', 'dgGlptDept')">编&nbsp;辑&nbsp;</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="detail('查看','icon-add', 700, 400, 'glptDeptController.do?saveorupdate', 'dgGlptDept')">查&nbsp;看&nbsp;</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="del('glptDeptController.do?delete', 'dgGlptDept')">删&nbsp;除&nbsp;</a>
		</div>
		</div>
	</div>
</body>
</html>