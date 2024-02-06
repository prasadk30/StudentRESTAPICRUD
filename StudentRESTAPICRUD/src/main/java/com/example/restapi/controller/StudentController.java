package com.example.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.entity.Student;
import com.example.restapi.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	// get all the students list
	//write URI for get all students
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		
		List<Student> students= studentRepository.findAll();
		
		return students;
	}
	
	// uri is (localhost:8080/students/1)
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {

		Student student=studentRepository.findById(id).get();
		
		return student;
	}
	
	@PostMapping("/student/add")  // create new student
	@ResponseStatus(code = HttpStatus.CREATED) // for show the status code 201 at the Postman
	public void createStudent(@RequestBody Student student) {
		studentRepository.save(student);
	}
	
	@PutMapping("/student/update/{id}") //update the student
	public Student updateStudents(@PathVariable int id) {  // return the updated student details
		Student student=studentRepository.findById(id).get();
		student.setName("Prasad");
		student.setPercentage(92);
		
		studentRepository.save(student);
		
		return student;
	}
	
	@DeleteMapping("/student/delete/{id}")
	public void deleteStudent(@PathVariable int id) {
		Student student= studentRepository.findById(id).get();
		studentRepository.delete(student);
		
	}
}
