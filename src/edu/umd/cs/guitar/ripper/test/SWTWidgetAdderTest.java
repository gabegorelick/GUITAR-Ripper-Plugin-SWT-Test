package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.custom.TableTreeItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.junit.Test;

import edu.umd.cs.guitar.internal.SWTWidgetAdder;
import edu.umd.cs.guitar.model.GComponent;

public class SWTWidgetAdderTest {

	@Test
	public void testMenu() {
		Display display = new Display();
		Shell shell = new Shell(display);
		Menu menu = new Menu(shell);
		Menu menu2 = new Menu(menu);
		
		MenuItem item1 = new MenuItem(menu, SWT.CASCADE);
		item1.setText("One");
		item1.setMenu(menu2);
		MenuItem item2 = new MenuItem(menu, 1);
		item2.setText("Two");

		List<GComponent> chi1 = SWTWidgetAdder.handleWidget(menu, null);
		List<GComponent> chi2 = SWTWidgetAdder.handleWidget(item1, null);
		assertEquals(2, chi1.size());
		assertEquals(1, chi2.size());

		display.dispose();
	}

	@Test
	public void testTabFolder() {
		Display display = new Display();
		Shell shell = new Shell(display);
		TabFolder folder = new TabFolder(shell, 0);

		Button b = new Button(folder, 0);

		TabFolder item1 = new TabFolder(folder, 0);
		Button b2 = new Button(item1, 0);
		TabItem item2 = new TabItem(folder, 0);
		TabItem item3 = new TabItem(item1, 0);
		Label lab = new Label(item1, 0);
		lab.setText("Label");
		TabItem item4 = new TabItem(folder, 0);
		item3.setText("One");
		item2.setControl(b);
		item3.setControl(lab);
		item4.setControl(new Text(folder, 0));

		List<GComponent> chi = SWTWidgetAdder.handleWidget(folder, null);
		List<GComponent> chi2 = SWTWidgetAdder.handleWidget(item1, null);
		List<GComponent> chi3 = SWTWidgetAdder.handleWidget(item2, null);
		List<GComponent> chi4 = SWTWidgetAdder.handleWidget(item3, null);
		List<GComponent> chi5 = SWTWidgetAdder.handleWidget(item4, null);
		assertEquals(2, chi.size());
		assertEquals(1, chi2.size());

		display.dispose();
	}

	@Test
	public void testTree() {
		Display display = new Display();
		Shell shell = new Shell(display);
		Tree tree = new Tree(shell, 0);

		TreeColumn tc = new TreeColumn(tree, 0);

		TreeItem item1 = new TreeItem(tree, SWT.CASCADE);

		item1.setText("One");
		TreeItem item2 = new TreeItem(tree, 1);
		item1.setText("Two");
		TreeItem item3 = new TreeItem(item1, 0);
		Button b = new Button(tree, 0);

		List<GComponent> chi = SWTWidgetAdder.handleWidget(tree, null);
		List<GComponent> chi2 = SWTWidgetAdder.handleWidget(item1, null);
		assertEquals(3, chi.size());
		assertEquals(1, chi2.size());

		display.dispose();
	}

	@Test
	public void testTable() {
		Display display = new Display();
		Shell shell = new Shell();
		Table table = new Table(shell, 0);

		TableColumn tc = new TableColumn(table, 0);

		TableItem item1 = new TableItem(table, 0);
		item1.setText("One");
		TableItem item2 = new TableItem(table, 1);
		item1.setText("Two");

		// Test TableTree as well

		TableTree tt = new TableTree(shell, 0);
		Button b = new Button(tt, 0);

		TableTreeItem tti = new TableTreeItem(tt, 0);
		TableTreeItem tti1 = new TableTreeItem(tt, 0);
		TableTreeItem tti2 = new TableTreeItem(tti, 0);

		List<GComponent> chi = SWTWidgetAdder.handleWidget(table, null);
		List<GComponent> chi2 = SWTWidgetAdder.handleWidget(tt, null);
		SWTWidgetAdder.handleWidget(tti, null);
		assertEquals(3, chi.size());
		assertEquals(2, chi2.size());

		display.dispose();
	}

	@Test
	public void testBar() {
		Display display = new Display();
		Shell shell = new Shell(display);
		ToolBar tb = new ToolBar(shell, 0);

		ToolItem item1 = new ToolItem(tb, 0);
		item1.setText("One");
		ToolItem item2 = new ToolItem(tb, 1);
		item1.setText("Two");

		List<GComponent> chi = SWTWidgetAdder.handleWidget(tb, null);
		assertEquals(2, chi.size());

		display.dispose();
	}

	@Test
	public void testTray() {
		Display display = new Display();
		Shell shell = new Shell(display);
		Tray tray = shell.getDisplay().getSystemTray();

		TrayItem item1 = new TrayItem(tray, 0);
		item1.setText("One");
		TrayItem item2 = new TrayItem(tray, 1);
		item1.setText("Two");

		List<GComponent> chi = SWTWidgetAdder.handleWidget(tray, null);
		assertEquals(2, chi.size());

		display.dispose();
	}
}