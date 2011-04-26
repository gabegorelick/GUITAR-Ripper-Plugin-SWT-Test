package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.guitar.model.swtwidgets.SWTDecorations;
import edu.umd.cs.guitar.model.swtwidgets.SWTWidgetFactory;


public class SWTWidgetFactoryTest {

	private Display display;
	private SWTWidgetFactory factory;
	
	@Before
	public void setUp() {
		display = new Display();
		factory = SWTWidgetFactory.INSTANCE;
	}
	
	@After
	public void tearDown() {
		display.dispose();
	}
	
	@Test
	public void testGetWidgetAdapter() {		
		Shell shell = new Shell(display);
		
		assertEquals(SWTDecorations.class, factory.getWidgetAdapter(shell));
	}
		
}
