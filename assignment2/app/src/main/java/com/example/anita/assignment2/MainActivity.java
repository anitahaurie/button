package com.example.anita.assignment2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Verify if data has been set
        if (!StaticData.getInstance().getIsInitialized())
        {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }

        Button mButton = (Button)this.findViewById(R.id.button_name);
        SharedPreferences sharedPref = this.getSharedPreferences("Profile_Preferences", 0);
        mButton.setText(sharedPref.getString(getString(R.string.saved_name), getString(R.string.default_empty)));

        Log.d(TAG, "onCreate: " + sharedPref.getString(getString(R.string.saved_name), getString(R.string.default_empty)));
    }

    public void DisplayInfo(View view)
    {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
