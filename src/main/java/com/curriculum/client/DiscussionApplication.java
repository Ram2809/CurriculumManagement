package com.curriculum.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import static com.curriculum.dao.impl.TopicDAOImpl.checkUnitNo;
import com.curriculum.controller.DiscussionController;
import com.curriculum.model.Discussion;
import com.curriculum.exception.ControllerException;
import com.curriculum.exception.InvalidChoiceException;
import com.curriculum.exception.TopicNotFoundException;

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
			boolean status=checkUnitNo(unitNo);
			if (!status) {
				throw new TopicNotFoundException("Topic not found,Enter the valid id!");
			}
			System.out.println("Enter the discussion date:");
			String date = bufferedReader.readLine();
			discussion.setQuestion(question);
			discussion.setAnswer(answer);
			discussion.setUnitNo(unitNo);
			discussion.setDate(date);
			count=discussionController.addDiscussionDetails(discussion);
			logger.info(count+" "+"Rows Inserted!");
		} catch (NumberFormatException | IOException |TopicNotFoundException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void updateDiscussion() {
		int count = 0;
		try {
			System.out.println("Enter the question no:");
			Long questionNo = Long.parseLong(bufferedReader.readLine());
			System.out.println("1.Update Question");
			System.out.println("2.Update Answer");
			System.out.println("3.Update UnitNo");
			System.out.println("4.Update Date");
			System.out.println("Enter your choice:");
			Integer userChoice = Integer.parseInt(bufferedReader.readLine());
			switch (userChoice) {
			case 1:
				System.out.println("Enter the new question:");
				String newQuestion = bufferedReader.readLine();
				count = discussionController.updateDiscussionDetails(questionNo, "Question", newQuestion);
				break;
			case 2:
				System.out.println("Enter the new answer:");
				String newAnswer = bufferedReader.readLine();
				count = discussionController.updateDiscussionDetails(questionNo, "Answer", newAnswer);
				break;
			case 3:
				System.out.println("Enter the new unitNo:");
				String newUnitNo = bufferedReader.readLine();
				boolean status=checkUnitNo(newUnitNo);
				if (!status) {
					throw new TopicNotFoundException("Topic not found,Enter the valid id!");
				}
				count = discussionController.updateDiscussionDetails(questionNo, "UnitNo", newUnitNo);
				break;
			case 4:
				System.out.println("Enter the new date:");
				String newDate = bufferedReader.readLine();
				count = discussionController.updateDiscussionDetails(questionNo, "Date", newDate);
				break;
			default:
				throw new InvalidChoiceException("Enter the valid choice!");
			}
		} catch (NumberFormatException | IOException | ControllerException | InvalidChoiceException|TopicNotFoundException e) {
			logger.warn(e.getMessage());
		}
		logger.info(count + " " + "Rows Updated!");
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

	/*public static void discussionByUnit() {
		try {
			System.out.println("Enter the unit no:");
			String unitNo = bufferedReader.readLine();
			discussionController.getDiscussionStatusByUnit(unitNo);
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}*/

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
					insertDiscussion();
					break;
				case 2:
					updateDiscussion();
					break;
				case 3:
					deleteDiscussion();
					break;
				case 4:
					getDiscussion();
					break;
				case 5:
					getParticularDiscussion();
					break;
				/*case 6:
					discussionByUnit();
					break;*/
				case 7:
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
