package com.curriculum.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.model.HeadMaster;
import com.curriculum.service.HeadMasterService;
import com.curriculum.service.impl.HeadMasterServiceImpl;

public class HeadMasterController {
	static HeadMasterService headMasterServiceImpl = (HeadMasterService) new HeadMasterServiceImpl();
	static Logger logger = Logger.getLogger("HeadMasterController.class");

	public static int addHeadMasterDetails(HeadMaster headMaster) {
		return headMasterServiceImpl.addHeadMasterDetails(headMaster);
	}

	public static int updateHeadMasterDetails(Long id, String userOption, String newValue) {
		return headMasterServiceImpl.updateHeadMasterDetails(id, userOption, newValue);
	}

	public static int deleteHeadMasterDetails(Long id) {
		return headMasterServiceImpl.deleteHeadMasterDetails(id);
	}

	public static List<HeadMaster> getHeadMasterDetails() {
		return headMasterServiceImpl.getHeadMasterDetails();
	}
}
