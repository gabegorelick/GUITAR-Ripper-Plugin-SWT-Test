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
import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.SWTRipperRunner;
import edu.umd.cs.guitar.util.GUITARLog;

public class IntegrationTest {

	public static void ripAndDiff(String filename) {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setGuiFile("testoutput.xml");

		String fullName = "edu.umd.cs.guitar.ripper.test.aut." + filename;
		config.setMainClass(fullName);
		
		final SWTRipper swtRipper = new SWTRipper(config, Thread.currentThread());
		new SWTRipperRunner(swtRipper).start();
				
		String name = "expected/" + filename + ".xml";
		
		XMLUnit.setNormalizeWhitespace(true);
		
		Document actual;
		Document expected;
		try {
			// also called testDoc by XMLUnit
			expected = XMLUnit.buildTestDocument(new InputSource(new FileReader(name)));
			
			// also called controlDoc by XMLUnit
			actual = XMLUnit.buildControlDocument(new InputSource(new FileReader(config.getGuiFile())));
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
					Node xyNode = nameNode.getFirstChild();
					String xy = xyNode.getTextContent();
					
					if (xy.equals(GUITARConstants.X_TAG_NAME)
							|| xy.equals(GUITARConstants.Y_TAG_NAME)) {
						
						StringBuilder builder = new StringBuilder();
						builder.append("Ignoring bad ").append(xy);
						builder.append(" value (expected ");
						builder.append(difference.getControlNodeDetail().getValue());
						builder.append(" but got ");
						builder.append(actualNodeDetail.getValue()).append(")");
						
						GUITARLog.log.info(builder);

						return DifferenceListener.RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
					}
				} catch (NullPointerException e) {
					GUITARLog.log.warn("Unexpected GUI structure ", e);
					return DifferenceListener.RETURN_ACCEPT_DIFFERENCE;
				}
				
				return DifferenceListener.RETURN_ACCEPT_DIFFERENCE;
			}
		});
		
		for (Object o : diff.getAllDifferences()) {
			GUITARLog.log.warn(o); // TODO get line numbers
		}
		
		assertTrue(diff.similar());	
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
	
//	@Test
//	public void testMultiWindowDynamicApp() {
//		ripAndDiff("SWTMultiWindowDynamicApp");
//	}
	
	@Test
	public void testTabFolderApp() {
		ripAndDiff("SWTTabFolderApp");
	}
	
	@Test
	public void testTableApp() {
		ripAndDiff("SWTTableApp");
	}
	
	@Test
	public void testTreeApp() {
		ripAndDiff("SWTTreeApp");
	}
	
	@Test
	public void testToolbarApp() {
		ripAndDiff("SWTToolbarApp");
	}

}
