/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.trackElementType.*;
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

    private static String filename = "test.csv";

    public static void main(String[] args) {

        trackBuilder t = new trackBuilder(filename);
        carPhysics phys = new carPhysics();

        //This is the structure for creating track elements
        //why does the initial angle start at 180*, you may ask yourself
        //it just be like that
        
        /*
        t.addElement(new trackElement(CURVE, 0, 0, 180, 45, 100));
        t.addElement(new trackElement(STRAIGHT, t.getLastX(), t.getLastY(), 100, t.getLastExitTheta()));
        t.addElement(new trackElement(CURVE, t.getLastX(), t.getLastY(), t.getLastExitTheta(), 45, 100 ));
        t.addElement(new trackElement(STRAIGHT, t.getLastX(), t.getLastY(), 100, t.getLastExitTheta()));
        t.addElement(new trackElement(CURVE, t.getLastX(), t.getLastY(), t.getLastExitTheta(), 45, 100 ));
        t.addElement(new trackElement(CURVE, t.getLastX(), t.getLastY(), t.getLastExitTheta(), 45, 100 ));
        */
        
        
        
        //close I/O when we're done adding elements
        t.close();
        
        System.out.println("total: " + phys.totalTime());
        
        //base jframe init
        JFrame frame = new JFrame("Draw Arc Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(960, 640)); 
        
        //initialize all our componenets
        MainPanel mainPanel = new MainPanel();
        
        //TrackPanel will be used as a singleton object for modification in multiple classes
        TrackPanel.getInstance();
        
        SidePanel controls = new SidePanel(frame.getPreferredSize().width / 5, frame.getPreferredSize().height);
        
        
        //add elements to the panel for drawing
        for (trackElement e : t.getAllElements()) {
            TrackPanel.getInstance().addElement(e);
        }
  
        
        mainPanel.add(TrackPanel.getInstance(), BorderLayout.CENTER);
        mainPanel.add(controls, BorderLayout.EAST);
        
        //add shit to jframe and draw
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

        //add frame contents
        
        
        
    }

}
