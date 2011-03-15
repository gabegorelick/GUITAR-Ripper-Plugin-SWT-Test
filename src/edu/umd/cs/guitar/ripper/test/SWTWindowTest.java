package edu.umd.cs.guitar.ripper.test;

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

}
