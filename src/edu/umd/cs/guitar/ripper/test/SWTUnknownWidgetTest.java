package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertTrue;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.guitar.model.SWTWindow;
import edu.umd.cs.guitar.model.swtwidgets.SWTUnknownWidget;
import edu.umd.cs.guitar.model.swtwidgets.SWTWidget;
import edu.umd.cs.guitar.model.swtwidgets.SWTWidgetFactory;


public class SWTUnknownWidgetTest {
	
	private Display display;
	private SWTWidgetFactory factory = SWTWidgetFactory.newInstance();
	
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
	public void testIsEnabled() {
		Shell shell = new Shell(display);
		
		// Tray is a good test because it extends Widget (not Control) and we
		// don't handle it explicitly
		Tray tray = display.getSystemTray();
		
		SWTWidget widget = factory.newSWTWidget(tray, new SWTWindow(shell));
		assertTrue(widget instanceof SWTUnknownWidget);
		assertTrue(widget.isEnabled());
	}
	
	@Test
	public void testGetChildren() {
		Shell shell = new Shell(display);
		Tray tray = display.getSystemTray();
		
		SWTWidget widget = factory.newSWTWidget(tray, new SWTWindow(shell));
		assertTrue(widget instanceof SWTUnknownWidget);
		assertTrue(widget.getChildren().isEmpty());
	}
}
