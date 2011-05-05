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

import static org.junit.Assert.assertTrue;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.guitar.model.SitarWindow;
import edu.umd.cs.guitar.model.swtwidgets.UnknownSitarWidget;
import edu.umd.cs.guitar.model.swtwidgets.SitarWidget;
import edu.umd.cs.guitar.model.swtwidgets.SitarWidgetFactory;

/**
 * Tests for {@link UnknownSitarWidget}.
 */
public class UnknownSitarWidgetTest {
	
	private Display display;
	private SitarWidgetFactory factory = SitarWidgetFactory.INSTANCE;
	
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
	 * Dispose of the display.
	 */
	@After
	public void tearDown() {
		display.dispose();
	}
	
	/**
	 * Test {@link UnknownSitarWidget#isEnabled()}.
	 */
	@Test
	public void testIsEnabled() {
		Shell shell = new Shell(display);
		
		// Tray is a good test because it extends Widget (not Control) and we
		// don't handle it explicitly
		Tray tray = display.getSystemTray();
		
		SitarWidget widget = factory.newSWTWidget(tray, new SitarWindow(shell));
		assertTrue(widget instanceof UnknownSitarWidget);
		assertTrue(widget.isEnabled());
	}
	
	/**
	 * Test {@link UnknownSitarWidget#getChildren()}.
	 */
	@Test
	public void testGetChildren() {
		Shell shell = new Shell(display);
		Tray tray = display.getSystemTray();
		
		SitarWidget widget = factory.newSWTWidget(tray, new SitarWindow(shell));
		assertTrue(widget instanceof UnknownSitarWidget);
		assertTrue(widget.getChildren().isEmpty());
	}
}
