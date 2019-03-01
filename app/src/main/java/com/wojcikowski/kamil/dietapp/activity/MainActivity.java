package com.wojcikowski.kamil.dietapp.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wojcikowski.kamil.dietapp.R;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "com.wojcikowski.kamil.dietApp.sharedPrefs";

    Button logoutBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String loggedUser = sharedPreferences.getString("loggeduser", "");


        if(loggedUser.equals("")) {
            startActivity(new Intent(MainActivity.this, LRActivity.class));
        } else {
            setContentView(R.layout.activity_main);
            logoutBt = findViewById(R.id.logoutBt);
            logoutBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    logout();
                }
            });
        }
    }

    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(getString(R.string.loggedUser), "");
        editor.apply();

        finish();
        startActivity(getIntent());
    }
}
