package com.curriculum.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.controller.SubjectController;
import com.curriculum.exception.ControllerException;
import com.curriculum.exception.InvalidChoiceException;
import com.curriculum.model.Subject;

public class SubjectApplication {
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static Subject subject = new Subject();
	static SubjectController subjectController = new SubjectController();
	static List<Subject> subjectList = new ArrayList<>();
	static Logger logger = Logger.getLogger("SubjectApplication.class");

	public static void insertSubject() {
		int count=0;
		try {
			System.out.println("Enter the subject details:");
			System.out.println("Enter the subject code:");
			String code = bufferedReader.readLine();
			System.out.println("Enter the subject name:");
			String subjectName = bufferedReader.readLine();
			System.out.println("Enter the class room no:");
			Long classId = Long.parseLong(bufferedReader.readLine());
			subject.setCode(code);
			subject.setName(subjectName);
			subject.setRoomNo(classId);
			count=subjectController.addSubjectDetails(subject);
		} catch (IOException | NumberFormatException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count+" "+"Rows Inserted!");
	}

	public static void updateSubject() {
		int count = 0;
		try {
			System.out.println("Enter the subject code:");
			String subjectCode = bufferedReader.readLine();
			System.out.println("1.Update name");
			System.out.println("2.Update class room no");
			System.out.println("Enter your choice:");
			Integer userChoice = Integer.parseInt(bufferedReader.readLine());
			switch (userChoice) {
			case 1:
				System.out.println("Enter the new subject name:");
				String newSubjectName = bufferedReader.readLine();
				count = subjectController.updateSubjectDetails(subjectCode, "name", newSubjectName);
				break;
			case 2:
				System.out.println("Enter the new class room no:");
				Long newRoomNo = Long.parseLong(bufferedReader.readLine());
				count = subjectController.updateStudentDetailsByRoomNo(subjectCode, "RoomNo", newRoomNo);
				break;
			default:
				throw new InvalidChoiceException("Enter the valid choice!");
			}
		} catch (NumberFormatException | IOException | ControllerException | InvalidChoiceException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Updated!");
	}

	public static void deleteSubject() {
		int count=0;
		try {
			System.out.println("Enter the subject code:");
			String subjectCode = bufferedReader.readLine();
			count=subjectController.deleteSubjectDetails(subjectCode);
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count+" "+"Rows Deleted!");
	}

	public static void getSubject() {
		subjectList = subjectController.getSubjectDetails();
		Iterator<Subject> subjectIterator = subjectList.iterator();
		System.out
				.println("-------------------------------------------------------------------------------------------");
		while (subjectIterator.hasNext()) {
			System.out.println(subjectIterator.next());
		}
		System.out
				.println("-------------------------------------------------------------------------------------------");
	}

	public static void getParticularSubject() throws ControllerException {
		try {
			System.out.println("Enter the subject id:");
			String subjectCode = bufferedReader.readLine();
			List<Subject> subjectParicularList = new ArrayList<Subject>();
			subjectParicularList = subjectController.getParticularSubjectDetails(subjectCode);
			Iterator<Subject> subjectParticularIterator = subjectParicularList.iterator();
			System.out.println(
					"-------------------------------------------------------------------------------------------");
			while (subjectParticularIterator.hasNext()) {
				System.out.println(subjectParticularIterator.next());
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------");
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	/*public static void subjectStatus() {
		try {
			System.out.println("Enter the subject id:");
			Integer subjectId = Integer.parseInt(bufferedReader.readLine());
			subjectController.getSubjectStatus(subjectId);
		} catch (NumberFormatException | IOException | ControllerException e) {
			logger.warn(e.getMessage());
		}

	}*/

	public static void main(String[] args) {
		while (true) {
			System.out.println("Subject Application\n");
			System.out.println("1.Insert");
			System.out.println("2.Update");
			System.out.println("3.Delete");
			System.out.println("4.Retrieval");
			System.out.println("5.Particular subject data");
			System.out.println("6.Get subject status");
			System.out.println("7.Exit");
			System.out.println("Enter the choice:");
			try {
				Integer userChoice = Integer.parseInt(bufferedReader.readLine());
				switch (userChoice) {
				case 1:
					insertSubject();
					break;
				case 2:
					updateSubject();
					break;
				case 3:
					deleteSubject();
					break;
				case 4:
					getSubject();
					break;
				case 5:
					getParticularSubject();
					break;
				/*
				 * case 6: subjectStatus(); break;
				 */
				case 7:
					logger.info("Exits from subject application");
					System.exit(0);
					break;
				default:
					throw new InvalidChoiceException("Enter the valid choice!");
				}
			} catch (IOException | NumberFormatException | ControllerException | InvalidChoiceException e) {
				logger.warn(e.getMessage());
			}
		}
	}
}
