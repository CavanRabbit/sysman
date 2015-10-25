package cn.com.pingan.sm.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	
		private static Configuration cfg ;
		private static SessionFactory sessionFactory;
		private static StandardServiceRegistry serviceRegistry;
/*		private HibernateSessionFactory(){
			
		}*/
		static{
			try{
				cfg = new Configuration().configure();
			    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			    sessionFactory = cfg.buildSessionFactory(serviceRegistry);				
			}catch(Throwable e){
				
				System.out.println("¥¥Ω®session factory ß∞‹");
				throw new ExceptionInInitializerError(e);
			}
       		    
		}
		public synchronized static SessionFactory getSessionFactory(){
			
			return sessionFactory;
		}
		
	
}
