package edu.umd.cs.guitar.ripper.test;

import org.junit.Test;
import org.kohsuke.args4j.CmdLineException;

import edu.umd.cs.guitar.ripper.SWTRipperMain;

public class SWTRipperMainTest {

	@Test
	public void testMainNoArgs() {
		SWTRipperMain.main(new String[0]);
		// TODO test output by redirecting stdout?
	}

	@Test(expected = NullPointerException.class) // TODO this should fail with ClassNotFound
	public void testMain() {
		SWTRipperMain.main(new String[] {"-c", "NOSUCHCLASS"});
	}
}
