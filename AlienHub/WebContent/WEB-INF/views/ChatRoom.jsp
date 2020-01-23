<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chatroom</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <script src="https://www.gstatic.com/firebasejs/7.0.0/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.0.0/firebase-firestore.js"></script>
</head>

<body>

<script>
firebase.initializeApp({
	apiKey: "GcPVh9kkUSbi2pP7CGRif83mbjwWkdmcCnJzBpM",
	  authDomain: "alienhub-4c22a.firebaseapp.com",
	  databaseURL: "https://alienhub-4c22a.firebaseio.com",
	  projectId: "alienhub-4c22a",
	  storageBucket: "",
	  messagingSenderId: "920881843329",
	  appId: "1:920881843329:web:e3f50734a72370d5"

});

$(function(){
	$(".text1").keyup(function(event) {
	    if (event.keyCode === 13) {
	        $(".submit").click();
	    }
	});
  $(".submit").click(function(){
    var t1 = $(".text1").val();
    var t2 = $(".text_data").html();
    $(".text_data").html(t2 +" <br> "+ t1);
    $(".text1").val('');
    var db = firebase.firestore();
   //var docid= db.collection("chat").doc();
   var dataref=db.collection("chat").doc("msg");
   	var merge_data=dataref.set({
        first: "rjn",
        middle: "the",
        last: "noob",
        born: 1912
    },{merge:true})
    .then(function() {
        console.log("Document written with ID: ");
    })
    .catch(function(error) {
        console.error("Error adding document: ", error);
    });

  })
  })
</script>

<div style="margin-top: 10px">

<input class="text1" type="text">
<button class="submit">Send </button>
<div class="text_data"></div>
</div>
<script>

function sendTo()
{
	
	
	}


</script>




<!-- 
<div ng-app="myapp" ng-controller="sendController">
<input type="text" ng-model="msg"  id="msg" placeholder="Write Message">
<input class="button" type="button" value="send" ng-click="sendMsg(); resetData();"><br><br>


<p>{{msg_array}}</p>
<script>
var app =angular.module('myapp' ,[]);
app.controller('sendController',function($scope){
	$scope.msg='';
	$scope.msg_array=[];
	$scope.sendMsg=function() {
			console.log("msg:"+$scope.msg);
			
			$scope.msg_array.push($scope.msg);
			console.log("msgarraydata is:"+msgarray)
			
			document.getElementById("msg").value="";
		
		
	}
	
	
});

</script>


</div>
 -->
</body>
</html>