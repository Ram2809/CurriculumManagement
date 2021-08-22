package com.curriculum.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.controller.TeacherController;
import com.curriculum.exception.ControllerException;
import com.curriculum.exception.InvalidChoiceException;
import com.curriculum.model.Teacher;

public class TeacherApplication {
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static Teacher teacher = new Teacher();
	static TeacherController teacherController = new TeacherController();
	static List<Teacher> teacherList = new ArrayList<>();
	static Logger logger = Logger.getLogger("TeacherApplication.class");

	public static void insertTeacher() {
		int count = 0;
		try {
			System.out.println("Enter the teacher details:");
			System.out.println("Enter the first name:");
			String firstName = bufferedReader.readLine();
			System.out.println("Enter the last name:");
			String lastName = bufferedReader.readLine();
			System.out.println("Enter the dob:");
			String dateOfBirth = bufferedReader.readLine();
			System.out.println("Enter the gender:");
			String gender = bufferedReader.readLine();
			System.out.println("Enter the address:");
			String address = bufferedReader.readLine();
			System.out.println("Enter the qualification:");
			String qualification = bufferedReader.readLine();
			teacher.setFirstName(firstName);
			teacher.setLastName(lastName);
			teacher.setDateOfBirth(dateOfBirth);
			teacher.setGender(gender);
			teacher.setAddress(address);
			teacher.setQualification(qualification);
			count = teacherController.addTeacherDetails(teacher);
		} catch (IOException | NumberFormatException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Inserted!");
	}

	public static void updateTeacher() {
		int count = 0;
		try {
			System.out.println("Enter the id:");
			Long id = Long.parseLong(bufferedReader.readLine());
			System.out.println("1.Update firstname");
			System.out.println("2.Update lastname");
			System.out.println("3.Update date of birth");
			System.out.println("4.Update gender");
			System.out.println("5.Update address");
			System.out.println("6.Update qualification");
			System.out.println("Enter your choice:");
			Integer userChoice = Integer.parseInt(bufferedReader.readLine());
			switch (userChoice) {
			case 1:
				System.out.println("Enter the new first name:");
				String newFirstName = bufferedReader.readLine();
				count = teacherController.updateTeacherDetails(id, "FirstName", newFirstName);
				break;
			case 2:
				System.out.println("Enter the new last name:");
				String newLastName = bufferedReader.readLine();
				count = teacherController.updateTeacherDetails(id, "LastName", newLastName);
				break;
			case 3:
				System.out.println("Enter the new dob:");
				String newDOB = bufferedReader.readLine();
				count = teacherController.updateTeacherDetails(id, "DateOfBirth", newDOB);
				break;
			case 4:
				System.out.println("Enter the new gender:");
				String newGender = bufferedReader.readLine();
				count = teacherController.updateTeacherDetails(id, "Gender", newGender);
				break;
			case 5:
				System.out.println("Enter the new address:");
				String newAddress = bufferedReader.readLine();
				count = teacherController.updateTeacherDetails(id, "Address", newAddress);
				break;
			case 6:
				System.out.println("Enter the new qualification:");
				String newQualification = bufferedReader.readLine();
				count = teacherController.updateTeacherDetails(id, "Qualification", newQualification);
				break;
			default:
				throw new InvalidChoiceException("Enter the valid choice!");
			}
		} catch (NumberFormatException | IOException | ControllerException | InvalidChoiceException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Updated!");
	}

	public static void deleteTeacher() {
		int count=0;
		System.out.println("Enter the teacher id:");
		Long id;
		try {
			id = Long.parseLong(bufferedReader.readLine());
			count=teacherController.deleteTeacherDetails(id);
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count+" "+"Rows Deleted!");
	}

	public static void getTeacher() {
		teacherList = teacherController.getTeacherDetails();
		Iterator<Teacher> teacherIterator = teacherList.iterator();
		System.out
				.println("-------------------------------------------------------------------------------------------");
		while (teacherIterator.hasNext()) {
			System.out.println(teacherIterator.next());
		}
		System.out
				.println("-------------------------------------------------------------------------------------------");
	}

	public static void getParticularTeacher() {
		try {
			System.out.println("Enter the id:");
			Long id = Long.parseLong(bufferedReader.readLine());
			List<Teacher> teacherParicularList = new ArrayList<Teacher>();
			teacherParicularList = teacherController.getParticularTeacherDetails(id);
			Iterator<Teacher> teacherParticularIterator = teacherParicularList.iterator();
			System.out.println(
					"-------------------------------------------------------------------------------------------");
			while (teacherParticularIterator.hasNext()) {
				System.out.println(teacherParticularIterator.next());
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------");
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	/*public static void getTeacherByClassRoom() {
		try {
			System.out.println("Enter the class room no:");
			Long classRoomNo = Long.parseLong(bufferedReader.readLine());
			teacherController.getTeacherDetailsByClassRoom(classRoomNo);
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}*/

	/*public static void getTeacherBySubject() {
		try {
			System.out.println("Enter the subject name:");
			String subjectName = bufferedReader.readLine();
			teacherController.getTeacherDetailsBySubjectName(subjectName);
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("Teacher Application\n");
			System.out.println("1.Insert");
			System.out.println("2.Update");
			System.out.println("3.Delete");
			System.out.println("4.Retrieval");
			System.out.println("5.Paricular teacher data");
			System.out.println("6.Get teachers for particular classroom");
			System.out.println("7.Get teachers for particular subject");
			System.out.println("8.Exit");
			System.out.println("Enter the choice:");
			try {
				Integer userChoice = Integer.parseInt(bufferedReader.readLine());
				switch (userChoice) {
				case 1:
					insertTeacher();
					break;
				case 2:
					updateTeacher();
					break;
				case 3:
					deleteTeacher();
					break;
				case 4:
					getTeacher();
					break;
				case 5:
					getParticularTeacher();
					break;
				/*case 6:
					getTeacherByClassRoom();
					break;
				case 7:
					getTeacherBySubject();
					break;*/
				case 8:
					System.exit(0);
					break;
				default:
					throw new InvalidChoiceException("Enter the valid choice!");
				}
			} catch (InvalidChoiceException | IOException | NumberFormatException e) {
				logger.warn(e.getMessage());
			}
		}
	}
}
