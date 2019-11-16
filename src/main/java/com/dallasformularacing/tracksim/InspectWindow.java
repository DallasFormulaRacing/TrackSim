/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.TrackElementType.CURVE;
import static com.dallasformularacing.tracksim.TrackElementType.STRAIGHT;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Josh
 */
public class InspectWindow extends JFrame {
    
    private JTable table;
    
    public InspectWindow(TrackElement t){
        
        this.setTitle("TrackSim Inspect Element");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(640,720));
        this.setResizable(false);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        panel.setMinimumSize(new Dimension(this.getWidth(), this.getHeight()));
        
        
        if(t.getType() == STRAIGHT){
            //create a table of appropriate length with headers
            String[] header ={"position", "velocity", "vNot", "vFin", "time"};
            Object[][] tmparr = new Object[t.getPosition().size()][5];
            table = new JTable(tmparr, header);
            

            for(int i = 0; i < t.getPosition().size(); i++){

                table.setValueAt(t.getPosition().get(i), i, 0);
                table.setValueAt(t.getVelocity().get(i), i, 1);
                table.setValueAt(t.getvNot(), i, 2);
                table.setValueAt(t.getvFin(), i, 3);
                table.setValueAt(t.getTime(), i, 4);
            }

        }else if(t.getType() == CURVE){
            String[] header ={"radius", "vNot", "vFin"};
            Object[][] tmparr = new Object[2][3];
            table = new JTable(tmparr, header);
            table.setValueAt(t.getRadius(), 0, 0);
            table.setValueAt(t.getvNot(), 0, 1);
            table.setValueAt(t.getvFin(), 0, 2);

        }
        
        table.setEnabled(false);
        table.setFillsViewportHeight(true);
        JScrollPane scrollpane = new JScrollPane(table);

        panel.add(scrollpane, BorderLayout.WEST);
        
        add(panel);        
        pack();
        setVisible(true);
    }
}
