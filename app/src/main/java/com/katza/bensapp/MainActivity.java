package com.katza.bensapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SeekBar seekBar;
    CompoundButton sw1;
    View img1;

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
        img1 = findViewById(R.id.imageView);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // נקרא כאשר הערך (ה-Progress) משתנה
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // ה-Progress הוא מספר שלם (למשל 0 עד 100).
                // Alpha דורש מספר צף (float) בין 0.0 ל-1.0.
                // לכן, נמיר את ה-progress ל-float ונחלק ב-100f.

                float alphaValue = progress / 100f;
                img1.setAlpha(alphaValue);
            }

            // נקרא כאשר המשתמש מתחיל לגעת ב-SeekBar
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Hi", Toast.LENGTH_SHORT).show();            }

            // נקרא כאשר המשתמש עוזב את ה-SeekBar
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Bye", Toast.LENGTH_SHORT).show();            }
        });
        img1.setAlpha(0.0f);
        seekBar.setProgress(0);
    }
//        sw1 = findViewById(R.id.switch1);
//        sw1.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttomView, boolean isChecked) {
//                if (isChecked) {
//                    img1.setAlpha(1f);
//                } else {
//                    img1.setAlpha(0f);
//                }
//            }
//        });

    @Override
    public void onClick(View v) {

    }
}