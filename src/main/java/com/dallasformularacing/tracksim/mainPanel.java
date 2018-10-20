/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.trackElementType.CURVE;
import static com.dallasformularacing.tracksim.trackElementType.STRAIGHT;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import static java.awt.geom.Arc2D.PIE;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author Josh
 */

        
public class mainPanel extends JPanel {
    
    private LinkedList<trackElement> elements = new LinkedList();
    
    /**
     * Constructor
     * 
     */
    public mainPanel(){
        
        //Add a mouse listener to this panel. This does nothing yet
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                
               System.out.println("x: " + (e.getPoint().x - mainPanel.super.getWidth()/2) + "y: " + (e.getPoint().y - mainPanel.super.getHeight()/2) );
               
            }
        });
    }
    
    
    /**
     * Paint method override for this panel
     * @param g 
     */
    @Override
    public void paint(Graphics g){
        
        super.paint(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        //set (0,0) to the middle of the panel
        g2.translate(super.getWidth()/2, super.getHeight()/2);
        
        //draw some axis lines
        //TODO: draw better axis lines
        g2.drawLine(-5, 0, 5, 0);
        g2.drawLine(0,-5,0,5);
        
        //Draw all of our track elements
        //TODO: this is sort of broken
        for(trackElement t: elements){
            
          if(t.getType() == CURVE){  
              
              g2.draw(new Arc2D.Double(t.getX0() - t.getdx(), t.getY0() - t.getRadius() + t.getdy(), t.getRadius() * 2, t.getRadius() * 2, t.getEntryTheta(), -t.getdTheta(), PIE));
              
          }else if(t.getType() == STRAIGHT){
              
              g2.draw(new Line2D.Double(t.getX0(), t.getY0(), t.getX1(), t.getY1()));
              
          }
        }
    }
    
    //Add an element to be drawn
    public void addElement(trackElement t){
        elements.add(t);
    }
    

}
