<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int id = 1;
    if (session.getAttribute("ID") != null) {
        id = (Integer) session.getAttribute("ID") + 1;
        session.setAttribute("ID",new Integer(id));
    }
    else{
        session.setAttribute("ID",new Integer(1));
    }
%>
<label class='awidget_head' style="height:24px;line-height:24px;width:100%;text-align:right;"><%=id %>描述文字</label>