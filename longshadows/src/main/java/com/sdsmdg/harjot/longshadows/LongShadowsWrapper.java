package com.sdsmdg.harjot.longshadows;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.sdsmdg.harjot.longshadows.shadowutils.LongShadowsGenerator;

public class LongShadowsWrapper extends RelativeLayout {

    LongShadowsGenerator longShadowsGenerator;

    private int angle;
    private int shadowLength;

    public LongShadowsWrapper(Context context) {
        super(context);
        init();
    }

    public LongShadowsWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LongShadowsWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {

        angle = 30;
        shadowLength = 700;

        setClipChildren(false);
        setClipToPadding(false);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (longShadowsGenerator == null) {
            longShadowsGenerator = new LongShadowsGenerator(this, angle, shadowLength);
        }
        longShadowsGenerator.generate();
    }
}
