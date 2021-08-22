package com.curriculum.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.exception.ControllerException;
import com.curriculum.model.TimeTable;
import com.curriculum.service.TimeTableService;
import com.curriculum.service.impl.TimeTableServiceImpl;

public class TimeTableController {
	TimeTableService timeTableServiceImpl = new TimeTableServiceImpl();
	static Logger logger = Logger.getLogger("TimeTableController.class");

	public void addTimeTableDetails(TimeTable timeTable) throws ControllerException {
		int count=0;
		try {
			return timeTableServiceImpl.addTimeTableDetails(timeTable);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public int updateTimeTableDetails(Long classRoomNo, String day) throws ControllerException {
		try {
			return timeTableServiceImpl.updateTimeTableDetails(classRoomNo, day);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public int deleteTimeTableDetails(Long classRoomNo, String day) throws ControllerException {
		try {
			return timeTableServiceImpl.deleteTimeTableDetails(classRoomNo, day);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public List<TimeTable> getTimeTableDetails() {
		return timeTableServiceImpl.getTimeTableDetails();
	}

	public List<TimeTable> getParticularTimeTableDetails(Long classRoomNo, String day) throws ControllerException {
		try {
			return timeTableServiceImpl.getParticularTimeTableDetails(classRoomNo, day);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	/*public void getTimeTableByclassRoom(Integer roomNo) throws ControllerException {
		logger.info("In time table service -> get time table for specific class room method");
		try {
			timeTableServiceImpl.getTimeTableByclassRoom(roomNo);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	public void getTimeTableByclassStandard(Integer roomNo) throws ControllerException {
		logger.info("In time table service -> get time table for specific standard method");
		try {
			timeTableServiceImpl.getTimeTableByclassStandard(roomNo);
		} catch (BusinessServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}*/
}
