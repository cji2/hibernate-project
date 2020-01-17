package edu.gmu.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.gmu.hibernate.entity.Employee;

public class QueryEmployeeDB {

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
			
			// query employees.
			List<Employee> Employees = session
									.createQuery("from Employee")
									.getResultList();
			
			// display the employees.
			displayEmployees(Employees);
			
			// query employees: lastName='Jeon'
			Employees = session
					.createQuery("from Employee e where e.company='Google Inc.'")
					.getResultList();
			
			// display the employees.
			System.out.println("\nEmployees who work for the company of 'Google Inc.': ");
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
			System.out.println(aEmployee);
		}
	}
}
