package com.play.scoreboard.servise;

import com.play.scoreboard.HibernateUtil;
import com.play.scoreboard.hibernateDAO.PlayerDAO;
import com.play.scoreboard.models.Player;

import java.util.List;
import java.util.Optional;

public class PlayerServise {
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
