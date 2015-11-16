package edu.iut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iut.app.ApplicationErrorLog;
import edu.iut.app.ApplicationWarningLog;

public class ApplicationWarningLogTest {

	@Test
	public void testSetMessage() {
		ApplicationWarningLog a = new ApplicationWarningLog();
		String mes = new String("Test");
		a.setMessage(mes);
		if(a.getMessage() != mes){
			fail("setMessage() n'a pas fonctionné");
		}
	}

}
