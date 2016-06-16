package com.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginContentPane extends JLabel implements MouseMotionListener, ComponentListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private MainWindow parent;
	
	private final int blurIntensity = 25;
	
	private int posX, posY;
	private boolean loginMode = true;
	private boolean stateDone = false;
	
	private BufferedImage background;
	
	private BlurComponent topDecoration;
	private BlurComponent mainPanel;
	
	private Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
	
	private HintTextField[] entries;
	
	private JLabel login, exit, newuser, statuslab;
	private JPanel mainPanelGrid;
	
	public LoginContentPane(MainWindow parent) {
		this.parent = parent;
		try {
			setContentPaneProperties();
			addTopDecoration();
			addMainPanel();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addMainPanel() {
		// Adding the main panel
		mainPanel = new BlurComponent(SGeom.LOGINMAINPANEL, Style.glassColor);
		mainPanel.addComponentListener(this);
		mainPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Style.foregroundC));
		mainPanel.setLayout(new FlowLayout());
		addComponent(mainPanel, BorderLayout.SOUTH);
		
		mainPanelGrid = new JPanel(new GridLayout(6, 1, 0, 5));
		mainPanelGrid.setOpaque(false);
		
		// Adding the status
		statuslab = new JLabel("Please log in :)");
		statuslab.setPreferredSize(new Dimension((int) (getWidth() * 0.9), mainPanel.getHeight() / 7));
		statuslab.setForeground(Style.foregroundC);
		statuslab.setFont(Style.font);
		mainPanelGrid.add(statuslab);
		
		// Adding the 3 textfields
		entries = new HintTextField[3];
		for(int i = 0; i < entries.length; i++) {
			entries[i] = new HintTextField("");
			decorateComponent(entries[i]);
			if(i > 0) {
				entries[i].makePasswordField();
			}
			mainPanelGrid.add(entries[i]);
		}
		
		entries[0].setHint("Username");
		entries[1].setHint("Password");
		entries[2].setHint("Repeat Password");
		entries[2].setVisible(false);
		
		// Adding the log in button
		login = new JLabel("LOG IN", JLabel.CENTER);
		decorateComponent(login);
		login.setOpaque(true);
		login.setBorder(Style.buttonBorder);
		login.addMouseMotionListener(this);
		login.addMouseListener(this);
		mainPanelGrid.add(login);
		
		newuser = new JLabel("Create new user");
		newuser.setFont(Style.font.deriveFont(fontAttributes));
		newuser.setForeground(Style.foregroundC);
		newuser.addMouseListener(this);
		newuser.addMouseMotionListener(this);
		mainPanelGrid.add(newuser);
		
		mainPanel.add(mainPanelGrid);
	}

	private void addTopDecoration() throws IOException {
		// Adding the tool bar to the contentpane.
		topDecoration = new BlurComponent(SGeom.LOGINDECORATION, Style.glassColor);
		topDecoration.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Style.foregroundC));
		topDecoration.addMouseMotionListener(this);
		addComponent(topDecoration, BorderLayout.NORTH);
		topDecoration.setLayout(new BorderLayout());
		
		// Adding the title to the tool bar.
		JLabel title = new JLabel("Client", JLabel.CENTER);
		title.setForeground(Style.foregroundC);
		title.setFont(Style.font);
		topDecoration.add(title, BorderLayout.CENTER);
		
		// Adding the exit button to the tool bar.
		exit = new JLabel(" X ", JLabel.CENTER);
		exit.setFont(Style.font.deriveFont(Font.BOLD));
		exit.setForeground(Style.foregroundC);
		exit.addMouseListener(this);
		topDecoration.add(exit, BorderLayout.EAST);
	}

	private void setContentPaneProperties() throws IOException {
		setPreferredSize(SGeom.LOGINFRAME);
		setSize(SGeom.LOGINFRAME);
		background = ImageIO.read(new File("resources/loginresources/background.jpg"));
		background = Utility.createResizedBufferedImage(background, getWidth(), getHeight());
		setBorder(BorderFactory.createLineBorder(Style.foregroundC));
		setIcon(new ImageIcon(background));
		setLayout(new BorderLayout());
		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	}
	
	private void decorateComponent(JComponent c) {
		c.setBorder(Style.paneBorder);
		c.setForeground(Style.foregroundC);
		c.setBackground(Style.backgroundC);
		c.setFont(Style.font);
		c.setOpaque(false);
	}
	
	private void addComponent(JComponent c, String constraints) {
		add(c, constraints);
		validate();
	}
	
	private void animateMainPanel() {
		topDecoration.setVisible(false);
		new Thread(new Runnable() {
			public void run() {
				Dimension maxDimension = new Dimension((int) (SGeom.LOGINFRAME.getWidth() * 0.95), (int) (SGeom.LOGINFRAME.getHeight()-1));
				Dimension minDimension = new Dimension((int) maxDimension.getWidth(), 1);
				int currentWidth = mainPanel.getWidth();
				int currentHeight = mainPanel.getHeight();
				
				while(true) {
					if(currentHeight > minDimension.getHeight()) {
						mainPanel.setPreferredSize(new Dimension(currentWidth, currentHeight -= 1));
						if(currentWidth > minDimension.getWidth()) {
							mainPanel.setPreferredSize(new Dimension(currentWidth -= 1, currentHeight));
						}
					} else {
						break;
					}
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				while(true) {
					if(currentHeight < maxDimension.getHeight()) {
						mainPanel.setPreferredSize(new Dimension(currentWidth, currentHeight += 1));
						if(currentWidth < maxDimension.getWidth()) {
							mainPanel.setPreferredSize(new Dimension(currentWidth += 1, currentHeight));
						}
					} else {
						break;
					}
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//JLabel loadingGif = new JLabel(new ImageIcon("resources/loginresources/loading.gif"));
				JLabel loading = new JLabel("... LOADING ...", JLabel.CENTER);
				decorateComponent(loading);
				loading.setFont(loading.getFont().deriveFont(20.0f));
				mainPanel.setLayout(new BorderLayout());
				mainPanel.add(loading, BorderLayout.CENTER);
				mainPanel.validate();
				
				while(true) {
					if(stateDone) {
						break;
					}
					if(loading.getText().equals("... LOADING ...")) {
						loading.setText(". LOADING .");
					} else if(loading.getText().equals(". LOADING .")) {
						loading.setText(".. LOADING ..");
					} else if(loading.getText().equals(".. LOADING ..")) {
						loading.setText("... LOADING ...");
					}
					
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(e.getSource() == topDecoration) {
			parent.setLocation(e.getXOnScreen() - posX, e.getYOnScreen() - posY);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		posX = e.getX(); posY = e.getY();
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {
		if(e.getSource() == mainPanel) {
			mainPanel.addEffect(background, BlurComponent.GAUSSIAN_BLUR, blurIntensity);
		}
		if(e.getSource() == topDecoration) {
			topDecoration.addEffect(background, BlurComponent.GAUSSIAN_BLUR, blurIntensity);
		}
	}


	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == newuser && newuser.getText().equals("Create new user")) {
			loginMode = false;
			entries[2].setVisible(true);
			statuslab.setText("User creation");
			login.setText("CREATE USER");
			newuser.setText("Already have a user?");
		} else if(e.getSource() == newuser && newuser.getText().equals("Already have a user?")) {
			loginMode = true;
			entries[2].setVisible(false);
			statuslab.setText("Please log in :)");
			login.setText("LOG IN");
			newuser.setText("Create new user");
		}
		
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == login) {
			login.setForeground(Style.highlight);
			login.setBorder(BorderFactory.createLineBorder(Style.highlight));
		}
		
		if(e.getSource() == login && !entries[0].getText().equals("") && !entries[1].getText().equals("") && loginMode) {
			mainPanelGrid.setVisible(false);
			animateMainPanel();
		}
		
		if(e.getSource() == login && !entries[0].getText().equals("") && !entries[1].getText().equals("") && !entries[2].getText().equals("") && !loginMode) {
			if(entries[1].getText().equals(entries[2].getText())) {
				System.out.println("CREATE USER");
			} else {
				System.out.println("Passwords not matching");
			}
			
		}
		
		if(e.getSource() == exit) {
			System.exit(0);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == login) {
			login.setForeground(Style.foregroundC);
			login.setBorder(Style.buttonBorder);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == newuser) {
			newuser.setForeground(Style.highlight);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == newuser) {
			newuser.setForeground(Style.foregroundC);
		}
		
	}

	public void setStateDone(boolean stateDone) {
		mainPanel.removeComponentListener(this);
		this.stateDone = stateDone;
	}

}
