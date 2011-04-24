package edu.umd.cs.guitar.ripper.test;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

import edu.umd.cs.guitar.ripper.SWTApplicationRunner;
import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.SWTRipperMain;
import edu.umd.cs.guitar.ripper.SWTRipperRunner;

public class CommandLineRipper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        SWTRipperConfiguration configuration = new SWTRipperConfiguration();
        CmdLineParser parser = new CmdLineParser(configuration);
        //CmdLineParser.registerHandler(String[].class, StringArrayOptionHandler.class);
        
        args = new String[] {"-c","/aut/SWTBasicApp"};
        
        try {
        	
            parser.parseArgument(args);
                        
            SWTRipper swtRipper = new SWTRipper(configuration, Thread.currentThread());
            //SWTRipperRunner runner = new SWTRipperRunner(swtRipper);
            new SWTApplicationRunner(swtRipper).run();
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println();
            System.err.println("Usage: java [JVM options] "
                    + SWTRipperMain.class.getName() + " [Ripper options] \n");
            System.err.println("where [Ripper options] include:");
            System.err.println();
            parser.printUsage(System.err);
        }
    }

}
