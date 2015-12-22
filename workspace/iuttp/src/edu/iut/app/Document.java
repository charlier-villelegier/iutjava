package edu.iut.app;

/**
 * <b>Document est la classe repr�sentant les documents � rapporter le jour de la soutenance.</b>
 * <p>Un document est caract�ris� par le chemin du fichier du document concern�.</p>
 */
public class Document {
	
	/**
	 * Constructeur vide Document
	 * <p>
	 * En utilisant ce constructeur, on initialise le nom du fichier par une cha�ne de caract�re
	 * vide.
	 * </p>
	 */
	public Document() {
		documentURI="";
	}
	
	/**
	 * Constructeur Document
	 * <p>
	 * En utilisant ce constructeur, on initialise le nom du fichier par une cha�ne de caract�re
	 * pass�e en param�tre.
	 * </p>
	 * 
	 * @param documentURI
	 * 			Le chemin du document.
	 */
	public Document(String documentURI) {
		this.documentURI = documentURI;
	}
	
	/**
	 * Met � jour le chemin du fichier.
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
