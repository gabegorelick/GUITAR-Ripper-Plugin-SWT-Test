package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import edu.umd.cs.guitar.model.GWindow;
import edu.umd.cs.guitar.model.SWTApplication;
import edu.umd.cs.guitar.model.SWTWindow;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;


public class SWTApplicationTest {
	@Test
	public void testGetThreadField() {
		String[] s = new String[1];
		s[0] = "http://www.google.com/";
		try {
			SWTApplication swtApp = new SWTApplication("edu.umd.cs.guitar.ripper.SWTRipperConfiguration", s);
			swtApp.getThreadField();
			swtApp = null;
		} catch (MalformedURLException e) {
			assertTrue(false);
		} catch (ClassNotFoundException e) {
			assertTrue(false);
		}
		
	}
	
	@Test
	public void testGetThread() {
		String[] s = new String[1];
		s[0] = "http://www.google.com/";
		try {
			SWTApplication swtApp = new SWTApplication("edu.umd.cs.guitar.ripper.SWTRipperConfiguration", s);
			swtApp.getThread();
			swtApp = null;
		} catch (MalformedURLException e) {
			assertTrue(false);
		} catch (ClassNotFoundException e) {
			assertTrue(false);
		}
		
	}
	
	@Test
	public void testDepreciatedConstructor() {
		try {
			SWTApplication swtApp = new SWTApplication("edu.umd.cs.guitar.ripper.SWTRipperConfiguration", 0);
			swtApp.getThread();
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
			SWTApplication swtApp = new SWTApplication("edu.umd.cs.guitar.ripper.SWTRipperConfiguration", s);
			swtApp.connect();
			swtApp = null;
		} catch (MalformedURLException e) {
			assertTrue(false);
		} catch (ClassNotFoundException e) {
			assertTrue(false);
		}
		
	}
	
	@Test
	public void testConnectArgs() {
		String[] s = new String[1];
		s[0] = "http://www.google.com/";
		try {
			String[] fakeArgs = new String[1];
			fakeArgs[0] = "fake";
			SWTApplication swtApp = new SWTApplication("edu.umd.cs.guitar.ripper.SWTRipperConfiguration", s);
			swtApp.connect(fakeArgs);
			swtApp = null;
		} catch (MalformedURLException e) {
			assertTrue(false);
		} catch (ClassNotFoundException e) {
			assertTrue(false);
		}
		
	}
	
//	@Test
//	public void testGetAllWindow() {
//		String[] s = new String[1];
//		s[0] = "http://www.google.com/";
//		try {
//			SWTApplication swtApp = new SWTApplication("edu.umd.cs.guitar.ripper.SWTRipperConfiguration", s);
//			Shell shell = new Shell();
//			SWTWindow window = new SWTWindow(shell);
//			shell.setVisible(true);
//			shell.setText("Test text");
//			Set<GWindow> allWindows = swtApp.getAllWindow();
//			swtApp = null;
//		} catch (MalformedURLException e) {
//			assertTrue(false);
//		} catch (ClassNotFoundException e) {
//			assertTrue(false);
//		}
//		
//	}
}
