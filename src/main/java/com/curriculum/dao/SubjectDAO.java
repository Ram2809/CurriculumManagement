package com.curriculum.dao;

import java.util.List;

import com.curriculum.exception.DatabaseException;
import com.curriculum.model.Subject;


public interface SubjectDAO {
	int addSubjectDetails(Subject subject);

	int updateSubjectDetails(String subjectCode,String userOption,String newValue) throws DatabaseException;
	int updateSubjectDetailsByRoomNo(String subjectCode, String userOption, Long newValue) throws DatabaseException;
	int deleteSubjectDetails(String subjectCode) throws DatabaseException;

	List<Subject> getSubjectDetails();

	List<Subject> getParticularSubjectDetails(String subjectCode) throws DatabaseException;

	//void getSubjectStatus(Integer subjectId) throws DatabaseException;
}
