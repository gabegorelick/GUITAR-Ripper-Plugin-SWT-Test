package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.guitar.model.SWTApplication;
import edu.umd.cs.guitar.model.SWTWindow;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.SWTRipperMonitor;


public class SWTRipperMonitorTest {
	
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
	public void testGetOpenedWindowCache() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		SWTApplication app = new SWTApplication(config.getMainClass(), Thread.currentThread());
		
		assertTrue(new SWTRipperMonitor(config, app).getOpenedWindowCache().isEmpty());
	}
	
	@Test
	public void testIsWindowClosed() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		SWTApplication app = new SWTApplication(config.getMainClass(), Thread.currentThread());
		
		assertFalse(new SWTRipperMonitor(config, app).isWindowClosed());
	}
	
	@Test(expected = NullPointerException.class)
	public void testCloseWindow() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		SWTApplication app = new SWTApplication(config.getMainClass(), Thread.currentThread());
		
		new SWTRipperMonitor(config, app).closeWindow(null);
	}
	
	@Test
	public void testIsIgnoredWindow() {
		Shell shell = new Shell(display);
		
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		SWTApplication app = new SWTApplication(config.getMainClass(), Thread.currentThread());
		SWTRipperMonitor monitor = new SWTRipperMonitor(config, app);
		
		assertFalse(monitor.isIgnoredWindow(new SWTWindow(shell)));
	}
		
	@Test
	public void testExpandGUI() {
		// TODO test more when we implement expandGUI
//		new SWTRipperMonitor(null).expandGUI(null);
	}
	
}
