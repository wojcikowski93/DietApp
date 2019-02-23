package com.wojcikowski.kamil.dietapp.activity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.wojcikowski.kamil.dietapp.R;
import com.wojcikowski.kamil.dietapp.database.DBUser;
import com.wojcikowski.kamil.dietapp.model.User;
import com.wojcikowski.kamil.dietapp.model.UserDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserDetailsActivity extends AppCompatActivity {

    private static final String TAG = "UserDetails";

    private EditText birthdayET;
    private EditText heightET;
    private EditText targetWeightET;

    private Date birthday;
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
        setContentView(R.layout.activity_user_details);

        heightET = findViewById(R.id.userHeight);
        targetWeightET = findViewById(R.id.userTargetWeight);
        birthdayET = findViewById(R.id.birthdayPicker);
        Button confirmBt = findViewById(R.id.confirmDetailsBt);

        birthdayET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadDatePicker();
            }
        });

        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveUserDetails();
                } catch (ParseException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        });

    }

    private void saveUserDetails() throws ParseException {
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

        //TODO: on details success
    }

    private void initialize() throws ParseException {
        targetWeight = Double.parseDouble(targetWeightET.getText().toString());
        height = Integer.parseInt(heightET.getText().toString());
        birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthdayET.getText().toString());
    }

    private void loadDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListener,
                year, month, day);

        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = day + "/" + month + "/" + year;
                birthdayET.setText(date);
            }
        };
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
