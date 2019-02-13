package com.wojcikowski.kamil.dietapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wojcikowski.kamil.dietapp.R;
import com.wojcikowski.kamil.dietapp.database.DatabaseHandler;
import com.wojcikowski.kamil.dietapp.model.User;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_userName, et_userPassword, et_confirmUserPassword, et_userEmail;
    private String userName, userPassword, confirmUserPassword, userEmail;
    Button registerBt;

    DatabaseHandler dbHandler;

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
        dbHandler = new DatabaseHandler(getApplicationContext());
        dbHandler.open();
        List<User> userList = dbHandler.getAllUsers();
        for(User user : userList){
            System.out.println(user.getUser_id() + ": " + user.getUsername());
        }
        initialize();
        if(!validate()) {
            Toast.makeText(this, "Signup has Failed.", Toast.LENGTH_SHORT).show();
        } else {
            User user = new User(userName, userPassword, userEmail);
            dbHandler.insertUser(user);
            dbHandler.close();
            onSignupSuccess();
        }
    }

    public void onSignupSuccess() {
        startActivity(new Intent(RegisterActivity.this, Test.class));

        //if(userDetailsEmpty()){
            //TODO: go to user detail activity
       // } else {
            //TODO: go to main view activity
        //}
    }

    public boolean validate() {
        boolean valid = true;
        if(userName.isEmpty() || userName.length() < 4){
            et_userName.setError("Please enter valid name.");
            valid = false;
        } else if (dbHandler.usernameExists(userName)){
            et_userName.setError("Username exists.");
            valid = false;
        }
        if(userEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            et_userEmail.setError("Please enter valid email address.");
            valid = false;
        } else if (dbHandler.emailExists(userEmail)){
            et_userEmail.setError("Email exists.");
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
