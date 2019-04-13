/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.TrackElementType.CURVE;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;

/**
 * Class to calculate track time and eventually other physics too
 *
 * @author Josh
 */
public class CarPhysics {

    //these are roughly the specs of a 4th gen ND Miata, in m/s
    private static double totalTime = 0;
    private static double gLat = 0.90;
    private static double aLat = gLat * 9.8;
    private static double aLong = 4.39;
    private static double aBrake = -10.44;
    
    private static double x_end;    //these are constants for the recursive line partitioning function
    private static double y_end;
    

    public double totalTime() {

        return totalTime;
    }

    /**
     *
     * @param t track element to calculate
     * @return time needed for car to complete this element
     */
    //TODO: this needs lots of work
    public static double elementTime(TrackElement t) {

        double velocity, time;

        if (t.getType() == CURVE) {

            velocity = Math.sqrt(aLat * t.getRadius());
            time = t.getLength() / velocity;
            totalTime += time;
            return time;

        } else {
            time = Math.sqrt(Math.abs(t.getLength()) / aLong);
            totalTime += time;
            return time;
        }
    }
    
    
    public static double getVelocity(TrackElement t){
        
        double velocity;

        if (t.getType() == CURVE) {

            velocity = Math.sqrt(aLat * t.getRadius());
            return velocity;

        } else {
            velocity = Math.sqrt(aLong * t.getLength());
            return velocity;
        }
        
    }
    
    
    public static double getBrakingDistance(double vFinal, double vNot, TrackElement t){
        
        x_end = t.getX1();
        y_end = t.getY1();
        
        
        double distance =  t.getLength() + ((Math.pow(vFinal, 2) - Math.pow(vNot,2)) / (2*aBrake));
        if(distance < 0) distance = 0;
        if (distance >= t.getLength()) distance = t.getLength();
        return distance;
        
    }
    

   
    
    
    
    

}
