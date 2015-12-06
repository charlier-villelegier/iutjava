package edu.iut.app;

/**
 * <b>CommandLineOption est la classe repr�sentant les diff�rentes options possibles en lan�ant
 * l'application via une ligne de commande.</b>
 * <p>Une option est caract�ris�e par :
 * <ul>
 * <li>Une cl�</li>
 * <li>Une description</li>
 * <li>Une valeur par d�faut</li>
 * <li>Une valeur</li>
 * <li>Un type d'option</li>
 *
 * @param <ValueType>
 */
public class CommandLineOption<ValueType> {
	
	/**
	 * Enum�ration des diff�rentes options possible.
	 * <p>Une option peut donc �tre :
	 * <ul>
	 * <li>Nulle (on prend alors l'option par d�faut)</li>
	 * <li>Un fichier</li>
	 * <li>Un entier</li>
	 * <li>Un nombre de type double</li>
	 * <li>Ne pas avoir de valeur</li>
	 * </ul>
	 * </p>
	 */
	public enum OptionType{
		NONE("None"),
		FILE("File"),
		STRING("String"),
		INTEGER("Integer"),
		DOUBLE("Double"),
		NOVALUE("NoValue");
		private String optionType;
		
		/**
		 * Initialise une fonction.
		 * 
		 * @param optionType
		 * 			Le type de l'option d�sir�e.
		 */
		OptionType(String optionType) {
			this.optionType = optionType;
		}
		
		/**
		 * Retourne le type de l'option.
		 * 
		 * @return Le type de l'option.
		 */
		public String toString() {
			return optionType;
		}		
	}
	
	/**
	 * Constructeur vide CommandLineOption.
	 * 
	 * <p>
	 * Ce constructeur initialise chacun des attributs de la classe avec une valeur nulle.
	 * </p>
	 */
	public CommandLineOption() {	
		this.key=new String();
		this.description=new String();
		this.defaultValue=null;
		this.value=null;
		this.optionType=OptionType.NONE;
		
	}
	
	/**
	 * Constructeur CommandLineOption
	 * 
	 * <p>
	 * En utilisant ce constructeur, on cr�e une instance de CommandLineOption en initialisant
	 * les attributs de la classe avec les diff�rents param�tres.
	 * </p>
	 * 
	 * @param optionType
	 * 			Le type de l'option.
	 * @param key
	 * 			La cl� de l'option.
	 * @param description
	 * 			La description de l'option.
	 * @param defaultValue
	 * 			La valeur par d�faut de l'option.
	 */
	public CommandLineOption(final OptionType optionType, final String key, final String description, final ValueType defaultValue) {
		this.optionType=optionType;
		this.key=key;
		this.description=description;
		this.defaultValue=defaultValue;
		this.value=null;
	}
	
	/**
	 * Met � jour une option.
	 * 
	 * @param optionType
	 * 			Le nouveau type de l'option.
	 * @param key
	 * 			La nouvelle cl� de l'option.
	 * @param description
	 * 			La nouvelle description de l'option.
	 * @param defaultValue
	 * 			La nouvelle valeur par d�faut de l'option.
	 */
	public void setOption(OptionType optionType, String key, String description, ValueType defaultValue) {
		this.optionType=optionType;
		this.key=key;
		this.description=description;
		this.defaultValue=defaultValue;
	}
	
	/**
	 * Met � jour la valeur de l'option.
	 * 
	 * @param value
	 * 				La nouvelle veleur de l'option.
	 */
	public  void setValue(ValueType value) {
		this.value = value;
	}
	
	/**
	 * Retourne la cl� de l'option.
	 * 
	 * @return La cl� de l'option.
	 */
	public String getKey() {
		return this.key;
	}
	
	/**
	 * Retourne la description de l'option.
	 * 
	 * @return La description de l'option.
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Retourne la valeur de l'option si elle est d�finie sinon sa valeur par d�faut.
	 * 
	 * @return La valeur de l'option ou sa valeur par d�faut.
	 */
	public ValueType getValue() {
		if (value != null) {
			return value;
		}
		return defaultValue;
	}
	
	/**
	 * Retourne le type de l'option.
	 * 
	 * @return Le type de l'option.
	 */
	public OptionType getOptionType() {
		return optionType;
	}

	protected String key;
	protected String description;
	protected ValueType defaultValue;
	protected ValueType value;
	protected OptionType optionType;
	

}
