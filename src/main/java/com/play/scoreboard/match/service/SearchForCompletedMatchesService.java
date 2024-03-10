package com.play.scoreboard.match.service;

import com.play.scoreboard.hibernate.HibernateUtil;
import com.play.scoreboard.match.models.Match;
import com.play.scoreboard.match.dao.MatchDAO;

import java.util.List;

public class SearchForCompletedMatchesService {
    MatchDAO dao = new MatchDAO(HibernateUtil.getSessionFactory());

    public List<Match> getMatchesForName(String name, int page, int sizePage) {

        return dao.findByNamePlayer(name, page, sizePage);
    }

    public List<Match> getMatchesByPage(int page, int sizePage) {
        return dao.findByPage(page, sizePage);
    }


    public Long getNoOfRecords() {
        return dao.getNoOfRecords();
    }
}
