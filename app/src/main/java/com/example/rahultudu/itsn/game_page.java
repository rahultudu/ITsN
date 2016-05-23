package com.example.rahultudu.itsn;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.SystemClock;
import android.os.Handler;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.conn.scheme.HostNameResolver;

import java.util.Timer;
import java.util.concurrent.RunnableFuture;
//import java.util.logging.Handler;

public class game_page extends AppCompatActivity {
    int i = 1;
    ImageView drawingImageView;
    float x,y;
    int radius, x_centre, y_centre;
    int toggle = -1;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    long startTime = 0L;
    Bitmap bitmap_ring;
    Canvas ring;
    Paint paint_ring;
    int maxX,maxY;
    TextView headerValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        x = 1;
        y = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        ImageView imV = (ImageView) this.findViewById(R.id.DrawingImageView);
        Bitmap bitmap = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        imV.setImageBitmap(bitmap);


        drawingImageView = (ImageView) this.findViewById(R.id.ring);
        bitmap_ring = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        ring = new Canvas(bitmap_ring);



        Display mdisp = getWindowManager().getDefaultDisplay();
        maxX = mdisp.getWidth();
        maxY = mdisp.getHeight();

        int startx, starty, endx, endy;
        Paint paint = new Paint();
        paint_ring =  new Paint();
        paint_ring.setColor(Color.rgb(0, 255, 85));
        paint_ring.setStrokeWidth(20);
        paint_ring.setAlpha(80);

        paint.setColor(Color.rgb(0, 255, 85));
        paint.setStrokeWidth(20);
        int k;
        k = 2;
//upper line
        startx = 0;
        starty = maxY / k;
        endx = maxX;
        endy = maxY / k;
        canvas.drawLine(startx, starty, endx, endy, paint);
//Diagonal line
        startx = maxX;
        starty = maxY / k;
        endx = 0;
        endy = maxY-15;
        canvas.drawLine(startx, starty, endx, endy, paint);
//Lower line
        startx = 0;
        starty = maxY-15;
        endx = maxX;
        endy = maxY-15;
        canvas.drawLine(startx, starty, endx, endy, paint);
//draw a ring
        drawingImageView.setImageBitmap(bitmap_ring);
        radius = maxX/8;
        x_centre = maxX/8;
        y_centre = maxY/2;
      //  ring.drawCircle(x_centre, y_centre, radius, paint_ring);


        //canvas.drawColor(0, PorterDuff.Mode.CLEAR);


        headerValue = (TextView) findViewById(R.id.text);
        final RelativeLayout touchView = (RelativeLayout) findViewById(R.id.full);
       // headerValue.setText("abc");
        touchView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                final int action = event.getAction();
                switch (action & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN: {
                        toggle = toggle * -1;
                        customHandler.postDelayed(update, 0);
                        break;
                    }
                }

                return true;

            }

        });


    }
private Runnable update = new Runnable(){

    public void run()
    {

        if(toggle == -1) {
           // headerValue.setText("toggle is negative");
            return;
        }
        long current_time = SystemClock.uptimeMillis();
        if(((int)(current_time - startTime) >= 500) && (x_centre < maxX - maxX/8)) {
            i++;
            x_centre = x_centre + 1;
            startTime = current_time;
            clearRingImage();
            ring.drawColor(0, PorterDuff.Mode.CLEAR);
            headerValue.setText(Integer.toString(x_centre));
            ring.drawCircle(x_centre, y_centre, radius, paint_ring);
            


        }
        customHandler.postDelayed(this, 50);

    }

};

    private void clearRingImage(){
        drawingImageView.setImageBitmap(null);
        bitmap_ring = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        ring = new Canvas(bitmap_ring);
        drawingImageView.setImageBitmap(bitmap_ring);
    }

}