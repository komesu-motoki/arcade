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
</head>
<body>

    <a href="menu"><img src="images/homeicon.png" alt=""class="home-icon"></a>
    <a href="game"><img src="images/returnbtn.png" alt="" class="returnbtn"></a>
    <h2 id="sub-title">インベーダーゲーム</h2>
    <p class="confirm-msg">アイテム選択</p>

    <form:form action="invaderPlay"  modelAttribute="ItemSelectForm">
        <div id="form-div" class="form-center">
            <div class="game">
				<label for="" class="form-item">アイテム1</label>
				<form:select path="gameIdOne" class="form-select" multiple="false">
						<form:option value="0">選択する</form:option>
					<c:forEach var="item" items="${stockList}">
						<form:option value="${item.itemId}" label="${item.itemName}" />
					</c:forEach>
				</form:select>
			</div>
        </div>
        <br>
        <div id="form-div" class="form-center">
            <div class="game">
				<label for="" class="form-item">アイテム2</label>
				<form:select path="gameIdTow" class="form-select" multiple="false">
						<form:option value="0">選択する</form:option>
					<c:forEach var="item" items="${stockList}">
						<form:option value="${item.itemId}" label="${item.itemName}" />
					</c:forEach>
				</form:select>
			</div>
        </div>
        <br>
        <div id="form-div" class="form-center">
            <div class="game">
				<label for="" class="form-item">アイテム3</label>
				<form:select path="gameIdThree" class="form-select" multiple="false">
						<form:option value="0">選択する</form:option>
					<c:forEach var="item" items="${stockList}">
						<form:option value="${item.itemId}" label="${item.itemName}" />
					</c:forEach>
				</form:select>
			</div>
        </div>
        <div id="form-btn-center"><button class="form-btn" style="width: 200px;">始める</button></div>
    </form:form>

</body>
</html>