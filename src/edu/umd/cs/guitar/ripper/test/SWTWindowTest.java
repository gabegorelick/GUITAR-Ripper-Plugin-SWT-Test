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

import edu.umd.cs.guitar.model.SWTWindow;

public class SWTWindowTest {

	private Display display;
	
	@Before
	public void setUp() {
		if (display == null || display.isDisposed()) {
			display = new Display();
		}
	}
	
	@After
	public void tearDown() {
		display.dispose();
	}
	
	@Test
	public void testEmptyShell() {
		Shell shell = new Shell(display);
		SWTWindow swtWindow = new SWTWindow(shell);

		assertEquals(swtWindow.getShell(), shell);
	}

	@Test
	public void testShellTitle() {
		Shell shell = new Shell(display);

		assertEquals(shell.getClass().getName(),
				new SWTWindow(shell).getTitle());

		String shellTitle = "ShellTitle";
		shell.setText(shellTitle);
		SWTWindow swtWindow = new SWTWindow(shell);

		assertEquals(shellTitle, swtWindow.getTitle());
	}

	@Test
	public void testShellLocation() {
		Shell shell = new Shell(display);
		shell.setLocation(1, 1);
		SWTWindow swtWindow = new SWTWindow(shell);

		assertEquals(swtWindow.getX(), 0);
		assertEquals(swtWindow.getY(), 0);
	}

	@Test
	public void testShellIsValid() {
		Shell shell = new Shell(display);
		shell.setText("ShellTitle");
		shell.setVisible(true);
		SWTWindow swtWindow = new SWTWindow(shell);

		assertEquals(swtWindow.isValid(), true);
	}

	@Test
	public void testShellIsValidFalse() {
		Shell shell = new Shell(display);
		shell.setText("ShellTitle");
		shell.setVisible(false);
		SWTWindow swtWindow = new SWTWindow(shell);

		assertEquals(swtWindow.isValid(), false);
	}

	@Test
	public void testShellIsModal() {
		Shell shell = new Shell(display, SWT.APPLICATION_MODAL);
		SWTWindow swtWindow = new SWTWindow(shell);

		assertEquals(swtWindow.isModal(), true);
	}

	@Test
	public void testShellIsModalFalse() {
		Shell shell = new Shell(display);
		SWTWindow swtWindow = new SWTWindow(shell);

		assertEquals(swtWindow.isModal(), false);
	}

	@Test
	public void testEquals() {
		Shell shell1 = new Shell(display);
		SWTWindow window1 = new SWTWindow(shell1);

		assertTrue(window1.equals(window1));

		assertFalse(window1.equals(shell1));

		assertFalse(window1.equals(null));

		Shell shell2 = new Shell(display);
		SWTWindow window2 = new SWTWindow(shell2);

		assertTrue(window1.equals(window2));

		Shell shell3 = new Shell(display);
		shell3.setText("Window");
		SWTWindow window3 = new SWTWindow(shell3);
		assertFalse(window1.equals(window3));
	}

	@Test
	public void testGetGUIProperties() {
		Shell shell1 = new Shell(display);
		SWTWindow window1 = new SWTWindow(shell1);

		window1.getGUIProperties();
		// verified by integration tests
	}

	@Test
	public void testExtractGUIProperties() {
		Shell shell1 = new Shell(display);
		SWTWindow window1 = new SWTWindow(shell1);

		window1.extractGUIProperties();
		// this method never used, so don't bother verifying
	}
}
