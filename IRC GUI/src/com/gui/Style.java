package com.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public final class Style {
	
	public static final Color glassColor = new Color(255, 255, 255, 35);
	public static final Color foregroundC = new Color(210, 210, 212);
	public static final Color backgroundC = new Color(25, 25, 25);
	public static final Color highlight = new Color(210, 30, 75);
	
	public static final Border paneBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, foregroundC);
	public static final Border hightlightBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, highlight);
	public static final Border buttonBorder = BorderFactory.createLineBorder(foregroundC);
	public static final Font font = new Font("Tahoma", Font.PLAIN, 14);

}
