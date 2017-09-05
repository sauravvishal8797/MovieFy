package android.example.com.popularmovies.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.example.com.popularmovies.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_TIMER = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                Intent i = new Intent(SplashScreenActivity.this, Main2Activity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIMER);
    }
}
