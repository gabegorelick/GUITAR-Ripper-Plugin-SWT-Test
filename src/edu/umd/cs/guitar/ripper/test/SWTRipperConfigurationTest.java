package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.Test;

import edu.umd.cs.guitar.ripper.SWTApplicationRunner;
import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.SWTRipperRunner;
import edu.umd.cs.guitar.ripper.test.aut.SWTArgumentApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTBasicApp;

public class SWTRipperConfigurationTest {

	@Test
	public void testSetLogFile() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setLogFile("foo");
		assertEquals("foo", config.getLogFile());
	}
	
	@Test
	public void testSetLogWidgetFile() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setLogWidgetFile("foo");
		assertEquals("foo", config.getLogWidgetFile());
	}
	
	@Test
	public void testSetInitialWaitingTime() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setInitialWaitTime(100);
		assertEquals(100, config.getInitialWaitTime());
	}
		
	/**
	 * Test that arguments are correctly passed to the application under test.
	 */
	@Test
	public void testArguments() { // TODO change how we handle arguments
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setMainClass(SWTArgumentApp.class.getName());
		config.setGuiFile("testoutput.xml");
		config.setArgumentList(new String[] {"one","1"});

		SWTRipper swtRipper = new SWTRipper(config);
		SWTRipperRunner runner = new SWTRipperRunner(swtRipper);
		runner.start();
		assertEquals(2,config.getArgumentList().length);
		assertEquals("one",config.getArgumentList()[0]);
		IntegrationTest.ripAndDiff("SWTArgumentApp");
	}

	
	@Test
	public void testURLs() { 
		SWTRipperConfiguration rc = new SWTRipperConfiguration();
		rc.setUrlArrayList(new String[]{"file:/GUITAR-Ripper-Plugin-SWT-Test/testfiles","file:/GUITAR-Ripper-Plugin-SWT-Test/"});
		rc.setMainClass("SWTBasicApp");
		final SWTRipper ripper = new SWTRipper(rc);
		
		assertEquals(2,rc.getUrls().length);
		assertEquals("file:/GUITAR-Ripper-Plugin-SWT-Test/testfiles", rc.getUrls()[0].toString());
		// assertEquals(-1,diff("testfiles/SWTURLApp.xml","testoutput2.xml"));

	}

	/*
	 * CURRENT STATE: It does something. Telling it to ignore a button and it
	 * seems to leave out a lot of the xml.
	 */
	@Test
	public void testConfigureFile() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setMainClass(SWTBasicApp.class.getName());
		config.setConfigFile("testconfig.xml");

		SWTRipper swtRipper = new SWTRipper(config);
		new SWTApplicationRunner(swtRipper).run();
		
		// TODO finish
		
//		IntegrationTest.ripAndDiff(""); 
//		
//		assertEquals(-1,diff("testfiles/ConfigureFile.xml","testoutput4.xml"));

	}
}
