package springboot.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import springboot.dao.IStudentDao;
import springboot.entity.Student;

@Service
public class StudentService {

	@Autowired
	@Qualifier("fakeData")
	private IStudentDao studentDao;
	
	public Collection<Student> getAllStudents(){
		return studentDao.getAllStudents();
	}
	public Student getStudentById(int id){
		return this.studentDao.getStudentById(id);
	}
	public void removeStudentById(int id) {
		this.studentDao.removeStudentById(id);
		
	}
	public void createStudent(Student student) {
		this.studentDao.createStudent(student);
		
	}
	
}
