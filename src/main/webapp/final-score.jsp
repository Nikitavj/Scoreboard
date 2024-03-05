<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell N
  Date: 29.01.2024
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/normalize.css">
<link type="text/css" rel="stylesheet" href="css/style_index.css"/>
<link type="text/css" rel="stylesheet" href="css/style_final_score.css"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Finish Match</title>
</head>
<body>
<header class="header">
    <div class="container">
        <nav class="nav">
            <a class="logo-left">
                <img class="logo-img" src="images/ball.png">
            </a>
            <div class="header-text">Finish match</div>
            <a class="logo-right">
                <img class="logo-img" src="images/raketka.png">
            </a>
        </nav>
    </div>
</header>

<main class="main">
    <div class="container-main">

        <nav class="nav-main">

            <a class="headline-btn" href="new-match">New Match</a>
            <a class="headline-btn" href="matches">Matches</a>

            <div class="board">
                <div class="column">
                    <div class="title">Sets</div>
                    <div class="wind">
                        <div class="win">1</div>
                        <div class="win">1</div>
                        <div class="win">1</div>
                    </div>
                    <div class="wind">
                        <div class="win">2</div>
                        <div class="win">2</div>
                        <div class="win">2</div>
                    </div>
                </div>

                <div class="column">
                    <div class="orderPlayers">Players</div>
                    <div class="orderPlayers-div">

                        <div class="wind">
                            <div class="win-name">${match.getPlayer1().getName()}</div>

                            <c:if test="${match.getWinner().equals(match.getPlayer1())}">
                                <img class="logo-cup-img" src="images/cup.png">
                            </c:if>

                        </div>

                        <div class="wind">
                            <div class="win-name">${match.getPlayer1().getName()}</div>
                            <c:if test="${match.getWinner().equals(match.getPlayer2())}">
                                <img class="logo-cup-img" src="images/cup.png">
                            </c:if>
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