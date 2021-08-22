package com.curriculum.dao;

import java.util.List;

import com.curriculum.exception.DatabaseException;
import com.curriculum.model.Teacher;

public interface TeacherDAO {
	int addTeacherDetails(Teacher teacher);

	int updateTeacherDetails(Long id,String userOption,String newValue) throws DatabaseException;

	int deleteTeacherDetails(Long id) throws DatabaseException;

	List<Teacher> getTeacherDetails();

	List<Teacher> getParticularTeacherDetails(Long id) throws DatabaseException;

	//void getTeacherDetailsByClassRoom(Long roomNo) throws DatabaseException;

	//void getTeacherDetailsBySubjectName(String subjectName) throws DatabaseException;
}
