package edu.iut.io;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import edu.iut.app.ExamEvent;

//EX 1 Completer la classe 

public class XMLProjectWriter {
	
	public XMLProjectWriter() {		
	}
	
	public void save(LinkedList<ExamEvent> data, java.io.File xmlfile) {
		XMLEncoder encoder;
		try {
			encoder = new XMLEncoder(new FileOutputStream(xmlfile));
			encoder.writeObject(data);
	        encoder.flush();
	        encoder.close();
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
	}
}
