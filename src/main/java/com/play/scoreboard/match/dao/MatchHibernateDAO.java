package com.play.scoreboard.match.dao;

import com.play.scoreboard.hibernate.HibernateDAO;
import com.play.scoreboard.match.models.Match;

import java.util.List;

public interface MatchHibernateDAO extends HibernateDAO<Match> {
    List<Match> findByPage(int page, int size);

    Long getNoOfRecordsAll();

    Long getNoOfRecordsByPlayer(String Name);

    List<Match> findByNamePlayer(String name, int page, int size);
}
