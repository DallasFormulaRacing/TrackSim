/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;


import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josh
 */

public class trackBuilder {

    private static LinkedList<trackElement> elements = new LinkedList();
    private CSVReader csvr;
    private CSVWriter csvw;

    /**
     * 
     * @param filename filename of csv file of track. Creates it if not exists
     */
    
    //constructor
    public trackBuilder(String filename) {

        //our key for the data elements in the csv file
        String[] CSVHeader = {"type", "x0", "y0", "x1", "y1", "entryTheta", "dTheta" , "exitTheta","radius"};
        String[] s;
        try {
            try{
                //try to obtain the path of the given filename
                //if exception throw, file does not exist
                Path p = Paths.get(filename);   
                FileReader fr = new FileReader(p.toFile());
                FileWriter fw = new FileWriter(p.toFile(), true);
                csvr = new CSVReader(fr);
                csvw = new CSVWriter(fw);
                
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("File not found, creating now");
                File f = new File(filename);
                f.createNewFile();
                FileReader fr = new FileReader(filename);
                FileWriter fw = new FileWriter(filename);
                csvr = new CSVReader(fr);
                csvw = new CSVWriter(fw);
                csvw.writeNext(CSVHeader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addElement(trackElement e) {
        elements.add(e);
        csvw.writeNext(e.getData());
    }
    
    public trackElement getLastElement(){
        return elements.getLast();
    }
    
    public void addFromPrevious(){
        /*
        TODO this function should add a new tackElement based on the previous element
        if next element is a curve, no restriction on direction
        if next element is a straight, it should be coming out of the curve at the exit angle of the curve (?)
        exit angle is given by 90-theta (???)
        */
    }

    public double getLastX() {
        return elements.getLast().getX1();
    }

    public double getLastY() {
        return elements.getLast().getY1();
    }
    
    public static double getLastEntryTheta(){
        return elements.getLast().getEntryTheta();
    }
    
    public static double getLastExitTheta(){
        return elements.getLast().getExitTheta();
    }
    

    public void close() {
        try {
            csvr.close();
            csvw.close();
        } catch (IOException ex) {
            Logger.getLogger(trackBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isTrackComplete() {
        //if the endpoints of the last element equal the start points of the first element
        if ((elements.getLast().getX1() == elements.getFirst().getX0()) && (elements.getLast().getY1() == elements.getFirst().getY0())) {
            return true;
        }
        return false;
    }

    public LinkedList<trackElement> getAllElements(){
        return elements;
    }
    

}
