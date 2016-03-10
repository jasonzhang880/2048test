package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

/**
 * Created by Administrator on 2016/3/9.
 */
public class GameView extends GridLayout {
    private Card[][] cardsMap=new Card[4][4];
    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }

//    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
    private void initGameView( ) {

        setColumnCount(4);
        setBackgroundColor(0xffbbada0);
        setOnTouchListener(new OnTouchListener() {
            private float startX,startY,offsetX,offsetY;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX=motionEvent.getX()-startY;
                        offsetY=motionEvent.getY()-startY;

                        if(Math.abs(offsetX)>Math.abs(offsetY)) {
                            if(offsetX<-5) {
                                System.out.println("left");
                            }
                            else if (offsetX>-5)
                                System.out.println("right");
                        }else {
                            if (offsetY<-5) {
                                System.out.println("up");
                            }
                            else if (offsetY>-5)
                                System.out.println("down");
                        }
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int cardWidth=(Math.min(w,h)-10)/4;

        addCards(cardWidth,cardWidth);
    }

    public void addCards(int cardWidth,int cardHeight) {
        Card c;
        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                c=new Card(getContext());
                c.setNum(2);
                addView(c,cardWidth,cardHeight);
            }
        }
    }

    private void swipeLeft() {

    }

    private void swipeRight() {

    }

    private void swipeUp() {

    }

    private void swipeDown() {

    }
}
