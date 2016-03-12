package com.example.myapplication;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class GameView extends GridLayout {
    public static String Tag="randomNum";
    //
    private Card[][] cardsMap=new Card[4][4];
    //空卡片数组
    private List<Point> emptyPoints=new ArrayList<>();

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
                        offsetX=motionEvent.getX()-startX;
                        offsetY=motionEvent.getY()-startY;

                        if(Math.abs(offsetX)>Math.abs(offsetY)) {
                            if(offsetX<-5) {
                                swipeLeft();
                            }
                            else if (offsetX>-5)
                                swipeRight();
                        }else {
                            if (offsetY<-5) {
                                swipeUp();
                            }
                            else if (offsetY>-5)
                                swipeDown();
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
        startGame();
    }

    public void addCards(int cardWidth,int cardHeight) {
        Card c;
        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                c=new Card(getContext());
                c.setNum(0);
                addView(c,cardWidth,cardHeight);

                cardsMap[i][j]=c;
            }
        }
    }

    private void addRandomNum( ) {
        emptyPoints.clear();
        for (int y=0;y<4;y++) {
            for(int x=0;x<4;x++) {
                if (cardsMap[x][y].getNum()<=0) {
                    //所有空值卡片加入
                    emptyPoints.add(new Point(x,y));
                }
            }
        }
        //随机取出一个点
        Point p=emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
    }

    private void startGame( ) {
        for (int y=0;y<4;y++) {
            for (int x=0;x<4;x++)
                cardsMap[x][y].setNum(0);
        }

        addRandomNum();
        Log.d(Tag,".............第一个数");
        addRandomNum();
        Log.d(Tag,".............第一个数");
    }

    private void swipeLeft() {
        for (int column=0;column<4;column++) {
            for (int row=0;row<4;row++) {
                for (int columnPointer=column+1;columnPointer<4;columnPointer++) {
                    if (cardsMap[row][columnPointer].getNum()>0) {
                        if (cardsMap[row][column].getNum()<=0) {
                            cardsMap[row][column].setNum(cardsMap[row][columnPointer].getNum());
                            cardsMap[row][columnPointer].setNum(0);

                            row--;
                            break;
                        } else if (cardsMap[row][column].equals(cardsMap[row][columnPointer])) {
                            cardsMap[row][column].setNum(cardsMap[row][column].getNum()*2);
                            cardsMap[row][columnPointer].setNum(0);
                        }
                    }
                }
            }
        }
        addRandomNum();
        addRandomNum();
    }

    private void swipeRight() {
        for (int column=3;column>=0;column--) {
            for (int row=0;row<4;row++) {
                for (int columnPointer=column-1;columnPointer>=0;columnPointer--) {
                    if (cardsMap[row][columnPointer].getNum()>0) {
                        if (cardsMap[row][column].getNum()<=0) {
                            cardsMap[row][column].setNum(cardsMap[row][columnPointer].getNum());
                            cardsMap[row][columnPointer].setNum(0);

                            row--;
                            break;
                        } else if (cardsMap[row][column].equals(cardsMap[row][columnPointer])) {
                            cardsMap[row][column].setNum(cardsMap[row][column].getNum()*2);
                            cardsMap[row][columnPointer].setNum(0);
                        }
                    }
                }
            }
        }
        addRandomNum();
        addRandomNum();
    }

    private void swipeUp() {
        for (int row=0;row<4;row++) {
            for (int column=0;column<4;column++) {
                for (int rowPointer=row+1;rowPointer<4;rowPointer++) {
                    if (cardsMap[rowPointer][column].getNum()>0) {
                        if (cardsMap[row][column].getNum()<=0) {
                            cardsMap[row][column].setNum(cardsMap[rowPointer][column].getNum());
                            cardsMap[rowPointer][column].setNum(0);

                            column--;
                            break;
                        } else if (cardsMap[row][column].equals(cardsMap[rowPointer][column])) {
                            cardsMap[row][column].setNum(cardsMap[row][column].getNum()*2);
                            cardsMap[rowPointer][column].setNum(0);
                        }
                    }
                }
            }
        }
        addRandomNum();
        addRandomNum();
    }

    private void swipeDown() {
        for (int row=3;row>=0;row--) {
            for (int column=0;column<4;column++) {
                for (int rowPointer=row-1;rowPointer>=0;rowPointer--) {
                    if (cardsMap[rowPointer][column].getNum()>0) {
                        if (cardsMap[row][column].getNum()<=0) {
                            cardsMap[row][column].setNum(cardsMap[rowPointer][column].getNum());
                            cardsMap[rowPointer][column].setNum(0);

                            column--;
                            break;
                        } else if (cardsMap[row][column].equals(cardsMap[rowPointer][column])) {
                            cardsMap[row][column].setNum(cardsMap[row][column].getNum()*2);
                            cardsMap[rowPointer][column].setNum(0);
                        }
                    }
                }
            }
        }
        addRandomNum();
        addRandomNum();
    }
}
