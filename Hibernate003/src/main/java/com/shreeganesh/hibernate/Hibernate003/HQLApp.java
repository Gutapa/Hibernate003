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
		
		System.out.println("******************************************************************************************************");
		getAllStudents();
		System.out.println("******************************************************************************************************");
		getNameOfAllStudents();
		System.out.println("******************************************************************************************************");
		getNameAgeOfAllStudents();
		System.out.println("******************************************************************************************************");
		getStudentsByAge(24);
		System.out.println("******************************************************************************************************");
		getStudentsByNameAge("Krishna",25);
		System.out.println("******************************************************************************************************");
		getStudentsBetweenAge(20,45);
		System.out.println("******************************************************************************************************");
		getStudentsAgeSum();
		System.out.println("******************************************************************************************************");
		getStudentsAgeSumByCountry();
		System.out.println("******************************************************************************************************");
		updateAllStudentsAgeBy(2);
		System.out.println("******************************************************************************************************");
		deleteStudentsByAge(50);
		System.out.println("******************************************************************************************************");
		getAllStudentsNamed();
		System.out.println("*************************************NATIVE*****************************************************************");
		getAllStudentsNative();
		System.out.println("*************************************NATIVE*****************************************************************");
		getStudentsByAgeNative(24);
		System.out.println("*************************************NATIVE*****************************************************************");
		getStudentsByAgeNamed(24);
		System.out.println("******************************************************************************************************");
		getStudentsByNamePatternNamed("%a%");
		System.out.println("******************************************************************************************************");
		
		transaction.commit();

		session.close();
	}

	private static void deleteStudentsByAge(int age) {
		
		int rowsdeleted = session.createQuery("delete from Student s where s.age=:AGE").setParameter("AGE", age).executeUpdate();
		System.out.println("No of Rows Deleted : " + rowsdeleted);
		
	}

	private static void updateAllStudentsAgeBy(int increment) {

		int rowsupdated = session.createQuery("update Student s set s.age=s.age+:INCREMENT").setParameter("INCREMENT", increment).executeUpdate();
		System.out.println("No of Rows Updated : " + rowsupdated);
	}

	private static void getStudentsAgeSumByCountry() {
		List<Object[]> list = session.createQuery("select s.country,sum(s.age) from Student s group by s.country", Object[].class).list();
		for(Object[] temp:list) {
		System.out.println(temp[0]+" - "+temp[1]);
		}
	}

	private static void getStudentsAgeSum() {
		List<Object> list = session.createQuery("select sum(s.age) from Student s", Object.class).list();
		System.out.println(list);
	}

	private static void getStudentsByAgeNative(int age) {

		List<Student> list = session.createNativeQuery("select * from students where age=:AGE", Student.class).setParameter("AGE", age).list();
		list.forEach(System.out::println);
	}

	private static void getStudentsByNamePatternNamed(String namePattern) {

		List<Student> list = session.createNamedQuery("myapp.allstudents.name.like", Student.class).setParameter("NAME_PATTERN", namePattern).list();
		list.forEach(System.out::println);
	}

	private static void getStudentsByAgeNamed(int age) {

		List<Student> list = session.createNamedQuery("myapp.allstudents.age",Student.class).setParameter("AGE", age).list();
		list.forEach(System.out::println);
	}

	private static void getAllStudentsNative() {
		List<Student> list = session.createNativeQuery("select * from students", Student.class).list();
		list.forEach(System.out::println);
	}

	private static void getAllStudentsNamed() {
		
		//List<Student> list = session.createQuery("from Student", Student.class).list();
		
		List<Student> list = session.createNamedQuery("myapp.allstudents",Student.class).list();
		
		list.forEach(System.out::println);
	}

	private static void getNameAgeOfAllStudents() {
		Query<Object[]> query = session.createQuery("select s.name,s.age from Student s", Object[].class);
		List<Object[]> list = query.list();
		for(Object[] a: list) {
			System.out.println(a[0]+" - "+a[1]);
		}
	}

	private static void getNameOfAllStudents() {

		Query<String> query = session.createQuery("select s.name from Student s",String.class);
		List<String> list = query.list();
		list.forEach(System.out::println);
	}

	private static void getStudentsBetweenAge(int min_age, int max_age) {

		Query<Student> query = session.createQuery("from Student s where s.age between :MIN_AGE and :MAX_AGE", Student.class);
		query.setParameter("MIN_AGE", min_age);
		query.setParameter("MAX_AGE", max_age);
		List<Student> list = query.list();
		list.forEach(System.out::println);
		
	}

	private static void getStudentsByNameAge(String name, int age) {
		
		Query<Student> query = session.createQuery("from Student s where s.age=:AGE and s.name=:NAME", Student.class);
		query.setParameter("AGE", age);
		query.setParameter("NAME", name);
		List<Student> list = query.list();
		list.forEach(System.out::println);
		
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
