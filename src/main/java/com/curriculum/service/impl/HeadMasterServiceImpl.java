package com.curriculum.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.HeadMasterDAO;
import com.curriculum.dao.impl.HeadMasterDAOImpl;
import com.curriculum.model.HeadMaster;
import com.curriculum.service.HeadMasterService;

public class HeadMasterServiceImpl implements HeadMasterService {
	HeadMasterDAO headMasterDAOImpl = (HeadMasterDAO) new HeadMasterDAOImpl();
	static Logger logger = Logger.getLogger("HeadMasterServiceImpl.class");

	public int addHeadMasterDetails(HeadMaster headMaster) {
		return headMasterDAOImpl.addHeadMasterDetails(headMaster);
	}

	public int updateHeadMasterDetails(Long id, String userOption, String newValue) {
		return headMasterDAOImpl.updateHeadMasterDetails(id, userOption, newValue);
	}

	public int deleteHeadMasterDetails(Long id) {
		return headMasterDAOImpl.deleteHeadMasterDetails(id);
	}

	public List<HeadMaster> getHeadMasterDetails() {
		return headMasterDAOImpl.getHeadMasterDetails();
	}
}
