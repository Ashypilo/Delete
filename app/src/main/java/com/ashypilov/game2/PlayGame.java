package com.ashypilov.game2;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class PlayGame {

    private CircleObject sunCircle;
    private CircleObject rayCircle;
    private ArrayList<CircleObject> rayList = new ArrayList<>();

    public PlayGame(StartGameActivity startGameActivity, ConstraintLayout layout) {
        for (int i = 0; i < 10; i++) {
            rayCircle = new CircleObject(startGameActivity);
            rayCircle.createRay();
            layout.addView(rayCircle.getImageView());
            rayList.add(rayCircle);
        }
        sunCircle = new CircleObject(startGameActivity);
        sunCircle.createSun();
        layout.addView(sunCircle.getImageView());
    }

    public void moveRay(final TextView scoreTextView) {
        for (int i = 0; i < 10; i++) {
            rayList.get(i).move();
            final int finalI = i;
            rayList.get(i).getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rayList.get(finalI).deleteRay();
                    Integer score = new Integer((String) scoreTextView.getText());
                    score++;
                    scoreTextView.setText(score.toString());
                }
            });
        }
    }

    public CircleObject getRayCircle() {
        return rayCircle;
    }

    public CircleObject getSunCircle() {
        return sunCircle;
    }
}
