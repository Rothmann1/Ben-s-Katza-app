package com.katza.bensapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class ActivityFirst extends AppCompatActivity {

    private EditText etFirstName, etLastName, etAge;
    private TextView tvAgeResult;
    private Button btnSendAll, btnGetBirthYear;

    // The Launcher to get data BACK from Page 2
    private final ActivityResultLauncher<Intent> birthYearLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    int birthYear = result.getData().getIntExtra("BIRTH_YEAR", 0);
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    int calculatedAge = currentYear - birthYear;

                    // Update the new TextView with the calculated result
                    tvAgeResult.setText("Calculated Age: " + calculatedAge);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // Initialize all elements
        etFirstName = findViewById(R.id.edit_text_first_name);
        etLastName = findViewById(R.id.edit_text_last_name);
        etAge = findViewById(R.id.edit_text_age);
        tvAgeResult = findViewById(R.id.text_view_age_display);
        btnSendAll = findViewById(R.id.button_send);
        btnGetBirthYear = findViewById(R.id.button_go_to_birth);

        // Original Button: Sends everything to Page 2
        btnSendAll.setOnClickListener(v -> {
            String ageStr = etAge.getText().toString();
            int age = ageStr.isEmpty() ? 0 : Integer.parseInt(ageStr);

            Intent intent = new Intent(ActivityFirst.this, ActivitySecond.class);
            intent.putExtra("FIRST_NAME", etFirstName.getText().toString());
            intent.putExtra("LAST_NAME", etLastName.getText().toString());
            intent.putExtra("USER_AGE", age);
            startActivity(intent);
        });

        // New Button: Goes to Page 2 specifically to get Birth Year back
        btnGetBirthYear.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityFirst.this, ActivitySecond.class);
            birthYearLauncher.launch(intent);
        });
    }
}