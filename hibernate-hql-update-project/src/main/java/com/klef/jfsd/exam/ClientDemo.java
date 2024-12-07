package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Initialize Hibernate
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Update operation using HQL with positional parameters
            String hqlUpdate = "UPDATE Department SET name = ?1, location = ?2 WHERE id = ?3";
            int updatedRows = session.createQuery(hqlUpdate)
                    .setParameter(1, "Updated Department Name")
                    .setParameter(2, "Updated Location")
                    .setParameter(3, 1) // Replace with the department ID to update
                    .executeUpdate();

            transaction.commit();
            System.out.println("Number of rows updated: " + updatedRows);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
