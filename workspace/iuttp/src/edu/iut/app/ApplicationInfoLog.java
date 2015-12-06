package edu.iut.app;

/**
 * <b>ApplicationInfoLog est la classe repr�sentant un message d'information �mis par l'application.
 * <p>Une ApplicationInfoLog est caract�ris�e par les attributs de sa classe m�re :
 * <ul>
 * <li>Un message</li>
 * <li>Une liste de listeners</li>
 * </ul>
 * </p>
 * 
 * @see AbstractApplicationLog
 */
public class ApplicationInfoLog extends AbstractApplicationLog {
	
	/**
	 * Constructeur ApplicationInfoLog
	 * 
	 * <p>
	 * Ce constructeur utilise le constructeur de sa classe m�re.
	 * Il initialise un message nul et cr�e une liste de listeners.
	 * </p>
	 */
	public ApplicationInfoLog() {
		super();
	}
	
	/**
	 * Met � jour le message d'information.
	 * 
	 * @param message
	 * 		Le nouveau message d'information.
	 * 
	 * @see AbstractApplicationLog
	 * @see AbstractApplicationLog.instance()
	 * @see AbstractApplicationLog.fireMessage()
	 */
	@Override
	public void setMessage(String message) {
		this.message = message;
		ApplicationSession.instance().getGUILogger().info(this.message);
        super.fireMessage("[INFO]", this.message);
	}

}
