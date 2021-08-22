package com.curriculum.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.ClassDAO;
import com.curriculum.dao.impl.ClassDAOImpl;
import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.DatabaseException;
import com.curriculum.model.ClassDetail;
import com.curriculum.service.ClassService;;

public class ClassServiceImpl implements ClassService {

	ClassDAO classDAOImpl = (ClassDAO) new ClassDAOImpl();
	// static Logger logger = Logger.getLogger("ClassServiceImpl.class");

	public int addClassDetails(ClassDetail classes) {
		return classDAOImpl.addClassDetails(classes);

	}

	public int updateClassDetails(Long roomNo,String userOption,String newValue) throws BusinessServiceException {
		try {
			return classDAOImpl.updateClassDetails(roomNo,userOption,newValue);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public int deleteClassDetails(Long roomNo) throws BusinessServiceException {
		try {
			return classDAOImpl.deleteClassDetails(roomNo);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public List<ClassDetail> getClassDetails() {
		return classDAOImpl.getClassDetails();
	}

}