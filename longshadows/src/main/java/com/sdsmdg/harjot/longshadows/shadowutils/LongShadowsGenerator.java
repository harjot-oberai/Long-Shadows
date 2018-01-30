package com.sdsmdg.harjot.longshadows.shadowutils;

import android.graphics.Bitmap;
import android.graphics.Path;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.sdsmdg.harjot.longshadows.LongShadowsImageView;
import com.sdsmdg.harjot.longshadows.models.Point2D;
import com.sdsmdg.harjot.longshadows.models.ShadowPath;

import java.util.ArrayList;

/**
 * Created by Harjot on 27-Jan-18.
 */

public class LongShadowsGenerator {

    private ViewGroup viewGroup;

    static {
        System.loadLibrary("native-lib");
    }

    public LongShadowsGenerator(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    public void generate() {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof LongShadowsImageView) {

                if (!((LongShadowsImageView) view).isShadowDirty()) {
                    return;
                }

                view.buildDrawingCache();
                Bitmap bitmap = view.getDrawingCache();

                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int[] intArray = new int[width * height];
                bitmap.getPixels(intArray, 0, width, 0, 0, width, height);

                Log.d("TIME", "TIME_1");

                Point2D[] points = getContours(intArray, width, height);

                Log.d("TIME_CPP_START", "TIME_2");

                ArrayList<ArrayList<Point2D>> allContours = new ArrayList<>();

                Log.d("TIME_CPP_END", "TIME_2");

                ArrayList<Point2D> contour = new ArrayList<>();

                ArrayList<ShadowPath> paths = new ArrayList<>();

                for (Point2D point : points) {
                    if (point.getX() != -1 && point.getY() != -1) {
                        contour.add(point);
                    } else {
                        allContours.add(new ArrayList<Point2D>(contour));
                        contour.clear();
                    }
                }

                Path temp = new Path();

                for (ArrayList<Point2D> contourPoints : allContours) {
                    int boundarySize = contourPoints.size() / 2;
                    for (int k = 0; k < boundarySize - 1; k++) {

                        Point2D pointFirst = contourPoints.get(k);
                        Point2D pointSecond = contourPoints.get(k + 1);
                        Point2D pointThird = contourPoints.get(k + boundarySize);
                        Point2D pointFourth = contourPoints.get(k + boundarySize + 1);

                        temp.moveTo(pointFirst.getX(), pointFirst.getY());
                        temp.lineTo(pointSecond.getX(), pointSecond.getY());
                        temp.lineTo(pointFourth.getX(), pointFourth.getY());
                        temp.lineTo(pointThird.getX(), pointThird.getY());

                        temp.close();
                        paths.add(new ShadowPath(new Path(temp), new Point2D[]{pointFirst, pointSecond, pointThird, pointFourth}, 45));
                        temp.reset();
                    }
                }


                Log.d("TIME", "TIME_3");

                ((LongShadowsImageView) view).setShadowPaths(paths);

                Log.d("TIME", "TIME_4");

            }
        }
    }

    public native Point2D[] getContours(int arr[], int width, int height);

}
