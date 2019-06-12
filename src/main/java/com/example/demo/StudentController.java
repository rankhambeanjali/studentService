package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.IStudentService;

@RestController
public class StudentController {

	@Autowired
	IStudentService istudentService;

	@GetMapping("/student")
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> list = istudentService.getAllStudent();
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);

	}

	@GetMapping("/student/{student_id}")
	public ResponseEntity<Student> getStudentByRollNo(@PathVariable("student_id") Integer student_id) {
		Student student = istudentService.getStudentByRollNo(student_id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@PostMapping("/student")
	public ResponseEntity<Void> addStudent(@RequestBody Student student, UriComponentsBuilder ucbuilder) {
		boolean flag = istudentService.addStudent(student);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ucbuilder.path("/student/{student_id}").buildAndExpand(student.getStudent_id()).toUri());
		return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);

	}

	@DeleteMapping("/student/{student_id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("student_id") Integer student_id) {
		istudentService.deleteStudent(student_id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}