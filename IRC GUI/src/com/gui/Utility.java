package com.gui;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.IOException;

public class Utility {
	
	public static final int BLUR = 0;
	public static final int GAUSSIAN_BLUR = 1;
	public static final int EDGE_DETECTION = 2;
	
	public static BufferedImage createResizedBufferedImage(BufferedImage image, int width, int height) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}

	public static BufferedImage cropBufferedImage(BufferedImage src, int width, int height) throws IOException {
		int x = src.getWidth() / 2 - width / 2;
		int y = src.getHeight() / 2 - height / 2;

		BufferedImage clipping = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);// src.getType());
		Graphics2D area = (Graphics2D) clipping.getGraphics().create();
		area.drawImage(src, 0, 0, clipping.getWidth(), clipping.getHeight(), x, y, x + clipping.getWidth(),
				y + clipping.getHeight(), null);
		area.dispose();
		return clipping;
	}
	
	public static BufferedImage bufferedImageEffect(BufferedImage src, int type, int intensity) {
		Kernel kernel = null;
		if(type == EDGE_DETECTION) {
			kernel = new Kernel(3,3, new float[] {
				-1f, -1f, -1f,
				-1f,  8f, -1f,
				-1f, -1f, -1f
			});
		}
		if(type == GAUSSIAN_BLUR) {
			kernel = new Kernel(3,3, new float[] {
					1f/16f, 2f/16f, 1f/16f,
					2f/16f, 4f/16f, 2f/16f,
					1f/16f, 2f/16f, 1f/16f
			});
		}
		if(type == BLUR) {
			kernel = new Kernel(3,3, new float[] {
					1f/9f, 1f/9f, 1f/9f,
					1f/9f, 1f/9f, 1f/9f,
					1f/9f, 1f/9f, 1f/9f
			});
		}
		
		ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_ZERO_FILL, null);
		
		for(int i = 0; i < intensity; i++) {
			src = op.filter(src, null);
		}
		return src;
	}

}
