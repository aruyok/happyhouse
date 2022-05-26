<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap 4 Example</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!--   <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script> -->
<!--   <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script> -->
<!--   <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script> -->
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/css/apt.css">
				
</head>
<body>

<script type="text/javascript">
var msg = "${u_msg}"
	if(msg){
//			console.log("hi")
		alert(msg);
				
	}

var d_msg = "${d_msg}"
	if(d_msg){
		alert(d_msg);
	}

</script>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<c:if test="${empty userinfo }">
	<div class="container">
	  <div class="jumbotron">
	    <h1>아파트 매물 실거래가</h1>      
	    <p>아파트 매물 실거래가를 보시려면 로그인을 해주세요!</p>
	  </div>    
	</div>
</c:if>

<c:if test="${!empty userinfo }">
	<div class="container">
		<header id="index_header" class="jumbotron text-center mb-1">
<!-- 			<img id="logo" src="/img/happyhouse.png"> -->
		</header>
		<!-- nav start -->
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark rounded">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="#">Home</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						동네 정보
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">APT 매매</a>
						<a class="dropdown-item" href="#">APT 전월세</a>
						<a class="dropdown-item" href="#">주택 매매</a>
						<a class="dropdown-item" href="#">주택 전월세</a>
						<a class="dropdown-item" href="#">상권 정보</a>
						<a class="dropdown-item" href="#">환경 정보</a>
					</div>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Notice</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">News</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Contact</a>
				</li>
			</ul>
		</nav>

		<section id="index_section">
			<div class="card col-sm-12 mt-1" style="min-height: 850px;">
				<div class="card-body">
					<div class="form-group form-inline justify-content-center">
						<label class="mr-2" for="sido">시도 : </label>
						<select class="form-control" id="sido">
							<option value="0">선택</option>
						</select>
						<label class="mr-2 ml-3" for="gugun">구군 : </label>
						<select class="form-control" id="gugun">
							<option value="0">선택</option>
						</select>
						<label class="mr-2 ml-3" for="dong">읍면동 : </label>
						<select class="form-control" id="dong">
							<option value="0">선택</option>
						</select>
						<label class="mr-2 ml-3" for="dong">원하는 옵션 : </label>
						<select class="form-control" id="interesting">
							<option value="0">선택</option>
							<option value="1">편의점</option>
							<option value="2">은행</option>
						</select>
						<button type="button" class="btn btn-light mr-2 ml-3" id="search-btn">검색</button>
					</div>
					<table class="table mt-2">
						<colgroup>
							<col width="100">
							<col width="150">
							<col width="*">
							<col width="120">
							<col width="120">
						</colgroup>	
						<thead>
							<tr>
								<th>번호</th>
								<th>아파트이름</th>
								<th class="text-center">주소</th>
								<th>건축연도</th>
								<th>최근거래금액</th>
							</tr>
						</thead>
						<tbody id="searchResult"></tbody>
					</table>
				<div id="map" style="width:100%;height:500px;"></div>
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e1fb9b0abd6fa78647b5428d4a651ae&libraries=services"></script>
				<script type="text/javascript" src="js/map3.js"></script>
				<script type="text/javascript">
				
				let colorArr = ['table-primary','table-success','table-danger'];
				$(document).ready(function(){					
					$.get("/map/sido"
						,function(data, status){
							$.each(data, function(index, vo) {
								$("#sido").append("<option value='"+vo.sidoCode+"'>"+vo.sidoName+"</option>");
							});
						}
						, "json"
					);
				});
						
				
				
				
				$(document).on("change", "#sido", function() {
					$.get("/map/gugun"
							,{sido: $("#sido").val()}
							,function(data, status){
								$("#gugun").empty();
								$("#gugun").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#gugun", function() {
					$.get("/map/dong"
							,{gugun: $("#gugun").val()}
							,function(data, status){
								$("#dong").empty();
								$("#dong").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#dong").append("<option value='"+vo.dongCode+"'>"+vo.dongName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#dong", function() {
					$.get("/map/apt"
							,{dong: $("#dong").val()}
						
							,function(data, status){
								$("tbody").empty();
								$.each(data, function(index, vo) {
									console.log(vo)
									let str = `
										<tr class="${colorArr[index%3]}">
										<td>${'${vo.aptCode}'}</td>
										<td>${'${vo.aptName}'}</td>
										<td>${'${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}'}</td>
										<td>${'${vo.buildYear}'}</td>
										<td>${'${vo.recentPrice}'}</td>
										</tr>
									`;
// 									console.log(str)
// 									$("tbody").append(str);
									
								});
								
							}
							, "json"
					);
				});
				
				
				$(document).on("click","#search-btn", "#sido", function() {
					if($("#sido").val()!=0 && $("#gugun").val()==0 && $("#dong").val()==0){
						console.log("클릭!")
						$.get("/map/s_sido"
								,{sido: $("#sido").val()}
								,function(data, status){
									$("tbody").empty();
									$.each(data, function(index, vo) {
										console.log(vo)
										let str = `
											<tr class="${colorArr[index%3]}">
											<td>${'${vo.aptCode}'}</td>
											<td>${'${vo.aptName}'}</td>
											<td>${'${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}'}</td>
											<td>${'${vo.buildYear}'}</td>
											<td>${'${vo.recentPrice}'}</td>
											</tr>
										`;
// 										console.log(str)
										$("tbody").append(str);
									});
						
									displayMarkers(data);
									
								}
								, "json"
						);
					
					}
						
				});
				$(document).on("click","#search-btn", "#gugun", function() {
// 					console.log($("#gugun").val())
					if($("#gugun").val()!=0 && $("#dong").val()==0){
						console.log("gugunClick")
						$.get("/map/s_gugun"
								,{gugun: $("#gugun").val()}
								,function(data, status){
									$("tbody").empty();
									$.each(data, function(index, vo) {
										console.log(vo)
										let str = `
											<tr class="${colorArr[index%3]}">
											<td>${'${vo.aptCode}'}</td>
											<td>${'${vo.aptName}'}</td>
											<td>${'${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}'}</td>
											<td>${'${vo.buildYear}'}</td>
											<td>${'${vo.recentPrice}'}</td>
											</tr>
										`;
// 										console.log(str)
										$("tbody").append(str);
									});
							
									displayMarkers(data);
								}
								, "json"
						);
					}
				});
				
				
				$(document).on("click","#search-btn", "#dong", function() {
// 					console.log($("#dong").val())
					
					if($("#dong").val()!=0){
						let i=0;
						console.log("dongClick")
						let lat;
						let lon;
						$.get("/map/s_dong"
								,{dong: $("#dong").val()}
								,function(data, status){
									$("tbody").empty();
									$.each(data, function(index, vo) {
										console.log(vo)
										let str = `
											<tr class="${colorArr[index%3]}">
											<td>${'${vo.aptCode}'}</td>
											<td>${'${vo.aptName}'}</td>
											<td>${'${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}'}</td>
											<td>${'${vo.buildYear}'}</td>
											<td>${'${vo.recentPrice}'}</td>
											</tr>
										`;
										console.log(str)
										
										
										$("tbody").append(str);
// 										calc_dist(0,vo.lat,vo.lng);
// 										let=vo.lat;
// 										lon=vo.lng;
// 										getDistance(0,let,lon);
										
									});
									displayMarkers(data);
// 									init();
									
									
									
								}
								, "json"
						);
					}
					
				});
				
				
				$(document).on("click","#search-btn", "#interesting", function() {
// 					console.log($("#dong").val())
					var flag=0;
					let location=[];
					if($("#interesting").val()==1){//편의점을 골랐을 경우
						console.log("hi");
						flag=1;
					
					}else if($("#interesting").val()==2){//지하철 역을 골랐을 경우
						console.log("hi2");
						flag=2;
					}
					
					
					if($("#interesting").val()!=0){
				
						
						console.log("interesting Click")
						let lat;
						let lon;
						
						$.get("/map/s_dong"
								,{dong: $("#dong").val()}
								,function(data, status){
									$("tbody").empty();
									$.each(data, function(index, vo) {
										console.log(vo)
										let str = `
											<tr class="${colorArr[index%3]}">
											<td>${'${vo.aptCode}'}</td>
											<td>${'${vo.aptName}'}</td>
											<td>${'${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}'}</td>
											<td>${'${vo.buildYear}'}</td>
											<td>${'${vo.recentPrice}'}</td>
											</tr>
										`;
// 										console.log(str)
										
										
										$("tbody").append(str);
// 										calc_dist(0,vo.lat,vo.lng);
										let arr = [];
										lat=vo.lat;
										lon=vo.lng;
										arr.push(lat,lon);
										location.push(arr);
// 										getDistance(0,lat,lon);
			
										
									});
									
									displayMarkers(data,flag); //
									for(let i=0;i<location.length;i++){
										console.log("한번눌렀어요!");
										getDistance(0,location[i][0],location[i][1]); //최단거리
										console.log("여기 들어왔습니다!");
									}
									
									
									
									init();
									
									console.log("init");
			 						
								}
								, "json"
						);

					}
					
				});
				
				
				</script>
				</div>
			</div>
		</section>
	</div>
</c:if>


</body>
</html>