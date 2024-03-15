package com.play.scoreboard.match.service;

import com.play.scoreboard.hibernate.HibernateUtil;
import com.play.scoreboard.match.models.Match;
import com.play.scoreboard.match.models.MatchScoreModel;
import com.play.scoreboard.match.dao.MatchDAO;
import com.play.scoreboard.player.dao.PlayerDAO;
import com.play.scoreboard.player.models.Player;
import jakarta.persistence.EntityExistsException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinishedMatchesPersistenceService {
    private static final Logger log = LoggerFactory.getLogger(FinishedMatchesPersistenceService.class);
    MatchDAO matchDAO = new MatchDAO(HibernateUtil.getSessionFactory());
    PlayerDAO playerDAO = new PlayerDAO(HibernateUtil.getSessionFactory());
    public long save(MatchScoreModel model) {
        Match match = new Match();
        Player player1 = model.getPlayer1();
        Player player2 = model.getPlayer2();

        try {
            playerDAO.save(player1);
            match.setPlayer1(player1);
            log.info("Saved the {} object to database.", player1);

        } catch (EntityExistsException e) {
            match.setPlayer1(
                    playerDAO.findByName(player1.getName()).get());
        }

        try {
            playerDAO.save(player2);
            match.setPlayer2(player2);
            log.info("Saved the {} object to database.", player2);

        } catch (EntityExistsException e) {
            match.setPlayer2(
                    playerDAO.findByName(player2.getName()).get());
        }

        match.setWinner(model.getWinner());
        log.info("Saved the {} object to database.", match);
        return matchDAO.save(match);
    }
}
