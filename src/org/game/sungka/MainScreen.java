package org.game.sungka;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainScreen extends JPanel {

	/**
	 * SERIAL SUB-VERSION ID
	 */
	private static final long serialVersionUID = 1L;
	
	private GradientPaint gradient =
		    new GradientPaint(0, 0, new Color(205,205,193), 0, 175, new Color(139,119,101),
		                      true); 
	
	public MainScreen() {
		setLayout(new FlowLayout());
		//generateImage();
		setPreferredSize(new Dimension(600, 200));
		setOpaque(false);					
	}
	
	private void generateImage() {
		Icon icon = new ImageIcon("sungka.png");
		JLabel labelIcon = new JLabel();
		labelIcon.setIcon(icon);
		add(labelIcon, BorderLayout.NORTH);
	}
	
	public void paintComponent(Graphics g) {		
		Graphics2D g2d = (Graphics2D) g;		
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
		
        // paint 
        super.paintComponent(g);
        
        // draw user home
        g.setColor(new Color(139,119,101));     
        g.setFont(new Font("Arial", Font.BOLD, 46));
        g.setColor(Color.magenta);
        g.drawString("PINOY GAME SUNGKA", 10, 50);
    }//end paintComponent
	
}
