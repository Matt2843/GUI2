package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MainContentPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JPanel leftPanel, rightPanel, rightPanel2;
	private Thread grow;
	
	public MainContentPane() {
		setNewLayout();
		addPanels();
		growRightPanel();
	}
	
	private void afterAnimation() {
		System.out.println("Kappochard");
		rightPanel2 = new JPanel();
		rightPanel2.setBackground(Color.orange);
		rightPanel2.setPreferredSize(SGeom.CHAT);
		remove(rightPanel);
		add(rightPanel2, BorderLayout.EAST);
		validate();
	}

	private void growRightPanel() {
		grow = new Thread(new Runnable() {
			public void run() {
				int hVel = 6;
				while(true) {
					if(rightPanel.getWidth() < SGeom.CHAT.getWidth()) {
						rightPanel.setPreferredSize(new Dimension(rightPanel.getWidth() + hVel, rightPanel.getHeight()));
						rightPanel.setSize(new Dimension(rightPanel.getWidth() + hVel, rightPanel.getHeight()));
					} else break;
					
					try {
						validate();
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				afterAnimation();
			}
		});
		grow.start();
	}

	private void addPanels() {
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(SGeom.NAVIGATIONOVERVIEW);
		leftPanel.setBackground(Style.backgroundC);
		
		rightPanel = new JPanel();
		rightPanel.setSize(new Dimension(leftPanel.getHeight(), 0));
		rightPanel.setBackground(Color.white);
		
		add(leftPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
	}

	private void setNewLayout() {
		setPreferredSize(SGeom.MAINFRAME);
		setSize(SGeom.MAINFRAME);
		setLayout(new BorderLayout());
		setBorder(Style.buttonBorder);
	}

}
