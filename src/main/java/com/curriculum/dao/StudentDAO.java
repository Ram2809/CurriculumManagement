package com.curriculum.dao;

import java.util.List;

import com.curriculum.exception.DatabaseException;
import com.curriculum.model.Student;

public interface StudentDAO {
	int addStudentDetails(Student student);

	int updateStudentDetails(Long id, String userOption, String newValue) throws DatabaseException;

	int updateStudentDetailsByRoomNo(Long id, String userOption, Long newValue) throws DatabaseException;

	int deleteStudentDetails(Long id) throws DatabaseException;

	List<Student> getStudentDetails();

	List<Student> getParticularStudentDetails(Long id) throws DatabaseException;

	void getStudentDetailsByClassRoom(Long roomNo) throws DatabaseException;
}
