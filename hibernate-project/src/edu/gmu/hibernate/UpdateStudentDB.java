package edu.gmu.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.gmu.hibernate.entity.Student;

public class UpdateStudentDB {

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
			
			// retrieve a student base don the id: primary key.
			int id = 3;
			
			System.out.println("\nGetting student with id: " + id);
			Student theStudent = session.get(Student.class, id);
			
			/* update first name to "John"
			 * we don't need to have session.save(theStudent),
			 * since theStudent is already persistent data. 
			 * so, theStudent.setFirstName makes DB updated and save new data.
			 */
			theStudent.setFirstName("John");
			theStudent.setEmail("john@gmail.com");
			
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
