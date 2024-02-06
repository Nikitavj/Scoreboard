package com.play.scoreboard.controller;

import com.play.scoreboard.models.Match;
import com.play.scoreboard.servise.SearchComplitedMatchesServise;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/matches")
public class MatchesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var searchService = new SearchComplitedMatchesServise();
        List<Match> matchList = searchService.searchByPlayer("Igor");

        System.out.println(matchList);

        req.setAttribute("matches", matchList);
        getServletContext().getRequestDispatcher("/matches.jsp").forward(req, resp);
    }
}
