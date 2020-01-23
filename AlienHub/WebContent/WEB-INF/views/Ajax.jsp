<%@ page language="java" contentType="text/html" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ajax testing</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> 
</head>
<body>
<div id="div1"><h2>Login Here</h2></div>
 <form>
  			<input type="email"  placeholder="Email" id="uemail">
            <input type="number"  placeholder="Mobile" id="umob">
            <input class="button" type="submit" value="Sign Up" onclick="ajaxcheck()">
            </form>
 <script> 
 
 function ajaxcheck()
 {
	 var uid=$("#uemail").val();
	 var umob=$("#umob").val();
	 alert("uid"+uid);
	 alert("umob"+umob);
 

/*  
	
 $(document).ready(function(){
	 
	 console.log("IN the function"+uid);
	  $(".button").click(function(){
		  
		   */
		  
		  
		  console.log("calling $.ajax");
		    $.ajax({
		    	data:JSON.stringify({"email":uid, "mobile":umob}),
				url:'http://localhost:8080/AlienHub/ajaxtest',
				type:'post',
				dataType:'json',
				contentType:'json',
				cache:false,
				 success: function(result){
					 alert("done");
					 console.log(result);
		      $("#div1").html(result);
		    },
		   		 error: function(error){
		   			 
		   			 console.log("Error is:"+error);
		   			 //console.log("status is:"+status);
		   		 }
		    
		  
	/* 	}); */
 });
 }
	
</script>
</body>
</html>