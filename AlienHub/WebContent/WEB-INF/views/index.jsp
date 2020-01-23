<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<script src="https://unpkg.com/geofirestore/dist/geofirestore.js"></script>
 <script src="https://www.gstatic.com/firebasejs/6.6.1/firebase-app.js"></script>
  <script src="https://www.gstatic.com/firebasejs/6.6.1/firebase-auth.js"></script>
  <script src="https://www.gstatic.com/firebasejs/6.6.1/firebase-firestore.js"></script>

</head>
<body>

 
<h1>Hello Alien how is Your planet</h1>
<a href="Signup.html">Sign Up Here</a>
<a href="Signin.html">Sign In Here</a>
<a href="test">Image Test</a>
<a href="testing.html">Testing</a>
<a href="Messages.html">Messages</a>
<p id="demo"></p>

<button onClick="getLocation()">Location</button>

<script>

function getLocation() {
	alert("rrrr");
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition, showError);
  } else { 
    x.innerHTML = "Geolocation is not supported by this browser.";
  }
  
}


var lat=0.0;
var lng=0.0;
var firebaseConfig = ({
  apiKey: "GcPVh9kkUSbi2pP7CGRif83mbjwWkdmcCnJzBpM",
  authDomain: "alienhub-4c22a.firebaseapp.com",
  databaseURL: "https://alienhub-4c22a.firebaseio.com",
  projectId: "alienhub-4c22a",
  storageBucket: "",
  messagingSenderId: "920881843329",
  appId: "1:920881843329:web:e3f50734a72370d5"
});

firebase.initializeApp(firebaseConfig);
var firestore = firebase.firestore();

geofirestore GeoFirestore = new GeoFirestore(firestore);
const geocollection GeoCollectionReference = geofirestore.collection('near_by');

function updateGeoLocation(var lat, var lng) {
geocollection.add({
	  name: 'rahul',
	  uid: 100,
	  upic:'https://www.googleapis.com/download/storage/v1/b/alienhub-4c22a.appspot.com/o/rajan.jpg?generation=1568214496816775&alt=media'
	  // The coordinates field must be a GeoPoint!
	  coordinates: new firebase.firestore.GeoPoint(lat, lng)
	})

	// Create a GeoQuery based on a location
	const query: GeoQuery = geocollection.near({ center: new firebase.firestore.GeoPoint(lat, lng), radius: 1000 });

	// Get query (as Promise)
	query.get().then((value: GeoQuerySnapshot) => {
	  // All GeoDocument returned by GeoQuery, like the GeoDocument added above
	  console.log(value.docs);
	});

}



	var x = document.getElementById("demo");


	function showPosition(position) {
	  x.innerHTML = "Latitude: " + position.coords.latitude + 
	  "<br>Longitude: " + position.coords.longitude;  
	  lat=position.coords.latitude;
	  lng=position.coords.longitude;
	  function updateGeoLocation(lat,lng)
	  {};
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

</script>



</body>
</html>
