package com.sdsmdg.harjot.longshadows.shadowutils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.sdsmdg.harjot.longshadows.Constants;
import com.sdsmdg.harjot.longshadows.LongShadowsImageView;
import com.sdsmdg.harjot.longshadows.LongShadowsTextView;
import com.sdsmdg.harjot.longshadows.LongShadowsWrapper;
import com.sdsmdg.harjot.longshadows.models.ShadowPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.sdsmdg.harjot.longshadows.Constants.POS_UPDATE_ALL;

/**
 * Created by Harjot on 27-Jan-18.
 */

public class LongShadowsGenerator {

    private final Object TASKS_LOCK = new Object();

    private List<Future<?>> tasksInProgress = new ArrayList<>();
    private ExecutorService workerPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private Handler uiThreadHandler = new Handler(Looper.getMainLooper());

    private SparseArray<ShadowPath[]> viewShadowPaths;

    private ViewGroup viewGroup;

    private boolean shouldShowWhenAllReady = Constants.DEFAULT_SHOW_WHEN_ALL_READY;
    private boolean shouldCalculateAsync = Constants.DEFAULT_CALCULATE_ASYNC;
    private boolean shouldAnimateShadow = Constants.DEFAULT_ANIMATE_SHADOW;

    private int animationDuration = Constants.DEFAULT_ANIMATION_TIME;

    private int childrenWithShadow;

    static {
        System.loadLibrary("native-lib");
    }

    public LongShadowsGenerator(ViewGroup viewGroup, boolean shouldShowWhenAllReady, boolean shouldCalculateAsync, boolean shouldAnimateShadow, int animationDuration) {
        this.viewGroup = viewGroup;
        this.shouldShowWhenAllReady = shouldShowWhenAllReady;
        this.shouldCalculateAsync = shouldCalculateAsync;
        this.shouldAnimateShadow = shouldAnimateShadow;
        this.animationDuration = animationDuration;
    }

    public void generate() {
        clearShadowCache(); //Maybe some children changed their size
        childrenWithShadow = 0;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof LongShadowsWrapper) {
                continue;
            }
            childrenWithShadow++;
            if (shouldCalculateAsync) {
                calculateAndRenderShadowAsync(view, i);
            } else {
                calculateAndRenderShadow(view, i);
            }
        }
    }

    public void releaseResources() {
        workerPool.shutdown();
        uiThreadHandler.removeCallbacksAndMessages(null);
    }

    private void clearShadowCache() {
        cancelTasksInProgress();
        uiThreadHandler.removeCallbacksAndMessages(null);
        viewShadowPaths = new SparseArray<>();
    }

    private void updateShadows(int pos) {
        if (pos == POS_UPDATE_ALL) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                setLongShadowAtPosition(i);
            }
        } else {
            setLongShadowAtPosition(pos);
        }
    }

    private void setLongShadowAtPosition(int childIndex) {
        ShadowPath[] shadowPaths = getViewPathWithOffsetAt(childIndex);
        if (shadowPaths == null) {
            //Path calculation is still in progress
            return;
        }
        final View child = viewGroup.getChildAt(childIndex);

        if (child instanceof LongShadowsImageView) {
            ((LongShadowsImageView) child).setShadowPaths(new ArrayList<>(Arrays.asList(shadowPaths)));
        } else if (child instanceof LongShadowsTextView) {
            ((LongShadowsTextView) child).setShadowPaths(new ArrayList<>(Arrays.asList(shadowPaths)));
        }

//        if (shouldAnimateShadow) {
//            animateOutlineAlpha(child, outlineProvider);
//        }
    }

