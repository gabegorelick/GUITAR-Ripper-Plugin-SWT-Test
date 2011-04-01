package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.umd.cs.guitar.model.SWTApplication;
import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.SWTRipperRunner;
import edu.umd.cs.guitar.ripper.test.aut.SWTBasicApp;

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
	
	/**
	 * Test that {@link SWTApplication#connect()} doesn't throw an exception.
	 */
	@Test
	public void testConnect() {
		SWTApplication swtApp = new SWTApplication(TEST_CLASS_NAME, Thread.currentThread());
		swtApp.connect();
	}

	/**
	 * Test that {@link SWTApplication#connect(String[])} doesn't throw an exception.
	 */
	@Test
	public void testConnectArgs() {
		String[] fakeArgs = new String[1];
		fakeArgs[0] = "fake";
		SWTApplication swtApp = new SWTApplication(TEST_CLASS_NAME, Thread.currentThread());
		swtApp.connect(fakeArgs);
	}

	@Test
	public void testGetAllWindow() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setMainClass(TEST_CLASS_NAME);
		SWTRipper ripper = new SWTRipper(config, Thread.currentThread());
		new SWTRipperRunner(ripper).start();
		
		// TODO figure out how to test this if AUT is closed by this point
//		ripper.getMonitor().getApplication().getAllWindow();
	}
}
