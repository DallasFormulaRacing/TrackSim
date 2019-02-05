/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.TrackElementType.CURVE;
import static com.dallasformularacing.tracksim.TrackElementType.STRAIGHT;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import static java.awt.geom.Arc2D.PIE;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Josh THIS CLASS IS TO BE USED AS A SINGLETON OBJECT
 */
public class TrackPanel extends JPanel {

    private LinkedList<TrackElement> elements = new LinkedList();
    private Graphics2D g2;
    private double x0, y0, x1, y1, dx, dy = 0;
    private TrackElement singleElement;

    //Types for singleton 
    private static TrackPanel instance;
    private static boolean instanceExists = false;

    /**
     * Constructor
     *
     */
    private TrackPanel() {

        //Mouse movement listener for panning action
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if (x0 == 0 && y0 == 0 && x1 == 0 && y1 == 0) {
                    x0 = e.getX();
                    y0 = e.getY();
                } else if (x0 != 0 && y0 != 0 && x1 == 0 && y1 == 0) {
                    x1 = e.getX();
                    y1 = e.getY();
                } else if (x0 != 0 && y0 != 0 && x1 != 0 && y1 != 0) {

                    dx = dx + 3 * (x1 - x0);
                    dy = dy + 3 * (y1 - y0);

                    //System.out.println("dx: " + dx + " dy: " + dy);
                    revalidate();
                    repaint();

                    x0 = 0;
                    y0 = 0;
                    x1 = 0;
                    y1 = 0;

                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TrackPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    /**
     * Paint method override for this panel
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {

        super.paint(g);

        g2 = (Graphics2D) g;

        //This creates the actual panning action based on values dx and dy
        //creates a transformation then applies it to the graphics object
        AffineTransform at = new AffineTransform();
        at.translate(dx, dy);
        g2.transform(at);

        //set (0,0) to the middle of the panel
        g2.translate(super.getWidth() / 2, super.getHeight() / 2);

        //draw some axis lines
        //TODO: draw better axis lines
        g2.drawLine(-5, 0, 5, 0);
        g2.drawLine(0, -5, 0, 5);

        //Draw all of our track elements
        for (TrackElement t : elements) {

            if (t.getType() == CURVE) {

                g2.draw(t.getArc());
                //g2.draw(t.getBoundingBox());

            } else if (t.getType() == STRAIGHT) {

                g2.draw(new Line2D.Double(t.getX0(), t.getY0(), t.getX1(), t.getY1()));

            }
        }

        //This is for drawing a single element
        //(live preview)
        if (singleElement == null) {

        } else {
            if (singleElement.getType() == CURVE) {
                
                g2.setColor(Color.GREEN);
                g2.draw(singleElement.getArc());
                g2.setColor(Color.red);
                g2.drawOval((int)Math.round(singleElement.getArc().getStartPoint().getX()) - 5, (int)Math.round(singleElement.getArc().getStartPoint().getY()) - 5, 10, 10);
                g2.setColor(Color.blue);
                g2.drawOval((int)Math.round(singleElement.getArc().getEndPoint().getX()) - 5, (int)Math.round(singleElement.getArc().getEndPoint().getY()) - 5, 10, 10);
                g2.setColor(Color.black);
                
                
            } else {
                
                g2.setColor(Color.GREEN);
                g2.draw(singleElement.getLine());
                g2.setColor(Color.black);
            }
        }

    }

    //Add an element to be drawn
    public void addElement(TrackElement t) {
        elements.add(t);
        revalidate();
        repaint();
    }

    public void drawSingleElement(TrackElement t) {
        
        singleElement = t;
        this.removeAll();
        revalidate();
        repaint();

    }

    //Singleton access method
    public static TrackPanel getInstance() {

        if (instanceExists == false) {

            instance = new TrackPanel();
            instanceExists = true;
            return instance;

        } else {
            return instance;
        }
    }

}
