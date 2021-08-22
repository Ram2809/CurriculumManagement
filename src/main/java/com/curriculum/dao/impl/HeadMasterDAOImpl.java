package com.curriculum.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.HeadMasterDAO;
import com.curriculum.model.HeadMaster;
import com.curriculum.util.DBUtil;

public class HeadMasterDAOImpl implements HeadMasterDAO {
	static List<HeadMaster> headMasterList = new ArrayList<>();
	static Logger logger = Logger.getLogger("HeadMasterDAOImpl.class");

	public int addHeadMasterDetails(HeadMaster headMaster) {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "INSERT INTO headmaster(FirstName,LastName,DateOfBirth,Gender,Address,Qualification) VALUES(?,?,?,?,?,?)";
			pst = con.prepareStatement(query);
			pst.setString(1, headMaster.getFirstName());
			pst.setString(2, headMaster.getLastName());
			pst.setString(3, headMaster.getDateOfBirth());
			pst.setString(4, headMaster.getGender());
			pst.setString(5, headMaster.getAddress());
			pst.setString(6, headMaster.getQualification());
			count = pst.executeUpdate();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return count;
	}

	public int updateHeadMasterDetails(Long id, String userOption, String newValue) {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "";
			query = "UPDATE headmaster SET " + userOption + "=? Where Id=?";
			pst = con.prepareStatement(query);
			pst.setString(1, newValue);
			pst.setLong(2, id);
			count = pst.executeUpdate();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return count;
	}

	public int deleteHeadMasterDetails(Long id) {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "DELETE FROM headmaster WHERE Id=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, id);
			count = pst.executeUpdate();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return count;
	}

	public List<HeadMaster> getHeadMasterDetails() {
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "SELECT * FROM headmaster";
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				headMasterList.add(new HeadMaster(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7)));
			}
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return headMasterList;
	}
}
