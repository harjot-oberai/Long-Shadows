package com.sdsmdg.harjot.longshadows;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.sdsmdg.harjot.longshadows.models.ShadowPath;

import java.util.ArrayList;

/**
 * Created by Harjot on 28-Jan-18.
 */

public class LongShadowsImageView extends ImageView {

    private Context context;

    private ArrayList<ShadowPath> shadowPaths;
    private Paint shadowPaint;

    public LongShadowsImageView(Context context) {
        super(context);
        this.context = context;
    }

    public LongShadowsImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public LongShadowsImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    void init() {

        shadowPaths = new ArrayList<>();

        shadowPaint = new Paint();
        shadowPaint.setAntiAlias(true);
        shadowPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
//        shadowPaint.setColor(Color.RED);
        shadowPaint.setStyle(Paint.Style.FILL);

        shadowPaint.setMaskFilter(new BlurMaskFilter(1, BlurMaskFilter.Blur.NORMAL));
//        shadowPaint.setStyle(Paint.Style.STROKE);
//        shadowPaint.setStrokeWidth(3);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

    @Override
    protected void onDraw(Canvas canvas) {


        Log.d("TIME", "RENDER_START");
        if (shadowPaths != null && shadowPaths.size() > 0) {
            for (ShadowPath shadowPath : shadowPaths) {
                shadowPaint.setShader(new LinearGradient(shadowPath.getPathPoints()[0].getX(), shadowPath.getPathPoints()[0].getY(), shadowPath.getPathPoints()[3].getX(), shadowPath.getPathPoints()[3].getY(), Color.GRAY, Color.TRANSPARENT, Shader.TileMode.CLAMP));
                canvas.drawPath(shadowPath.getPath(), shadowPaint);
            }
        }
        Log.d("TIME", "RENDER_FINISH");

        super.onDraw(canvas);

    }

    public void setShadowPaths(ArrayList<ShadowPath> shadowPaths) {
        this.shadowPaths = shadowPaths;
        invalidate();
    }

}
