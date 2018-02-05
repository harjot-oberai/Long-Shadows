package com.sdsmdg.harjot.longshadows.shadowutils;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.sdsmdg.harjot.longshadows.LongShadowsImageView;
import com.sdsmdg.harjot.longshadows.LongShadowsTextView;
import com.sdsmdg.harjot.longshadows.models.ShadowPath;

import java.util.ArrayList;
import java.util.Arrays;

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

                LongShadowsImageView longShadowsImageView = (LongShadowsImageView) view;

                if (!longShadowsImageView.isShadowDirty()) {
                    return;
                }

                view.buildDrawingCache();
                Bitmap bitmap = view.getDrawingCache();

                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int[] intArray = new int[width * height];
                bitmap.getPixels(intArray, 0, width, 0, 0, width, height);

                Log.d("TIME_CPP_START", "TIME_1");

                ShadowPath[] paths = getContours(intArray,
                        width,
                        height,
                        longShadowsImageView.getShadowAngle(),
                        longShadowsImageView.getShadowLength());

                Log.d("TIME_CPP_END", "TIME_2");

                for (ShadowPath path : paths) {
                    path.constructPath();
                    Log.d("PATH", path.getStartPointOne().getX() + " : " + path.getStartPointOne().getY());
                    Log.d("PATH", path.getStartPointTwo().getX() + " : " + path.getStartPointTwo().getY());
                    Log.d("PATH", path.getEndPointOne().getX() + " : " + path.getEndPointOne().getY());
                    Log.d("PATH", path.getEndPointTwo().getX() + " : " + path.getEndPointTwo().getY());
                }


                Log.d("TIME", "TIME_3");

                longShadowsImageView.setShadowPaths(new ArrayList<>(Arrays.asList(paths)));

                Log.d("TIME", "TIME_4");

            } else if (view instanceof LongShadowsTextView) {

                LongShadowsTextView longShadowsTextView = (LongShadowsTextView) view;

                if (!longShadowsTextView.isShadowDirty()) {
                    return;
                }

                view.buildDrawingCache();
                Bitmap bitmap = view.getDrawingCache();

                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int[] intArray = new int[width * height];
                bitmap.getPixels(intArray, 0, width, 0, 0, width, height);

                Log.d("TIME_CPP_START", "TIME_1");

                ShadowPath[] paths = getContours(intArray,
                        width,
                        height,
                        longShadowsTextView.getShadowAngle(),
                        longShadowsTextView.getShadowLength());

                Log.d("TIME_CPP_END", "TIME_2");

                for (ShadowPath path : paths) {
                    path.constructPath();
                }

                Log.d("TIME", "TIME_3");

                longShadowsTextView.setShadowPaths(new ArrayList<>(Arrays.asList(paths)));

                Log.d("TIME", "TIME_4");

            }
        }
    }

    public native ShadowPath[] getContours(int arr[], int width, int height, float angle, int shadowLength);

}
