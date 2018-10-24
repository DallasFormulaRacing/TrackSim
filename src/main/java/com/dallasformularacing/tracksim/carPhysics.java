/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.trackElementType.CURVE;

/**
 * Class to calculate track time and eventually other physics too
 * @author Josh
 */


public class carPhysics {
    
   
    private static double totalTime = 0;
    private static double G = 0.9;
    private static double aRad = G * 9.8;
  
    
    public double totalTime(){

        return totalTime;
    }
    
    
    /**
     * 
     * @param t track element to calculate
     * @return time needed for car to complete this element
     */
    //TODO: this needs lots of work
    public static double elementTime(trackElement t){
        
        double velocity, time;
        
        if(t.getType() == CURVE){
            
            velocity = Math.sqrt(aRad * t.getRadius());
            time = t.getLength() / velocity;
            totalTime += time;
            return time;
            
        }else{
            
        }
        
        
        
     return 0;   
    }
    
    
    
}
