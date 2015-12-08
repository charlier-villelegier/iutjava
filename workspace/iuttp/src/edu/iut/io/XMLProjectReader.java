package edu.iut.io;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import edu.iut.app.ExamEvent;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

// EX 1 Completer la classe

public class XMLProjectReader {
	public XMLProjectReader() {
		
	}
	
	public LinkedList<ExamEvent> load(java.io.File xmlfile) throws IOException {
		LinkedList<ExamEvent> data = new LinkedList<ExamEvent>();
		  
	    // ouverture de decodeur
	    XMLDecoder decoder = new XMLDecoder(new FileInputStream(xmlfile));
	    try {
	        // deserialisation de l'objet
	        data = (LinkedList<ExamEvent>)decoder.readObject();
	    } finally {
	        // fermeture du decodeur
	        decoder.close();
	    }
		return data;
		
	}
}
