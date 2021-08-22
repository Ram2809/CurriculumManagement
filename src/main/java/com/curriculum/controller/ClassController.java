package com.curriculum.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.ControllerException;
import com.curriculum.model.ClassDetail;
import com.curriculum.service.ClassService;
import com.curriculum.service.impl.ClassServiceImpl;

public class ClassController {
	static ClassService classServiceImpl = new ClassServiceImpl();
	// static Logger logger = Logger.getLogger("ClassController.class");

	public int addClassDetails(ClassDetail classes) {
		return classServiceImpl.addClassDetails(classes);
	}

	public static List<ClassDetail> getClassDetails() {
		return classServiceImpl.getClassDetails();
	}

	public int updateClassDetails(Long roomNo,String userOption,String newValue) throws ControllerException {
		try {
			return classServiceImpl.updateClassDetails(roomNo,userOption,newValue);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public int deleteClassDetails(Long roomNo) throws ControllerException {
		try {
			return classServiceImpl.deleteClassDetails(roomNo);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
}
