package com.curriculum.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.ControllerException;
import com.curriculum.model.Topic;
import com.curriculum.service.TopicService;
import com.curriculum.service.impl.TopicServiceImpl;

public class TopicController {
	TopicService topicsServiceImpl = (TopicService) new TopicServiceImpl();
	static Logger logger = Logger.getLogger("TopicController.class");

	public int addTopicsDetails(Topic topics) {
		return topicsServiceImpl.addTopicsDetails(topics);
	}

	public List<Topic> getTopicsDetails() {
		return topicsServiceImpl.getTopicsDetails();
	}

	public int updateTopicsDetails(String unitNo,String userOption,String newValue) throws ControllerException {
		try {
			return topicsServiceImpl.updateTopicsDetails(unitNo,userOption,newValue);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	public int updateTopicsStatusDetails(String unitNo,String userOption,Boolean newStatus) throws ControllerException {
		try {
			return topicsServiceImpl.updateTopicsStatusDetails(unitNo,userOption,newStatus);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public int deleteTopicsDetails(String unitNo) throws ControllerException {
		try {
			return topicsServiceImpl.deleteTopicsDetails(unitNo);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public List<Topic> getParticularTopicDetails(String unitNo) throws ControllerException {
		try {
			return topicsServiceImpl.getParticularTopicDetails(unitNo);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
}
