package com.shreeganesh.hibernate.Hibernate003;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.shreeganesh.hibernate.Hibernate003.entity.Address;
import com.shreeganesh.hibernate.Hibernate003.entity.Student;
import com.shreeganesh.hibernate.Hibernate003.utils.HibernateUtils;

public class HQLApp {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

		Session session = sessionFactory.openSession();
		
		Transaction transaction = session.getTransaction();
		
		
		transaction.begin();
		
		Student student = session.get(Student.class, 1);
		
		System.out.println(student);
		
		transaction.commit();

		session.close();
	}
}
