package com.curriculum.service;

import java.util.List;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.model.Topic;

public interface TopicService {
	int addTopicsDetails(Topic topics);

	List<Topic> getTopicsDetails();

	int updateTopicsDetails(String unitNo, String userOption, String newValue) throws BusinessServiceException;

	int updateTopicsStatusDetails(String unitNo, String userOption, Boolean newStatus) throws BusinessServiceException;

	int deleteTopicsDetails(String unitNo) throws BusinessServiceException;

	List<Topic> getParticularTopicDetails(String unitNo) throws BusinessServiceException;
}
