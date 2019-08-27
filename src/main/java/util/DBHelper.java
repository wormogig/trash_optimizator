package util;

import model.Category;
import model.ModelPoint;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBHelper {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(ModelPoint.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Category.class);
        configuration.setProperty("hibernate.dialect", PropertiesReader.getProperties("dialect"));
        configuration.setProperty("hibernate.connection.driver_class", PropertiesReader.getProperties("driver.class"));
        configuration.setProperty("hibernate.connection.url", PropertiesReader.getProperties("connection.url"));
        configuration.setProperty("hibernate.connection.username", PropertiesReader.getProperties("username"));
        configuration.setProperty("hibernate.connection.password", PropertiesReader.getProperties("password"));
        configuration.setProperty("hibernate.show_sql", PropertiesReader.getProperties("show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", PropertiesReader.getProperties("hbm2ddl.auto"));
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
