package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Hashtable;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.guitar.event.EventManager;
import edu.umd.cs.guitar.event.SWTEventHandler;
import edu.umd.cs.guitar.model.GComponent;
import edu.umd.cs.guitar.model.GUITARConstants;
import edu.umd.cs.guitar.model.SWTComposite;
import edu.umd.cs.guitar.model.SWTWidget;


public class SWTCompositeTest {

	private Display display;
	
	@Before
	public void setUp() {
		if (display == null || display.isDisposed()) {
			display = new Display();
		}
	}
	
	@After
	public void tearDown() {
		display.dispose();
	}
	
	@Test
	public void testGetControl() {
		Shell shell = new Shell(display);		
		Button button = new Button(shell, SWT.PUSH);
		
		SWTComposite comp = new SWTComposite(button, null);
		assertEquals(button, comp.getControl());
	}
	
	@Test
	public void testGetTitle() {
		assertEquals("", new SWTComposite(null, null).getTitle());
		
		Shell shell = new Shell(display);
		
		Button button = new Button(shell, SWT.PUSH);
		String buttonText = "Button text"; 
		button.setText(buttonText);
		
		assertEquals("", new SWTComposite(button, null).getTitle());
		
		String shellText = "Shell Title"; 
		shell.setText(shellText);
		assertEquals(shellText, new SWTComposite(button, null).getTitle());
	}
	
	@Test
	public void testGetX() {
		Shell shell = new Shell(display);
				
		assertEquals(0, new SWTComposite(null, null).getX());
		assertEquals(0, new SWTComposite(shell, null).getX());
		
		Button button = new Button(shell, SWT.PUSH);
		assertEquals(0, new SWTComposite(button, null).getX());
		
		button.setBounds(85, 110, 80, 30);
		assertEquals(85, new SWTComposite(button, null).getX());
	}
	
	@Test
	public void testGetY() {
		Shell shell = new Shell(display);
		
		assertEquals(0, new SWTComposite(null, null).getY());
		assertEquals(0, new SWTComposite(shell, null).getY());
		
		Button button = new Button(shell, SWT.PUSH);
		assertEquals(0, new SWTComposite(button, null).getY());
		
		button.setBounds(85, 110, 80, 30);
		assertEquals(110, new SWTComposite(button, null).getY());
	}
	
	@Test
	public void testGetGUIProperties() {
		Shell shell = new Shell(display);
				
		new SWTComposite(shell, null).getGUIProperties();
		
		shell.setImage(new Image(display, 20, 20));
		new SWTComposite(shell, null).getGUIProperties();
		
		// TODO compare output instead of just making sure it doesn't error
	}
	
	@Test
	public void testGetClassVal() {
		Shell shell = new Shell(display);
		
		assertEquals(shell.getClass().getName(), new SWTComposite(shell, null).getClassVal());
	}
	
	@Test
	public void testGetChildren() {
		Shell shell = new Shell(display);
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		SWTComposite comp = new SWTComposite(shell, null);
		List<GComponent> children = comp.getChildren();
		
		menu.dispose();
		Menu popUpMenu = new Menu(shell, SWT.POP_UP);
		shell.setMenu(popUpMenu);
		
		Tray tray = display.getSystemTray();
		new TrayItem(tray, SWT.NONE);
		
		children = comp.getChildren();
		assertEquals(2, children.size());
	}
	
	@Test
	public void testGetParent() {
		Shell shell = new Shell(display);
		
		SWTWidget parent = (SWTWidget) new SWTComposite(shell, null).getParent();
		assertNull(parent.getWidget());
		
		Button button = new Button(shell, SWT.PUSH);
		parent = (SWTWidget) new SWTComposite(button, null).getParent();
		assertEquals(shell, parent.getWidget());
		
	}
	
	@Test
	public void testIsEnable() {
		Shell shell = new Shell(display);
		
		assertTrue(new SWTComposite(shell, null).isEnable());
	}
	
	@Test
	public void testGetTypeVal() {
		Shell shell = new Shell(display);
		
		assertEquals(GUITARConstants.SYSTEM_INTERACTION, new SWTComposite(shell, null).getTypeVal());
	}
	
	@Test
	public void testGetEventList() {
		Shell shell = new Shell(display);
		
//		GEvent event = mock(SWTEventHandler.class);
		SWTComposite comp = new SWTComposite(shell, null);
		
		//when(event.isSupportedBy(any(GComponent.class))).thenReturn(true);

		EventManager manager = EventManager.getInstance();	
		
		manager.registerEvent(MyEventNoConstructor.class);
		comp.getEventList();
		manager.getEvents().clear();
		
		manager.registerEvent(SWTEventHandlerStub.class);
		comp.getEventList();
		manager.getEvents().clear();
	}
	
	// no available constructor for getEventList since it's private
	// TODO mock this instead
	private class MyEventNoConstructor extends SWTEventHandler {

		@Override
		public boolean isSupportedBy(GComponent arg0) {
			return true;
		}

		@Override
		protected void performImpl(GComponent arg0,
				Hashtable<String, List<String>> arg1) {		
		}

		@Override
		protected void performImpl(GComponent arg0, Object arg1,
				Hashtable<String, List<String>> arg2) {
		}
		
	}
}
