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

import org.junit.Test;

import edu.umd.cs.guitar.ripper.SitarRipperMain;
import edu.umd.cs.guitar.ripper.test.aut.SWTBasicApp;

/**
 * Test for {@link SitarRipperMain}.
 * 
 */
public class SitarRipperMainTest {

	/**
	 * Test {@link SitarRipperMain#main(String[])} with no arguments.
	 */
	@Test
	public void testMainNoArgs() {
//		SitarRipperMain.main(new String[0]);
		// TODO test output by redirecting stdout?
	}

	/**
	 * Test {@link SitarRipperMain#main(String[])} with a nonexistent main
	 * class.
	 */
	@Test
	public void testMain() {
		SitarRipperMain.main(new String[] { "-c", "NOSUCHCLASS" });
		// TODO how do we test this if it handles the exception itself?
	}
	
	/**
	 * Test {@link SitarRipperMain#main(String[])} with really bad arguments.
	 */
	@Test
	public void testMainBadArgs() {
		SitarRipperMain.main(new String[] { "-lksdfj", "laskjdfa" });
		// TODO how do we test this if it handles the exception itself?
	}

	/**
	 * Test {@link SitarRipperMain#main(String[])} with URL arguments.
	 */
	@Test
	public void testMainURL() {
		SitarRipperMain.main(new String[] { "-c", SWTBasicApp.class.getName(), "-u", "http://www.google.com/" });
		// TODO how do we test this if it handles the exception itself?
	}

	/**
	 * Test {@link SitarRipperMain#main(String[])} with bad URL arguments.
	 */
	@Test
	public void testMainURLWithDash() {
		SitarRipperMain.main(new String[] { "-u", "-http://www.google.com/" });
		// TODO how do we test this if it handles the exception itself?
	}
}
