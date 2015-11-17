package edu.iut.app;

import java.util.Date;
import java.util.LinkedList;

public interface ICriteriaDate {
	public LinkedList<ExamEvent> meetCriteriaDateBefore(Date date);
	public LinkedList<ExamEvent> meetCriteriaDateAfter(Date date);
	public LinkedList<ExamEvent> meetCriteriaDateEqual(Date date);
}
