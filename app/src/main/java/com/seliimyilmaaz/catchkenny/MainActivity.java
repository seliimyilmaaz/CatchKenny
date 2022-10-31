package com.seliimyilmaaz.catchkenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewTime;
    TextView textViewScore;
    LinearLayout linearLayout;
    Runnable runnable;
    Handler handler;
    int number;
    ImageView imageView;
    int score = 1;
    int randomX,randomY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Game is started", Toast.LENGTH_SHORT).show();

        imageView = findViewById(R.id.imgKenny);
        textViewTime = findViewById(R.id.txtTimer);
        textViewScore = findViewById(R.id.txtScore);
        linearLayout = findViewById(R.id.linearLayout);

        generateNumber();
        number = 10;
        //score = 1;

        CountDownTimer countDownTimer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisecond) {
                textViewTime.setText("Time : " + millisecond/1000);
            }

            @Override
            public void onFinish() {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Game Over");
                alertDialog.setMessage("Do you want to restart the game ?");

                alertDialog.setPositiveButton("YES!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this , MainActivity.class);
                        startActivity(intent);
                    }
                });

                alertDialog.setNegativeButton("NO!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textViewTime.setText("Time off");
                        imageView.setVisibility(View.INVISIBLE);
                    }
                });

                alertDialog.show();

            }
        }.start();
    }

    public void increaseScore(View view){

        int finalScore = score++;
        textViewScore.setText("Your Score : " +finalScore);
    }

    public void generateNumber(){
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                randomX = new Random().nextInt(800 - 0) + 0;
                randomY = new Random().nextInt(1200 - 0) + 0;
                handler.postDelayed(this, 400);
                imageView.setX(randomX);
                imageView.setY(randomY);

            }
        };
        handler.post(runnable);
    }
}