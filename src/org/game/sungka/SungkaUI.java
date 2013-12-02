package org.game.sungka;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.Border;


public class SungkaUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar = new JMenuBar();
	private JLabel status;
	private JMenu menuGame, helpGame;
	private JMenuItem[] menuGameItem, helpGameItem;
	
	private String[] menuItemStr = { "New Game", "Exit" };
	private String[] helpItemStr = { "About" };
	
	private PlayerUI[] playerui;
	
	private MainScreen mainScreen = new MainScreen();
	
	private SungkaGraph sungkaGraph = new SungkaGraph();
	
	public SungkaUI(String title){
		super(title);
		mainMenu();
		status = new JLabel("Play the game now!");
		add(BorderLayout.SOUTH, status);		
		add(sungkaGraph, BorderLayout.CENTER);
		this.settings();
	}
	
	private void mainMenu() {
		setJMenuBar(menuBar);
		menuGame = new JMenu("Game");
		initializeMenu(menuGame, menuGameItem, menuItemStr);
		helpGame = new JMenu("Help");
		initializeMenu(helpGame, helpGameItem, helpItemStr);
		menuBar.add(menuGame);
		menuBar.add(helpGame);
	}
	
	private void initializeMenu(JMenu menu, JMenuItem menuItems[], String[] strItems) {
		menuItems = new JMenuItem[strItems.length];
		for(int i = 0; i < menuItems.length; i++) {
			menuItems[i] = new JMenuItem(strItems[i]);
			addEvent(menuItems[i]);
			menu.add(menuItems[i]);
		}
	}
	
	private void addEvent(JMenuItem item) {
		item.addActionListener(
			new ActionListener() {						
				public void actionPerformed(ActionEvent event) {
					if(event.getActionCommand() == "New Game") {
						playerui = new PlayerUI[2];
						int j = 1;
						for(int i = 0; i < playerui.length; i++) {
							playerui[i] = new PlayerUI(null, "Enter name for player" + String.valueOf(j) + ": ");
							playerui[i].centerScreen();
							playerui[i].setSize(300,120);
							playerui[i].setVisible(true);
							j++;
						}
						sungkaGraph.setPlayerStat(status);
						sungkaGraph.setCountPlayer(playerui);
						sungkaGraph.validate();
						sungkaGraph.repaint();
					} else if(event.getActionCommand() == "About"){
						String msg = "Program by: Jessie S. Labadan\n" +
									 "This is a free license program\n" +
									 "Sungka v1.0";
						JOptionPane.showMessageDialog(null, msg, "About", 
								JOptionPane.INFORMATION_MESSAGE);						
					}	else if(event.getActionCommand() == "Exit"){
						System.exit(0);						
					}							
				}

		});
	}
		
	private void settings(){
		this.setSize(750, 220);
		this.setVisible(true);
		centerScreen();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void centerScreen() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);
	}
	
}
