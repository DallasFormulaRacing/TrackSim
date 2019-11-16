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

/**
 * Class to calculate track time and eventually other physics too
 *
 * @author Josh
 */
public class CarPhysics {
	
    private static double gLat = 1.25;
    private static double aLat = gLat * 9.8;
    private static double x=0;
    private static double vi=0;
    private static double vf= Double.MAX_VALUE;
    private static double totalDistance=75;
    private static double accelerationG = 1*9.81;
    private static double brakeG = -1.6*9.81;

  
     /**
      * function now takes a TrackElement as a parameter and updates its fields instead of returning a time
      * @param t
      * @param vI
      * @param vF
      * @param distance
      * @param carAcc
      * @param brakeA
      */
     public static void timeCal( TrackElement t, double vI, double vF, double distance, double carAcc, double brakeA){

         double time_inc=0;

         double accDist=0;

         double remainingDist=0;

         double currentV=0;

         double afterBrakeV=0;

         do{

             time_inc+=.01;

             accDist=vI*time_inc+.5*carAcc*Math.pow(time_inc,2);

             remainingDist=distance-accDist;

             currentV=vI+carAcc*time_inc;
             
             t.addVelocity(currentV);
             t.addPosition(accDist);
             
             afterBrakeV=Math.pow(currentV,2)+2*brakeA*remainingDist;

             if(afterBrakeV<0){

                 continue;

             }

             afterBrakeV=Math.sqrt(afterBrakeV);

         }while(afterBrakeV<vF && accDist<distance);

         double brakeTime=(afterBrakeV-currentV)/brakeA; 
         
         t.setTime(time_inc + brakeTime);
         
         
         time_inc = 0;
         double vI_brk = currentV;
         double offset = accDist;
         
         //add remaining positions and velocities for braking in a straight
         do {
        	 
        	 time_inc+=.01;

             accDist=vI_brk*time_inc+.5*brakeA*Math.pow(time_inc,2) + offset;

             remainingDist=distance-accDist;

             currentV=vI_brk+brakeA*time_inc;
             
             t.addVelocity(currentV);
             t.addPosition(accDist);
         }while(remainingDist > 0);
         
         
     }

 
    
    /**
     * get static velocity for curved track elements
     * @param t curved track element to calculate velocity on
     * @return static velocity (vfin & vnot)
     */
    public static double getVelocity(TrackElement t) {

        double velocity;

        if (t.getType() == CURVE) {

            velocity = Math.sqrt(aLat * t.getRadius());
            return velocity;

        } else {

            return -1;
        }

    }
    
    
    public static double getAcc() {
    	return accelerationG;
    }
    
    public static double getBraking() {
    	return brakeG;
    }


    

}
