package com.example.anita.assignment1;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class FindMeActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    public final static String TAG = "Coordinates_Activity: ";

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationManager mLocationManager;
    TextView mLatitude;
    TextView mLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        try
        {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        catch(java.lang.NullPointerException excep)
        {
            Log.d(TAG, "No action Bar");
        }

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    protected void onStart()
    {
        super.onStart();
        mGoogleApiClient.connect();

        StaticData.getInstance().setShowSnackbar(true);
    }

    protected void onStop()
    {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle)
    {

       try
        {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
        }

        catch (SecurityException excep)
        {
            mLatitude = (TextView)findViewById(R.id.latView);
            mLatitude.setText("Location Disabled");
        }
        //mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null)
        {
            Log.d(TAG, "onConnected: mLastLocation");
            mLatitude = (TextView)findViewById(R.id.latView);
            mLatitude.setText("Latitude: " + String.valueOf(mLastLocation.getLatitude()));

            mLongitude = (TextView)findViewById(R.id.lonView);
            mLongitude.setText("Longitude: " + String.valueOf(mLastLocation.getLongitude()));
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
