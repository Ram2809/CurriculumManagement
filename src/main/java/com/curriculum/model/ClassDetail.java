package com.curriculum.model;

public class ClassDetail {
	private Long roomNo;
	private String standard;
	private String section;

	public ClassDetail() {
		super();
	}

	public ClassDetail(Long roomNo, String standard, String section) {
		super();
		this.roomNo = roomNo;
		this.standard = standard;
		this.section = section;
	}

	public Long getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Long roomNo) {
		this.roomNo = roomNo;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	@Override
	public String toString() {
		return "ClassDetail [roomNo=" + roomNo + ", standard=" + standard + ", section=" + section + "]";
	}

}
