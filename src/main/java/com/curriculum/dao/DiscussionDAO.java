package com.curriculum.dao;

import java.util.List;

import com.curriculum.exception.DatabaseException;
import com.curriculum.model.Discussion;

public interface DiscussionDAO {
	int addDiscussionDetails(Discussion discussion);

	int updateDiscussionDetails(Long questionNo,String userOption,String newValue) throws DatabaseException;

	int deleteDiscussionDetails(Long questionNo) throws DatabaseException;

	List<Discussion> getDiscussionDetails();

	List<Discussion> getParticularDiscussionDetails(Long questionNo) throws DatabaseException;

	//void getDiscussionStatusByUnit(String unitNo) throws DatabaseException;
}
