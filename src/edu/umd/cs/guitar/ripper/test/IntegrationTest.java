package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.SWTRipperRunner;

public class IntegrationTest {

	public static void ripAndDiff(String filename) {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setGuiFile("testoutput.xml");

		String fullName = "edu.umd.cs.guitar.ripper.test.aut." + filename;
		config.setMainClass(fullName);
		
		final SWTRipper swtRipper = new SWTRipper(config, Thread.currentThread());
		new SWTRipperRunner(swtRipper).start();
				
		String name = "expected/" + filename + ".xml";
		assertEquals(-1, diff(name, config.getGuiFile()));

	}

	/**
	 * Reads and compares two files for any differences, returns the line number
	 * if different, -1 if the files are the same.
	 */
	private static int diff(String expectedFile, String actualFile) {
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
		String expectedString;
		String actualString;
		
		String lastLine = null;
		
		boolean equal = true;
		try { // TODO this doesn't work if one of the files is empty
			while (expectedReader.ready()) {
				expectedString = expectedReader.readLine();
				if (actualReader.ready()) {
					actualString = actualReader.readLine();
				} else {
					equal = false;
					break;
				}
				
				// ignore if this is X or Y value
				// TODO use XMLUnit to do this
				if (!expectedString.equals(actualString)) {
					if (lastLine != null 
							&& !lastLine.contains("<Name>X</Name>")
							&& !lastLine.contains("<Name>Y</Name>")) {
					
						System.err.println("Failed at line " + lineNumber);
						System.err.println("Expected: " + expectedString);
						System.err.println("Actual: " + actualString);
						equal = false;
						break;
					}
				}
				lineNumber++;
				lastLine = actualString;
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
		ripAndDiff("SWTTwoWindowsApp");
	}

	@Test
	public void testWindowApp() {
		ripAndDiff("SWTWindowApp");
	}
	
//	@Test
//	public void testMultiWindowDynamicApp() {
//		ripAndDiff("SWTMultiWindowDynamicApp");
//	}
	
	@Test
	public void testTabFolderApp() {
		ripAndDiff("SWTTabFolderApp");
	}
	
	@Test
	public void testTableApp() {
		ripAndDiff("SWTTableApp");
	}
	
	@Test
	public void testTreeApp() {
		ripAndDiff("SWTTreeApp");
	}
	
	@Test
	public void testToolbarApp() {
		ripAndDiff("SWTToolbarApp");
	}

}
