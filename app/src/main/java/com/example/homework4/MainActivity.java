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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private int width = 800, height = 400;
    private boolean drawRectangle = false, drawCircle = false;

    private ArrayList<float[]> rectangles = new ArrayList<>();
    private ArrayList<float[]> circles = new ArrayList<>();
    private float circleRadius = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.drawingCanvas);
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
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
                        float left = event.getX() - 100;
                        float top = event.getY() - 100;
                        float right = event.getX() + 100;
                        float bottom = event.getY() + 100;
                        rectangles.add(new float[]{left, top, right, bottom});
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(left, top, right, bottom, paint);
                        imageView.invalidate();
                    } else if (drawCircle) {
                        float x = event.getX();
                        float y = event.getY();
                        circles.add(new float[]{x, y, circleRadius});
                        paint.setColor(Color.BLACK);
                        canvas.drawCircle(x, y, circleRadius, paint);
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
                paint.setStyle(Paint.Style.FILL);
                for (float[] rect : rectangles) {
                    canvas.drawRect(rect[0], rect[1], rect[2], rect[3], paint);
                }
                imageView.invalidate();
            }
        });

        paintCircleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.BLUE);
                paint.setStyle(Paint.Style.FILL);
                for (float[] circle : circles) {
                    canvas.drawCircle(circle[0], circle[1], circle[2], paint);
                }
                imageView.invalidate();
            }
        });

        eraseDrawingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.drawColor(Color.WHITE);
                rectangles.clear();
                circles.clear();
                imageView.invalidate();
            }
        });
    }
}
