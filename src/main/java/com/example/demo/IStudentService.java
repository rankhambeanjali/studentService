package com.example.demo;

import java.util.List;

public interface IStudentService {

	List<Student> getAllStudent();
	Student getStudentByRollNo(int student_id);
	boolean addStudent(Student student);
	void deleteStudent(int student_id);

}
