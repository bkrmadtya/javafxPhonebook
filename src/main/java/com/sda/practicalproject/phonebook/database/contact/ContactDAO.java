package com.sda.practicalproject.phonebook.database.contact;

import com.sda.practicalproject.phonebook.utils.sessionManager.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.function.Function;

public class ContactDAO {

    public static void createContact(Contact contact) {
        query(session -> {
            session.save(contact);
            return contact;
        });
    }


    public static void deleteContact(Long id) {
        query(session -> {
            Contact contact = session.find(Contact.class, id);
            session.delete(contact);
            return contact;
        });
    }


    public static void updateContact(Contact updatedContact, Long id) {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String hql = "UPDATE Contact SET " +
                    "personName=:personName, " +
                    "phoneNumber=:phoneNumber, " +
                    "email=:email, " +
                    "address=:address " +
                    "where contactId=:contactId";

            Query query = session.createQuery(hql);
            query.setParameter("personName", updatedContact.getPersonName())
                    .setParameter("phoneNumber", updatedContact.getPhoneNumber())
                    .setParameter("email", updatedContact.getEmail())
                    .setParameter("address", updatedContact.getAddress())
                    .setParameter("contactId", id);

            int rows = query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        session.close();
    }


    private static Contact getContact(Long id) {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Contact where contactId=:id");
        query.setParameter("id", id);

        Contact contact = (Contact) query.uniqueResult();
        session.close();
        return contact;
    }

    private static void query(Function<Session, Contact> function) {
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
