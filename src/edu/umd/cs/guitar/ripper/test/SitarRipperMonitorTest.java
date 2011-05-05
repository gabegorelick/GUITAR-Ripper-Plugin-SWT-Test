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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.guitar.model.SitarApplication;
import edu.umd.cs.guitar.model.SitarWindow;
import edu.umd.cs.guitar.ripper.SitarRipperConfiguration;
import edu.umd.cs.guitar.ripper.SitarRipperMonitor;

/**
 * Test for {@link SitarRipperMonitor}.
 */
public class SitarRipperMonitorTest {
	
	private Display display;
	
	/**
	 * Set up the display.
	 */
	@Before
	public void setUp() {
		if (display == null || display.isDisposed()) {
			display = new Display();
		}
	}
	
	/**
	 * Tear down the display.
	 */
	@After
	public void tearDown() {
		display.dispose();
	}
	
	/**
	 * Test {@link SitarRipperMonitor#getOpenedWindowCache()}.
	 */
	@Test
	public void testGetOpenedWindowCache() {
		SitarRipperConfiguration config = new SitarRipperConfiguration();
		SitarApplication app = new SitarApplication(config.getMainClass(), Thread.currentThread());
		
		assertTrue(new SitarRipperMonitor(config, app).getOpenedWindowCache().isEmpty());
	}
	
	/**
	 * Test {@link SitarRipperMonitor#isWindowClosed()}.
	 */
	@Test
	public void testIsWindowClosed() {
		SitarRipperConfiguration config = new SitarRipperConfiguration();
		SitarApplication app = new SitarApplication(config.getMainClass(), Thread.currentThread());
		
		assertFalse(new SitarRipperMonitor(config, app).isWindowClosed());
	}

	/**
	 * Verify
	 * {@link SitarRipperMonitor#closeWindow(edu.umd.cs.guitar.model.GWindow)}
	 * throws a {@link NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testCloseWindow() {
		SitarRipperConfiguration config = new SitarRipperConfiguration();
		SitarApplication app = new SitarApplication(config.getMainClass(), Thread.currentThread());
		
		new SitarRipperMonitor(config, app).closeWindow(null);
	}
	
	/**
	 * Test {@link SitarRipperMonitor#isIgnoredWindow(edu.umd.cs.guitar.model.GWindow)}.
	 */
	@Test
	public void testIsIgnoredWindow() {
		Shell shell = new Shell(display);
		
		SitarRipperConfiguration config = new SitarRipperConfiguration();
		SitarApplication app = new SitarApplication(config.getMainClass(), Thread.currentThread());
		SitarRipperMonitor monitor = new SitarRipperMonitor(config, app);
		
		assertFalse(monitor.isIgnoredWindow(new SitarWindow(shell)));
	}

	/**
	 * Test
	 * {@link SitarRipperMonitor#SitarRipperMonitor(SitarRipperConfiguration, SitarApplication)}
	 * with a null configuration.
	 */
	@Test
	public void testNullConfig() {
		SitarRipperConfiguration config = new SitarRipperConfiguration();
		SitarApplication app = new SitarApplication(config.getMainClass(), Thread.currentThread());
		new SitarRipperMonitor(null, app);
		
		// TODO need to add equals method to config to test this
	}
	
}
