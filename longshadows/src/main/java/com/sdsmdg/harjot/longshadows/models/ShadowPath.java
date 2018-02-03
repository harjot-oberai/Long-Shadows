package com.sdsmdg.harjot.longshadows.models;

import android.graphics.Path;

/**
 * Created by Harjot on 30-Jan-18.
 */

public class ShadowPath {

    private Point2D[] points;
    private Point2D startPointOne, startPointTwo, endPointOne, endPointTwo;

    private Path path;

    private boolean isPathDirty = true;

    public ShadowPath(Point2D[] points, Point2D startPointOne, Point2D startPointTwo, Point2D endPointOne, Point2D endPointTwo) {
        this.points = points;
        this.startPointOne = startPointOne;
        this.startPointTwo = startPointTwo;
        this.endPointOne = endPointOne;
        this.endPointTwo = endPointTwo;
    }

    public Point2D[] getPoints() {
        return points;
    }

    public void setPoints(Point2D[] points) {
        this.points = points;
    }

    public Point2D getStartPointOne() {
        return startPointOne;
    }

    public void setStartPointOne(Point2D startPointOne) {
        this.startPointOne = startPointOne;
    }

    public Point2D getStartPointTwo() {
        return startPointTwo;
    }

    public void setStartPointTwo(Point2D startPointTwo) {
        this.startPointTwo = startPointTwo;
    }

    public Point2D getEndPointOne() {
        return endPointOne;
    }

    public void setEndPointOne(Point2D endPointOne) {
        this.endPointOne = endPointOne;
    }

    public Point2D getEndPointTwo() {
        return endPointTwo;
    }

    public void setEndPointTwo(Point2D endPointTwo) {
        this.endPointTwo = endPointTwo;
    }

    public void constructPath() {
        if (path == null) {
            path = new Path();
        } else if (isPathDirty) {
            path.reset();
        } else {
            return;
        }

        if (points.length > 0) {
            path.moveTo(points[0].getX(), points[0].getY());
            for (int i = 1; i < points.length; i++) {
                path.lineTo(points[i].getX(), points[i].getY());
            }
            path.close();
        }

    }

    public Path getPath() {
        return path;
    }

}
