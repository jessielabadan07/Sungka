package org.game.sungka;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JLabel;
import javax.swing.JPanel;

public class SungkaGraph extends JPanel implements ActionListener, Runnable {
	
	
    private JPanel panel = new JPanel();
	
	private RoundButton[] buttons = new RoundButton[15];
	private int userScore = 0;
	private int user2Score = 0;
	int numuserScore = 0, numcpu1Score = 0;
	int numcpu2Score = 0;
	int player1Score = 0;
	int player2Score = 0;
	private boolean player1Move = false;
	private boolean player2Move = false;
	
	private String player1Name, player2Name = "";
	/**
	 * SERIAL SUBVERSION ID
	 */
	private static final long serialVersionUID = 1L;
	
	private GradientPaint gradient =
		    new GradientPaint(0, 0, new Color(205,205,193), 0, 175, new Color(139,119,101),
		                      true); 
	
	private JLabel playerStat = new JLabel();
	
	public SungkaGraph(){		
		initializeRoundButton();
		setPreferredSize(new Dimension(600, 200));
		setOpaque(false);		  
	}
	
	public void setPlayerStat(JLabel status ) {
		playerStat = status;
	}
	
	public void setCountPlayer(PlayerUI[] playerui) {
		player1Name = playerui[0].player.getName();
		player2Name = playerui[1].player.getName();
		player1Move = true;		
		playerStat.setText(player1Name + " move");
	}
	
	public void paintComponent(Graphics g) {		
		
		add(panel, BorderLayout.CENTER);
		
		Graphics2D g2d = (Graphics2D) g;		
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
		
        // paint 
        super.paintComponent(g);
        
        // draw user home
        g.setColor(new Color(139,119,101));
        g.fillOval(20, 10, 100, 100);
        g.drawOval(20, 10, 100, 100);       
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.white);
        g.drawString(String.valueOf(userScore), 60, 70);
                
        // player 1
        initializePlayer(g, player1Name, player1Score, 20, 130);
        
        // player 2
        initializePlayer(g, player2Name, player2Score, 650, 130);
                
        // draw computer ai home
        g.setColor(new Color(139,119,101));
        g.fillOval(620, 10, 100, 100);        
        g.drawOval(620, 10, 100, 100);   
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.white);
        g.drawString(String.valueOf(user2Score), 665, 70);
    }//end paintComponent
	
	private void initializePlayer(Graphics g, String playerName, int score, int x, int y) {
		 g.setFont(new Font("Arial", Font.BOLD, 12));
		 g.drawString(String.valueOf(playerName), x, y);
		 g.drawString("Score: " + score, x, y+15);
	}
	
	private void initializeRoundButton() {		
		panel.setOpaque(false);
        panel.setLayout(new GridLayout(2, 7, 20, 20));        
        for(int i = 1; i <= 14; i++)
        {
        	buttons[i] = new RoundButton("7");    
        	buttons[i].setSize(100, 100);
        	buttons[i].setActionCommand(String.valueOf(i));
        	buttons[i].setForeground(Color.white);        	
        	buttons[i].addActionListener(this);
        	panel.add(buttons[i]);       	
        }
	}
	
	int buttonIndex = 0;
	int getDataValue = 0;
	
	public void actionPerformed(ActionEvent e)
	{		
		Thread game = new Thread(this);
		buttonIndex = Integer.parseInt(e.getActionCommand());	
		getDataValue = Integer.parseInt(buttons[buttonIndex].getText());
		game.start();
	}

	@Override
	public void run() {	
		int counter = 0;
		int cpuVal = 0;
		int defCpuRow = 8;		
		if((buttonIndex <= 7) && (getDataValue > 0) && player1Move == true) {
			buttons[buttonIndex].setText("0");
			for(int i = buttonIndex-1; i >= 0; i--) {
				try{
					if(i == 0) {
						userScore += 1;
						cpuVal = (getDataValue) - (counter+1);		
						for(int j = 1; j <= cpuVal; j++) {
							numcpu1Score = Integer.parseInt(buttons[defCpuRow].getText()) + 1;
							buttons[defCpuRow].setText(String.valueOf(numcpu1Score));
							System.out.println(defCpuRow);
							defCpuRow++;
							Thread.sleep(1000L);		
							player1Move = false;
							player2Move = true;
							playerStat.setText(player2Name + " move");
						}
						Thread.sleep(1000L);
					} else {		
						numuserScore = Integer.parseInt(buttons[i].getText()) + 1;
						buttons[i].setText(String.valueOf(numuserScore));
						counter++;
						Thread.sleep(1000L);
						player1Move = true;
						player2Move = false;
						playerStat.setText(player1Name + " move");
					}						
				} catch(InterruptedException e) {
				}
			} 
			numuserScore = buttonIndex; 
		} else if((buttonIndex > 7) &&  (getDataValue > 0) &&(player2Move == true)) {
			buttons[buttonIndex].setText("0");
			int tempVal = 0;
			int player1Dec = 7;
			for(int i = 1; i <= getDataValue; i++)
			{				
				try{ 
					tempVal = (buttonIndex + i);
					if(tempVal == 15) {
						user2Score += 1;
						player2Move = true;
						playerStat.setText(player2Name + " move");
					} else if(tempVal > 15) {
						numcpu2Score = Integer.parseInt(buttons[player1Dec].getText()) + 1;
						buttons[player1Dec].setText(String.valueOf(numcpu2Score));
						player1Dec--;
						player1Move = true;
						playerStat.setText(player1Name + " move");
					} else {	
						numcpu2Score = Integer.parseInt(buttons[tempVal].getText()) + 1;
						buttons[tempVal].setText(String.valueOf(numcpu2Score));
						player1Move = true;
						playerStat.setText(player1Name + " move");
					}
					Thread.sleep(1000L);
				} catch(InterruptedException e) {
				} // end catch
			}			
		}
		buttonIndex = 0;
		counter = 0;
		defCpuRow = 8;
		validate();
		repaint();
	}
	
}
