<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="exam.Hello"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Hello</title>
</head>
<body>
<%
	ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(
			request.getSession().getServletContext());
	
// 	for(String s : context.getBeanDefinitionNames()){
// 		out.println(s+"<br>");
// 	}
	
	Hello hello = context.getBean(Hello.class);
	
	out.println(hello.sayHello());

%>
</body>
</html>