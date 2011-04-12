package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.guitar.model.SWTWindow;
import edu.umd.cs.guitar.model.swtwidgets.SWTWidget;
import edu.umd.cs.guitar.model.swtwidgets.SWTWidgetFactory;


public class SWTWidgetTest {

	private Display display;
	private SWTWidgetFactory factory;
	
	@Before
	public void setUp() {
		factory = SWTWidgetFactory.newInstance();
		if (display == null || display.isDisposed()) {
			display = new Display();
		}
	}
	
	@After
	public void tearDown() {
		display.dispose();
	}
	
	@Test
	public void testIsTerminal() {
		final Shell shell = new Shell(display);
		Button button = new Button(shell, SWT.PUSH);
		
		SWTWidget swtButton = factory.newSWTWidget(button, new SWTWindow(shell));
		
		assertFalse(swtButton.isTerminal());
		// make sure we didn't actually close the shell
		assertFalse(shell.isDisposed());
		
		button.setText("Quit");
		assertFalse(swtButton.isTerminal());
		assertFalse(shell.isDisposed());
		
		button.setText("Close");
		assertFalse(swtButton.isTerminal());
		assertFalse(shell.isDisposed());
		
		button.setText("Exit");
		assertFalse(swtButton.isTerminal());
		assertFalse(shell.isDisposed());
		
		SelectionAdapter closeShell = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		}; 
		
		button.addSelectionListener(closeShell);
		assertTrue(swtButton.isTerminal());
		assertFalse(shell.isDisposed());
		
		button.removeSelectionListener(closeShell);
		assertFalse(swtButton.isTerminal());
		assertFalse(shell.isDisposed());
		
		// test that existing close listeners aren't notified
		final boolean[] listenerNotified = { false };
		
		ShellListener shellListener = new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				listenerNotified[0] = true;
			}
		};
		shell.addShellListener(shellListener);
		
		assertFalse(swtButton.isTerminal());
		assertFalse(listenerNotified[0]);
		
		shell.open(); // just to be sure
		shell.close();
		
		// make sure existing listeners are notified if real close happens
		assertTrue(listenerNotified[0]);
		
	}
		
}
