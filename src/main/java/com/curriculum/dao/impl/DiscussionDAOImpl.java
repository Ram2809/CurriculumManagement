package com.curriculum.dao.impl;

//import static com.revature.curriculummanagement.dao.TopicsDAOImpl.getUnitNo;
//import static com.revature.curriculummanagement.dao.TopicsDAOImpl.topicsIdList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.DiscussionDAO;
import com.curriculum.exception.DatabaseException;
import com.curriculum.exception.QuestionNotFoundException;
import com.curriculum.exception.StudentNotFoundException;
import com.curriculum.model.Discussion;
import com.curriculum.util.DBUtil;

public class DiscussionDAOImpl implements DiscussionDAO {
	static List<Discussion> discussionList = new ArrayList<>();
	static Logger logger = Logger.getLogger("DiscussionDAOImpl.class");

	public boolean checkQuestionNo(Long questionNo) {
		boolean status = false;
		try (Connection con = DBUtil.getConnection();) {
			Statement statement = null;
			String query = "SELECT * FROM discussion where questionNo=" + questionNo;
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			status = rs.next();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return status;
	}

	public int addDiscussionDetails(Discussion discussion) {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "INSERT INTO discussion(Question,Answer,UnitNo,Date) VALUES(?,?,?,?)";
			pst = con.prepareStatement(query);
			pst.setString(1, discussion.getQuestion());
			pst.setString(2, discussion.getAnswer());
			pst.setString(3, discussion.getUnitNo());
			pst.setString(4, discussion.getDate());
			count = pst.executeUpdate();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return count;

	}

	public int updateDiscussionDetails(Long questionNo,String userOption,String newValue) throws DatabaseException {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "";
			boolean status=checkQuestionNo(questionNo);
			if (!status) {
				throw new QuestionNotFoundException("Question not found,Enter the valid id!");
			}
			query = "UPDATE discussion SET " + userOption + "=? Where QuestionNo=?";
			pst = con.prepareStatement(query);
			pst.setString(1, newValue);
			pst.setLong(2, questionNo);
			count = pst.executeUpdate();
		} catch (SQLException | QuestionNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public int deleteDiscussionDetails(Long questionNo) throws DatabaseException {
		int count=0;
		try {
			boolean status=checkQuestionNo(questionNo);
			if (!status) {
				throw new QuestionNotFoundException("Question not found,Enter the valid id!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "DELETE FROM discussion WHERE QuestionNo=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, questionNo);
			count = pst.executeUpdate();
		} catch (SQLException | QuestionNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public List<Discussion> getDiscussionDetails() {
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "SELECT * FROM discussion";
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				discussionList.add(new Discussion(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5)));
			}
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return discussionList;
	}

	public List<Discussion> getParticularDiscussionDetails(Long questionNo) throws DatabaseException {
		List<Discussion> discussionParticularList = new ArrayList<>();
		try {
			boolean status=checkQuestionNo(questionNo);
			if (!status) {
				throw new QuestionNotFoundException("Question not found,Enter the valid id!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "SELECT * FROM discussion WHERE QuestionNo=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, questionNo);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				discussionParticularList.add(new Discussion(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5)));
			}
		} catch (SQLException | QuestionNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return discussionParticularList;
	}

	/*public void getDiscussionStatusByUnit(String unitNo) throws DatabaseException {
		try {
			getUnitNo();
			if (!topicsIdList.contains(unitNo)) {
				throw new TopicNotFoundException("Topic not found,Enter the valid id!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "SELECT class.roomNo,class.standard,class.section,subject.id,subject.name,topics.unitNo,topics.unitName,discussion.questionNo,discussion.question,discussion.answer,discussion.date FROM class JOIN subject ON class.roomNo=subject.classId JOIN topics ON subject.id=topics.subjectId JOIN discussion ON topics.unitNo=discussion.unitNo WHERE topics.unitNo=?";
			pst = con.prepareStatement(query);
			pst.setString(1, unitNo);
			ResultSet rs = pst.executeQuery();
			System.out.println(
					"-------------------------------------------------------------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4)
						+ " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " " + rs.getString(8)
						+ " " + rs.getString(9) + " " + rs.getString(10) + " " + rs.getString(11));
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------");
		} catch (SQLException | TopicNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
	}*/
}
