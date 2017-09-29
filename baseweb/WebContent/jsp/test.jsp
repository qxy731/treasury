<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/comm.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type='text/javascript'>
$(function () {
	$("#test").bind("click",execution);
});

function execution(){
	alert("execution");
	var ajaxOptions = {
			type:'post',
			url:'${_CONTEXT_PATH}/pub/data-import!query.action',
			data:[{name:'queryIn.fileType',value:'1'}],
			dataType: 'json',
			success:function(result){
				alert("handleSuccess");
			},
			error:function(result){
				alert("handleError");
			},
			complete:function(result){
				alert("handleComplete");
			}
		};
		$.ajax(ajaxOptions);
}

</script>
</head>
<body>
<input type="button" id="test" name="test" value="测试按钮"></input>
</body>
</html>