<%@page import="com.Model.DisplayAllData"%>
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
<body bgcolor=#f0f0f5>
<div>
<%@include file="NavCom.jsp" %>
<%@include file="Backbutdealer.jsp" %>
</div>

	<br>
	<div id="d1"><h3 style="text-align: left;">List of All Accounts</h3></div>
	<%!List<DisplayAllData> lst; %>
	
	<%
		if(!session.isNew())
		{
			lst=(List<DisplayAllData>)session.getAttribute("AllAcc");
			session.invalidate();
	%>
	<table align="left">
	<tr>
		<td>Farmer ID</td>
		<td>Crop Name</td>
		<td>Quantity in Kg</td>
	</tr>
	<%
		for(DisplayAllData a:lst){
	%>
	<tr>
		<td><%=a.getF_id() %></td>
		<td><%=a.getCrop() %></td>
		<td><%=a.getWgt() %></td>
	</tr>
	<%
		}
	%>
	</table>
	
	<%
		}
	%>
	
	<div class="login-box">
	<form action="MailTofarmController">
				<p>Farmer Id         </p>
				
			<select id="cars" name="farmid" placeholder="Enter Farmer Id" autocomplete="off">
			<% for(DisplayAllData a:lst){	%>
			  <option value=<%=a.getF_id() %>><%=a.getF_id() %></option>  
			  
			<%
		}
			  %>
			</select>
			

				<p>Enter Your Mobile No.</p>
				<div class="select">
				<input type="text"  pattern="[789][0-9]{9}" class="resizedTextbox" name="mobno" placeholder="Your phone no." title="phone no. must be of 10 digit and always start from 7 or 8 or 9" id="mono" autocomplete="off" required>
				
				</div>
				<br>
				<br>
				<p>After clicking ok mail will be sent to that farmerId.</p>
				<br>
				<input type="submit" name="submit" value="ok">
	</form>
	</div>
	
</body>
</html>