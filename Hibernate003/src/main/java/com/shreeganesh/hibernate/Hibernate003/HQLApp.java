package com.shreeganesh.hibernate.Hibernate003;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.shreeganesh.hibernate.Hibernate003.entity.Student;
import com.shreeganesh.hibernate.Hibernate003.utils.HibernateUtils;

public class HQLApp {
	
	static Session session=null;
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

		session = sessionFactory.openSession();
		
		Transaction transaction = session.getTransaction();
		
		transaction.begin();
		
		
		getAllStudents();
		
		getStudentsByAge(24);
		
		transaction.commit();

		session.close();
	}

	private static void getStudentsByAge(int age) {
		Query<Student> query = session.createQuery("from Student s where s.age=?1", Student.class);
		query.setParameter(1, age);
		List<Student> list = query.list();
		list.forEach(System.out::println);
	}

	private static void getAllStudents() {
		//Query<Student> createQuery = session.createQuery("select s from Student s",Student.class);
		Query<Student> createQuery = session.createQuery("from Student s",Student.class);
		List<Student> list = createQuery.list();
		list.forEach(System.out::println);
	}
}
