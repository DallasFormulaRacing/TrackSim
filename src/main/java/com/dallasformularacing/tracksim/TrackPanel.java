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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Josh THIS CLASS IS TO BE USED AS A SINGLETON OBJECT
 */
public class TrackPanel extends JPanel implements MouseListener, MouseMotionListener{

    private LinkedList<TrackElement> elements = new LinkedList();
    private Graphics2D g2;
    private double x0, y0, x1, y1, dx, dy = 0;
    private TrackElement previewElement;
    private TrackElement selectedElement;
    private PopupMenu popup;
  

    //Types for singleton 
    private static TrackPanel instance;
    private static boolean instanceExists = false;
    
 

    /**
     * Constructor
     *
     */
    private TrackPanel() {

        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setBackground(Color.GRAY);  
        
        //keybind for deselecting an element
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "esc");
        this.getActionMap().put("esc", new DeselectElementHandler());
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
        //AffineTransform at = new AffineTransform();
        //at.translate(dx, dy);
        // g2.transform(at);
        //set (0,0) to the middle of the panel
        g2.translate(super.getWidth() / 2, super.getHeight() / 2);

        //draw some axis lines
        g2.drawLine(-5, 0, 5, 0);
        g2.drawLine(0, -5, 0, 5);

        //Draw all of the elements that have been added
        for (TrackElement t : elements) {

            if (t.getType() == CURVE) {
                g2.setColor(Color.ORANGE);
                g2.draw(t.getArc());
                //g2.draw(t.getBoundingBox());

            } else if (t.getType() == STRAIGHT) {

                g2.setColor(Color.BLACK);
                g2.draw(new Line2D.Double(t.getX0(), t.getY0(), t.getX1(), t.getY1()));

            }
        }

        //This is for drawing a single element
        //(live preview)
        if (previewElement == null) {

        } else {
            if (previewElement.getType() == CURVE) {

                g2.setColor(Color.GREEN);
                g2.draw(previewElement.getArc());
                g2.setColor(Color.red);
                g2.drawOval((int) Math.round(previewElement.getArc().getStartPoint().getX()) - 5, (int) Math.round(previewElement.getArc().getStartPoint().getY()) - 5, 10, 10);
                g2.setColor(Color.blue);
                g2.drawOval((int) Math.round(previewElement.getArc().getEndPoint().getX()) - 5, (int) Math.round(previewElement.getArc().getEndPoint().getY()) - 5, 10, 10);
                g2.setColor(Color.black);

            } else {

                g2.setColor(Color.GREEN);
                g2.draw(previewElement.getLine());
                g2.setColor(Color.black);
            }
        }

        //this is for the currently selected element
        if (selectedElement != null) {
            if (selectedElement.getType() == CURVE) {

                g2.setColor(Color.ORANGE);
                g2.draw(selectedElement.getArc());
                g2.setColor(Color.RED);
                g2.draw(selectedElement.getBoundingBox());
                g2.setColor(Color.BLACK);

            } else {

                g2.setColor(Color.BLACK);
                g2.draw(selectedElement.getLine());
                g2.setColor(Color.RED);
                g2.draw(selectedElement.getBoundingBox());
                g2.setColor(Color.BLACK);
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

        previewElement = t;
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

    @Override
    public void mouseClicked(MouseEvent e) {

            
            for (TrackElement t : elements) {

                if (t.getBoundingBox().contains(e.getX() - super.getWidth() / 2, e.getY() - super.getHeight() / 2)) {
                    selectedElement = t;
                    removeAll();
                    revalidate();
                    repaint();

                }
            }
            
      
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
        if(e.isPopupTrigger() && selectedElement != null){
            popup = new PopupMenu(selectedElement);
            popup.show(e.getComponent(), e.getX(), e.getY());
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        if(e.isPopupTrigger() && selectedElement != null){
            popup = new PopupMenu(selectedElement);
            popup.show(e.getComponent(), e.getX(), e.getY());
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

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
    
    public void deselectElement(){
        
        selectedElement = null;
        revalidate();
        repaint();
    }
    
    public TrackElement getSelectedElement(){
        return selectedElement;
    }
    
    

}
