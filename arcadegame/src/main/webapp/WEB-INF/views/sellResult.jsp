<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

    <h1 id="sub-title">売却</h1>

    <p id="menu-btn">売却完了</p>

      <c:forEach items="${sellInfo}" var="sell">
              <p class="confirm-msg"> ${sell.itemName}を${sell.number}個売却しました</p>
      </c:forEach>
    <p class="confirm-msg">コイン残高：${coin}</p>

    <div id="form-btn-center"><a href="shop"><button class="form-btn" style="width: 200px;">ショップに戻る</button></a></div>

</body>
</html>