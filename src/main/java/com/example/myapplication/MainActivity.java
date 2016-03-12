package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tvScore;
    private int score=0;
    private static MainActivity mainActivity=null;
    public MainActivity( ) {
        mainActivity=this;
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore=(TextView)findViewById(R.id.tvScore);
    }

    public void clearScore() {
        score=0;
        showScore();
    }

    public void showScore( ) {
        tvScore.setText(score + "");
    }

    public void addScore(int s) {
        score+=s;
        showScore();
    }


}
