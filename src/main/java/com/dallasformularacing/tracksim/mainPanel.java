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
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(super.getWidth()/2, super.getHeight()/2);
        
        g2.drawLine(-5, 0, 5, 0);
        g2.drawLine(0,-5,0,5);
        
        for(trackElement t: elements){
          if(t.getType() == CURVE){  
              g2.draw(new Arc2D.Double(t.getX0(), t.getY0(), t.getRadius(), t.getRadius(), t.getTheta0(), t.getTheta1(), PIE));
          }else if(t.getType() == STRAIGHT){
              g2.draw(new Line2D.Double(t.getX0(), t.getY0(), t.getX1(), t.getY1()));
          }
        }
    }
    
    public void addElement(trackElement t){
        elements.add(t);
    }
}
