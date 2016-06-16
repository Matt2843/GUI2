package com.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

public final class SGeom {
	
	private static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	
	// LOGIN DIALOG SIZES
	public static final Dimension LOGINFRAME = new Dimension((int) (SCREENSIZE.getWidth() * 0.19),(int) (SCREENSIZE.getHeight() * 0.43));
	public static final Dimension LOGINDECORATION = new Dimension((int) (LOGINFRAME.getWidth()), (int) (LOGINFRAME.getHeight() / 18));
	public static final Dimension LOGINSTATUS = new Dimension((int) (LOGINFRAME.getWidth()), (int) (LOGINFRAME.getHeight() / 6));
	public static final Dimension LOGINMAINPANEL = new Dimension((int) (LOGINFRAME.getWidth()), (int) (LOGINFRAME.getHeight() / 2));
	public static final Dimension LOGINLOWER = new Dimension((int) (LOGINFRAME.getWidth()), (int) (LOGINMAINPANEL.getHeight() + LOGINSTATUS.getHeight()));
	
	// MAIN PANE SIZES
	public static final Dimension MAINFRAME = new Dimension((int) (SCREENSIZE.getWidth() * 0.7), (int) (SCREENSIZE.getHeight() * 0.7));
	public static final Dimension NAVIGATIONOVERVIEW = new Dimension((int) (MAINFRAME.getWidth()*0.3), (int) (MAINFRAME.getHeight()));
	public static final Dimension CHAT = new Dimension((int) (MAINFRAME.getWidth()*0.7), (int) (MAINFRAME.getHeight()));

}
