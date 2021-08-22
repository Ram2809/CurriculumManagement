package com.curriculum.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.TopicDAO;
import com.curriculum.exception.DatabaseException;
import com.curriculum.exception.TopicNotFoundException;
import com.curriculum.model.Topic;
import com.curriculum.util.DBUtil;

public class TopicDAOImpl implements TopicDAO{
	static List<Topic> topicsList = new ArrayList<>();
	static Logger logger = Logger.getLogger("TopicDAOImpl.class");

	public boolean checkUnitNo(String unitNo) {
		boolean status = false;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement preparedStatement = null;
			String query = "SELECT * FROM topic where UnitNo=?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, unitNo);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return status;
	}

	public int addTopicsDetails(Topic topics) {
		int count=0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "INSERT INTO topic(UnitNo,UnitName,beginDate,status,SubjectCode) VALUES(?,?,?,?,?)";
			pst = con.prepareStatement(query);
			pst.setString(1, topics.getUnitNo());
			pst.setString(2, topics.getUnitName());
			pst.setString(3, topics.getBeginDate());
			pst.setBoolean(4, topics.getStatus());
			pst.setString(5, topics.getSubjectCode());
			count=pst.executeUpdate();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return count;
	}

	public int updateTopicsDetails(String unitNo,String userOption,String newValue) throws DatabaseException {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "";
			boolean status=checkUnitNo(unitNo);
			if (!status) {
				throw new TopicNotFoundException("Topic not found,Enter the valid id!");
			}
			query = "UPDATE topic SET " + userOption + "=? Where UnitNo=?";
			pst = con.prepareStatement(query);
			pst.setString(1, newValue);
			pst.setString(2, unitNo);
			count = pst.executeUpdate();
		} catch (SQLException | TopicNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}
	public int updateTopicsStatusDetails(String unitNo,String userOption,Boolean newStatus) throws DatabaseException {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "";
			boolean status=checkUnitNo(unitNo);
			if (!status) {
				throw new TopicNotFoundException("Topic not found,Enter the valid id!");
			}
			query = "UPDATE topic SET " + userOption + "=? Where UnitNo=?";
			pst = con.prepareStatement(query);
			pst.setBoolean(1, newStatus);
			pst.setString(2, unitNo);
			count = pst.executeUpdate();
		} catch (SQLException | TopicNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public int deleteTopicsDetails(String unitNo) throws DatabaseException {
		int count=0;
		try {
			boolean status=checkUnitNo(unitNo);
			if (!status) {
				throw new TopicNotFoundException("Topic not found,Enter the valid id!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "DELETE FROM topic WHERE UnitNo=?";
			pst = con.prepareStatement(query);
			pst.setString(1, unitNo);
			count = pst.executeUpdate();
		} catch (SQLException | TopicNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public List<Topic> getTopicsDetails() {
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "SELECT * FROM topic";
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				topicsList.add(new Topic(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getBoolean(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return topicsList;
	}

	public List<Topic> getParticularTopicDetails(String unitNo) throws DatabaseException {
		List<Topic> topicsParticularList = new ArrayList<>();
		try {
			boolean status=checkUnitNo(unitNo);
			if (!status) {
				throw new TopicNotFoundException("Topic not found,Enter the valid id!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "SELECT * FROM topic WHERE UnitNo=?";
			pst = con.prepareStatement(query);
			pst.setString(1, unitNo);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				topicsParticularList.add(new Topic(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getBoolean(5), rs.getString(6)));
			}
		} catch (SQLException | TopicNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return topicsParticularList;
	}

}
