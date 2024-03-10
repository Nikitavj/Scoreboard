package com.play.scoreboard.controller;

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
    private static final int DEFAULT_PAGE = 1;
    private static final int SIZE_ROWS_PAGE = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlayerService playerService = new PlayerService();
        SearchForCompletedMatchesService complMatches = new SearchForCompletedMatchesService();

        String pageString = req.getParameter("page");
        String name = req.getParameter("filter_by_player_name");
        List<Match> matches = null;
        int pages = 1;
        int page = 1;

        try {
            if (name != null) {
                Validator.validName(name);
            }

            if (pageString != null) {
                page = Integer.parseInt(pageString);
                Validator.validPage(page);
            }
        } catch (RuntimeException e) {
            req.setAttribute("message", e.getMessage());
            getServletContext().getRequestDispatcher("/exception.jsp").forward(req, resp);
        }

        //все матчи, первая страница
        if (pageString == null && name == null) {
            matches = complMatches.getMatchesByPage(DEFAULT_PAGE, SIZE_ROWS_PAGE);
            pages = (int)Math.ceil(
                    complMatches.getNoOfRecords() * 1.0 / SIZE_ROWS_PAGE);
        }
        //конкретная страница с матчами
        if (pageString != null && name == null) {
            matches = complMatches.getMatchesByPage(page, SIZE_ROWS_PAGE);
            pages = (int)Math.ceil(
                    complMatches.getNoOfRecords() * 1.0 / SIZE_ROWS_PAGE);
        }
        //поиск только по имени, первая страница
        if (pageString == null && name != null) {
            matches =complMatches.getMatchesForName(name, DEFAULT_PAGE, SIZE_ROWS_PAGE);
            pages = (int)Math.ceil(
                    complMatches.getNoOfRecords() * 1.0 / SIZE_ROWS_PAGE);
        }
        //поиск по имени и по странице
        if (pageString != null && name != null) {
            matches = complMatches.getMatchesForName(name, page, SIZE_ROWS_PAGE);
            pages = (int)Math.ceil(
                    complMatches.getNoOfRecords() * 1.0 / SIZE_ROWS_PAGE);
        }

        List<String> names = playerService.getNames();

        req.setAttribute("names", names);
        req.setAttribute("name", name);
        req.setAttribute("matches", matches);
        req.setAttribute("pages", pages);
        req.setAttribute("currentPage", page);
        getServletContext().getRequestDispatcher("/matches.jsp").forward(req, resp);
    }

}
