package edu.midlandstech.williamhhall.flingfinal;

import android.os.Bundle;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {


    ImageView ball;
    FrameLayout mainView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ball = (ImageView)findViewById(R.id.ball);
        mainView = (FrameLayout)findViewById(R.id.mainView);

        final GestureDetector myGesture = new GestureDetector(this, new MyOnGestureListener());

        ball.setOnTouchListener(new OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return myGesture.onTouchEvent(event);
            }});

        ball.setClickable(true);

    }

    class MyOnGestureListener implements OnGestureListener{

        int MIN_DIST = 100;

        @Override
        public boolean onDown(MotionEvent arg0) {

            return false;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX,
                               float velocityY) {

            float e1X = event1.getX();
            float e1Y = event1.getY();
            float e2X = event2.getX();
            float e2Y = event2.getY();

            float distX = e2X - e1X;
            float distY = e2Y - e1Y;

            //Get the Y OFfset
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int offsetY = displayMetrics.heightPixels - mainView.getMeasuredHeight();

            int[] location = new int[2];
            ball.getLocationOnScreen(location);
            float orgX = location[0];
            float orgY = location[1] - offsetY;

            float stopX = orgX + distX;
            float stopY = orgY + distY;

            if (distX > MIN_DIST) {
                //Fling Right
                ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(ball, "translationX", orgX, stopX);
                flingAnimator.setDuration(1000);
                flingAnimator.start();
            }else if(distX < - MIN_DIST){
                //Fling Left
                ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(ball, "translationX", orgX, stopX);
                flingAnimator.setDuration(1000);
                flingAnimator.start();
            }else if (distY > MIN_DIST) {
                //Fling Down
                ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(ball, "translationY", orgY, stopY);
                flingAnimator.setDuration(1000);
                flingAnimator.start();
            }else if(distY < - MIN_DIST){
                //Fling Up
                ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(ball, "translationY", orgY, stopY);
                flingAnimator.setDuration(1000);
                flingAnimator.start();
            }

            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }};

}
