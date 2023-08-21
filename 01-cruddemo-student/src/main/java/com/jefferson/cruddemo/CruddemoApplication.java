package com.jefferson.cruddemo;

import com.jefferson.cruddemo.dao.StudentDAO;
import com.jefferson.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) { // simply give a reference to the studentDAO and Spring will inject it
		return runner -> {
//			createStudent(studentDAO);
			createMultipleStudents(studentDAO);
//			readStudent(studentDAO);
//			queryForStudents(studentDAO);
//			queryForStudentsByLastName(studentDAO);
//			updateStudent(studentDAO);
//			deleteStudent(studentDAO);
//			deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted " + numRowsDeleted + " rows.");
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int id = 7;
		studentDAO.delete(id);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// find the student by id
		Student student = studentDAO.findById(1); // should return Jefferson

		// change first name to "Jeff
		student.setFirstName("Jonas");

		// update the student
		studentDAO.update(student);

		// display the updated student
		System.out.println("Updated student: " + student);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students by last name
		List<Student> students = studentDAO.findByLastName("Jurado");
		// display the students
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of all students
		List<Student> students = studentDAO.findAll();

		// display list of students
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		// create multiple students
		System.out.println("Creating new student object...");

		// create the student object
		Student student = new Student("Jurado", "Garcia", "jurado@aa.io");
		Student student2 = new Student("Adilson", "Jurado", "adilson@aa.io");
		Student student3 = new Student("Jeff", "Lopez", "lopez@aa.io");

		// save the student objects
		System.out.println("Saving the students...");

		studentDAO.save(student);
		studentDAO.save(student2);
		studentDAO.save(student3);

	}

	private void createStudent(StudentDAO studentDAO) {

		System.out.println("Creating new student object...");

		// create the student object
		Student student = new Student("Jefferson", "Garcia", "jeff@aa.io");
		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(student);
		// display id of the saved student
		System.out.println("Saved student. Generated id: " + student.getId());
	}

	private void readStudent(StudentDAO studentDAO) {
//		// find the student by id
//		System.out.println("Getting student with id: 1");
//		Student student = studentDAO.findById(1); // should return Jefferson
//		System.out.println("This is the student: " + student);

		// create a student object
		Student student = new Student("Adilson", "Lopez", "adilson@aa.io"); // since we send this to the constructor
		// and the id is mapped to that column and auto generated, Java will know which id we are going to give to this student.

		// save the student object
		studentDAO.save(student);
		// display id of the saved student
		int theId = student.getId();
		Student myStudent = studentDAO.findById(theId);

		System.out.println("This is the student: " + myStudent);

	}
}
