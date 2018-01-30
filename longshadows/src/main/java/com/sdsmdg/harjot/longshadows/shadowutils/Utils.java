package com.sdsmdg.harjot.longshadows.shadowutils;

import android.graphics.Path;
import android.util.Log;

import com.sdsmdg.harjot.longshadows.models.Point2D;

/**
 * Created by Harjot on 28-Jan-18.
 */

public class Utils {

    public static Path getPathFromPoints(Point2D[] points) {
        Path path = new Path();

        if (points.length == 0) {
            Log.d("PATH_FROM_POINTS", "0 length points");
            return null;
        }

        path.moveTo(points[0].getX(), points[0].getY());
        for (int i = 1; i < points.length; i++) {
            if (points[i].getX() == -1 && points[i].getY() == -1) {
                if (i + 1 < points.length) {
                    path.moveTo(points[i + 1].getX(), points[i + 1].getY());
                }
                i++;
            } else {
                path.lineTo(points[i].getX(), points[i].getY());
            }
        }

        return path;
    }

}
