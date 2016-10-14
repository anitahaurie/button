package com.example.anatoly.assignment3;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class ReportActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Menu actionBar;

    Spinner spinner = null; // (Spinner) findViewById(R.id.my_spinner);
    String spin_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        spinner = (Spinner) findViewById(R.id.my_spinner);
        spinner.setOnItemSelectedListener(this);
        loadSpinnerData();
    }

    public void loadSpinnerData() {
        DBHandler db = new DBHandler(getApplicationContext());
        List<String> labels = db.getAllRunLogIDs();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.my_spinner);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
     //   String label = parent.getItemAtPosition(position).toString();     //TODO remove, prbably doesn't matter
        spinner = (Spinner) findViewById(R.id.my_spinner);
        spin_id = spinner.getSelectedItem().toString();

        DBHandler db = new DBHandler(getApplicationContext());
        RunLog log = db.getRunLog(Integer.parseInt(spin_id));

        // populate report page based on selected spinner item
        TextView text = (TextView) findViewById(R.id.tvDistanceVal);
        text.setText(Float.toString(log.getDistance()));

        text = (TextView) findViewById(R.id.tvDurationVal);
        text.setText(Float.toString(log.getDuration()));

        text = (TextView) findViewById(R.id.tvAvgSpeedVal);
        text.setText(Float.toString(log.getAvgSpeed()));

        text = (TextView) findViewById(R.id.tvCaloriesVal);
        text.setText(Integer.toString(log.getCalories()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO: make this useful?
    }
}
