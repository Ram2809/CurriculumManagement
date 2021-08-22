package com.curriculum.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.controller.DiscussionController;
import com.curriculum.model.Discussion;
import com.curriculum.exception.ControllerException;

public class DiscussionApplication {
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static Discussion discussion = new Discussion();
	static DiscussionController discussionController = new DiscussionController();
	static List<Discussion> discussionList = new ArrayList<>();
	static Logger logger = Logger.getLogger("DiscussionApplication.class");

	public static void insertDiscussion() {
		int count=0;
		try {
			System.out.println("Enter the discussion details:");
			System.out.println("Enter the question:");
			String question = bufferedReader.readLine();
			System.out.println("Enter the answer:");
			String answer = bufferedReader.readLine();
			System.out.println("Enter the unit no:");
			String unitNo = bufferedReader.readLine();
			System.out.println("Enter the discussion date:");
			String date = bufferedReader.readLine();
			discussion.setQuestion(question);
			discussion.setAnswer(answer);
			discussion.setUnitNo(unitNo);
			discussion.setDate(date);
			count=discussionController.addDiscussionDetails(discussion);
			logger.info(count+" "+"Rows Inserted!");
		} catch (NumberFormatException | IOException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void updateDiscussion() {
		try {
			System.out.println("Enter the id:");
			String questionNo = bufferedReader.readLine();
			discussionController.updateDiscussionDetails(questionNo);
		} catch (NumberFormatException | IOException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void deleteDiscussion() {
		int count=0;
		try {
			System.out.println("Enter the question no:");
			Long questionNo = Long.parseLong(bufferedReader.readLine());
			count=discussionController.deleteDiscussionDetails(questionNo);
			logger.info(count+" "+"Rows Deleted!");
		} catch (NumberFormatException | IOException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void getDiscussion() {
		discussionList = discussionController.getDiscussionDetails();
		Iterator<Discussion> discussionIterator = discussionList.iterator();
		System.out
				.println("-------------------------------------------------------------------------------------------");
		while (discussionIterator.hasNext()) {
			System.out.println(discussionIterator.next());
		}
		System.out
				.println("-------------------------------------------------------------------------------------------");
	}

	public static void getParticularDiscussion() {
		try {
			System.out.println("Enter the question no:");
			Long questionNo = Long.parseLong(bufferedReader.readLine());
			List<Discussion> discussionParicularList = new ArrayList<Discussion>();
			discussionParicularList = discussionController.getParticularDiscussionDetails(questionNo);
			Iterator<Discussion> discussionParticularIterator = discussionParicularList.iterator();
			System.out.println(
					"-------------------------------------------------------------------------------------------");
			while (discussionParticularIterator.hasNext()) {
				System.out.println(discussionParticularIterator.next());
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------");
		} catch (NumberFormatException | IOException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void discussionByUnit() {
		try {
			System.out.println("Enter the unit no:");
			String unitNo = bufferedReader.readLine();
			discussionController.getDiscussionStatusByUnit(unitNo);
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void main(String[] args) {
		logger.info("In discussion application");
		while (true) {
			System.out.println("Discussion Application\n");
			System.out.println("1.Insert");
			System.out.println("2.Update");
			System.out.println("3.Delete");
			System.out.println("4.Retrieval");
			System.out.println("5.Paricular discussion data");
			System.out.println("6.Get discussion details by unitNo");
			System.out.println("7.Exit");
			System.out.println("Enter the choice:");
			try {
				Integer userChoice = Integer.parseInt(bufferedReader.readLine());
				switch (userChoice) {
				case 1:
					logger.info("In discussion controller -> add method");
					insertDiscussion();
					break;
				case 2:
					logger.info("In discussion controller -> update method");
					updateDiscussion();
					break;
				case 3:
					logger.info("In discussion controller -> delete method");
					deleteDiscussion();
					break;
				case 4:
					logger.info("In discussion controller -> get method");
					getDiscussion();
					break;
				case 5:
					logger.info("In discussion controller -> get particular discussion method");
					getParticularDiscussion();
					break;
				case 6:
					logger.info("In discussion controller -> get discussion by unit method");
					discussionByUnit();
					break;
				case 7:
					logger.info("Exits from class application");
					System.exit(0);
					break;
				default:
					throw new InvalidChoiceException("Enter the valid choice!");
				}
			} catch (IOException | InvalidChoiceException e) {
				logger.warn(e.getMessage());
			}
		}
	}
}
