package com.sda.practicalproject.phonebook.utils.sessionManager;

import com.sda.practicalproject.phonebook.database.contact.Contact;
import com.sda.practicalproject.phonebook.database.user.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class SessionManager {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                registry = new StandardServiceRegistryBuilder().configure().build();

                MetadataSources sources = new MetadataSources(registry);

                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();


//                Configuration configuration = new Configuration();
//                Properties settings = new Properties();
//
//                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
//                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/phonebook");
//                settings.put(Environment.USER, "root");
//                settings.put(Environment.PASS, "admin");
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//                settings.put(Environment.HBM2DDL_AUTO, "update");
//
//                configuration.setProperties(settings);
//
//                configuration.addAnnotatedClass(User.class);
//                configuration.addAnnotatedClass(Contact.class);
//
//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties())
//                        .build();
//
//                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
                if(registry != null){
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if(registry != null){
            StandardServiceRegistryBuilder.destroy(registry);
        }
//        getSessionFactory().close();
    }
}
