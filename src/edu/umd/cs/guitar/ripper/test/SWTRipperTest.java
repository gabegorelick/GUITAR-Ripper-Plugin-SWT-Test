package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.kohsuke.args4j.CmdLineException;

import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;

public class SWTRipperTest {

	@Test
	public void testSWTRipper() {
		SWTRipper ripper = new SWTRipper(null, Thread.currentThread());
		assertNotNull(ripper.getMonitor());

		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setMainClass("edu.umd.cs.guitar.ripper.test.aut.SWTHelloWorld");

		final SWTRipper swtRipper = new SWTRipper(config, Thread.currentThread());

		try {
			swtRipper.execute();
		} catch (CmdLineException e) {
		e.printStackTrace();
		}
	}

	@Test
	public void testEventList() {

	}
}
