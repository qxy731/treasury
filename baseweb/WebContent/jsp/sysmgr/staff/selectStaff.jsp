<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/ncrm-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/comm.jsp"></jsp:include>
<style type="text/css">
.queryBox {padding: 5px;margin: 2px;border: 1px solid #dddddd;}
.queryBox table {border: 0;width:100%;}
.queryBox table tr td {min-width: 100px; padding-left: 10px;}
.queryBox table tr td {min-width: 100px;}
.queryBox select {width: 131px;}
#reset,#query {width: 80px;margin-left: 100px;}
</style>
</head>
<body>
<s:debug />
<n:enums keys='valid_type'/>
<table cellpadding="5">
	<tr>
		<td>
		<fieldset class="queryBox"><legend>查询条件</legend>
		<form id="myform" action="${_CONTEXT_PATH}/Test!query.action">
		 <table class='params'>
            <tr>
                    <td>员工ID </td><td><input type='text' id='staffId' name='staffId'/></td>
                                        <td>员工姓名 </td><td><input type='text' id='staffName' name='staffName'/></td>
                                        <td><input id='query' name='query' type="button" value="查询" /></td>
                            </tr>
                        <tr>
                     <td>员工级别 </td><td><input type='text' id='staffLevel' name='staffLevel'/></td>
                    <td>员工状态 </td><td><n:select codetype="valid_type" id="staffStatus" name='queryIn.staffStatus' emptyOption="true" disabled="false"></n:select></td>
                    <td><input id='reset' name='reset' type="button" value='重置' /></td>
                     </tr>
        </table>
		
		</form>
		</fieldset>
		</td>
	</tr>
	<tr>
		<td>
		   <div id='stafflist'>
		       <table>
		          <tr>
		             <td>员工编号</td><td>员工姓名</td><td>员工级别</td><td>员工性别</td><td>员工所属组织</td>
		          </tr>
		          
		          <tr>
		             <td>1</td><td>1</td><td>1</td><td>1</td><td>1</td>
		          </tr>
		       </table>
		   </div>
		</td>
	</tr>
</table>
</body>

</html>