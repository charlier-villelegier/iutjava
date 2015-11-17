package edu.iut.app;

import java.util.ArrayList;
import java.util.LinkedList;

import edu.iut.app.Person.PersonFunction;

public interface ICriteriaPerson {
	
	public LinkedList<ExamEvent> meetCriteriaFirstName(PersonFunction function,String firstName);
	public LinkedList<ExamEvent> meetCriteriaLastName(PersonFunction function,String firstName);
	public LinkedList<ExamEvent> meetCriteriaMail(PersonFunction function,String firstName);
	public LinkedList<ExamEvent> meetCriteriaPhone(PersonFunction function,String firstName);
	
	
}
