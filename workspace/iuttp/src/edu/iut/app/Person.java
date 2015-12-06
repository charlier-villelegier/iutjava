package edu.iut.app;

/**
 * <b>Person est la classe repr�sentant les diff�rents individus concern�s par les soutenances.</b>
 * <p>Une personne est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Un pr�nom</li>
 * <li>Un nom</li>
 * <li>Une fonction</li>
 * <li>Un e-mail</li>
 * <li>Un num�ro de t�l�phone</li>
 * </ul>
 * </p>
 * 
 */
public class Person {
	
	/**
	 * Enum�ration des diff�rentes fonctions possible d'une personne
	 * <p>
	 * Une personne peut n'avoir aucune fonction (none), faire partie du juri (jury) ou �tre un
	 * �tudiant (student).
	 * </p>
	 */
	public enum PersonFunction{
		/* EX2 : Internationalisation */
		NONE(ApplicationSession.instance().resourceBundle.getString("none")),
		JURY(ApplicationSession.instance().resourceBundle.getString("jury")),
		STUDENT(ApplicationSession.instance().resourceBundle.getString("student"));
		
		private String personFunction;
		
		/**
		 * Initialise une fonction.
		 * 
		 * @param personFunction
		 * 			La fonction d�sir�e.
		 */
		PersonFunction(String personFunction) {
			this.personFunction = personFunction;
		}
		
		/**
		 * Retourne la fonction d'une personne.
		 * 
		 * @return La fonction de la personne.
		 */
		public String toString() {
			return personFunction;
		}		
	}
	
	/**
	 * Constructeur vide Person
	 * <p>
	 * En utilisant ce constructeur, on initialise uniquement sa fonction � "aucune".
	 * </p>
	 */
	public Person() {
		personFunction = PersonFunction.NONE;
	}
	
	/**
	 * Constructeur Person
	 * <p>
	 * En utilisant ce constructeur, on initialise chacun des attributs d'une personne gr�ce aux
	 * variables pass�es en param�tre.
	 * </p>
	 * 
	 * @param personFunction
	 * 			La fonction de la personne (none, jury ou student).
	 * @param firstname
	 * 			Le pr�nom de la personne.
	 * @param lastname
	 * 			Le nom de la personne.
	 * @param email
	 * 			L'adresse �lectronique de la personne.
	 * @param phone
	 * 			Le num�ro de t�l�phone de la personne.
	 */
	public Person(PersonFunction personFunction,
				  String firstname,
				  String lastname,
				  String email,
				  String phone) {
		/* EX2: initialisation */
		this.personFunction = personFunction;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
	}
	
	/**
	 * Met � jour la fonction de la personne.
	 * 
	 * @param function
	 * 			La nouvelle fonction de la personne.
	 */
	public void setFunction(PersonFunction function) {
		this.personFunction = function;
	}
	
	/**
	 * Retourne la fonction de la personne.
	 * 
	 * @return La fonction de la personne.
	 */
	public PersonFunction getFunction() {
		return personFunction;
	}
	
	/**
	 * Met � jour le pr�nom de la personne.
	 * 
	 * @param function
	 * 			Le nouveau pr�nom de la personne.
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/**
	 * Retourne le pr�nom de la personne.
	 * 
	 * @return Le pr�nom de la personne.
	 */
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * Met � jour le nom de la personne.
	 * 
	 * @param function
	 * 			Le nouveau nom de la personne.
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/**
	 * Retourne le nom de la personne.
	 * 
	 * @return Le nom de la personne.
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * Met � jour l'adresse �lectronique de la personne.
	 * 
	 * @param function
	 * 			La nouvelle adresse �lectronique de la personne.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Retourne l'adresse �lectronique de la personne.
	 * 
	 * @return L'adresse �lectronique de la personne.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Met � jour le num�ro de t�l�phone de la personne.
	 * 
	 * @param function
	 * 			Le nouveau num�ro de t�l�phone de la personne.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Retourne le num�ro de t�l�phone de la personne.
	 * 
	 * @return Le num�ro de t�l�phone de la personne.
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Retourne le pr�nom et le nom d'une personne concat�n�s.
	 * 
	 * @return Le pr�nom et le nom de la personne.
	 */
	public String toString(){
		return(this.firstname + " " + this.lastname);
	}
	
	protected PersonFunction personFunction;
	protected String firstname;
	protected String lastname;
	protected String email;
	protected String phone;
	

}
