package com.wojcikowski.kamil.dietapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_userName, et_userPassword, et_confirmUserPassword, et_userEmail;
    private String userName, userPassword, confirmUserPassword, userEmail;
    Button registerBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_userName = findViewById(R.id.userName);
        et_userPassword = findViewById(R.id.userPassword);
        et_confirmUserPassword = findViewById(R.id.confirmUserPassword);
        et_userEmail = findViewById(R.id.userEmail);

        registerBt = findViewById(R.id.registerBt);
        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    public void register() {
        initialize();
        if(!validate()) {
            Toast.makeText(this, "Signup has Failed.", Toast.LENGTH_SHORT).show();
        } else {
            onSignupSuccess();
        }
    }

    public void onSignupSuccess() {
        //TODO: registration   new User
    }

    public boolean validate() {
        boolean valid = true;
        if(userName.isEmpty() || userName.length() > 15){
            et_userName.setError("Please enter valid name.");
            valid = false;
        }
        if(userEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            et_userEmail.setError("Please enter valid email address.");
            valid = false;
        }
        if(userPassword.isEmpty()) {
            et_userPassword.setError("Please enter valid password.");
            valid = false;
        }
        if(confirmUserPassword.isEmpty() || !confirmUserPassword.equals(userPassword)) {
            et_confirmUserPassword.setError("Please confirm password.");
            et_userPassword.setText("");
            valid = false;
        }
        return valid;
    }

    public void initialize() {
        userName = et_userName.getText().toString().trim();
        userPassword = et_userPassword.getText().toString().trim();
        confirmUserPassword = et_confirmUserPassword.getText().toString().trim();
        userEmail = et_userEmail.getText().toString().trim();
    }
}
