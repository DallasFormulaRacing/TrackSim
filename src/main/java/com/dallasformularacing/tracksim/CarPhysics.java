/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.TrackElementType.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import javafx.util.Pair;

/**
 * Class to calculate track time and eventually other physics too
 *
 * @author Josh
 */
public class CarPhysics {

    //these are roughly the specs of a 4th gen ND Miata, in m/s
    private static double totalTime = 0;
    private static double gLat = 1.25;
    private static double aLat = gLat * 9.8;
    private static double aLong = 0.6;
    private static double aBrake = -9.8; //1g
    private static double tireCoeff = 1.1;

    public double totalTime() {

        return totalTime;
    }

    /**
     *
     * @param t track element to calculate
     * @return time needed for car to complete this element
     */
    //TODO: completely redo this function to account for braking and accelerating 
    public static double elementTime(TrackElement t) {

        double velocity, time;

        if (t.getType() == CURVE) {

            velocity = Math.sqrt(tireCoeff * t.getRadius() * 9.8);
            time = t.getLength() / velocity;
            totalTime += time;
            return time;

        } else {
            time = Math.sqrt(Math.abs(t.getLength()) / aLong);
            totalTime += time;
            return time;
        }
    }

    public static double getVelocity(TrackElement t) {

        double velocity;

        if (t.getType() == CURVE) {

            velocity = Math.sqrt(aLat * t.getRadius());
            return velocity;

        } else {//TODO  uhh

            return -1;
        }

    }

    public static void getVelocity(double vNot, double vFin, TrackElement t) {

        if (t.getType() == STRAIGHT) {

            double time = 0;
            boolean isBraking = false;
            double distNot = 0;
            double distFin = t.getLength();
            double brkTime;
            double brkPos;
            
            double vel = vNot + aLong * time;
            double dist = distNot + vNot * time + 0.5 * aLong * Math.pow(time, 2);

            while(dist < distFin && !isBraking){
                vel = vNot + aLong * time;
                dist = distNot + vNot * time + 0.5 * aLong * Math.pow(time, 2);
                
                //brkTime = (vFin - vel) / aBrake;
                //brkPos = dist + vel * brkTime + 0.5 * aBrake * Math.pow(brkTime, 2);
                
                brkTime = Math.sqrt((2*(distFin - dist - vNot * time)) / aBrake);
               
                
                if((vel + aBrake * brkTime) <= vFin){
                    isBraking = true;
                    vNot = vel;
                    distNot = dist;
                    time = 0;
                }
                
                time += 0.001;
                
                t.addPosition(dist);
                t.addVelocity(vel);
                
            }
          
            while(dist < distFin && isBraking && vel >= vFin){
                
                vel = vNot + aBrake * time;
                dist = distNot + vNot * time + 0.5 * aBrake * Math.pow(time, 2);
                time += 0.001;
                
                t.addPosition(dist);
                t.addVelocity(vel);
               
                
            }
            

        } else {
            double vel = Math.sqrt(aLat * t.getRadius());
            
        }
        
    }

    

}
