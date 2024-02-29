<%--
  Created by IntelliJ IDEA.
  User: Nikit
  Date: 22.02.2024
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style_index.css">
<link rel="stylesheet" href="css/style_exception.css">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
</head>
<body>
<header class="header">
    <div class="container">
        <nav class="nav">
            <a class="logo-left">
                <img class="logo-img" src="images/ball.png">
            </a>
            <div class="header-text">Error</div>
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
                <p>
                <h2>${message}</h2>
                </p>
            </div>

            <a class="headline-btn" href="new-match.html">New Match</a>
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
