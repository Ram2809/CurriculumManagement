package com.curriculum.dao.impl;

import static com.curriculum.dao.impl.ClassDAOImpl.checkClassId;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.dao.StudentDAO;
import com.curriculum.exception.ClassRoomNotFoundException;
import com.curriculum.exception.DatabaseException;
import com.curriculum.exception.StudentNotFoundException;
import com.curriculum.model.Student;
import com.curriculum.util.DBUtil;

public class StudentDAOImpl implements StudentDAO {
	static List<Student> studentList = new ArrayList<>();
	static Logger logger = Logger.getLogger("StudentDAOImpl.class");

	public boolean checkStudentId(Long rollNo) {
		boolean status = false;
		try (Connection con = DBUtil.getConnection();) {
			Statement statement = null;
			String query = "SELECT * FROM student where rollNo=" + rollNo;
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			status = rs.next();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return status;
	}

	public int addStudentDetails(Student student) {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "INSERT INTO student(FirstName,LastName,DateOfBirth,Gender,Address,ClassRoomNo) VALUES(?,?,?,?,?,?)";
			pst = con.prepareStatement(query);
			pst.setString(1, student.getFirstName());
			pst.setString(2, student.getLastName());
			pst.setString(3, student.getDateOfBirth());
			pst.setString(4, student.getGender());
			pst.setString(5, student.getAddress());
			pst.setLong(6, student.getRoomNo());
			count = pst.executeUpdate();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return count;
	}

	public int updateStudentDetails(Long id, String userOption, String newValue) throws DatabaseException {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "";
			boolean status = checkStudentId(id);
			if (!status) {
				throw new StudentNotFoundException("Student not found,Enter the valid id!");
			}
			query = "UPDATE student SET " + userOption + "=? Where RollNo=?";
			pst = con.prepareStatement(query);
			pst.setString(1, newValue);
			pst.setLong(2, id);
			count = pst.executeUpdate();
		} catch (SQLException | StudentNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public int updateStudentDetailsByRoomNo(Long id, String userOption, Long newValue) throws DatabaseException {
		int count = 0;
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "";
			boolean status = checkStudentId(id);
			if (!status) {
				throw new StudentNotFoundException("Student not found,Enter the valid id!");
			}
			boolean classCheckstatus = checkClassId(newValue);
			if (!status) {
				throw new ClassRoomNotFoundException("Class Room No not found, Enter the valid room no!");
			}
			query = "UPDATE student SET " + userOption + "=? Where RollNo=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, newValue);
			pst.setLong(2, id);
			count = pst.executeUpdate();
		} catch (SQLException | StudentNotFoundException | ClassRoomNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public int deleteStudentDetails(Long id) throws DatabaseException {
		int count = 0;
		try {
			boolean checkStudent = checkStudentId(id);
			if (!checkStudent) {
				throw new StudentNotFoundException("Student not found,Enter the valid id!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "DELETE FROM student WHERE RollNo=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, id);
			count = pst.executeUpdate();
		} catch (SQLException | StudentNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return count;
	}

	public List<Student> getStudentDetails() {
		try (Connection con = DBUtil.getConnection();) {
			PreparedStatement pst = null;
			String query = "SELECT * FROM student";
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				studentList.add(new Student(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getLong(7)));
			}
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}
		return studentList;
	}

	public List<Student> getParticularStudentDetails(Long id) throws DatabaseException {
		List<Student> studentParticularList = new ArrayList<>();
		try {
			boolean status = checkStudentId(id);
			if (!status) {
				throw new StudentNotFoundException("Student not found,Enter the valid id!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "SELECT * FROM student WHERE RollNo=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				studentParticularList.add(new Student(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getLong(7)));
			}
		} catch (SQLException | StudentNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return studentParticularList;
	}

	public void getStudentDetailsByClassRoom(Long roomNo) throws DatabaseException {
		try {
			boolean status = checkClassId(roomNo);
			if (!status) {
				throw new ClassRoomNotFoundException("Class Room No not found, Enter the valid room no!");
			}
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
			String query = "SELECT student.RollNo,student.FirstName,student.LastName,student.DateOfBirth,student.Address,class.Standard,class.Section FROM student JOIN class ON student.ClassRoomNo=class.RoomNo WHERE ClassRoomNo=?";
			pst = con.prepareStatement(query);
			pst.setLong(1, roomNo);
			ResultSet rs = pst.executeQuery();
			System.out.println(
					"-------------------------------------------------------------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getLong(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
						+ " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7));
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------");
		} catch (SQLException | ClassRoomNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

}
