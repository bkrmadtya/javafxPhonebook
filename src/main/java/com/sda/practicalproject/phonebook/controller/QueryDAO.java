package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.contact.Contact;
import com.sda.practicalproject.phonebook.database.user.User;
import com.sda.practicalproject.phonebook.utils.sessionManager.SessionManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAO {

    @SuppressWarnings({"unchecked", "unsafe"})
    public static User getUserByName(String username) {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from User where username=:username");
        query.setParameter("username", username);

        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

<<<<<<< HEAD
//    public static List getAllUser() {
//        Session session = SessionManager.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//
//        Query query = session.createQuery("from User");
//
//        List list = query.list();
//
//        session.close();
//
//        return list;
//    }
=======
    @SuppressWarnings({"unchecked", "unsafe"})
    public static List<User> getAllUser() {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from User");

        List<User> list = query.list();

        session.close();

        return query.list();
    }
>>>>>>> fcc46104119140f0fb6f8270bdaa0cc6a4437f29

    public static boolean usernameIsUnique(String username) {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        boolean result = true;
        User user = getUserByName(username);

        if (user != null) {
            result = false;
        }
        session.close();
        return result;
    }

    @SuppressWarnings({"unchecked", "unsafe"})
    public static List<Contact> getAllContacts() {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Contact");
        List<Contact> list = query.list();

        session.close();

        return list;
    }


}
