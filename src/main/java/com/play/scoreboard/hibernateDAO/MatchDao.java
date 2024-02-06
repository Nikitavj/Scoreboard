package com.play.scoreboard.hibernateDAO;

import com.play.scoreboard.models.Match;
import com.play.scoreboard.models.Player;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MatchDao extends BaseDao<Match> implements MatchHibernateDAO {
    private final SessionFactory factory;

    public MatchDao(SessionFactory factory) {
        super(factory);
        this.factory = factory;
    }

    @Override
    public List<Match> findAll() {
        return null;
    }

    @Override
    public Match findById(long id) {
        try(Session session = factory.openSession()) {
            session.getTransaction().begin();
            Match match = session.get(Match.class, id);
            session.getTransaction().commit();
            return match;
        }
    }

    public List<Match> findByNamePlayer(String name) {
        try(Session session = factory.openSession()) {
            session.getTransaction().begin();

            Query query = session.createQuery("from Match where player1.name =: namePlayer", Match.class);
            query.setParameter("namePlayer", name);
            List<Match> matchesOfPlayer = query.getResultList();
            session.getTransaction().commit();

            return matchesOfPlayer;
        }
    }

    @Override
    public long delete(long id) {
        return 0;
    }
}
