<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->
			<!--Lấy messege trên thanh trình duyệt hiện tại -->
			<c:if test="${not empty message}">
				<div class="alert alert-${alert}">
					<!--Cái này truyền danger:màu đỏ, success:màu xanh -->
					<strong>${message}</strong>
				</div>
			</c:if>
			<!-- Icon -->
			<div class="fadeIn first">
				<h2>Đăng nhập</h2>
			</div>

			<!-- Login Form -->

			<form action="<c:url value='/dang-nhap'/>" id="formLogin"
				method="post">
				<!-- id dùng để submit bằng jquery và name dùng để ánh xạ sang server -->
				 <c:if test="${not empty requestScope.UserName}">
        			<input type="text" id="userName" class="fadeIn second" name="userName" placeholder="Email address" value="${requestScope.UserName}">
   				 </c:if>
				<c:if test="${empty requestScope.UserName}">
        			<input type="text" id="userName" class="fadeIn second" name="userName" placeholder="Email address" ">
   				 </c:if>
    			<c:if test="${not empty requestScope.password}">
        			<input type="password" id="password" class="fadeIn third" name="password" placeholder="Password" value="${requestScope.password}">
    			</c:if>
				<c:if test="${ empty requestScope.password}">
        			<input type="password" id="password" class="fadeIn third" name="password" placeholder="Password" >
    			</c:if>
				<input type="hidden" value="login"
					name="action" /> 
				<input type="submit" class="fadeIn fourth" value="Đăng nhập"><!-- Khi người dùng nhấn nút này thì 2 input tk,mk đc nộp httpbody của 
																			   url .../dang-nhap?action=login(action=login là input hidden thành tham số url)  với method post(httpBody sẽ ko hiện mk,tk và đi vào method post) -->
			</form>

			<!-- Remind Passowrd -->


		</div>
	</div>
</body>
</html>