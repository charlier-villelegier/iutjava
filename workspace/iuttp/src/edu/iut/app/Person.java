package edu.iut.app;

/**
 * <b>Person est la classe représentant les différents individus concernés par les soutenances.</b>
 * <p>Une personne est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un prénom</li>
 * <li>Un nom</li>
 * <li>Une fonction</li>
 * <li>Un e-mail</li>
 * <li>Un numéro de téléphone</li>
 * </ul>
 * </p>
 * 
 */
public class Person {
	
	/**
	 * Enumération des différentes fonctions possible d'une personne
	 * <p>
	 * Une personne peut n'avoir aucune fonction (none), faire partie du juri (jury) ou être un
	 * étudiant (student).
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
		 * 			La fonction désirée.
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
	 * En utilisant ce constructeur, on initialise uniquement sa fonction à "aucune".
	 * </p>
	 */
	public Person() {
		personFunction = PersonFunction.NONE;
	}
	
	/**
	 * Constructeur Person
	 * <p>
	 * En utilisant ce constructeur, on initialise chacun des attributs d'une personne grâce aux
	 * variables passées en paramètre.
	 * </p>
	 * 
	 * @param personFunction
	 * 			La fonction de la personne (none, jury ou student).
	 * @param firstname
	 * 			Le prénom de la personne.
	 * @param lastname
	 * 			Le nom de la personne.
	 * @param email
	 * 			L'adresse électronique de la personne.
	 * @param phone
	 * 			Le numéro de téléphone de la personne.
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
	 * Met à jour la fonction de la personne.
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
	 * Met à jour le prénom de la personne.
	 * 
	 * @param function
	 * 			Le nouveau prénom de la personne.
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/**
	 * Retourne le prénom de la personne.
	 * 
	 * @return Le prénom de la personne.
	 */
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * Met à jour le nom de la personne.
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
	 * Met à jour l'adresse électronique de la personne.
	 * 
	 * @param function
	 * 			La nouvelle adresse électronique de la personne.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Retourne l'adresse électronique de la personne.
	 * 
	 * @return L'adresse électronique de la personne.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Met à jour le numéro de téléphone de la personne.
	 * 
	 * @param function
	 * 			Le nouveau numéro de téléphone de la personne.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Retourne le numéro de téléphone de la personne.
	 * 
	 * @return Le numéro de téléphone de la personne.
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Retourne le prénom et le nom d'une personne concaténés.
	 * 
	 * @return Le prénom et le nom de la personne.
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
