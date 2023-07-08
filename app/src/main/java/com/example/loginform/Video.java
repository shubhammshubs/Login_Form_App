package com.example.loginform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#03DAC5"));
        // Set BackgroundDrawable
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        // Set the custom action bar layout
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_title);
        TextView actionBarTitle = getSupportActionBar().getCustomView().findViewById(R.id.action_bar_title);
        actionBarTitle.setText("Video");


        // calling the action bar for going back page
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Enable the back button
        }

        VideoView videoView = findViewById(R.id.videoView);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video1;
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);


        videoView.start();





    }


        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    // Handle the back button click
                    onBackPressed();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);

            }
        }




}