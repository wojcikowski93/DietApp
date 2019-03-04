package com.wojcikowski.kamil.dietapp.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wojcikowski.kamil.dietapp.R;
import com.wojcikowski.kamil.dietapp.database.DBDetails;
import com.wojcikowski.kamil.dietapp.database.DBUser;
import com.wojcikowski.kamil.dietapp.model.User;
import com.wojcikowski.kamil.dietapp.model.UserDetails;

import java.time.LocalDate;
import java.util.Calendar;

public class UserDetailsActivity extends AppCompatActivity {

    private TextView birthdayTV;
    private EditText heightET;
    private EditText targetWeightET;

    private LocalDate birthday;
    private String gender;
    private int height;
    private Double targetWeight;

    DBUser dbUser;
    DBDetails dbDetails;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    public static final String SHARED_PREFS = "com.wojcikowski.kamil.dietApp.sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String loggedUser = sharedPreferences.getString("loggeduser", "");
        dbUser = new DBUser((getApplicationContext()));
        dbUser.open();
        User user = dbUser.findUserByUsername(loggedUser);
        dbUser.close();

        dbDetails = new DBDetails((getApplicationContext()));
        dbDetails.open();
        UserDetails userDetails = dbDetails.findDetailsByUserID(user.getUserid());
        dbDetails.close();

        RadioButton femaleRB = findViewById(R.id.femaleRB);
        RadioButton maleRB = findViewById(R.id.maleRB);

        if(userDetails != null && areDetailsFulfilled(userDetails)) {
            startActivity(new Intent(UserDetailsActivity.this, MainActivity.class));
        } else {
            setContentView(R.layout.activity_user_details);

            heightET = findViewById(R.id.userHeight);
            targetWeightET = findViewById(R.id.userTargetWeight);
            birthdayTV = findViewById(R.id.birthdayPicker);
            Button confirmBt = findViewById(R.id.confirmDetailsBt);

            birthdayTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            UserDetailsActivity.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            dateSetListener,
                            year, month, day);

                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    datePickerDialog.show();
                }
            });

            dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;

                    String date = year + "-" + month + "-" + day;
                    birthdayTV.setText(date);
                }
            };

            confirmBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveUserDetails();
                }
            });

            if(userDetails != null && userDetails.getHeight() != 0){
                heightET.setText(String.valueOf(userDetails.getHeight()));
            }
            if(userDetails != null && userDetails.getGender() != null && userDetails.getGender().equals("female")){
                femaleRB.setChecked(true);
            } else if (userDetails != null && userDetails.getGender() != null && userDetails.getGender().equals("male")) {
                maleRB.setChecked(true);
            }
            if(userDetails != null && userDetails.getTargetWeight() != null) {
                targetWeightET.setText(userDetails.getTargetWeight().toString());
            }
            if(userDetails != null && userDetails.getBirthday() != null) {
                birthdayTV.setText(userDetails.getBirthday());
            }
        }
    }

    private boolean areDetailsFulfilled(UserDetails userDetails) {
        return userDetails.getBirthday() != null
                && userDetails.getGender() != null
                && userDetails.getHeight() != 0
                && userDetails.getTargetWeight() != null;
    }

    private void saveUserDetails() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String loggedUser = sharedPreferences.getString("loggeduser", "");
        dbUser = new DBUser((getApplicationContext()));
        dbUser.open();
        User user = dbUser.findUserByUsername(loggedUser);
        dbUser.close();
        dbDetails = new DBDetails(getApplicationContext());
        dbDetails.open();
        initialize();
        UserDetails userDetails = new UserDetails(user.getUserid(), gender, birthday, height, targetWeight);
        dbDetails.insertDetails(userDetails);
        dbDetails.close();

        //TODO: go to Measurements Activity
        startActivity(new Intent(UserDetailsActivity.this, MainActivity.class));
    }

    private void initialize() {
        targetWeight = Double.parseDouble(targetWeightET.getText().toString());
        height = Integer.parseInt(heightET.getText().toString());
        String[] date = birthdayTV.getText().toString().split("-");
        birthday = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        int i = view.getId();
        if (i == R.id.femaleRB && checked) {
            gender = "female";
        } else if (i != R.id.maleRB || !checked) {
            return;
        }
        gender = "male";
    }
}
