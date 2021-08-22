package com.curriculum.dao;

import java.util.List;

import com.curriculum.exception.DatabaseException;
import com.curriculum.model.TeacherAssign;

public interface TeacherAssignDAO {
	int addTeacherAssignDetails(TeacherAssign teacherAssignDetails);

	List<TeacherAssign> getTeacherAssignDetails();

	int updateTeacherAssignDetails(Long assignId,String userOption,Long newValue) throws DatabaseException;
	int updateTeacherAssignDetailsBySubjectCode(Long assignId,String userOption,String newValue) throws DatabaseException;

	int deleteTeacherAssignDetails(Long assignId) throws DatabaseException;

	//List<TeacherAssign> getParticularTeacherAssignDetails(Long assignId) throws BusinessServiceException;
}
