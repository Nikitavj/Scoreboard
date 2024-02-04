package com.play.scoreboard;

import com.play.scoreboard.hibernateDAO.MatchDao;
import com.play.scoreboard.hibernateDAO.PlayerDAO;
import com.play.scoreboard.models.Match;
import com.play.scoreboard.models.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class App {
    public static void main(String[] args) {

        Player player = new Player();
        player.setName("Lev");

        PlayerDAO playerDAO = new PlayerDAO(HibernateUtil.getSessionFactory());
        long id = playerDAO.save(player);
        System.out.println("saved id player = " + id);

        MatchDao matchDao = new MatchDao(HibernateUtil.getSessionFactory());
        Match match = new Match();
        long id1 = matchDao.save(match);
        System.out.println("saved id match = " + id1);



        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            List<Player> list1 = session.createQuery("from Player", Player.class).list();

            for(Player p: list1) {
                System.out.println(p);
            }

            List<Match> list2 = session.createQuery("from Match ", Match.class).list();

            for(Match p: list2) {
                System.out.println(p);
            }

            System.out.println("________save player1____________");


            List<Player> list3 = session.createQuery("from Player", Player.class).list();

            for(Player p: list3) {
                System.out.println(p);
            }

            List<Match> list4 = session.createQuery("from Match ", Match.class).list();

            for(Match p: list4) {
                System.out.println(p);
            }


            transaction.commit();
        }







    }
}
