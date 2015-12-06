package edu.iut.app;

/**
 * <b>Classroom est la classe représentant une salle de l'IUT.</b>
 * <p>Une salle est caractérisée par un nom de salle (une lettre suivi d'un nombre).</p>
 */
public class Classroom {
	
	/**
	 * Constructeur vide Classroom
	 * <p>
	 * En utilisant ce constructeur, on initialise le nom de la salle à "non affecté" en utilisant
	 * l'internationalisation.
	 * </p>
	 * 
	 * @see ApplicationSession
	 * @see ApplicationSession#instance()
	 * @see {@link ApplicationSession#getString(String)}
	 */
	public Classroom() {
	classRoomNumber=ApplicationSession.instance().resourceBundle.getString("notaffected");
	}
	
	/**
	 * Constructeur Classroom
	 * <p>
	 * En utilisant ce constructeur, on initialise le nom de salle grâce au paramètre classRoomNumber.
	 * </p>
	 * @param classRoomNumber
	 * 			Le nom de la salle.
	 */
	public Classroom(String classRoomNumber) {
		/* EX2: AFFECTATION */;
		this.classRoomNumber = classRoomNumber;
	}
	
	/**
	 * Met à jour le nom de la salle.
	 * 
	 * @param number
	 * 			Le nouveau nom de la salle.
	 */
	public void setClassroomNumber(String number) {
		/* EX2: AFFECTATION */;
		this.classRoomNumber = number;
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
