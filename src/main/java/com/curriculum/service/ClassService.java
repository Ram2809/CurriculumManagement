package com.curriculum.service;

import java.util.List;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.model.ClassDetail;

public interface ClassService {
	int addClassDetails(ClassDetail classes);

	int updateClassDetails(Long roomNo,String userOption,String newValue) throws BusinessServiceException;

	int deleteClassDetails(Long roomNo) throws BusinessServiceException;

	List<ClassDetail> getClassDetails();
}
