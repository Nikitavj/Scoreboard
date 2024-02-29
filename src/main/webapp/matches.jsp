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
<link rel="stylesheet" href="css/normalize.css">
<link type="text/css" rel="stylesheet" href="css/style_index.css"/>
<link type="text/css" rel="stylesheet" href="css/style_matches.css"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Matches</title>
</head>
<body>
<header class="header">
    <div class="container">
        <nav class="nav">
            <a class="logo-left">
                <img class="logo-img" src="images/ball.png">
            </a>
            <div class="header-text">Matches</div>
            <a class="logo-right">
                <img class="logo-img" src="images/raketka.png">
            </a>
        </nav>
    </div>
</header>

<main class="main">
    <div class="container-main">
        <nav class="nav-main">

            <div class="board">

                <div class="match-buttons">
                    <a href="matches">
                        <button>All Matches</button>
                    </a>

                    <a href="new-match">
                        <button>New match</button>
                    </a>
                </div>

                <form class="form-name" action="matches" method="get">
                    <input class="input-name" name="filter_by_player_name" type="text" list="namesPlayers"
                           placeholder="${name}" required>
                    <input class="submit-point" type="submit" value="Search">
                </form>

                <datalist id="namesPlayers">
                    <c:forEach items="${names}" var="namePlayer">
                        <option value="${namePlayer}">${namePlayer}</option>
                    </c:forEach>
                </datalist>

                <div class="table-div">
                    <table class="table-matches" border="1">
                        <tr>
                            <th></th>
                            <th>Player1</th>
                            <th>Player2</th>
                            <%--                            <th>Winner</th>--%>
                        </tr>
                        <c:forEach var="match" items="${matches}">
                            <tr>
                                <td>${match.getId()}</td>
                                <td>
                                    <c:if test="${match.getPlayer1().equals(match.getWinner())}">
                                        <img class="logo-cup-img" src="images/cup.png">
                                    </c:if>
                                        ${match.getPlayer1().getName()}
                                </td>
                                <td>
                                    <c:if test="${match.getPlayer2().equals(match.getWinner())}">
                                        <img class="logo-cup-img" src="images/cup.png">
                                    </c:if>
                                        ${match.getPlayer2().getName()}
                                </td>
                                    <%--                                <td>${match.getWinner().getName()}</td>--%>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="navigate-board">
                    <div class="button-navigate">
                        <c:if test="${currentPage > 1}">
                            <td>
                                <c:if test="${name != null}">
                                    <a href="matches?page=${currentPage - 1}&filter_by_player_name=${name}">
                                        <button>Back</button>
                                    </a>
                                </c:if>
                                <c:if test="${name == null}">
                                    <a href="matches?page=${currentPage - 1}">
                                        <button>Back</button>
                                    </a>
                                </c:if>
                            </td>
                        </c:if>
                    </div>

                    <div class="button-navigate">
                        <p>${currentPage} (${pages})</p>
                    </div>

                    <div class="button-navigate">
                        <c:if test="${currentPage < pages}">
                            <td>
                                <c:if test="${name != null}">
                                    <a href="matches?page=${currentPage + 1}&filter_by_player_name=${name}">
                                        <button>Next</button>
                                    </a>
                                </c:if>
                                <c:if test="${name == null}">
                                    <a href="matches?page=${currentPage + 1}">
                                        <button>Next</button>
                                    </a>
                                </c:if>
                            </td>
                        </c:if>
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
