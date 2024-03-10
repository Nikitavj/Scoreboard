package com.play.scoreboard.match.service;

import com.play.scoreboard.hibernate.HibernateUtil;
import com.play.scoreboard.match.models.Match;
import com.play.scoreboard.match.models.MatchScoreModel;
import com.play.scoreboard.match.dao.MatchDAO;
import com.play.scoreboard.player.dao.PlayerDAO;
import com.play.scoreboard.player.models.Player;
import jakarta.persistence.EntityExistsException;
import org.hibernate.exception.ConstraintViolationException;

public class FinishedMatchesPersistenceService {
    MatchDAO matchDAO = new MatchDAO(HibernateUtil.getSessionFactory());
    PlayerDAO playerDAO = new PlayerDAO(HibernateUtil.getSessionFactory());
    public long save(MatchScoreModel model) {
        Match match = new Match();
        Player player1 = model.getPlayer1();
        Player player2 = model.getPlayer2();

        try {
            playerDAO.save(player1);
            match.setPlayer1(player1);
        } catch (EntityExistsException e) {
            match.setPlayer1(
                    playerDAO.findByName(player1.getName()).get());
        }

        try {
            playerDAO.save(player2);
            match.setPlayer2(player2);
        } catch (EntityExistsException e) {
            match.setPlayer2(
                    playerDAO.findByName(player2.getName()).get());
        }

        match.setWinner(model.getWinner());

        return matchDAO.save(match);
    }
}
