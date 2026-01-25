package com.katza.bensapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class camera extends AppCompatActivity {

    Button btnCamera;
    ImageView imgPreview;

    // 1. Define the Launcher at the class level
    ActivityResultLauncher<Intent> cameraLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btnCamera = findViewById(R.id.button_camera);
        imgPreview = findViewById(R.id.image_preview);

        // 2. Initialize the Launcher (This is your "Receiver")
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            // Grab the data just like your ActivitySecond example
                            Bundle extras = result.getData().getExtras();
                            Bitmap imageBitmap = (Bitmap) extras.get("data");

                            // Display the photo
                            imgPreview.setImageBitmap(imageBitmap);
                        }
                    }
                }
        );

        // 3. Set the button to trigger the Launcher
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use the standard Intent filter action for camera
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraLauncher.launch(i);
            }
        });
    }
}