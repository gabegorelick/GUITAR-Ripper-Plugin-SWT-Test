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

public class SWTRipperConfigurationTest {

	@Test
	public void testSetLogFile() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setLogFile("foo");
		assertEquals("foo", config.getLogFile());
	}
	
	@Test
	public void testSetLogWidgetFile() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setLogWidgetFile("foo");
		assertEquals("foo", config.getLogWidgetFile());
	}
	
	@Test
	public void testSetInitialWaitingTime() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setInitialWaitingTime(100);
		assertEquals(100, config.getInitialWaitingTime());
	}
	
	@Test
	public void testSetJvmOptions() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setJvmOptions("foo");
		assertEquals("foo", config.getJvmOptions());
	}
	
	@Test
	public void testSetCustomizedEventList() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setCustomizedEventList("foo");
		assertEquals("foo", config.getCustomizedEventList());
	}
	
	/*
	 * CURRENT STATE: Functioning, xml hierarchy is dependent on argument input.
	 */
	@Test
	public void testArguments() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setMainClass("edu.umd.cs.guitar.ripper.test.aut.SWTArgumentApp");
		config.setGuiFile("testoutput1.xml");
		config.setInitialWaitingTime(700);
		config.setArgumentList("one:1");

		SWTRipper swtRipper = new SWTRipper(config);

		try {
			swtRipper.execute();
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
		}

		// assertEquals(-1,
		// diff("testfiles/ArgumentApp.xml","testoutput1.xml"));

	}

	/*
	 * CURRENT STATE: In SWTApplication it appears URLs are only added if the
	 * (file:,jar:,http:) prefix is at the beginning of the URL, but the current
	 * urlList delimiter is ':'. As far as I can tell, no URL added by
	 * setUrlList will be in the right form.
	 */
	@Test
	public void testURLs() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setMainClass("edu.umd.cs.guitar.ripper.test.aut.SWTURLApp");
		config.setGuiFile("testoutput2.xml");
		config.setUrlList("file:testfiles/utest.txt");

		final SWTRipper swtRipper = new SWTRipper(config);

		try {
			swtRipper.execute();
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
		}

		// assertEquals(-1,diff("testfiles/SWTURLApp.xml","testoutput2.xml"));

	}

	/*
	 * CURRENT STATE: showversion/fullversion cause no apparent response. Can't
	 * find if the jvm options are sent anywhere else (never referenced in
	 * SWTRipperMonitor).
	 */
	@Test
	public void testJVMOptions() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setMainClass("edu.umd.cs.guitar.ripper.test.aut.SWTBasicApp");
		config.setGuiFile("testoutput3.xml");
		config.setJvmOptions("-showversion");

		final SWTRipper swtRipper = new SWTRipper(config);

		try {
			swtRipper.execute();
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
		}

		// assertEquals(-1,diff("testfiles/BasicApp.xml","testoutput3.xml"));

	}

	/*
	 * CURRENT STATE: It does something. Telling it to ignore a button and it
	 * seems to leave out a lot of the xml.
	 */
	@Test
	public void testConfigureFile() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setMainClass("edu.umd.cs.guitar.ripper.test.aut.SWTBasicApp");
		config.setGuiFile("testoutput4.xml");
		config.setConfigFile("testconfig.xml");

		final SWTRipper swtRipper = new SWTRipper(config);

		try {
			swtRipper.execute();
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
		}

		// assertEquals(-1,diff("testfiles/ConfigureFile.xml","testoutput4.xml"));

	}

	/*
	 * Reads and compares two files for any differences, returns the line number
	 * if different, -1 if the files are the same.
	 */
	// TODO merge with other diff method
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
						equal = false;
						System.out.println("Expected: " + s1);
						System.out.println("Actual: " + s2);
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
		} else
			return i;

	}
}
