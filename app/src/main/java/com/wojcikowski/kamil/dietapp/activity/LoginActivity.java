package com.wojcikowski.kamil.dietapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wojcikowski.kamil.dietapp.R;
import com.wojcikowski.kamil.dietapp.database.DBUser;
import com.wojcikowski.kamil.dietapp.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText userNameET;
    private EditText userPasswordET;
    private String username;
    private String userPassword;

    private User user;

    DBUser dbUser;
    public static final String SHARED_PREFS = "com.wojcikowski.kamil.dietApp.sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameET = findViewById(R.id.userNameLogin);
        userPasswordET = findViewById(R.id.userPasswordLogin);

        Button loginBt = findViewById(R.id.loginBt);
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        dbUser = new DBUser(getApplicationContext());
        dbUser.open();
        initialize();
        if (!validate()) {
            Toast.makeText(this, "Login has Failed.", Toast.LENGTH_SHORT).show();
        } else {
            editor.putString(getString(R.string.loggedUser), user.getUsername());
            editor.apply();
            dbUser.close();
            Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, UserDetailsActivity.class));
        }
    }

    private boolean validate() {
        boolean valid = true;
        if (username.isEmpty()) {
            userNameET.setError("Please enter valid name.");
            valid = false;
        } else if (dbUser.findUserByUsername(username) == null) {
            userNameET.setError("User not exists.");
            valid = false;
        } else {
            user = dbUser.findUserByUsername(username);
        }
        if(user == null) {
            userNameET.setError("User not exists.");
            valid = false;
        } else if (userPassword.isEmpty()) {
            userPasswordET.setError("Please enter valid password.");
            valid = false;
        } else if (!userPassword.equals(user.getPassword())) {
            userPasswordET.setError("Wrong password.");
            valid = false;
        }
        return valid;
    }

    private void initialize() {
        username = userNameET.getText().toString().trim();
        userPassword = userPasswordET.getText().toString().trim();
    }


}
