package edu.iut.app;

import java.util.ArrayList;
import java.util.Date;

import edu.iut.app.Person.PersonFunction;

public class ExamEvent {
	public ExamEvent() {		
	}
	
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
	public Date getExamDate() {
		return examDate;
	}

	public Person getStudent() {
		return student;
	}

	public ArrayList<Person> getJury() {
		return jury;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public ArrayList<Document> getDocuments() {
		return documents;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public void setStudent(Person student) {
		this.student = student;
	}

	public void setJury(ArrayList<Person> jury) {
		this.jury = jury;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}

	
	
	
	
	 
}
