/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Josh
 */
public class PopupMenu extends JPopupMenu {
    JMenuItem inspect = new JMenuItem("Inspect");
    
    public PopupMenu(TrackElement t){
        
        inspect.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               
               
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {

               InspectWindow w = new InspectWindow(t);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
               
            }
        });
        
        
        add(inspect);
    }
}
