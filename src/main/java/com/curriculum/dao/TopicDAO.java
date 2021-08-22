package com.curriculum.dao;

import java.util.List;

import com.curriculum.exception.DatabaseException;
import com.curriculum.model.Topic;

public interface TopicDAO {
	int addTopicsDetails(Topic topics);

	int updateTopicsDetails(String unitNo, String userOption, String newValue) throws DatabaseException;

	int updateTopicsStatusDetails(String unitNo, String userOption, Boolean newStatus) throws DatabaseException;

	int deleteTopicsDetails(String unitNo) throws DatabaseException;

	List<Topic> getTopicsDetails();

	List<Topic> getParticularTopicDetails(String unitNo) throws DatabaseException;
}
