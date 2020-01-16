package edu.gmu.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.gmu.hibernate.entity.Student;

public class DeleteStudentDB2 {

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
			
			// retrieve a student based on the id: primary key.
						
			// delete the student.
			int id = 5;
			System.out.println("\nDeleting a student whose id is " + id);
			session
				.createQuery("delete from Student where id=5")
				.executeUpdate();
			
			// query students.
			List<Student> Students = session
										.createQuery("from Student")
										.getResultList();
						
			// display the students.
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
			System.out.println("\n" + aStudent);
		}
	}
}
