package com.play.scoreboard.hibernateDAO;

import com.play.scoreboard.exception.ExceptionDAO;
import com.play.scoreboard.models.EntityHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class BaseDao<T extends EntityHibernate> implements HibernateDAO<T> {
    private final SessionFactory factory;

    protected BaseDao(SessionFactory factory) {
        this.factory = factory;
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public long save(T entity) {
        try(Session session = factory.openSession()) {

            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();

            return entity.getId();
        } catch (Throwable e) {
            throw new ExceptionDAO(e);
        }
    }

    @Override
    public T update(T entity) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            entity =  session.merge(entity);
            session.getTransaction().commit();
            return entity;
        }
    }
}
