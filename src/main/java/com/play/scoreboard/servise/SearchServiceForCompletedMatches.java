package com.play.scoreboard.servise;

import com.play.scoreboard.HibernateUtil;
import com.play.scoreboard.hibernateDAO.MatchDao;
import com.play.scoreboard.models.Match;

import java.util.List;

public class SearchServiceForCompletedMatches {
    MatchDao dao = new MatchDao(HibernateUtil.getSessionFactory());

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
