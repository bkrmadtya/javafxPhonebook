package com.sda.practicalproject.phonebook.database.registry;

import com.sda.practicalproject.phonebook.services.sessionManager.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.function.Function;

public class RegistryDAO {

    public static void createRegistry(Registry registry) {
        query(session -> {
            session.save(registry);
            return registry;
        });
    }


    public static void deleteRegistry(Long id) {
        query(session -> {
            Registry registry = session.find(Registry.class, id);
            session.delete(registry);
            return registry;
        });
    }


    public static void updateRegistry(Registry updatedRegistry, Long id) {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String hql = "UPDATE Registry SET " +
                    "personName=:personName, " +
                    "phoneNumber=:phoneNumber, " +
                    "email=:email, " +
                    "address=:address " +
                    "where registryId=:registryId";

            Query query = session.createQuery(hql);
            query.setParameter("personName", updatedRegistry.getPersonName())
                    .setParameter("phoneNumber", updatedRegistry.getPhoneNumber())
                    .setParameter("email", updatedRegistry.getEmail())
                    .setParameter("address", updatedRegistry.getAddress())
                    .setParameter("registryId", id);

            int rows = query.executeUpdate();

            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        session.close();
    }


    private static Registry getRegistry(Long id) {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Registry where registryId=:id");
        query.setParameter("id", id);

        Registry registry = (Registry) query.uniqueResult();
        session.close();
        return registry;
    }

    private static void query(Function<Session, Registry> function) {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
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
