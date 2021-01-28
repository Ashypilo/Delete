package com.ashypilov.game2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StartGameActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private Handler handler;
    private PlayGame playGame;
    private TextView timerTextView;
    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        final View view = new View(this);
        layout = findViewById(R.id.layout_play_game);

        layout.post(new Runnable() {
            @Override
            public void run() {
                (layout).addView(view);
            }
        });

        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();
        playGame = new PlayGame(StartGameActivity.this, layout);
        scoreTextView = new TextView(this);
        timerTextView = new TextView(this);
        scoreTextView.setText("0");
        scoreTextView.setX(700);
        scoreTextView.setY(50);
        timerTextView.setX(200);
        timerTextView.setY(50);
        scoreTextView.setTextSize(20);
        timerTextView.setTextSize(20);
        layout.addView(scoreTextView);
        layout.addView(timerTextView);
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("" + millisUntilFinished / 1000);
                playGame.getSunCircle().changeSizeSun(5);
            }
            public void onFinish() {
//                constraintLayout.removeAllViews();
//                Intent intent = new Intent(PlayGameActivity.this, FinishActivity.class);
//                intent.putExtra("score", scoreTextView.getText());
//                startActivity(intent);
            }
        }.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                playGame.moveRay(scoreTextView);
                handler.postDelayed(this, 5);
            }
        });

    }
}
