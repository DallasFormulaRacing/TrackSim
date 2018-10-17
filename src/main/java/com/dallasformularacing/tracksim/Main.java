/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.trackElementType.*;
import com.opencsv.*;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilePermission;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 *
 * @author Josh
 */
class Main {

    private static String filename = "test.csv";

    public static void main(String[] args) {

        trackBuilder t = new trackBuilder(filename);

       // t.addElement(new trackElement(CURVE, 0, 0, 180, 45, 100));
       // t.addElement(new trackElement(CURVE, t.getLastX(), t.getLastY(), t.getLastExitTheta(), 45, 100));
       t.addElement(new trackElement(CURVE, 0, 0, 180, 30, 100));
       t.addElement(new trackElement(CURVE, t.getLastX(), t.getLastY(), t.getLastExitTheta(), 30, 100));
    t.addElement(new trackElement(CURVE, t.getLastX(), t.getLastY(), t.getLastExitTheta(), 65, 100));
        t.close();

        mainPanel p = new mainPanel();

        for (trackElement e : t.getAllElements()) {
            p.addElement(e);
        }

        JFrame frame = new JFrame("Draw Arc Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(p);
        frame.pack();
        frame.setSize(new Dimension(420, 420)); //nice
        frame.setVisible(true);

    }

}
