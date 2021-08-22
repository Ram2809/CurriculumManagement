package com.curriculum.service;

import java.util.List;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.model.Teacher;

public interface TeacherService {
	int addTeacherDetails(Teacher teacher);

	List<Teacher> getTeacherDetails();

	int updateTeacherDetails(Long id,String userOption,String newValue) throws BusinessServiceException;

	int deleteTeacherDetails(Long id) throws BusinessServiceException;

	List<Teacher> getParticularTeacherDetails(Long id) throws BusinessServiceException;

	//void getTeacherDetailsByClassRoom(Long roomNo) throws BusinessServiceException;

	//void getTeacherDetailsBySubjectName(String subjectName) throws BusinessServiceException;
}
