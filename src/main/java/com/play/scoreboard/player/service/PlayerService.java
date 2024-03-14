package com.play.scoreboard.player.service;

import com.play.scoreboard.hibernate.HibernateUtil;
import com.play.scoreboard.player.dao.PlayerDAO;
import com.play.scoreboard.player.models.Player;

import java.util.List;
import java.util.Optional;

public class PlayerService {
    private PlayerDAO dao = new PlayerDAO(HibernateUtil.getSessionFactory());

    public Player add(String name) {
        Optional<Player> opt = dao.findByName(name);

        if (opt.isPresent()) {
            return opt.get();
        } else {
            var player = new Player(name);
            dao.save(player);
            return player;
        }
    }

    public List<String> getNames() {
        return dao.getAllNames();
    }
}
