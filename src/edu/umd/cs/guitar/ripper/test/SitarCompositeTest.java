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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.guitar.model.GUITARConstants;
import edu.umd.cs.guitar.model.SitarWindow;
import edu.umd.cs.guitar.model.swtwidgets.SitarComposite;
import edu.umd.cs.guitar.model.swtwidgets.SitarWidgetFactory;

/**
 * Tests for {@link SitarComposite}.
 */
public class SitarCompositeTest {

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
	 * Dispose the display.
	 */
	@After
	public void tearDown() {
		display.dispose();
	}
	
	/**
	 * Test {@link SitarComposite#getWidget()}.
	 */
	@Test
	public void testGetWidget() {
		Shell shell = new Shell(display);
		Spinner spinner = new Spinner(shell, SWT.NONE);
				
		SitarComposite comp = (SitarComposite) SitarWidgetFactory.INSTANCE.newSWTWidget(spinner, null);
		assertEquals(spinner, comp.getWidget());
	}
	
	/**
	 * Test {@link SitarComposite#getX()}.
	 */
	@Test
	public void testGetX() {
		SitarWidgetFactory factory = SitarWidgetFactory.INSTANCE;
		
		Shell shell = new Shell(display);
		Spinner spinner = new Spinner(shell, SWT.NONE);
		
		assertEquals(0, factory.newSWTWidget(spinner, null).getX());
				
		spinner.setBounds(85, 110, 80, 30);
		assertEquals(85, factory.newSWTWidget(spinner, null).getX());
	}
	
	/**
	 * Test {@link SitarComposite#getY()}.
	 */
	@Test
	public void testGetY() {
		SitarWidgetFactory factory = SitarWidgetFactory.INSTANCE;
		
		Shell shell = new Shell(display);
		Spinner spinner = new Spinner(shell, SWT.NONE);
		
		assertEquals(0, factory.newSWTWidget(spinner, null).getY());
				
		spinner.setBounds(85, 110, 80, 30);
		assertEquals(110, factory.newSWTWidget(spinner, null).getY());
	}
	
	/**
	 * Test {@link SitarComposite#getGUIProperties()}.
	 */
	@Test
	public void testGetGUIProperties() {
		SitarWidgetFactory factory = SitarWidgetFactory.INSTANCE;
		
		Shell shell = new Shell(display);
		Spinner spinner = new Spinner(shell, SWT.NONE);
				
		factory.newSWTWidget(spinner, null).getGUIProperties();
		
		shell.setImage(new Image(display, 20, 20));
		factory.newSWTWidget(spinner, null).getGUIProperties();
		
		// verification occurs in integration tests
	}
	
	/**
	 * Test {@link SitarComposite#getClassVal()}.
	 */
	@Test
	public void testGetClassVal() {
		SitarWidgetFactory factory = SitarWidgetFactory.INSTANCE;
		
		Shell shell = new Shell(display);
		Spinner spinner = new Spinner(shell, SWT.NONE);
		
		assertEquals(spinner.getClass().getName(), factory.newSWTWidget(spinner, null).getClassVal());
	}
	
	/**
	 * Test {@link SitarComposite#getParent()}.
	 */
	@Test
	public void testGetParent() {
		SitarWidgetFactory factory = SitarWidgetFactory.INSTANCE;
		
		Shell shell = new Shell(display);
		Spinner spinner = new Spinner(shell, SWT.NONE);
		
		SitarComposite parent = (SitarComposite) factory.newSWTWidget(spinner, null).getParent();
		assertEquals(shell, parent.getWidget());
	}
	
	/**
	 * Test {@link SitarComposite#isEnable()}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testIsEnable() {
		SitarWidgetFactory factory = SitarWidgetFactory.INSTANCE;
		
		Shell shell = new Shell(display);
		Spinner spinner = new Spinner(shell, SWT.NONE);
		
		assertTrue(factory.newSWTWidget(spinner, null).isEnable());
		
		spinner.setEnabled(false);
		assertFalse(factory.newSWTWidget(spinner, null).isEnable());
		
		spinner.setEnabled(true);
		assertTrue(factory.newSWTWidget(spinner, null).isEnable());
		
		// make sure disabling parent disables child
		shell.setEnabled(false);
		assertFalse(factory.newSWTWidget(spinner, null).isEnable());
	}
	
	/**
	 * Test {@link SitarComposite#getTypeVal()}.
	 */
	@Test
	public void testGetTypeVal() {
		SitarWidgetFactory factory = SitarWidgetFactory.INSTANCE;
		
		Shell shell = new Shell(display);
		SitarWindow window = new SitarWindow(shell);
		Spinner spinner = new Spinner(shell, SWT.NONE);
		
		assertEquals(GUITARConstants.SYSTEM_INTERACTION, 
				factory.newSWTWidget(spinner, window).getTypeVal());
	}
	
}
