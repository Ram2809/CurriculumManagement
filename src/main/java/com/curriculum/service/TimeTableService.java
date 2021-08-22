package com.curriculum.service;

import java.util.List;

import com.curriculum.exception.BusinessServiceException;
import com.curriculum.model.TimeTable;

public interface TimeTableService {
	int addTimeTableDetails(TimeTable timeTable) throws BusinessServiceException;

	int updateTimeTableDetails(Long classRoomNo, String day) throws BusinessServiceException;

	int deleteTimeTableDetails(Long classRoomNo, String day) throws BusinessServiceException;

	List<TimeTable> getTimeTableDetails();

	List<TimeTable> getParticularTimeTableDetails(Long classRoomNo, String day) throws BusinessServiceException;

	//void getTimeTableByclassRoom(Integer roomNo) throws BusinessServiceException;

	//void getTimeTableByclassStandard(Integer roomNo) throws BusinessServiceException;
}
