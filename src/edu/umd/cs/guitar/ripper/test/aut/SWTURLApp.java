package edu.umd.cs.guitar.ripper.test.aut;

import java.net.URL;


public class SWTURLApp {

	private static URL url;
	
	public static URL getURL() {
		return url;
	}
	
	public static void main(String[] args) {
		if (args.length > 0) {
			ClassLoader cl = SWTURLApp.class.getClassLoader();
			url = cl.getResource(args[0]);
		}
	}

}
