/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author joseph
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static List executeHQLQuery(String hql) {
        List resultList = null;
        try {
            Session session = getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(hql);
            resultList = q.list();
            session.getTransaction().commit();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return resultList;
    }
    
    public static Integer addEntity(Object entity){
        Integer id = null;
        try{
            Session session = getSessionFactory().openSession();
            session.beginTransaction();
            
            id = (Integer)session.save(entity);
            
            session.getTransaction().commit();
            getSessionFactory().close();
            
        }catch(HibernateException he) {
            he.printStackTrace();
        }
        return id;
    }
    
    public static void editEntity(Object entity){
        try{
            Session session = getSessionFactory().openSession();
            session.beginTransaction();
            
            session.update(entity);
            
            session.getTransaction().commit();
            getSessionFactory().close();
            
        }catch(HibernateException he) {
            he.printStackTrace();
        }
    }
    
    public static void deleteEntity(Object entity){
        try{
            Session session = getSessionFactory().openSession();
            session.beginTransaction();
            
            session.delete(entity);
            
            session.getTransaction().commit();
            getSessionFactory().close();
            
        }catch(HibernateException he) {
            he.printStackTrace();
        }
    }
}
