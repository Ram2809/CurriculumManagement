package com.curriculum.service;

import java.util.List;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.model.Subject;

public interface SubjectService {
	int addSubjectDetails(Subject subject);

	List<Subject> getSubjectDetails();

	int updateSubjectDetails(String subjectCode, String userOption, String newValue) throws BusinessServiceException;
	int updateSubjectDetailsByRoomNo(String subjectCode, String userOption, Long newValue) throws BusinessServiceException;
	int deleteSubjectDetails(String subjectCode) throws BusinessServiceException;

	List<Subject> getParticularSubjectDetails(String subjectCode) throws BusinessServiceException;

	//void getSubjectStatus(Integer subjectId) throws BusinessServiceException;
}
