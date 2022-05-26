<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html> 
<html> 
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
<div id="content"> 
	<h3>[ ${vo.username } ]고객 정보</h3> 
	<table> 
		<tr> 
			<th>아이디</th> 
			<td>${vo.userid }</td> 
		</tr> 
		<tr> 
			<th>이메일</th> 
			<td>${vo.email }</td> 
		</tr> 
		<tr> 
			<th>비밀번호</th> 
			<td>${vo.userpwd }</td> 
		</tr> 
	</table> 
<div class='btnSet'> 
	<a class='btn-fill' href="list.cu">고객 목록</a> 
	<a class='btn-fill' href="modify.cu?id=${vo.id }">수정</a> 
	<a class='btn-fill' onclick="if( confirm('삭제') ){ href='delete.cu?id=${vo.id }' }" >삭제</a> 
</div> 
</div> 
</body> 
</html>
