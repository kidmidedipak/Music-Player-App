package com.dipak.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        },2000);

//        MobileAds.initialize(this);
//        Application application = getApplication();
//        ((MyApplication) application).loadAd(this);
//
//        createTimer();
    }

//    public void createTimer() {
//
//        CountDownTimer countDownTimer = new CountDownTimer(4000, 1000) {
//            @Override
//            public void onTick(long l) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                Application application = getApplication();
//                ((MyApplication) application).showAdIfAvailable(SplashActivity.this, new MyApplication.OnShowAdCompleteListener() {
//                    @Override
//                    public void onAdShown() {
//                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//            }
//        };
//
//        countDownTimer.start();
//
//    }

}
