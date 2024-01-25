package com.nothing.lab3.db;

import com.nothing.lab3.beans.DotsCollection;
import com.nothing.lab3.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of DAO interface
 */
public class HsqlQueries {
    private static final String TABLE = "result";

    /**
     * Adds new instance of DotsCollection to database
     *
     * @param dotsCollection accepts result bean instance
     * @throws SQLException
     */
    public static void addResult(DotsCollection dotsCollection) throws SQLException {
        Session session = null;
        Transaction transaction;
        try {
            session = HibernateUtils.buildSessionFactory().openSession();
            transaction = session.getTransaction();
            session.beginTransaction();
            session.persist(dotsCollection);
            transaction.commit();
        } catch (Exception ex) {
            throw new SQLException();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    /**
     * Clear whole table
     * @throws SQLException
     */

    public static void clearResults() throws SQLException {
        Session session = null;
        Transaction transaction;
        try {
            session = HibernateUtils.buildSessionFactory().openSession();
            transaction = session.getTransaction();
            session.beginTransaction();
            String deleteTable = "delete from " + TABLE;
            session.createNativeQuery(deleteTable).executeUpdate();
            session.clear();
            transaction.commit();
        } catch (Exception ex) {
            throw new SQLException();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Fetching all data from table
     * @return List of result bean objects
     * @throws SQLException
     */

    public static List<DotsCollection> getAllResults() throws SQLException {
        Session session = null;
        List<DotsCollection> res;
        try {
            session = HibernateUtils.buildSessionFactory().openSession();
            CriteriaQuery<DotsCollection> criteriaBuilder = session.getCriteriaBuilder().createQuery(DotsCollection.class);
            criteriaBuilder.from(DotsCollection.class);
            res = session.createQuery(criteriaBuilder).getResultList();
        } catch (Exception ex) {
            throw new SQLException();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return res;
    }
}
