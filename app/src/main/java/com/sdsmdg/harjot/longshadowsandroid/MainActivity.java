package com.sdsmdg.harjot.longshadowsandroid;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                Log.e("EXCEPTION_IN_THREAD", t.getName() + " : " + e.getMessage());
            }
        });

        Typeface typeface = Typeface.createFromAsset(getResources().getAssets(), "fonts/Quicksand-Regular.ttf");

//        ((TextView) findViewById(R.id.text_1)).setTypeface(typeface);
//        ((TextView) findViewById(R.id.text_2)).setTypeface(typeface);
//        ((TextView) findViewById(R.id.text_view_3)).setTypeface(typeface);
//        ((TextView) findViewById(R.id.text_4)).setTypeface(typeface);

    }

}
