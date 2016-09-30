package com.gambiapro.android.task3;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ViewLayout(this));
    }

    private class ViewLayout extends View {
        long LOOP_TIME = 1_000_000_000L; //1 second loop
        Random rnd = new Random();
        long startTime = 0;
        int r, g, b;

        public ViewLayout(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            long now = System.nanoTime();
            if (now - startTime > LOOP_TIME) {
                //Update canvas color
                r = rnd.nextInt(256);
                g = rnd.nextInt(256);
                b = rnd.nextInt(256);

                //Update the start time
                startTime = now;
            }

            //Draw and invalidate the canvas
            canvas.drawRGB(r, g, b);
            invalidate();
        }
    }
}
