package edu.iut.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.iut.app.Person.PersonFunction;

/**
 * <b>ExamEvent est la classe repr�sentant les soutenances.</b>
 * <p>Une soutenance est caract�ris� par :
 * <ul>
 * <li>Un date</li>
 * <li>Un �tudiant</li>
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
	 * En utilisant ce constructeur, on cr�e une instance d'ExamEvent vide. Elle est utilis�e
	 * pour la s�rialisation.
	 * </p>
	 */
	public ExamEvent() {		
	}
	
	/**
	 * Constructeur ExamEvent
	 * <p>
	 * En utilisant ce constructeur, on initialise chacun des attributs d'une soutenance gr�ce aux
	 * variables pass�es en param�tre.
	 * </p>
	 * 
	 * @param date
	 * 			La date de la soutenance.
	 * @param person
	 * 			L'�tudiant concern� par la soutenance.
	 * @param jury
	 * 			La liste des jurys assistant � la soutenance.
	 * @param classRoom
	 * 			La classe o� se d�roule la soutenance.
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
	 * Retourne l'�tudiant concern� par la soutenance.
	 * 
	 * @return L'�tudiant passant la soutenance.
	 */
	public Person getStudent() {
		return student;
	}
	
	/**
	 * Retourne la liste des jurys assisant � la soutenance.
	 * 
	 * @return La liste des jurys pr�sents � la soutenance.
	 */
	public ArrayList<Person> getJury() {
		return jury;
	}
	
	/**
	 * Retourne la salle de classe o� se d�roule la soutenance.
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
	 * Met � jour la date de la soutenance.
	 * 
	 * @param examDate
	 * 			La nouvelle date de la soutenance.
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	
	/**
	 * Met � jour l'�tudiant concern� par la soutenance.
	 * 
	 * @param student
	 * 			Le nouvel �tudiant passant sa soutenance.
	 */
	public void setStudent(Person student) {
		this.student = student;
	}
	
	/**
	 * Met � jour les jurys de la soutenance.
	 * 
	 * @param jury
	 * 			Les nouveaux jurys de la soutenance.
	 */
	public void setJury(ArrayList<Person> jury) {
		this.jury = jury;
	}
	
	/**
	 * Met � jour la salle de classe de la soutenance.
	 * 
	 * @param classroom
	 * 			La nouvelle salle de classe de la soutenance.
	 */
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	/**
	 * Met � jour les documents remis pour la soutenance.
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
