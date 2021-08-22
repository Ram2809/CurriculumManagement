package com.curriculum.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.ControllerException;
import com.curriculum.model.Student;
import com.curriculum.service.StudentService;
import com.curriculum.service.impl.StudentServiceImpl;

public class StudentController {
	StudentService studentServiceImpl = (StudentService) new StudentServiceImpl();
	static Logger logger = Logger.getLogger("StudentController.class");

	public int addStudentDetails(Student student) {
		return studentServiceImpl.addStudentDetails(student);
	}

	public List<Student> getStudentDetails() {
		return studentServiceImpl.getStudentDetails();
	}

	public int updateStudentDetails(Long id, String userOption, String newValue) throws ControllerException {
		try {
			return studentServiceImpl.updateStudentDetails(id, userOption, newValue);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public int updateStudentDetailsByRoomNo(Long id, String userOption, Long newValue) throws ControllerException {
		try {
			return studentServiceImpl.updateStudentDetailsByRoomNo(id, userOption, newValue);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public int deleteStudentDetails(Long id) throws ControllerException {
		try {
			return studentServiceImpl.deleteStudentDetails(id);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public List<Student> getParticularStudentDetails(Long id) throws ControllerException {
		try {
			return studentServiceImpl.getParticularStudentDetails(id);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public void getStudentDetailsByClassRoom(Long roomNo) throws ControllerException {
		try {
			studentServiceImpl.getStudentDetailsByClassRoom(roomNo);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

}
