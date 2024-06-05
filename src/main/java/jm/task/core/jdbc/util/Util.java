package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mytest");
                settings.put(Environment.USER, "valuev");
                settings.put(Environment.PASS, "Delopo93");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Ошибка соединение не установленно !!!!!!!!!!!");
            }
        }
        return sessionFactory;
    }
}


//  // реализуйте настройку соеденения с БД
//  private static final String DB_URL = "jdbc:mysql://localhost:3306/mytest";
//  private static final String DB_USERNAME = "valuev";
//  private static final String DB_PASSWORD = "Delopo93";

//  public static Connection getConnection() {
//      Connection connection = null;
//      try {
//          Class.forName("com.mysql.cj.jdbc.Driver");
//          connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//          System.out.println("Connected to database");
//      } catch (ClassNotFoundException | SQLException e) {
//          e.printStackTrace();
//          System.out.println("Could not connect to database");
//      }
//      return connection;
//  }
