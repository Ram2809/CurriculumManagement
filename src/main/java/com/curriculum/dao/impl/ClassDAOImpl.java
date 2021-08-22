package com.curriculum.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.ClassDAO;
import com.curriculum.exception.ClassRoomNotFoundException;
import com.curriculum.exception.DatabaseException;
import com.curriculum.model.ClassDetail;
import com.curriculum.util.DBUtil;

public class ClassDAOImpl implements ClassDAO {
	static List<ClassDetail> classList = new ArrayList<>();
	static Logger logger = Logger.getLogger("ClassDAOImpl.class");

	public static boolean checkClassId(Long roomNo) {
		boolean status = false;
		try (Connection con = DBUtil.getConnection();) {
			Statement statement = null;
			String query = "SELECT * FROM class where roomNo=" + roomNo;
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			status = rs.next();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return status;
	}

	public int addClassDetails(ClassDetail classes) {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "INSERT INTO class(standard,section) VALUES(?,?)";
			pst = con.prepareStatement(query);
			pst.setString(1, classes.getStandard());
			pst.setString(2, classes.getSection());
			count = pst.executeUpdate();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return count;
	}

	public int updateClassDetails(Long roomNo, String userOption, String newValue) throws DatabaseException {
		int count = 0;
		try {
			boolean checkUser = checkClassId(roomNo);
			if (!checkUser) {
				throw new ClassRoomNotFoundException("Class Room No not found, Enter the valid room no!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "";
			query = "UPDATE class SET " + userOption + "=? Where RoomNo=?";
			pst = con.prepareStatement(query);
			pst.setString(1, newValue);
			pst.setLong(2, roomNo);
			count = pst.executeUpdate();
		} catch (SQLException | ClassRoomNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public int deleteClassDetails(Long roomNo) throws DatabaseException {
		int count = 0;
		try {
			boolean checkUser = checkClassId(roomNo);
			if (!checkUser) {
				throw new ClassRoomNotFoundException("Class Room No not found, Enter the valid room no!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "DELETE FROM class WHERE RoomNo=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, roomNo);
			count = pst.executeUpdate();
		} catch (SQLException | ClassRoomNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public List<ClassDetail> getClassDetails() {
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "SELECT * FROM class";
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				classList.add(new ClassDetail(rs.getLong(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return classList;
	}
}
