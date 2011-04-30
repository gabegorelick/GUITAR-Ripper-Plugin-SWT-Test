package edu.umd.cs.guitar.ripper.test;

import org.herac.tuxguitar.gui.TGMain;
import org.junit.Test;

import edu.umd.cs.guitar.ripper.SWTGuitarRunner;
import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;


public class TuxGuitarTest {

	@Test
	public void testRipTuxGuitar() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setInitialWaitTime(5000);
		config.setMainClass(TGMain.class.getName());
		config.setGuiFile("testoutput.xml");
		SWTRipper ripper = new SWTRipper(config);
		new SWTGuitarRunner(ripper).run();
	}
}
