<%--
  Created by IntelliJ IDEA.
  User: Dell N
  Date: 29.01.2024
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Finish Match</title>
</head>
<body>

<a href="new-match">
    <button>New Match</button>
</a>
<br>

<a href="matches">
    <button>Matches</button>
</a>
<br>

<table border="1">
    <tr>
        <c:if test="${score.getTieBreak()}">Tie-break</c:if>
        <c:if test="${score.getEqually()}">Equally</c:if>
    </tr>

    <tr>
        <th>PREVIOUS</th>
        <th>PREVIOUS</th>
        <th>PREVIOUS</th>
        <th>PLAYER</th>
        <th>SETS</th>
    </tr>

    <tr>
        <th>${score.getPrevSets(player1, 1)}</th>
        <th>${score.getPrevSets(player1, 2)}</th>
        <th>${score.getPrevSets(player1, 3)}</th>
        <th>${player1.getName()}</th>
        <th>${score.getSets(player1)}</th>
    </tr>
    <tr>
        <th>${score.getPrevSets(player2, 1)}</th>
        <th>${score.getPrevSets(player2, 2)}</th>
        <th>${score.getPrevSets(player2, 3)}</th>
        <th>${player2.getName()}</th>
        <th>${score.getSets(player2)}</th>
    </tr>
</table>

</body>
</html>