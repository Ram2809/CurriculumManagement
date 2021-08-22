package com.curriculum.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.TopicDAO;
import com.curriculum.dao.impl.TopicDAOImpl;
import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.DatabaseException;
import com.curriculum.model.Topic;
import com.curriculum.service.TopicService;

public class TopicServiceImpl implements TopicService{
	TopicDAO topicsDAOImpl = new TopicDAOImpl();
	static Logger logger = Logger.getLogger("TopicServiceImpl.class");

	public int addTopicsDetails(Topic topics) {
		return topicsDAOImpl.addTopicsDetails(topics);
	}

	public int updateTopicsDetails(String unitNo,String userOption,String newValue) throws BusinessServiceException {
		try {
			return topicsDAOImpl.updateTopicsDetails(unitNo,userOption,newValue);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}
	public int updateTopicsStatusDetails(String unitNo,String userOption,Boolean newStatus) throws BusinessServiceException {
		try {
			return topicsDAOImpl.updateTopicsStatusDetails(unitNo,userOption,newStatus);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public int deleteTopicsDetails(String unitNo) throws BusinessServiceException {
		try {
			return topicsDAOImpl.deleteTopicsDetails(unitNo);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public List<Topic> getTopicsDetails() {
		return topicsDAOImpl.getTopicsDetails();
	}

	public List<Topic> getParticularTopicDetails(String unitNo) throws BusinessServiceException {
		try {
			return topicsDAOImpl.getParticularTopicDetails(unitNo);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}
}
