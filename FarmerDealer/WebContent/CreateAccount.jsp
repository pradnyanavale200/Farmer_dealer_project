<%@page import="javafx.scene.control.RadioButton"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="HomePagedesign.css">
<title>Insert title here</title>
	<script type="text/javascript">
		function nospacenumbertxt(x)
		{
			if((x.keyCode == 32))
				{
					alert("No space and number allowed");
					return false;
				}
			else if(x.keyCode > 90 &&  x.keyCode < 97)
				{
				alert("No specific letter allowed");
				return false;
				}
			else if(x.keyCode < 65 &&  x.keyCode > 47 )
				{
				alert("No number or specific character allowed");
				return false;
				}
			else if(x.keyCode > 122 )
				{
				alert("no specific character allowed");
				return false;
				}
			return true;
		}
		function nospacetxt(x)
		{
			if((x.keyCode == 32))
				{
					alert("No space and number allowed");
					return false;
				}
			return true;
		}
		function validateDate()
		{
			var d=document.getElementById("date").value;
			var Split=d.value.split("-");
			if(parseInt(Split[2]) > 2000)
				{
					alert("date not valid");
					return false;
				}
			else
				{
					return true;
				}
		}
	</script>
</head>
<body>
<div id="navigation">

	<nav>
	
	
		<ul>
			<li><a href="home.jsp">HOME</a></li>
			<li><a href="Login.jsp">LOGIN</a></li>
			
		</ul>
	</nav>
</div>
<center>
<center><h1 text-align="center">Registration Form</h1></center>
<table bgcolor=#B7C7C5 cellpadding=30px>

	<form action="CreateAccountController" method="post">
		<div class="form">
		<tr>
		<div id="d1">
			<td><h3>First Name :</h3></td>
			<td><h3><input type="text" class="resizedTextbox"  name="fname" placeholder="Your name.." id="fname" autocomplete="off" onkeydown="return nospacenumbertxt(event)" required></td></h3>
		</tr>
		<tr>
			<td><h3>Last Name :</h3></td>
			<td><h3><input type="text" class="resizedTextbox"  name="lname" placeholder="Your name.." id="lname" autocomplete="off" onkeydown="return nospacenumbertxt(event)" required></h3></td>
		</tr>
		<tr>
			<td><h3>Gender:</h3></td>
			
			<td><h3><input type="radio" name="gender" value="Male"  >Male
			<input type="radio" name="gender" value="Female">Female</h3></td>
		
		</tr>
		
		<tr>
			<td><h3>Date of Birth :</h3></td>
			<td><h3><input type="date" class="resizedTextbox" name="dob" placeholder="Your date of birth.." id="date" autocomplete="off" min="1965-01-01" max="2000-12-01" oninput ="this.validateDate()" required></h3></td>
		</tr>
		<tr>
			<td><h3>City:</h3></td>
			<td><h3><input type="text" class="resizedTextbox" name="city" placeholder="Your city.." autocomplete="off" onkeydown="return nospacenumbertxt(event)" required></h3></td>
		</tr>
		<tr>
			<td><h3>State :</h3></td>
			<td><h3><input type="text" class="resizedTextbox" name="state" placeholder="Your state.." autocomplete="off" onkeydown="return nospacenumbertxt(event)" required></h3></td>
		</tr>
		<tr>
			<td><h3>Nationality :</h3></td>
			<td><h3><input type="text" class="resizedTextbox" name="nationality" placeholder="Your nationality.." autocomplete="off" onkeydown="return nospacenumbertxt(event)" required></h3></td>
		</tr>
		<tr>
			<td><h3>Mobile no. :</h3></td>
			<td><h3><input type="text"  pattern="[789][0-9]{9}" class="resizedTextbox" name="mono" placeholder="Your phone no." title="phone no. must be of 10 digit and always start from 7 or 8 or 9" id="mono" autocomplete="off" required></h3></td>
		</tr>
		<tr>
			<td><h3>Email ID :</h3></td>
			<td><h3><input type="email" pattern="[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" class="resizedTextbox" name="email" placeholder="Your email-id.." title="Email-id must be in format characters@characters.domain(for eg:name@gmail.com)" id="email" autocomplete="off" required></h3></td>
		</tr>
		<tr>
			<td><h3>Category of User:</h3></td>
			<td><h3>
		
		<select name="catofuser" style="width: 330px;height:30px">
	  		<option value="---select---">---select---</option>
	  		<option value="Farmer">Farmer</option>
	  		<option value="Dealer">Dealer</option>
  		</select></h3></td>
		</tr>
		<tr>
			<td><h3>UserId :</h3></td>
			<td><h3><input type="text" class="resizedTextbox" name="username" placeholder="Create your own userid.." autocomplete="off" onkeydown="return nospacetxt(event)" required></h3></td>
		</tr>
		
		</table>
		
		<h2>(Note : After submiting this form you will get username
			 And password for your Account on your registered Email..)
		</h2><br>
		
		<input type="submit" id="search" value="Submit">
		<input type="reset" id="search">		 
		
		
	</form>
	</div>
	</center>
	
</body>
</html>