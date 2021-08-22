package com.curriculum.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.StudentDAO;
import com.curriculum.dao.impl.StudentDAOImpl;
import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.DatabaseException;
import com.curriculum.model.Student;
import com.curriculum.service.StudentService;

public class StudentServiceImpl implements StudentService {
	StudentDAO studentDAOImpl = new StudentDAOImpl();
	static Logger logger = Logger.getLogger("StudentServiceImpl.class");

	public int addStudentDetails(Student student) {
		return studentDAOImpl.addStudentDetails(student);
	}

	public int updateStudentDetails(Long id,String userOption,String newValue) throws BusinessServiceException {
		try {
			return studentDAOImpl.updateStudentDetails(id,userOption,newValue);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}
	public int updateStudentDetailsByRoomNo(Long id,String userOption,Long newValue) throws BusinessServiceException {
		try {
			return studentDAOImpl.updateStudentDetailsByRoomNo(id,userOption,newValue);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public int deleteStudentDetails(Long id) throws BusinessServiceException {
		try {
			return studentDAOImpl.deleteStudentDetails(id);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public List<Student> getStudentDetails() {
		return studentDAOImpl.getStudentDetails();
	}

	public List<Student> getParticularStudentDetails(Long id) throws BusinessServiceException {
		try {
			return studentDAOImpl.getParticularStudentDetails(id);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public void getStudentDetailsByClassRoom(Long roomNo) throws BusinessServiceException {
		try {
			studentDAOImpl.getStudentDetailsByClassRoom(roomNo);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

}
