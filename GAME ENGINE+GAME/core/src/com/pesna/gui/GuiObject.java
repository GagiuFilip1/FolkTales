package com.pesna.gui;

import com.pesna.Main;

public abstract class GuiObject {
	public final boolean isVisible, isUpdateable, isInteractive;
	
	public GuiObject()
	{
		isVisible = true;
		isUpdateable = false;
		isInteractive = false;
	}
	
	abstract public void draw( Main _reference );
	abstract public void update( Main _reference );
}
