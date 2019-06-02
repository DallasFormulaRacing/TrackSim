/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Josh
 */
public class DeselectElementHandler extends AbstractAction{

    
    public DeselectElementHandler(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        TrackPanel obj;
        
        if(e.getSource().equals(TrackPanel.getInstance())){
            
            obj = (TrackPanel)e.getSource();
            obj.deselectElement();
            
        }
    }
    
}
