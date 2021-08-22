package com.curriculum.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.ControllerException;
import com.curriculum.model.TeacherAssign;
import com.curriculum.service.TeacherAssignService;
import com.curriculum.service.impl.TeacherAssignServiceImpl;

public class TeacherAssignController {
	TeacherAssignService teacherAssignServiceImpl = (TeacherAssignService) new TeacherAssignServiceImpl();
	static Logger logger = Logger.getLogger("TeacherAssignController.class");

	public int addTeacherAssignDetails(TeacherAssign teacherAssignDetails) {
		return teacherAssignServiceImpl.addTeacherAssignDetails(teacherAssignDetails);
	}

	public List<TeacherAssign> getTeacherAssignDetails() {
		return teacherAssignServiceImpl.getTeacherAssignDetails();
	}

	public int updateTeacherAssignDetails(Long assignId,String userOption,Long newValue) throws ControllerException {
		try {
			return teacherAssignServiceImpl.updateTeacherAssignDetails(assignId,userOption,newValue);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	public int updateTeacherAssignDetailsBySubjectCode(Long assignId,String userOption,String newValue) throws ControllerException {
		try {
			return teacherAssignServiceImpl.updateTeacherAssignDetailsBySubjectCode(assignId,userOption,newValue);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public int deleteTeacherAssignDetails(Long assignId) throws ControllerException {
		try {
			return teacherAssignServiceImpl.deleteTeacherAssignDetails(assignId);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	/*public List<TeacherAssign> getParticularTeacherAssignDetails(Long assignId) throws ControllerException {
		try {
			return teacherAssignServiceImpl.getParticularTeacherAssignDetails(assignId);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}*/
}
