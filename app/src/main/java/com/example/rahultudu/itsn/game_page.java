package com.example.rahultudu.itsn;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class game_page extends AppCompatActivity {
    ImageView drawingImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        drawingImageView = (ImageView) this.findViewById(R.id.DrawingImageView);
        Bitmap bitmap = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawingImageView.setImageBitmap(bitmap);

        Display mdisp = getWindowManager().getDefaultDisplay();
        int maxX = mdisp.getWidth();
        int maxY = mdisp.getHeight();

        int startx, starty, endx, endy;
        Paint paint = new Paint();
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
    }
}
