package springboot.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import springboot.entity.Student;

@Repository
@Qualifier("fakeData")
public class StudentDao implements IStudentDao {

	private static Map<Integer,Student> map;
	
	static {
		 map = new HashMap<Integer,Student>(){
			 {
				 put(1,new Student(1,"Matias","ETPA1"));
				 put(2,new Student(2,"Matias","ETPA1"));
				 put(3,new Student(3,"Matias","ETPA1"));
				 put(4,new Student(4,"Matias","ETPA1"));
				 put(5,new Student(5,"Matias","ETPA1"));
			 }
		 };
	}
	
	/* (non-Javadoc)
	 * @see springboot.dao.IStudentDao#getAllStudents()
	 */
	@Override
	public Collection<Student> getAllStudents(){
		return this.map.values();
	}
	
	/* (non-Javadoc)
	 * @see springboot.dao.IStudentDao#getStudentById(int)
	 */
	@Override
	public Student getStudentById(int id){
		return this.map.get(id);
	}

	/* (non-Javadoc)
	 * @see springboot.dao.IStudentDao#removeStudentById(int)
	 */
	@Override
	public void removeStudentById(int id) {
		this.map.remove(id);
		
	}

	/* (non-Javadoc)
	 * @see springboot.dao.IStudentDao#createStudent(springboot.entity.Student)
	 */
	@Override
	public void createStudent(Student student) {
		this.map.put(map.size()+1, student);
		
	}
}
