package com.play.scoreboard.controller;

import com.play.scoreboard.exception.BadRequestException;
import com.play.scoreboard.exception.DatabaseException;
import com.play.scoreboard.match.models.Match;
import com.play.scoreboard.player.service.PlayerService;
import com.play.scoreboard.match.service.SearchForCompletedMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesController extends HttpServlet {
    PlayerService playerService = new PlayerService();
    SearchForCompletedMatchesService complMatchServise = new SearchForCompletedMatchesService();
    private static final int DEFAULT_PAGE = 1;
    private static final int SIZE_ROWS_PAGE = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> names = null;
        List<Match> matches = null;
        int pages = DEFAULT_PAGE;
        int page = DEFAULT_PAGE;

        String pageString = req.getParameter("page");
        String name = req.getParameter("filter_by_player_name");
        try {
            if (pageString != null) {
                Validator.validPage(pageString);
                page = Integer.parseInt(pageString.trim());
            }

            if (name != null) {
                Validator.validName(name);
                name = name.trim();
            }

            if (pageString == null) {
                if (name == null) {
                    matches = complMatchServise.getMatchesByPage(
                            DEFAULT_PAGE,
                            SIZE_ROWS_PAGE);
                } else {
                    matches = complMatchServise.getMatchesForName(
                            name,
                            DEFAULT_PAGE,
                            SIZE_ROWS_PAGE);
                }
            } else {
                if (name == null) {
                    matches = complMatchServise.getMatchesByPage(
                            page,
                            SIZE_ROWS_PAGE);
                } else {
                    matches = complMatchServise.getMatchesForName(
                            name,
                            page,
                            SIZE_ROWS_PAGE);
                }
            }

            pages = (int) Math.ceil(complMatchServise.getNoOfRecords() * 1.0 / SIZE_ROWS_PAGE);
            names = playerService.getNames();

        } catch (BadRequestException e) {
            req.setAttribute("message", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            getServletContext().
                    getRequestDispatcher("/exception.jsp").
                    forward(req, resp);

        } catch (NumberFormatException e) {
            req.setAttribute("message", "Параметр page должен содержать только одно число");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            getServletContext().
                    getRequestDispatcher("/exception.jsp").
                    forward(req, resp);

        } catch (DatabaseException e) {
            req.setAttribute("message", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            getServletContext().
                    getRequestDispatcher("/exception.jsp").
                    forward(req, resp);
        }

        req.setAttribute("name", name);
        req.setAttribute("names", names);
        req.setAttribute("matches", matches);
        req.setAttribute("pages", pages);
        req.setAttribute("currentPage", page);
        getServletContext().
                getRequestDispatcher("/matches.jsp").
                forward(req, resp);
    }

}
