package com.example;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginform.R;

public class Spinner_Image extends AppCompatActivity {



    private Spinner spinner;
    private ImageView image;


    private final int[] images = {R.drawable.one, R.drawable.three, R.drawable.two};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_image);

//        Title of Page Action Bar
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
        actionBarTitle.setText("Home");

        spinner = findViewById(R.id.spinner);
        image = findViewById(R.id.image);

        String[] number = {"Select one","One", "Two", "Three"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, number);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position){
                    case 1:
                        image.setImageResource(R.drawable.one);
                        Toast.makeText(Spinner_Image.this,"Hello",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        image.setImageResource(R.drawable.two);
                        break;
                    case 3:
                        image.setImageResource(R.drawable.three);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}