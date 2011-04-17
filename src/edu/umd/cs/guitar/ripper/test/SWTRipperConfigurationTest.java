package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.umd.cs.guitar.ripper.SWTApplicationRunner;
import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
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
	
	@Test
	public void testSetCustomizedEventList() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setCustomizedEventList("foo");
		assertEquals("foo", config.getCustomizedEventList());
	}
	
	/**
	 * Test that arguments are correctly passed to the application under test.
	 */
	@Test
	public void testArguments() { // TODO change how we handle arguments
//		SWTRipperConfiguration config = new SWTRipperConfiguration();
//		config.setMainClass(SWTArgumentApp.class.getName());
//		config.setArgumentList("one:1");
//
//		SWTRipper swtRipper = new SWTRipper(config);
//		new SWTRipperRunner(swtRipper).start();
//
//		IntegrationTest.ripAndDiff("SWTArgumentApp");
	}

	/*
	 * CURRENT STATE: In SWTApplication it appears URLs are only added if the
	 * (file:,jar:,http:) prefix is at the beginning of the URL, but the current
	 * urlList delimiter is ':'. As far as I can tell, no URL added by
	 * setUrlList will be in the right form.
	 */
	@Test
	public void testURLs() { // TODO change how we handle URLS
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setMainClass("edu.umd.cs.guitar.ripper.test.aut.SWTURLApp");
		config.setGuiFile("testoutput2.xml");
		config.setUrlList("file:testfiles/utest.txt");

//		final SWTRipper swtRipper = new SWTRipper(config);
//
//		try {
//			swtRipper.execute();
//		} catch (CmdLineException e) {
//			System.err.println(e.getMessage());
//		}

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
