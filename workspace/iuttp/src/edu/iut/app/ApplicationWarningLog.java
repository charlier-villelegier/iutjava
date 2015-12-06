package edu.iut.app;

/**
 * <b>ApplicationWarningLog est la classe repr�sentant un message d'avertissement �mis par l'application.
 * <p>Une ApplicationWarningLog est caract�ris�e par les attributs de sa classe m�re :
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
	 * Ce constructeur utilise le constructeur de sa classe m�re.
	 * Il initialise un message nul et cr�e une liste de listeners.
	 * </p>
	 */
	public ApplicationWarningLog() {
		super();
	}
	
	/**
	 * Met � jour le message d'avertissement.
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
