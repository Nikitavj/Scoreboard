package com.play.scoreboard.player.dao;

import com.play.scoreboard.hibernate.HibernateDAO;
import com.play.scoreboard.player.models.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerHibernateDAO extends HibernateDAO<Player> {
    Optional<Player> findByName(String name);

    List<String> getAllNames();
}
