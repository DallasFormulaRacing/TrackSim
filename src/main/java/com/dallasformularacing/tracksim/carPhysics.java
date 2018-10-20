/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.trackElementType.CURVE;

/**
 *
 * @author Josh
 */


public class carPhysics {
    
    final double weight = 1000;
    double time = 0;
  
    
    public double getTime(){
        
        
        return time;
    }
    
    
    
    public double elementTime(trackElement t){
        
        double velocity, time;
        
        if(t.getType() == CURVE){
            
            velocity = Math.sqrt(t.getRadius() * 1);
            //return t.getCurveLength() / velocity
            
        }else{
            
        }
        
        
        
     return 0;   
    }
    
    
    
}
