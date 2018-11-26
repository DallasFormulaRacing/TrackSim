/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.trackElementType.CURVE;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Josh
 */
//panel for controls
public class SidePanel extends JPanel {

    private     JSlider     radiusSlider, angleSlider;
    private     JTextField  radiusField, angleField;
    private     JLabel      radiusLabel, angleLabel;
    private     JPanel      spacer;
    private     JButton     addButton;
    private     trackElement    t;
    private     ButtonGroup     typeButtons;
    private     JRadioButton    straight, curve; 
    
    private double radius, angle, length = 0;
    
    public SidePanel(int width, int height) {

        //this panel will use a box layout (items ordered vertically)
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(this.getPreferredSize());

        
        
        //add a border & bg
        javax.swing.border.Border b = BorderFactory.createLineBorder(Color.DARK_GRAY);
        this.setBorder(b);
        this.setBackground(Color.LIGHT_GRAY);

        
        
        //add label for radius slider
        radiusLabel = new JLabel();
        radiusLabel.setText("Radius");
        radiusLabel.setPreferredSize(new Dimension(this.getPreferredSize().width, 25));
        radiusLabel.setMaximumSize(radiusLabel.getPreferredSize());
        this.add(radiusLabel);

        
        
        //Set up radius slider
        radiusSlider = new JSlider();
        radiusSlider.setMaximum(1000);
        radiusSlider.setMinimum(-1000);
        radiusSlider.setValue(0);
        radiusSlider.setPreferredSize(new Dimension(this.getPreferredSize().width, 30));
        radiusSlider.setMaximumSize(radiusSlider.getPreferredSize());
        this.add(radiusSlider);

        
        
        //radius slider listener
        radiusSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                //create new slider object to represent the event state
                //set radius and update the text field
                JSlider src = (JSlider) e.getSource();
                radius = src.getValue();
                radiusField.setText(Integer.toString(src.getValue()));
                
                if(radius > 0 ){
                    t = new trackElement(CURVE, 0, 0, 180, -angle, radius);
                }else if(radius < 0){
                    
                    t = new trackElement(CURVE, 0, 0, 0, -angle, Math.abs(radius));
                }
                

                TrackPanel.getInstance().drawSingleElement(t);
                
            }
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
            }
        });
        this.add(radiusField);

        
        
        //add a spacer
        spacer = new JPanel();
        spacer.setBackground(this.getBackground());
        spacer.setPreferredSize(new Dimension(this.getPreferredSize().width, 25));
        spacer.setMaximumSize(spacer.getPreferredSize());
        this.add(spacer);

        
        
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
                angle = src.getValue();
                angleField.setText(Integer.toString(src.getValue()));
               
                if(radius > 0 ){
                    t = new trackElement(CURVE, 0, 0, 180, -angle, radius);
                }else if(radius < 0){
                    
                    t = new trackElement(CURVE, 0, 0, 0, -angle, Math.abs(radius));
                }
                
                TrackPanel.getInstance().drawSingleElement(t);
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
            }
        });
       
        
        this.add(spacer);
        
        
        
        //radio buttons
        typeButtons = new ButtonGroup();
        
        straight = new JRadioButton();
        straight.setText("Straight");
        
        curve = new JRadioButton();
        curve.setText("Curve");
        
        typeButtons.add(straight);
        typeButtons.add(curve);
        //TODO add button listener
        this.add(straight);
        this.add(curve);
        
        
        
        this.add(spacer);
        
        //add button
        addButton = new JButton();
        addButton.setText("Ass");
        
        
       
       
        //add button listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                
            }
        });
        
        this.add(addButton);
    }

}