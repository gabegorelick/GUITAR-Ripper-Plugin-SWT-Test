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
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

//Source code found at http://www.mkyong.com/swt/swt-tabfolder-example/ 

/**
 * Sample application that has a {@link TabFolder}.
 */
public class SWTTabFolderApp {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SWT TabFolder Example");

		shell.setLayout(new FillLayout());

		TabFolder folder = new TabFolder(shell, SWT.NONE);

		// Tab 1
		TabItem tab1 = new TabItem(folder, SWT.NONE);
		tab1.setText("Tab 1");
		
		// Create the SashForm with HORIZONTAL
		SashForm sashForm = new SashForm(folder, SWT.HORIZONTAL);
		new Button(sashForm, SWT.PUSH).setText("Left");
		new Button(sashForm, SWT.PUSH).setText("Right");

		tab1.setControl(sashForm);

		// Tab 2
		TabItem tab2 = new TabItem(folder, SWT.NONE);
		tab2.setText("Tab 2");

		Group group = new Group(folder, SWT.NONE);
		group.setText("Group in Tab 2");

		Label label = new Label(group, SWT.BORDER);
		label.setText("Label in Tab 2");
		label.setBounds(10, 100, 100, 100);

		Text text = new Text(group, SWT.NONE);
		text.setText("Text in Tab 2");
		text.setBounds(10, 200, 100, 100);

		tab2.setControl(group);

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}