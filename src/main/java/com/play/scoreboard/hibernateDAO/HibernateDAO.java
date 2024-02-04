package com.play.scoreboard.hibernateDAO;

import java.util.List;

public interface HibernateDAO<T> {

    long save(T entity);

    List<T> findAll();

    T findById(long id);

    T update(T entity);

    long delete(long id);
}
