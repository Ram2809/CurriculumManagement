package com.curriculum.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.curriculum.controller.TimeTableController;
import com.curriculum.exception.ControllerException;
import com.curriculum.exception.InvalidChoiceException;
import com.curriculum.model.TimeTable;

public class TimeTableApplication {
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static TimeTable timeTable = new TimeTable();
	static TimeTableController timeTableController = new TimeTableController();
	static List<TimeTable> timeTableList = new ArrayList<>();
	static Logger logger = Logger.getLogger("TimeTableApplication.class");

	public static void insertTimeTable() {
		int count=0;
		try {
			System.out.println("Enter the timetable details:");
			System.out.println("Enter the class room no:");
			Long roomNo=Long.parseLong(bufferedReader.readLine());
			for(int i=0;i<5;i++)
			{
				System.out.println("Enter the day:");
				String day=bufferedReader.readLine();
				System.out.println("Enter the 1st period:");
				String periodOne=bufferedReader.readLine();
				System.out.println("Enter the 2nd period:");
				String periodTwo=bufferedReader.readLine();
				System.out.println("Enter the 3rd period:");
				String periodThree=bufferedReader.readLine();
				System.out.println("Enter the 4th period:");
				String periodFour=bufferedReader.readLine();
				System.out.println("Enter the 5th period:");
				String periodFive=bufferedReader.readLine();
				System.out.println("Enter the 6th period:");
				String periodSix=bufferedReader.readLine();
				timeTable.setRoomNo(roomNo);
				timeTable.setDay(day);
				timeTable.setPeriodOne(periodOne);
				timeTable.setPeriodTwo(periodTwo);
				timeTable.setPeriodThree(periodThree);
				timeTable.setPeriodFour(periodFour);
				timeTable.setPeriodFive(periodFive);
				timeTable.setPeriodSix(periodSix);
				count=timeTableController.addTimeTableDetails(timeTable);
			}
			logger.info(count+" "+"Rows Inserted!");
		} catch (IOException | NumberFormatException |ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void updateTimeTable() {
		int count=0;
		try {
			System.out.println("Enter the class room no:");
			Long classRoomNo = Long.parseLong(bufferedReader.readLine());
			System.out.println("Enter the day:");
			String day = bufferedReader.readLine();
			count=timeTableController.updateTimeTableDetails(classRoomNo, day);
			logger.info(count+" "+"Rows Deleted!");
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void deleteTimeTable() {
		int count=0;
		try {
			System.out.println("Enter the class room no:");
			Long classRoomNo = Long.parseLong(bufferedReader.readLine());
			System.out.println("Enter the day:");
			String day = bufferedReader.readLine();
			count=timeTableController.deleteTimeTableDetails(classRoomNo, day);
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void getTimeTable() {
		timeTableList = timeTableController.getTimeTableDetails();
		Iterator<TimeTable> timeTableIterator = timeTableList.iterator();
		System.out
				.println("-------------------------------------------------------------------------------------------");
		while (timeTableIterator.hasNext()) {
			System.out.println(timeTableIterator.next());
		}
		System.out
				.println("-------------------------------------------------------------------------------------------");
	}

	public static void getParticularTimeTable() {
		try {
			System.out.println("Enter the class room no:");
			Long classRoomNo = Long.parseLong(bufferedReader.readLine());
			System.out.println("Enter the day:");
			String day = bufferedReader.readLine();
			List<TimeTable> timeTableParicularList = new ArrayList<TimeTable>();
			timeTableParicularList = timeTableController.getParticularTimeTableDetails(classRoomNo, day);
			Iterator<TimeTable> timeTableParticularIterator = timeTableParicularList.iterator();
			System.out.println(
					"-------------------------------------------------------------------------------------------");
			while (timeTableParticularIterator.hasNext()) {
				System.out.println(timeTableParticularIterator.next());
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------");
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	/*public static void timeTableByclassRoom() {
		try {
			System.out.println("Enter the class room no:");
			Integer roomNo = Integer.parseInt(bufferedReader.readLine());
			timeTableController.getTimeTableByclassRoom(roomNo);
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}

	public static void timeTableByclassStandard() {
		try {
			System.out.println("Enter the class room no:");
			Integer roomNo = Integer.parseInt(bufferedReader.readLine());
			timeTableController.getTimeTableByclassStandard(roomNo);
		} catch (IOException | NumberFormatException | ControllerException e) {
			logger.warn(e.getMessage());
		}
	}*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("TimeTable Application\n");
			System.out.println("1.Insert");
			System.out.println("2.Update");
			System.out.println("3.Delete");
			System.out.println("4.Retrieval");
			System.out.println("5.Paricular class timetable data");
			System.out.println("6.Get timetable for paticular standard");
			System.out.println("7.Exit");
			System.out.println("Enter the choice:");
			try {
				Integer userChoice = Integer.parseInt(bufferedReader.readLine());
				switch (userChoice) {
				case 1:
					insertTimeTable();
					break;
				case 2:
					updateTimeTable();
					break;
				case 3:
					deleteTimeTable();
					break;
				case 4:
					getTimeTable();
					break;
				case 5:
					getParticularTimeTable();
					break;
				/*case 6:
					timeTableByclassStandard();
					break;*/
				case 7:
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
