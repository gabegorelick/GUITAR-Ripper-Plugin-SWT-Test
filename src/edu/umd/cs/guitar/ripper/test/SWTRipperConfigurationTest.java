/*	
 *  Copyright (c) 2011-@year@. The GUITAR group at the University of Maryland. Names of owners of this group may
 *  be obtained by sending an e-mail to atif@cs.umd.edu
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 *  documentation files (the "Software"), to deal in the Software without restriction, including without 
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 *	the Software, and to permit persons to whom the Software is furnished to do so, subject to the following 
 *	conditions:
 * 
 *	The above copyright notice and this permission notice shall be included in all copies or substantial 
 *	portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
 *	LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO 
 *	EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
 *	IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR 
 *	THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
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
