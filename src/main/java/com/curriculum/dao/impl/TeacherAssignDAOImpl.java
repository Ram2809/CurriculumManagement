package com.curriculum.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.TeacherAssignDAO;
import com.curriculum.exception.DatabaseException;
import com.curriculum.exception.TeacherAssignIdNotFoundException;
import com.curriculum.model.TeacherAssign;
import com.curriculum.util.DBUtil;

public class TeacherAssignDAOImpl implements TeacherAssignDAO{
	static List<TeacherAssign> teacherAssignList = new ArrayList<>();
	static List<Integer> teacherIdList = new ArrayList<>();
	static Logger logger = Logger.getLogger("TeacherAssignDAOImpl.class");

	public boolean checkTeacherAssignId(Long id) {
		boolean status = false;
		try (Connection con = DBUtil.getConnection();) {
			Statement statement = null;
			String query = "SELECT * FROM teachersubject where Id=" + id;
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			status = rs.next();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return status;
	}

	public int addTeacherAssignDetails(TeacherAssign teacherAssignDetails) {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "INSERT INTO teachersubject(TeacherId,subjectCode) VALUES(?,?)";
			pst = con.prepareStatement(query);
			pst.setLong(1, teacherAssignDetails.getTeacherId());
			pst.setString(2, teacherAssignDetails.getSubjectCode());
			count = pst.executeUpdate();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return count;
	}

	public int updateTeacherAssignDetails(Long assignId, String userOption, Long teacherId) throws DatabaseException {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "";
			boolean status = checkTeacherAssignId(assignId);
			if (!status) {
				throw new TeacherAssignIdNotFoundException("Teacher Assign Id not found,Enter the valid ID!");
			}
			query = "UPDATE teachersubject SET " + userOption + "=? Where Id=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, teacherId);
			pst.setLong(2, assignId);
			count = pst.executeUpdate();
		} catch (SQLException | TeacherAssignIdNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public int updateTeacherAssignDetailsBySubjectCode(Long assignId, String userOption, String subjectCode)
			throws DatabaseException {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "";
			boolean status = checkTeacherAssignId(assignId);
			if (!status) {
				throw new TeacherAssignIdNotFoundException("Teacher Assign Id not found,Enter the valid ID!");
			}
			query = "UPDATE teachersubject SET " + userOption + "=? Where Id=?";
			pst = con.prepareStatement(query);
			pst.setString(1, subjectCode);
			pst.setLong(2, assignId);
			count = pst.executeUpdate();
		} catch (SQLException | TeacherAssignIdNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public int deleteTeacherAssignDetails(Long assignId) throws DatabaseException {
		int count = 0;
		try {
			boolean status = checkTeacherAssignId(assignId);
			if (!status) {
				throw new TeacherAssignIdNotFoundException("Teacher Assign Id not found,Enter the valid ID!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "DELETE FROM teachersubject WHERE Id=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, assignId);
			count = pst.executeUpdate();
		} catch (SQLException | TeacherAssignIdNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public List<TeacherAssign> getTeacherAssignDetails() {
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "SELECT * FROM teachersubject";
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				teacherAssignList.add(new TeacherAssign(rs.getLong(1), rs.getLong(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return teacherAssignList;
	}


	/*@Override
	public List<TeacherAssign> getParticularTeacherAssignDetails(Long assignId) throws BusinessServiceException {
		// TODO Auto-generated method stub
		return null;
	}*/

}
