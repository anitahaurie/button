package com.example.anatoly.assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

// TODO make sure the report activity remembers the selected item
// TODO limit number of elements in db
// TODO pop-up message before overwriting items (5 item memory)

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*DBHandler db = new DBHandler(this);

        Log.d("Insert: ", "Inserting ..");      // inserting test rows

        db.addRunLog(new RunLog(1, 45,45));
        db.addRunLog(new RunLog(2, 50,50));

        // Reading all shops
        Log.d("Reading: ", "Reading all shops..");
        List<RunLog> logs = db.getAllRunLogs();

        for (RunLog log : logs) {
            String print = "Id: " + log.getID() + " ,Name: " + log.getDistance() + " ,Address: " + log.getDuration();
            // Writing shops to log
            Log.d("Shop: : ", print);
        }*/
    }

    // open ReportActivity
    public void openReports(View view) {
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }

    public void generateData(View view) {
        DBHandler db = new DBHandler(this);

        RunLog log = new RunLog(1, 20.0f, 30.0f);
        db.addRunLog(log);        //  TODO add random values
        db.addRunLog(new RunLog(2, 40.0f, 60.0f));        //  TODO add random values
    }

}
