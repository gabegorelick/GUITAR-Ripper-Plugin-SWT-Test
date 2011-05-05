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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.*;

/**
 * Sample application with a label that changes when a button is pressed. 
 */
public class SWTDynamicLabelApp {
	private int label_num = 0;

	public SWTDynamicLabelApp(Display display) {
		Shell shell = new Shell(display);
		shell.setSize(250, 250);
		shell.setText("Window");

		final Label label = new Label(shell, SWT.LEFT);
		label.setText("Change this text");
		Point p = label.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		label.setBounds(10, 10, p.x + 10, p.y + 10);

		Button changeText = new Button(shell, SWT.PUSH);
		changeText.setText("Change Text");
		changeText.setBounds(85, 110, 80, 30);
		changeText.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (label_num == 0) {
					label.setText("Text 1");
					label_num = 1;
				} else {
					label.setText("Text 0");
					label_num = 0;
				}
			}
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public static void main(String[] args) {
		Display display = new Display();
		new SWTDynamicLabelApp(display);
		display.dispose();
	}

}
