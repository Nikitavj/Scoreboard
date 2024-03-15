package com.play.scoreboard.controller;

import com.play.scoreboard.exception.BadRequestException;
import com.play.scoreboard.match.models.MatchScoreModel;
import com.play.scoreboard.match.service.OngoingMatchesServise;
import com.play.scoreboard.player.models.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(NewMatchController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/new-match.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name1 = req.getParameter("name1");
        String name2 = req.getParameter("name2");
        log.info("Parameters received: name1 = {}, name2 = {}.", name1, name2);

        try {
            Validator.validNames(name1, name2);
            Player player1 = new Player(name1.trim());
            Player player2 = new Player(name2.trim());
            MatchScoreModel match = new OngoingMatchesServise()
                    .startNewMatch(player1, player2);

            resp.sendRedirect("/match-score?uuid=" + match.getUuid());

        } catch (BadRequestException e) {
            log.warn("Exception in the validNames method of Validator.", e);
            req.setAttribute("message", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
