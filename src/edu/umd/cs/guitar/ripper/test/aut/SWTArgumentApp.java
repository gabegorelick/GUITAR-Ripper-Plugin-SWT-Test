package edu.umd.cs.guitar.ripper.test.aut;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

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
