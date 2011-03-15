package edu.umd.cs.guitar.ripper.test;

import java.util.Hashtable;
import java.util.List;

import edu.umd.cs.guitar.event.SWTEventHandler;
import edu.umd.cs.guitar.model.GComponent;

// TODO delete this when we get mocking working
public class SWTEventHandlerStub extends SWTEventHandler {

	public SWTEventHandlerStub() {
		
	}
	
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
