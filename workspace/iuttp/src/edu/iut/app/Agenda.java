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
public class Agenda extends LinkedList<ExamEvent> implements ICriteriaPerson, ICriteriaDocument, ICriteriaDate, ICriteriaClassroom {
	
	protected ArrayList<Person> students;
	protected ArrayList<Person> jurys;
	protected ArrayList<Document> documents;
	protected ArrayList<Classroom> classrooms;
	
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
		classrooms = new ArrayList<Classroom>();
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
	
	
	
	
	/**
	 * Retourne la liste des documents.
	 * 
	 * @return La liste des documents.
	 */
	public ArrayList<Document> getDocuments() {
		return documents;
	}
	
	/**
	 * Met � jour la liste des documents.
	 * 
	 * @param number
	 * 			La nouvelle liste de documents.
	 */
	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}
	
	/**
	 * Met � jour la liste des �tudiants.
	 * 
	 * @param number
	 * 			La nouvelle liste d'�tudiants.
	 */
	public void setStudents(ArrayList<Person> students) {
		this.students = students;
	}
	
	/**
	 * Met � jour la liste des jurys.
	 * 
	 * @param number
	 * 			La nouvelle liste de jurys.
	 */
	public void setJurys(ArrayList<Person> jurys) {
		this.jurys = jurys;
	}
	
	/**
	 * Retourne la liste des �tudiants.
	 * 
	 * @return La liste des �tudiants.
	 */
	public ArrayList<Person> getStudents() {
		return students;
	}
	
	/**
	 * Retourne la liste des jurys.
	 * 
	 * @return La liste des jurys.
	 */
	public ArrayList<Person> getJurys() {
		return jurys;
	}
	
	/**
	 * Retourne la liste des salles de classe.
	 * 
	 * @return La liste des salles de classe.
	 */
	public ArrayList<Classroom> getClassrooms() {
		return classrooms;
	}
	
	/**
	 * Met � jour la liste des salles de classe.
	 * 
	 * @param number
	 * 			La nouvelle liste de salles de classe.
	 */
	public void setClassrooms(ArrayList<Classroom> classrooms) {
		this.classrooms = classrooms;
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
				if(exam.jury!=null){
					for (Person person : exam.jury) {
						if(person.getFirstname().toLowerCase().contains(firstName.toLowerCase())){
							goodFNameEvents.add(exam);break;
						}
					}
				}
			}
		}
		//Si on demande l'étudiant
		else{
			for (ExamEvent exam : this) {
				if(exam.getStudent().getFirstname().toLowerCase().contains(firstName.toLowerCase())){
					goodFNameEvents.add(exam);
				}
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
				if(exam.jury!=null){
					for (Person person : exam.jury) {
						if(person.getLastname().toLowerCase().contains(lastName.toLowerCase())){
							goodLNameEvents.add(exam);break;
						}
					}
				}
			}
		}
		//Si on demande l'étudiant
		else{
			for (ExamEvent exam : this) {
				if(exam.getStudent().getLastname().toLowerCase().contains(lastName.toLowerCase())){
					goodLNameEvents.add(exam);
				}
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
	public LinkedList<ExamEvent> meetCriteriaDoc(Document doc) {
		LinkedList<ExamEvent> goodDocEvents = new LinkedList<>();
		for (ExamEvent exam : this) {
			if(exam.getDocuments()!=null){
				for (Document document : exam.getDocuments()) {
					if(document==doc){
						if(!goodDocEvents.contains(exam))goodDocEvents.add(exam);
					}
				}
			}
		}
		return goodDocEvents;
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
	
	/**
	 * Retourne une liste d'ExamEvent qui concernent un �tudiant pass� en param�tre.
	 * 
	 * @param student
	 * 			L'�tudiant souhait�.
	 * 
	 * @see ExamEvent
	 */
	@Override
	public LinkedList<ExamEvent> meetCriteriaStudent(Person student) {
		LinkedList<ExamEvent> studentEqual = new LinkedList<>();
		for (ExamEvent exam : this) {
			if(exam.getStudent()==student){
				studentEqual.add(exam);
			}
		}
		return studentEqual;
	}
	
	/**
	 * Retourne une liste d'ExamEvent qui concernent un jury pass� en param�tre.
	 * 
	 * @param jury
	 * 			Le jury souhait�.
	 * 
	 * @see ExamEvent
	 */
	@Override
	public LinkedList<ExamEvent> meetCriteriaJury(Person jury) {
		LinkedList<ExamEvent> juryEqual = new LinkedList<>();
		for (ExamEvent exam : this) {
			if(exam.getJury()!=null){
				for(Person jur : exam.getJury()){
					if(jur==jury){
						if(!juryEqual.contains(exam))juryEqual.add(exam);
					}
				}
			}
		}
		return juryEqual;
	}
	
	/**
	 * Retourne une liste d'ExamEvent qui ont lieu dans une salle pass� en param�tre.
	 * 
	 * @param room
	 * 			La salle de classe souhait�e.
	 * 
	 * @see ExamEvent
	 */
	@Override
	public LinkedList<ExamEvent> meetCriteriaClassroom(Classroom room) {
		LinkedList<ExamEvent> goodClassroomEvents = new LinkedList<>();
		for (ExamEvent exam : this) {
			if(exam.getClassroom()!=null){
				if(exam.getClassroom()==room){
					if(!goodClassroomEvents.contains(exam))goodClassroomEvents.add(exam);
				}
			}
		}
		return goodClassroomEvents;
	}

}
