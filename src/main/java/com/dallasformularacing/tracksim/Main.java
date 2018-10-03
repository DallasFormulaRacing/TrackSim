/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.trackElementType.*;
import com.opencsv.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilePermission;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;


/**
 *
 * @author Josh
 */
class Main {
    
    private static String filename = "test.csv";

   
    public static void main(String[] args) {
        
        
        trackBuilder t = new trackBuilder(filename);
        //t.addElement(new trackElement(STRAIGHT,0,0,0,10));
        //t.addElement(new trackElement(CURVE, t.getLastX(), t.getLastY(), t.getLastTheta(),45, 100));
       t.addElement(new trackElement(CURVE, 0, 0, 0, -45, 10));
       
        t.close();
        
    }
    
    
    
    
    
}
