<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>


<meta charset="utf-8">



<title>Find Aliens</title>
</head>
<body>
<script src="https://unpkg.com/geofirestore/dist/geofirestore.js"></script>
<c:set var="path" value="http://localhost:8080/AlienHub/resources/"></c:set>
<script src="${path}scripts/location.js"></script>



<p id="demo"></p>

<script>
var x = document.getElementById("demo");

function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(storeLocation,showError);
  } else { 
    x.innerHTML = "Geolocation is not supported by this browser.";
  }
}



function showError(error) {
  switch(error.code) {
    case error.PERMISSION_DENIED:
      x.innerHTML = "User denied the request for Geolocation."
      break;
    case error.POSITION_UNAVAILABLE:
      x.innerHTML = "Location information is unavailable."
      break;
    case error.TIMEOUT:
      x.innerHTML = "The request to get user location timed out."
      break;
    case error.UNKNOWN_ERROR:
      x.innerHTML = "An unknown error occurred."
      break;
  }
}

function storeLocation(position)
{
	var l=position.coords.latitude;
	var lo= position.coords.longitude;
	alert("Your Latitude is:" +l);
	alert("Your Longitude is:" +lo);
	console.log(l);
	
	
}
	</script>
<button onclick="getLocation()">Fetch Location</button>
</body>
</html>