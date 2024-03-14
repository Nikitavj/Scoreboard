package com.play.scoreboard.controller;

import com.play.scoreboard.exception.BadRequestException;
import com.play.scoreboard.exception.DatabaseException;
import com.play.scoreboard.exception.NotFoundException;
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
    MatchScoreCalculationServise calculationServise = new MatchScoreCalculationServise();
    FinishedMatchesPersistenceService persistenceService = new FinishedMatchesPersistenceService();
    OngoingMatchesServise ongMatServ = new OngoingMatchesServise();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String uuid = req.getParameter("uuid");
            Validator.validUuid(uuid);
            MatchScoreModel match = ongMatServ.get(uuid);

            req.setAttribute("match", match);
            getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);

        } catch (BadRequestException e) {
            req.setAttribute("message", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);

        } catch (NotFoundException e) {
            req.setAttribute("message", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int winnerNumber = Integer.parseInt(
                req.getParameter("winnerNumber")
                        .replaceAll("/", "")
        );

        try {
            String uuid = req.getParameter("uuid");
            Validator.validUuid(uuid);
            MatchScoreModel match = ongMatServ.get(uuid);
            req.setAttribute("match", match);

            if (calculationServise.isFinished(match, winnerNumber)) {
                persistenceService.save(match);
                ongMatServ.remove(match.getUuid());
                getServletContext().getRequestDispatcher("/final-score.jsp").forward(req, resp);

            } else {
                getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);
            }

        } catch (BadRequestException e) {
            req.setAttribute("message", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);

        } catch (NotFoundException e) {
            req.setAttribute("message", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);

        } catch (DatabaseException e) {
            req.setAttribute("message", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
