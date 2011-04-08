package edu.umd.cs.guitar.ripper.test.aut;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

//Written from the template found at http://www.java2s.com/Code/Java/SWT-JFace-Eclipse/SWTTree.htm

public class SWTTreeApp {
	public static void main(String[] args){
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Window");
		shell.setSize(275, 275);
		
		final Tree tree = new Tree(shell, SWT.BORDER);
		tree.setSize(250, 250);
		
		for( int level1 = 0; level1 < 3; level1++){
			TreeItem item1 = new TreeItem(tree, 0);
			item1.setText("Level 1 Item # " + level1);
			for( int level2 = 0; level2 < 3; level2++){
				TreeItem item2 = new TreeItem(item1, 0);
				item2.setText("Level 2 Item # " + level2);
				for( int level3 = 0; level3 < 3; level3++){
					TreeItem item3 = new TreeItem(item2, 0);
					item3.setText("Level 3 Item # " + level3);
				}
			}
		}
		
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch()){
				display.sleep();
			}
		}
		display.dispose();
	}
}
