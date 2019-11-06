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

    @SuppressWarnings({"unchecked", "unsafe"})
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

    @SuppressWarnings({"unchecked", "unsafe"})
    public static List<Contact> getAllContacts() {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Contact");
        List<Contact> list = query.list();

        session.close();
        return list;
    }

    public static Contact getContactByName(String name) {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Contact where personName :=name");
        query.setParameter("name", name);
        Contact contact = (Contact) query.uniqueResult();

        session.close();
        return contact;
    }

    public static Contact getContactByNumber(Long number) {
        Session session = SessionManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from Contact where phonenumber=:phonenumber");
        query.setParameter("phonenumber", number);
        Contact contact = (Contact) query.uniqueResult();

        session.close();
        return contact;

    }


}
