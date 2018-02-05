package com.sdsmdg.harjot.longshadows.shadowutils;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.Log;

import com.sdsmdg.harjot.longshadows.models.Point2D;
import com.sdsmdg.harjot.longshadows.models.ShadowPath;

/**
 * Created by Harjot on 28-Jan-18.
 */

public class Utils {

    public static LinearGradient generateLinearGradient(ShadowPath shadowPath, int startColor, int endColor) {
        int startX = (shadowPath.getStartPointOne().getX() + shadowPath.getStartPointTwo().getX()) / 2;
        int startY = (shadowPath.getStartPointOne().getY() + shadowPath.getStartPointTwo().getY()) / 2;
        int endX = (shadowPath.getEndPointOne().getX() + shadowPath.getEndPointTwo().getX()) / 2;
        int endY = (shadowPath.getEndPointOne().getY() + shadowPath.getEndPointTwo().getY()) / 2;

        return new LinearGradient(startX, startY, endX, endY, startColor, endColor, Shader.TileMode.CLAMP);

    }

}
