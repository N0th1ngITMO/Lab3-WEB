package com.nothing.lab3.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();;

    public static SessionFactory buildSessionFactory() {
        return sessionFactory;
    }
}
