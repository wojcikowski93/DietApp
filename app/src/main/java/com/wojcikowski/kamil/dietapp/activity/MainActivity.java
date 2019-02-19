package com.wojcikowski.kamil.dietapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wojcikowski.kamil.dietapp.R;

public class MainActivity extends AppCompatActivity {

    Button registerBt;
    Button logInBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerBt = findViewById(R.id.signIn);
        logInBt = findViewById(R.id.logIn);

        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        logInBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: startActivity(new Intent(MainActivity.this, LogInActivity.class));
            }
        });
    }
}
