package com.sdsmdg.harjot.longshadows.models;

/**
 * Created by Harjot on 2/14/2018.
 */

public class ShadowBitmap {

    private int[][] pixels;
    private int width;
    private int height;
    private Point2D startPoint;

    public ShadowBitmap(int[][] pixels, int width, int height, Point2D startPoint) {
        this.pixels = pixels;
        this.width = width;
        this.height = height;
        this.startPoint = startPoint;
    }

    public int[][] getPixels() {
        return pixels;
    }

    public void setPixels(int[][] pixels) {
        this.pixels = pixels;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point2D startPoint) {
        this.startPoint = startPoint;
    }
}
