/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.trackElementType.*;
import java.awt.geom.Arc2D;
import static java.awt.geom.Arc2D.OPEN;

/**
 * Class representing an individual track element; curves and straights
 * @author Josh
 */
public class trackElement {

    private trackElementType type;
    private double x0, y0, x1, y1, dx, dy, entryTheta, dTheta, exitTheta, radius, time;

    /**
     * This constructor is for straight elements only
     *
     * @param t type of track element
     * @param x0 initial x pos
     * @param y0 initial y pos
     * @param x1 ending x pos
     * @param y1 ending y pos
     */
    public trackElement(trackElementType t, double x0, double y0, double length, double theta) {
        //TODO: this function still needs work
        this.x0 = x0;
        this.y0 = y0; 
        this.type = t; 
        this.x1 = x0 + (length * Math.cos(Math.toRadians(theta)));
        this.y1 = y0 - (length * Math.sin(Math.toRadians(theta)));
        this.entryTheta = theta;
        this.exitTheta = this.entryTheta;
        this.radius = -1;
    }

    /**
     * This constructor is for curve elements only
     *
     * @param t element type (always CURVE for this constructor)
     * @param x0 x coord OF THE BOUNDING BOX
     * @param y0 y coord OF THE BOUNDING BOX
     * @param entryTheta angle that the turn begins at
     * @param dTheta angle of the turn itself
     * @param radius
     */
    public trackElement(trackElementType t, double x0, double y0, double entryTheta, double dTheta, double radius) {

        //We're using a java2d arc element here to make some calculations easier
        Arc2D arc = new Arc2D.Double();
        this.entryTheta = entryTheta; 
        this.exitTheta = this.entryTheta - dTheta;
        this.dTheta = this.entryTheta - this.exitTheta;
        
        //creating an arc is as follows: x and y are the coordinates of THE TOP LEFT CORNER OF A BOUNDING BOX, not the actual curve
        //next 2 params are the total width & height of the hypothetical ellipse this curve is part of.
        //since we want circular curves, width = height = diameter
        //next param is the initial angle of the curve followed by the angle extent of the curve
        //last param doesn't really matter
        //(negative angle CW positive angle CCW)
        arc.setArc(x0, y0 - radius, radius * 2, radius * 2 , this.entryTheta, -dTheta, OPEN);
        
        //this is needed because if entryTheta != 180, x0 & y0 are not equal to the starting point of the arc
        double dx = arc.getStartPoint().getX() - x0;
        double dy = arc.getStartPoint().getY() - y0;
        
        this.x0 = arc.getStartPoint().getX() - dx;
        this.y0 = arc.getStartPoint().getY() - dy;
        this.x1 = arc.getEndPoint().getX() - dx;
        this.y1 = arc.getEndPoint().getY() - dy;
        this.type = t;  
        this.radius = radius;
        
        this.time = carPhysics.elementTime(this);
    }

    
    //bunch of getter functions
    public String[] getData() {

        String[] s = {type.toString(), Double.toString(x0), Double.toString(y0), Double.toString(x1), Double.toString(y1), Double.toString(entryTheta), 
                        Double.toString(dTheta), Double.toString(exitTheta), Double.toString(radius), Double.toString(getLength()), Double.toString(time)};
        return s;
    }

    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getEntryTheta() {
        return entryTheta;
    }
    
    public double getdTheta() {
        return dTheta;
    }
    
    public double getExitTheta() {
        return exitTheta;
    }
     
     public trackElementType getType() {
        return type;
    }
     
     public double getRadius() {
        return radius;
    }
     
     public double getdx(){
         return dx;
     }
     
     public double getdy(){
         return dy;
     }
     
     public double getLength(){
         if(this.type == CURVE){
           return (2 * Math.PI * radius * (dTheta / 360) );  
         }else{
             return 0;
         }
         
     }
     
     
}
