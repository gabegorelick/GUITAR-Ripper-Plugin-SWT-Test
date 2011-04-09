package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Hashtable;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import edu.umd.cs.guitar.event.EventManager;
import edu.umd.cs.guitar.event.SWTEventHandler;
import edu.umd.cs.guitar.model.GComponent;
import edu.umd.cs.guitar.model.swtwidgets.SWTWidget;


public class SWTWidgetTest {

	@Test
	public void testGetTitle() {
//		assertTrue(new SWTWidget(null, null).getTitle().isEmpty());
//		
//		Display display = new Display();
//		Shell shell = new Shell(display);
//		
//		Menu menu = new Menu(shell, SWT.BAR);
//		MenuItem item = new MenuItem(menu, SWT.CASCADE);
//		
//		assertEquals(item.getText(), new SWTWidget(item, null).getTitle());
//		
//		display.dispose();
	}
	
	@Test
	public void testGetParent() {
//		assertNull(new SWTWidget(null, null).getParent());
	}
	
	@Test
	public void testIsEnable() {
//		Display display = new Display();
//		Shell shell = new Shell(display);
//		
//		assertTrue(new SWTWidget(shell, null).isEnable());
//		
//		Menu menu = new Menu(shell, SWT.BAR);
//		assertFalse(new SWTWidget(menu, null).isEnable());
//		
//		display.dispose();
	}
	
	@Test
	public void testGetEventList() {
//		Display display = new Display();
//		Shell shell = new Shell(display);
//		
////		GEvent event = mock(SWTEventHandler.class);
//		SWTWidget widget = new SWTWidget(shell, null);
//		
//		//when(event.isSupportedBy(any(GComponent.class))).thenReturn(true);
//
//		EventManager manager = EventManager.getInstance();	
//		
//		manager.registerEvent(MyEventNoConstructor.class);
//		widget.getEventList();
//		manager.getEvents().clear();
//		
//		manager.registerEvent(SWTEventHandlerStub.class);
//		widget.getEventList();
//		manager.getEvents().clear();
//		
//		display.dispose();
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
