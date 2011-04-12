package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import edu.umd.cs.guitar.model.SWTApplication;
import edu.umd.cs.guitar.model.SWTApplicationStartException;
import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.SWTRipperRunner;
import edu.umd.cs.guitar.ripper.test.aut.SWTBasicApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTTwoWindowsApp;

public class SWTApplicationTest {
	
	private final static String TEST_CLASS_NAME = SWTBasicApp.class.getName();
	
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
		config.setMainClass(SWTTwoWindowsApp.class.getName());
		SWTRipper ripper = new SWTRipper(config, Thread.currentThread());
		
		
		Display display = new Display();
		Shell shell1 = new Shell(display);
		Shell shell2 = new Shell(display);
		SWTApplication swtapp = ripper.getMonitor().getApplication();
		try {
			swtapp.startGUI();
			assertEquals(2,swtapp.getAllWindow().size());
		} catch (SWTApplicationStartException e) {
			//This will get thrown
		}
		
		//new SWTRipperRunner(ripper).start();
		
	}
}
