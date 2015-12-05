package edu.iut.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import edu.iut.app.Person.PersonFunction;
import edu.iut.gui.frames.SchedulerFrame;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;


public class ApplicationSession {
	
	protected ResourceBundle resourceBundle;
	protected Locale locale;
	protected Logger sessionGuiLogger;
	protected Logger sessionExceptionLogger;
	protected String[] months;
	protected String[] days;
	protected Calendar dateSelected;
	protected Agenda agenda;
	protected SchedulerFrame myFrame;
	protected ActiveView actualView;

	private static ApplicationSession session = null;
	private ApplicationSession() {
		locale = Locale.getDefault();
		resourceBundle = ResourceBundle.getBundle("edu.iut.resources.strings.res");
		sessionGuiLogger = Logger.getLogger("IUTTrain");
		sessionGuiLogger.setLevel(Level.ALL);
		sessionExceptionLogger = Logger.getLogger("IUTException");
		sessionExceptionLogger.setLevel(Level.ALL);
		
		days = new String[7];
		days[0] = getString("monday"); days[1] =  getString("tuesday"); days[2] =  getString("wednesday");
		days[3] = getString("thursday");   days[4] =  getString("friday"); days[5] =  getString("saturday");
		days[6] = getString("sunday"); 
		
		months = new String[12];
		months[0] = getString("january"); months[1] =  getString("february"); months[2] =  getString("march");
		months[3] = getString("april");   months[4] =  getString("may");      months[5] =  getString("june");
		months[6] = getString("july"); months[7] =  getString("august"); months[8] =  getString("september");
		months[9] = getString("october"); months[10] =  getString("november"); months[11] =  getString("december");
		
		dateSelected = Calendar.getInstance();
		dateSelected.set(Calendar.MINUTE,0);
		dateSelected.set(Calendar.SECOND,0);
		dateSelected.set(Calendar.MILLISECOND,0);
		
		agenda = new Agenda();
		
		agenda.getStudents().add(new Person(PersonFunction.STUDENT,"Léo","Charlier","leo.charlier@u-psud.fr","0611223344"));
		
		
		agenda.getJurys().add(new Person(PersonFunction.JURY,"Hélène","Maynard","helene.maynard@u-psud.fr","0611223344"));
		agenda.getJurys().add(new Person(PersonFunction.JURY,"Julien","Tourille","julien.tourille@u-psud.fr","0611223344"));
		
		
		actualView=ActiveView.DAY_VIEW;
	}
	
	
	static public ApplicationSession instance() {
		if (session == null) {			
			session = new ApplicationSession();
		}
		return session;
	}
	
	public Logger getGUILogger() {
		return sessionGuiLogger;
	}
	public Logger getExceptionLogger() {
		return sessionExceptionLogger;
	}
	
	public void setLocale(Locale locale){
		this.locale = locale;
		Locale.setDefault(this.locale);
		resourceBundle=ResourceBundle.getBundle("edu.iut.resources.strings.res");
	}
	
	public String getString(String key) {
		return resourceBundle.getString(key);
	}
	
	public String[] getDays() {
		return days;
	}
	public String[] getMonths() {
		return months;
	}


	public Calendar getDateSelected() {
		return dateSelected;
	}


	public void setDateSelected(Calendar dateSelected) {
		this.dateSelected = dateSelected;
	}


	public SchedulerFrame getMyFrame() {
		return myFrame;
	}


	public void setMyFrame(SchedulerFrame myFrame) {
		this.myFrame = myFrame;
	}


	public Agenda getAgenda() {
		return agenda;
	}


	public ActiveView getActualView() {
		return actualView;
	}


	public void setActualView(ActiveView actualView) {
		this.actualView = actualView;
	}

	
	
	
	
}
