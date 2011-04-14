package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.guitar.model.SWTApplication;
import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.test.aut.SWTBasicApp;

public class SWTApplicationTest {
	
	private final static String TEST_CLASS_NAME = SWTBasicApp.class.getName();
	
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
	
	/**
	 * Test that the application is set to run on the <code>main</code> thread.
	 */
	@Test
	public void testAppThread() {
		SWTApplication swtApp = new SWTApplication(TEST_CLASS_NAME, Thread.currentThread());
		Thread thr = swtApp.getAppThread();
		assertEquals("main", thr.getName());
	}
	
	@Test
	public void testGetAllWindow() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setMainClass(TEST_CLASS_NAME);
		SWTRipper ripper = new SWTRipper(config, Thread.currentThread());
		
		Shell shell1 = new Shell(display);
		Shell shell2 = new Shell(display);
		shell1.setVisible(true);
		shell2.setVisible(true);
		SWTApplication app = ripper.getMonitor().getApplication();
		app.connect();
		
		assertEquals(2, app.getAllWindow().size());
		
		shell2.dispose();
		assertEquals(1, app.getAllWindow().size());
		
		shell1.setVisible(false);
		assertEquals(0, app.getAllWindow().size());
		
		shell1.setVisible(true);
		assertEquals(1, app.getAllWindow().size());
		
		shell1.dispose();
		assertEquals(0, app.getAllWindow().size());
	}
}
