package com.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private LoginContentPane loginContentPane;
	private MainContentPane mainContentPane;
	
	public MainWindow() {
		configureWindow();
		loginState();
		pack();
		setLocationRelativeTo(null);
		requestFocus();
		
		startTimer();	
	}
	
	private void mainState() {
		setContentPane(new JLabel());
		growWindow();
		mainContentPane = new MainContentPane();
		setContentPane(mainContentPane);
		pack();
		setLocationRelativeTo(null);
	}

	private void growWindow() {
		int hVel = 6, wVel = 6;
		while(true) {
			if (getWidth() < SGeom.MAINFRAME.getWidth() && getHeight() < SGeom.MAINFRAME.getHeight()) {
				setLocation(getX() - wVel/2, getY() - hVel/2);
				this.setPreferredSize(new Dimension(this.getWidth()+wVel, this.getHeight()+hVel));
			} else if (this.getWidth() < SGeom.MAINFRAME.getWidth()) {
				this.setLocation(this.getX() - wVel/2, this.getY());
				this.setPreferredSize(new Dimension(this.getWidth()+wVel, this.getHeight()));
			} else break;
			try {
				pack();
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}

	private void startTimer() {
		new Thread(new Runnable() {
			public void run() {
				int countdown = 5;
				while(true) {
					try {
						System.out.println(countdown);
						Thread.sleep(1000);
						countdown -= 1;
						if(countdown == 0) {
							break;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				loginContentPane.setStateDone(true);
				mainState();
				
			}
		}).start();
		
	}

	private void loginState() {
		loginContentPane = new LoginContentPane(this);
		setContentPane(loginContentPane);
		setVisible(true);
	}
	
	private void configureWindow() {
		setSize(SGeom.LOGINFRAME);
		setPreferredSize(SGeom.LOGINFRAME);
		setBackground(Style.backgroundC);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new MainWindow();
	}

}
