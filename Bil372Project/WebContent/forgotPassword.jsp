<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Get Password</title>


  
      <link rel="stylesheet" href="css/forgotPassword.css">
</head>
<body>

	<form action="ForgotPasswordServlet" method="post">
		<div class="form">
	
	          <h1>Forgot Your Password?</h1>
	          
	          <form action="/" method="post">
	          
	            
	           <div class="field-wrap">
	            <input type="text"required autocomplete="off" placeholder="Enter your username" name="username"/>
	          </div>
	          <div class="mailtext"> 
	          	<h2 style="margin-top: 50px;">Your password will be sent to your e-mail!</h2>
	          </div>
	          <button class="button button-block">SENT TO MY MAIL </button>
	          
	          </form>
	
	        
	      
	      
		</div> <!-- /form -->
	</form>
</body>

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
</html>