package edu.gmu.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.gmu.hibernate.entity.Student;

public class QueryStudentDB {

	public static void main(String[] args) {

		// create session factory.
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction.
			session.beginTransaction();
			
			// query students.
			List<Student> Students = session
									.createQuery("from Student")
									.getResultList();
			
			// display the students.
			displayStudents(Students);
			
			// query students: lastName='Ji'
			Students = session
					.createQuery("from Student s where s.lastName='Jeon'")
					.getResultList();
			
			// display the students.
			System.out.println("\nStudents who have the last name of 'Jeon': ");
			displayStudents(Students);
			
			// query students: lastName='Ji'
			Students = session
					.createQuery("from Student s where s.lastName='Lee'" +
							" OR s.firstName='Paul'")
					.getResultList();
			
			// display the students.
			System.out.println("\nStudents who have the last name of 'Lee' or " +
								"the first name of 'Paul'");
			displayStudents(Students);
						
			// commit the transaction.
			session.getTransaction().commit();
			System.out.println("Done!");
		} 
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> Students) {
		for (Student aStudent : Students) {
			System.out.println(aStudent);
		}
	}
}
