package com.curriculum.service;

import java.util.List;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.model.Student;

public interface StudentService {
	int addStudentDetails(Student student);

	List<Student> getStudentDetails();

	int updateStudentDetails(Long id, String userOption, String newValue) throws BusinessServiceException;

	int updateStudentDetailsByRoomNo(Long id, String userOption, Long newValue) throws BusinessServiceException;

	int deleteStudentDetails(Long id) throws BusinessServiceException;

	List<Student> getParticularStudentDetails(Long id) throws BusinessServiceException;

	void getStudentDetailsByClassRoom(Long roomNo) throws BusinessServiceException;
}
