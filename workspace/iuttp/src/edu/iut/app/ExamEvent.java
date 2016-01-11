package edu.iut.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.iut.app.Person.PersonFunction;

/**
 * <b>ExamEvent est la classe reprï¿½sentant les soutenances.</b>
 * <p>Une soutenance est caractï¿½risï¿½ par :
 * <ul>
 * <li>Un date</li>
 * <li>Un étudiant</li>
 * <li>Une liste de jurys</li>
 * <li>Une salle de classe</li>
 * <li>Une liste de documents </li>
 * </ul>
 * </p>
 * 
 * @see Person
 * @see Classroom
 * @see Document
 */
public class ExamEvent implements Comparable<ExamEvent>{
	
	/**
	 * Constructeur vide ExamEvent
	 * <p>
	 * En utilisant ce constructeur, on crée une instance d'ExamEvent vide. Elle est utilisée
	 * pour la sérialisation.
	 * </p>
	 */
	public ExamEvent() {		
	}
	
	/**
	 * Constructeur ExamEvent
	 * <p>
	 * En utilisant ce constructeur, on initialise chacun des attributs d'une soutenance grâce aux
	 * variables passées en paramètre.
	 * </p>
	 * 
	 * @param date
	 * 			La date de la soutenance.
	 * @param person
	 * 			L'étudiant concerné par la soutenance.
	 * @param jury
	 * 			La liste des jurys assistant à la soutenance.
	 * @param classRoom
	 * 			La classe où se déroule la soutenance.
	 * @param document
	 * 			La liste des documents remis pour la soutenance.
	 */
	public ExamEvent(Date date, Person person, ArrayList<Person> jury,
					Classroom classRoom, ArrayList<Document> document) {
		this.examDate = date;
		this.student = person;
		this.jury = jury;
		this.classroom = classRoom;
		this.documents = document;
	}
	
	/** EX2: FAITE LES ACCESSEURS DES ATTRIBUTS, AJOUTER DES ATTRIBUT ? **/
	protected Date examDate;
	protected Person student;
	protected ArrayList<Person> jury;
	protected Classroom classroom;
	protected ArrayList<Document> documents;
	
	//Accesseurs
	
	/**
	 * Retourne la date de la soutenance.
	 * 
	 * @return La date de la soutenance.
	 */
	public Date getExamDate() {
		return examDate;
	}
	
	/**
	 * Retourne l'étudiant concerné par la soutenance.
	 * 
	 * @return L'étudiant passant la soutenance.
	 */
	public Person getStudent() {
		return student;
	}
	
	/**
	 * Retourne la liste des jurys assisant à la soutenance.
	 * 
	 * @return La liste des jurys présents à la soutenance.
	 */
	public ArrayList<Person> getJury() {
		return jury;
	}
	
	/**
	 * Retourne la salle de classe où se déroule la soutenance.
	 * 
	 * @return La salle de classe.
	 */
	public Classroom getClassroom() {
		return classroom;
	}
	
	/**
	 * Retourne la liste des documents remis pour la soutenance.
	 * 
	 * @return La liste des documents.
	 */
	public ArrayList<Document> getDocuments() {
		return documents;
	}
	
	/**
	 * Met à jour la date de la soutenance.
	 * 
	 * @param examDate
	 * 			La nouvelle date de la soutenance.
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	
	/**
	 * Met à jour l'étudiant concerné par la soutenance.
	 * 
	 * @param student
	 * 			Le nouvel étudiant passant sa soutenance.
	 */
	public void setStudent(Person student) {
		this.student = student;
	}
	
	/**
	 * Met à jour les jurys de la soutenance.
	 * 
	 * @param jury
	 * 			Les nouveaux jurys de la soutenance.
	 */
	public void setJury(ArrayList<Person> jury) {
		this.jury = jury;
	}
	
	/**
	 * Met à jour la salle de classe de la soutenance.
	 * 
	 * @param classroom
	 * 			La nouvelle salle de classe de la soutenance.
	 */
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	/**
	 * Met à jour les documents remis pour la soutenance.
	 * 
	 * @param documents
	 * 			Les nouveaux documents.
	 */
	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}
	
	
	@Override
	public int compareTo(ExamEvent o) {
		return(this.getExamDate().compareTo(((ExamEvent) o).getExamDate()));
	}

	
	
	
	
	 
}
