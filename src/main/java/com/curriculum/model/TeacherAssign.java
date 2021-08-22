package com.curriculum.model;

public class TeacherAssign {
	private Long id;
	private Long teacherId;
	private String subjectCode;

	public TeacherAssign() {
		super();
	}

	public TeacherAssign(Long id, Long teacherId, String subjectCode) {
		super();
		this.id = id;
		this.teacherId = teacherId;
		this.subjectCode = subjectCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	@Override
	public String toString() {
		return "TeacherAssign [id=" + id + ", teacherId=" + teacherId + ", subjectCode=" + subjectCode + "]";
	}

}
