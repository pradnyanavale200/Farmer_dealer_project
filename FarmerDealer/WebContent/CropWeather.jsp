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
<%@include file="Backbutton.jsp" %>
</head>
<body>
<%!List<DisplayWeather> lst; %>
	
	<%
		if(!session.isNew())
		{
			lst=(List<DisplayWeather>)session.getAttribute("crop");
			session.invalidate();
		%>

		<div class="login-box">
			<h3>Enter Crop and Weather here to know information about it..</h3>
			<form action="CropWeatherController">
				<p>Enter Crop Name</p>
				<div class="select">
			<select id="cars" name="crop">
			<% for(DisplayWeather a:lst){	%>
			  <option value=<%=a.getCrop() %>><%=a.getCrop() %></option>  
			  
			<%
		}
			  %>
			</select>
			</div>
	
			  <p>Enter Weather</p>
			  <div class="select">
			<select id="cars" name="wea">
			
			  <option value="Sunny">Sunny</option>
			  <option value="Rainy">Rainy</option>
			  <option value="Winter">Winter</option>
			</select>
			</div>
			
			<%
			
	}
	%>
				<br>
				<br>
				<br>
				<input type="submit" name="submit" value="ok">
			</form>
			</div>
</body>
</html>