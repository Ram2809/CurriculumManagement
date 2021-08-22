package com.curriculum.service;

import java.util.List;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.model.TeacherAssign;

public interface TeacherAssignService {
	int addTeacherAssignDetails(TeacherAssign teacherAssignDetails);

	List<TeacherAssign> getTeacherAssignDetails();

	int updateTeacherAssignDetails(Long assignId, String userOption, Long newValue) throws BusinessServiceException;

	int updateTeacherAssignDetailsBySubjectCode(Long assignId, String userOption, String newValue)
			throws BusinessServiceException;

	int deleteTeacherAssignDetails(Long assignId) throws BusinessServiceException;

	//List<TeacherAssign> getParticularTeacherAssignDetails(Long assignId) throws BusinessServiceException;
}
