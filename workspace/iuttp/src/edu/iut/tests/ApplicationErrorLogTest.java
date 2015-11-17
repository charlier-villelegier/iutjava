package edu.iut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iut.app.ApplicationErrorLog;

public class ApplicationErrorLogTest {

	@Test
	public void testSetMessage() {
		ApplicationErrorLog a = new ApplicationErrorLog();
		String mes = new String("Test");
		a.setMessage(mes);
		assertTrue("setMessage() n'a pas fonctionné", a.getMessage() == mes);
	}

}
