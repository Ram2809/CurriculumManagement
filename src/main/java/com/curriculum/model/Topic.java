package com.curriculum.model;

public class Topic {
	private Long id;
	private String unitNo;
	private String unitName;
	private String beginDate;
	private Boolean status;
	private String subjectCode;
	public Topic() {
		super();
	}
	public Topic(Long id, String unitNo, String unitName, String beginDate, Boolean status, String subjectCode) {
		super();
		this.id = id;
		this.unitNo = unitNo;
		this.unitName = unitName;
		this.beginDate = beginDate;
		this.status = status;
		this.subjectCode = subjectCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	@Override
	public String toString() {
		return "Topic [id=" + id + ", unitNo=" + unitNo + ", unitName=" + unitName + ", beginDate=" + beginDate
				+ ", status=" + status + ", subjectCode=" + subjectCode + "]";
	}
	
}
