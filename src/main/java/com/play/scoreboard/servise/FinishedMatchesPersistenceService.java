package com.play.scoreboard.servise;

import com.play.scoreboard.HibernateUtil;
import com.play.scoreboard.hibernateDAO.MatchDao;
import com.play.scoreboard.models.Match;
import com.play.scoreboard.models.MatchScoreModel;

public class FinishedMatchesPersistenceService {
    MatchDao dao = new MatchDao(HibernateUtil.getSessionFactory());

    public long save(MatchScoreModel model) {
        Match match = new Match();
        match.setPlayer1(model.getPlayer1());
        match.setPlayer2(model.getPlayer2());
        match.setWinner(model.getWinner());

        return dao.save(match);
    }
}
