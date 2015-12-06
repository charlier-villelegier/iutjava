package edu.iut.app;

/**
 * <b>Document est la classe représentant les documents à rapporter le jour de la soutenance.</b>
 * <p>Un document est caractérisé par le chemin du fichier du document concerné.</p>
 */
public class Document {
	
	/**
	 * Constructeur vide Document
	 * <p>
	 * En utilisant ce constructeur, on initialise le nom du fichier par une chaîne de caractère
	 * vide.
	 * </p>
	 */
	public Document() {
		documentURI="";
	}
	
	/**
	 * Constructeur Document
	 * <p>
	 * En utilisant ce constructeur, on initialise le nom du fichier par une chaîne de caractère
	 * passée en paramètre.
	 * </p>
	 * 
	 * @param documentURI
	 * 			Le chemin du document.
	 */
	public Document(String documentURI) {
		/* EX2 : Affectation */
		this.documentURI = documentURI;
	}
	
	/**
	 * Met à jour le chemin du fichier.
	 * 
	 * @param number
	 * 			Le nouveau chemin du fichier.
	 */
	public void setDocumentURI(String number) {
		this.documentURI = number;
	}
	
	/**
	 * Retourne le chemin du fichier.
	 * 
	 * @return Le chemin du fichier.
	 */
	public String getDocumentURI() {
		return documentURI;
	}
	
	protected String documentURI;
}
