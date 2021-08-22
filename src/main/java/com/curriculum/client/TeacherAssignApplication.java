package com.curriculum.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.controller.TeacherAssignController;
import com.curriculum.exception.ControllerException;
import com.curriculum.exception.InvalidChoiceException;
import com.curriculum.model.TeacherAssign;

public class TeacherAssignApplication {
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static TeacherAssign teacherAssignDetails = new TeacherAssign();
	static TeacherAssignController teacherAssignController = new TeacherAssignController();
	static List<TeacherAssign> teacherAssignList = new ArrayList<>();
	static Logger logger = Logger.getLogger("TeacherAssignApplication.class");

	public static void insertAssign() {
		int count = 0;
		try {
			System.out.println("Enter the teacher id:");
			Long teacherId = Long.parseLong(bufferedReader.readLine());
			System.out.println("Enter the subject code:");
			String subjectCode = bufferedReader.readLine();
			teacherAssignDetails.setTeacherId(teacherId);
			teacherAssignDetails.setSubjectCode(subjectCode);
			count = teacherAssignController.addTeacherAssignDetails(teacherAssignDetails);
		} catch (IOException | NumberFormatException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Inserted!");
	}

	public static void updateAssign() {
		int count = 0;
		try {
			System.out.println("Enter the assign id:");
			Long assignId = Long.parseLong(bufferedReader.readLine());
			System.out.println("1.Update teacher id");
			System.out.println("2.Update subject code");
			System.out.println("Enter your choice:");
			Integer userChoice = Integer.parseInt(bufferedReader.readLine());
			switch (userChoice) {
			case 1:
				System.out.println("Enter the new teacher id:");
				Long newTeacherId = Long.parseLong(bufferedReader.readLine());
				count = teacherAssignController.updateTeacherAssignDetails(assignId, "TeacherId", newTeacherId);
				break;
			case 2:
				System.out.println("Enter the new subject code:");
				String newSubjectCode = bufferedReader.readLine();
				count = teacherAssignController.updateTeacherAssignDetailsBySubjectCode(assignId, "subjectCode", newSubjectCode);
				break;
			default:
				throw new InvalidChoiceException("Enter the valid choice!");
			}
		} catch (NumberFormatException | IOException | ControllerException | InvalidChoiceException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Updated!");
	}

	public static void deleteAssign() {
		int count = 0;
		try {
			System.out.println("Enter the Assign id:");
			Long assignId = Long.parseLong(bufferedReader.readLine());
			count = teacherAssignController.deleteTeacherAssignDetails(assignId);
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Deleted!");
	}

	public static void getAssign() {
		teacherAssignList = teacherAssignController.getTeacherAssignDetails();
		Iterator<TeacherAssign> teacherAssignIterator = teacherAssignList.iterator();
		while (teacherAssignIterator.hasNext()) {
			System.out.println(teacherAssignIterator.next());
		}
	}

	/*public static void getParticularAssign() {
		try {
			System.out.println("Enter the assign id:");
			Long assignId = Long.parseLong(bufferedReader.readLine());
			List<TeacherAssign> teacherAssignParicularList = new ArrayList<TeacherAssign>();
			teacherAssignParicularList = teacherAssignController.getParticularTeacherAssignDetails(assignId);
			Iterator<TeacherAssign> teacherAssignParticularIterator = teacherAssignParicularList.iterator();
			while (teacherAssignParticularIterator.hasNext()) {
				System.out.println(teacherAssignParticularIterator.next());
			}
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("Teacher Assign Application\n");
			System.out.println("1.Insert");
			System.out.println("2.Update");
			System.out.println("3.Delete");
			System.out.println("4.Retrieval");
			System.out.println("5.Paricular teacher assign data");
			System.out.println("6.Exit");
			System.out.println("Enter the choice:");
			try {
				Integer userChoice = Integer.parseInt(bufferedReader.readLine());
				switch (userChoice) {
				case 1:
					insertAssign();
					break;
				case 2:
					updateAssign();
					break;
				case 3:
					deleteAssign();
					break;
				case 4:
					getAssign();
					break;
				/*case 5:
					getParticularAssign();
					break;*/
				case 6:
					System.exit(0);
					break;
				default:
					throw new InvalidChoiceException("Enter the valid choice!");
				}
			} catch (IOException | NumberFormatException | InvalidChoiceException e) {
				logger.warn(e.getMessage());
			}
		}

	}
}
