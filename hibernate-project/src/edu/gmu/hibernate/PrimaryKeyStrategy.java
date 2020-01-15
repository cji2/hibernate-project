package edu.gmu.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.gmu.hibernate.entity.Student;

public class PrimaryKeyStrategy {

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
			// create three student objects.
			System.out.println("Creating three student objects ... ");
			Student aStudent1 = new Student("Abraham", "Lee", "abraham@gmu.edu");
			Student aStudent2 = new Student("Isaac", "Kim", "isaac@gmu.edu");
			Student aStudent3 = new Student("Jacob", "Park", "jacob@gmu.edu");
			
			// start transaction.
			session.beginTransaction();
			
			// save the student objects.
			System.out.println("Saving the student objects...");
			session.save(aStudent1);
			session.save(aStudent2);
			session.save(aStudent3);
			
			// commit the transaction.
			session.getTransaction().commit();
			System.out.println("Done!");
		} 
		finally {
			factory.close();
		}
	}
}
