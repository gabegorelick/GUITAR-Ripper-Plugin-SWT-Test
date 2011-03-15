package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import edu.umd.cs.guitar.model.SWTWindow;

public class SWTWindowTest {

	@Test
	public void testEmptyShell() {
		Shell shell = new Shell();
		SWTWindow swtWindow = new SWTWindow(shell);
		assertEquals(swtWindow.getShell(), shell);
	}

	@Test
	public void testShellTitle() {
		Shell shell = new Shell();
		shell.setText("ShellTitle");
		SWTWindow swtWindow = new SWTWindow(shell);
		assertEquals(swtWindow.getTitle(), "ShellTitle");
	}

	@Test
	public void testShellLocation() {
		Shell shell = new Shell();
		shell.setLocation(1, 1);
		SWTWindow swtWindow = new SWTWindow(shell);
		assertEquals(swtWindow.getX(), 1);
		assertEquals(swtWindow.getY(), 1);
	}

	public void testShellIsValid() {
		Shell shell = new Shell();
		shell.setText("ShellTitle");
		shell.setVisible(true);
		SWTWindow swtWindow = new SWTWindow(shell);
		assertEquals(swtWindow.isValid(), true);
	}

	public void testShellIsValidFalse() {
		Shell shell = new Shell();
		shell.setText("ShellTitle");
		shell.setVisible(false);
		SWTWindow swtWindow = new SWTWindow(shell);
		assertEquals(swtWindow.isValid(), false);
	}

	public void testShellIsModal() {
		Shell shell = new Shell(SWT.APPLICATION_MODAL);
		SWTWindow swtWindow = new SWTWindow(shell);
		assertEquals(swtWindow.isModal(), true);
	}

	public void testShellIsModalFalse() {
		Shell shell = new Shell();
		SWTWindow swtWindow = new SWTWindow(shell);
		assertEquals(swtWindow.isModal(), false);
	}

}
