package com.sda.practicalproject.phonebook.database.registry;

import com.sda.practicalproject.phonebook.services.sessionManager.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Function;

public class RegistryDAO {

    public static Session session = SessionManager.getSessionFactory().getCurrentSession();

    public static void createRegistry(Registry registry){
        query(session -> {
            session.save(registry);
            return registry;
        });
    }

    private static void query(Function<Session, Registry> function){
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();

            function.apply(session);

            transaction.commit();
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}
