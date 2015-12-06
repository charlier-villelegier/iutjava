package edu.iut.app;

/**
 * <b>ApplicationWarningLog est la classe représentant un message d'avertissement émis par l'application.
 * <p>Une ApplicationWarningLog est caractérisée par les attributs de sa classe mère :
 * <ul>
 * <li>Un message</li>
 * <li>Une liste de listeners</li>
 * </ul>
 * </p>
 * 
 * @see AbstractApplicationLog
 */
public class ApplicationWarningLog extends AbstractApplicationLog {
	
	/**
	 * Constructeur ApplicationWarningLog
	 * 
	 * <p>
	 * Ce constructeur utilise le constructeur de sa classe mère.
	 * Il initialise un message nul et crée une liste de listeners.
	 * </p>
	 */
	public ApplicationWarningLog() {
		super();
	}
	
	/**
	 * Met à jour le message d'avertissement.
	 * 
	 * @param message
	 * 		Le nouveau message d'avertissement.
	 * 
	 * @see AbstractApplicationLog
	 * @see AbstractApplicationLog.instance()
	 * @see AbstractApplicationLog.fireMessage()
	 */
	@Override
	public void setMessage(String message) {
		this.message = message;
		ApplicationSession.instance().getGUILogger().warning(this.message);
		super.fireMessage("[WARNING]", this.message);
	}
}
