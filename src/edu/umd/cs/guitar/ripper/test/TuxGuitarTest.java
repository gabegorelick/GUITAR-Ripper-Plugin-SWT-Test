package edu.umd.cs.guitar.ripper.test;


import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.herac.tuxguitar.gui.TGMain;
import org.junit.Ignore;
import org.junit.Test;

import edu.umd.cs.guitar.model.SitarWindow;
import edu.umd.cs.guitar.model.swtwidgets.SitarControl;
import edu.umd.cs.guitar.model.swtwidgets.SitarDecorations;
import edu.umd.cs.guitar.model.swtwidgets.SitarWidgetFactory;
import edu.umd.cs.guitar.ripper.SitarRipper;
import edu.umd.cs.guitar.ripper.SitarRipperConfiguration;
import edu.umd.cs.guitar.ripper.SitarRunner;

/**
 * Tests for TuxGuitar.
 */
public class TuxGuitarTest {

	/**
	 * Rip TuxGuitar.
	 */
	@Test
	@Ignore
	public void testRipTuxGuitar() {
		SitarRipperConfiguration config = new SitarRipperConfiguration();
		config.setInitialWaitTime(5000);
		config.setMainClass(TGMain.class.getName());
		config.setGuiFile("testoutput.xml");
		SitarRipper ripper = new SitarRipper(config);
		
		SitarWidgetFactory.INSTANCE.registerWidgetAdapter(Button.class, TuxButton.class);
		SitarWidgetFactory.INSTANCE.registerWidgetAdapter(Shell.class, TuxShell.class);
		
		new SitarRunner(ripper).run();
	}
	
	public static class TuxButton extends SitarControl {

		public TuxButton(Button button, SitarWindow window) {
			super(button, window);
		}
		
		@Override
		protected void notifyAllListeners() {
			// do nothing
		}
		
	}
		
	public static class TuxShell extends SitarDecorations {
		
		private final boolean shouldOverride;
		
		public TuxShell(Shell widget, SitarWindow window) {
			super(widget, window);
			shouldOverride = getTitle().equals("TuxGuitar - Untitled.tg"); 
		}
		
		@Override
		public void notifyAllListeners() {
			if (!shouldOverride) {
				super.notifyAllListeners();
			}
		}
		
	}
}
