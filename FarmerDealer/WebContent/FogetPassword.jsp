<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function nospacetxt(x)
		{
			if((x.keyCode == 32))
				{
					alert("No space and number allowed");
					return false;
				}
			return true;
		}
</script>
<style>
.header{
	padding: 14px;
	text-align:center;
	background-color: #0003;
	color:#ffffff;
	font-size: 15px;
	font-weight:bold;
}
.btn{
	padding:15px 20px;
	background-color: #ffad33;
	border:none;
	width:210px;
	color:white;
	border-radius:32px;
	font-size:18px;
	text-decoration:none;
	font-weight:bold;
	transition: all .3s ease-in;
	}
.lower-container{
	height:400px;
	background: #fff;
	padding:70px;
	padding-top:129px;
	padding-left:60px;
}	
td{
	font-size:24px;
}
.resizedTextbox 
{width: 350px; height: 30px}

</style>
</head>
<body>
	<div class="header">
	<h2>Forget Password</h2>
	</div>
	<br>
	<div class="lower-container">
		<form action="ForgetPasswordController" method="post">
		<br>
		<br>
		
		
		<table align="center">
			<tr>
				<td>Enter Username : </td>
				<td><input type="text" name="uname" class="resizedTextbox" autocomplete="off"  onkeydown="return nospacetxt(event)" required ></td>
			</tr>
			<tr>
				<td>Enter Email id : </td>
				<td><input type="text" name="email" pattern="[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Email-id must be in format characters@characters.domain(for eg:name@gmail.com)" class="resizedTextbox"autocomplete="off" required></td>
			</tr>
		</table>
		
			<h1 style="text-align: center;"><input type="submit" name="save" value="Submit" class="btn"></h1>
		</form>
		</div>
</body>
</html>