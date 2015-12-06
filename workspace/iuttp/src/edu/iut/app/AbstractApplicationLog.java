package edu.iut.app;

import java.util.ArrayList;

/**
 * <b>AbstractApplicationLog est la classe abstraite représentant les différents messages pouvant être
 * émis par l'application (d'erreur, d'information et d'avertissement).</b>
 * <p>Les classes filles d'AbstractApplicationLog sont caractérisées par les attributs suivant :
 * <ul>
 * <li>Un message</li>
 * <li>Une liste de listeners</li>
 * </ul>
 * </p>
 * 
 */
public abstract class AbstractApplicationLog implements IApplicationLog {

	protected String message;
	protected ArrayList<IApplicationLogListener> listeners;
	
	/**
	 * Constructeur abstrait AbstractApplicationLog
	 * 
	 * <p>
	 * Il initialise un message nul et crée une liste de listeners.
	 * </p>
	 */
	public AbstractApplicationLog() {
		message = null;
		listeners = new ArrayList<IApplicationLogListener>();
	}
	
	/**
	 * Met à jour un message.
	 * 
	 * @param message
	 * 			Le nouveau message.
	 */
	@Override
	public abstract void setMessage(String message);
	
	/**
	 * Retourne le message.
	 * 
	 * @return le message.
	 */
	@Override
	public String getMessage() {
		return message;
	}
	
	/**
	 * Ajoute un listener à la liste de listener.
	 * 
	 * @param listener
	 * 			Le listenet à ajouter.
	 */
	@Override
	public void addListener(IApplicationLogListener listener) {
		listeners.add(listener);

	}
	
	/**
	 * Rtourne la liste des listeners.
	 * 
	 * @return la liste des listeners.
	 */
	@Override
	public IApplicationLogListener[] getApplicationLogListeners() {
		return (IApplicationLogListener[])listeners.toArray();
	}
	
	protected void fireMessage(String level, String message) {
		for (IApplicationLogListener listener_i : listeners) {
			listener_i.newMessage(level, message);
		}
	}
}
