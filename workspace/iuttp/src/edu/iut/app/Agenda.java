package edu.iut.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import edu.iut.app.Person.PersonFunction;

public class Agenda extends LinkedList<ExamEvent> implements ICriteriaPerson, ICriteriaDocument, ICriteriaDate {
	
	protected ArrayList<Person> students;
	protected ArrayList<Person> jurys;
	
	public Agenda() {
		students = new ArrayList<Person>();
		jurys = new ArrayList<Person>();
	}
	
	public void addCheckedEvent(ExamEvent examEvent) {
		this.add(examEvent);
	}
	
	
	
	
	
public ArrayList<Person> getStudents() {
		return students;
	}

	public ArrayList<Person> getJurys() {
		return jurys;
	}

/**Critères implémentés**/
	
	//Person
	
	@Override
	public LinkedList<ExamEvent> meetCriteriaFirstName(PersonFunction function,String firstName) {
		LinkedList<ExamEvent> goodFNameEvents = new LinkedList<>();
		
		//Si on demande les jury
		if(function==PersonFunction.JURY){
			for (ExamEvent exam : this) {
				for (Person person : exam.jury) {
					if(person.getFirstname().contains(firstName)){
						goodFNameEvents.add(exam);break;
					}
				}
			}
		}
		
		//Si on demande l'étudiant
		for (ExamEvent exam : this) {
			if(exam.getStudent().getFirstname().contains(firstName)){
				goodFNameEvents.add(exam);
			}
		}
		
		return goodFNameEvents;
	}

	@Override
	public LinkedList<ExamEvent> meetCriteriaLastName(PersonFunction function,String lastName) {
		LinkedList<ExamEvent> goodLNameEvents = new LinkedList<>();
		
		//Si on demande les jury
		if(function==PersonFunction.JURY){
			for (ExamEvent exam : this) {
				for (Person person : exam.jury) {
					if(person.getLastname().contains(lastName)){
						goodLNameEvents.add(exam);break;
					}
				}
			}
		}
		
		//Si on demande l'étudiant
		for (ExamEvent exam : this) {
			if(exam.getStudent().getLastname().contains(lastName)){
				goodLNameEvents.add(exam);
			}
		}
		
		return goodLNameEvents;
	}

	@Override
	public LinkedList<ExamEvent> meetCriteriaMail(PersonFunction function,String mail) {
		LinkedList<ExamEvent> goodMailEvents = new LinkedList<>();
		
		//Si on demande les jury
		if(function==PersonFunction.JURY){
			for (ExamEvent exam : this) {
				for (Person person : exam.jury) {
					if(person.getEmail().contains(mail)){
						goodMailEvents.add(exam);break;
					}
				}
			}
		}
		
		//Si on demande l'étudiant
		for (ExamEvent exam : this) {
			if(exam.getStudent().getEmail().contains(mail)){
				goodMailEvents.add(exam);
			}
		}
		
		return goodMailEvents;
	}

	@Override
	public LinkedList<ExamEvent> meetCriteriaPhone(PersonFunction function,String phone) {
		LinkedList<ExamEvent> goodPhoneEvents = new LinkedList<>();
		
		//Si on demande les jury
		if(function==PersonFunction.JURY){
			for (ExamEvent exam : this) {
				for (Person person : exam.jury) {
					if(person.getPhone().contains(phone)){
						goodPhoneEvents.add(exam);break;
					}
				}
			}
		}
		
		//Si on demande l'étudiant
		for (ExamEvent exam : this) {
			if(exam.getStudent().getPhone().contains(phone)){
				goodPhoneEvents.add(exam);
			}
		}
		
		return goodPhoneEvents;
	}
	

	
	//Document
	@Override
	public LinkedList<ExamEvent> meetCriteriaURI(String uri) {
		LinkedList<ExamEvent> goodURIEvents = new LinkedList<>();
		
		for (ExamEvent exam : this) {
			for (Document doc : exam.getDocuments()) {
				if(doc.getDocumentURI().contains(uri)){
					goodURIEvents.add(exam);
				}
			}
		}
		return goodURIEvents;
	}

	
	//Date
	@Override
	public LinkedList<ExamEvent> meetCriteriaDateBefore(Date date) {
		LinkedList<ExamEvent> dateBefore = new LinkedList<>();
		for (ExamEvent exam : this) {
			if(exam.getExamDate().compareTo(date)<0){
				dateBefore.add(exam);
			}
		}
		
		return dateBefore;
	}

	@Override
	public LinkedList<ExamEvent> meetCriteriaDateAfter(Date date) {
		LinkedList<ExamEvent> dateAfter = new LinkedList<>();
		for (ExamEvent exam : this) {
			if(exam.getExamDate().compareTo(date)>0){
				dateAfter.add(exam);
			}
		}
		
		return dateAfter;
	}

	@Override
	public LinkedList<ExamEvent> meetCriteriaDateEqual(Date date) {
		LinkedList<ExamEvent> dateEqual = new LinkedList<>();
		for (ExamEvent exam : this) {
			if(exam.getExamDate().compareTo(date)==0){
				dateEqual.add(exam);
			}
		}
		
		return dateEqual;
	}

	
	
}
