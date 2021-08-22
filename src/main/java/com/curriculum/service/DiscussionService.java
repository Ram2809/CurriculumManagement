package com.curriculum.service;

import java.util.List;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.model.Discussion;

public interface DiscussionService {
	int addDiscussionDetails(Discussion discussion);

	List<Discussion> getDiscussionDetails();

	int updateDiscussionDetails(Long questionNo,String userOption,String newValue) throws BusinessServiceException;

	int deleteDiscussionDetails(Long questionNo) throws BusinessServiceException;

	List<Discussion> getParticularDiscussionDetails(Long questioNo) throws BusinessServiceException;

	//void getDiscussionStatusByUnit(String unitNo) throws BusinessServiceException;
}
