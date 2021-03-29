<%@page import="com.Model.DisplayWeather"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="cropdesign.css">
</head>
<body>
<%!List<DisplayWeather> lst; %>
	
	<%
		if(!session.isNew())
		{
			lst=(List<DisplayWeather>)session.getAttribute("weather");
			session.invalidate();
	
		for(DisplayWeather a:lst){
	%>
	<div class="login-boxi">
		<h5><%=a.getCropInfo() %></h5>
		<br>
		<h5><%=a.getCare() %></h5>
		</div>
	
	<%
		}
	}
	%>
	<div class="but">
	<form action="CropDispWeatherController">
	<input type="submit" value="OK">
</form>
</div>
	

</body>
</html>