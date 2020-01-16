package edu.gmu.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.gmu.hibernate.entity.Student;

public class ReadStudentDB {

	public static void main(String[] args) {

		// create session factory.
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			/* use the session object to save Java object*/
			// create a student object.
			System.out.println("Creating a new student object ...");
			Student aStudent = new Student("Paul", "Jeon", "paul@gmu.edu");
			
			// start transaction.
			session.beginTransaction();
			
			// save the student object.
			System.out.println("Saving the student ...");
			System.out.println(aStudent);
			session.save(aStudent);
			
			// commit the transaction.
			session.getTransaction().commit();
			
			/* retrieve student based on the id: primary key. */
			// find out the student's id.
			int id = aStudent.getId();
			System.out.println("Saved student's generated id: " + id);
			
			// now get a new session and start transaction.
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve a student based on the id: primary key.
			System.out.println("\nGetting student with id: " + id);
			Student theStudent = session.get(Student.class, id);
			
			System.out.println("\nGet complete: " + theStudent);
			
			// commit the transaction.
			session.getTransaction().commit();
			System.out.println("Done!");
		} 
		finally {
			factory.close();
		}
	}
}
