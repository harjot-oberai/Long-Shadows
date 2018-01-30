package com.sdsmdg.harjot.longshadowsandroid;

import android.graphics.Bitmap;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.sdsmdg.harjot.longshadows.LongShadowsWrapper;
import com.sdsmdg.harjot.longshadows.models.Contour;
import com.sdsmdg.harjot.longshadows.models.Point2D;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    ImageView imageView;
//    CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        imageView = findViewById(R.id.image_view);
//        customView = findViewById(R.id.custom_view);
//
//        imageView.post(new Runnable() {
//            @Override
//            public void run() {
//                imageView.buildDrawingCache();
//
//                Bitmap bitmap = imageView.getDrawingCache();
//
//                int width = bitmap.getWidth();
//                int height = bitmap.getHeight();
//                int[] intArray = new int[width * height];
//                bitmap.getPixels(intArray, 0, width, 0, 0, width, height);
//
////                Point2D[] points = LongShadowsWrapper.getContours(intArray, width, height);
//
////                ArrayList<Point2D> point2DS = new ArrayList<>();
////                ArrayList<Contour> contours = new ArrayList<>();
////
////                for (Point2D point : points) {
////                    if (point.getX() != -1 && point.getY() != -1) {
////                        point2DS.add(new Point2D(point.getX(), point.getY()));
////                    } else {
////                        contours.add(new Contour(point2DS));
////                        point2DS.clear();
////                    }
////                }
//            }
//        });

    }

}
