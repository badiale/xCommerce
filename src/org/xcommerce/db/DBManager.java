/**
 * Package with database connections handlers.
 * */
package org.xcommerce.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Conexion manager.<br>
 * <p>
 * For example:
 * <pre>
 * 	Session session = DBManager.getSession();
 * 	session.beginTransaction();
 * 	session.save(myObject);
 * 	session.getTransaction().commit();
 * </pre>
 * </p>
 *
 * @author Rodrigo Mello
 * */
public class DBManager {
	private static final SessionFactory sessionFactory;
	
	/** 
	 * This constructor isn't supposed to be called directly. <br>
	 * Use getSession instead. 
	 * */
	private DBManager () {}

	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Method that returns an session factory.<br>
	 * @return factory to get sessions.
	 * @deprecated Use getSession instead.
	 * */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * Method to get an session with the data base<br>
	 * @return returns the session with the data base
	 * */
	public static Session getSession () {
		return sessionFactory.getCurrentSession();
	}
}
