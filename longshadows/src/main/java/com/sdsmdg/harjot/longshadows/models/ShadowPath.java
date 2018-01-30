package com.sdsmdg.harjot.longshadows.models;

import android.graphics.Path;

/**
 * Created by Harjot on 30-Jan-18.
 */

public class ShadowPath {

    private Path path;
    private Point2D[] pathPoints;
    private float angle;

    public ShadowPath(Path path, Point2D[] pathPoints, float angle) {
        this.path = path;
        this.pathPoints = pathPoints;
        this.angle = angle;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Point2D[] getPathPoints() {
        return pathPoints;
    }

    public void setPathPoints(Point2D[] pathPoints) {
        this.pathPoints = pathPoints;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
