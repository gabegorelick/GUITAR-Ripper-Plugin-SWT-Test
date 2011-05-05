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
package edu.umd.cs.guitar.ripper.test.aut;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * Sample application to test argument passing.
 */
public class SWTArgumentApp {

	public SWTArgumentApp(Display display, String[] args) {
		Shell shell = new Shell();
		shell.setText("Window");
		shell.setSize(250, 250);
		
		GridLayout layout = new GridLayout(2, false);
		// set the layout of the shell
		shell.setLayout(layout);
		
		Label label = new Label(shell, SWT.LEFT);
		if (args.length > 0) {
			label.setText("Argument 1: " + args[0]);
		} else {
			label.setText("No arguments given");
		}
		
		Point p = label.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		label.setBounds(10, 10, p.x + 10, p.y + 10);
		
		Button button = new Button(shell, SWT.PUSH);
		
		if (args.length > 1) {
			button.setText("Argument 2: " + args[1]);
		} else {
			button.setText("No second argument");
		}

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	private static String[] args;
	
	public static void main(String[] args) {
		SWTArgumentApp.args = args;
		Display display = new Display();
		new SWTArgumentApp(display, args);
		display.dispose();

	}
	
	public static String[] getArgs() {
		return args;
	}

}
