package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.kohsuke.args4j.CmdLineException;

import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.SWTRipperMonitor;

public class IntegrationTest {

	private void ripAndDiff(String filename) {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setGuiFile("testoutput.xml");

		String fullName = "edu.umd.cs.guitar.ripper.test.aut." + filename;
		config.setMainClass(fullName);
		
		final SWTRipper swtRipper = new SWTRipper(config, Thread.currentThread());

		Thread ripperThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					swtRipper.execute();
				} catch (CmdLineException e) {
					e.printStackTrace();
				}
			}
		});
		
		ripperThread.setName("ripper-thread");
		ripperThread.start();
		SWTRipperMonitor monitor = (SWTRipperMonitor) swtRipper.getMonitor();
		
		monitor.getApplication().startGUI(); // start GUI on main thread, this blocks until GUI terminates
		try {
			// we don't want the main thread closing (and thus the JVM) before the ripper is done  
			ripperThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String name = "expected/" + filename + ".xml";
		assertEquals(-1, diff(name, config.getGuiFile()));

	}

	/**
	 * Reads and compares two files for any differences, returns the line number
	 * if different, -1 if the files are the same.
	 */
	private int diff(String expectedFile, String actualFile) {
		File expected = new File(expectedFile);
		File actual = new File(actualFile);
		BufferedReader expectedReader = null;
		BufferedReader actualReader = null;

		try {
			expectedReader = new BufferedReader(new FileReader(expected));
			actualReader = new BufferedReader(new FileReader(actual));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		int lineNumber = 1;
		String s1 = new String();
		String s2 = new String();

		boolean equal = true;
		try {
			while (expectedReader.ready()) {
				s1 = expectedReader.readLine();
				if (actualReader.ready()) {
					s2 = actualReader.readLine();
				} else {
					equal = false;
					break;
				}
				if (!s1.equals(s2)) {
					System.err.println("Failed at line " + lineNumber);
					System.err.println("Expected: " + s1);
					System.err.println("Actual: " + s2);
					equal = false;
					break;
				}
				lineNumber++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (equal) {
			return -1;
		} else {
			return lineNumber;
		}

	}

	@Test
	public void testBasicApp() {
		ripAndDiff("SWTBasicApp");
	}

	@Test
	public void testButtonApp() {
		ripAndDiff("SWTButtonApp");
	}

	@Test
	public void testCheckButtonApp() {
		ripAndDiff("SWTCheckButtonApp");
	}

	@Test
	public void testHelloWorld() {
		ripAndDiff("SWTHelloWorld");
	}

	@Test
	public void testLabelApp() {
		ripAndDiff("SWTLabelApp");
	}

	@Test
	public void testListApp() {
		ripAndDiff("SWTListApp");
	}

	@Test
	public void testMenuBarApp() {
		ripAndDiff("SWTMenuBarApp");
	}

	@Test
	public void testTwoWindowsApp() {
		ripAndDiff("SWTTwoWindowsApp"); // FIXME can't rip subcomponents of multi-window apps
	}

	@Test
	public void testWindowApp() {
		ripAndDiff("SWTWindowApp");
	}

}
