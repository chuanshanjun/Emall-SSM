<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page contentType="text/html; charset=utf-8" %>

<%
    request.getRequestDispatcher("/malluser/toWelcome.do").forward(request, response);
%>