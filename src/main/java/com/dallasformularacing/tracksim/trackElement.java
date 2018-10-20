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
 *
 * @author Josh
 */
public class trackElement {

    private trackElementType type;
    private double x0, y0, x1, y1, dx, dy, entryTheta, dTheta, exitTheta, radius;

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
        //TODO this function needs work
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
     * @param t
     * @param x0
     * @param y0
     * @param theta0
     * @param theta1
     * @param radius
     */
    public trackElement(trackElementType t, double x0, double y0, double entryTheta, double dTheta, double radius) {

        Arc2D arc = new Arc2D.Double();
        this.entryTheta = entryTheta; 
        this.exitTheta = this.entryTheta - dTheta;
        this.dTheta = this.entryTheta - this.exitTheta;
        
        //negative angle CW positive angle CCW
        arc.setArc(x0, y0 - radius, radius * 2, radius * 2 , this.entryTheta, -dTheta, OPEN);
        
        double dx = arc.getStartPoint().getX() - x0;
        double dy = arc.getStartPoint().getY() - y0;
        
        this.x0 = arc.getStartPoint().getX() - dx;
        this.y0 = arc.getStartPoint().getY() - dy;
        this.x1 = arc.getEndPoint().getX() - dx;
        this.y1 = arc.getEndPoint().getY() - dy;
        this.type = t;
        
        
        this.radius = radius;
    }

    public String[] getData() {

        String[] s = {type.toString(), Double.toString(x0), Double.toString(y0), Double.toString(x1), Double.toString(y1), Double.toString(entryTheta), 
                        Double.toString(dTheta), Double.toString(exitTheta), Double.toString(radius)};
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
     
     public double getCurveLength(){
         
         return (2 * Math.PI * radius * (dTheta / 360) );
     }
     
     
}
