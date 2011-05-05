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

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Sample application with buttons windows that open when a button is clicked.
 */
public class SWTMultiWindowDynamicApp {
	private ArrayList<Shell> shellList = new ArrayList<Shell>();
	private Display globalDisplay = null;

	public SWTMultiWindowDynamicApp(Display display) {
		final Shell shell = new Shell(display);
		shell.setSize(250, 250);
		shell.setText("Window");
		shellList.add(shell);
		globalDisplay = display;

		Button addWindow = new Button(shell, SWT.PUSH);
		addWindow.setText("Add Window");
		addWindow.setBounds(85, 110, 80, 30);

		addWindow.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Shell tempShell = new Shell(globalDisplay);
				tempShell.setSize(250, 250);
				tempShell.setText("Window " + shellList.size());
				shellList.add(tempShell);
				tempShell.open();
			}
		});

		Button changeColor = new Button(shell, SWT.PUSH);
		changeColor.setBounds(20, 20, 80, 30);
		changeColor.setText("Change Color");

		changeColor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.setBackground(new Color(globalDisplay, 255, 0, 0));
			}
		});

		shell.open();
		for (int i = 0; i < shellList.size(); i++) {
			Shell currentShell = shellList.get(i);
			while (!currentShell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		}

	}

	public static void main(String[] args) {
		Display display = new Display();
		new SWTMultiWindowDynamicApp(display);
		display.dispose();
	}

}
