<html>
    <head>
        <style>
        
        *{
    box-sizing: border-box;
}
body{
height:780px;
background: rgb(121,97,247);
background: linear-gradient(45deg, rgba(121,97,247,1) 0%, rgba(84,56,231,1) 31%, rgba(135,91,181,1) 47%, rgba(239,101,101,1) 100%);
background-repeat: no-repeat;
}
h1{
    color: #fff;
    text-align: center;
    margin: 40px 0;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, .4);
}
.form{
    background-color: #fff;
    width: 550px;
    height:500px;
    margin:0 auto;
    border-radius: 15px;
    box-shadow: 2px 2px 4px rgba(0, 0, 0, .4);
    display: inline-block;
    display:flex;
    justify-content:center;
}
.wrapper{
    width: 450px;
    height:400px;
    margin: 49px;
}
.form .wrapper h2{
    color:#775ff3;
    border-bottom: 8px solid #775ff3;
    display: inline;
}
.form .wrapper p{
    color: #a4a4a4;
    margin-top: 40px;
}
.form .wrapper input{
    width:36%;
    margin-right: 10%;
    padding: 5px;
    box-sizing: border-box;
    margin-top: 40px;
    border: none;
    border-bottom: 4px solid #ccc;
    font-size: 15px;
}
.form.wrapper.gender {
	width:90%;
	height:90%;
	color:red;
	background-color:red;
}

.form .wrapper ::placeholder{
    color: #333333;
  opacity:.5;
}

.form .wrapper input:focus{
    outline: none;
}
.form .wrapper .button{
    border-bottom:none;
    background-color: #6666ff;
    color:#fff;
    float: left;
    padding: 12px 0;
    border-radius: 40px;
    cursor: pointer;
    box-shadow: 2px 2px 4px rgba(0, 0, 0, .4);

}
.form .wrapper .button:hover{
    background: rgb(41,0,255);
background: linear-gradient(45deg, rgba(41,0,255,1) 0%, rgba(135,91,181,1) 100%);
opacity: .7;
}

        </style>
    </head>

    <body>
        
            <h1>Sign Up!</h1>
            <div class="form">
            <div class="wrapper">
            <h2>Create Account</h2>
            <p>Please fill in this form to create an account</p>
            
              <form action="signup" method="get" modelAttribute="usignup">
            <input type="text"  name="name" placeholder="Full Name">
	            <select class="gender"> 
	            <option value="Male">Male</option>
	           	<option value="Female">Female</option>
	           	</select> 
          
            <input type="email" name="email" placeholder="Email">
            <input type="number" name="mobile" placeholder="Mobile">
            <input type="password" name="password" placeholder="Password">
             <input type="password" placeholder="Confirm Password">
            <input class="button" type="submit" value="Sign Up">
             </form>
               </div>
            </div>

    </body>

</html>