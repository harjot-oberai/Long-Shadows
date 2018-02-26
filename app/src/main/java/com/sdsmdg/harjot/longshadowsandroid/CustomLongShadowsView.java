package com.sdsmdg.harjot.longshadowsandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.sdsmdg.harjot.longshadows.LongShadowsView;

/**
 * Created by Harjot on 2/26/2018.
 */

public class CustomLongShadowsView extends LongShadowsView {

    Paint paint;

    public CustomLongShadowsView(Context context) {
        super(context);
    }

    public CustomLongShadowsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public CustomLongShadowsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/ 2, getHeight()/2,100, paint);
        canvas.drawRect(100, 100, 300, 300, paint);
    }
}
