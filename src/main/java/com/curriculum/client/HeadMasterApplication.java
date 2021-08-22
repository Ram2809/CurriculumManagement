package com.curriculum.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.controller.HeadMasterController;
import com.curriculum.exception.ControllerException;
import com.curriculum.exception.InvalidChoiceException;
import com.curriculum.model.HeadMaster;

public class HeadMasterApplication {
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static HeadMaster headMaster = new HeadMaster();
	static HeadMasterController headMasterController = new HeadMasterController();
	static List<HeadMaster> headMasterList = new ArrayList<>();
	static Logger logger = Logger.getLogger("HeadMasterApplication.class");

	public static void insertHeadMaster() {
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
			headMaster.setFirstName(firstName);
			headMaster.setLastName(lastName);
			headMaster.setDateOfBirth(dateOfBirth);
			headMaster.setGender(gender);
			headMaster.setAddress(address);
			headMaster.setQualification(qualification);
			count = headMasterController.addHeadMasterDetails(headMaster);
		} catch (IOException | NumberFormatException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Inserted!");
	}

	public static void updateHeadMaster() {
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
				count = headMasterController.updateHeadMasterDetails(id, "FirstName", newFirstName);
				break;
			case 2:
				System.out.println("Enter the new last name:");
				String newLastName = bufferedReader.readLine();
				count = headMasterController.updateHeadMasterDetails(id, "LastName", newLastName);
				break;
			case 3:
				System.out.println("Enter the new dob:");
				String newDOB = bufferedReader.readLine();
				count = headMasterController.updateHeadMasterDetails(id, "DateOfBirth", newDOB);
				break;
			case 4:
				System.out.println("Enter the new gender:");
				String newGender = bufferedReader.readLine();
				count = headMasterController.updateHeadMasterDetails(id, "Gender", newGender);
				break;
			case 5:
				System.out.println("Enter the new address:");
				String newAddress = bufferedReader.readLine();
				count = headMasterController.updateHeadMasterDetails(id, "Address", newAddress);
				break;
			case 6:
				System.out.println("Enter the new qualification:");
				String newQualification = bufferedReader.readLine();
				count = headMasterController.updateHeadMasterDetails(id, "Qualification", newQualification);
				break;
			default:
				throw new InvalidChoiceException("Enter the valid choice!");
			}
		} catch (NumberFormatException | IOException | InvalidChoiceException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Updated!");
	}

	public static void deleteHeadMaster() {
		int count = 0;
		try {
			System.out.println("Enter the id:");
			Long id = Long.parseLong(bufferedReader.readLine());
			count = headMasterController.deleteHeadMasterDetails(id);
		} catch (NumberFormatException | IOException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Deleted!");
	}

	public static void getHeadMaster() {
		headMasterList = headMasterController.getHeadMasterDetails();
		Iterator<HeadMaster> headMasterIterator = headMasterList.iterator();
		System.out
				.println("-------------------------------------------------------------------------------------------");
		while (headMasterIterator.hasNext()) {
			System.out.println(headMasterIterator.next());
		}
		System.out
				.println("-------------------------------------------------------------------------------------------");
	}

	public static void performHMCRUD() {
		System.out.println("Head Master Application");
		while (true) {
			System.out.println("1.Insert");
			System.out.println("2.Update");
			System.out.println("3.Delete");
			System.out.println("4.Retrieval");
			System.out.println("5.Exit");
			System.out.println("Enter your choice:");
			try {
				Integer userChoice = Integer.parseInt(bufferedReader.readLine());
				switch (userChoice) {
				case 1:
					insertHeadMaster();
					break;
				case 2:
					updateHeadMaster();
					break;
				case 3:
					deleteHeadMaster();
					break;
				case 4:
					getHeadMaster();
					break;
				case 5:
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

	public static void main(String[] args) {
		System.out.println("Welcome to Head Master Section\n");
		while (true) {
			System.out.println("1.Head Master section");
			System.out.println("2.Student Section");
			System.out.println("3.Teacher Section");
			System.out.println("4.Class Section");
			System.out.println("5.Subject Section");
			System.out.println("6.Teacher Assign Section");
			System.out.println("7.Time Table Section");
			System.out.println("8.Discussion Section");
			System.out.println("9.Exit");
			System.out.println("Enter your choice:");
			try {
				int userChoice = Integer.parseInt(bufferedReader.readLine());
				switch (userChoice) {
				case 1:
					performHMCRUD();
					break;
				/*
				 * case 2: StudentApplication.main(args); break; case 3:
				 * TeacherApplication.main(args); break; case 4: ClassApplication.main(args);
				 * break; case 5: SubjectApplication.main(args); break; case 6:
				 * TeacherAssign.main(args); break; case 7: TimeTableApplication.main(args);
				 * break; case 8: DiscussionApplication.main(args); break;
				 */
				case 9:
					logger.info("Exits from head master section");
					System.exit(0);
					break;
				default:
					throw new InvalidChoiceException("Enter the valid choice!");
				}
			} catch (NumberFormatException | IOException | InvalidChoiceException e) {
				logger.info(e.getMessage());
			}
		}
	}
}
