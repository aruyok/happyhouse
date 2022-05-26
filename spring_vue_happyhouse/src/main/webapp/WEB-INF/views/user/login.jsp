<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		var msg = "${msg}"
		if(msg){
// 			console.log("hi")
			alert(msg);
					
		}
		
		var msg = "${r_msg}"
		  	if(msg){
		  		console.log("hi")
		  		alert(msg);
		  	}
		$(document).ready(function(){
			$("#login_btn").click(function(){
				$("#login_form").attr("action","/user/login").submit();
			});
			$("#regist_btn").click(function(){
				console.log("hi");
				location.href = "/user/regist";
			});
		});
			
	</script>


<div class="container">
  <h2>Login</h2>
  <form id="login_form" method="post" action="">
    <div class="form-group">
      <label for="id">Id:</label>
      <input type="text" class="form-control" id="id" name="userid" placeholder="Enter Id">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="userpwd">
    </div>
    <div class="form-group form-check">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" name="remember"> Remember me
      </label>
    </div>
    <button type="button" class="btn btn-primary" id="login_btn">Login</button>
    <button type="button" class="btn btn-primary" id="regist_btn">Sign Up</button>
  </form>
</div>

</body>
</html>