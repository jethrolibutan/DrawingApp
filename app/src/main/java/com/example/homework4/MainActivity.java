package com.example.homework4;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private int width = 800, height = 400;
    private boolean drawRectangle = false, drawCircle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.drawingCanvas);
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setStrokeWidth(5);
        canvas.drawColor(Color.WHITE);

        imageView.setImageBitmap(bitmap);

        Button drawRectangleButton = findViewById(R.id.drawRectangleButton);
        Button drawCircleButton = findViewById(R.id.drawCircleButton);
        Button paintRectangleButton = findViewById(R.id.paintRectangleButton);
        Button paintCircleButton = findViewById(R.id.paintCircleButton);
        Button eraseDrawingButton = findViewById(R.id.eraseDrawingButton);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (drawRectangle) {
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(event.getX() - 100, event.getY() - 100, event.getX() + 100, event.getY() + 100, paint);
                        imageView.invalidate();
                    } else if (drawCircle) {
                        paint.setColor(Color.BLACK);
                        canvas.drawCircle(event.getX(), event.getY(), 100, paint);
                        imageView.invalidate();
                    }
                }
                return true;
            }
        });

        drawRectangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawRectangle = true;
                drawCircle = false;
            }
        });

        drawCircleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawRectangle = false;
                drawCircle = true;
            }
        });

        paintRectangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.RED);
                canvas.drawRect(100, 100, 300, 300, paint);
                imageView.invalidate();
            }
        });

        paintCircleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.BLUE);
                canvas.drawCircle(500, 200, 100, paint);
                imageView.invalidate();
            }
        });


        eraseDrawingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.WHITE);
                canvas.drawColor(Color.WHITE);
                imageView.invalidate();
            }
        });
    }
}
