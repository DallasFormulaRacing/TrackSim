/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.TrackElementType.*;
import java.awt.geom.Arc2D;
import static java.awt.geom.Arc2D.OPEN;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * Class representing an individual track element; curves and straights
 * @author Josh
 */
public class TrackElement {

    private TrackElementType type;
    private double x0, y0, x1, y1, dx, dy, entryTheta, dTheta, exitTheta, radius, length, time;

    

    

   
    private Arc2D arc;
    private Line2D line = new Line2D.Double();

    /**
     * This constructor is for straight elements only
     *
     * @param t type of track element
     * @param x0 initial x pos
     * @param y0 initial y pos
     */
    public TrackElement(TrackElementType t, double x0, double y0, double length, double theta) {
        //TODO: this function still needs work
        this.x0 = x0;
        this.y0 = y0; 
        this.type = t; 
        this.x1 = x0 - (length * Math.cos(Math.toRadians(90 - (180 - theta))));
        this.y1 = y0 + (length * Math.sin(Math.toRadians(90 - (180 - theta))));
        this.entryTheta = theta;
        this.exitTheta = this.entryTheta;
        this.radius = -1;
        this.length = length;
        this.time = CarPhysics.elementTime(this);
        this.line.setLine(x0, y0, x1, y1);
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
    public TrackElement(TrackElementType t, double x0, double y0, double entryTheta, double dTheta, double radius) {

        //We're using a java2d arc element here to make some calculations easier
        this.arc = new Arc2D.Double();
       
        this.entryTheta = entryTheta; 
        this.exitTheta = this.entryTheta - dTheta;
        this.dTheta = this.entryTheta - this.exitTheta;
        
        //creating an arc is as follows: x and y are the coordinates of THE TOP LEFT CORNER OF A BOUNDING BOX, not the actual curve
        //next 2 params are the total width & height of the hypothetical ellipse this curve is part of.
        //since we want circular curves, width = height = diameter
        //next param is the initial angle of the curve followed by the angle extent of the curve
        //last param doesn't really matter
        //(negative angle CW positive angle CCW)
        this.arc.setArc(x0, y0 - radius, radius * 2, radius * 2 , this.entryTheta, dTheta, OPEN);
        
        //this is needed because if entryTheta != 180, x0 & y0 are not equal to the starting point of the arc
        double dx = arc.getStartPoint().getX() - x0;
        double dy = arc.getStartPoint().getY() - y0;

        this.x0 = arc.getStartPoint().getX() - dx;
        this.y0 = arc.getStartPoint().getY() - dy;
        this.x1 = arc.getEndPoint().getX() - dx;
        this.y1 = arc.getEndPoint().getY() - dy;
        this.type = t;  
        this.radius = radius;
        this.length = 2 * Math.PI * radius * (dTheta / 360);
        this.time = CarPhysics.elementTime(this);
        
        this.arc.setArc(this.x0 - dx, this.y0 - radius - dy, this.radius * 2, this.radius * 2, this.entryTheta, this.dTheta, OPEN);
        
        this.exitTheta = this.arc.getAngleStart() + this.arc.getAngleExtent();
        
    }

    
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
     
     public TrackElementType getType() {
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
         
         return length;
         
     }
     
     public Arc2D getArc(){
         if(type == CURVE){
              return arc;
         }else{
             return null;
         }
        
     }
     
     public void setX0(double x0) {
        this.x0 = x0;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }
    
    public void setEntryTheta(double entryTheta) {
        this.entryTheta = entryTheta;
    }

    public void setExitTheta(double exitTheta) {
        this.exitTheta = exitTheta;
    }
    
    
     public Line2D getLine(){
         return line;
     }
     
     public Rectangle2D getBoundingBox(){
         
         return arc.getBounds2D();
     }
     
      public double getTime() {
        return time;
    }
}
