package com.sda.practicalproject.phonebook.database.user;

import com.sda.practicalproject.phonebook.utils.sessionManager.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Function;

public class UserDAO {
    private static Session session = SessionManager.getSessionFactory().getCurrentSession();

    public static void createUser(User user) {
        query(session -> {
            session.save(user);
            return user;
        });
    }

    private static void query(Function<Session, User> function) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            function.apply(session);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        session.close();
    }
}
