package com.curriculum.dao;

import java.util.List;

import com.curriculum.exception.DatabaseException;
import com.curriculum.model.ClassDetail;

public interface ClassDAO {
	int addClassDetails(ClassDetail classes);

	int updateClassDetails(Long roomNo,String userOption,String newValue) throws DatabaseException;

	int deleteClassDetails(Long roomNo) throws DatabaseException;

	List<ClassDetail> getClassDetails();
}
