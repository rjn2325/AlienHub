<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="utf-8">
<title>Sign in Here</title>
</head>
<body>
<h1>${login_status}</h1>
<form action="Signin" method="post" modelAttribute="usignin">
Email:<input type="email" name="email" placeholder="Enter Email"><br><br>
Password:<input type="password" name="password" id="upwd" placeholder="Enter Password:">
<input type="button" onclick="myFunction" value="Show password"><br><br> 	

<script>
function myFunction() 
{
	alert("function called");
  var x = document.getElementById("upwd");
  alert(x);
  if(x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}
</script>
<Button type="submit">Sign In</Button>
</form>


</body>
</html>
