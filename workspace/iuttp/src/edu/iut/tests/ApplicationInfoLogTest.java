package edu.iut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iut.app.ApplicationErrorLog;
import edu.iut.app.ApplicationInfoLog;

public class ApplicationInfoLogTest {

	@Test
	public void testSetMessage() {
		ApplicationInfoLog a = new ApplicationInfoLog();
		String mes = new String("Test");
		a.setMessage(mes);
		if(a.getMessage() != mes){
			fail("setMessage() n'a pas fonctionné");
		}
	}

}
