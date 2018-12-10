/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dallasformularacing.tracksim;

import static com.dallasformularacing.tracksim.TrackElementType.CURVE;

/**
 * Class to calculate track time and eventually other physics too
 *
 * @author Josh
 */
public class CarPhysics {

    //these are roughly the specs of a 4th gen ND Miata, in m/s
    private static double totalTime = 0;
    private static double gLat = 0.88;
    private static double aLat = gLat * 9.8;
    private static double aLong = 4.39;

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
            time = Math.sqrt(t.getLength() / aLong);
            totalTime += time;
            return time;
        }
    }

}
