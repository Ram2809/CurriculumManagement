package com.curriculum.dao.impl;

import static com.curriculum.dao.impl.ClassDAOImpl.checkClassId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.SubjectDAO;
import com.curriculum.exception.ClassRoomNotFoundException;
import com.curriculum.exception.DatabaseException;
import com.curriculum.exception.SubjectNotFoundException;
import com.curriculum.model.Subject;
import com.curriculum.util.DBUtil;

public class SubjectDAOImpl implements SubjectDAO{
	static List<Subject> subjectList = new ArrayList<>();
	static Logger logger = Logger.getLogger("SubjectDAOImpl.class");

	public boolean checkSubjectId(String subjectCode) {
		boolean status = false;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement preparedStatement = null;
			String query = "SELECT * FROM subject where code=?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, subjectCode);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return status;
	}

	public int addSubjectDetails(Subject subject) {
		int count=0;
		try (Connection con = DBUtil.getConnection();) {
			String query = "INSERT INTO subject(code,name,RoomNo) VALUES(?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, subject.getCode());
			pst.setString(2, subject.getName());
			pst.setLong(3, subject.getRoomNo());
			count = pst.executeUpdate();
			System.out.println(count + " " + "Rows Inserted!");
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return count;
	}

	public int updateSubjectDetails(String subjectCode,String userOption,String newValue) throws DatabaseException {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "";
			boolean status = checkSubjectId(subjectCode);
			if (!status) {
				throw new SubjectNotFoundException("Subject not found,Enter the valid id!");
			}
			query = "UPDATE subject SET " + userOption + "=? Where code=?";
			pst = con.prepareStatement(query);
			pst.setString(1, newValue);
			pst.setString(2, subjectCode);
			count = pst.executeUpdate();
		} catch (SQLException | SubjectNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}
	public int updateSubjectDetailsByRoomNo(String subjectCode, String userOption, Long newValue) throws DatabaseException {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "";
			boolean status = checkSubjectId(subjectCode);
			if (!status) {
				throw new SubjectNotFoundException("Subject not found,Enter the valid id!");
			}
			boolean classCheckstatus = checkClassId(newValue);
			if (!classCheckstatus) {
				throw new ClassRoomNotFoundException("Class Room No not found, Enter the valid room no!");
			}
			query = "UPDATE subject SET " + userOption + "=? Where code=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, newValue);
			pst.setString(2, subjectCode);
			count = pst.executeUpdate();
		} catch (SQLException | SubjectNotFoundException | ClassRoomNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public int deleteSubjectDetails(String subjectCode) throws DatabaseException {
		int count=0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			boolean status=checkSubjectId(subjectCode);
			if (!status) {
				throw new SubjectNotFoundException("Subject not found,Enter the valid id!");
			}
			String query = "DELETE FROM subject WHERE code=?";
			pst = con.prepareStatement(query);
			pst.setString(1, subjectCode);
			count = pst.executeUpdate();
		} catch (SQLException | SubjectNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public List<Subject> getSubjectDetails() {
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "SELECT * FROM subject";
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				subjectList.add(new Subject(rs.getLong(1), rs.getString(2), rs.getString(3),rs.getLong(4)));
			}
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return subjectList;
	}

	public List<Subject> getParticularSubjectDetails(String subjectCode) throws DatabaseException {
		List<Subject> subjectParticularList = new ArrayList<>();
		try {
			boolean status=checkSubjectId(subjectCode);
			if (!status) {
				throw new SubjectNotFoundException("Subject not found,Enter the valid id!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "SELECT * FROM subject WHERE code=?";
			pst = con.prepareStatement(query);
			pst.setString(1, subjectCode);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				subjectParticularList.add(new Subject(rs.getLong(1), rs.getString(2), rs.getString(3),rs.getLong(4)));
			}
		} catch (SQLException | SubjectNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return subjectParticularList;
	}

	/*public void getSubjectStatus(Integer subjectId) throws DatabaseException {
		try {
			getSubjectId();
			if (!subjectIdList.contains(subjectId)) {
				throw new SubjectNotFoundException("Subject not found,Enter the valid id!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "SELECT class.RoomNo,class.Standard,class.Section,subject.Id,subject.Name,teacherdetails.teacherId,teacher.name,topics.unitNo,topics.unitName,topics.beginDate,discussion.date AS completedDate,topics.status FROM class JOIN subject ON class.RoomNo=subject.classId JOIN topics ON subject.Id=topics.subjectId JOIN teacherdetails ON topics.subjectId=teacherdetails.subjectId JOIN teacher ON teacherdetails.teacherId=teacher.id JOIN discussion ON topics.unitNo=discussion.unitNo WHERE subject.Id=?";
			pst = con.prepareStatement(query);
			pst.setInt(1, subjectId);
			ResultSet rs = pst.executeQuery();
			System.out.println(
					"-------------------------------------------------------------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4)
						+ " " + rs.getString(5) + " " + rs.getInt(6) + " " + rs.getString(7) + " " + rs.getString(8)
						+ " " + rs.getString(9) + " " + rs.getString(10) + " " + rs.getString(11) + " "
						+ rs.getBoolean(12));
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------");
		} catch (SQLException | SubjectNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}

	}*/

}
