package com.play.scoreboard.hibernate;

import com.play.scoreboard.exception.DatabaseException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        String sqlQuery;

        try {
            URL initSql = HibernateUtil.class.getClassLoader().getResource("init.sql");
            Path path = Paths.get(initSql.toURI());
            List<String> list = Files.readAllLines(path);
            sqlQuery = String.join("", list);
        } catch (URISyntaxException e) {
            throw new DatabaseException(e);
        } catch (IOException e) {
            throw new DatabaseException(e);
        }

        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery(sqlQuery, void.class).executeUpdate();
        } catch (HibernateException e) {
            throw new DatabaseException(e);
        }
    }

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            Metadata metadata = new MetadataSources(registry).buildMetadata();
            sessionFactory = metadata.buildSessionFactory();
        }

        return sessionFactory;
    }
}
