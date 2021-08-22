package com.curriculum.service;

import java.util.List;

import com.curriculum.model.HeadMaster;

public interface HeadMasterService {
	int addHeadMasterDetails(HeadMaster headMaster);

	int updateHeadMasterDetails(Long id, String userOption,String newValue);

	int deleteHeadMasterDetails(Long id);

	List<HeadMaster> getHeadMasterDetails();
}