//    private void animateOutlineAlpha(final View child, CustomViewOutlineProvider outlineProvider) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(outlineProvider, "alpha", 0, shadowAlpha);
//        animator.setDuration(animationDuration);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                child.invalidateOutline();
//            }
//        });
//        animator.start();
//    }

    private ShadowPath[] getViewPathWithOffsetAt(int position) {
        ShadowPath[] noOffsetPath = viewShadowPaths.get(position);
//        if (noOffsetPath == null) {
//            return null;
//        }
//        Path path = new Path();
//        path.set(noOffsetPath);
//        path.offset(offsetX, offsetY);
        return noOffsetPath;
    }

    private void calculateAndRenderShadowAsync(final View view, final int pos) {
        final Future[] future = new Future[1];
        future[0] = workerPool.submit(new Runnable() {
            @Override
            public void run() {
                calculateAndRenderShadow(view, pos);
                synchronized (TASKS_LOCK) {
                    tasksInProgress.remove(future[0]);
                }
            }
        });
        tasksInProgress.add(future[0]);
    }

    private void cancelTasksInProgress() {
        synchronized (TASKS_LOCK) {
            for (int i = tasksInProgress.size() - 1; i >= 0; i--) {
                Future<?> task = tasksInProgress.get(i);
                task.cancel(true);
                tasksInProgress.remove(i);
            }
        }
    }

    private void calculateAndRenderShadow(final View view, int pos) {
        if (Thread.currentThread().isInterrupted()) {
            return;
        }

        ShadowPath[] shadowPaths = getShadowPaths(view);

        synchronized (TASKS_LOCK) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            uiThreadHandler.postAtFrontOfQueue(new SetLongShadowTask(pos, shadowPaths));
        }
    }

    private ShadowPath[] getShadowPaths(View view) {
        if (view instanceof LongShadowsImageView) {
            LongShadowsImageView longShadowsImageView = (LongShadowsImageView) view;

            if (!longShadowsImageView.isShadowDirty()) {
                return null;
            }

            view.buildDrawingCache();
            Bitmap bitmap = view.getDrawingCache();

            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] intArray = new int[width * height];
            bitmap.getPixels(intArray, 0, width, 0, 0, width, height);

            ShadowPath[] paths = getContours(intArray,
                    width,
                    height,
                    longShadowsImageView.getShadowAngle(),
                    longShadowsImageView.getShadowLength(),
                    longShadowsImageView.isHighQuality(),
                    (longShadowsImageView.isBackgroundTransparent()) ? 0 : longShadowsImageView.getBackgroundColor());

            for (ShadowPath path : paths) {
                path.constructPath(longShadowsImageView.isHighQuality());
            }

            return paths;

        } else if (view instanceof LongShadowsTextView) {

            LongShadowsTextView longShadowsTextView = (LongShadowsTextView) view;

            if (!longShadowsTextView.isShadowDirty()) {
                return null;
            }

            view.buildDrawingCache();
            Bitmap bitmap = view.getDrawingCache();

            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] intArray = new int[width * height];
            bitmap.getPixels(intArray, 0, width, 0, 0, width, height);

            ShadowPath[] paths = getContours(intArray,
                    width,
                    height,
                    longShadowsTextView.getShadowAngle(),
                    longShadowsTextView.getShadowLength(),
                    longShadowsTextView.isHighQuality(),
                    (longShadowsTextView.isBackgroundTransparent()) ? 0 : longShadowsTextView.getBackgroundColor());

            for (ShadowPath path : paths) {
                path.constructPath(longShadowsTextView.isHighQuality());
            }

            return paths;

        } else {
            return null;
        }
    }

    private class SetLongShadowTask implements Runnable {

        private int viewPos;
        private ShadowPath[] shadowPaths;

        private SetLongShadowTask(int pos, ShadowPath[] shadowPaths) {
            this.viewPos = pos;
            this.shadowPaths = shadowPaths;
        }

        @Override
        public void run() {
            if (((LongShadowsWrapper) viewGroup).isAttached()) {
                viewShadowPaths.put(viewPos, shadowPaths);
                if (shouldShowWhenAllReady) {
                    if (viewShadowPaths.size() == childrenWithShadow) {
                        updateShadows(POS_UPDATE_ALL);
                    }
                    return;
                }
                updateShadows(viewPos);
            }
        }
    }

    public native ShadowPath[] getContours(int arr[], int width, int height, float angle, int shadowLength, boolean highQuality, int backgroundColor);
}
