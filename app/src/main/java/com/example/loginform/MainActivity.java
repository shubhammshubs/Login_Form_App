package com.example.loginform;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText txtEmail,txtPwd;
    private Button btnLogin;

    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
        actionBarTitle.setText("Login");


        txtEmail = findViewById(R.id.txtEmail);
        txtPwd =  findViewById(R.id.txtPwd);
        btnLogin = findViewById(R.id.btnLogin);
        TextView register = (TextView)findViewById(R.id.lnkRegister);





        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = txtEmail.getText().toString();
                String enteredPassword = txtPwd.getText().toString();

                if (enteredUsername.equals(CORRECT_USERNAME) && enteredPassword.equals(CORRECT_PASSWORD)) {
                    // Login successful, show another activity
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    intent.putExtra("message", "Welcome, " + CORRECT_USERNAME + "!");
                    startActivity(intent);
                } else {
                    // Login failed, show error message
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Please Register", Toast.LENGTH_SHORT).show();
                }


        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
});
};}