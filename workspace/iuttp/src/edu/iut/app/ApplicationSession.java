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

/**
 * <b>ApplicationSession est un singleton reprï¿½sentant une session en cours.
 * Il gï¿½re donc l'internationalisation ainsi que les logger (d'erreur, d'information et d'avertissement).</b>
 * <p>Une session est caractï¿½risï¿½e par :
 * <ul>
 * <li>Un ressourceBundle</li>
 * <li>Une langue (locale)</li>
 * <li>Une sessionGuiLogger</li>
 * <li>Une sessionExceptionLogger</li>
 * <li>La liste des mois de l'annï¿½e</li>
 * <li>La liste des jours de la semaine</li>
 * <li>Une date</li>
 * <li>Un agenda</li>
 * <li>Une fenï¿½tre graphique</li>
 * <li>La vue actuelle (jour, semaine, mois)</li>
 * </ul>
 * </p>
 */
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
	protected CommandLineParser commandLineParser;

	private static ApplicationSession session = null;
	
	/**
	 * Constructeur vide ApplicationSession
	 * 
	 * <p>
	 * Ce constructeur initialise chacun des attributs de la classe.
	 * </p>
	 */
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
		
		//agenda.getStudents().add(new Person(PersonFunction.STUDENT,"LÃ©o","Charlier","leo.charlier@u-psud.fr","0611223344"));
		
		//agenda.getJurys().add(new Person(PersonFunction.JURY,"Nicolas","Ferey","nicolas.ferey@u-psud.fr","0611223344"));
		//agenda.getJurys().add(new Person(PersonFunction.JURY,"HÃ©lÃ¨ne","Maynard","helene.maynard@u-psud.fr","0611223344"));
		//agenda.getJurys().add(new Person(PersonFunction.JURY,"Julien","Tourille","julien.tourille@u-psud.fr","0611223344"));
		
		
		actualView=ActiveView.DAY_VIEW;
	}
	
	/**
	 * Crï¿½e une nouvelle instance d'ApplicationSession si celle-ci et nulle et la retourne.
	 * Si une instance existe dï¿½jï¿½ elle ne fait que la retourner.
	 * 
	 * @return La session.
	 */
	static public ApplicationSession instance() {
		if (session == null) {			
			session = new ApplicationSession();
		}
		return session;
	}
	
	/**
	 * Retourne la sessionGUILogger.
	 * 
	 * @return La sessionGUILogger.
	 */
	public Logger getGUILogger() {
		return sessionGuiLogger;
	}
	
	/**
	 * Retourne la sessionExceptionLogger.
	 * 
	 * @return La sessionExceptionLogger.
	 */
	public Logger getExceptionLogger() {
		return sessionExceptionLogger;
	}
	
	/**
	 * Met à jour la langue par défaut.
	 * 
	 * @param locale
	 * 			La nouvelle langue par défaut.
	 */
	public void setLocale(Locale locale){
		this.locale = locale;
		Locale.setDefault(this.locale);
		resourceBundle=ResourceBundle.getBundle("edu.iut.resources.strings.res");
	}
	
	/**
	 * Retourne la clï¿½ du ressourceBundle.
	 * 
	 * @param key
	 * 			La clï¿½ du ressourceBundle.
	 * @return La clï¿½ du ressourceBundle.
	 */
	public String getString(String key) {
		return resourceBundle.getString(key);
	}
	
	/**
	 * Retourne les jours de la semaine.
	 * 
	 * @return Une liste des jours de la semaine.
	 */
	public String[] getDays() {
		return days;
	}
	
	/**
	 * Retourne les mois de l'annï¿½e.
	 * 
	 * @return Une liste des mois de l'annï¿½e.
	 */
	public String[] getMonths() {
		return months;
	}
	
	/**
	 * Retourne la date du jour sélectionné (jour actif).
	 * 
	 * @return La date sélectionnée.
	 */
	public Calendar getDateSelected() {
		return dateSelected;
	}

	/**
	 * Met à jour la date sélectionnée (active).
	 * 
	 * @param dateSelected
	 * 			La nouvelle date.
	 */
	public void setDateSelected(Calendar dateSelected) {
		this.dateSelected = dateSelected;
	}

	/**
	 * Retourne la fenï¿½tre graphique.
	 * 
	 * @return la fenï¿½tre graphique.
	 * 
	 * @see SchedulerFrame
	 */
	public SchedulerFrame getMyFrame() {
		return myFrame;
	}

	/**
	 * Met ï¿½ jour la fenï¿½tre graphique.
	 * 
	 * @param myFrame
	 * 			La nouvelle fenï¿½tre graphique.
	 */
	public void setMyFrame(SchedulerFrame myFrame) {
		this.myFrame = myFrame;
	}

	/**
	 * Retourne l'agenda de la session en cours.
	 * 
	 * @return L'agenda de la session en cours.
	 * 
	 * @see Agenda
	 */
	public Agenda getAgenda() {
		return agenda;
	}

	/**
	 * Retourne la vue actuelle de la session (jour, semaine ou mois).
	 * 
	 * @return La vue actuelle de la session en cours.
	 */
	public ActiveView getActualView() {
		return actualView;
	}

	/**
	 * Met ï¿½ jour la vue actuelle de la session en cours.
	 * 
	 * @param actualView
	 * 			La nouvelle vue dï¿½sirï¿½e.
	 */
	public void setActualView(ActiveView actualView) {
		this.actualView = actualView;
	}
	
	/**
	 * Met à jour l'agenda actif.
	 * 
	 * @param agenda
	 * 			Le nouvel agenda.
	 */
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
	/**
	 * Retourne le CommandLineParser.
	 * 
	 * @return Le CommandLineParser.
	 */
	public CommandLineParser getCommandLineParser() {
		return commandLineParser;
	}
	
	/**
	 * Met à jour le CommandLineParser.
	 * 
	 * @param commandLineParser
	 * 			Le nouveau CommandLineParser.
	 */
	public void setCommandLineParser(CommandLineParser commandLineParser) {
		this.commandLineParser = commandLineParser;
	}

	
	
	
	
}
