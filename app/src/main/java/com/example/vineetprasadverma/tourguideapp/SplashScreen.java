package com.example.vineetprasadverma.tourguideapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private final static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);

        ImageView logo = findViewById(R.id.splash_image);
        Animation fadeInImage = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        logo.startAnimation(fadeInImage);

        TextView textView = findViewById(R.id.splash_text);
        Animation fadeInText = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        textView.startAnimation(fadeInText);


        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);

    }
}
