package com.play.scoreboard.controller;

import com.play.scoreboard.models.Player;
import com.play.scoreboard.servise.MatchScoreCalculationServise;
import com.play.scoreboard.servise.OngoingMatchesServise;
import com.play.scoreboard.servise.Score;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {
    OngoingMatchesServise ongMatServ = new OngoingMatchesServise();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uuid = req.getParameter("uuid");
        var match = ongMatServ.get(uuid);

        Score score = match.getScore();
        Player player1 = match.getPlayer1();
        Player player2 = match.getPlayer2();
        req.setAttribute("name1", player1.getName());
        req.setAttribute("name2", player2.getName());
        req.setAttribute("id1", player1.getId());
        req.setAttribute("id2", player2.getId());
        req.setAttribute("sets1", score.getSets(player1));
        req.setAttribute("sets2", score.getSets(player2));
        req.setAttribute("games1", score.getGames(player1));
        req.setAttribute("games2", score.getGames(player2));
        req.setAttribute("points1", score.getPoints(player1));
        req.setAttribute("points2", score.getPoints(player2));
        req.setAttribute("tie-breake", score.getTieBreak());
        req.setAttribute("equally", score.getEquallPoints());


        getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("idwinner");
        Long idWin = Long.parseLong(
                idString
                .replaceAll("/", "")
        );

        var match = ongMatServ.get(req.getParameter("uuid"));

        MatchScoreCalculationServise scoreServ = new MatchScoreCalculationServise(match);

        if (scoreServ.calculate(idWin)) {
            getServletContext().getRequestDispatcher("/final-score.jsp").forward(req, resp);
        }

        Score score = match.getScore();
        Player player1 = match.getPlayer1();
        Player player2 = match.getPlayer2();
        req.setAttribute("name1", player1.getName());
        req.setAttribute("name2", player2.getName());
        req.setAttribute("id1", player1.getId());
        req.setAttribute("id2", player2.getId());
        req.setAttribute("sets1", score.getSets(player1));
        req.setAttribute("sets2", score.getSets(player2));
        req.setAttribute("games1", score.getGames(player1));
        req.setAttribute("games2", score.getGames(player2));
        req.setAttribute("points1", score.getPoints(player1));
        req.setAttribute("points2", score.getPoints(player2));
        req.setAttribute("tie-breake", score.getTieBreak());
        req.setAttribute("equally", score.getEquallPoints());

        getServletContext()
                .getRequestDispatcher("/match-score.jsp")
                .forward(req, resp);
    }
}
