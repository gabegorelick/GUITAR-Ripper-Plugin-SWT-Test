package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.util.Set;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import edu.umd.cs.guitar.exception.ApplicationConnectException;
import edu.umd.cs.guitar.model.GWindow;
import edu.umd.cs.guitar.model.SWTApplication;

public class SWTApplicationTest {
	
	@Test
	public void testAppThread() {
		String[] s = new String[1];
		s[0] = "http://www.google.com/";
		try {
			SWTApplication swtApp = new SWTApplication(
					"edu.umd.cs.guitar.ripper.test.aut.SWTBasicApp", s, Thread.currentThread());
			Thread thr = swtApp.getAppThread();
			assertEquals("main",thr.getName());
			swtApp = null;
		} catch (MalformedURLException e) {
			assertTrue(false);
		} catch (ClassNotFoundException e) {
			assertTrue(false);
		}

	}

	@Test
	public void testDeprecatedConstructor() {
		try {
			@SuppressWarnings("deprecation")
			// duh, we're only testing it, silly compiler
			SWTApplication swtApp = new SWTApplication(
					"edu.umd.cs.guitar.ripper.test.aut.SWTBasicApp", 0);
			
			assertEquals(null,swtApp.getAppThread());
			swtApp = null;
		} catch (ClassNotFoundException e) {
			assertTrue(false);
		}
	}

	@Test
	public void testConnect() {
		String[] s = new String[1];
		s[0] = "http://www.google.com/";
		try {
			SWTApplication swtApp = new SWTApplication(
					"edu.umd.cs.guitar.ripper.test.aut.SWTBasicApp", s, Thread.currentThread());
			swtApp.connect();
			swtApp = null;
		} catch (MalformedURLException e) {
			assertTrue(false);
		} catch (ClassNotFoundException e) {
			assertTrue(false);
		} catch (ApplicationConnectException e){
			assertTrue(false);
		}

	}

	@Test
	//connect method commented out, does this do anything anymore?
	public void testConnectArgs() {
		String[] s = new String[1];
		s[0] = "http://www.google.com/";
		try {
			String[] fakeArgs = new String[1];
			fakeArgs[0] = "fake";
			SWTApplication swtApp = new SWTApplication(
					"edu.umd.cs.guitar.ripper.test.aut.SWTBasicApp", s, Thread.currentThread());
			swtApp.connect(fakeArgs);
			
			swtApp = null;
		} catch (MalformedURLException e) {
			fail(e.getMessage());
		} catch (ClassNotFoundException e) {
			fail(e.getMessage());
		} catch (ApplicationConnectException e){
			assertTrue(false);
		}

	}

	@Test
	 //TODO still needs work
	public void testGetAllWindow() {
		String[] s = { "http://www.google.com/" };
		Display display = new Display();
		
		Shell shell = new Shell(display);
		Shell shell2 = new Shell(display);
		shell.setVisible(true);
		shell.setText("Test text");

		try {
			
			SWTApplication swtApp = new
			SWTApplication("edu.umd.cs.guitar.ripper.test.aut.SWTTwoWindowsApp", s,Thread.currentThread());
			Set<GWindow> windows = swtApp.getAllWindow();
			//should eventually get 2 windows
			assertEquals(2,windows.size());

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		} catch (ClassNotFoundException e) {
			fail(e.getMessage());
		} finally {
			display.dispose();
		}

	}
}
