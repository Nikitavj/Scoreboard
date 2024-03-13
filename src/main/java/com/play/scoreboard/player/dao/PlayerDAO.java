package com.play.scoreboard.player.dao;

import com.play.scoreboard.exception.DatabaseException;
import com.play.scoreboard.hibernate.BaseDAO;
import com.play.scoreboard.player.models.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class PlayerDAO extends BaseDAO<Player> implements PlayerHibernateDAO {
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

        } catch (HibernateException e) {
            throw new DatabaseException(e);
        }
    }

    public List<String> getAllNames() {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            List<String> names = session.createQuery("select name from Player", String.class).getResultList();
            session.getTransaction().commit();
            return names;

        } catch (HibernateException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public long delete(long id) {
        return 0;
    }
}
