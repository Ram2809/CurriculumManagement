package com.curriculum.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.TeacherAssignDAO;
import com.curriculum.dao.impl.TeacherAssignDAOImpl;
import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.DatabaseException;
import com.curriculum.model.TeacherAssign;
import com.curriculum.service.TeacherAssignService;

public class TeacherAssignServiceImpl implements TeacherAssignService {
	TeacherAssignDAO teacherAssignDAOImpl = (TeacherAssignDAO) new TeacherAssignDAOImpl();
	static Logger logger = Logger.getLogger("TeacherAssignServiceImpl.class");

	public int addTeacherAssignDetails(TeacherAssign teacherAssignDetails) {
		return teacherAssignDAOImpl.addTeacherAssignDetails(teacherAssignDetails);
	}

	public int updateTeacherAssignDetails(Long assignId, String userOption, Long newValue)
			throws BusinessServiceException {
		try {
			return teacherAssignDAOImpl.updateTeacherAssignDetails(assignId, userOption, newValue);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public int updateTeacherAssignDetailsBySubjectCode(Long assignId, String userOption, String newValue)
			throws BusinessServiceException {
		try {
			return teacherAssignDAOImpl.updateTeacherAssignDetailsBySubjectCode(assignId, userOption, newValue);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public int deleteTeacherAssignDetails(Long assignId) throws BusinessServiceException {
		try {
			return teacherAssignDAOImpl.deleteTeacherAssignDetails(assignId);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
		
	}

	public List<TeacherAssign> getTeacherAssignDetails() {
		return teacherAssignDAOImpl.getTeacherAssignDetails();
	}

	/*public List<TeacherAssign> getParticularTeacherAssignDetails(Long assignId) throws BusinessServiceException {
		return teacherAssignDAOImpl.getParticularTeacherAssignDetails(assignId);
	}*/
}
