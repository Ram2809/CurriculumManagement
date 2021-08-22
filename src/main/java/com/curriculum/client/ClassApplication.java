package com.curriculum.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.controller.ClassController;
import com.curriculum.model.ClassDetail;
import com.curriculum.exception.ControllerException;
import com.curriculum.exception.InvalidChoiceException;

public class ClassApplication {
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static ClassDetail classes = new ClassDetail();
	static ClassController classController = new ClassController();
	static List<ClassDetail> classList = new ArrayList<>();
	static Logger logger = Logger.getLogger("ClassApplication.class");

	public static void insertClass() {
		try {
			int count=0;
			System.out.println("Enter the class details:");
			System.out.println("Enter the standard:");
			String standard = bufferedReader.readLine();
			System.out.println("Enter the section:");
			String section = bufferedReader.readLine();
			classes.setStandard(standard);
			classes.setSection(section);
			count=classController.addClassDetails(classes);
			logger.info(count+" "+"Rows Inserted!");
		} catch (NumberFormatException | IOException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void updateClass() {
		int count=0;
		try {
			System.out.println("Enter the class room no:");
			Long roomNo = Long.parseLong(bufferedReader.readLine());
			System.out.println("1.Update standard");
			System.out.println("2.Update section");
			System.out.println("Enter your choice:");
			Integer userChoice = Integer.parseInt(bufferedReader.readLine());
			switch (userChoice) {
			case 1:
				System.out.println("Enter the new standard:");
				String newStandard = bufferedReader.readLine();
				count=classController.updateClassDetails(roomNo,"Standard",newStandard);
				break;
			case 2:
				System.out.println("Enter the new section:");
				String newSection = bufferedReader.readLine();
				count=classController.updateClassDetails(roomNo,"Section",newSection);
				break;
			default:
				throw new InvalidChoiceException("Enter the valid choice!");
			}
			
		} catch (NumberFormatException | IOException | ControllerException |InvalidChoiceException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count+" "+"Rows updated!");
	}

	public static void deleteClass() {
		int count=0;
		try {
			System.out.println("Enter the room no:");
			Long roomNo = Long.parseLong(bufferedReader.readLine());
			count=classController.deleteClassDetails(roomNo);
		} catch (NumberFormatException | IOException | ControllerException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count+" "+"Rows Deleted");
	}

	public static void getClassDetail() {
		classList = classController.getClassDetails();
		Iterator<ClassDetail> classIterator = classList.iterator();
		System.out
				.println("-------------------------------------------------------------------------------------------");
		while (classIterator.hasNext()) {
			System.out.println(classIterator.next());
		}
		System.out
				.println("-------------------------------------------------------------------------------------------");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.info("In class application");
		while (true) {
			System.out.println("Class Application\n");
			System.out.println("1.Insert");
			System.out.println("2.Update");
			System.out.println("3.Delete");
			System.out.println("4.Retrieval");
			System.out.println("5.Exit");
			System.out.println("Enter the choice:");
			try {
				Integer userChoice = Integer.parseInt(bufferedReader.readLine());
				switch (userChoice) {
				case 1:
					insertClass();
					break;
				case 2:
					updateClass();
					break;
				case 3:
					deleteClass();
					break;
				case 4:
					getClassDetail();
					break;
				case 5:
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
