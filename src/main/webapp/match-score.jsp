<%--  User: Dell N
  Date: 28.01.2024
  Time: 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = "match-score?uuid=" + request.getParameter("uuid");
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
        <th>Player1</th>
        <th></th>
        <th>Player2</th>
    </tr>
    <tr>
        <td>${name1}</td>
        <td>Name</td>
        <td>${name2}</td>
    </tr>
    <tr>
        <td>${sets1}</td>
        <td>Sets</td>
        <td>${sets2}</td>
    </tr>
    <tr>
        <td>${games1}</td>
        <td>Games</td>
        <td>${games2}</td>
    </tr>
    <tr>
        <td>${points1}</td>
        <td>Points</td>
        <td>${points2}</td>
    </tr>
    <tr>
        <td></td>
        <td><%=(boolean) request.getAttribute("tie-breake") ? "Tie-break" : ""%>
        </td>
        <td><%=(boolean) request.getAttribute("equally") ? "РОВНО" : ""%>
        </td>
        <td></td>
    </tr>
    <tr>
        <td>
            <form action${path1} method="POST">
                <input type="hidden" name="idwinner" value="${id1}"/>
                <input type="submit" value="POINT"/>
            </form>
        </td>

        <td></td>

        <td>
            <form action${path2} method="POST">
                <input type="hidden" name="idwinner" value="${id2}"/>
                <input type="submit" value="POINT"/>
            </form>
        </td>
    </tr>
</table>

</body>
</html>
