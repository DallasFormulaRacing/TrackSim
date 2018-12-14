/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.TrackElementType.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Josh
 */
//panel for controls
public class SidePanel extends JPanel {

    private JSlider radiusSlider, angleSlider;
    private JTextField radiusField, angleField;
    private JLabel radiusLabel, angleLabel, entryAngleLabel, exitAngleLabel, deltaAngleLabel, pos0Label, pos1Label, totalTimeLabel;
    private JButton addButton, finishButton;
    private JToggleButton reverseButton;
    private TrackElement t;
    private ButtonGroup typeButtons;
    private JRadioButton straightRButton, curveRButton;

    private double radius, deltaAngle = 0;
    private boolean isReversed = false;
    
    private TrackElementType elementState = CURVE;
    
    public SidePanel(int width, int height) {

        //this panel will use a box layout (items ordered vertically)
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(this.getPreferredSize());

        //add a border & bg
        javax.swing.border.Border b = BorderFactory.createLineBorder(Color.DARK_GRAY);
        this.setBorder(b);
        this.setBackground(Color.LIGHT_GRAY);
        
        
        
         /*
        *-----------------------
        *CODE FOR RADIUS CONTROLS
        *-----------------------
        */
         
        //add label for radius slider
        radiusLabel = new JLabel();
        radiusLabel.setText("Radius");
        radiusLabel.setPreferredSize(new Dimension(this.getPreferredSize().width, 25));
        radiusLabel.setMaximumSize(radiusLabel.getPreferredSize());
        this.add(radiusLabel);

        //Set up radius slider
        radiusSlider = new JSlider();
        radiusSlider.setMaximum(1000);
        radiusSlider.setMinimum(0);
        radiusSlider.setValue(0);
        radiusSlider.setPreferredSize(new Dimension(this.getPreferredSize().width, 30));
        radiusSlider.setMaximumSize(radiusSlider.getPreferredSize());
        this.add(radiusSlider);

        //radius slider listener
        radiusSlider.addChangeListener((ChangeEvent e) -> {
            //create new slider object to represent the event state
            //set radius and update the text field
            JSlider src = (JSlider) e.getSource();
            radius = src.getValue();
            radiusField.setText(Integer.toString(src.getValue()));
            
            //render the element in TrackPanel
            createElement();
            updateFields();
        });

        //Add text field for radius
        radiusField = new JTextField();
        radiusField.setPreferredSize(new Dimension(this.getPreferredSize().width / 2, 30));
        radiusField.setMaximumSize(radiusField.getPreferredSize());

        //radius text field listener
        radiusField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField src = (JTextField) e.getSource();
                radiusSlider.setValue(Integer.parseInt(src.getText()));
                
                createElement();            
                updateFields();
            }
        });
        this.add(radiusField);

        
        
        //a spacer
        this.add(new Spacer(this.getPreferredSize().width, 25, this.getBackground()));

        
        
        /*
        *-----------------------
        *CODE FOR ANGLE CONTROLS
        *-----------------------
        */
        //add label for angle
        angleLabel = new JLabel();
        angleLabel.setText("Angle");
        angleLabel.setPreferredSize(new Dimension(this.getPreferredSize().width, 25));
        angleLabel.setMaximumSize(angleLabel.getPreferredSize());
        this.add(angleLabel);

        //add angle slider
        angleSlider = new JSlider();
        angleSlider.setMaximum(90);
        angleSlider.setMinimum(-90);
        angleSlider.setValue(0);
        angleSlider.setPreferredSize(new Dimension(this.getPreferredSize().width, 30));
        angleSlider.setMaximumSize(angleSlider.getPreferredSize());
        this.add(angleSlider);

        //angle slider listener
        angleSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                //create new slider object to represent the event state
                //set angle and update the text field
                JSlider src = (JSlider) e.getSource();
                deltaAngle = src.getValue();
                angleField.setText(Integer.toString(src.getValue()));

                createElement();
                updateFields();

            }
        });

        //text field for angle slider
        angleField = new JTextField();
        angleField.setPreferredSize(new Dimension(this.getPreferredSize().width / 2, 30));
        angleField.setMaximumSize(angleField.getPreferredSize());
        this.add(angleField);

        //angle text field listener
        angleField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField src = (JTextField) e.getSource();
                angleSlider.setValue(Integer.parseInt(src.getText()));
                
                createElement();            
                updateFields();
            }
        });

     
        this.add(new Spacer(this.getPreferredSize().width, 25, this.getBackground()));
        
        
        
        
         /*
        *-----------------------
        *CODE FOR RADIO BUTTONS
        *-----------------------
        */
        //radio buttons
        typeButtons = new ButtonGroup();

        straightRButton = new JRadioButton();
        straightRButton.setText("Straight");
        straightRButton.setBackground(this.getBackground());

        curveRButton = new JRadioButton();
        curveRButton.setText("Curve");
        curveRButton.setBackground(this.getBackground());

        typeButtons.add(straightRButton);
        typeButtons.add(curveRButton);
        
        //some listeners
        curveRButton.addActionListener((ActionEvent e) -> {
           elementState = CURVE;
           radiusLabel.setText("Radius");
           
            createElement();            
            updateFields();
        });
        
        straightRButton.addActionListener((ActionEvent e) ->{
            elementState = STRAIGHT;
            radiusLabel.setText("Length");
            
            createElement();            
            updateFields();
        });
        
        this.add(straightRButton);
        this.add(curveRButton);

        
        
        
        this.add(new Spacer(this.getPreferredSize().width, 25, this.getBackground()));

        
        
        /*
        *-----------------------
        *CODE FOR REVERSE BUTTON
        *-----------------------
        */
        //reverse button
        reverseButton = new JToggleButton();
        reverseButton.setText("Reverse");
        reverseButton.addActionListener((ActionEvent e) -> {
            if (isReversed == false) {
                isReversed = true;
            } else {
                isReversed = false;
            }
            
            createElement();
            updateFields();
        });
        this.add(reverseButton);
        
        
        
        this.add(new Spacer(this.getPreferredSize().width, 10, this.getBackground()));
        
        
        
        /*
        *-----------------------
        *CODE FOR ADD BUTTON
        *-----------------------
        */
        //add button
        addButton = new JButton();
        addButton.setText("Add");

        //add button listener
        addButton.addActionListener((ActionEvent e) -> {
            TrackPanel.getInstance().addElement(t);
            TrackBuilder.getInstance().addElement(t);
            
            createElement();
            updateFields();
        });

        this.add(addButton);
        
        
        
        this.add(new Spacer(this.getPreferredSize().width, 10, this.getBackground()));
         
        
        
        finishButton = new JButton();
        finishButton.setText("Finish");
        finishButton.addActionListener((ActionEvent e) ->{
        
            TrackBuilder.getInstance().close();
        
        });
        
        this.add(finishButton);
        
        
        
        this.add(new Spacer(this.getPreferredSize().width, 10, this.getBackground()));
        
        
        
        /*
        *-----------------------
        *CODE FOR DIAGNOSTIC INFO
        *-----------------------
        */
        entryAngleLabel = new JLabel("Entry \u0398: -");
        exitAngleLabel = new JLabel("Exit \u0398: -");
        deltaAngleLabel = new JLabel("Delta \u0398: -");
        pos0Label = new JLabel("x0: - y0: -");
        pos1Label = new JLabel("x1: - y1: -");
        totalTimeLabel = new JLabel("Time: -");
        
        this.add(entryAngleLabel);
        this.add(exitAngleLabel);
        this.add(deltaAngleLabel);
        this.add(pos0Label);
        this.add(pos1Label);
        this.add(totalTimeLabel);
    }
    
    
    
    private void createElement() {

        double lastX = 0, lastY = 0, thisX, thisY, deltaX = 0, deltaY = 0;
        TrackElement lastElement;
        
        if(elementState == CURVE){
            
            //Some functionality for the reverse button. 
            
            if (isReversed == false) {
                if (TrackBuilder.getInstance().getAllElements().isEmpty()) {
                    t = new TrackElement(CURVE, 0, 0, 0, deltaAngle, radius);
                } else {
 
                    t = new TrackElement(CURVE, TrackBuilder.getInstance().getLastElement().getX1(), TrackBuilder.getInstance().getLastElement().getY1(),
                            TrackBuilder.getInstance().getLastElement().getExitTheta(), deltaAngle, radius);
                }

            } else {
                if (TrackBuilder.getInstance().getAllElements().isEmpty()) {
                    t = new TrackElement(CURVE, 0, 0, 180, deltaAngle, radius);
                } else {
                    
                    /*
                    TODO    this code contains a bug
                            the angle when reversing a curve element is almost correct but
                            doesn't exactly line up
                            (just play with the track builder to observe this effect)
                    */
                    lastX = TrackBuilder.getInstance().getLastX();
                    lastY = TrackBuilder.getInstance().getLastY();
                    lastElement = TrackBuilder.getInstance().getLastElement();
                    
                    if (lastElement.getExitTheta() > 0 && lastElement.getExitTheta() < 90){
                        
                        t = new TrackElement(CURVE, lastX, lastY,
                            270 - TrackBuilder.getInstance().getLastElement().getExitTheta() - deltaAngle, deltaAngle, radius);
                        
                    }else if (lastElement.getExitTheta() > 90 && lastElement.getExitTheta() < 180){
                        
                        t = new TrackElement(CURVE, lastX, lastY,
                            90 - TrackBuilder.getInstance().getLastElement().getExitTheta() - deltaAngle, deltaAngle, radius);
                        
                    }else if (lastElement.getExitTheta() > 180 && lastElement.getExitTheta() < 270){
                        
                        t = new TrackElement(CURVE, lastX, lastY,
                            270 - TrackBuilder.getInstance().getLastElement().getExitTheta() - deltaAngle, deltaAngle, radius);
                        
                    }else if (lastElement.getExitTheta() > 270 && lastElement.getExitTheta() < 360){
                        
                        t = new TrackElement(CURVE, lastX, lastY,
                            90 - TrackBuilder.getInstance().getLastElement().getExitTheta() - deltaAngle, deltaAngle, radius);
                        
                    }
                    

                    thisY = t.getY1();
                    thisX = t.getX1();

                    deltaX = thisX - lastX;
                    deltaY = thisY - lastY;

                    if (lastElement.getExitTheta() > 0 && lastElement.getExitTheta() < 90){
                        
                        t = new TrackElement(CURVE, lastX - deltaX, lastY - deltaY,
                            270 - TrackBuilder.getInstance().getLastElement().getExitTheta() - deltaAngle, deltaAngle, radius);
                        
                    }else if (lastElement.getExitTheta() > 90 && lastElement.getExitTheta() < 180){
                        
                        t = new TrackElement(CURVE, lastX - deltaX, lastY - deltaY,
                            90 - TrackBuilder.getInstance().getLastElement().getExitTheta() - deltaAngle, deltaAngle, radius);
                        
                    }else if (lastElement.getExitTheta() > 180 && lastElement.getExitTheta() < 270){
                        
                        t = new TrackElement(CURVE, lastX - deltaX, lastY - deltaY,
                            270 - TrackBuilder.getInstance().getLastElement().getExitTheta() - deltaAngle, deltaAngle, radius);
                        
                    }else if (lastElement.getExitTheta() > 270 && lastElement.getExitTheta() < 360){
                        
                        t = new TrackElement(CURVE, lastX - deltaX, lastY - deltaY,
                            90 - TrackBuilder.getInstance().getLastElement().getExitTheta() - deltaAngle, deltaAngle, radius);
                        
                    }
                    


                }
            }

            
            
            
            
        }else if(elementState == STRAIGHT){
            
            if(TrackBuilder.getInstance().getAllElements().isEmpty()){
                
            }else{
                
                lastX = TrackBuilder.getInstance().getLastX();
                lastY = TrackBuilder.getInstance().getLastY();
                
                t = new TrackElement(STRAIGHT, lastX, lastY, radius, TrackBuilder.getInstance().getLastExitTheta());
                
            }
            
            
        }
        
        
         TrackPanel.getInstance().drawSingleElement(t);
    }
    
   
      
    private void updateFields() {

        entryAngleLabel.setText("Entry \u0398: " + Double.toString(t.getEntryTheta()));
        exitAngleLabel.setText("Exit \u0398: " + Double.toString(t.getExitTheta()));
        deltaAngleLabel.setText("Delta \u0398: " + Double.toString(t.getdTheta()));
        pos0Label.setText("x0: " + Double.toString(Math.round(t.getX0())) + " y0: " + Double.toString(Math.round(t.getY0())));
        pos1Label.setText("x1: " + Double.toString(Math.round(t.getX1())) + " y1: " + Double.toString(Math.round(t.getY1())));
        totalTimeLabel.setText("Time: " + Double.toString(TrackBuilder.getInstance().getTime()));

    }

}
