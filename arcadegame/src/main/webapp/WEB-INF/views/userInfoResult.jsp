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

	<a href="menu"><img src="images/homeicon.png" alt=""
		class="home-icon"></a>
	<h2 id="sub-title">ユーザー情報</h2>
	<p class="confirm-msg">変更が完了しました</p>


	<div id="form-btn-center">
		<div id="form-div">
			<div>
				<label for="" class="form-item">ID</label><input type="text"
					class="text-design-id text-box" value="${sessionScope.LoginId}"
					readonly>
			</div>
			<br>
			<div>
				<label for="" class="form-item">名前</label><input type="text"
					class="text-design-name text-box" value="${sessionScope.UserName}"
					readonly>

			</div>
			<br>
			<div>
				<label for="" class="form-item">PASS</label><input type="password"
					class="text-design-pass text-box" value="${sessionScope.Password}"
					readonly>
			</div>
		</div>
	</div>

	<div id="form-btn-center">
		<a href="menu"><button class="form-btn" style="width: 200px;">メニューへ</button></a>
	</div>



</body>
</html>