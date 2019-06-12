package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {

	@Autowired
	private IStudentDAO istudentServicedao;

	@Override
	public List<Student> getAllStudent() {
		return istudentServicedao.getAllStudent();
	}

	@Override
	public Student getStudentByRollNo(int student_id) {
		Student stu = istudentServicedao.getStudentByRollNo(student_id);
		return stu;

	}

	@Override
	public synchronized boolean addStudent(Student student) {
		if (istudentServicedao.studentExists(student.getFname())) {
			return false;

		} else {
			istudentServicedao.addStudent(student);
			return true;
		}

	}

	@Override
	public void deleteStudent(int student_id) {
		istudentServicedao.deleteStudent(student_id);

	}
}
