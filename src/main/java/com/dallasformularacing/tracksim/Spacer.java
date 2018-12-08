/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Josh
 */
public class Spacer extends JPanel{
    
    public Spacer(int width, int height, Color bg){
        
        super();
        
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(this.getPreferredSize());
        this.setBackground(bg);
    }
    
    
}
