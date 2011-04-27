package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceListener;
import org.custommonkey.xmlunit.NodeDetail;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import edu.umd.cs.guitar.model.GUITARConstants;
import edu.umd.cs.guitar.ripper.SWTApplicationRunner;
import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.test.aut.SWTBasicApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTButtonApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTCheckButtonApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTHelloWorld;
import edu.umd.cs.guitar.ripper.test.aut.SWTLabelApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTListApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTMenuBarApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTMultiWindowDynamicApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTTabFolderApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTTableApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTToolbarApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTTreeApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTTwoWindowsApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTWindowApp;
import edu.umd.cs.guitar.util.GUITARLog;

public class IntegrationTest {
	
	private static final String DEFAULT_GUI_FILENAME = "testoutput.xml";
	
	/**
	 * Run the ripper on the given class. Returns the name of the GUI structure
	 * output file.
	 * 
	 * @param clazz the class to rip
	 * @return the name of the output file
	 */
	private static String rip(Class<?> clazz) {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setGuiFile(DEFAULT_GUI_FILENAME);
		config.setMainClass(clazz.getName());
		
		SWTRipper swtRipper = new SWTRipper(config, Thread.currentThread());
		new SWTApplicationRunner(swtRipper).run();
		
		return config.getGuiFile();
	}
		
	private static void diff(String expectedFilename, String actualFilename) {
		XMLUnit.setNormalizeWhitespace(true);
		
		Document actual;
		Document expected;
		try {
			// also called controlDoc by XMLUnit
			expected = XMLUnit.buildTestDocument(new InputSource(new FileReader(expectedFilename)));
			
			// also called expectedDoc by XMLUnit
			actual = XMLUnit.buildControlDocument(new InputSource(new FileReader(actualFilename)));
		} catch (SAXException e) {
			// so calling methods don't have to declare this as checked exception
			throw new AssertionError(e);
		} catch (IOException e) {
			throw new AssertionError(e);
		}
		
		DetailedDiff diff = new DetailedDiff(new Diff(expected, actual));
		
		diff.overrideDifferenceListener(new DifferenceListener() {
			@Override
			public void skippedComparison(Node control, Node test) {
				// do nothing				
			}
			
			@Override
			public int differenceFound(Difference difference) {				
				NodeDetail actualNodeDetail = difference.getTestNodeDetail();
				
				try {
					Node valueNode = actualNodeDetail.getNode().getParentNode();
					Node nameNode = valueNode.getPreviousSibling().getPreviousSibling();
					Node propNode = nameNode.getFirstChild();
					String prop = propNode.getTextContent();
					
					if (prop.equals(GUITARConstants.X_TAG_NAME)
							|| prop.equals(GUITARConstants.Y_TAG_NAME)
							|| prop.equals("width")) {
						
						StringBuilder builder = new StringBuilder();
						builder.append("Ignoring bad ").append(prop);
						builder.append(" value (expected ");
						builder.append(difference.getControlNodeDetail().getValue());
						builder.append(" but got ");
						builder.append(actualNodeDetail.getValue()).append(")");
						
						GUITARLog.log.info(builder);

						return DifferenceListener.RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
					}
				} catch (NullPointerException e) {
					GUITARLog.log.warn("Unexpected GUI structure");									
					return DifferenceListener.RETURN_ACCEPT_DIFFERENCE;
				} 
				
				return DifferenceListener.RETURN_ACCEPT_DIFFERENCE;
			}
		});
		
		for (Object o : diff.getAllDifferences()) {
			GUITARLog.log.warn(o);
		}
		
		assertTrue(diff.similar());
	}
	
	private static String getExpectedFilename(Class<?> clazz) {
		return "expected/" + clazz.getSimpleName() + ".xml";
	}
	
	private static void ripAndDiff(Class<?> clazz) {
		diff(getExpectedFilename(clazz), rip(clazz));
	}
	
	@Test
	public void testBasicApp() {
		ripAndDiff(SWTBasicApp.class);
	}

	@Test
	public void testButtonApp() {
		ripAndDiff(SWTButtonApp.class);
	}

	@Test
	public void testCheckButtonApp() {
		ripAndDiff(SWTCheckButtonApp.class);
	}

	@Test
	public void testHelloWorld() {
		ripAndDiff(SWTHelloWorld.class);
	}

	@Test
	public void testLabelApp() {
		ripAndDiff(SWTLabelApp.class);
	}

	@Test
	public void testListApp() {
		ripAndDiff(SWTListApp.class);
	}

	@Test
	public void testMenuBarApp() {
		ripAndDiff(SWTMenuBarApp.class);
	}

	@Test
	public void testTwoWindowsApp() {
		ripAndDiff(SWTTwoWindowsApp.class);
	}

	@Test
	public void testWindowApp() {
		ripAndDiff(SWTWindowApp.class);
	}
		
	@Test
	public void testTabFolderApp() {
		ripAndDiff(SWTTabFolderApp.class);
	}
	
	@Test
	public void testTableApp() {
		ripAndDiff(SWTTableApp.class);
	}
	
	@Test
	public void testTreeApp() {
		ripAndDiff(SWTTreeApp.class);
	}
	
	@Test
	public void testToolbarApp() {
		ripAndDiff(SWTToolbarApp.class);
	}
	
	@Test
	public void testMultiWindowDynamicApp() {
		ripAndDiff(SWTMultiWindowDynamicApp.class);
	}
	
	@Test
	public void testIgnoreComponents() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setGuiFile(DEFAULT_GUI_FILENAME);
		config.setConfigFile("testconfig.xml");
		config.setMainClass(SWTListApp.class.getName());
		
		SWTRipper swtRipper = new SWTRipper(config, Thread.currentThread());
		new SWTApplicationRunner(swtRipper).run();
		
		diff("expected/SWTListAppIgnoreList.xml", DEFAULT_GUI_FILENAME);
	}
	
	@Test
	public void testIgnoreComponentsWithWindow() {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setGuiFile(DEFAULT_GUI_FILENAME);
		config.setConfigFile("testConfigWithWindow.xml");
		config.setMainClass(SWTListApp.class.getName());
		
		SWTRipper swtRipper = new SWTRipper(config, Thread.currentThread());
		new SWTApplicationRunner(swtRipper).run();
		
		diff("expected/SWTListAppIgnoreList.xml", DEFAULT_GUI_FILENAME);
	}
	
}
