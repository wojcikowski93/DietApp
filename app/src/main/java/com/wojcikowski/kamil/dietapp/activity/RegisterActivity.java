package com.wojcikowski.kamil.dietapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wojcikowski.kamil.dietapp.R;
import com.wojcikowski.kamil.dietapp.database.DBUser;
import com.wojcikowski.kamil.dietapp.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText userNameET;
    private EditText userPasswordET;
    private EditText confirmUserPasswordET;
    private EditText userEmailET;
    private String userName;
    private String userPassword;
    private String confirmUserPassword;
    private String userEmail;
    Button registerBt;

    DBUser dbUser;
    public static final String SHARED_PREFS = "com.wojcikowski.kamil.dietApp.sharedPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userNameET = findViewById(R.id.userName);
        userPasswordET = findViewById(R.id.userPassword);
        confirmUserPasswordET = findViewById(R.id.confirmUserPassword);
        userEmailET = findViewById(R.id.userEmail);

        registerBt = findViewById(R.id.registerBt);
        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    public void register() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        dbUser = new DBUser(getApplicationContext());
        dbUser.open();
        initialize();
        if(!validate()) {
            Toast.makeText(this, "Signup has Failed.", Toast.LENGTH_SHORT).show();
        } else {
            User user = new User(userName, userPassword, userEmail);
            dbUser.insertUser(user);
            dbUser.close();
            editor.putString(getString(R.string.loggedUser), user.getUsername());
            editor.apply();
            onSignupSuccess();
        }
    }

    public void onSignupSuccess() {
        startActivity(new Intent(RegisterActivity.this, UserDetailsActivity.class));
    }

    public boolean validate() {
        boolean valid = true;
        if(userName.isEmpty() || userName.length() < 4){
            userNameET.setError("Please enter valid name.");
            valid = false;
        } else if (dbUser.findUserByUsername(userName) != null){
            userNameET.setError("Username exists.");
            valid = false;
        }
        if(userEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            userEmailET.setError("Please enter valid email address.");
            valid = false;
        } else if (dbUser.findUserByEmail(userEmail) != null){
            userEmailET.setError("Email exists.");
            valid = false;
        }
        if(userPassword.isEmpty()) {
            userPasswordET.setError("Please enter valid password.");
            valid = false;
        }
        if(confirmUserPassword.isEmpty() || !confirmUserPassword.equals(userPassword)) {
            confirmUserPasswordET.setError("Please confirm password.");
            userPasswordET.setText("");
            valid = false;
        }
        return valid;
    }

    public void initialize() {
        userName = userNameET.getText().toString().trim();
        userPassword = userPasswordET.getText().toString().trim();
        confirmUserPassword = confirmUserPasswordET.getText().toString().trim();
        userEmail = userEmailET.getText().toString().trim();
    }
}
