package springboot.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springboot.entity.Purchase;
import springboot.entity.Student;
import springboot.entity.User;
import springboot.service.PurchaseService;
import springboot.service.StudentService;
import springboot.service.UserService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	
	@RequestMapping(value="/purchasesByUser",method=RequestMethod.GET)
	public Collection<Purchase> getLast5PurchasesByUser(){
		return this.purchaseService.getLast5PurchaseByUser("Kiarra86");
	}
	@RequestMapping(value="/purchasesByProduct",method=RequestMethod.GET)
	public Collection<Purchase> getPurchasesByProductId(){
		return this.purchaseService.getPurchasesByProductId("817522");
	}	
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public Collection<User> getAllUsers(){
		return this.userService.getAllUsers();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Collection<Student> getAllStudents(){
		return this.studentService.getAllStudents();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Student getStudentById(@PathVariable("id") int id){
		return studentService.getStudentById(id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void deleteStudentById(@PathVariable("id") int id){
		studentService.removeStudentById(id);
	}
	@RequestMapping(method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void createStudent(@RequestBody Student student){
		this.studentService.createStudent(student);
	}
}
