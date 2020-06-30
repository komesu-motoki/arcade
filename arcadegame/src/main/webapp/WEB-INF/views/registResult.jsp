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
	<h2 id="sub-title">新規登録</h2>
	<p class="confirm-msg">登録しました</p>

	<form:form action="regist3" modelAttribute="regist">
		<div id="form-btn-center">
			<div id="form-div">

			<div>
				<label for="" class="form-item">ID</label><input type="text"
					class="text-design-id text-box" value="${sessionScope.loginId}"
					readonly>
			</div>
			<br>
			<div>
				<label for="" class="form-item">名前</label><input type="text"
					class="text-design-name text-box" value="${sessionScope.userName}"
					readonly>

			</div>
			<br>
			<div>
				<label for="" class="form-item">PASS</label><input type="password"
					class="text-design-pass text-box" value="${sessionScope.password}"
					readonly>
			</div>


			</div>
		</div>
		<div id="form-btn-center">
			<a href="login"><button class="form-btn" style="width: 200px;">ログイン画面へ</button></a>
		</div>
	</form:form>
</body>
</html>