package edu.iut.app;

/**
 * <b>Classroom est la classe repr�sentant une salle de l'IUT.</b>
 * <p>Une salle est caract�ris�e par un nom de salle (une lettre suivi d'un nombre).</p>
 */
public class Classroom {
	
	/**
	 * Constructeur vide Classroom
	 * <p>
	 * En utilisant ce constructeur, on initialise le nom de la salle � "non affect�" en utilisant
	 * l'internationalisation.
	 * </p>
	 * 
	 * @see ApplicationSession
	 * @see ApplicationSession#instance()
	 * @see {@link ApplicationSession#getString(String)}
	 */
	public Classroom() {
		classRoomNumber="";
	}
	
	/**
	 * Constructeur Classroom
	 * <p>
	 * En utilisant ce constructeur, on initialise le nom de salle gr�ce au param�tre classRoomNumber.
	 * </p>
	 * @param classRoomNumber
	 * 			Le nom de la salle.
	 */
	public Classroom(String classRoomNumber) {
		this.classRoomNumber = classRoomNumber;
	}
	
	/**
	 * Met � jour le nom de la salle.
	 * 
	 * @param number
	 * 			Le nouveau nom de la salle.
	 */
	
	public void setClassRoomNumber(String classRoomNumber) {
		this.classRoomNumber = classRoomNumber;
	}
	
	/**
	 * Retourne le nom de la salle.
	 * 
	 * @return Le nom de la salle.
	 */
	public String getClassRoomNumber() {
		return classRoomNumber;
	}
	
	
	


	protected String classRoomNumber;
}
