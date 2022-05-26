<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="#">SSAFY Apart</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Link</a>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
	          Dropdown
	        </a>
	        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	          <a class="dropdown-item" href="#">Action</a>
	          <a class="dropdown-item" href="#">Another action</a>
	          <div class="dropdown-divider"></div>
	          <a class="dropdown-item" href="#">Something else here</a>
	        </div>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link disabled">Disabled</a>
	      </li>
	      
	    </ul>
<!-- 	    <a href="/user/login">검색</a> -->

		<c:if test="${empty userinfo }">
	    <form class="form-inline my-2 my-lg-0" action="/user/login" method="GET" >
<!-- 	      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"> -->
	      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
	    </form>
	    </c:if>
	    <c:if test="${!empty userinfo }">
	    	<strong>${userinfo.username}</strong> (${userinfo.userid})님 안녕하세요.<br />
	    	<form class="form-inline my-2 my-lg-0" action="/user/logout" method="GET" >
<!-- 	      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"> -->
	      		<button class="btn btn-outline-success mr-2 ml-1" type="submit">로그아웃</button>
	    	 </form>
	    	 <form class="form-inline my-2 my-lg-0" action="/user/modify" method="GET" >
	      		<button class="btn btn-outline-success mr-2 ml-1" type="submit">회원정보 수정</button>
	    	 </form>
	    	 <form class="form-inline my-2 my-lg-0" action="/user/delete" method="GET" >
<!-- 	      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"> -->
	      		<button class="btn btn-outline-success mr-2 ml-1" type="submit">회원탈퇴</button>
	    	 </form>
	    </c:if>

	  </div>
	</nav>
	
</body>
</html>