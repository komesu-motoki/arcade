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
    <h3 id="mycoin">所持コイン：${coin}枚</h3>
    <a href="shop"><img src="images/returnbtn.png" alt=""class="returnbtn"></a>

  <c:if test="${not empty msg}">
    <p class="confirm-msg">${fn:escapeXml(msg)}</p>
  </c:if>
<c:if test="${empty flag}">
<form:form action="sellResult" modelAttribute="sellForm">
    <table>
        <tr>
            <th></th>
            <th class="checkbox">ゲーム名</th>
            <th class="checkbox">アイテム名</th>
            <th class="checkbox">値段</th>
            <th class="checkbox">個数</th>
        </tr>
      <c:forEach items="${StockAll}" var="stock">
        <tr>
                <td><form:checkbox value="${stock.itemId}" path ="itemsId" style="width: 20px; height: 20px;" /></td>
                <td>${stock.gameName}</td>
                <td>${stock.itemName}</td>
                <td>${stock.price}</td>
                <td><form:input type="number" value="0" min="0" max="${stock.itemHave}" path ="number" class="form-number" /></td>
        </tr>
      </c:forEach>
    </table>

    <div id="form-btn-center"><button class="form-btn">売却する</button></div>
</form:form>
</c:if>
</body>
</html>