package com.sdsmdg.harjot.longshadows.models;

import java.util.ArrayList;

/**
 * Created by Harjot on 28-Jan-18.
 */

public class Contour {

    private ArrayList<Point2D> contours;

    public Contour(ArrayList<Point2D> contours) {
        this.contours = new ArrayList<>(contours);
    }

    public ArrayList<Point2D> getContours() {
        return contours;
    }

    public void setContours(ArrayList<Point2D> contours) {
        this.contours = contours;
    }
}
