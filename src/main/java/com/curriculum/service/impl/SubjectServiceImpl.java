package com.curriculum.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.SubjectDAO;
import com.curriculum.dao.impl.SubjectDAOImpl;
import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.DatabaseException;
import com.curriculum.model.Subject;
import com.curriculum.service.SubjectService;

public class SubjectServiceImpl implements SubjectService{
	SubjectDAO subjectDAOImpl = (SubjectDAO) new SubjectDAOImpl();
	static Logger logger = Logger.getLogger("SubjectServiceImpl.class");

	public int addSubjectDetails(Subject subject) {
		return subjectDAOImpl.addSubjectDetails(subject);
	}

	public int updateSubjectDetails(String subjectCode, String userOption, String newValue)
			throws BusinessServiceException {
		try {
			return subjectDAOImpl.updateSubjectDetails(subjectCode,userOption,newValue);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}
	public int updateSubjectDetailsByRoomNo(String subjectCode,String userOption,Long newValue) throws BusinessServiceException {
		try {
			return subjectDAOImpl.updateSubjectDetailsByRoomNo(subjectCode,userOption,newValue);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}
	public int deleteSubjectDetails(String subjectCode) throws BusinessServiceException {
		try {
			return subjectDAOImpl.deleteSubjectDetails(subjectCode);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public List<Subject> getSubjectDetails() {
		return subjectDAOImpl.getSubjectDetails();
	}

	public List<Subject> getParticularSubjectDetails(String subjectCode) throws BusinessServiceException {
		try {
			return subjectDAOImpl.getParticularSubjectDetails(subjectCode);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	/*public void getSubjectStatus(Integer subjectId) throws BusinessServiceException {
		logger.info("In subject DAO -> get subject status method");
		try {
			subjectDAOImpl.getSubjectStatus(subjectId);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}*/
}
