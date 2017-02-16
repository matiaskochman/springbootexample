package springboot.dao;

import java.util.Collection;

import springboot.entity.Student;

public interface IStudentDao {

	Collection<Student> getAllStudents();

	Student getStudentById(int id);

	void removeStudentById(int id);

	void createStudent(Student student);

}