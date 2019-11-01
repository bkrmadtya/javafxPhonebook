package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.registry.Registry;
import com.sda.practicalproject.phonebook.database.user.User;
import com.sda.practicalproject.phonebook.services.sessionManager.SessionManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAO {


    public static User getUserByName(String username) {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from User where username=:username");
        query.setParameter("username", username);

        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    public static List<User> getAllUser() {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from User");

        List<User> list = query.list();

        session.close();

        return query.list();
    }

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

    public static List<Registry> getAllRegistry() {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Registry");
        List<Registry> list = query.list();

        session.close();

        return list;
    }


}
