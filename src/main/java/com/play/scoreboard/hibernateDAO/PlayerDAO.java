package com.play.scoreboard.hibernateDAO;

import com.play.scoreboard.models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class PlayerDAO extends BaseDao<Player> implements PlayerHibernateDAO {
    private final SessionFactory factory;

    public PlayerDAO(SessionFactory factory) {
        super(factory);
        this.factory = factory;
    }

    @Override
    public List<Player> findAll() {
        return null;
    }

    @Override
    public Player findById(long id) {
        return null;
    }

    public Optional<Player> findByName(String name) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Player player = session
                    .createQuery("from Player where name = :paramName", Player.class)
                    .setParameter("paramName", name)
                    .getSingleResultOrNull();
            session.getTransaction().commit();

            return Optional.ofNullable(player);
        }
    }

    @Override
    public long delete(long id) {
        return 0;
    }
}
