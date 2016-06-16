package com.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BlurComponent extends JLabel {
	private static final long serialVersionUID = 1L;
	
	private Color glassColor = new Color(0, 0, 0, 0);
	
	public static final int BLUR = 0;
	public static final int GAUSSIAN_BLUR = 1;
	public static final int EDGE_DETECTION = 2;
	
	public BlurComponent(Dimension dimension) {
		setSizes(dimension);
	}
	
	public BlurComponent(Dimension dimension, Color glassColor) {
		this.glassColor = glassColor;
		setSizes(dimension);
	}
	
	private void setSizes(Dimension dimension) {
		setPreferredSize(dimension);
		setSize(dimension);
		setOpaque(false);
	}
	
	public void addEffect(BufferedImage src, int type,int intensity) {
		src = Utility.bufferedImageEffect(src, type, intensity);
		src = src.getSubimage(getX(), getY(), getWidth(), getHeight());
		setIcon(new ImageIcon(src));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(glassColor);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
