<%@ page language="java" contentType="text/html;"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Profile</title>
</head>
<body>

<h1>hello ${u_data.name}</h1>
<h2>Your Updated profile picture </h2>
<img src="${u_data.upic}" height=200px width=200px/>
<p>Mobile no-${u_data.mobile } </p>
<p>Email id-${u_data.email}</p>
</body>
</html>