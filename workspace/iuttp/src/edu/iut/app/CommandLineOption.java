package edu.iut.app;

/**
 * <b>CommandLineOption est la classe représentant les différentes options possibles en lançant
 * l'application via une ligne de commande.</b>
 * <p>Une option est caractérisée par :
 * <ul>
 * <li>Une clé</li>
 * <li>Une description</li>
 * <li>Une valeur par défaut</li>
 * <li>Une valeur</li>
 * <li>Un type d'option</li>
 *
 * @param <ValueType>
 */
public class CommandLineOption<ValueType> {
	
	/**
	 * Enumération des différentes options possible.
	 * <p>Une option peut donc être :
	 * <ul>
	 * <li>Nulle (on prend alors l'option par défaut)</li>
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
		 * 			Le type de l'option désirée.
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
	 * En utilisant ce constructeur, on crée une instance de CommandLineOption en initialisant
	 * les attributs de la classe avec les différents paramètres.
	 * </p>
	 * 
	 * @param optionType
	 * 			Le type de l'option.
	 * @param key
	 * 			La clé de l'option.
	 * @param description
	 * 			La description de l'option.
	 * @param defaultValue
	 * 			La valeur par défaut de l'option.
	 */
	public CommandLineOption(final OptionType optionType, final String key, final String description, final ValueType defaultValue) {
		this.optionType=optionType;
		this.key=key;
		this.description=description;
		this.defaultValue=defaultValue;
		this.value=null;
	}
	
	/**
	 * Met à jour une option.
	 * 
	 * @param optionType
	 * 			Le nouveau type de l'option.
	 * @param key
	 * 			La nouvelle clé de l'option.
	 * @param description
	 * 			La nouvelle description de l'option.
	 * @param defaultValue
	 * 			La nouvelle valeur par défaut de l'option.
	 */
	public void setOption(OptionType optionType, String key, String description, ValueType defaultValue) {
		this.optionType=optionType;
		this.key=key;
		this.description=description;
		this.defaultValue=defaultValue;
	}
	
	/**
	 * Met à jour la valeur de l'option.
	 * 
	 * @param value
	 * 				La nouvelle veleur de l'option.
	 */
	public  void setValue(ValueType value) {
		this.value = value;
	}
	
	/**
	 * Retourne la clé de l'option.
	 * 
	 * @return La clé de l'option.
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
	 * Retourne la valeur de l'option si elle est définie sinon sa valeur par défaut.
	 * 
	 * @return La valeur de l'option ou sa valeur par défaut.
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
