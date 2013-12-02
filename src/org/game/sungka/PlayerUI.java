package org.game.sungka;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlayerUI extends JDialog implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Player> playerList;
	
	public Player player;
	private String playerName;
	private int _size;
	
	private static int counterPlayer = 1;
	
	// private fields
	private JLabel playerLabel;
	private JTextField nameField;
	private JButton btnOk;
	
	public PlayerUI(int size) {
		this._size = size;
	}
	
	public PlayerUI(JFrame frame, String title){
		super(frame, title, true);
		this.setLayout(new FlowLayout());
		playerLabel = new JLabel("Player" + counterPlayer + " name: ");
		nameField = new JTextField(15);
		btnOk = new JButton("Ok");
		btnOk.addActionListener(this);
		add(playerLabel);
		add(nameField);
		add(btnOk);		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		counterPlayer++;
	}
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(btnOk)) {
			player = new Player();
			playerName = nameField.getText();
			if(player.validateName(playerName)) {				
				player.setName(playerName);
				this.addToPlayerList(player);				
				this.setVisible(false);	
			} else {									
				JOptionPane.showMessageDialog(null, "Invalid");			
			}			
		}
	}
	
	private void addToPlayerList(Player p) {
		playerList = new ArrayList<Player>();
		playerList.add(p);
	}
	
	public String getPlayer() {
		String str = "";
		for(Player p : playerList)
			str += p + "\n"; 
		return str;
	}
	
	public int getNumberofPlayer() {
		return this._size;
	}
	
	public void centerScreen() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 3);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);
	}
	
}
