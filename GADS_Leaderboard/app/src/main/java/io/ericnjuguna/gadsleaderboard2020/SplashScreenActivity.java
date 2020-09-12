

package io.ericnjuguna.gadsleaderboard2020;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import io.ericnjuguna.gadsleaderboard2020.home.HomeActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

    }

    @Override
    protected void onResume() {
        super.onResume();

        final Handler handler = new Handler();

        handler.postDelayed(this::launchHome, 1000);
    }

    private void launchHome(){
        startActivity( new Intent(this, HomeActivity.class));
    }
}