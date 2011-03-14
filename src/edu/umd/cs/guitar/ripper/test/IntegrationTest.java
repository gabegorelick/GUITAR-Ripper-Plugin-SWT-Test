package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.kohsuke.args4j.CmdLineException;

import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;

public class IntegrationTest {

	private void ripAndDiff(String filename) {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setGuiFile("testoutput.xml");

		String fullName = "edu.umd.cs.guitar.ripper.test.aut." + filename;
		config.setMainClass(fullName);
		
		SWTRipper swtRipper = new SWTRipper(config);

		try {
			swtRipper.execute();
		} catch (CmdLineException e) {
			fail(e.getMessage());
		}

		String name = "expected/" + filename + ".xml";
		diff(name, config.getGuiFile());

	}

	/**
	 * Reads and compares two files for any differences, returns the line number
	 * if different, -1 if the files are the same.
	 */
	private int diff(String file1, String file2) {
		File f1 = new File(file1);
		File f2 = new File(file2);
		BufferedReader read1 = null;
		BufferedReader read2 = null;

		try {
			read1 = new BufferedReader(new FileReader(f1));
			read2 = new BufferedReader(new FileReader(f2));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		int i = 0;
		String s1 = new String();
		String s2 = new String();

		boolean equal = true;
		try {
			while (read1.ready()) {
				s1 = read1.readLine();
				if (read2.ready()) {
					s2 = read2.readLine();
				} else {
					equal = false;
					break;
				}
				if (!s1.equals(s2)) {
					if (!s1.contains("Font")) {
						System.out.println(i);
						System.out.println(s1);
						System.out.println(s2);
						equal = false;
						break;
					}
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (equal) {
			return -1;
		} else {
			return i;
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

}
