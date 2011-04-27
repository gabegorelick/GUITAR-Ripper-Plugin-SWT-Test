package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import edu.umd.cs.guitar.model.SWTApplicationStartException;
import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.test.aut.SWTArgumentApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTURLApp;

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
	public void testArguments() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setMainClass(SWTArgumentApp.class.getName());
		
		String[] args = new String[0];
		config.setArguments(args);

		SWTRipper swtRipper = new SWTRipper(config);
		assertArrayEquals(args, swtRipper.getApplication().getArgsToApp());

		args = new String[] { "one" };
		config.setArguments(args);
		swtRipper = new SWTRipper(config);
		assertArrayEquals(args, swtRipper.getApplication().getArgsToApp());
		
		args = new String[] { "one", "two" };
		config.setArguments(args);
		swtRipper = new SWTRipper(config);
		assertArrayEquals(args, swtRipper.getApplication().getArgsToApp());
	}

	@Test
	public void testURLs() throws SWTApplicationStartException, MalformedURLException {
		final String fileName = "README";
		
		File file = new File(fileName);
		assertTrue(file.exists());
				
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setMainClass(SWTURLApp.class.getName());
		config.setArguments(new String[] { fileName });
		config.setUrls(new URL[0]);
		
		SWTRipper ripper = new SWTRipper(config);
		
		// SWTURLApp doesn't block forever, so can start it on this thread
		ripper.getApplication().startGUI();
				
		config.setUrls(new URL[] { file.toURI().toURL()});
		new SWTRipper(config);
		
		// TODO figure our how to test this
				
	}

}
