package io.github.aerhakim.bacaberita.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.github.aerhakim.bacaberita.R;


public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIMER = 2000; //2000 sama dengan 2 detik

    //varianel
    ImageView icon;
    TextView nama;
    Animation sideAnim, bottomAnim;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash_screen);

        //hooks
        icon =  findViewById(R.id.iv_icon);
        nama = findViewById(R.id.tv_nama);

        //Animasi
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        //Setting Elemen Animasi
        icon.setAnimation(sideAnim);
        nama.setAnimation(bottomAnim);

        new Handler().postDelayed(() -> {

            //Shared preference, jika user pertama install akan ke onboarding jika tidak akan langsung ke main activity

            onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
            boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

            if(isFirstTime){

            SharedPreferences.Editor editor = onBoardingScreen.edit();
            editor.putBoolean("firstTime",false);
            editor.commit();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class); //Nanti diganti jadi onBoarding
            startActivity(intent);

            } else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
            finish();
        }, SPLASH_TIMER);

    }


}