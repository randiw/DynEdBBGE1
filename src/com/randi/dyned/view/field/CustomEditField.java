package com.randi.dyned.view.field;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.VirtualKeyboard;
import net.rim.device.api.ui.component.BasicEditField;

public class CustomEditField extends BasicEditField {

	private VirtualKeyboard virtualKeyboard;
	
	private boolean empty = true;
	private String initialValue;
	
	public CustomEditField() {
	}

	public CustomEditField(long style) {
		super(style);
	}

	public CustomEditField(String label, String initialValue) {
		super(label, initialValue);
		this.initialValue = initialValue;
	}

	public CustomEditField(String label, String initialValue, int maxNumChars, long style) {
		super(label, initialValue, maxNumChars, style);
		this.initialValue = initialValue;
	}
	
	protected void onDisplay() {
		virtualKeyboard = UiApplication.getUiApplication().getActiveScreen().getVirtualKeyboard();
		super.onDisplay();
	}
	
	public void setInitialValue(String initialValue) {
		if(initialValue == null){
			initialValue = "";
		}
		this.initialValue = initialValue;
		setText(initialValue);
	}
	
	protected void paint(Graphics g) {
		if(empty){
			g.setColor(Color.GRAY);
		} else {
			g.setColor(Color.BLACK);
		}
		super.paint(g);
	}
	
	protected boolean keyChar(char key, int status, int time) {
		if(empty){
			empty = false;
			setText("");
			invalidate();
		}
		return super.keyChar(key, status, time);
	}

	protected void onUnfocus() {
		if(virtualKeyboard != null) {
			virtualKeyboard.setVisibility(VirtualKeyboard.HIDE);			
		}
		if("".equals(getText()) || getText() == null){
			empty = true;
			setText(initialValue);
			invalidate();
		}
		super.onUnfocus();
	}
	
	protected void onFocus(int direction) {
		if(virtualKeyboard != null) {			
			virtualKeyboard.setVisibility(VirtualKeyboard.SHOW);
		}
		super.onFocus(direction);
	}
}