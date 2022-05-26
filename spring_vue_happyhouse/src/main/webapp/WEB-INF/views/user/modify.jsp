<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        $(document).ready(function () {
            $("#modifyBtn").click(function () {
                if (!$("#password").val()) {
                    alert("비밀번호 입력!!!");
                    return;
                } else if (!$("#name").val()) {
                    alert("이름 입력!!!");
                    return;
                } else if (!$("#email").val()) {
                    alert("비밀번호 입력!!!");
                    return;
                } else {
                    $("#updateform").attr("action", "/user/update").submit();
                }
            });
        });
    </script>
	<section>
		<div align="center" style="margin-top:50px">
			<form id="updateform" method="post" action="">
                <input type="hidden" name="act" id="act" value="update">
				<div>
					<div>
						<h2>회원 정보 수정</h2>
					</div>
				</div>
				<div>
					<label>아이디</label>
					<div>
						<input type="text" value ="${userinfo.userid }" id="id" name="userid" readonly="readonly">					
					</div>
				</div>
				<div>
					<label>비밀번호</label>
					<div>
						<input type="password" value ="${password }" id="password" name="userpwd" required="required">					
					</div>
				</div>
				<div>
					<label>이름</label>
					<div>
						<input type="text" value ="${name }" id="name" name="username" required="required">					
					</div>
				</div>
				<div>
					<label>주소</label>
					<div>
						<input type="text" value ="${email }" id="email" name="email" required="required">
					</div>
				</div>
<!-- 				<div> -->
<!-- 					<label>전화번호</label> -->
<!-- 					<div> -->
<%-- 						<input type="tel"value ="${tel }" id="tel" name="tel" required="required"> --%>
<!-- 					</div> -->
<!-- 				</div> -->
				
				<div>
					<div style="margin-top:10px">
						<button type="button" id="modifyBtn">수정
							&nbsp; &#10003;</button>
					</div>
				</div>
			</form>
			
		</div>
	</section>

<%-- 	<%@ include file="/template/footer.jsp"%> --%>
</body>

</html>
