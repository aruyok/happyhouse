<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<div class="container">
  <h2>Regist</h2>
  <form action="/user/regist" method="post">
    <div class="form-group">
      <label for="id">사용할 Id를 입력하세요</label>
      <input type="text" class="form-control" id="userid" placeholder="Enter Id" name="userid">
    </div>
    <div class="form-group">
      <label for="password">사용할 Password를 입력하세요</label>
      <input type="password" class="form-control" id="userpwd" placeholder="Enter password" name="userpwd">
    </div>
    <div class="form-group">
      <label for="name">이름을 입력하세요</label>
      <input type="text" class="form-control" id="username" placeholder="Enter name" name="username">
    </div>
     <div class="form-group">
      <label for="email">사용할 이메일을 입력하세요</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
    </div>
    <button type="submit" class="btn btn-primary">Sign Up</button>
  </form>

</div>

</body>
</html>
