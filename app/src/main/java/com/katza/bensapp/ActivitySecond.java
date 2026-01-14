package com.katza.bensapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // UI for receiving
        TextView tvReceived = findViewById(R.id.text_view_result);
        EditText etBirthYear = findViewById(R.id.edit_text_birth_year);
        Button btnOk = findViewById(R.id.button_ok);

        // Get data from the First Intent
        String fName = getIntent().getStringExtra("FIRST_NAME");
        String lName = getIntent().getStringExtra("LAST_NAME");
        int age = getIntent().getIntExtra("USER_AGE", 0);

        if (fName != null) {
            tvReceived.setText("Received: " + fName + " " + lName + " (Age: " + age + ")");
        }

        // Logic to send Birth Year BACK
        btnOk.setOnClickListener(v -> {
            String yearStr = etBirthYear.getText().toString();
            if (!yearStr.isEmpty()) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("BIRTH_YEAR", Integer.parseInt(yearStr));
                setResult(RESULT_OK, returnIntent);
                finish(); // This returns you to ActivityFirst
            } else {
                finish(); // Just close if empty
            }
        });
    }
}