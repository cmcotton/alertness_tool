package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import entity.ArcEvent;
import entity.ArcEventCorrelation;
import entity.Event;

public class DBConnection {
    
    SessionFactory sessionFactory = null;
  
    Session session = null;
    
    public void connect() throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("db.properties"));

        Configuration configuration = new Configuration();

        configuration.configure("hibernate.cfg.xml").addProperties(properties);
        configuration.addAnnotatedClass(ArcEvent.class);
        configuration.addAnnotatedClass(ArcEventCorrelation.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

//        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);        
        
        if (sessionFactory == null) {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        
        if (session == null) {
            
            try {
                session = sessionFactory.openSession();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void close() {
    
        if (session != null) {
            session.close();
        }
        
        if (session != null) {
            sessionFactory.close();
        }
    }

    public void save(Event evt) {
        
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(evt);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

    public static void main(String[] args) throws HibernateException {
        

        ArcEvent evt = new ArcEvent();
        evt.setESM_HOST("a");
        evt.setEVENT_ID(1111111);

        DBConnection db = new DBConnection();
        try {
            db.connect();
            db.save(evt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        
        db.close();

        System.out.println("新增資料OK!請先用MySQL觀看結果！");
    }
}