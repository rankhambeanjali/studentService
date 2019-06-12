package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class StudentDAO implements IStudentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Student> getAllStudent() {
		String sql = "select * from student";
		RowMapper<Student> rowMapper = new BeanPropertyRowMapper<Student>(Student.class);
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Student getStudentByRollNo(int student_id) {
		String sql = "select student_id , fname , studentid from student where student_id = ?";
		RowMapper<Student> rowMapper = new BeanPropertyRowMapper<Student>(Student.class);
		Student student = jdbcTemplate.queryForObject(sql, rowMapper, student_id);
		return student;
	}

	@Override
	public void addStudent(Student student) {
		String sql = "insert into student (studentid,fname) values (?,?)";
		jdbcTemplate.update(sql, student.getStudentid(), student.getFname());

		sql = "select student_id from student where studentid = ?";
		int student_id = jdbcTemplate.queryForObject(sql, Integer.class, student.getStudentid());

		student.setStudent_id(student_id);
	}

	@Override
	public void deleteStudent(int student_id) {
		String sql = "delete from student where student_id = ?";
		jdbcTemplate.update(sql, student_id);
	}

	@Override
	public boolean studentExists(String fname) {
		String sql = "select count(*) from student where fname = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, fname);
		if (count == 0) {
			return false;

		} else {
			return true;
		}
	}
}
