package edu.iut.app;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public final class DragDropEventTool {
	public static Date source;
	public static Date destination;
	public static ExamEvent movingExam;
	
	public static void swap(){
		if (source!=null && destination!=null){
			System.out.println("Source : " + source + "destination : " + destination);
			LinkedList<ExamEvent> examToMove = ApplicationSession.instance().getAgenda().meetCriteriaDateEqual(source);
			LinkedList<ExamEvent> examDest = ApplicationSession.instance().getAgenda().meetCriteriaDateEqual(destination);
			
			//S'il y a une soutenance la ou on veut déplacer, on interverti les deux
			if(!examToMove.isEmpty() && !examDest.isEmpty()){
				examToMove.getFirst().setExamDate(destination);
				examDest.getFirst().setExamDate(source);
			}
			else if(!examToMove.isEmpty() && examDest.isEmpty()){
				examToMove.getFirst().setExamDate(destination);
			}
		}
	}
	
	public static void fakeSwap(){
		System.out.println("Source : " + source + "destination : " + destination);
	}
	
	//Permet de mettre à jour la date de destination : on garde l'heure de la source mais on prend le jour de la destination
	public static void majDestinationMonth(){
		if (source!=null && destination!=null){
			Calendar cSource = Calendar.getInstance();
			Calendar cDest = Calendar.getInstance();
		
			cSource.setTime(source);
			cDest.setTime(destination);
		
			cSource.set(Calendar.DAY_OF_MONTH, cDest.get(Calendar.DAY_OF_MONTH));
		
			destination = cSource.getTime();
		}
	}
	
	public static void moveExam(){
		source=movingExam.getExamDate();
		swap();
		movingExam=null;
	}
}
