package org.game.sungka;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

public class RoundButton extends JButton {
	
	/**
	 * SERIAL SUBVERSION ID
	 */
	private static final long serialVersionUID = 1L;
	
	private GradientPaint gradient =
		    new GradientPaint(0, 0, new Color(205,205,193), 0, 175, new Color(139,119,101),
		                      true); 
	
	public RoundButton(String label) {
	    super(label);

	// These statements enlarge the button so that it 
	// becomes a circle rather than an oval.
	    Dimension size = getPreferredSize();
	    size.width = size.height = Math.max(50, 
	      50);
	    setPreferredSize(size);

	// This call causes the JButton not to paint 
	   // the background.
	// This allows us to paint a round background.
	    setContentAreaFilled(false);
	    setOpaque(false);
	  }

	// Paint the round background and label.
	  protected void paintComponent(Graphics g) {	
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setPaint(gradient);
		
	    if (getModel().isArmed()) {
	   // You might want to make the highlight color 
	   // a property of the RoundButton class.
	      g2d.setColor(new Color(139, 125, 107));
	    } else {
	    	g2d.setColor(new Color(139, 125, 107));
	    }
	    
	    g2d.fillOval(0, 0, getSize().width-1, 
	  	      getSize().height-1);
	    
	// This call will paint the label and the 
	   // focus rectangle.
	    super.paintComponent(g2d);
	  }

	// Paint the border of the button using a simple stroke.
	  protected void paintBorder(Graphics g) {
	    g.setColor(new Color(139, 125, 107));
	    g.drawOval(0, 0, getSize().width-1, 
	      getSize().height-1);
	  }

	// Hit detection.
	  Shape shape;
	  public boolean contains(int x, int y) {
	// If the button has changed size, 
	   // make a new shape object.
	    if (shape == null || 
	      !shape.getBounds().equals(getBounds())) {
	      shape = new Ellipse2D.Float(0, 0, 
	        getWidth(), getHeight());
	    }
	    return shape.contains(x, y);
	  }
	  
	   		  
}
