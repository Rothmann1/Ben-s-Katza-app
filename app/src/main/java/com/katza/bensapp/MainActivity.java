package com.katza.bensapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    
    Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();


    }
    
    private void initViews() {
        btn1 = findViewById(R.id.btnClick1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btnClick2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btnClick3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btn1) Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
        else if (v == btn2) Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}