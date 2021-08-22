package com.curriculum.model;

public class Subject {
	private Long id;
	private String code;
	private String name;
	private Long roomNo;

	public Subject() {
		super();
	}

	public Subject(Long id, String code, String name, Long roomNo) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.roomNo = roomNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Long roomNo) {
		this.roomNo = roomNo;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", code=" + code + ", name=" + name + ", roomNo=" + roomNo + "]";
	}

}
