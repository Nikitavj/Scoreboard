package com.play.scoreboard.match.dao;

import com.play.scoreboard.exception.DatabaseException;
import com.play.scoreboard.hibernate.BaseDAO;
import com.play.scoreboard.match.models.Match;
import jakarta.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MatchDAO extends BaseDAO<Match> implements MatchHibernateDAO {
    private final SessionFactory factory;
    private Long noOfRecords;

    public MatchDAO(SessionFactory factory) {
        super(factory);
        this.factory = factory;
    }

    @Override
    public List<Match> findAll() {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            List<Match> matches = session.createQuery("from Match", Match.class).getResultList();
            session.getTransaction().commit();
            return matches;
        } catch (HibernateException e) {
            throw new DatabaseException(e);
        }
    }

    public List<Match> findByPage(int page, int size) {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();

            noOfRecords = session.createQuery("select count (id) from Match", Long.class).uniqueResult();
            int pages = (int) Math.ceil(noOfRecords * 1.0 / size);
            if (page > pages) {
                page = ++pages;
            }

            Query query = session.createQuery("from Match", Match.class);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            List<Match> matches = query.getResultList();

            session.getTransaction().commit();
            return matches;
        } catch (HibernateException e) {
            throw new DatabaseException(e);
        }
    }

    public Long getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public Match findById(long id) {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            Match match = session.get(Match.class, id);
            session.getTransaction().commit();
            return match;
        } catch (HibernateException e) {
            throw new DatabaseException(e);
        }
    }

    public List<Match> findByNamePlayer(String name, int page, int size) {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();

            Query query1 = session.createQuery(
                    "select count (id) from Match where player1.name = :name or player2.name = :name", Long.class);
            query1.setParameter("name", name);
            noOfRecords = (Long) query1.getResultList().get(0);

            int pages = (int) Math.ceil(noOfRecords * 1.0 / size);
            if (page > pages) {
                page = ++pages;
            }

            Query query = session.createQuery("from Match where player1.name =:name or player2.name =:name", Match.class);
            query.setParameter("name", name);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            List<Match> matchesOfPlayer = query.getResultList();
            session.getTransaction().commit();
            return matchesOfPlayer;
        } catch (HibernateException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public long delete(long id) {
        return 0;
    }
}
