package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import edu.umd.cs.guitar.ripper.SWTApplicationRunner;
import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.test.aut.SWTHelloWorld;

public class SWTRipperTest {

	@Test
	public void testSWTRipper() {
		SWTRipper ripper = new SWTRipper(null, Thread.currentThread());
		assertNotNull(ripper.getMonitor());
		
		SWTRipperConfiguration config = new SWTRipperConfiguration();
        config.setMainClass(SWTHelloWorld.class.getName());
        
        SWTRipper swtRipper = new SWTRipper(config, Thread.currentThread());
        new SWTApplicationRunner(swtRipper);
	}
	
}
