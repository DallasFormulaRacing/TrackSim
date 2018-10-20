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
    
   
    double time = 0;
  
    
    public double getTime(){
        
        
        return time;
    }
    
    
    /**
     * 
     * @param t track element to calculate
     * @return time needed for car to complete this element
     */
    //TODO: this needs lots of work
    public double elementTime(trackElement t){
        
        double velocity, time;
        
        if(t.getType() == CURVE){
            
           
            
        }else{
            
        }
        
        
        
     return 0;   
    }
    
    
    
}
