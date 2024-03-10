package com.play.scoreboard.controller;

import com.play.scoreboard.match.models.MatchScoreModel;
import com.play.scoreboard.match.service.OngoingMatchesServise;
import com.play.scoreboard.player.models.Player;
import com.play.scoreboard.player.service.PlayerService;
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
        String name1 = req.getParameter("name1");
        String name2 = req.getParameter("name2");

        try {
            Validator.validNames(name1, name2);
//            PlayerService regServise = new PlayerService();
//            var player1 = regServise.add(name1);
//            var player2 = regServise.add(name2);

            Player player1 = new Player(name1);
            Player player2 = new Player(name2);

            MatchScoreModel match = new OngoingMatchesServise()
                    .startNewMatch(player1, player2);

            resp.sendRedirect("/match-score?uuid=" + match.getUuid());
        } catch (RuntimeException e) {
            req.setAttribute("message", e.getMessage());
            getServletContext().getRequestDispatcher("/exception.jsp").forward(req, resp);
        }
    }
}
