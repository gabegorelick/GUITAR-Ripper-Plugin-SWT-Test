package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import edu.umd.cs.guitar.event.EventManager;
import edu.umd.cs.guitar.event.GEvent;
import edu.umd.cs.guitar.event.SWTEventHandler;
import edu.umd.cs.guitar.model.GComponent;
import edu.umd.cs.guitar.model.SWTComposite;


public class SWTCompositeTest {

	@Test
	public void testGetControl() {
		Display display = new Display();
		Shell shell = new Shell(display);		
		Button button = new Button(shell, SWT.PUSH);
		
		SWTComposite comp = new SWTComposite(button, null);
		assertEquals(button, comp.getControl());
		
		display.dispose();
	}
	
	@Test
	public void testGetTitle() {
		assertEquals("", new SWTComposite(null, null).getTitle());
		
		Display display = new Display();
		Shell shell = new Shell(display);
		
		Button button = new Button(shell, SWT.PUSH);
		String buttonText = "Button text"; 
		button.setText(buttonText);
		
		assertEquals("", new SWTComposite(button, null).getTitle());
		
		String shellText = "Shell Title"; 
		shell.setText(shellText);
		assertEquals(shellText, new SWTComposite(button, null).getTitle());
		
		display.dispose();
	}
	
	@Test
	public void testGetX() {
		Display display = new Display();
		Shell shell = new Shell(display);
		
		assertEquals(0, new SWTComposite(null, null).getX());
		assertEquals(0, new SWTComposite(shell, null).getX());
		
		Button button = new Button(shell, SWT.PUSH);
		assertEquals(0, new SWTComposite(button, null).getX());
		
		button.setBounds(85, 110, 80, 30);
		assertEquals(85, new SWTComposite(button, null).getX());
				
		display.dispose();
	}
	
	@Test
	public void testGetY() {
		Display display = new Display();
		Shell shell = new Shell(display);
		
		assertEquals(0, new SWTComposite(null, null).getY());
		assertEquals(0, new SWTComposite(shell, null).getY());
		
		Button button = new Button(shell, SWT.PUSH);
		assertEquals(0, new SWTComposite(button, null).getY());
		
		button.setBounds(85, 110, 80, 30);
		assertEquals(110, new SWTComposite(button, null).getY());
				
		display.dispose();
	}
	
	@Test
	public void testGetGUIProperties() {
		Display display = new Display();
		Shell shell = new Shell(display);
				
		new SWTComposite(shell, null).getGUIProperties();
		
		shell.setImage(new Image(display, 20, 20));
		new SWTComposite(shell, null).getGUIProperties();
		
		// TODO compare output instead of just making sure it doesn't error
		
		display.dispose();
	}
	
	@Test
	public void testGetClassVal() {
		Display display = new Display();
		Shell shell = new Shell(display);
		
		assertEquals(shell.getClass().getName(), new SWTComposite(shell, null).getClassVal());
				
		display.dispose();
	}
	
	@Test
	public void testGetEventList() {
		Display display = new Display();
		Shell shell = new Shell(display);
		
		assertEquals(new ArrayList<GEvent>(), new SWTComposite(null, null).getEventList());
		
		// TODO mock these instead
		EventManager.getInstance().registerEvent(new SWTEventHandler() {
			@Override
			public boolean isSupportedBy(GComponent arg0) {
				return true;
			}
			
			@Override
			protected void performImpl(GComponent arg0, Object arg1,
					Hashtable<String, List<String>> arg2) {	
			}
			
			@Override
			protected void performImpl(GComponent arg0,
					Hashtable<String, List<String>> arg1) {
			}
		}.getClass());
		
		
		
		new SWTComposite(null, null).getEventList();
		
		display.dispose();
	}
}
