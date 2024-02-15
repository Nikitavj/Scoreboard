package com.play.scoreboard.controller;

import com.play.scoreboard.HibernateUtil;
import com.play.scoreboard.hibernateDAO.MatchDao;
import com.play.scoreboard.models.Match;
import com.play.scoreboard.servise.PlayerServise;
import com.play.scoreboard.servise.SearchServiceForCompletedMatches;
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
        PlayerServise playerServise = new PlayerServise();
        SearchServiceForCompletedMatches complMatches = new SearchServiceForCompletedMatches();

        String pageString = req.getParameter("page");
        String name = req.getParameter("filter_by_player_name");
        if (name != null) {
            Validator.validName(name);
        }

        List<Match> matches = null;
        int pages = 1;
        int page = 1;
        if (pageString != null) {
            page = Integer.parseInt(pageString);
            Validator.validPage(page);
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

        List<String> names = playerServise.getNames();

        req.setAttribute("names", names);
        req.setAttribute("name", name);
        req.setAttribute("matches", matches);
        req.setAttribute("pages", pages);
        req.setAttribute("currentPage", page);
        getServletContext().getRequestDispatcher("/matches.jsp").forward(req, resp);
    }

}
