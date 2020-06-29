<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Arcade Game</title>
<link rel="stylesheet" href="css/all.css">
<link rel="icon" href="images/invader1.png">
</head>
<body>
	<a href="userInfo"><img src="images/returnbtn.png" alt=""
		class="returnbtn"></a>
	<h2 id="sub-title">ユーザー情報</h2>
	<a href="menu"><img src="images/homeicon.png" alt=""
		class="home-icon"></a>
		<c:if test="${not empty msg}">
        <p class="confirm-msg" style="color: red">${msg}</p>
        </c:if>

	<form:form action="userInfoChange1" modelAttribute="userInfo">
		<div id="form-btn-center">
			<div id="form-div">
				<div>
					<label for="" class="form-item">ID</label><input type="text"
						name="loginId" class="text-design-id text-box" value="${sessionScope.loginId}">
						<form:errors path="loginId" cssStyle="color: red" />
				</div>
				<br>
				<div>
					<label for="" class="form-item">名前</label><input type="text"
						name="userName" class="text-design-name text-box" value="${sessionScope.userName}">
						<form:errors path="userName" cssStyle="color: red" />
				</div>
				<br>
				<div>
					<label for="" class="form-item">PASS</label><input type="password"
						name="password" class="text-design-pass text-box" value="${sessionScope.password}">
						<form:errors path="password" cssStyle="color: red" />
				</div>
			</div>
		</div>
		<div id="form-btn-center">
			<a href="userInfoConfirm"><button class="form-btn">確認</button>
		</a>
		</div>
	</form:form>

</body>
</html>