package com.sdsmdg.harjot.longshadows;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.sdsmdg.harjot.longshadows.models.ShadowPath;
import com.sdsmdg.harjot.longshadows.shadowutils.Utils;

import java.util.ArrayList;

/**
 * Created by Harjot on 31-Jan-18.
 */

public class LongShadowsTextView extends TextView {

    private Context context;

    private ArrayList<ShadowPath> shadowPaths;
    private Paint shadowPaint;

    private int shadowLength;

    private boolean isShadowDirty = true;

    public LongShadowsTextView(Context context) {
        super(context);
        this.context = context;
    }

    public LongShadowsTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public LongShadowsTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    void init() {

        shadowLength = 100;

        shadowPaths = new ArrayList<>();

        shadowPaint = new Paint();
        shadowPaint.setAntiAlias(true);
        shadowPaint.setMaskFilter(new BlurMaskFilter(2, BlurMaskFilter.Blur.NORMAL));
        shadowPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(final Canvas canvas) {


        Log.d("TIME", "RENDER_START");
        if (shadowPaths != null && shadowPaths.size() > 0) {
            for (ShadowPath shadowPath : shadowPaths) {
                shadowPaint.setShader(Utils.generateLinearGradient(shadowPath, Color.parseColor("#88000000"), Color.TRANSPARENT));
                canvas.drawPath(shadowPath.getPath(), shadowPaint);
            }
        }

        Log.d("TIME", "RENDER_FINISH");

        super.onDraw(canvas);

    }

    public void setShadowPaths(ArrayList<ShadowPath> shadowPaths) {
        this.shadowPaths = shadowPaths;
        isShadowDirty = false;
        invalidate();
    }

    public boolean isShadowDirty() {
        return isShadowDirty;
    }

    public void setShadowDirty(boolean shadowDirty) {
        isShadowDirty = shadowDirty;
    }

}
