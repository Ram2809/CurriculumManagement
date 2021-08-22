package com.curriculum.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.ControllerException;
import com.curriculum.model.Discussion;
import com.curriculum.service.DiscussionService;
import com.curriculum.service.impl.DiscussionServiceImpl;


public class DiscussionController {
	DiscussionService discussionServiceImpl = (DiscussionService) new DiscussionServiceImpl();
	static Logger logger = Logger.getLogger("DiscussionController.class");

	public int addDiscussionDetails(Discussion discussion) {
		return discussionServiceImpl.addDiscussionDetails(discussion);
	}

	public List<Discussion> getDiscussionDetails() {
		return discussionServiceImpl.getDiscussionDetails();
	}

	public int updateDiscussionDetails(Long questionNo,String userOption,String newValue) throws ControllerException {
		try {
			return discussionServiceImpl.updateDiscussionDetails(questionNo,userOption,newValue);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public int deleteDiscussionDetails(Long questionNo) throws ControllerException {
		try {
			return discussionServiceImpl.deleteDiscussionDetails(questionNo);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public List<Discussion> getParticularDiscussionDetails(Long questionNo) throws ControllerException {
		try {
			return discussionServiceImpl.getParticularDiscussionDetails(questionNo);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	/*public void getDiscussionStatusByUnit(String unitNo) throws ControllerException {
		try {
			discussionServiceImpl.getDiscussionStatusByUnit(unitNo);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}*/
}
