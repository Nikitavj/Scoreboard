package com.play.scoreboard.controller;

import com.play.scoreboard.match.service.FinishedMatchesPersistenceService;
import com.play.scoreboard.match.service.MatchScoreCalculationServise;
import com.play.scoreboard.match.models.MatchScoreModel;
import com.play.scoreboard.match.service.OngoingMatchesServise;
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

        try {
            String uuid = req.getParameter("uuid");
            MatchScoreModel match = ongMatServ.get(uuid);

            req.setAttribute("match", match);
            getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);

        } catch (RuntimeException e) {
            req.setAttribute("message", e.getMessage());
            getServletContext().getRequestDispatcher("/exception.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MatchScoreCalculationServise calculationServise = new MatchScoreCalculationServise();
        FinishedMatchesPersistenceService persistenceService = new FinishedMatchesPersistenceService();

        int winnerNumber = Integer.parseInt(
                req.getParameter("winnerNumber")
                        .replaceAll("/", "")
        );

        try {
            MatchScoreModel match = ongMatServ.get(req.getParameter("uuid"));
            //Validator.validIdForMatch(idWin, match);
            req.setAttribute("match", match);
            if (calculationServise.isFinished(match, winnerNumber)) {
                persistenceService.save(match);
                ongMatServ.remove(match.getUuid());
                getServletContext()
                        .getRequestDispatcher("/final-score.jsp")
                        .forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);
            }
        } catch (RuntimeException e) {
            req.setAttribute("message", e.getMessage());
            getServletContext().getRequestDispatcher("/exception.jsp").forward(req, resp);
        }
    }
}
