<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="CropFarmer.css">
</head>
<body>
<div>
<%@include file="NavCom.jsp" %>
<%@include file="Backbutton.jsp" %>
</div>
<br>

	<div class="login-boxi">
			<h1>Crop Input</h1>
			<form action="CropInputController">
				<p>Crop Name</p>
				<input type="text" name="crop" placeholder="Enter Cropname" autocomplete="off" required>
				<p>Quantity in kg</p>
				<input type="text" name="wgt" placeholder="Enter quantity" autocomplete="off" required>
				<br>
				<br>
				<br>
				<input type="submit" name="submit" value="ok">
			</form>
			</div>
</body>
</html>