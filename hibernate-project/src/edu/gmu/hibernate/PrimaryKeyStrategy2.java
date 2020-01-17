package edu.gmu.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.gmu.hibernate.entity.Employee;

public class PrimaryKeyStrategy2 {

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
			// create three employee objects.
			System.out.println("Creating three employee objects ... ");
			Employee aEmployee1 = new Employee("Joseph", "Lee", "Facebook Inc.");
			Employee aEmployee2 = new Employee("Isaac", "Kim", "Uber Inc.");
			Employee aEmployee3 = new Employee("Isaiah", "Park", "Microsoft Inc.");
			
			// start transaction.
			session.beginTransaction();
			
			// save the employee objects.
			System.out.println("Saving the employee objects...");
			session.save(aEmployee1);
			session.save(aEmployee2);
			session.save(aEmployee3);
			
			// commit the transaction.
			session.getTransaction().commit();
			System.out.println("Done!");
		} 
		finally {
			factory.close();
		}
	}
}
