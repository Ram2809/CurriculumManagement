package com.curriculum.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.ControllerException;
import com.curriculum.model.Teacher;
import com.curriculum.service.TeacherService;
import com.curriculum.service.impl.TeacherServiceImpl;

public class TeacherController {
	TeacherService teacherServiceImpl = (TeacherService) new TeacherServiceImpl();
	static Logger logger = Logger.getLogger("TeacherController.class");

	public int addTeacherDetails(Teacher teacher) {
		return teacherServiceImpl.addTeacherDetails(teacher);
	}

	public List<Teacher> getTeacherDetails() {
		return teacherServiceImpl.getTeacherDetails();
	}

	public int updateTeacherDetails(Long id,String userOption,String newValue) throws ControllerException {
		try {
			return teacherServiceImpl.updateTeacherDetails(id,userOption,newValue);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public int deleteTeacherDetails(Long id) throws ControllerException {
		try {
			return teacherServiceImpl.deleteTeacherDetails(id);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public List<Teacher> getParticularTeacherDetails(Long id) throws ControllerException {
		try {
			return teacherServiceImpl.getParticularTeacherDetails(id);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	/*public void getTeacherDetailsByClassRoom(Long roomNo) throws ControllerException {
		try {
			teacherServiceImpl.getTeacherDetailsByClassRoom(roomNo);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}*/

	/*public void getTeacherDetailsBySubjectName(String subjectName) throws ControllerException {
		logger.info("In teacher service -> get teacher details by subject name method");
		try {
			teacherServiceImpl.getTeacherDetailsBySubjectName(subjectName);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}*/
}
