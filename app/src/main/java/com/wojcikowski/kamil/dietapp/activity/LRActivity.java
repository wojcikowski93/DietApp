package com.wojcikowski.kamil.dietapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wojcikowski.kamil.dietapp.R;

public class LRActivity extends AppCompatActivity {

    Button registerBt;
    Button logInBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lr);

        registerBt = findViewById(R.id.signIn);
        logInBt = findViewById(R.id.logIn);

        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LRActivity.this, RegisterActivity.class));
            }
        });

        logInBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LRActivity.this, LoginActivity.class));
            }
        });

    }
}
