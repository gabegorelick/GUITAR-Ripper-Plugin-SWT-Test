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

//Credit to http://zetcode.com/tutorials/javaswttutorial/widgets/

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * Sample list application.
 *
 */
public class SWTListApp {
	private Shell shell;

	public SWTListApp(Display display) {
		shell = new Shell(display);
		shell.setText("Window");
		shell.setSize(250, 250);

		initUI();

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void initUI() {
		final Label status = new Label(shell, SWT.BORDER);
		status.setText("Ready");

		FormLayout layout = new FormLayout();
		shell.setLayout(layout);

		FormData labelData = new FormData();
		labelData.left = new FormAttachment(0);
		labelData.right = new FormAttachment(100);
		labelData.bottom = new FormAttachment(100);

		status.setLayoutData(labelData);

		final List list = new List(shell, SWT.BORDER | SWT.SINGLE);

		list.add("Item 1");
		list.add("Item 2");
		list.add("Item 3");
		list.add("Item 4");
		list.add("Item 5");

		FormData listData = new FormData();
		listData.left = new FormAttachment(shell, 30, SWT.LEFT);
		listData.top = new FormAttachment(shell, 30, SWT.TOP);
		list.setLayoutData(listData);
		
		list.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				status.setText(list.getSelection()[0]);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
				
		// for some reason calling this causes the label to be "Ready" on GTK 
		list.select(0);
	}

	public static void main(String[] args) {
		Display display = new Display();
		new SWTListApp(display);
		display.dispose();

	}

}
