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

<form action="new-match">
    <input type="submit" value="New Match">
</form>
<form action="matches">
    <input type="submit" value="Matches">
</form>
<br>

<table border="1">
    <tr>
        <td>Player1</td>
        <td>Player1</td>
        <td>Winner</td>
    </tr>
    <tr>
        <td>${match.getPlayer1().getName()}</td>
        <td>${match.getPlayer2().getName()}</td>
        <td>${match.getWinner().getName()}</td>
    </tr>
</table>

</body>
</html>
