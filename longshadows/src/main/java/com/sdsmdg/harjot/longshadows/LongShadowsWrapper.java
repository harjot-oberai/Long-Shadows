package com.sdsmdg.harjot.longshadows;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.sdsmdg.harjot.longshadows.shadowutils.LongShadowsGenerator;

public class LongShadowsWrapper extends RelativeLayout {

    LongShadowsGenerator longShadowsGenerator;

    boolean shouldShowWhenAllReady = Constants.DEFAULT_SHOW_WHEN_ALL_READY;
    boolean shouldCalculateAsync = Constants.DEFAULT_CALCULATE_ASYNC;
    boolean shouldAnimateShadow = Constants.DEFAULT_ANIMATE_SHADOW;

    int animationDuration = Constants.DEFAULT_ANIMATION_TIME;

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

        setClipChildren(false);
        setClipToPadding(false);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (longShadowsGenerator == null) {
            longShadowsGenerator = new LongShadowsGenerator(this, shouldShowWhenAllReady, shouldCalculateAsync, shouldAnimateShadow, animationDuration);
        }
        longShadowsGenerator.generate();
    }
}
