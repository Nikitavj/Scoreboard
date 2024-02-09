<%@ page import="com.play.scoreboard.models.Match" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell N
  Date: 06.02.2024
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Matches</title>
</head>
<body>

<a href="new-match">
    <button>New Match</button>
</a>
<br><br>



Поиск матчей по имени Игрока
<form action="matches" method="get">
    <input name="filter_by_player_name" type="text" list="namesPlayers" placeholder="${name}" required>
    <button>Найти</button>
</form>

<datalist id="namesPlayers">
    <c:forEach items="${names}" var="namePlayer">
        <option value="${namePlayer}">${namePlayer}</option>
    </c:forEach>
</datalist>

<a href="matches">
    <button>All Matches</button>
</a>
<br>

<table border="1">
    <tr>
        <td></td>
        <td>Player1</td>
        <td>Player2</td>
        <td>Winner</td>
    </tr>
    <c:forEach var="match" items="${matches}">
        <tr>
            <td>${match.getId()}</td>
            <td>${match.getPlayer1().getName()}</td>
            <td>${match.getPlayer2().getName()}</td>
            <td>${match.getWinner().getName()}</td>
        </tr>
    </c:forEach>
</table>
<br>

<c:if test="${currentPage > 1}">
    <td>
        <c:if test="${name != null}">
            <a href="matches?page=${currentPage - 1}&filter_by_player_name=${name}">Back</a>
        </c:if>
        <c:if test="${name == null}">
            <a href="matches?page=${currentPage - 1}">Back</a>
        </c:if>
    </td>
</c:if>

<td>${currentPage} (${pages})</td>

<c:if test="${currentPage < pages}">
    <td>
        <c:if test="${name != null}">
            <a href="matches?page=${currentPage + 1}&filter_by_player_name=${name}">Next</a>
        </c:if>
        <c:if test="${name == null}">
            <a href="matches?page=${currentPage + 1}">Next</a>
        </c:if>
    </td>
</c:if>

</body>
</html>
