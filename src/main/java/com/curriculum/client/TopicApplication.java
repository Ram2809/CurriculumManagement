package com.curriculum.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.controller.TopicController;
import com.curriculum.model.Topic;
import com.curriculum.exception.ControllerException;
import com.curriculum.exception.InvalidChoiceException;

public class TopicApplication {
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static Topic topics = new Topic();
	static TopicController topicsController = new TopicController();
	static List<Topic> topicsList = new ArrayList<>();
	static Logger logger = Logger.getLogger("TopicApplication.class");

	public static void insertTopics() {
		int count = 0;
		try {
			System.out.println("Enter the topics details:");
			System.out.println("Enter the unit no:");
			String unitNo = bufferedReader.readLine();
			System.out.println("Enter the unit name:");
			String unitName = bufferedReader.readLine();
			System.out.println("Enter the starting date:");
			String startingDate = bufferedReader.readLine();
			System.out.println("Enter the completed status:");
			Boolean status = Boolean.parseBoolean(bufferedReader.readLine());
			System.out.println("Enter the subject code:");
			String subjectCode = bufferedReader.readLine();
			topics.setUnitNo(unitNo);
			topics.setUnitName(unitName);
			topics.setBeginDate(startingDate);
			topics.setStatus(status);
			topics.setSubjectCode(subjectCode);
			count = topicsController.addTopicsDetails(topics);
		} catch (NumberFormatException | IOException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Inserted!");
	}

	public static void updateTopics() {
		int count = 0;
		try {
			System.out.println("Enter the unit no:");
			String unitNo = bufferedReader.readLine();
			System.out.println("1.Update unitname");
			System.out.println("2.Update begin date");
			System.out.println("3.Update completed status");
			System.out.println("4.Update subject code");
			System.out.println("Enter your choice:");
			Integer userChoice = Integer.parseInt(bufferedReader.readLine());
			switch (userChoice) {
			case 1:
				System.out.println("Enter the new unit name:");
				String newUnitName = bufferedReader.readLine();
				count = topicsController.updateTopicsDetails(unitNo, "UnitName", newUnitName);
				break;
			case 2:
				System.out.println("Enter the new begin date:");
				String newBeginDate = bufferedReader.readLine();
				count = topicsController.updateTopicsDetails(unitNo, "beginDate", newBeginDate);
				break;
			case 3:
				System.out.println("Enter the new status:");
				 Boolean newStatus = Boolean.parseBoolean(bufferedReader.readLine());
				count = topicsController.updateTopicsStatusDetails(unitNo, "status", newStatus);
				break;
			case 4:
				System.out.println("Enter the new subject code:");
				String newSubjectCode = bufferedReader.readLine();
				count = topicsController.updateTopicsDetails(unitNo, "SubjectCode", newSubjectCode);
				break;
			default:
				throw new InvalidChoiceException("Enter the valid choice!");
			}
		} catch (NumberFormatException | IOException | ControllerException | InvalidChoiceException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Updated!");
	}

	public static void deleteTopics() {
		int count = 0;
		try {
			System.out.println("Enter the unit no:");
			String unitNo = bufferedReader.readLine();
			count = topicsController.deleteTopicsDetails(unitNo);
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Deleted!");
	}

	public static void getTopics() {
		topicsList = topicsController.getTopicsDetails();
		Iterator<Topic> topicsIterator = topicsList.iterator();
		System.out
				.println("-------------------------------------------------------------------------------------------");
		while (topicsIterator.hasNext()) {
			System.out.println(topicsIterator.next());
		}
		System.out
				.println("-------------------------------------------------------------------------------------------");
	}

	public static void getParticularTopic() {
		try {
			System.out.println("Enter the unit no:");
			String unitNo = bufferedReader.readLine();
			List<Topic> topicsParicularList = new ArrayList<Topic>();
			topicsParicularList = topicsController.getParticularTopicDetails(unitNo);
			Iterator<Topic> topicsParticularIterator = topicsParicularList.iterator();
			System.out.println(
					"-------------------------------------------------------------------------------------------");
			while (topicsParticularIterator.hasNext()) {
				System.out.println(topicsParticularIterator.next());
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------");
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void main(String[] args) {
		while (true) {
			System.out.println("Topic Application\n");
			System.out.println("1.Insert");
			System.out.println("2.Update");
			System.out.println("3.Delete");
			System.out.println("4.Retrieval");
			System.out.println("5.Paricular topic data");
			System.out.println("6.Exit");
			System.out.println("Enter the choice:");
			Integer userChoice;
			try {
				userChoice = Integer.parseInt(bufferedReader.readLine());
				switch (userChoice) {
				case 1:
					insertTopics();
					break;
				case 2:
					updateTopics();
					break;
				case 3:
					deleteTopics();
					break;
				case 4:
					getTopics();
					break;
				case 5:
					getParticularTopic();
					break;
				case 6:
					System.exit(0);
					break;
				default:
					throw new InvalidChoiceException("Enter the valid choice!");
				}
			} catch (IOException | NumberFormatException | InvalidChoiceException e) {
				logger.info(e.getMessage());
			}
		}
	}
}
