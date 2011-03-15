package edu.umd.cs.guitar.ripper.test.aut;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SWTArgumentApp {

	public SWTArgumentApp(Display display, String part1, String part2) {
		Shell shell = new Shell();
		shell.setText("Window");
		shell.setSize(250, 250);
		
		GridLayout layout = new GridLayout(2, false);
		// set the layout of the shell
		shell.setLayout(layout);
		Integer p2 = Integer.parseInt(part2);

		Label label = new Label(shell, SWT.LEFT);
		label.setText("Argument 1: "+part1);
		Point p = label.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		label.setBounds(10, 10, p.x + 10, p.y + 10);
		
		if(p2 == 1){
			Button button = new Button(shell, SWT.PUSH);
			button.setText("Argument 2");
		}

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	public static void main(String[] args) {
		
		Display display = new Display();
		new SWTArgumentApp(display,args[0],args[1]);
		display.dispose();

	}

}
