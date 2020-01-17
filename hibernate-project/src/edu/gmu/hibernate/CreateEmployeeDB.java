package edu.gmu.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.gmu.hibernate.entity.Employee;

public class CreateEmployeeDB {

	public static void main(String[] args) {

		// create session factory.
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg2.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			/* use the session object to save Java object*/
			// create a student object.
			Employee aEmployee = new Employee("Abraham", "Ji", "Google Inc.");
			
			// start transaction.
			session.beginTransaction();
			
			// save the student object.
			System.out.println("Saving the employee object...");
			session.save(aEmployee);
			
			// commit the transaction.
			session.getTransaction().commit();
			System.out.println("Done!");
		} 
		finally {
			factory.close();
		}
	}
}
