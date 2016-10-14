package com.example.anita.assignment2;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by anita on 2016-09-23.
 */
public class NoEditFragment extends Fragment {

    public final static String TAG = "NoEditFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide Save button
        Button mSaveButton = (Button) getActivity().findViewById(R.id.button_save);
        mSaveButton.setVisibility(View.INVISIBLE);

        //Disable text editing
        EditText mEdit = (EditText) getActivity().findViewById(R.id.editTextName);
        mEdit.setEnabled(false);

        //Set values in text boxes. Default if not set, otherwise, values from sharedPreferences
        SharedPreferences sharedPref = getActivity().getSharedPreferences("Profile_Preferences", 0);
        mEdit.setText(sharedPref.getString(getString(R.string.saved_name), getString(R.string.default_empty)));

        Log.d(TAG, "OnCreate");

    }
}