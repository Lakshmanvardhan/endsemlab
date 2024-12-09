package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        // Configure Hibernate programmatically
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/labexam");
        cfg.setProperty("hibernate.connection.username", "root"); // Replace with your username
        cfg.setProperty("hibernate.connection.password", "Vardhan@99"); // Replace with your password
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("hibernate.format_sql", "true");
        cfg.setProperty("hibernate.hbm2ddl.auto", "update");

        // Add the annotated class
        cfg.addAnnotatedClass(Client.class);

        // Build the session factory and registry
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties()).build();
        SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);

        // Open a session and start a transaction
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        // Insert Data
        Client client1 = new Client();
        client1.setName("Jane Doe");
        client1.setGender("Female");
        client1.setAge(25);
        client1.setLocation("California");
        client1.setEmail("jane.doe@example.com");
        client1.setMobile("9876543210");

        session.save(client1); // Save the object
        tx.commit();

        // Retrieve All Data
        List<Client> clients = session.createQuery("from Client", Client.class).list();
        System.out.println("All Clients:");
        for (Client client : clients) {
            System.out.println(client);
        }

        // Close the session and session factory
        session.close();
        sessionFactory.close();
    }
}
