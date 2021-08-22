package com.curriculum.dao.impl;

import static com.curriculum.dao.impl.ClassDAOImpl.checkClassId;

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

import com.curriculum.dao.TeacherDAO;
import com.curriculum.exception.ClassRoomNotFoundException;
import com.curriculum.exception.DatabaseException;
import com.curriculum.exception.StudentNotFoundException;
import com.curriculum.exception.TeacherNotFoundException;
import com.curriculum.model.Teacher;
import com.curriculum.util.DBUtil;

public class TeacherDAOImpl implements TeacherDAO {
	static List<Teacher> teacherList = new ArrayList<>();
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static Logger logger = Logger.getLogger("TeacherDAOImpl.class");

	public boolean checkTeacherId(Long id) {
		boolean status = false;
		try (Connection con = DBUtil.getConnection();) {
			Statement statement = null;
			String query = "SELECT * FROM teacher where Id=" + id;
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			status = rs.next();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return status;
	}

	public int addTeacherDetails(Teacher teacher) {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "INSERT INTO teacher(FirstName,LastName,DateOfBirth,Gender,Address,Qualification) VALUES(?,?,?,?,?,?)";
			pst = con.prepareStatement(query);
			pst.setString(1, teacher.getFirstName());
			pst.setString(2, teacher.getLastName());
			pst.setString(3, teacher.getDateOfBirth());
			pst.setString(4, teacher.getGender());
			pst.setString(5, teacher.getAddress());
			pst.setString(6, teacher.getQualification());
			count = pst.executeUpdate();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return count;
	}

	public int updateTeacherDetails(Long id,String userOption,String newValue) throws DatabaseException {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "";
			boolean status = checkTeacherId(id);
			if (!status) {
				throw new TeacherNotFoundException("Teacher not found,Enter the valid id!");
			}
			query = "UPDATE teacher SET " + userOption + "=? Where Id=?";
			pst = con.prepareStatement(query);
			pst.setString(1, newValue);
			pst.setLong(2, id);
			count = pst.executeUpdate();
		} catch (SQLException | TeacherNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;

	}

	public int deleteTeacherDetails(Long id) throws DatabaseException {
		int count=0;
		try {
			boolean status = checkTeacherId(id);
			if (!status) {
				throw new TeacherNotFoundException("Teacher not found,Enter the valid id!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "DELETE FROM teacher WHERE Id=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, id);
			count = pst.executeUpdate();
			System.out.println(count + " " + "Rows deleted!");
		} catch (SQLException | TeacherNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;

	}

	public List<Teacher> getTeacherDetails() {
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "SELECT * FROM teacher";
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				teacherList.add(new Teacher(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6),rs.getString(7)));
			}
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return teacherList;
	}

	public List<Teacher> getParticularTeacherDetails(Long id) throws DatabaseException {
		List<Teacher> teacherParticularList = new ArrayList<>();
		try {
			boolean status = checkTeacherId(id);
			if (!status) {
				throw new TeacherNotFoundException("Teacher not found,Enter the valid id!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "SELECT * FROM teacher WHERE Id=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				teacherParticularList.add(new Teacher(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6),rs.getString(7)));
			}
		} catch (SQLException | TeacherNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return teacherParticularList;
	}

	/*public void getTeacherDetailsByClassRoom(Long roomNo) throws DatabaseException {
		try {
			boolean status = checkClassId(roomNo);
			if (!status) {
				throw new ClassRoomNotFoundException("Class Room No not found, Enter the valid room no!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "SELECT teacher.Id AS TeacherId,teacher.FirstName AS TeacherFirstName,teacherdetails.subjectId AS SubjectId,subject.name AS SubjectName from teacher JOIN teacherdetails ON teacher.id=teacherdetails.teacherId JOIN subject ON teacherdetails.subjectId=subject.Id WHERE classRoomNo=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, roomNo);
			ResultSet rs = pst.executeQuery();
			System.out.println(
					"-------------------------------------------------------------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4));
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------");
		} catch (SQLException | ClassRoomNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
	}*/

	/*@Override
	public void getTeacherDetailsBySubjectName(String subjectName) throws DatabaseException {
		// TODO Auto-generated method stub
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "select Id,Name from teacher where Subject=?";
			pst = con.prepareStatement(query);
			pst.setString(1, subjectName);
			ResultSet rs = pst.executeQuery();
			System.out.println(
					"-------------------------------------------------------------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------");
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
	}*/
}
