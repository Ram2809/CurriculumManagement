package com.curriculum.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.TimeTableDAO;
import com.curriculum.dao.impl.TimeTableDAOImpl;
import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.DatabaseException;
import com.curriculum.model.TimeTable;
import com.curriculum.service.TimeTableService;

public class TimeTableServiceImpl implements TimeTableService{
	TimeTableDAO timeTableDAOImpl = new TimeTableDAOImpl();
	static Logger logger = Logger.getLogger("TimeTableServiceImpl.class");

	public int addTimeTableDetails(TimeTable timeTable) throws BusinessServiceException {
		try {
			return timeTableDAOImpl.addTimeTableDetails(timeTable);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public int updateTimeTableDetails(Long classRoomNo, String day) throws BusinessServiceException {
		logger.info("In time table DAO -> update method");
		try {
			return timeTableDAOImpl.updateTimeTableDetails(classRoomNo, day);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public void deleteTimeTableDetails(Integer classId, String day) throws BusinessServiceException {
		logger.info("In time table DAO -> delete method");
		try {
			timeTableDAOImpl.deleteTimeTableDetails(classId, day);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public List<TimeTable> getTimeTableDetails() {
		logger.info("In time table DAO -> get method");
		return timeTableDAOImpl.getTimeTableDetails();
	}

	public List<TimeTable> getParticularTimeTableDetails(Integer classId, String day) throws BusinessServiceException {
		logger.info("In time table DAO -> get particular time table details method");
		try {
			return timeTableDAOImpl.getParticularTimeTableDetails(classId, day);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	public void getTimeTableByclassRoom(Integer roomNo) throws BusinessServiceException {
		logger.info("In time table DAO -> get time table by class room method");
		try {
			timeTableDAOImpl.getTimeTableByclassRoom(roomNo);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}

	}

	public void getTimeTableByclassStandard(Integer roomNo) throws BusinessServiceException {
		logger.info("In time table DAO -> get time table by standard method");
		try {
			timeTableDAOImpl.getTimeTableByclassStandard(roomNo);
		} catch (DatabaseException e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}
}
