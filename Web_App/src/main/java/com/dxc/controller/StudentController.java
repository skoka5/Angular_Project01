package com.dxc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.dxc.repository.StudentRepository;
import com.dxc.model.Student;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@RequestMapping("showStudentForm")
	public String adStudent() {
		return "showStudentForm";
	}
	
	@RequestMapping(value="addStudent", method= RequestMethod.POST)
	public String addstudent(Student student) {
		studentRepository.save(student);
		return "home";
	}
	
	@GetMapping(path="students/{id}")
	public Student getStudent(@PathVariable("id") int id) {
		Student student = studentRepository.findById(id).orElse(new Student());
			return student;
	}
	
	@GetMapping("students")
	public List<Student> getStudents() {
		List<Student> students = (List<Student>) studentRepository.findAll();
		return students;
	}

	@PostMapping("students")
	public Student save(@RequestBody Student student) {
		return studentRepository.save(student);
	}

	
	@DeleteMapping(path="students/{id}")
	public void deleteStudent(@PathVariable("id") int id) {
		studentRepository.deleteById(id);
	}
	
	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();
            student.setId(id);
	        studentRepository.save(student);
            return ResponseEntity.noContent().build();
	}
	
}
