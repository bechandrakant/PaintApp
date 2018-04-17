package ml.chandrakant.paintapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    PaintView paintArea;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Use above code make your paint app full screen.

        paintArea = new PaintView(this);
        setContentView(paintArea);
        paintArea.requestFocus();
    }

    public class PaintView extends View implements View.OnTouchListener {

        List<Point> points = new ArrayList<Point>();
        Paint paint = new Paint();

        public PaintView(Context context) {
            super(context);
            setFocusable(true);
            setFocusableInTouchMode(true);

            this.setOnTouchListener(this);
            paint.setColor(Color.RED);
            //You can use spinner to choose color.
            paint.setAntiAlias(true);
            paint.setStrokeWidth(16f);
            //setStrokeWidth to change thickness of paint line.
        }

        @Override
        public void onDraw(Canvas canvas) {
            for (Point point : points) {
                // canvas.drawCircle(point.x, point.y, 5, paint);
                canvas.drawLine(point.x, point.y, point.x + 5, point.y + 5, paint);
            }
        }

        public boolean onTouch(View view, MotionEvent event) {
            Point dot = new Point();
            dot.x = event.getX();
            dot.y = event.getY();
            points.add(dot);
            invalidate();
            return true;
        }
    }

    class Point {
        float x, y;

        @Override
        public String toString() {
            return x + ", " + y;
        }
    }

}