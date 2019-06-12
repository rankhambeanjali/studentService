package com.example.demo;

import java.util.List;

public interface IStudentDAO {
	List<Student> getAllStudent();
	Student getStudentByRollNo(int student_id);
	void addStudent(Student fname);
	void deleteStudent(int student_id);
	boolean studentExists(String fname);
	
}
