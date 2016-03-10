package com.example.myapplication;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/3/10.
 */
public class Card extends FrameLayout {
    private int num;
    private TextView lable;

    public Card(Context context) {
        super(context);
        lable=new TextView(getContext());
        LayoutParams lp=new LayoutParams(-1,-1);
        lp.setMargins(10,10,0,0);
        lable.setTextSize(32);
        lable.setGravity(Gravity.CENTER);
        lable.setBackgroundColor(0x33ffffff);
        addView(lable,lp);
        setNum(0);
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        lable.setText(num+"");
    }

    public boolean equals(Card c) {
        return num==c.getNum();
    }
}
