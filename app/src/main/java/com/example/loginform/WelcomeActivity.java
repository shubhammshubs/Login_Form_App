package com.example.loginform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.Spinner_Image;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 4; // Number of slides


//    private BluetoothAdapter bluetoothAdapter;


    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 500; // Delay in milliseconds before the slider changes slide
    private final long PERIOD_MS = 3000; // Interval in milliseconds between each slide change
    private TextView welcomeTextView;
    private ViewPager2 viewPager;
    private SliderAdapter sliderAdapter;
    ImageButton ImageButton;



//_______________________________________________ - On Create Method _________________________________________________

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

// code for slider
        viewPager = findViewById(R.id.viewPager);
        sliderAdapter = new SliderAdapter(getImageItems());
        viewPager.setAdapter(sliderAdapter);

        // Auto-scroll the slider
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, DELAY_MS, PERIOD_MS);

//      Title of Page Action Bar
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

        welcomeTextView = findViewById(R.id.welcomeTextView);

        String message = getIntent().getStringExtra("message");
        welcomeTextView.setText(message);


        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Enable the back button
        }


//        Creating Image Button for Gallery Activity
        ImageButton = (ImageButton) findViewById(R.id.btnNextPage);

        ImageButton ImageButton1 = (ImageButton) findViewById(R.id.btnVideoPage);

//      For Gallery
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeActivity.this, "Gallery", Toast.LENGTH_SHORT).show();
                openNextPage();
            }
        });
//      For Video Page
        ImageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeActivity.this, "Videos", Toast.LENGTH_SHORT).show();
                openVideoPage();
            }
        });



    }
// Button for Gallery Activity Page
    private void openNextPage() {
        Intent intent = new Intent(WelcomeActivity.this, GalleryActivity.class);
        startActivity(intent);
    }
//Button for Video page
    private void openVideoPage() {
        Intent intent = new Intent(WelcomeActivity.this, Video.class);
        startActivity(intent);
    }

//________________________________________-Switch Case On Option Item Select ________________________________________________

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle the back button click
                onBackPressed();
                return true;
            case R.id.home1:
                // Handle the home button click from the menu
                this.finish();
                return true;


            case R.id.About:
                Toast.makeText(WelcomeActivity.this, "About", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.gallery1:
                Toast.makeText(WelcomeActivity.this, "Gallery", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.image:
                if (item.getItemId() == R.id.image) {
                    Intent intent = new Intent(WelcomeActivity.this, Spinner_Image.class);
                    startActivity(intent);

                Toast.makeText(WelcomeActivity.this, "Images", Toast.LENGTH_SHORT).show();
                return true;
                }

            case R.id.video:
                if (item.getItemId() == R.id.video) {
                    Intent intent = new Intent(WelcomeActivity.this, Video.class);
                    startActivity(intent);

                    Toast.makeText(WelcomeActivity.this, "Video", Toast.LENGTH_SHORT).show();
                    return true;
                }


            case R.id.Feedback:
                displayFeedbackPopup();

//                Toast.makeText(WelcomeActivity.this,"Feedback",Toast.LENGTH_SHORT).show();
                return true;


            case R.id.Shareit:
                Toast.makeText(WelcomeActivity.this, "Share", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Bluetooth:
                shareApp();
                return true;

            case R.id.menu_share_whatsapp:
                shareApkOnWhatsApp();
                return true;

//
//                String textToShare = "Hello, this is the text I want to share";
//                shareTextToWhatsApp(textToShare);
////                sendAppItself();
//                return true;


            case R.id.Logout:
                // Navigate to the login page
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Optional: Finish the current activity to prevent navigating back to it
                Toast.makeText(WelcomeActivity.this, "Log Out", Toast.LENGTH_SHORT).show();

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }


    }

// Log Out method to Log Out of page
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    // Create a method to share text to WhatsApp

//    private void shareTextToWhatsApp(String text) {
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("text/plain");
//        intent.setPackage("com.whatsapp");
//        intent.putExtra(Intent.EXTRA_TEXT, text);
//
//        try {
//            startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            // WhatsApp is not installed or not supported
//            Toast.makeText(this, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();
//        }
//    }

//    public static void sendAppItself(Activity paramActivity) throws IOException {
//        PackageManager pm = paramActivity.getPackageManager();
//        ApplicationInfo appInfo;
//        try {
//            appInfo = pm.getApplicationInfo(paramActivity.getPackageName(),
//                    PackageManager.GET_META_DATA);
//            Intent sendBt = new Intent(Intent.ACTION_SEND);
//            sendBt.setType("*/*");
//            sendBt.putExtra(Intent.EXTRA_STREAM,
//                    Uri.parse("file://" + appInfo.publicSourceDir));
//
//            paramActivity.startActivity(Intent.createChooser(sendBt,
//                    "Share it using"));
//        } catch (PackageManager.NameNotFoundException e1) {
//            e1.printStackTrace();
//        }
//    }

    public void shareApp() {
        try {
            // Get the APK file of the app to be shared
            ApplicationInfo applicationInfo = getApplicationContext().getApplicationInfo();
            String apkPath = applicationInfo.sourceDir;

            // Create the intent to send the APK file
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));

            // Start the activity to share the app
            startActivity(Intent.createChooser(intent, "Share via"));
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
            Toast.makeText(this, "Unable to share the app", Toast.LENGTH_SHORT).show();
        }
    }







    private void shareApkOnWhatsApp() {
        // Get the File object of the APK
        File apkFile = new File(getApplicationInfo().sourceDir);

        // Get the URI for the APK file using FileProvider
        Uri apkUri = FileProvider.getUriForFile(this, "com.example.loginform.FileProvider", apkFile);

        // Create the intent to share the APK file on WhatsApp
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/vnd.android.package-archive");
        intent.putExtra(Intent.EXTRA_STREAM, apkUri);
        intent.setPackage("com.whatsapp");

        // Grant read permission to the content URI
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Check if WhatsApp is installed on the device
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Start the activity to share the APK file
            startActivity(intent);
        } else {
            // WhatsApp is not installed, show an error message or handle it accordingly
            Toast.makeText(this, "WhatsApp is not installed.", Toast.LENGTH_SHORT).show();
        }
    }




    //  FeedBack Code
    private void displayFeedbackPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Feedback")
                .setItems(R.array.feedback_options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String[] feedbackOptions = getResources().getStringArray(R.array.feedback_options);
                        String selectedFeedback = feedbackOptions[which];
                        Toast.makeText(WelcomeActivity.this, "You selected: " + selectedFeedback, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }






    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu); // Use the menu resource
        return true;
    }

//    Slider Image
    private List<ImageItem> getImageItems() {
        List<ImageItem> imageItems = new ArrayList<>();
        imageItems.add(new ImageItem(R.drawable.i2));
        imageItems.add(new ImageItem(R.drawable.i1));
        imageItems.add(new ImageItem(R.drawable.i3));
        return imageItems;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel the timer when the activity is destroyed to prevent memory leaks
        if (timer != null) {
            timer.cancel();
        }
    }








}
