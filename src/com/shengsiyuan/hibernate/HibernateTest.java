package com.shengsiyuan.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
	private static SessionFactory sessionFactory;

	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			sessionFactory = configuration.buildSessionFactory(registry);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Student student = new Student();
		student.setName("zhangsan");
		
		IdCard idCard = new IdCard();
		idCard.setNumber(987654);
		
		student.setIdCard(idCard);
		idCard.setStudent(student);
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.save(student);
			
			tx.commit();
		} catch(Exception ex) {
			if(null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(null != session) {
				session.close();
			}
		}
		
		/*
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Student student = null;
		
		try {
			tx = session.beginTransaction();
			
			student = session.get(Student.class, "8a80869153fe17e00153fe17e2780000");
			
			tx.commit();
		} catch(Exception ex) {
			if(null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(null != session) {
				session.close();
			}
		}
		
		System.out.println(student.getName());
		System.out.println(student.getIdCard().getNumber());
		*/
		
		/*
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Student student = null;
		
		try {
			tx = session.beginTransaction();
			
			student = session.get(Student.class, "8a80869153fe17e00153fe17e2780000");
			student.setName("lisi");
			
			tx.commit();
		} catch(Exception ex) {
			if(null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(null != session) {
				session.close();
			}
		}
		System.out.println(student.getName());
		*/
		
		/*
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Student student = null;
		
		try {
			tx = session.beginTransaction();
			
			student = session.get(Student.class, "8a80869153fe17e00153fe17e2780000");
			session.delete(student);
			
			tx.commit();
		} catch(Exception ex) {
			if(null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(null != session) {
				session.close();
			}
		}
		*/
		
		sessionFactory.close();
	}
}
