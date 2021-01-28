package com.ashypilov.game2;

import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.ashypilov.game2.Utils.Utils;

import java.util.Random;

public class CircleObject {

    private float x;
    private float y;
    private int randomX;
    private int randomY;
    private ImageView imageView;
    private StartGameActivity view;
    private int sizeChangeWidth;
    private int sizeChangeHeight;
    private int speedX;
    private int speedY;
    private int widthLayout;
    private int heightLayout;
    private boolean isRay = false;
    private int randomRay;

    public int getSizeChangeWidth() {
        return sizeChangeWidth;
    }

    public int getSizeChangeHeight() {
        return sizeChangeHeight;
    }

    public void setSizeChangeWidth(int sizeChangeWidth) {
        this.sizeChangeWidth = sizeChangeWidth;
    }

    public void setSizeChangeHeight(int sizeChangeHeight) {
        this.sizeChangeHeight = sizeChangeHeight;
    }

    public void changeSizeSun(int change) {
        sizeChangeWidth -= change;
        sizeChangeHeight -= change;
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.height -= change;
        layoutParams.width -= change;
        imageView.setLayoutParams(layoutParams);
        imageView.setX(imageView.getX() + change/2);
        imageView.setY(imageView.getY() + change/2);
    }

    public CircleObject(StartGameActivity view) {
        imageView = new ImageView(view);
        imageView.setImageResource(R.drawable.sun);
        widthLayout = view.getWindowManager().getDefaultDisplay().getWidth();
        heightLayout = view.getWindowManager().getDefaultDisplay().getHeight();
        this.view = view;
        sizeChangeHeight = Utils.HEIGHTCIRCLE;
        sizeChangeWidth = Utils.WIDTHCIRCLE;
    }

    public void createSun() {
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(Utils.WIDTHCIRCLE, Utils.HEIGHTCIRCLE);
        imageView.setLayoutParams(layoutParams);
        Log.i("set", widthLayout/2 + "-" + heightLayout/2);
        imageView.setX(widthLayout/2 - Utils.WIDTHCIRCLE/2);
        imageView.setY(heightLayout/2 - Utils.HEIGHTCIRCLE/2);
    }

    public void createRay() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Utils.WIDTHRAY, Utils.HEIGHTRAY);
        imageView.setLayoutParams(layoutParams);
        startPositionRay();
        speedX = Utils.SPEEDRAY;
        speedY = Utils.SPEEDRAY;
    }

    public void startPositionRay() {
        imageView.setY(heightLayout/2 - Utils.HEIGHTRAY/2);
        imageView.setX(widthLayout/2 - Utils.WIDTHRAY/2);
    }

    public void move() {
        if (!isRay) {
            Random random = new Random();
            randomX = 20 + random.nextInt(40);
            randomY = 20 + random.nextInt(40);
            imageView.setX(imageView.getX() - randomX);
            imageView.setY(imageView.getY() + randomY);
            Log.i("random", randomX + "-" + randomY);
            isRay = true;
            randomMoveRay();
            Log.i("start_position", "start");
        }

        x = imageView.getX() + speedX;
        y = imageView.getY() + speedY;
        if (x > widthLayout || (x + Utils.WIDTHRAY) < 0 || y > heightLayout || (y + Utils.HEIGHTRAY) < 0) {
            deleteRay();
            Log.i("start_position", "finish");
        }
        else {
            imageView.setX(x);
            imageView.setY(y);
            Log.i("start_position", "process" + speedX + "-" + speedY + "---" + randomRay);
        }
    }

    public void deleteRay() {
        isRay = false;
        startPositionRay();
    }

    public void randomMoveRay() {
        Random random = new Random();
        randomRay = 1 + random.nextInt(8);
        speedX = Utils.SPEEDRAY;
        speedY = Utils.SPEEDRAY;
        switch (randomRay) {
            case 1:
                speedX = Utils.SPEEDRAY;
                speedY = Utils.SPEEDRAY;
                break;
            case 2:
                speedX = Utils.SPEEDRAY;
                speedY *= -1;
                break;
            case 3:
                speedX = Utils.SPEEDRAY;
                speedY = 0;
                break;
            case 4:
                speedX *= -1;
                speedY = 0;
                break;
            case 5:
                speedX *= -1;
                speedY *= -1;
                break;
            case 6:
                speedX *= -1;
                speedY = Utils.SPEEDRAY;
                break;
            case 7:
                speedX = 0;
                speedY *= -1;
                break;
            case 8:
                speedX = 0;
                speedY = Utils.SPEEDRAY;
                break;
        }
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setSpeed(int speed) {
        this.speedX = speed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getSpeed() {
        return speedX;
    }
}
