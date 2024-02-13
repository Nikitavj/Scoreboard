<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--  User: Dell N
  Date: 28.01.2024
  Time: 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = "match-score?uuid=" + request.getParameter("uuid");
    request.setAttribute("path", path);
%>

<html>
<head>
    <title>ScoreBoard</title>
</head>
<body>

<a href="index.html">
    <button>Interrupt</button>
</a>
<br>

<table border="1">
        <tr>
        <th>PREVIOUS</th>
        <th>PREVIOUS</th>
        <th>PREVIOUS</th>
        <th>PLAYER</th>
        <th>SETS</th>
        <th>GAME</th>
        <th>POINTS</th>
    </tr>

    <tr>
        <th>${score.getPrevSets(player1, 1)}</th>
        <th>${score.getPrevSets(player1, 2)}</th>
        <th>${score.getPrevSets(player1, 3)}</th>
        <th>${player1.getName()}</th>
        <th>${score.getSets(player1)}</th>
        <th>${score.getGames(player1)}</th>
        <th>${score.getPoints(player1)}</th>
        <th>
            <form action="${path}" method="POST">
                <input type="hidden" name="idwinner" value="${player1.getId()}"/>
                <input type="submit" value="POINT"/>
            </form>
        </th>
    </tr>
    <tr>
        <th>${score.getPrevSets(player2, 1)}</th>
        <th>${score.getPrevSets(player2, 2)}</th>
        <th>${score.getPrevSets(player2, 3)}</th>
        <th>${player2.getName()}</th>
        <th>${score.getSets(player2)}</th>
        <th>${score.getGames(player2)}</th>
        <th>${score.getPoints(player2)}</th>
        <th>
            <form action="${path}" method="POST">
                <input type="hidden" name="idwinner" value="${player2.getId()}"/>
                <input type="submit" value="POINT"/>
            </form>
        </th>
    </tr>
</table>

</body>
</html>
