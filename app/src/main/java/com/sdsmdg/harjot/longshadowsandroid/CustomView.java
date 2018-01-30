package com.sdsmdg.harjot.longshadowsandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Harjot on 18-Jun-17.
 */

public class CustomView extends View {

    ArrayList<Path> paths;
    Paint paint;

    float offsetX, offsetY;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {

        offsetX = 0.0f;
        offsetY = 0.0f;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#121212"));
        paint.setStyle(Paint.Style.FILL);
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (paths != null && paths.size() > 0) {
            for (Path path : paths) {
                path.offset(offsetX, offsetY);
                canvas.drawPath(path, paint);
            }
        }
    }
}
