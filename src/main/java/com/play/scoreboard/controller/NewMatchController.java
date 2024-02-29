package com.play.scoreboard.controller;

import com.play.scoreboard.models.MatchScoreModel;
import com.play.scoreboard.servise.OngoingMatchesServise;
import com.play.scoreboard.servise.PlayerServise;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext()
                .getRequestDispatcher("/new-match.html")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //проверить параметры запроса, заполнены ли поля
        String name1 = req.getParameter("name1");
        String name2 = req.getParameter("name2");

        try {
            Validator.validNames(name1, name2);

            //проверить существование игроков в таблице и создать если не существуют
            var regServise = new PlayerServise();
            var player1 = regServise.add(name1);
            var player2 = regServise.add(name2);

            MatchScoreModel match = new OngoingMatchesServise()
                    .startNewMatch(player1, player2);

            resp.sendRedirect("/match-score?uuid=" + match.getUuid());
        } catch (RuntimeException e) {
            req.setAttribute("message", e.getMessage());
            getServletContext().getRequestDispatcher("/exception.jsp").forward(req, resp);
        }
    }
}
