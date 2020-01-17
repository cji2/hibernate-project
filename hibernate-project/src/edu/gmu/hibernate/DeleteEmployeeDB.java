package edu.gmu.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.gmu.hibernate.entity.Employee;

public class DeleteEmployeeDB {

	public static void main(String[] args) {

		// create session factory.
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg2.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction.
			session.beginTransaction();
			
			// retrieve a employee based on the id: primary key.
			int id = 4;
			System.out.println("\nGetting a employee with id: " + id);
			Employee theEmployee = session.get(Employee.class, id);
			
			// delete the employee.
			System.out.println("\nDeleting the employee: " + theEmployee);
			session.delete(theEmployee);
			
			// query employees.
			List<Employee> Employees = session
										.createQuery("from Employee")
										.getResultList();
						
			// display the employees.
			displayEmployees(Employees);
			
			// commit the transaction.
			session.getTransaction().commit();
			System.out.println("Done!");
		} 
		finally {
			factory.close();
		}
	}
	
	private static void displayEmployees(List<Employee> Employees) {
		for (Employee aEmployee : Employees) {
			System.out.println("\n" + aEmployee);
		}
	}
}
