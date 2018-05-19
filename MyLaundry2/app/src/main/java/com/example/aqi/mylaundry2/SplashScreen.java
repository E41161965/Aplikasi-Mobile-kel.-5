package com.example.aqi.mylaundry2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplashScreen extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //menghilangkan ActionBar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splashscreen);
        imageView = (ImageView) findViewById(R.id.logo);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splashscreen);
        imageView.startAnimation(myanim);
        final Intent i= new Intent(this,LoginActivity.class);
        Thread timer = new Thread() {
            @Override
            public void run() {

                try {
                    sleep(5000);
                } catch (
                        InterruptedException e)

                {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }

}