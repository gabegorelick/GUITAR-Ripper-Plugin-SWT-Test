package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import edu.umd.cs.guitar.model.SWTWindow;

public class SWTWindowTest {
	
	@Test
	public void testEmptyShell() {
		Display display = new Display();
		Shell shell = new Shell(display);
		SWTWindow swtWindow = new SWTWindow(shell);
		
		assertEquals(swtWindow.getShell(), shell);
		
		display.dispose();
	}

	@Test
	public void testShellTitle() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("ShellTitle");
		SWTWindow swtWindow = new SWTWindow(shell);
		
		assertEquals(swtWindow.getTitle(), "ShellTitle");
		
		display.dispose();
	}

	@Test
	public void testShellLocation() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLocation(1, 1);
		SWTWindow swtWindow = new SWTWindow(shell);
		
		assertEquals(swtWindow.getX(), 1);
		assertEquals(swtWindow.getY(), 1);
		
		display.dispose();
	}

	@Test
	public void testShellIsValid() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("ShellTitle");
		shell.setVisible(true);
		SWTWindow swtWindow = new SWTWindow(shell);
		
		assertEquals(swtWindow.isValid(), true);
		
		display.dispose();
	}

	@Test
	public void testShellIsValidFalse() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("ShellTitle");
		shell.setVisible(false);
		SWTWindow swtWindow = new SWTWindow(shell);
		
		assertEquals(swtWindow.isValid(), false);
		
		display.dispose();
	}

	@Test
	public void testShellIsModal() {
		Display display = new Display();
		Shell shell = new Shell(display, SWT.APPLICATION_MODAL);
		SWTWindow swtWindow = new SWTWindow(shell);
		
		assertEquals(swtWindow.isModal(), true);
		
		display.dispose();
	}

	@Test
	public void testShellIsModalFalse() {
		Display display = new Display();
		Shell shell = new Shell(display);
		SWTWindow swtWindow = new SWTWindow(shell);
		
		assertEquals(swtWindow.isModal(), false);
		
		display.dispose();
	}
	
	@Test
	public void testEquals(){
		Display display = new Display();
		Shell shell1 = new Shell(display);
		SWTWindow window1 = new SWTWindow(shell1);
		
		assertTrue(window1.equals(window1));
		
		assertFalse(window1.equals(shell1));
		
		assertFalse(window1.equals(null));
		
		Shell shell2 = new Shell(display);
		SWTWindow window2 = new SWTWindow(shell2);
		
		assertTrue(window1.equals(window2));
		
		SWTWindow nullShell = new SWTWindow(null);
		assertFalse(nullShell.equals(window1));
		
		Shell shell3 = new Shell(display);
		shell3.setText("Window");
		SWTWindow window3 = new SWTWindow(shell3);
		assertFalse(window1.equals(window3));
		
		display.dispose();
	}
	
	@Test
	public void testGetContainer(){
		Display display = new Display();
		Shell shell1 = new Shell(display);
		SWTWindow window1 = new SWTWindow(shell1);
		
		window1.getContainer();
		display.dispose();
	}
	
	@Test
	public void testGetGUIProperties(){
		Display display = new Display();
		Shell shell1 = new Shell(display);
		SWTWindow window1 = new SWTWindow(shell1);
		
		window1.getGUIProperties();
		display.dispose();
	}
	
	@Test
	public void testExtractGUIProperties(){
		Display display = new Display();
		Shell shell1 = new Shell(display);
		SWTWindow window1 = new SWTWindow(shell1);
		
		window1.extractGUIProperties();
		display.dispose();
	}
}
