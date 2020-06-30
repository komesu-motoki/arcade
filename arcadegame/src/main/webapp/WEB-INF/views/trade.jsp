<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	<h1 id="sub-title">交換</h1>

	<a href="tradeMenu"><img src="images/returnbtn.png" alt=""
		class="returnbtn"></a>

	<c:choose>
		<c:when test="${not empty msg}">
			<p class="confirm-msg">${fn:escapeXml(msg)}</p>
		</c:when>
		<c:otherwise>

			<p class="confirm-msg">${fn:escapeXml(no)}</p>

			<c:if test="${empty flag}">

				<form:form action="tradeResult" modelAttribute="TradeForm">
					<table>
						<tr>
							<th></th>
							<th>出品者名</th>
							<th>ゲーム名</th>
							<th>出品アイテム</th>
							<th>欲しいアイテム</th>
						</tr>
						<c:forEach var="market" items="${marketItem}">
							<tr>
								<td><form:checkbox value="${market.saleId}" path="trade"
										style="width: 20px; height: 20px;" /></td>


								<td>${market.userName}</td>
								<td>${market.gameName}</td>
								<td>${market.giveName}</td>
								<td>${market.takeName}</td>
							</tr>

						</c:forEach>

					</table>

					<div id="form-btn-center">
						<button class="form-btn" style="width: 200px;">交換に応じる</button>
					</div>
				</form:form>
			</c:if>
		</c:otherwise>
	</c:choose>
</body>
</html>