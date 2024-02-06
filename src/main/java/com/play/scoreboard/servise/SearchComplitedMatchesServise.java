package com.play.scoreboard.servise;

import com.play.scoreboard.HibernateUtil;
import com.play.scoreboard.hibernateDAO.MatchDao;
import com.play.scoreboard.models.Match;

import java.util.List;

public class SearchComplitedMatchesServise {

    public List<Match> searchByPlayer(String name) {
        MatchDao dao = new MatchDao(HibernateUtil.getSessionFactory());
        return dao.findByNamePlayer(name);
    }

}
