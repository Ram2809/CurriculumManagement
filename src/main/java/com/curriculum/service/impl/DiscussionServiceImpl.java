package com.curriculum.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.DiscussionDAO;
import com.curriculum.dao.impl.DiscussionDAOImpl;
import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.DatabaseException;
import com.curriculum.model.Discussion;
import com.curriculum.service.DiscussionService;

public class DiscussionServiceImpl implements DiscussionService {
	DiscussionDAO discussionDAOImpl = (DiscussionDAO) new DiscussionDAOImpl();
	static Logger logger = Logger.getLogger("DiscussionServiceImpl.class");

	public int addDiscussionDetails(Discussion discussion) {
		return discussionDAOImpl.addDiscussionDetails(discussion);
	}

	public int updateDiscussionDetails(Long questionNo,String userOption,String newValue) throws BusinessServiceException {
		try {
			return discussionDAOImpl.updateDiscussionDetails(questionNo,userOption,newValue);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public int deleteDiscussionDetails(Long questionNo) throws BusinessServiceException {
		try {
			return discussionDAOImpl.deleteDiscussionDetails(questionNo);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public List<Discussion> getDiscussionDetails() {
		return discussionDAOImpl.getDiscussionDetails();
	}

	public List<Discussion> getParticularDiscussionDetails(Long questionNo) throws BusinessServiceException {
		try {
			return discussionDAOImpl.getParticularDiscussionDetails(questionNo);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	/*public void getDiscussionStatusByUnit(String unitNo) throws BusinessServiceException {
		try {
			discussionDAOImpl.getDiscussionStatusByUnit(unitNo);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}

	}*/

}
