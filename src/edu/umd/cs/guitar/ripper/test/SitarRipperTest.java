/*	
 *  Copyright (c) 2011-@year@. The GUITAR group at the University of Maryland. Names of owners of this group may
 *  be obtained by sending an e-mail to atif@cs.umd.edu
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 *  documentation files (the "Software"), to deal in the Software without restriction, including without 
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 *	the Software, and to permit persons to whom the Software is furnished to do so, subject to the following 
 *	conditions:
 * 
 *	The above copyright notice and this permission notice shall be included in all copies or substantial 
 *	portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
 *	LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO 
 *	EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
 *	IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR 
 *	THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
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
import edu.umd.cs.guitar.ripper.SitarRunner;
import edu.umd.cs.guitar.ripper.SitarRipper;
import edu.umd.cs.guitar.ripper.SitarRipperConfiguration;
import edu.umd.cs.guitar.ripper.test.aut.SWTBasicApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTButtonApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTCheckButtonApp;
import edu.umd.cs.guitar.ripper.test.aut.SWTHelloWorldApp;
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

/**
 * Tests for {@code SitarRipper}.
 */
public class SitarRipperTest {
	
	private static final String DEFAULT_GUI_FILENAME = "testoutput.xml";
	
	/**
	 * Run the ripper on the given class. Returns the name of the GUI structure
	 * output file.
	 * 
	 * @param clazz the class to rip
	 * @return the name of the output file
	 */
	private static String rip(Class<?> clazz) {
		SitarRipperConfiguration config = new SitarRipperConfiguration();
		config.setGuiFile(DEFAULT_GUI_FILENAME);
		config.setMainClass(clazz.getName());
		
		SitarRipper swtRipper = new SitarRipper(config, Thread.currentThread());
		new SitarRunner(swtRipper).run();
		
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
	
	/**
	 * Rip {@link SWTBasicApp}.
	 */
	@Test
	public void testBasicApp() {
		ripAndDiff(SWTBasicApp.class);
	}

	/**
	 * Rip {@link SWTButtonApp}.
	 */
	@Test
	public void testButtonApp() {
		ripAndDiff(SWTButtonApp.class);
	}

	/**
	 * Rip {@link SWTCheckButtonApp}.
	 */
	@Test
	public void testCheckButtonApp() {
		ripAndDiff(SWTCheckButtonApp.class);
	}

	/**
	 * Rip {@link SWTHelloWorldApp}.
	 */
	@Test
	public void testHelloWorld() {
		ripAndDiff(SWTHelloWorldApp.class);
	}

	/**
	 * Rip {@link SWTLabelApp}.
	 */
	@Test
	public void testLabelApp() {
		ripAndDiff(SWTLabelApp.class);
	}

	/**
	 * Rip {@link SWTListApp}.
	 */
	@Test
	public void testListApp() {
		ripAndDiff(SWTListApp.class);
	}

	/**
	 * Rip {@link SWTMenuBarApp}.
	 */
	@Test
	public void testMenuBarApp() {
		ripAndDiff(SWTMenuBarApp.class);
	}

	/**
	 * Rip {@link SWTTwoWindowsApp}.
	 */
	@Test
	public void testTwoWindowsApp() {
		ripAndDiff(SWTTwoWindowsApp.class);
	}

	/**
	 * Rip {@link SWTWindowApp}.
	 */
	@Test
	public void testWindowApp() {
		ripAndDiff(SWTWindowApp.class);
	}
	
	/**
	 * Rip {@link SWTTabFolderApp}.
	 */
	@Test
	public void testTabFolderApp() {
		ripAndDiff(SWTTabFolderApp.class);
	}
	
	/**
	 * Rip {@link SWTTableApp}.
	 */
	@Test
	public void testTableApp() {
		ripAndDiff(SWTTableApp.class);
	}
	
	/**
	 * Rip {@link SWTTreeApp}.
	 */
	@Test
	public void testTreeApp() {
		ripAndDiff(SWTTreeApp.class);
	}
	
	/**
	 * Rip {@link SWTToolbarApp}.
	 */
	@Test
	public void testToolbarApp() {
		ripAndDiff(SWTToolbarApp.class);
	}
	
	/**
	 * Rip {@link SWTMultiWindowDynamicApp}.
	 */
	@Test
	public void testMultiWindowDynamicApp() {
		ripAndDiff(SWTMultiWindowDynamicApp.class);
	}
	
	/**
	 * Test ignoring components with configuration.xml file.
	 */
	@Test
	public void testIgnoreComponents() {
		SitarRipperConfiguration config = new SitarRipperConfiguration();
		config.setGuiFile(DEFAULT_GUI_FILENAME);
		config.setConfigFile("testconfig.xml");
		config.setMainClass(SWTListApp.class.getName());
		
		SitarRipper swtRipper = new SitarRipper(config, Thread.currentThread());
		new SitarRunner(swtRipper).run();
		
		diff("expected/SWTListAppIgnoreList.xml", DEFAULT_GUI_FILENAME);
	}
	
	/**
	 * Test ignoring windows with configuration.xml file.
	 */
	@Test
	public void testIgnoreComponentsWithWindow() {
		SitarRipperConfiguration config = new SitarRipperConfiguration();
		config.setGuiFile(DEFAULT_GUI_FILENAME);
		config.setConfigFile("testConfigWithWindow.xml");
		config.setMainClass(SWTListApp.class.getName());
		
		SitarRipper swtRipper = new SitarRipper(config, Thread.currentThread());
		new SitarRunner(swtRipper).run();
		
		diff("expected/SWTListAppIgnoreList.xml", DEFAULT_GUI_FILENAME);
	}

	/**
	 * Test {@link SitarRipper#SitarRipper(SitarRipperConfiguration)} with a
	 * null configuration.
	 */
	@Test
	public void testNullConfig() {
		new SitarRipper(null, Thread.currentThread());
		
		// TODO need equals for config to test this, 
		// but still good to test that it doesn't throw exception
	}
	
}
