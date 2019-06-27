package com.geekykel;

import com.geekykel.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourses {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			int id = 6;

			// start a transaction
			session.beginTransaction();

			Course course = session.get(Course.class, id);

			System.out.println("\nDeleting the Course..." + course);

			session.delete(course);

			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {

			session.close();

			factory.close();
		}
	}

}





