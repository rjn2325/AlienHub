<%@ page language="java" %>
   
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> 
<link rel="stylesheet"  href="resources/css/signin.css">
<meta charset="utf-8">
<title>Sign in Here</title>


</head>
<body>

<h2>Login Here</h2>
<center>
<div class="form-area">
<div class="avatar">
<center><img class="user_image" src="resources/images/avatar.jpg"></center>
<div class="res"></div>
</div>

<div class="form-data">
<form action="Signin" method="post" modelAttribute="usignin">
<input type="email" name="email" placeholder="Enter Email" id ="uemail"><br>
<label id="imail"></label>
<input type="password" name="password" id="upwd" placeholder="Enter Password:" id="upwd"><br>
<label id="ipassword"></label>
<b><p id="sign_up">Don't have an account  <span><a href="#" id="sign_up_link" >Sign up</a></p></b></span>



<input type="button"  class="btn" value ="Sign In" onclick="formsubmit()">
<a href="#"><p id="fp">Forget Password?</p> </a>

</form>
</div>
</div>




</center>
<script>

function formsubmit()
{
	
	var uid=$("#uemail").val();
	var upsd=$("#upwd").val();
	alert(uid);
    alert(upsd);
    var n=0;
    
    var regex=/^([a-z A-Z 0-9\.-]+)@([a-z A-Z 0-9]+).([a-z A-Z]{2,8}).([a-z A-Z]{2,8})$/;
    if(uid=="" || uid==null)
    {
        alert("please enter email id");
        document.getElementById("imail").innerHTML="Please Enter Email Id";
        document.getElementById("imail").style.visibility="visible";
        document.getElementById("imail").style.color='red';
        $('#imail').fadeOut(10000);
    
    }
    else if(regex.test(uid))
    {
        alert("Valid Email Address");
        
        n++;
    }
    
    else 
    {
        document.getElementById("imail").innerHTML="Your Email Id Is Not Valid";
        document.getElementById("imail").style.visibility="visible";
        document.getElementById("imail").style.color='red';
        $('#imail').fadeOut(8000);
        $("#uemail").val('');
        $("#upwd").val('');
	     
        // alert("invalid email address");
    }

    if(upsd=="" || upsd==null)
    {
        
        document.getElementById("ipassword").innerHTML="Please Enter Password";
        document.getElementById("ipassword").style.visibility="visible";
        document.getElementById("ipassword").style.color='red';
        $('#ipassword').fadeOut(10000);
       
	      $("#upwd").val('');
        // alert("please enter your password");
    
    }

    else 
    {
        alert("valid password");
        n++;
        alert(n);
    }

    if(n==2)
    {
        console.log("ajax calling");    
	 $.ajax({
        
	    	data: {email:uid, password:upsd},
	    	type:'get',
			url:"Signinn?="+uid+"&="+upsd,
			dataType:'text',
			contentType:'text',
			cache:false,
			 success: function(result){
				 alert("done");
				 console.log(result);
				 alert(result);
				 if(result==="success")
					 {
					 window.location.href='/AlienHub/Home';
					 }
				 else
					 {
	      $(".res").text(result);
	      $(".res").show();
	      $("#uemail").val('');
	      $("#upwd").val('');
	      
					 }
	    },
	   		 error: function(error){
	   			 
	   			 console.log("Error is:"+error);
	   			
	   		 }
	    
	    		
	 });
	 
	
    }
		 
}


</script>


</body>
</html>

