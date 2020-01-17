package edu.gmu.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.gmu.hibernate.entity.Employee;

public class ReadEmployeeDB {

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
			// create a employee object.
			System.out.println("Creating a new employee object ...");
			Employee aEmployee = new Employee("Paul", "Jeon", "Lyft Inc.");
			
			// start transaction.
			session.beginTransaction();
			
			// save the employee object.
			System.out.println("Saving the employee ...");
			System.out.println(aEmployee);
			session.save(aEmployee);
			
			// commit the transaction.
			session.getTransaction().commit();
			
			/* retrieve employee based on the id: primary key. */
			// find out the employee's id.
			int id = aEmployee.getId();
			System.out.println("Saved employee's generated id: " + id);
			
			// now get a new session and start transaction.
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve a employee based on the id: primary key.
			System.out.println("\nGetting employee with id: " + id);
			Employee theEmployee = session.get(Employee.class, id);
			
			System.out.println("\nGet complete: " + theEmployee);
			
			// commit the transaction.
			session.getTransaction().commit();
			System.out.println("Done!");
		} 
		finally {
			factory.close();
		}
	}
}
