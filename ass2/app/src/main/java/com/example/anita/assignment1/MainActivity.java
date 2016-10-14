package com.example.anita.assignment1;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void FindMe(View view)
    {
        Intent intent = new Intent(this, FindMeActivity.class);
        startActivity(intent);
    }

    public void onStart()
    {
        super.onStart();
        if (StaticData.getInstance().getShowSnackbar())
        {
            ViewGroup layout = (ViewGroup) findViewById(R.id.activity_main);
            Snackbar mySnackbar = Snackbar.make(layout, "Location Found!", Snackbar.LENGTH_SHORT);
            mySnackbar.show();
            StaticData.getInstance().setShowSnackbar(false);
        }
    }
}
