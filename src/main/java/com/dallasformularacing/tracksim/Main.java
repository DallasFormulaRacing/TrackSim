/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.TrackElementType.*;
import com.opencsv.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilePermission;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

/**
 *
 * @author Josh
 */
class Main {

    //private static String filename = "test.csv";

    public static void main(String[] args) {

        
        
        //base jframe init
        JFrame frame = new JFrame("TrackSim beta");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(960, 640)); 
        
        //initialize all our componenets
        ParentPanel mainPanel = new ParentPanel();
        
        //TrackPanel will be used as a singleton object for modification in multiple classes
        TrackPanel.getInstance();
        
        SidePanel controls = new SidePanel(frame.getPreferredSize().width / 5, frame.getPreferredSize().height);
        
        
        //add elements to the panel for drawing
        
  
        
        mainPanel.add(TrackPanel.getInstance(), BorderLayout.CENTER);
        mainPanel.add(controls, BorderLayout.EAST);
        
        //add shit to jframe and draw
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

        //add frame contents
        
        
        
    }

}
