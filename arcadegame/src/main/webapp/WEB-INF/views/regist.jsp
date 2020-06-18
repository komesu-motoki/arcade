<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>アーケードゲーム</title>
    <link rel="stylesheet" href="css/all.css">
    <link rel="icon" href="images/invader1.png">
</head>
<body>
    <h2 id="sub-title">新規登録</h2>
    <a href="top"><img src="images/returnbtn.png" alt="" class="returnbtn"></a>

    <form:form action="regist1" modelAttribute="regist">

    <div id="form-btn-center">
        <div id="form-div">
            <div><label for="" class="form-item">ID</label><input type="text" name="loginId" class="text-design-id text-box"></div><br>
            <div><label for="" class="form-item">名前</label><input type="text" name="userName" class="text-design-name text-box"></div><br>
            <div><label for="" class="form-item">PASS</label><input type="text" name="password" class="text-design-pass text-box"></div>
        </div>
    </div>
    <div id="form-btn-center"><button class="form-btn">確認</button></div>
    </form:form>
</body>
</html>