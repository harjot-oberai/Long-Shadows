package com.sdsmdg.harjot.longshadows;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.sdsmdg.harjot.longshadows.shadowutils.LongShadowsGenerator;

public class LongShadowsFrameLayoutWrapper extends FrameLayout {

    LongShadowsGenerator longShadowsGenerator;

    boolean shouldShowWhenAllReady = Constants.DEFAULT_SHOW_WHEN_ALL_READY;
    boolean shouldCalculateAsync = Constants.DEFAULT_CALCULATE_ASYNC;
    boolean shouldAnimateShadow = Constants.DEFAULT_ANIMATE_SHADOW;
    int animationDuration = Constants.DEFAULT_ANIMATION_TIME;

    boolean isAttached = false;

    boolean shouldClipChildren = Constants.DEFAULT_SHOULD_CLIP_CHILDREN;
    boolean shouldClipToPadding = Constants.DEFAULT_SHOULD_CLIP_TO_PADDING;

    public LongShadowsFrameLayoutWrapper(@NonNull Context context) {
        super(context);
    }

    public LongShadowsFrameLayoutWrapper(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initXMLAttrs(context, attrs);
        init();
    }

    public LongShadowsFrameLayoutWrapper(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initXMLAttrs(context, attrs);
        init();
    }

    void initXMLAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LongShadowsFrameLayoutWrapper);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.LongShadowsFrameLayoutWrapper_calculateAsync) {
                shouldCalculateAsync = a.getBoolean(attr, Constants.DEFAULT_CALCULATE_ASYNC);
            } else if (attr == R.styleable.LongShadowsFrameLayoutWrapper_showWhenAllReady) {
                shouldShowWhenAllReady = a.getBoolean(attr, Constants.DEFAULT_SHOW_WHEN_ALL_READY);
            } else if (attr == R.styleable.LongShadowsFrameLayoutWrapper_shouldClipChildren) {
                shouldClipChildren = a.getBoolean(attr, Constants.DEFAULT_SHOULD_CLIP_CHILDREN);
            } else if (attr == R.styleable.LongShadowsFrameLayoutWrapper_shouldClipToPadding) {
                shouldClipToPadding = a.getBoolean(attr, Constants.DEFAULT_SHOULD_CLIP_TO_PADDING);
            } else if (attr == R.styleable.LongShadowsFrameLayoutWrapper_animateShadow) {
                shouldAnimateShadow = a.getBoolean(attr, Constants.DEFAULT_ANIMATE_SHADOW);
            } else if (attr == R.styleable.LongShadowsFrameLayoutWrapper_animationDuration) {
                animationDuration = a.getInteger(attr, Constants.DEFAULT_ANIMATION_TIME);
            }
        }
        a.recycle();
    }

    void init() {
        setShouldClipChildren(shouldClipChildren);
        setShouldClipToPadding(shouldClipToPadding);
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

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isAttached = false;
        if (longShadowsGenerator != null) {
            longShadowsGenerator.releaseResources();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isAttached = true;
    }

    public boolean isShouldShowWhenAllReady() {
        return shouldShowWhenAllReady;
    }

    public void setShouldShowWhenAllReady(boolean shouldShowWhenAllReady) {
        this.shouldShowWhenAllReady = shouldShowWhenAllReady;
        if (longShadowsGenerator != null) {
            longShadowsGenerator.setShouldShowWhenAllReady(shouldShowWhenAllReady);
        }
    }

    public boolean isShouldCalculateAsync() {
        return shouldCalculateAsync;
    }

    public void setShouldCalculateAsync(boolean shouldCalculateAsync) {
        this.shouldCalculateAsync = shouldCalculateAsync;
        if (longShadowsGenerator != null) {
            longShadowsGenerator.setShouldCalculateAsync(shouldCalculateAsync);
        }
    }

    public boolean isShouldAnimateShadow() {
        return shouldAnimateShadow;
    }

    public void setShouldAnimateShadow(boolean shouldAnimateShadow) {
        this.shouldAnimateShadow = shouldAnimateShadow;
        if (longShadowsGenerator != null) {
            longShadowsGenerator.setShouldAnimateShadow(shouldAnimateShadow);
        }
    }

    public int getAnimationDuration() {
        return animationDuration;
    }

    public void setAnimationDuration(int animationDuration) {
        this.animationDuration = animationDuration;
        if (longShadowsGenerator != null) {
            longShadowsGenerator.setAnimationDuration(animationDuration);
        }
    }

    public boolean isAttached() {
        return isAttached;
    }

    public void setAttached(boolean attached) {
        isAttached = attached;
    }

    public boolean isShouldClipChildren() {
        return shouldClipChildren;
    }

    public void setShouldClipChildren(boolean shouldClipChildren) {
        setClipChildren(shouldClipChildren);
        this.shouldClipChildren = shouldClipChildren;
        requestLayout();
    }

    public boolean isShouldClipToPadding() {
        return shouldClipToPadding;
    }

    public void setShouldClipToPadding(boolean shouldClipToPadding) {
        setClipToPadding(shouldClipToPadding);
        this.shouldClipToPadding = shouldClipToPadding;
        requestLayout();
    }

    public void updateChildrenIfDirty() {
        requestLayout();
    }

}
