package com.geekykel;

import com.geekykel.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndStudents {

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

			// start a transaction
			session.beginTransaction();

			Course course = new Course("COM 422");

			System.out.println("\nSaving the course...");

			session.save(course);

			System.out.println("\nSaved the course..." + course);

			Student student1 = new Student("Taiwo","Alabi", "taiwo@geekykel.com");
			Student student2 = new Student("Kehinde","Alabi", "kehinde@geekykel.com");

			course.addStudent(student1);
			course.addStudent(student2);

			System.out.println("\nSaving the students...");
			session.save(student1);
			session.save(student2);

			// print associated students
			System.out.println("\nSaved the student..." + course.getStudents());

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





