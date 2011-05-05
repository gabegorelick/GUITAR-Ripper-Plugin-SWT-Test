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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.guitar.model.SitarWindow;

/**
 * Tests for {@link SitarWindow}.
 */
public class SitarWindowTest {

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
	 * Dispose of the display.
	 */
	@After
	public void tearDown() {
		display.dispose();
	}
	
	/**
	 * Test {@link SitarWindow#getShell()}.
	 */
	@Test
	public void testEmptyShell() {
		Shell shell = new Shell(display);
		SitarWindow swtWindow = new SitarWindow(shell);

		assertEquals(swtWindow.getShell(), shell);
	}

	/**
	 * Test {@link SitarWindow#getTitle()}.
	 */
	@Test
	public void testShellTitle() {
		Shell shell = new Shell(display);

		assertEquals(shell.getClass().getName(),
				new SitarWindow(shell).getTitle());

		String shellTitle = "ShellTitle";
		shell.setText(shellTitle);
		SitarWindow swtWindow = new SitarWindow(shell);

		assertEquals(shellTitle, swtWindow.getTitle());
	}

	/**
	 * Test {@link SitarWindow#getX()} and {@link SitarWindow#getY()}.
	 */
	@Test
	public void testShellLocation() {
		Shell shell = new Shell(display);
		shell.setLocation(1, 1);
		SitarWindow swtWindow = new SitarWindow(shell);

		assertEquals(swtWindow.getX(), 0);
		assertEquals(swtWindow.getY(), 0);
	}

	/**
	 * Test {@link SitarWindow#isValid()}.
	 */
	@Test
	public void testShellIsValid() {
		Shell shell = new Shell(display);
		shell.setText("ShellTitle");
		shell.setVisible(true);
		SitarWindow swtWindow = new SitarWindow(shell);

		assertEquals(swtWindow.isValid(), true);
	}

	/**
	 * Test {@link SitarWindow#isValid()} when shell isn't visible.
	 */
	@Test
	public void testShellIsValidFalse() {
		Shell shell = new Shell(display);
		shell.setText("ShellTitle");
		shell.setVisible(false);
		SitarWindow swtWindow = new SitarWindow(shell);

		assertEquals(swtWindow.isValid(), false);
	}

	/**
	 * Test {@link SitarWindow#isModal()}.
	 */
	@Test
	public void testShellIsModal() {
		Shell shell = new Shell(display, SWT.APPLICATION_MODAL);
		SitarWindow swtWindow = new SitarWindow(shell);

		assertEquals(swtWindow.isModal(), true);
	}

	/**
	 * Test {@link SitarWindow#isModal()} when it shouldn't be.
	 */
	@Test
	public void testShellIsModalFalse() {
		Shell shell = new Shell(display);
		SitarWindow swtWindow = new SitarWindow(shell);

		assertEquals(swtWindow.isModal(), false);
	}

	/**
	 * Test {@link SitarWindow#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		Shell shell1 = new Shell(display);
		SitarWindow window1 = new SitarWindow(shell1);

		assertTrue(window1.equals(window1));

		assertFalse(window1.equals(shell1));

		assertFalse(window1.equals(null));

		Shell shell2 = new Shell(display);
		SitarWindow window2 = new SitarWindow(shell2);

		assertTrue(window1.equals(window2));

		Shell shell3 = new Shell(display);
		shell3.setText("Window");
		SitarWindow window3 = new SitarWindow(shell3);
		assertFalse(window1.equals(window3));
	}

	/**
	 * Test {@link SitarWindow#getGUIProperties()}.
	 */
	@Test
	public void testGetGUIProperties() {
		Shell shell1 = new Shell(display);
		SitarWindow window1 = new SitarWindow(shell1);

		window1.getGUIProperties();
		// verified by integration tests
	}

	/**
	 * Test {@link SitarWindow#extractGUIProperties()}.
	 */
	@Test
	public void testExtractGUIProperties() {
		Shell shell1 = new Shell(display);
		SitarWindow window1 = new SitarWindow(shell1);

		window1.extractGUIProperties();
		// this method never used, so don't bother verifying
	}
}
