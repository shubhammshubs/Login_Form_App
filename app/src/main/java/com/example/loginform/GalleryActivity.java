package com.example.loginform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Objects;

public class GalleryActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#03DAC5"));
        // Set BackgroundDrawable
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(colorDrawable);
        // Set the custom action bar layout
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_title);
        TextView actionBarTitle = getSupportActionBar().getCustomView().findViewById(R.id.action_bar_title);
        actionBarTitle.setText("Gallery");


        // calling the action bar for going back page
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Enable the back button
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// Handle the back button click
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}