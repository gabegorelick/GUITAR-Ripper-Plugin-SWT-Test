package edu.umd.cs.guitar.ripper.test.aut;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

// Based on code found at http://dev.eclipse.org/viewcvs/viewvc.cgi/org.eclipse.swt.snippets/src/org/eclipse/swt/snippets/Snippet18.java?view=co

public class SWTToolbarApp {

	public static void main(String[] args) {
		Shell shell = new Shell();

		ToolBar bar = new ToolBar(shell, SWT.BORDER);
		for (int i = 0; i < 8; i++) {
			ToolItem item = new ToolItem(bar, SWT.PUSH);
			item.setText("Item " + i);
		}

		Rectangle clientArea = shell.getClientArea();
		bar.setLocation(clientArea.x + 5, clientArea.y + 5);
		bar.pack();

		shell.open();
		shell.setSize(380, 80);
		Display display = shell.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
