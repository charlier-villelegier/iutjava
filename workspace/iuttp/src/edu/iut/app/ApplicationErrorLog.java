package edu.iut.app;

/**
 * <b>ApplicationErrorLog est la classe représentant un message d'erreur émis par l'application.
 * <p>Une ApplicationErrorLog est caractérisée par les attributs de sa classe mère :
 * <ul>
 * <li>Un message</li>
 * <li>Une liste de listeners</li>
 * </ul>
 * </p>
 * 
 * @see AbstractApplicationLog
 */
public class ApplicationErrorLog extends AbstractApplicationLog {
	
	/**
	 * Constructeur ApplicationErrorLog
	 * 
	 * <p>
	 * Ce constructeur utilise le constructeur de sa classe mère.
	 * Il initialise un message nul et crée une liste de listeners.
	 * </p>
	 */
	public ApplicationErrorLog() {
		super();
	}
	
	/**
	 * Met à jour le message d'erreur.
	 * 
	 * @param message
	 * 		Le nouveau message d'erreur.
	 * 
	 * @see AbstractApplicationLog
	 * @see AbstractApplicationLog.instance()
	 * @see AbstractApplicationLog.fireMessage()
	 */
	@Override
	public void setMessage(String message) {
		this.message = message;
		ApplicationSession.instance().getGUILogger().severe(this.message);
		super.fireMessage("[ERROR]", this.message);
	}


}
