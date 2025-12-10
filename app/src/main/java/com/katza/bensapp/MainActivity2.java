package com.katza.bensapp;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    SharedPreferences sp;
    Button btnSave;
    EditText name;
    TextView display;
    ConstraintLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Dialog d;
        btnSave = findViewById(R.id.btnSave);
        display = findViewById(R.id.display);
        sp = getSharedPreferences("spBen",0);
        String nameDisplay = sp.getString("name",null);
        if (nameDisplay != null) display.setText("Hello " + nameDisplay);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name", name.getText().toString());
                editor.commit();
                createDialog();
            }
        });
        name = findViewById(R.id.name);


    }

    public void createDialog() {
        Dialog d = new Dialog(this);
        d.setContentView(R.layout.dialog);
        d.setTitle("title");
        d.setCancelable(true);
        d.show();
    }
}