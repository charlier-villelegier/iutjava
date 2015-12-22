package edu.iut.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import edu.iut.app.Person.PersonFunction;

/**
 * <b>Agenda est la classe repr�sentant le planing des soutenances.</b>
 * <p>Un agenda est caract�ris� par une liste d'ExamEvent et deux listes de Person (une d'�tudiants et une de juris).</p>
 * 
 * @see ExamEvent
 * @see Person
 */
public class Agenda extends LinkedList<ExamEvent> implements ICriteriaPerson, ICriteriaDocument, ICriteriaDate {
	
	protected ArrayList<Person> students;
	protected ArrayList<Person> jurys;
	protected ArrayList<Document> documents;
	
	/**
	 * Constructeur vide Agenda
	 * <p>
	 * Ce constructeur initialise la liste des �tudiants et des juris.
	 * </p>
	 */
	public Agenda() {
		students = new ArrayList<Person>();
		jurys = new ArrayList<Person>();
		documents = new ArrayList<Document>();
	}
	
	/**
	 * Ajoute un ExamEvent � la liste de l'agenda.
	 * 
	 * @param examEvent
	 * 		ExamEvent � ajouter.
	 * 
	 * @see ExamEvent
	 */
	public void addCheckedEvent(ExamEvent examEvent) {
		this.add(examEvent);
	}
	
	
	
	
	
	public ArrayList<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}

	public void setStudents(ArrayList<Person> students) {
		this.students = students;
	}

	public void setJurys(ArrayList<Person> jurys) {
		this.jurys = jurys;
	}

	public ArrayList<Person> getStudents() {
		return students;
	}

	public ArrayList<Person> getJurys() {
		return jurys;
	}

/**Critères implémentés**/
	
	//Person
	
	/**
	 * Retourne une liste d'ExamEvent qui contiennent le pr�nom pass� en param�tre selon sa fonction.
	 * 
	 * @param function
	 * 			La fonction de la personne souhait�e.
	 * @param firstName
	 * 			Le pr�nom de la personne souhait�e.
	 * 
	 * @see ExamEvent
	 * @see Person
	 */
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
	
	/**
	 * Retourne une liste d'ExamEvent qui contiennent le nom pass� en param�tre selon sa fonction.
	 * 
	 * @param function
	 * 			La fonction de la personne souhait�e.
	 * @param lastName
	 * 			Le nom de la personne souhait�e.
	 * 
	 * @see ExamEvent
	 * @see Person
	 */
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
	
	/**
	 * Retourne une liste d'ExamEvent qui contiennent l'adresse �lectronique pass�e en param�tre selon sa fonction.
	 * 
	 * @param function
	 * 			La fonction de la personne souhait�e.
	 * @param mail
	 * 			L'adresse �lectronique de la personne souhait�e.
	 * 
	 * @see ExamEvent
	 * @see Person
	 */
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
	
	/**
	 * Retourne une liste d'ExamEvent qui contiennent le num�ro de t�l�phone pass� en param�tre selon sa fonction.
	 * 
	 * @param function
	 * 			La fonction de la personne souhait�e.
	 * @param phone
	 * 			Le num�ro de t�l�phone de la personne souhait�e.
	 * 
	 * @see ExamEvent
	 * @see Person
	 */
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
	
	/**
	 * Retourne une liste d'ExamEvent qui contiennent le document pass� en param�tre.
	 * 
	 * @param uri
	 * 			Le document souhait�.
	 * 
	 * @see ExamEvent
	 * @see Document
	 */
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
	
	/**
	 * Retourne une liste d'ExamEvent qui ont lieu avant la date pass�e en param�tre.
	 * 
	 * @param date
	 * 			La date souhait�e.
	 * 
	 * @see ExamEvent
	 */
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
	
	/**
	 * Retourne une liste d'ExamEvent qui ont lieu apr�s la date pass�e en param�tre.
	 * 
	 * @param date
	 * 			La date souhait�e.
	 * 
	 * @see ExamEvent
	 */
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
	
	/**
	 * Retourne une liste d'ExamEvent qui ont lieu � la date pass�e en param�tre.
	 * 
	 * @param date
	 * 			La date souhait�e.
	 * 
	 * @see ExamEvent
	 */
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
