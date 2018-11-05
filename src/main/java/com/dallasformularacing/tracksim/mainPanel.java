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
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import static java.awt.geom.Arc2D.PIE;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Josh
 */

        
public class mainPanel extends JPanel {
    
    private LinkedList<trackElement> elements = new LinkedList();
    private Graphics2D g2;
    private double x0, y0, x1, y1, dx, dy = 0;
    
    /**
     * Constructor
     * 
     */
    public mainPanel(){
        
        //Add a mouse listener to this panel. This does nothing yet
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
               
                if(x0 == 0 && y0 == 0 && x1 == 0 && y1 == 0){
                    x0 = e.getX();
                    y0 = e.getY();
                }else if(x0 != 0 && y0 != 0 && x1 == 0 && y1 == 0){
                    x1 = e.getX();
                    y1 = e.getY();
                }else if(x0 != 0 && y0 != 0 && x1 != 0 && y1 != 0){
                    
                    dx = dx + (x1 - x0);
                    dy = dy + (y1 - y0);
                    
                    System.out.println("dx: " + dx + " dy: " + dy);
                    
                    repaint();
                    
                    x0 = 0;
                    y0 = 0;
                    x1 = 0;
                    y1 = 0;

                }
                
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(mainPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                
               
               
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
        
        g2 = (Graphics2D) g;
        
        AffineTransform at = new AffineTransform();
        at.translate(dx, dy);
        g2.transform(at);
        
        
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
             
              g2.draw(t.getArc());
              
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
