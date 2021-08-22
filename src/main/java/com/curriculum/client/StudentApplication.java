package com.curriculum.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.controller.StudentController;
import com.curriculum.exception.InvalidChoiceException;
import com.curriculum.model.Student;
import com.curriculum.exception.ControllerException;

public class StudentApplication {
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static Student student = new Student();
	static StudentController studentController = new StudentController();
	static List<Student> studentList = new ArrayList<>();
	static Logger logger = Logger.getLogger("StudentApplication.class");

	public static void insertStudent() {
		int count = 0;
		try {
			System.out.println("Enter the student details:");
			System.out.println("Enter the first name:");
			String firstName = bufferedReader.readLine();
			System.out.println("Enter the last name:");
			String lastName = bufferedReader.readLine();
			System.out.println("Enter the dob:");
			String dateOfBirth = bufferedReader.readLine();
			System.out.println("Enter the gender:");
			String gender = bufferedReader.readLine();
			System.out.println("Enter the student address:");
			String address = bufferedReader.readLine();
			System.out.println("Enter the class room no:");
			Long roomNo = Long.parseLong(bufferedReader.readLine());

			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setDateOfBirth(dateOfBirth);
			student.setGender(gender);
			student.setAddress(address);
			student.setRoomNo(roomNo);
			count = studentController.addStudentDetails(student);
		} catch (NumberFormatException | IOException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Inserted!");
	}

	public static void updateStudent() {
		int count = 0;
		try {
			System.out.println("Enter the id:");
			Long id = Long.parseLong(bufferedReader.readLine());
			System.out.println("1.Update firstname");
			System.out.println("2.Update lastname");
			System.out.println("3.Update date of birth");
			System.out.println("4.Update gender");
			System.out.println("5.Update address");
			System.out.println("6.Update class room no");
			System.out.println("Enter your choice:");
			Integer userChoice = Integer.parseInt(bufferedReader.readLine());
			switch (userChoice) {
			case 1:
				System.out.println("Enter the new first name:");
				String newFirstName = bufferedReader.readLine();
				count = studentController.updateStudentDetails(id, "FirstName", newFirstName);
				break;
			case 2:
				System.out.println("Enter the new last name:");
				String newLastName = bufferedReader.readLine();
				count = studentController.updateStudentDetails(id, "LastName", newLastName);
				break;
			case 3:
				System.out.println("Enter the new dob:");
				String newDOB = bufferedReader.readLine();
				count = studentController.updateStudentDetails(id, "DateOfBirth", newDOB);
				break;
			case 4:
				System.out.println("Enter the new gender:");
				String newGender = bufferedReader.readLine();
				count = studentController.updateStudentDetails(id, "Gender", newGender);
				break;
			case 5:
				System.out.println("Enter the new address:");
				String newAddress = bufferedReader.readLine();
				count = studentController.updateStudentDetails(id, "Address", newAddress);
				break;
			case 6:
				System.out.println("Enter the new class room no:");
				Long newRoomNo = Long.parseLong(bufferedReader.readLine());
				count = studentController.updateStudentDetailsByRoomNo(id, "ClassRoomNo", newRoomNo);
				break;
			default:
				throw new InvalidChoiceException("Enter the valid choice!");
			}
		} catch (NumberFormatException | IOException | ControllerException | InvalidChoiceException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Updated!");
	}

	public static void deleteStudent() {
		int count = 0;
		try {
			System.out.println("Enter the student id:");
			Long id = Long.parseLong(bufferedReader.readLine());
			count = studentController.deleteStudentDetails(id);
		} catch (NumberFormatException | IOException | ControllerException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Deleted!");
	}

	public static void getStudent() {
		studentList = studentController.getStudentDetails();
		Iterator<Student> studentIterator = studentList.iterator();
		System.out
				.println("-------------------------------------------------------------------------------------------");
		while (studentIterator.hasNext()) {
			System.out.println(studentIterator.next());
		}
		System.out
				.println("-------------------------------------------------------------------------------------------");
	}

	public static void getParticularStudent() {
		try {
			System.out.println("Enter the id:");
			Long id = Long.parseLong(bufferedReader.readLine());
			List<Student> studentParicularList = new ArrayList<Student>();
			studentParicularList = studentController.getParticularStudentDetails(id);
			Iterator<Student> studentParticularIterator = studentParicularList.iterator();
			System.out.println(
					"-------------------------------------------------------------------------------------------");
			while (studentParticularIterator.hasNext()) {
				System.out.println(studentParticularIterator.next());
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------");
		} catch (NumberFormatException | IOException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void getStudentByClassRoom() {
		try {
			System.out.println("Enter the class room no:");
			Long roomNo = Long.parseLong(bufferedReader.readLine());
			studentController.getStudentDetailsByClassRoom(roomNo);
		} catch (NumberFormatException | IOException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("Student Application\n");
			System.out.println("1.Insert");
			System.out.println("2.Update");
			System.out.println("3.Delete");
			System.out.println("4.Retrieval");
			System.out.println("5.Paricular student data");
			System.out.println("6.Get students by classroom");
			System.out.println("7.Exit");
			System.out.println("Enter the choice:");
			try {
				Integer userChoice = Integer.parseInt(bufferedReader.readLine());
				switch (userChoice) {
				case 1:
					insertStudent();
					break;
				case 2:
					updateStudent();
					break;
				case 3:
					deleteStudent();
					break;
				case 4:
					getStudent();
					break;
				case 5:
					getParticularStudent();
					break;
				case 6:
					getStudentByClassRoom();
					break;
				case 7:
					System.exit(0);
					break;
				default:
					throw new InvalidChoiceException("Enter the valid choice!");
				}
			} catch (NumberFormatException | IOException | InvalidChoiceException e) {
				logger.warn(e.getMessage());
			}
		}
	}
}
