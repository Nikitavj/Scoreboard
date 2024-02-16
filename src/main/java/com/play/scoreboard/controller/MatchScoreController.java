package com.play.scoreboard.controller;

import com.play.scoreboard.HibernateUtil;
import com.play.scoreboard.hibernateDAO.MatchDao;
import com.play.scoreboard.hibernateDAO.PlayerDAO;
import com.play.scoreboard.models.Match;
import com.play.scoreboard.models.MatchScoreModel;
import com.play.scoreboard.models.Player;
import com.play.scoreboard.servise.*;
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
        MatchScoreModel match = ongMatServ.get(uuid);

        ScoreGame score = match.getScore();
        Player player1 = match.getPlayer1();
        Player player2 = match.getPlayer2();

        req.setAttribute("player1", player1);
        req.setAttribute("player2", player2);
        req.setAttribute("score", score);

        getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MatchScoreCalculationServise scoreServ = new MatchScoreCalculationServise();
        FinishedMatchesPersistenceService finishServ = new FinishedMatchesPersistenceService();

        String idString = req.getParameter("idwinner");
        Long idWin = Long.parseLong(
                idString
                .replaceAll("/", "")
        );

        MatchScoreModel match = ongMatServ.get(req.getParameter("uuid"));
        Validator.validIdForMatch(idWin, match);
        Player winner = match.getPlayerById(idWin);
        ScoreGame score = match.getScore();
        score.addPoint(winner);

        if (scoreServ.calculate(score, winner)) {
            match.setWinner(winner);
            finishServ.save(match);

            getServletContext()
                    .getRequestDispatcher("/final-score.jsp")
                    .forward(req, resp);
        }

        resp.sendRedirect("/match-score" + "?uuid=" + match.getUuid());
    }
}
