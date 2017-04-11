<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/ncrm-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增枚举参数</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
body {
	margin: 20px;
	width: 90%;
	height: 80%;
}

#Text_enumDesc {
	width: 250px;
}

.fclass {
	color: #FF0000
}

.div_bottom {
	margin-top: 10px
}

.table_btn td {
	padding: 1px 20px 1px 20px
}
</style>
<script type="text/javascript">
	function addEnum() {
		if (validate()) {
			try {
				var url = "${_CONTEXT_PATH}/sys/enum!insert.action";
				var data = $('#insertForm').serialize();
				Utils.ajaxSubmit(url, data, function(x) {
					$.dialogBox.alert(x.retMsg, function() {
						$.dialogBox.opener.queryData();
						$.dialogBox.close();
					});
				});
			} catch (e) {
			}
		}
		return false;
	}
	function clearScreen() {
		$('input').each(function(i, item) {
			item.value = '';
		});
	}
	function validate() {
		return $("#insertForm").valid();
	}
	function cancelDialog() {
		$.dialog.close();
	}
	$(function() {
		Utils.validateInit();
		$('#commit').bind('click', addEnum);
		$('#cancel').bind('click', cancelDialog);
	});
</script>

</head>
<body>
	<n:enums keys='yes_no' />
	<form id="insertForm">
		<table class="s1-params">
			<tr>
				<td><font class='fclass'>*&nbsp;</font>枚举编号</td>
				<td><input id="Text_enumId" name="po.enumId"
					validate="{required:true,maxlength:64}" /></td>
			</tr>
			<tr>
				<td><font class='fclass'>*&nbsp;</font>枚举名称</td>
				<td><input id="Text_enumName" name="po.enumName"
					validate="{required:true,maxlength:64}"></input></td>
			</tr>
			<tr>
				<td>枚举描述</td>
				<td><input id="Text_enumDesc" name="po.enumDesc"></input></td>
			</tr>
			<tr>
				<td><font class='fclass'>*&nbsp;</font>代码关联标志</td>
				<td><n:select codetype="yes_no" id="linkSrcFlag"
						name='po.linkSrcFlag' emptyOption="true"
						validate="{required:true,maxlength:32}"></n:select></td>
			</tr>
		</table>
		<div align="center" class="div_bottom">
			<table class="table_btn">
				<tr>
					<td><input id="commit" type="button" value="提&nbsp;&nbsp;交"
						class="l-button"></input></td>
					<td><input id="cancel" type="button" value="取&nbsp;&nbsp;消"
						class="l-button"></td>
				</tr>
			</table>
		</div>
		<!--<table class="s1-button">
	<tr>
		<td>
			<input id="commit" type="button" value="提交" class="nbutton"></input>
		</td>
		<td>
			<input id="cancel" type="button" value="取消" class="nbutton"></input>
		</td>
	</tr>
</table>
-->
	</form>
</body>
</html>