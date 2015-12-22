package edu.iut.app;

import java.util.Date;
import java.util.LinkedList;

public final class DragDropEventTool {
	public static Date source;
	public static Date destination;
	
	public static void swap(){
		if (source!=null && destination!=null){
			System.out.println("Source : " + source + "destination : " + destination);
			LinkedList<ExamEvent> examToMove = ApplicationSession.instance().getAgenda().meetCriteriaDateEqual(source);
			LinkedList<ExamEvent> examDest = ApplicationSession.instance().getAgenda().meetCriteriaDateEqual(destination);
			
			if(!examToMove.isEmpty() && examDest.isEmpty()){
				examToMove.getFirst().setExamDate(destination);
			}
			
		}
		
	}
}
