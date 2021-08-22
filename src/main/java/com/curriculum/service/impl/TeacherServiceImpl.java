package com.curriculum.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.TeacherDAO;
import com.curriculum.dao.impl.TeacherDAOImpl;
import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.DatabaseException;
import com.curriculum.model.Teacher;
import com.curriculum.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {
	TeacherDAO teacherDAOImpl = (TeacherDAO) new TeacherDAOImpl();
	static Logger logger = Logger.getLogger("TeacherServiceImpl.class");

	public int addTeacherDetails(Teacher teacher) {
		return teacherDAOImpl.addTeacherDetails(teacher);
	}

	public int updateTeacherDetails(Long id, String userOption, String newValue) throws BusinessServiceException {
		try {
			return teacherDAOImpl.updateTeacherDetails(id, userOption, newValue);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public int deleteTeacherDetails(Long id) throws BusinessServiceException {
		try {
			return teacherDAOImpl.deleteTeacherDetails(id);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public List<Teacher> getTeacherDetails() {
		return teacherDAOImpl.getTeacherDetails();
	}

	public List<Teacher> getParticularTeacherDetails(Long id) throws BusinessServiceException {
		try {
			return teacherDAOImpl.getParticularTeacherDetails(id);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	/*public void getTeacherDetailsByClassRoom(Long roomNo) throws BusinessServiceException {
		try {
			teacherDAOImpl.getTeacherDetailsByClassRoom(roomNo);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}*/

/*	@Override
	public void getTeacherDetailsBySubjectName(String subjectName) throws BusinessServiceException {
		logger.info("In teacher DAO -> get teacher details by subject name method");
		try {
			teacherDAOImpl.getTeacherDetailsBySubjectName(subjectName);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}

	}*/

}
