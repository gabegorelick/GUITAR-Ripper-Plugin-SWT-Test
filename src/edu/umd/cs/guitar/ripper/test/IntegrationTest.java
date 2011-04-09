package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.NodeDetail;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.SWTRipperRunner;


public class IntegrationTest {

	public static void ripAndDiff(String filename) {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setGuiFile("testoutput.xml");

		String fullName = "edu.umd.cs.guitar.ripper.test.aut." + filename;
		config.setMainClass(fullName);
		
		final SWTRipper swtRipper = new SWTRipper(config, Thread.currentThread());
		new SWTRipperRunner(swtRipper).start();
				
		String name = "expected/" + filename + ".xml";
		//assertEquals(-1, diff(name, config.getGuiFile()));
		
		Document result = buildDoc(config.getGuiFile());
		Document test = buildDoc(name);
		DetailedDiff diff = new DetailedDiff(new Diff(test,result));
		
		List<Difference> difference = diff.getAllDifferences();
		if(difference.size() == 0 || difference == null){
			assertTrue(diff.similar());
		}
		else{
			int diffcount = 0;
			for(Difference d: difference){
				NodeDetail nd = d.getTestNodeDetail();
				Node parent = nd.getNode().getParentNode().getParentNode();
				NodeList nl = parent.getChildNodes();
				for(int i = 0; i < nl.getLength(); i++){
					if(nl.item(i).getNodeName().equals("Name")){
						String text = nl.item(i).getFirstChild().getNodeValue();
						System.out.println(text.toString()); ////
						if(text.equals("X") || text.equals("Y") || text.equals("text") ){
							diffcount++;
						}
						System.out.println(d.toString()); ////
					}
				}
			}
			assertEquals(difference.size(),diffcount);
		}
				
	}

	/**
	 * Reads and compares two files for any differences, returns the line number
	 * if different, -1 if the files are the same.
	 */
	private static int diff(String expectedFile, String actualFile) {
		File expected = new File(expectedFile);
		File actual = new File(actualFile);
		BufferedReader expectedReader = null;
		BufferedReader actualReader = null;

		try {
			expectedReader = new BufferedReader(new FileReader(expected));
			actualReader = new BufferedReader(new FileReader(actual));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		int lineNumber = 1;
		String expectedString;
		String actualString;
		
		String lastLine = null;
		
		boolean equal = true;
		try { // TODO this doesn't work if one of the files is empty
			while (expectedReader.ready()) {
				expectedString = expectedReader.readLine();
				if (actualReader.ready()) {
					actualString = actualReader.readLine();
				} else {
					equal = false;
					break;
				}
				
				// ignore if this is X or Y value
				// TODO use XMLUnit to do this
				if (!expectedString.equals(actualString)) {
					if (lastLine != null 
							&& !lastLine.contains("<Name>X</Name>")
							&& !lastLine.contains("<Name>Y</Name>")) {
					
						System.err.println("Failed at line " + lineNumber);
						System.err.println("Expected: " + expectedString);
						System.err.println("Actual: " + actualString);
						equal = false;
						break;
					}
				}
				lineNumber++;
				lastLine = actualString;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (equal) {
			return -1;
		} else {
			return lineNumber;
		}

	}
	
	private static Document buildDoc(String filename){
		Document doc = null;
		try {
			File file = new File(filename);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(file);
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return doc;
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

}
