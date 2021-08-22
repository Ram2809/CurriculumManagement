package com.curriculum.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.ControllerException;
import com.curriculum.model.Subject;
import com.curriculum.service.SubjectService;
import com.curriculum.service.impl.SubjectServiceImpl;


public class SubjectController {
	SubjectService subjectServiceImpl = (SubjectService) new SubjectServiceImpl();
	static Logger logger = Logger.getLogger("SubjectController.class");

	public int addSubjectDetails(Subject subject) {
		return subjectServiceImpl.addSubjectDetails(subject);
	}

	public List<Subject> getSubjectDetails() {
		return subjectServiceImpl.getSubjectDetails();
	}

	public int updateSubjectDetails(String subjectCode,String userOption,String newValue) throws ControllerException {
		try {
			return subjectServiceImpl.updateSubjectDetails(subjectCode,userOption,newValue);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	public int updateStudentDetailsByRoomNo(String subjectCode, String userOption, Long newValue) throws ControllerException {
		try {
			return subjectServiceImpl.updateSubjectDetailsByRoomNo(subjectCode, userOption, newValue);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public int deleteSubjectDetails(String subjectCode) throws ControllerException {
		try {
			return subjectServiceImpl.deleteSubjectDetails(subjectCode);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public List<Subject> getParticularSubjectDetails(String subjectCode) throws ControllerException {
		try {
			return subjectServiceImpl.getParticularSubjectDetails(subjectCode);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	/*public void getSubjectStatus(Integer subjectId) throws ControllerException {
		logger.info("In subject service -> get subject status method");
		try {
			subjectServiceImpl.getSubjectStatus(subjectId);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}*/
}
