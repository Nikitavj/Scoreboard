<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--  User: Dell N
  Date: 28.01.2024
  Time: 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/normalize.css">
<link type="text/css" rel="stylesheet" href="css/style_index.css"/>
<link type="text/css" rel="stylesheet" href="css/style_match_score.css"/>
<%
    String path = "match-score?uuid=" + request.getParameter("uuid");
    request.setAttribute("path", path);
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ScoreBoard</title>
</head>
<body>
<header class="header">
    <div class="container">
        <nav class="nav">
            <a class="logo-left">
                <img class="logo-img" src="images/ball.png">
            </a>
            <div class="header-text">Match score</div>
            <a class="logo-right">
                <img class="logo-img" src="images/raketka.png">
            </a>
        </nav>
    </div>
</header>
<main class="main">
    <div class="container-main">

        <nav class="nav-main">
            <a class="headline-btn" href="index.html">Finish</a>

            <div class="board">
                <div class="column">
                    <div class="title">Previous sets</div>
                    <div class="wind">
                        <c:forEach items="${match.getPreviousSetPlayer(0)}" var="set">
                        <div class="win">${set}</div>
                            </c:forEach>
                    </div>
                    <div class="wind">
                        <c:forEach items="${match.getPreviousSetPlayer(1)}" var="set">
                            <div class="win">${set}</div>
                        </c:forEach>
                    </div>
                </div>

                <div class="column">
                    <div class="orderPlayers">Players</div>
                    <div class="wind">
                        <div class="win-name">${match.getPlayer1().getName()}</div>
                    </div>
                    <div class="wind">
                        <div class="win-name">${match.getPlayer2().getName()}</div>
                    </div>
                </div>

                <div class="column">
                    <div class="title">Sets</div>
                    <div class="wind">
                        <div class="win">${match.getSetsPlayer(0)}</div>
                    </div>
                    <div class="wind">
                        <div class="win">${match.getSetsPlayer(1)}</div>
                    </div>
                </div>


                <c:if test="${match.isTiebreak()}">
                    <div class="tiebreak">
                        <div class="tiebreak-text">Tiebreak</div>
                    </div>
                </c:if>

                <div class="column">
                    <div class="title">Games</div>
                    <div class="wind">
                        <div class="win">${match.getGamesPlayer(0)}</div>
                    </div>
                    <div class="wind">
                        <div class="win">${match.getGamesPlayer(1)}</div>
                    </div>
                </div>

                <div class="column">
                    <div cclass="title">
                        <div class="points">Points</div>
                    </div>
                    <div class="wind">
                        <div class="win-sub">
                            <form action="${path}" method="POST">
                                <input type="hidden" name="winnerNumber" value="0"/>
                                <input class="point-sub" type="submit" value="${match.getPointsPlayer(0)}"/>
                            </form>
                        </div>
                    </div>
                    <div class="wind">
                        <div class="win-sub">
                            <form action="${path}" method="POST">
                                <input type="hidden" name="winnerNumber" value="1"/>
                                <input class="point-sub" type="submit" value="${match.getPointsPlayer(1)}"/>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </nav>
    </div>
</main>

<footer class="footer">
    <div class="container">
        <div class="footer-inner">
        </div>
    </div>
</footer>
</body>
</html>
