package edu.iut.app;

import java.util.ArrayList;

/**
 * <b>ApplicationLog est la classe permettant de gérer l'ensemble des messages émis par l'application.</b>
 */
public class ApplicationLogs extends ArrayList<IApplicationLog> {
	
	/**
	 * Constructeur vide ApplicationLogs.
	 * <p>
	 * En utilisant ce constructeur, on crée une instance de ApplicationLogs.
	 * </p>
	 * 
	 * @see ApplicationErrorLog
	 * @see ApplicationInfoLog
	 * @see ApplicationWarningLog
	 */
	public ApplicationLogs() {		
	}
	
	/**
	 * Retourne la liste des messages d'erreur émis par l'application.
	 * 
	 * @return Les messages d'erreur.
	 */
	public ArrayList<IApplicationLog> getErrors() {
		ArrayList<IApplicationLog> filteredLogs = new ArrayList<IApplicationLog>();
		for (IApplicationLog a : this) {
			if (a instanceof ApplicationErrorLog) {
				filteredLogs.add(a);
			}
		}
		return filteredLogs;
	}
	
	/**
	 * Retourne la liste des messages d'avertissement émis par l'application.
	 * 
	 * @return Les messages d'avertissement.
	 */
	public ArrayList<IApplicationLog> getWarnings() {
		ArrayList<IApplicationLog> filteredLogs = new ArrayList<IApplicationLog>();
		for (IApplicationLog a : this) {
			if (a instanceof ApplicationWarningLog) {
				filteredLogs.add(a);
			}
		}
		return filteredLogs;
	}
	
	/**
	 * Retourne la liste des messages d'information émis par l'application.
	 * 
	 * @return Les messages d'information.
	 */
	public ArrayList<IApplicationLog> getInfos() {
		ArrayList<IApplicationLog> filteredLogs = new ArrayList<IApplicationLog>();
		for (IApplicationLog a : this) {
			if (a instanceof ApplicationInfoLog) {
				filteredLogs.add(a);
			}
		}
		return filteredLogs;
	}
	

}
