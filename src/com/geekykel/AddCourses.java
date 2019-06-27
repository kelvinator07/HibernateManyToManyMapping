package com.geekykel;

import com.geekykel.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCourses {

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

			int id = 7;

			// start a transaction
			session.beginTransaction();

			Student student = session.get(Student.class, id);

			System.out.println("\nLoading the student..." + student);
			System.out.println("\nStudent courses" + student.getCourses());

			Course course1 = new Course("COM 321");
			Course course2 = new Course("COM 333");

			course1.addStudent(student);
			course2.addStudent(student);

			System.out.println("\nSaving the course...");
			session.save(course1);
			session.save(course2);

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





