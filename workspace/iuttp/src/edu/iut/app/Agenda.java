package edu.iut.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import edu.iut.app.Person.PersonFunction;

/**
 * <b>Agenda est la classe reprï¿½sentant le planing des soutenances.</b>
 * <p>Un agenda est caractï¿½risï¿½ par une liste d'ExamEvent et deux listes de Person (une d'ï¿½tudiants et une de juris).</p>
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
	 * Ce constructeur initialise la liste des ï¿½tudiants et des juris.
	 * </p>
	 */
	public Agenda() {
		students = new ArrayList<Person>();
		jurys = new ArrayList<Person>();
		documents = new ArrayList<Document>();
		classrooms = new ArrayList<Classroom>();
	}
	
	/**
	 * Ajoute un ExamEvent ï¿½ la liste de l'agenda.
	 * 
	 * @param examEvent
	 * 		ExamEvent ï¿½ ajouter.
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
	 * Met à jour la liste des documents.
	 * 
	 * @param number
	 * 			La nouvelle liste de documents.
	 */
	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}
	
	/**
	 * Met à jour la liste des étudiants.
	 * 
	 * @param number
	 * 			La nouvelle liste d'étudiants.
	 */
	public void setStudents(ArrayList<Person> students) {
		this.students = students;
	}
	
	/**
	 * Met à jour la liste des jurys.
	 * 
	 * @param number
	 * 			La nouvelle liste de jurys.
	 */
	public void setJurys(ArrayList<Person> jurys) {
		this.jurys = jurys;
	}
	
	/**
	 * Retourne la liste des étudiants.
	 * 
	 * @return La liste des étudiants.
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
	 * Met à jour la liste des salles de classe.
	 * 
	 * @param number
	 * 			La nouvelle liste de salles de classe.
	 */
	public void setClassrooms(ArrayList<Classroom> classrooms) {
		this.classrooms = classrooms;
	}

/**CritÃ¨res implÃ©mentÃ©s**/
	
	//Person


	/**
	 * Retourne une liste d'ExamEvent qui contiennent le prï¿½nom passï¿½ en paramï¿½tre selon sa fonction.
	 * 
	 * @param function
	 * 			La fonction de la personne souhaitï¿½e.
	 * @param firstName
	 * 			Le prï¿½nom de la personne souhaitï¿½e.
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
		//Si on demande l'Ã©tudiant
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
	 * Retourne une liste d'ExamEvent qui contiennent le nom passï¿½ en paramï¿½tre selon sa fonction.
	 * 
	 * @param function
	 * 			La fonction de la personne souhaitï¿½e.
	 * @param lastName
	 * 			Le nom de la personne souhaitï¿½e.
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
		//Si on demande l'Ã©tudiant
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
	 * Retourne une liste d'ExamEvent qui contiennent l'adresse ï¿½lectronique passï¿½e en paramï¿½tre selon sa fonction.
	 * 
	 * @param function
	 * 			La fonction de la personne souhaitï¿½e.
	 * @param mail
	 * 			L'adresse ï¿½lectronique de la personne souhaitï¿½e.
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
		
		//Si on demande l'Ã©tudiant
		for (ExamEvent exam : this) {
			if(exam.getStudent().getEmail().contains(mail)){
				goodMailEvents.add(exam);
			}
		}
		
		return goodMailEvents;
	}
	
	/**
	 * Retourne une liste d'ExamEvent qui contiennent le numï¿½ro de tï¿½lï¿½phone passï¿½ en paramï¿½tre selon sa fonction.
	 * 
	 * @param function
	 * 			La fonction de la personne souhaitï¿½e.
	 * @param phone
	 * 			Le numï¿½ro de tï¿½lï¿½phone de la personne souhaitï¿½e.
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
		
		//Si on demande l'Ã©tudiant
		for (ExamEvent exam : this) {
			if(exam.getStudent().getPhone().contains(phone)){
				goodPhoneEvents.add(exam);
			}
		}
		
		return goodPhoneEvents;
	}
	

	
	//Document
	
	/**
	 * Retourne une liste d'ExamEvent qui contiennent le document passï¿½ en paramï¿½tre.
	 * 
	 * @param uri
	 * 			Le document souhaitï¿½.
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
	 * Retourne une liste d'ExamEvent qui ont lieu avant la date passï¿½e en paramï¿½tre.
	 * 
	 * @param date
	 * 			La date souhaitï¿½e.
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
	 * Retourne une liste d'ExamEvent qui ont lieu aprï¿½s la date passï¿½e en paramï¿½tre.
	 * 
	 * @param date
	 * 			La date souhaitï¿½e.
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
	 * Retourne une liste d'ExamEvent qui ont lieu ï¿½ la date passï¿½e en paramï¿½tre.
	 * 
	 * @param date
	 * 			La date souhaitï¿½e.
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
	 * Retourne une liste d'ExamEvent qui concernent un étudiant passé en paramètre.
	 * 
	 * @param student
	 * 			L'étudiant souhaité.
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
	 * Retourne une liste d'ExamEvent qui concernent un jury passé en paramètre.
	 * 
	 * @param jury
	 * 			Le jury souhaité.
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
	 * Retourne une liste d'ExamEvent qui ont lieu dans une salle passé en paramètre.
	 * 
	 * @param room
	 * 			La salle de classe souhaitée.
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
