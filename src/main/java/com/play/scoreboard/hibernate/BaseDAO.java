package com.play.scoreboard.hibernate;

import com.play.scoreboard.exception.DatabaseException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class BaseDAO<T extends EntityHibernate> implements HibernateDAO<T> {
    private final SessionFactory factory;

    protected BaseDAO(SessionFactory factory) {
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

        } catch (HibernateException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public T update(T entity) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            entity =  session.merge(entity);
            session.getTransaction().commit();
            return entity;

        } catch (HibernateException e) {
            throw new DatabaseException(e);
        }
    }
}
